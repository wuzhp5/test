package nc.bs.ic.onhand.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.impl.pubapp.env.BSContext;
import nc.jdbc.framework.SQLParameter;
import nc.ui.querytemplate.querytree.FromWhereSQL;
import nc.vo.bd.material.MaterialVO;
import nc.vo.bd.rack.RackVO;
import nc.vo.ic.icreport.FilterItemEqOrIn;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.onhand.entity.OnhandNumVO;
import nc.vo.ic.onhand.pub.OnhandQueryDim;
import nc.vo.ic.onhand.pub.OnhandSelectDim;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.ic.pub.sql.ICFromWhereSQL;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.pub.JavaType;
import nc.vo.pub.query.ConditionVO;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;

/**
 * <p>
 * <b>现存量查询视图</b>
 * 
 * @version v60
 * @since v60
 * @author yangb
 * @time 2010-4-19 下午07:58:42
 */
@SuppressWarnings("serial")
public class OnhandQueryView extends AbstractOnhandView {

  private final OnhandLeftWithBatchView handinnerview;
  
  public static final String ViewAlias = "hand";
  
  /**
   * OnhandQueryView 的构造子
   */
  public OnhandQueryView() {
    super();
    this.handinnerview = new OnhandLeftWithBatchView();
    this.handinnerview.setAutoJoin(true);
  }

  /**
   * OnhandQueryView 的构造子
   */
  public OnhandQueryView(OnhandSelectDim select, ICFromWhereSQL fromwhere) {
    this.addViewFields(AbstractOnhandView.onhanddimmeta, ViewAlias, select
        .getSelectFields());
    if (select.isSum()) {
      this.addSumNumFields(ViewAlias);
    }
    else {
      this.addNumFields(ViewAlias);
    }
    this.handinnerview = new OnhandLeftWithBatchView(fromwhere);
    this.handinnerview.setAutoJoin(true);
    this.handinnerview.initView();
  }

  /**
   * OnhandQueryView 的构造子
   */
  public OnhandQueryView(OnhandSelectDim select, OnhandQueryDim dimcond) {
    this.addViewFields(AbstractOnhandView.onhanddimmeta, ViewAlias, select
        .getSelectFields());
    if (select.isSum()) {
      this.addSumNumFields(ViewAlias);
    }
    else {
      this.addNumFields(ViewAlias);
    }
    this.handinnerview = new OnhandLeftWithBatchView();
    this.handinnerview.initView();
    this.handinnerview.setOnhandQueryDim(dimcond);
  }
  
  public void addSelectDimField(String field,String fieldalias) {
    this.addViewField(AbstractOnhandView.onhanddimmeta, ViewAlias, fieldalias, fieldalias);
    this.handinnerview.addSelectField(OnhandNumVO.PK_ONHANDDIM, field, fieldalias);
  }
  
  public void addSelectNumFields(boolean isSum) {
    if (isSum) {
      this.addSumNumFields(ViewAlias);
    }else{
      this.addNumFields(ViewAlias);
    }
    this.handinnerview.addSelectFields(".", OnhandNumVO.numfields,
        OnhandNumVO.numfields);
  }
  
  public void addSelectBatchFields(String field) {
    this.addViewField(AbstractOnhandView.batchmeta, ViewAlias, field, field);
    this.handinnerview.addSelectField(OnhandNumVO.PK_ONHANDDIM+"."+OnhandDimVO.PK_BATCHCODE, field, field);
  }

  public void addInnerOtherCondForPick() {
    this.handinnerview.addLeftJoinLocation();
    this.handinnerview.addSelectField(OnhandNumVO.PK_ONHANDDIM + "."
        + OnhandDimVO.CLOCATIONID, RackVO.OUTPRIORITY, RackVO.OUTPRIORITY);
    this.handinnerview.addSelectField(OnhandNumVO.PK_ONHANDDIM + "."
            + OnhandDimVO.CLOCATIONID, RackVO.NAME, "rackname");
    this.addViewSortFields(ViewAlias, true, new String[] {
      OnhandDimVO.PK_ORG, OnhandDimVO.CMATERIALOID, OnhandDimVO.CMATERIALVID
    });
    this.addViewSortFields(ViewAlias, true, new String[] {
      BatchcodeVO.DVALIDATE
    });
    this.addViewSortFields(ViewAlias, false, new String[] {
      RackVO.OUTPRIORITY
    });
  }

  /**
   * matepathexpress: 元数据路径表达式
   */
  public void addInnerWhereByMetaPathExpress(String matepathexpress) {
    this.handinnerview.addWhereByAttrPathExp(matepathexpress);
  }
  
  public void addMaterialClassGroup(String selclfield,String selclalias, int ilevel) {
    this.addViewField(OnhandQueryView.ViewAlias, 
        selclalias, selclalias, JavaType.String);
    this.handinnerview.addMaterialClassGroup(
        OnhandNumVO.PK_ONHANDDIM+"."+OnhandDimVO.CMATERIALOID+"."+MaterialVO.PK_MARBASCLASS, 
        selclfield, selclalias, ilevel);
  }

  /**
   * 
   */
  public void addInnerWherePart(String where) {
    this.handinnerview.addWherePart(where);
  }

  public void addMaterialClassWhere(String[] pk_materialclassids) {
    if (ValueCheckUtil.isNullORZeroLength(pk_materialclassids)) {
      return;
    }
    this.handinnerview.setMutiMaterialClass(OnhandNumVO.PK_ONHANDDIM + "."
        + OnhandDimVO.CMATERIALOID + "." + MaterialVO.PK_MARBASCLASS,
        pk_materialclassids);
  }

	/**
	 * 加入仓库废品仓
	 * 
	 * @param joinstore
	 *            ,是否加入仓库内连接
	 * @param gubflag
	 *            ,是否过滤废品仓
	 */
	public void addJoinStoredoc(boolean gubflag) {
		this.handinnerview.addJoinStoredoc(gubflag);
	}
	
	
	public void addAtpAffected(){
	  this.handinnerview.addAtpStoredoc();
	}

  public void addJoinNotDirectStoredoc(boolean isdirectstore) {
    this.handinnerview.addJoinNotDirectStoredoc(isdirectstore);
  }
  /**
   * @return sqlparam
   */
  public SQLParameter getSqlparam() {
    return this.handinnerview.getSqlparam();
  }

  /**
   * 
   */
  @Override
  public String getViewSql() {
	  Object obj=BSContext.getInstance().getSession("ManufactureDatePickAutoAction");
    StringBuilder bf = new StringBuilder(" select ");
    bf.append(this.getSelectFieldsPart());
    bf.append(" from (");
    bf.append(this.handinnerview.getViewSql());
    bf.append(") "+ViewAlias+" ");
    if (!StringUtil.isSEmptyOrNull(this.getWhere())) {
      bf.append(" where ");
      bf.append(this.getWhere());
    }
    if (this.isGroup()) {
      bf.append(" group by ");
      bf.append(this.getGroupFieldsPart());
      if (!StringUtil.isSEmptyOrNull(this.getHaving())) {
        bf.append(" having ");
        bf.append(this.getHaving());
      }
    }
    if(obj!=null){
    	 bf.append(" order by  hand.vbatchcode,hand.rackname");
    }else if (this.isOrder()) {
      bf.append(" order by ");
      bf.append(this.getOrderFieldsPart());
    }
    return bf.toString();
  }

  /**
   * 动态处理表连接及Where
   * 
   * @param conds 条件 要求fieldcode 应当是元数据路径
   */
  public void processFromAndWhere(ConditionVO[] conds) {
    this.dealConditiVO(conds);
  }

  /**
   * 动态处理表连接及Where
   * 
   * @param mapconds key---元数据路径 value 条件
   */
  public void processFromAndWhere(Map<String, List<ConditionVO>> mapconds) {
    this.handinnerview.processFromAndWhere(mapconds);
  }

  /**
   * @param ctranstype 要设置的 ctranstype
   */
  public void setCtranstype(List<String> ctranstypes) {
    this.handinnerview.setCtranstype(ctranstypes);
  }

  /**
   * @param isuseablestate 要设置的 isuseablestate
   */
  public void setIsuseablestate(boolean isuseablestate) {
    this.handinnerview.setIsuseablestate(isuseablestate);
  }
  
  public void setOnhandQueryDim(OnhandQueryDim dimcond) {
    this.handinnerview.setOnhandQueryDim(dimcond);
  }

  /**
   * @param otherJoinPart 要设置的 otherJoinPart
   */
  public void setOtherJoinPart(String otherJoinPart) {
    this.handinnerview.setOtherJoinPart(otherJoinPart);
  }

  /**
   * 过滤物料分类
   * 
   * @param conds
   */
  private void dealConditiVO(ConditionVO[] conds) {
    if (ValueCheckUtil.isNullORZeroLength(conds)) {
      return;
    }
    String fieldcode = null;
    String materialBasClass =
        OnhandNumVO.PK_ONHANDDIM + FromWhereSQL.DEFAULT_ATTRPATH
            + ICPubMetaNameConst.CMATERIALOID + FromWhereSQL.DEFAULT_ATTRPATH
            + MaterialVO.PK_MARBASCLASS;
    List<ConditionVO> condList = new ArrayList<ConditionVO>();
    for (ConditionVO vo : conds) {
      fieldcode = vo.getFieldCode();
      if (fieldcode == null) {
        continue;
      }
      // 特殊处理物料分类
      if (materialBasClass.equalsIgnoreCase(fieldcode)
          && !StringUtil.isSEmptyOrNull(vo.getValue())) {
        this.dealMaterialClass(materialBasClass, vo);
        continue;
      }
      condList.add((ConditionVO) vo.clone());
    }
    this.handinnerview
        .processFromAndWhere(condList.toArray(new ConditionVO[0]));
  }

  /**
   * 处理物料分类及下级
   * 
   * @param materialBasClass
   * @param vo
   */
  private void dealMaterialClass(String materialBasClass, ConditionVO vo) {
    String[] values = FilterItemEqOrIn.getValues(vo);
    this.handinnerview.setMutiMaterialClass(materialBasClass, values);
  }

}
