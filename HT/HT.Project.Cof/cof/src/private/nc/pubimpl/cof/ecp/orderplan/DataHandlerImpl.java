package nc.pubimpl.cof.ecp.orderplan;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.logging.Logger;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.impl.pubapp.pattern.database.TempTable;
import nc.jdbc.framework.processor.BaseProcessor;
import nc.md.model.IAttribute;
import nc.md.model.IBean;
import nc.md.model.IBusinessEntity;
import nc.md.model.type.IRefType;
import nc.ui.cof.orderplan.maintain.excel.common.FieldItem;
import nc.ui.cof.orderplan.maintain.excel.common.IDataHandler;
import nc.ui.cof.orderplan.maintain.excel.imp.handler.MDQueryFacade;
import nc.ui.cof.orderplan.maintain.excel.imp.itf.DataHandlerAgency;
import nc.ui.cof.orderplan.maintain.excel.imp.itf.IDataHandlerRegister;
import nc.vo.bd.meta.IBDObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pub.JavaType;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.pubapp.pattern.pub.SqlBuilder;

public class DataHandlerImpl implements IDataHandler {

  @Override
  public ISuperVO handData(String itemkey, int modeltype, ISuperVO vo,
      Object value, String pk_org) throws BusinessException {
    IDataHandlerRegister register = new OpDataHandlerRegister();
    DataHandlerAgency agency = new DataHandlerAgency(register);
    FieldItem item = new FieldItem();
    item.setItemKey(itemkey);
    item.setModelType(modeltype);
    try {
      agency.handeData(item, vo, value, pk_org);
    }
    catch (Exception e) {

      throw new BusinessException(e.getMessage());
    }

    return vo;
  }

  @Override
  public String lookupGroupByName(String pk_group) {
	String sql = "select pk_group from org_group where name='" + pk_group + "'" +" and dr='0' ";
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sql.toString());
    if (set.next()) {
      String refid = set.getString(0);
      return refid;
    }
    return null;
  }

  @Override
  public String lookOrgByName(String pk_group, String pk_org) {
    StringBuilder sb = new StringBuilder();
    sb.append("select pk_salesorg from org_salesorg where name='");
    sb.append(pk_org);
    if (pk_group != null) {
      sb.append("' and pk_group='");

      sb.append(pk_group + "'");
    }
    sb.append(" and  dr='0'");
    
    DataAccessUtils utils = new DataAccessUtils();
    IRowSet set = utils.query(sb.toString());
    if (set.next()) {
      String refid = set.getString(0);
      return refid;
    }
    return null;

  }

  @Override
  public Map<String, Object> handBatchData(String itemkey, String classtype,
      String[] values, String pk_orgid) {
    // 根据属性在实体下的相对路径得到相应的属性
    IBean bean = MDQueryFacade.getBeanByFullClassName(classtype);
    IAttribute a = bean.getAttributeByPath(itemkey);
    // 属性引用类型描述
    IRefType reftype = (IRefType) a.getDataType();
    // 属性参照的数据实体
    IBean refbean = reftype.getRefType();
    // 获取到参照实体的id，pk_org和name分别对应的字段名称
    Map<String, String> name_attr_map =
        ((IBusinessEntity) refbean).getBizInterfaceMapInfo(IBDObject.class
            .getName());
    Map<String, Object> result=null;
    //项目专项，如果导入行为物料时，根据编码解析 add by zhaotf 2017-09-15
    if("cmaterialvid".equals(itemkey)){
    	 result =queryDatabaseByCode(name_attr_map, values, pk_orgid, refbean.getTable()
    		            .getName());
    }else{
    result =queryDatabase(name_attr_map, values, pk_orgid, refbean.getTable()
            .getName());
    }
    return result;
  }

  private Map<String, Object> queryDatabase(Map<String, String> name_attr_map,
      String[] values, String pk_orgid, String tablename) {

    String group = AppContext.getInstance().getPkGroup();
    final String id = name_attr_map.get("id");
    final String name = name_attr_map.get("name");
    SqlBuilder sb = new SqlBuilder();
    sb.append("select ");
    sb.append(name_attr_map.get("id"));
    sb.append(",");
    sb.append(name_attr_map.get("name"));
    sb.append(" from ");
    sb.append(tablename);
    sb.append(" where ");

    // IDExQueryBuilder ideBuilder = new IDExQueryBuilder("temp");
    // String idesql = ideBuilder.buildSQL(name_attr_map.get("name"), values);
    String temtable =
        createTmpTable(name_attr_map.get("name"), values, tablename);

    sb.append(name_attr_map.get("name"));
    sb.append(" in (select " + name + " from ");

    sb.append(temtable);
    sb.append(" ) ");
    // 如果参照实体有主组织属性，则加上主组织约束
    String pk_orgattr = name_attr_map.get("pk_org");
    String pk_groupattr = name_attr_map.get("pk_group");
    if (!PubAppTool.isNull(pk_orgattr)) {
      sb.append(" and ( ");

      sb.append(pk_orgattr, pk_orgid);
      if (!PubAppTool.isNull(pk_groupattr)) {
        sb.append(" or ");
        sb.append(pk_orgattr);
        sb.append(" = ");
        sb.append(pk_groupattr);
        sb.append(" or ( ");
        sb.append(pk_orgattr, "GLOBLE00000000000000");
        sb.append(" and (");
        sb.append(pk_groupattr, "~");
        sb.append(" or ");
        sb.append(pk_groupattr, group);
        sb.append("))");

      }
      sb.append(" or  ");
      sb.append(pk_orgattr, "GLOBLE00000000000000");
      sb.append(" ) ");

    }
    // 如果参照实体有主组织属性，则加上主组织约束
    if (!PubAppTool.isNull(pk_groupattr)) {
      sb.append(" and ( ");
      sb.append(pk_groupattr, group);
      sb.append(" or ");
      sb.append(pk_groupattr, "~");
      sb.append(" ) ");
    }
    sb.append(" and ( dr = 0 or dr is null ) ");

    try {
      BaseDAO dao = new BaseDAO();
      Map<String, Object> result =
          (Map<String, Object>) dao.executeQuery(sb.toString(),
              new BaseProcessor() {
                private static final long serialVersionUID = 1L;

                @Override
                public Object processResultSet(ResultSet rs)
                    throws SQLException {
                  Map<String, Object> result = new HashMap<String, Object>();

                  Object attrid = null;
                  String attrcode = null;
                  while (rs.next()) {
                    attrid = rs.getObject(id);
                    attrcode = rs.getString(name);
                    result.put(attrcode, attrid);
                  }
                  return result;
                }
              });
      return result;
    }
    catch (DAOException e) {
      ExceptionUtils.wrappException(e);
    }
    return null;
  }
  
  private Map<String, Object> queryDatabaseByCode(Map<String, String> name_attr_map,
	      String[] values, String pk_orgid, String tablename) {

	    String group = AppContext.getInstance().getPkGroup();
	    final String id = name_attr_map.get("id");
	    final String name = name_attr_map.get("name");
	    final String code= name_attr_map.get("code");
	    SqlBuilder sb = new SqlBuilder();
	    sb.append("select ");
	    sb.append(name_attr_map.get("id"));
	    sb.append(",");
	    sb.append(name_attr_map.get("code"));
	    sb.append(" from ");
	    sb.append(tablename);
	    sb.append(" where ");

	    // IDExQueryBuilder ideBuilder = new IDExQueryBuilder("temp");
	    // String idesql = ideBuilder.buildSQL(name_attr_map.get("name"), values);
	    String temtable =
	        createTmpTable(name_attr_map.get("name"), values, tablename);

	    sb.append(name_attr_map.get("code"));
	    sb.append(" in (select " + name + " from ");

	    sb.append(temtable);
	    sb.append(" ) ");
	    // 如果参照实体有主组织属性，则加上主组织约束
	    String pk_orgattr = name_attr_map.get("pk_org");
	    String pk_groupattr = name_attr_map.get("pk_group");
	    if (!PubAppTool.isNull(pk_orgattr)) {
	      sb.append(" and ( ");

	      sb.append(pk_orgattr, pk_orgid);
	      if (!PubAppTool.isNull(pk_groupattr)) {
	        sb.append(" or ");
	        sb.append(pk_orgattr);
	        sb.append(" = ");
	        sb.append(pk_groupattr);
	        sb.append(" or ( ");
	        sb.append(pk_orgattr, "GLOBLE00000000000000");
	        sb.append(" and (");
	        sb.append(pk_groupattr, "~");
	        sb.append(" or ");
	        sb.append(pk_groupattr, group);
	        sb.append("))");

	      }
	      sb.append(" or  ");
	      sb.append(pk_orgattr, "GLOBLE00000000000000");
	      sb.append(" ) ");

	    }
	    // 如果参照实体有主组织属性，则加上主组织约束
	    if (!PubAppTool.isNull(pk_groupattr)) {
	      sb.append(" and ( ");
	      sb.append(pk_groupattr, group);
	      sb.append(" or ");
	      sb.append(pk_groupattr, "~");
	      sb.append(" ) ");
	    }
	    
	    sb.append(" and ( dr = 0 or dr is null ) ");

	    try {
	      BaseDAO dao = new BaseDAO();
	      Map<String, Object> result =
	          (Map<String, Object>) dao.executeQuery(sb.toString(),
	              new BaseProcessor() {
	                private static final long serialVersionUID = 1L;

	                @Override
	                public Object processResultSet(ResultSet rs)
	                    throws SQLException {
	                  Map<String, Object> result = new HashMap<String, Object>();

	                  Object attrid = null;
	                  String attrcode = null;
	                  while (rs.next()) {
	                    attrid = rs.getObject(id);
	                    attrcode = rs.getString(code);
	                    result.put(attrcode, attrid);
	                  }
	                  return result;
	                }
	              });
	      return result;
	    }
	    catch (DAOException e) {
	      ExceptionUtils.wrappException(e);
	    }
	    return null;
	  }

  private String createTmpTable(String column, String[] values, String tname) {
    TempTable bo = new TempTable();
    long start = System.currentTimeMillis();
    List<List<Object>> dataList = new ArrayList<List<Object>>();
    for (int i = 0; i < values.length; i++) {
      List<Object> data = new ArrayList<Object>();
      data.add(values[i]);
      dataList.add(data);
    }
    String tablename =
        bo.getTempTable("tmp_cof_opexcle_" + tname, new String[] {
          column
        }, new String[] {
          "varchar(50)"
        }, new JavaType[] {
          JavaType.String
        }, dataList);
    long end = System.currentTimeMillis();
    Logger.debug("创建参数临时表" + dataList.size() + "条记录共花时间(ms)" + (end - start));
    return tablename;

  }

}
