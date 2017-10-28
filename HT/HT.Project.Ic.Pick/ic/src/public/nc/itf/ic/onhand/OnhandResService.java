package nc.itf.ic.onhand;

import java.util.Map;

import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.onhand.define.ICBillPickResults;
import nc.vo.ic.onhand.entity.OnhandDimVO;
import nc.vo.ic.onhand.entity.OnhandVO;
import nc.vo.ic.onhand.pub.OnhandSelectDim;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.sc.m61.entity.SCOrderIssueVO;

/**
 * <p>
 * <b>现存量资源服务</b>
 * 
 * @version v60
 * @since v60
 * @author yangb
 * @time 2010-4-15 下午11:58:24
 */
public interface OnhandResService {

  /**
   * 委外发料的结存数据获取
   * 
   * @param billvos
   * @return
   * @throws BusinessException
   */
  public SCOrderIssueVO[] getSCOrderIssueVOs(AggregatedValueObject[] billvos)
      throws BusinessException;

  /**
   * 方法功能描述：自动捡货
   * <p>
   * <b>参数说明</b>
   * 
   * @param billvo
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author yangb
   * @time 2010-9-2 上午10:14:27
   */
  public ICBillPickResults pickAuto(ICBillVO billvo) throws BusinessException;
  
  
  /**
   * 方法功能描述：自动捡货
   * <p>
   * <b>参数说明</b>
   * 
   * @param billvo
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author yangb
   * @time 2010-9-2 上午10:14:27
   */
  public ICBillPickResults pickAuto(ICBillVO billvo,boolean falg) throws BusinessException;

  /**
   * 方法功能描述：自动捡货
   * <p>
   * <b>参数说明</b>
   * 
   * @param bills
   * @return
   * @throws BusinessException <p>
   * @since 6.0
   * @author yangb
   * @time 2010-9-2 上午10:16:47
   */
  public ICBillVO[] pickAuto(ICBillVO[] bills) throws BusinessException;

  /**
   * 方法功能描述：查询单据的现存量(自动主辅计量平衡使用，过滤非自动主辅计量平衡物料)
   * 
   * @author yangb
   * @time 2010-6-5 下午10:43:05
   */
  public OnhandVO[] queryOnhandVOByBills(ICBillVO[] bills)
      throws BusinessException;

  /**
   * 方法功能描述：查询现存量
   * <p>
   * 非严格匹配的查询，只用非空维度字段做过滤 <b>默认查询所有维度字段，不做汇总处理，包括可用和不可用的结存</b>
   * 
   * @param dimvos 查询维度
   * @since 6.0
   * @author yangb
   * @time 2010-7-8 下午09:18:24
   */
  public OnhandVO[] queryOnhandVOByDims(OnhandDimVO[] dimvos)
      throws BusinessException;

  /**
   * 方法功能描述：查询现存量
   * <p>
   * <b>参数说明</b>
   * 
   * @param select 要查询的字段
   * @param dimvos 过滤维度
   * @param bqueryuseablestate
   * @param bytranstype
   * @return <p>
   * @since 6.0
   * @author liuzy
   * @time 2010-7-8 下午09:18:24
   */
  public OnhandVO[] queryOnhandVOByDims(OnhandSelectDim select,
      OnhandDimVO[] dimvos, boolean bqueryuseablestate, String bytranstype)
      throws BusinessException;

  /**
   * 方法功能描述：根据现存量维度查询可用的存量（为预留提供） 仅仅查询库存状态可用的存量
   * 
   * @author yangb
   * @time 2010-6-5 下午10:43:05
   */
  public OnhandVO[] queryUseableOnhand(OnhandDimVO[] dimvos)
      throws BusinessException;

  /**
   * 方法功能描述：根据现存量维度查询可用的存量（为可用量提供） 仅仅查询库存状态可用的存量,
   * 未除去不影响可用量的仓库
   * 
   * @author yangb
   * @time 2010-6-5 下午10:43:05
   */
  public OnhandVO[] queryUseableOnhandForAtp(OnhandDimVO[] dimvos)
      throws BusinessException;
 
  /**
   * 查询上次入库货位
   * 
   * @param pk_calbody
   * @param cwarehouseid
   * @param cmateiralvids
   * @return Map<物料Vid， 货位ID>
   * @throws BusinessException
   */
  public Map<String,String> queryLastInLocation(String pk_calbody,String cwarehouseid, String[] cmateiralvids) 
      throws BusinessException;
  
  /**
   * 
   * 查询存量货位
   * @param pk_calbody
   * @param cwarehouseid
   * @param cmaterialvids
   * @return
   * @throws BusinessException
   */
  public Map<String, String> queryOnhandLocation(String pk_calbody, String cwarehouseid, String[] cmaterialvids) 
      throws BusinessException;
  
  
  /**
   * 查询存量服务    (实现方法中未关联可用量查询)
   * 使用场景：查询存量服务
   * 过滤了废品仓，不影响可用量的仓库 ，及库存状态为不可用的物料
   * 
   * @param dimvos
   * @param bextendWarehouse 是否默认按仓库展开
   * @return
   * @throws BusinessException
   */
  public OnhandVO[] queryAtpOnhand(OnhandDimVO[] dimvos, boolean bextendWarehouse) throws BusinessException;
  
  /**
   * 查询存量服务    (实现方法中未关联可用量查询)
   * 使用场景： 电子销售增量同步可用量时查询现存量
   * @param dimvos
   * @param tupdatetime
   * @param endtime
   * @param bextendWarehouse 是否默认按仓库展开
   * @return
   * @throws BusinessException
   */
  public OnhandVO[] queryAtpOnhandUP(OnhandDimVO[] dimvos,UFDateTime tupdatetime,
      UFDateTime endtime, boolean bextendWarehouse) throws BusinessException;

  
}
