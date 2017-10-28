package nc.impl.ic.flowaccount.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.ic.flowaccount.data.TransConfigQuery;
import nc.bs.ic.pub.db.ICDBVisitor;
import nc.bs.ic.pub.db.SqlIn;
import nc.bs.ic.pub.env.ICBSContext;
import nc.bs.logging.Logger;
import nc.impl.ic.flowaccount.bp.FlowAccountBaseQry;
import nc.impl.pubapp.env.BSContext;
import nc.pubitf.ic.flowaccount.BillInfo;
import nc.pubitf.ic.flowaccount.FlowAccountQuery;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.vo.ic.admin.ICModuleManager;
import nc.vo.ic.flowaccount.entity.FlowAccountDetailVO;
import nc.vo.ic.flowaccount.entity.FlowAccountVO;
import nc.vo.ic.flowaccount.entity.FlowAccountView;
import nc.vo.ic.flowaccount.entity.FlowQueryConditionVO;
import nc.vo.ic.flowaccount.view.AbstractFlowView;
import nc.vo.ic.flowaccount.view.FlowAccountDetailHisView;
import nc.vo.ic.flowaccount.view.FlowQueryView;
import nc.vo.ic.flowaccount.view.KeepDetailViewForSearch;
import nc.vo.ic.flowaccount.view.OuttrackinView;
import nc.vo.ic.flowaccount.view.OuttrackinViewForSearch;
import nc.vo.ic.m50.entity.VmiSumViewVO;
import nc.vo.ic.material.define.OutPriority;
import nc.vo.ic.onhand.define.OnhandRes;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.onhand.entity.OnhandNumVO;
import nc.vo.ic.onhand.entity.OnhandVO;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.ic.pub.sql.AttributeResultSet;
import nc.vo.ic.pub.sql.VOResultSet;
import nc.vo.ic.pub.util.CollectionUtils;
import nc.vo.ic.pub.util.NCBaseTypeUtils;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.ic.pub.util.ValueCheckUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.ICalendar;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.SqlBuilder;
import nc.vo.pubapp.query2.sql.process.QuerySchemeProcessor;

/**
 * <b> �����ˮ�ʲ�ѯ����ʵ�� </b>
 * <p>
 * </p>
 * ��������:2009-07-16 09:29:18
 * 
 * @author yangb
 * @version v60
 */
public class FlowAcountQueryImpl implements FlowAccountQuery {
  /**
   *
   */
  public FlowAcountQueryImpl() {
    super();
  }

  @Override
  public String getSqlForMonth(String[] selectFields, String pk_group,
      UFDate beginDate, UFDate endDate, boolean isSign, String sWhere)
      throws BusinessException {
    try {
      return new FlowAccountBaseQry().getSqlForMonth(selectFields, pk_group,
          beginDate, endDate, isSign, sWhere);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return "";
  }

  @Override
  public boolean isIncludeFlowHis(UFDateTime querydate)
      throws BusinessException {
    try {
      TransConfigQuery tranquery = new TransConfigQuery();
      if (querydate != null) {
        return tranquery.isIncludeHisData(querydate);
      }
      // �жϵ�ǰ���������ת�������Ƿ�Ϊ�գ�Ϊ���򲻹�����ʷ��
      UFDateTime lasttrandate =
          tranquery.queryLastTransDate(new ICBSContext().getPk_group());
      if (lasttrandate == null) {
        return false;
      }
      return true;
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return false;
  }

  /**
   *
   */
  @Override
  public Set<String> queryBatchRef(String[] pk_batchcodes) {
    return new FlowAccountBaseQry().queryBatchRef(pk_batchcodes);
  }

  @Override
  public Map<String, String> queryBillcodeByBids(String[] bids)
      throws BusinessException {
    try {
      return new FlowAccountBaseQry().queryBillcodeByBids(bids);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public Map<String, List<BillInfo>> queryBillReferenced(String[] hids)
      throws BusinessException {
    try {
      return new FlowAccountBaseQry().queryBillReferenced(hids);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return new HashMap<String, List<BillInfo>>();
  }

  /**
   * �����������ѯ
   */
  @Override
  public FlowAccountView[] queryCorRef(FlowAccountView cond,
      boolean isJoinDetail) throws BusinessException {
    try {
      ICBSContext context = new ICBSContext();
      OuttrackinView queryview = new OuttrackinView(isJoinDetail);
      queryview.initView();
      queryview.setCondition(cond, context.getInvInfo(), context
          .getWarehouseInfo());

      VOResultSet<FlowAccountView> voset =
          new VOResultSet<FlowAccountView>(queryview.getViewFieldNames(),
              queryview.getViewFieldTypes(), FlowAccountView.class);
      // ��������ѯ������������
      final long maxrow = ICModuleManager.getMaxQueryCount();
      voset.setMaxrow(maxrow);

      ICDBVisitor db = new ICDBVisitor();
      db.query(queryview.getViewSql(), null, queryview.getViewFieldNames(),
          queryview.getViewFieldTypes(), voset);
      if (voset.getRowCount() >= maxrow) {
        return this.filterRepFlow(voset.toVOs());
      }

      Object value = cond.getAttributeValue("dbizdatefrom");
      if (value == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4008018_0", "04008018-0003")/*
                                                                     * @res
                                                                     * "��Ҫָ����ʼ����"
                                                                     */);
        return null;
      }
      if (this.isIncludeFlowHis(new UFDateTime(value.toString()))) {
        queryview = new OuttrackinView(isJoinDetail);
        queryview.setIsHistory(true);
        queryview.initView();
        queryview.setCondition(cond, context.getInvInfo(), context
            .getWarehouseInfo());
        db.query(queryview.getViewSql(), null, queryview.getViewFieldNames(),
            queryview.getViewFieldTypes(), voset);
      }
      return this.filterRepFlow(voset.toVOs());
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }
  
  private FlowAccountView[] filterRepFlow(FlowAccountView[] flowViews){
    if(ValueCheckUtil.isNullORZeroLength(flowViews)){
      return flowViews;
    }
    Map<String, FlowAccountView> mapFlow = CollectionUtils.hashVOArray(FlowAccountVO.CGENERALBID, flowViews);
    return mapFlow.values().toArray(new FlowAccountView[mapFlow.size()]);
  }

  @Override
  public FlowAccountView queryFlowAccountBySN(String vserialcode,String pk_serialcode, boolean isIn) {
    return new FlowAccountBaseQry().queryFlowAccountBySN(vserialcode,pk_serialcode, isIn);
  }

  @Override
  public AttributeResultSet queryFlowAccountDetailByBid(String[] bids) {
    if (ValueCheckUtil.isNullORZeroLength(bids)) {
      return null;
    }
    String[] queryFields =
        {
          FlowAccountDetailVO.CGENERALHID, FlowAccountDetailVO.CGENERALBID,
          FlowAccountDetailVO.CGENERALLID, FlowAccountDetailVO.PK_GROUP,
          FlowAccountDetailVO.CORPOID, FlowAccountDetailVO.CORPVID,
          FlowAccountDetailVO.NINNUM, FlowAccountDetailVO.NINASSISTNUM,
          FlowAccountDetailVO.NINGROSSNUM, FlowAccountDetailVO.NOUTNUM,
          FlowAccountDetailVO.NOUTASSISTNUM, FlowAccountDetailVO.NOUTGROSSNUM,
          FlowAccountDetailVO.NRETNUM, FlowAccountDetailVO.NRETASTNUM,
          FlowAccountDetailVO.NRETGROSSNUM, FlowAccountDetailVO.CLOCATIONID,
          FlowAccountDetailVO.VBARCODE, FlowAccountDetailVO.VBARCODESUB,
          FlowAccountDetailVO.VBOXBARCODE, FlowAccountDetailVO.VSNCODE,ICPubMetaNameConst.PK_SERIALCODE,
          FlowAccountDetailVO.TS
        };
    return new FlowAccountBaseQry().queryFlowAccountDetailByBid(bids,
        queryFields, null);
  }

  @Override
  public AttributeResultSet queryFlowAccountDetailByFlowId(String[] cflowids) {
    if (ValueCheckUtil.isNullORZeroLength(cflowids)) {
      return null;
    }
    String[] queryFields =
        {
          FlowAccountDetailVO.CGENERALHID, FlowAccountDetailVO.CGENERALBID,
          FlowAccountDetailVO.CGENERALLID, FlowAccountDetailVO.PK_FLOW,
          FlowAccountDetailVO.PK_GROUP, FlowAccountDetailVO.PK_FLOWDETAIL,
          FlowAccountDetailVO.CORPOID, FlowAccountDetailVO.CORPVID,
          FlowAccountDetailVO.NINNUM, FlowAccountDetailVO.NINASSISTNUM,
          FlowAccountDetailVO.NINGROSSNUM, FlowAccountDetailVO.NOUTNUM,
          FlowAccountDetailVO.NOUTASSISTNUM, FlowAccountDetailVO.NOUTGROSSNUM,
          FlowAccountDetailVO.NRETNUM, FlowAccountDetailVO.NRETASTNUM,
          FlowAccountDetailVO.NRETGROSSNUM, FlowAccountDetailVO.CLOCATIONID,
          FlowAccountDetailVO.VBARCODE, FlowAccountDetailVO.VBARCODESUB,
          FlowAccountDetailVO.VBOXBARCODE, FlowAccountDetailVO.VSNCODE,ICPubMetaNameConst.PK_SERIALCODE,
          FlowAccountDetailVO.TS
        };
    // 636 �������벻Ϊ�ոĳ� ���кŲ�Ϊ��
    SqlBuilder buider = new SqlBuilder();
    buider.append(FlowAccountDetailVO.VSNCODE + " is not null");
    return new FlowAccountBaseQry().queryFlowAccountDetailByFlowId(cflowids,
        queryFields, buider.toString());
  }

  @Override
  public String queryFlowAccountSQL(FlowQueryConditionVO cond) {
    this.checkIncludeHis(cond);
    FlowAccountDetailHisView view = new FlowAccountDetailHisView(cond);
    return view.getViewSql();
  }

  /**
   *
   */
  @Override
  public FlowAccountView[] queryFlowInfo(FlowQueryView view)
      throws BusinessException {
    try {
      VOResultSet<FlowAccountView> voset =
          new VOResultSet<FlowAccountView>(view.getViewFieldNames(), view
              .getViewFieldTypes(), FlowAccountView.class);
      // ��������ѯ������������
      final long maxrow = ICModuleManager.getMaxQueryCount();
      voset.setMaxrow(maxrow);
      new ICDBVisitor().query(view.getViewSql(), null,
          view.getViewFieldNames(), view.getViewFieldTypes(), voset);
      return voset.toVOs();
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public FlowAccountView[] queryFlowInfo(IQueryScheme scheme, boolean ibhis) {
    QuerySchemeProcessor p = new QuerySchemeProcessor(scheme);
    String alias = p.getMainTableAlias();
    return new FlowAccountBaseQry().queryFlowAccount(scheme.getWhereSQLOnly(),
        alias, ibhis);
  }

  /**
   *
   */
  @Override
  public FlowAccountView[] queryFlowInfo(String where, boolean ibhis)
      throws BusinessException {
    try {
      return new FlowAccountBaseQry().queryFlowAccount(
          AbstractFlowView.allflowfields, where, ibhis);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   *
   */
  @Override
  public FlowAccountView[] queryFlowInfoFORVMI(String where, boolean ibhis)
      throws BusinessException {
    try {
      FlowAccountBaseQry flow = new FlowAccountBaseQry();
      flow.setBaffectonhand(false);
      return flow.queryFlowAccount(
          AbstractFlowView.allflowfields, where, ibhis);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public FlowAccountView[] queryFlowInfo(String where, UFDateTime dbegindate)
      throws BusinessException {
    try {
      return this.queryFlowInfo(AbstractFlowView.allflowfields, where,
          dbegindate);
    }
    catch (Exception ex) {
      ExceptionUtils.marsh(ex);
    }
    return null;
  }

  @Override
  public FlowAccountView[] queryFlowInfo(String[] flowfields, String where,
      UFDateTime begindate) throws BusinessException {
    try {
      if (begindate == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4008018_0", "04008018-0004")/*
                                                                     * @res
                                                                     * "����ָ����ʼ����"
                                                                     */);
      }
      return new FlowAccountBaseQry().queryFlowAccount(flowfields, where, this
          .isIncludeFlowHis(begindate));
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
  
  @Override
  public FlowAccountView[] queryAllFlowInfo(String[] flowfields, String where,
	      UFDateTime begindate)
  		throws BusinessException {
	    try {
	        if (begindate == null) {
	          ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
	              .getNCLangRes().getStrByID("4008018_0", "04008018-0004")/*
	                                                                       * @res
	                                                                       * "����ָ����ʼ����"
	                                                                       */);
	        }
	        FlowAccountBaseQry queryer = new FlowAccountBaseQry();
	        queryer.setBaffectonhand(false);
	        return queryer.queryFlowAccount(flowfields, where, this
	            .isIncludeFlowHis(begindate));
	      }
	      catch (Exception e) {
	        ExceptionUtils.marsh(e);
	      }
	      return null;
  }

  /**
   *
   */
  @Override
  public AttributeResultSet queryFlowInfoBid(String[] fields, String[] cbillid,
      String where) throws BusinessException {
    try {
      return new FlowAccountBaseQry().queryFlowAccountByBid(fields, cbillid,
          where);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   *
   */
  @Override
  public AttributeResultSet queryFlowInfoBidFORVMI(String[] fields, String[] cbillid,
      String where) throws BusinessException {
    try {
      FlowAccountBaseQry flow = new FlowAccountBaseQry();
      flow.setBaffectonhand(false);
      return flow.queryFlowAccountByBid(fields, cbillid,
          where);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
  
  /**
   *
   */
  @Override
  public AttributeResultSet queryFlowInfoByHid(String[] fields,
      String[] cbillid, String where) throws BusinessException {
    try {
      return new FlowAccountBaseQry().queryFlowAccountByHid(fields, cbillid,
          where);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  @Override
  public VmiSumViewVO[] queryFlowInfoForVmi(FlowQueryView view,
      String[] billbids) throws BusinessException {
    if (ValueCheckUtil.isNullORZeroLength(billbids)) {
      return null;
    }
    FlowAccountBaseQry flow = new FlowAccountBaseQry();
    flow.setBaffectonhand(false);
    Set<String> dbbids = flow.queryCurFlowExsistsBID(billbids, true);
    if (dbbids == null || dbbids.size() != billbids.length) {
      view.setInludeHis(true);
    }
    try {
      ICDBVisitor db = new ICDBVisitor();
      return db.query(view.getViewSql(), null, view.getViewFieldNames(), view
          .getViewFieldTypes(), VmiSumViewVO.class);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }
  
  protected KeepDetailViewForSearch createSearchView(OutPriority priority, boolean bwithloc){
    return new KeepDetailViewForSearch(priority, bwithloc,false);
  }

  /**
   *
   */
  @Override
  public OnhandRes[] queryKeepDetailForOutpriority(String[] hashcodes,
      String where, UFDate fromdate, UFDate todate, OutPriority priority,
      boolean bwithloc, String[] locs) throws BusinessException {
    try {
      if (fromdate == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4008018_0", "04008018-0005")/*
                                                                     * @res
                                                                     * "��ʼ���ڲ���Ϊ��"
                                                                     */);
        return null;
      }
      boolean ishis =
          this.isIncludeFlowHis(new UFDateTime(fromdate.toString()));
      ICDBVisitor db = new ICDBVisitor();
      KeepDetailViewForSearch kpview =null;
      AttributeResultSet set=null;
      
      //�����Ƚ��ȳ������ϼ��ʱ���������ת�����ȼ���ˮ��ʷ�������ˮ����ʷ���е��������һ��Ҫ���ڷ���ʷ���
      //��ע�������ձ鳡����Ϊ����ˮ���е��������������ʷ���еģ����Բ��Բ�ѯ��������������򣬽���ͨ��������ѯ˳�����������ڵ��Ⱥ�˳��
      if(ishis){
    	  if(priority == OutPriority.FIFO){
    		  Object obj=BSContext.getInstance().getSession("ManufactureDatePickAutoAction");
    		  if(obj==null){
    		  set=this.qryHisFlowKpview(kpview, hashcodes, fromdate, todate, priority, bwithloc, locs, db, set);
    		  set=this.qryFlowKpview(kpview, hashcodes, fromdate, todate, priority, bwithloc, locs, db, set);
    		  //���ͻ�ͨ�� �������ڼ��
    		  }else{
    			  set=qryDatePickAutoview(kpview, hashcodes, fromdate, todate, priority, bwithloc, locs, db, set);
    		  }
    	  }else {
        	  set=this.qryFlowKpview(kpview, hashcodes, fromdate, todate, priority, bwithloc, locs, db, set);
        	  set=this.qryHisFlowKpview(kpview, hashcodes, fromdate, todate, priority, bwithloc, locs, db, set);
          }
      }else{
    	  set=this.qryFlowKpview(kpview, hashcodes, fromdate, todate, priority, bwithloc, locs, db, set);
      }
      if (set == null || set.getRowCount() <= 0) {
        return null;
      }

      String[] fields =
          CollectionUtils.combineArrs(OnhandDimVO.getDimContentFields(),
              new String[] {
                OnhandNumVO.NONHANDNUM, OnhandNumVO.NONHANDASTNUM,
                OnhandNumVO.NGROSSNUM
              });
      OnhandRes[] onhandress = new OnhandRes[set.getRowCount()];
      OnhandVO handvo = null;
      for (int i = 0; i < onhandress.length; i++) {
        handvo = new OnhandVO();
        for (String field : fields) {
          handvo.setAttributeValue(field, set.getAttributeValue(i, field));
        }
        //���ͻ�ͨ ����ƥ�������������
        String  vbodyuserdef4 =(String) set.getAttributeValue(i, FlowAccountVO.VBODYUSERDEF4);
        onhandress[i] = new OnhandRes(handvo,vbodyuserdef4);
        onhandress[i].getOnhanddimvo().setVsubhashcode(
            (String) set.getAttributeValue(i, FlowAccountVO.VHASHCODE));
      }
      
      if (priority != OutPriority.FIFO) {
    	  return onhandress;
      }
      return this.combineOnhandResByDims(onhandress);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  //begin-SCM-guofj-NC2016050500004-2016-06-06-ͨ��
  /**
   * �ò�������Զ����ʱʵ�����������ִ�������
   * ����������>֮ǰ�������֮�ͣ��ͻ������⣨���˵�����������
   * 800 400 -1802 800 1200 1000
   * ���ӦΪ 2398������ 3000��800+400-1802 < 0)
   */
  private OnhandRes sumVOs(String key, List<OnhandRes> ll) {
    if (ValueCheckUtil.isNullORZeroLength(ll)) return null;
    OnhandRes ret = ll.get(0);
    UFDouble nuseableastnum = UFDouble.ZERO_DBL;
    UFDouble nuseablegrossnum = UFDouble.ZERO_DBL;
    UFDouble nuseablenum = UFDouble.ZERO_DBL;
    for (OnhandRes res: ll) {
      nuseableastnum = NCBaseTypeUtils.add(nuseableastnum, res.getNuseableastnum());
      nuseablegrossnum = NCBaseTypeUtils.add(nuseablegrossnum, res.getNuseablegrossnum());
      nuseablenum = NCBaseTypeUtils.add(nuseablenum, res.getNuseablenum());
    }
    ret.setNuseableastnum(nuseableastnum);
    ret.setNuseablegrossnum(nuseablegrossnum);
    ret.setNuseablenum(nuseablenum);
    return ret;
  }
  
	private OnhandRes[] combineOnhandResByDims(OnhandRes[] onhandress) {
		List<OnhandRes> onhandresList = new ArrayList<OnhandRes>();
//		MapList<String, OnhandRes> ml = new MapList<String, OnhandRes>();
		//MapList.toMap()ΪHashMap���������put��ֵ���򣬵����Ƚ��ȳ����ʧЧ���ʸ�ΪLinkedHashMap
		Map<String,List<OnhandRes>> ml = new LinkedHashMap<String,List<OnhandRes>>();
		for (int i = 0; i < onhandress.length; i++) {
      OnhandRes onhandRes = onhandress[i];
      String key1 = (String)onhandRes.getAttributeValue(OnhandDimVO.VSUBHASHCODE);
      // ����vsubhashcode��ͬ����λ��ͬ���ִ���ά��
      String key2 = (String)onhandRes.getAttributeValue(OnhandDimVO.CLOCATIONID);
      String key = StringUtil.mergeString(new String[]{key1,key2},null,null);
      List<OnhandRes> l = ml.get(key);
      if (null == l) {
        l = new ArrayList<OnhandRes>();
        ml.put(key, l);
      }
      l.add(onhandRes);
//      ml.put((String)onhandRes.getAttributeValue(OnhandDimVO.VSUBHASHCODE), onhandRes);
		}
//		Map<String, List<OnhandRes>> mapset = ml.toMap();
		for (Map.Entry<String,List<OnhandRes>> entry : ml.entrySet()) {
		  OnhandRes ret = this.sumVOs(entry.getKey(), entry.getValue());
		  //�ִ���Ϊ�ջ򲻿��� 2017-9-13 19:39:34 by wzp
		  if (ret == null || !ret.isUseable()) {
        continue;
      }
		  onhandresList.add(ret);
		}

    return onhandresList.toArray(new OnhandRes[onhandresList.size()]);
    //end-SCM-guofj-NC2016050500004-2016-06-06-ͨ��
    
		/*for (int i = 0; i < onhandress.length; i++) {
			OnhandRes onhandRes = onhandress[i];		
			if (NCBaseTypeUtils.isGtZero(onhandRes.getNuseablenum())) {
				onhandresList.add(onhandRes);
				continue;
			}
			
			for (int j = 0; j < onhandresList.size(); j++) {
				OnhandRes nextOnhandRes = onhandresList.get(j);
				if (nextOnhandRes == null) {
					continue;
				}
				if (!nextOnhandRes.getAttributeValue(OnhandDimVO.VSUBHASHCODE)
						.equals(onhandRes
								.getAttributeValue(OnhandDimVO.VSUBHASHCODE))) {
					continue;
				}
				// ��ͬά��
				UFDouble nuseableastnum = NCBaseTypeUtils.add(
						onhandRes.getNuseableastnum(),
						nextOnhandRes.getNuseableastnum());

				UFDouble nuseablegrossnum = NCBaseTypeUtils.add(
						onhandRes.getNuseablegrossnum(),
						nextOnhandRes.getNuseablegrossnum());

				UFDouble nuseablenum = NCBaseTypeUtils.add(
						onhandRes.getNuseablenum(),
						nextOnhandRes.getNuseablenum());
				
				// �ɷ�����Դ����
				if (NCBaseTypeUtils.isGtZero(nuseablenum)) {
					nextOnhandRes.setNuseableastnum(nuseableastnum);
					nextOnhandRes.setNuseablegrossnum(nuseablegrossnum);
					nextOnhandRes.setNuseablenum(nuseablenum);
					break;
				} else {
					onhandresList.set(j, null);
					onhandRes.setNuseableastnum(nuseableastnum);
					onhandRes.setNuseablegrossnum(nuseablegrossnum);
					onhandRes.setNuseablenum(nuseablenum);
				}
			}
		}
		List<OnhandRes> reList = new ArrayList<OnhandRes>();
		for (int i = 0; i < onhandresList.size();i++) {
			if (onhandresList.get(i) == null) {
				continue;
			}
			reList.add(onhandresList.get(i));
		}
	
		return reList.toArray(new OnhandRes[0]);*/
	}
	
  private AttributeResultSet qryFlowKpview(KeepDetailViewForSearch kpview,String[] hashcodes,
	       UFDate fromdate, UFDate todate, OutPriority priority,
	       boolean bwithloc, String[] locs,ICDBVisitor db,AttributeResultSet set){
	  kpview = createSearchView(priority, bwithloc);
	  kpview.initView();
      kpview.addDbizDateWhere(fromdate, todate);
      kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowalias + "."
          + FlowAccountVO.VHASHCODE, hashcodes));
      if (bwithloc && locs != null && locs.length > 0) {
        kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowdetailalias
            + "." + FlowAccountDetailVO.CLOCATIONID, locs, true));
      }

      return
          db.query(kpview.getViewSql(), null, kpview.getViewFieldNames(),
              kpview.getViewFieldTypes(),set);
  }
  
  private AttributeResultSet qryHisFlowKpview(KeepDetailViewForSearch kpview,String[] hashcodes,
	       UFDate fromdate, UFDate todate, OutPriority priority,
	       boolean bwithloc, String[] locs,ICDBVisitor db,AttributeResultSet set){
      kpview = createSearchView(priority, bwithloc);
      kpview.setIsHistory(true);
      kpview.initView();
      kpview.addDbizDateWhere(fromdate, todate);
      kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowalias + "."
          + FlowAccountVO.VHASHCODE, hashcodes));
      if (bwithloc && locs != null && locs.length > 0) {
        kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowdetailalias
            + "." + FlowAccountDetailVO.CLOCATIONID, locs, true));
      }
      return
          db.query(kpview.getViewSql(), null, kpview.getViewFieldNames(),
              kpview.getViewFieldTypes(), set);
  }
  
  
  
  private AttributeResultSet qryDatePickAutoview(KeepDetailViewForSearch kpview,String[] hashcodes,
	       UFDate fromdate, UFDate todate, OutPriority priority,
	       boolean bwithloc, String[] locs,ICDBVisitor db,AttributeResultSet set){
	 Logger.error("�����ѯʵ��-----------------------");
     kpview = createSearchView(priority, bwithloc);
     kpview.setIsHistory(true);
     kpview.initView();
     kpview.addDbizDateWhere(fromdate, todate);
     kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowalias + "."
         + FlowAccountVO.VHASHCODE, hashcodes));
     if (bwithloc && locs != null && locs.length > 0) {
       kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowdetailalias
           + "." + FlowAccountDetailVO.CLOCATIONID, locs, true));
     }
     
     KeepDetailViewForSearch kpview1 = createSearchView(priority, bwithloc);
     kpview1.initView();
     kpview1.addDbizDateWhere(fromdate, todate);
     kpview1.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowalias + "."
         + FlowAccountVO.VHASHCODE, hashcodes));
     if (bwithloc && locs != null && locs.length > 0) {
       kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowdetailalias
           + "." + FlowAccountDetailVO.CLOCATIONID, locs, true));
     }
     SqlBuilder sql=new SqlBuilder();
     sql.append("select * from ("+kpview.getViewSql()+" union all " +kpview1.getViewSql()+") order by ");
     sql.append("vbatchcode");
//     sql.append("vbodyuserdef4,");
//     sql.append("nonhandnum,");
//     sql.append("nonhandastnum,");
//     sql.append("dbizdate,");
     if(bwithloc){
    	 sql.append(",outpriority,");
    	 sql.append("rackname asc");
     }
     return db.query(sql.toString(), null, kpview.getViewFieldNames(), kpview.getViewFieldTypes(), set);
 }
  /**
   *
   */
  @Override
  public OnhandRes[] queryKeepDetailForOuttrackin(String[] hashcodes,
      String where, UFDate fromdate, UFDate todate, OutPriority priority,
      boolean bwithloc, String[] locs) throws BusinessException {
    try {
      if (fromdate == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4008018_0", "04008018-0005")/*
                                                                     * @res
                                                                     * "��ʼ���ڲ���Ϊ��"
                                                                     */);
        return null;
      }
      boolean ishis =
          this.isIncludeFlowHis(new UFDateTime(fromdate.toString()));
      OuttrackinViewForSearch kpview =
          new OuttrackinViewForSearch(priority, bwithloc);
      kpview.initView();
      kpview.addDbizDateWhere(fromdate, todate);
      kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowalias + "."
          + FlowAccountVO.VHASHCODE, hashcodes));
      if (bwithloc && locs != null && locs.length > 0) {
        kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowdetailalias
            + "." + FlowAccountDetailVO.CLOCATIONID, locs, true));
      }
      ICDBVisitor db = new ICDBVisitor();
      AttributeResultSet set =
          db.query(kpview.getViewSql(), null, kpview.getViewFieldNames(),
              kpview.getViewFieldTypes());
      if (ishis) {
        kpview = new OuttrackinViewForSearch(priority, bwithloc);
        kpview.setIsHistory(ishis);
        kpview.initView();
        kpview.addDbizDateWhere(fromdate, todate);
        kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowalias + "."
            + FlowAccountVO.VHASHCODE, hashcodes));
        if (bwithloc && locs != null && locs.length > 0) {
          kpview.addWherePart(SqlIn.formInSQL(AbstractFlowView.flowdetailalias
              + "." + FlowAccountDetailVO.CLOCATIONID, locs, true));
        }
        set =
            db.query(kpview.getViewSql(), null, kpview.getViewFieldNames(),
                kpview.getViewFieldTypes(), set);
      }
      if (set == null || set.getRowCount() <= 0) {
        return null;
      }
      String[] fields =
          CollectionUtils.combineArrs(OnhandDimVO.getDimContentFields(),
              new String[] {
                OnhandNumVO.NONHANDNUM, OnhandNumVO.NONHANDASTNUM,
                OnhandNumVO.NGROSSNUM
              });
      OnhandRes[] onhandress = new OnhandRes[set.getRowCount()];
      OnhandVO handvo = null;
      for (int i = 0; i < onhandress.length; i++) {
        handvo = new OnhandVO();
        for (String field : fields) {
          handvo.setAttributeValue(field, set.getAttributeValue(i, field));
        }
        onhandress[i] = new OnhandRes(handvo);
        onhandress[i].getOnhanddimvo().setVsubhashcode(
            (String) set.getAttributeValue(i, FlowAccountVO.VHASHCODE));
        onhandress[i].setCbilltype((String) set.getAttributeValue(i,
            FlowAccountVO.CBILLTYPECODE));
        onhandress[i].setVtrantypecode((String) set.getAttributeValue(i,
            ICPubMetaNameConst.VTRANTYPECODE));
        onhandress[i].setVbillcode((String) set.getAttributeValue(i,
            FlowAccountVO.VBILLCODE));
        onhandress[i].setCgeneralhid((String) set.getAttributeValue(i,
            FlowAccountVO.CGENERALHID));
        onhandress[i].setCgeneralbid((String) set.getAttributeValue(i,
            FlowAccountVO.CGENERALBID));
        onhandress[i].setCrowno((String) set.getAttributeValue(i,
            FlowAccountVO.CROWNO));
      }
      return onhandress;
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  }

  /**
   * �����Ƿ��ѯ��ʷ
   * 
   * @param cond
   */
  private void checkIncludeHis(FlowQueryConditionVO cond) {
    if (cond.isHis()) {
      return;
    }
    boolean ishis = false;
    TransConfigQuery query = new TransConfigQuery();
    if (null != cond.getStartDate()) {
      // ʹ�ñ�׼ʱ��ת����ʱ������
      UFDateTime beginDateTime =
          new UFDateTime(cond.getStartDate(), ICalendar.BASE_TIMEZONE);
      ishis = query.isIncludeHisData(beginDateTime);
      if (ishis) {
        cond.setHis(ishis);
        return;
      }
    }
    if (null != cond.getEndDate()) {
      // ʹ�ñ�׼ʱ��ת����ʱ������
      UFDateTime endDateTime =
          new UFDateTime(cond.getEndDate(), ICalendar.BASE_TIMEZONE);
      ishis = query.isIncludeHisData(endDateTime);
    }
    cond.setHis(ishis);
  }

@Override
public FlowAccountView[] queryFlowInfo(String where, UFDateTime begindate,
		boolean isdetail) throws BusinessException {

    try {
      if (begindate == null) {
        ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl
            .getNCLangRes().getStrByID("4008018_0", "04008018-0004")/*
                                                                     * @res
                                                                     * "����ָ����ʼ����"
                                                                     */);
      }
      return new FlowAccountBaseQry().queryFlowAccount(null, where, this
          .isIncludeFlowHis(begindate),isdetail);
    }
    catch (Exception e) {
      ExceptionUtils.marsh(e);
    }
    return null;
  
}

}
