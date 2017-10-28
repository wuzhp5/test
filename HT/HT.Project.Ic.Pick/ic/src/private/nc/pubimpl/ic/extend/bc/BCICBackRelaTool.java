package nc.pubimpl.ic.extend.bc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import nc.bs.bc.pub.db.SqlIn;
import nc.bs.dao.BaseDAO;
import nc.bs.dao.DAOException;
import nc.bs.framework.common.NCLocator;
import nc.bs.uap.lock.PKLock;
import nc.impl.pubapp.pattern.database.DataAccessUtils;
import nc.itf.uap.IUAPQueryBS;
import nc.vo.ic.extend.bc.ICBackRelaVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.data.IRowSet;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class BCICBackRelaTool {
  
  public static void setBBack(String[] cgeneralbids) throws BusinessException {
    ICBackRelaVO[] toUpdateVOs = null;
    try {
      toUpdateVOs = BCICBackRelaTool.queryByPK(cgeneralbids);
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (toUpdateVOs == null || toUpdateVOs.length == 0) {
      return;
    }

    // 设置成已回传
    for (ICBackRelaVO icBackRelaVO : toUpdateVOs) {
      icBackRelaVO.setBback(UFBoolean.TRUE);
    }

    BaseDAO dao = new BaseDAO();
    try {
      dao.updateVOArray(toUpdateVOs, new String[] {
        ICBackRelaVO.BBACK, ICBackRelaVO.TS
      });
    }
    catch (DAOException e) {
      ExceptionUtils.wrappException(e);
    }
  }

  /**
   * 根据主键进行查询
   * 
   * @param cgeneralbids
   * @return
   */
  public static ICBackRelaVO[] queryByPK(String[] cgeneralbids)
      throws BusinessException {
    String condition = " dr = 0" + SqlIn.formInSQL(ICBackRelaVO.CGENERALBID, cgeneralbids);

    ICBackRelaVO[] queryVOs = null;
    try {
      @SuppressWarnings("unchecked")
      Collection<ICBackRelaVO> queryCollec =
          NCLocator.getInstance().lookup(IUAPQueryBS.class)
              .retrieveByClause(ICBackRelaVO.class, condition);
      if (queryCollec == null || queryCollec.size() == 0) {
        return null;
      }
      queryVOs = new ICBackRelaVO[queryCollec.size()];
      int i = 0;
      for (ICBackRelaVO queryVO : queryCollec) {
        queryVOs[i] = queryVO;
        i++;
      }
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (queryVOs == null || queryVOs.length == 0) {
      return null;
    }
    return queryVOs;
  }

  /**
   * 向关系表中增加数据，由于存在多次下发的情况，因此先查询是否存在，对不存在的单据进行插入操作
   * 需要处理并发
   * 
   * @param vos
   * @throws BusinessExceptionException
   */
  public static ICBackRelaVO[] insertICBackRelaVOs(String[] cgeneralbids,
      String billtype) throws BusinessException {
    if (cgeneralbids == null || cgeneralbids.length == 0) {
      return null;
    }
    PKLock.getInstance().addBatchDynamicLock(cgeneralbids);

    List<String> toInsertVOIDs = getToInsertVOIDs(cgeneralbids);

    if (toInsertVOIDs == null || toInsertVOIDs.size() == 0) {
      return null;
    }

    ICBackRelaVO[] inserVOs = buildICBackRelaVOs(toInsertVOIDs, billtype);

    BaseDAO tool = new BaseDAO();
    tool.insertVOArrayWithPK(inserVOs);

    return inserVOs;
  }

  /**
   * 构建VO
   * 
   * @param toInsertVOIDs
   * @param billtype
   * @return
   */
  private static ICBackRelaVO[] buildICBackRelaVOs(List<String> toInsertVOIDs,
      String billtype) {
    if (toInsertVOIDs == null || toInsertVOIDs.size() == 0) {
      return null;
    }
    ICBackRelaVO[] vos = new ICBackRelaVO[toInsertVOIDs.size()];
    for (int i = 0; i < vos.length; i++) {
      vos[i] = new ICBackRelaVO();
      vos[i].setCgeneralbid(toInsertVOIDs.get(i));
      vos[i].setBsend(UFBoolean.TRUE);
      vos[i].setBback(UFBoolean.FALSE);
      vos[i].setCbilltype(billtype);
    }

    return vos;
  }

  private static List<String> getToInsertVOIDs(String[] cgeneralbids) {
    if (cgeneralbids == null || cgeneralbids.length == 0) {
      return null;
    }

    String inSQL = SqlIn.formInSQL(ICBackRelaVO.CGENERALBID, cgeneralbids);

    StringBuilder querySql = new StringBuilder();
    querySql.append(" select ");
    querySql.append(ICBackRelaVO.CGENERALBID);
    querySql.append(" from  ");
    querySql.append(ICBackRelaVO.TABLE_NAME);
    querySql.append(" where dr = 0 ");
    querySql.append(inSQL);

    DataAccessUtils util = new DataAccessUtils();
    IRowSet results = util.query(querySql.toString());
    String[] existPKs = results.toOneDimensionStringArray();

    if (existPKs == null || existPKs.length == 0) {
      return Arrays.asList(cgeneralbids);
    }

    List<String> cgeneralbidExist = Arrays.asList(existPKs);
    List<String> notExistPKs = new ArrayList<String>();
    for (String cgeneralbid : cgeneralbids) {
      // 如果该bid已经存在了，则不用新增
      if (cgeneralbidExist.contains(cgeneralbid)) {
        continue;
      }
      if (notExistPKs.contains(cgeneralbid)) {
        continue;
      }
      notExistPKs.add(cgeneralbid);
    }

    if (notExistPKs.size() == 0) {
      return null;
    }

    return notExistPKs;
  }
}
