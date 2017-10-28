package nc.bs.pu.m20;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.bcmanage.bs.BusiCenterCache;
import nc.bs.framework.common.NCLocator;
import nc.cmbd.vo.scmpub.res.billtype.SOBillType;
import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.bd.supplier.baseinfo.ISupplierBaseInfoQryService;
import nc.itf.price.pub.service.IFindSalePrice;
import nc.itf.uap.IUAPQueryBS;
import nc.itf.uap.pf.IPfExchangeService;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.jdbc.framework.processor.MapProcessor;
import nc.pubitf.so.m30.api.ISaleOrderMaintain4Push;
import nc.vo.bd.bom.bom0202.entity.AggBomVO;
import nc.vo.bd.bom.bom0202.entity.BomItemVO;
import nc.vo.bd.bom.bom0202.entity.BomVO;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.price.pub.entity.FindPPLimitPriceResultVO;
import nc.vo.price.pub.entity.FindPriceParaVO;
import nc.vo.price.pub.entity.FindPriceResultVO;
import nc.vo.price.pub.entity.PromoteLimitPara;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDateTime;
import nc.vo.pub.lang.UFDouble;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderHVO;
import nc.vo.so.m30.entity.SaleOrderVO;

public class PushSave21ForM30Service {

private Map<String, Map<String, String>> cache = new HashMap<String, Map<String, String>>();
	
	public SaleOrderVO[] analyseOrder2SaleOrder(OrderVO[] orderVOs) throws BusinessException {
		for (OrderVO orderVO : orderVOs) {
			BusiCenterCache.getInstance().getBCVOs();
			AggregatedValueObject resultVO = NCLocator.getInstance().lookup(IPfExchangeService.class).runChangeData("21", "30", orderVO, null);
			SaleOrderVO sale = (SaleOrderVO) resultVO;
			fillSaleOrderVO(orderVO, sale);
			List<SaleOrderVO> newOrders = groupOrderVOByOrg(sale);
			for (SaleOrderVO newOrder : newOrders) {
				fillSaleOrg(newOrder);
				runAnalyzeBOM(newOrder);
				findPrice(newOrder);
			}
			NCLocator.getInstance().lookup(ISaleOrderMaintain4Push.class).insertBills(newOrders.toArray(new SaleOrderVO[newOrders.size()]));
		}
		return null;
	}
	
	/**
	 * 销售订单分组
	 * @param sale
	 * @return
	 * @throws BusinessException
	 */
	private List<SaleOrderVO> groupOrderVOByOrg(SaleOrderVO sale) throws BusinessException{
		SaleOrderBVO[] bvos = sale.getChildrenVO();
		Map<String, List<SaleOrderBVO>> groups = new HashMap<String, List<SaleOrderBVO>>();
		for(SaleOrderBVO bvo : bvos){
			String pk_org = bvo.getPk_org();
			if(pk_org == null){
				throw new BusinessException("采购订单库存组织未能映射到销售订单销售组织");
			}
			if(groups.containsKey(pk_org)){
				groups.get(pk_org).add(bvo);
			}else{
				List<SaleOrderBVO> value = new ArrayList<SaleOrderBVO>();
				value.add(bvo);
				groups.put(pk_org, value);
			}
		}
		List<SaleOrderVO> results = new ArrayList<SaleOrderVO>();
		Set<String> keys = groups.keySet();
		for(String key : keys){
			List<SaleOrderBVO> value = groups.get(key);
			SaleOrderVO clone = (SaleOrderVO) sale.clone();
			clone.setChildrenVO(value.toArray(new SaleOrderBVO[value.size()]));
			results.add(clone);
		}
		return results;
	}
	
	/**
	 * 关键字段填充
	 * @param orderVO
	 * @param saleorder
	 * @return
	 * @throws BusinessException
	 */
	private void fillSaleOrderVO(OrderVO orderVO, SaleOrderVO saleorder) throws BusinessException{
		fillCust(saleorder, orderVO.getHVO().getPk_supplier());
	}
	
	/**
	 * 填充供应商
	 * @param saleorder
	 * @param pk_supplier
	 * @throws BusinessException
	 */
	private void fillCust(SaleOrderVO saleorder, String pk_supplier) throws BusinessException{
		SupplierVO supplierVO = NCLocator.getInstance().lookup(ISupplierBaseInfoQryService.class).querySupplierVOByPk(pk_supplier);
		String pk_customer = supplierVO.getCorcustomer();
		if(pk_customer == null){
			throw new BusinessException("供应商【"+supplierVO.getName()+"】不存在关联的客户档案");
		}
		SaleOrderHVO hvo = saleorder.getParentVO();
		//ccustomerid 客户  chreceivecustid  收货客户  cinvoicecustid  开票客户 
		hvo.setAttributeValue("ccustomerid", pk_customer);
		hvo.setAttributeValue("chreceivecustid", pk_customer);
		hvo.setAttributeValue("cinvoicecustid", pk_customer);
	}
	
	/**
	 * 填充销售组织信息
	 * @param saleorder
	 * @throws BusinessException
	 */
	private void fillSaleOrg(SaleOrderVO saleorder) throws BusinessException{
		//pk_org  销售组织   pk_org_v  销售组织版本 
		String pk_org = saleorder.getChildrenVO()[0].getPk_org();
		Map<String, String> res = getPk_salesorg(pk_org);
		saleorder.getParentVO().setPk_org(res.get("pk_salesorg"));
		saleorder.getParentVO().setPk_org_v(res.get("pk_vid"));
	}
	
	/**
	 * 获取销售组织
	 * @param pk_stockorg
	 * @return
	 * @throws BusinessException
	 */
	private Map<String, String> getPk_salesorg(String pk_stockorg) throws BusinessException{
		if(!this.cache.containsKey(pk_stockorg)){
			StringBuffer sql = new StringBuffer();
			sql.append("select pk_salesorg,pk_vid from org_salesorg where code = ");
			sql.append("(select code from org_stockorg where pk_stockorg = '"+pk_stockorg+"') ");
			sql.append("and enablestate = 2 and nvl(dr,0) = 0 and islastversion  = 'Y'");
			@SuppressWarnings("unchecked")
			Map<String, String> res = (Map<String, String>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql.toString(),
					new MapProcessor());
			if(res == null || res.size() == 0){
				throw new BusinessException("按库存组织查询销售组织失败！");
			}
			this.cache.put(pk_stockorg, res);
			return res;
		}
		return this.cache.get(pk_stockorg);
	}
	
	/**
	 * 分析采购子项物料BOM
	 * @param orderVO
	 * @throws BusinessException
	 */
	private void runAnalyzeBOM(SaleOrderVO orderVO) throws BusinessException{
		String pk_group = orderVO.getParentVO().getPk_group();
		String pk_org = orderVO.getParentVO().getPk_org();
		SaleOrderBVO[] bvos = orderVO.getChildrenVO();
		List<SaleOrderBVO> analyBVOs = new ArrayList<SaleOrderBVO>();
		List<String> errMsg = new ArrayList<String>();
		for (SaleOrderBVO bvo : bvos) {
			String pk_materail = bvo.getCmaterialid();
			String pk_bom = queryBomID(pk_group, pk_org, pk_materail);
			if(pk_bom == null || pk_bom.trim().equals("")){
				errMsg.add("第【"+bvo.getVsrcrowno()+"】物料没有对应的BOM，无法协同销售订单\n");
			}
			if(errMsg.size() > 0){
				continue;
			}
			AggBomVO aggBomVO = queryAggBomVO(pk_bom);
			BomVO bomVO = (BomVO) aggBomVO.getParentVO();
			UFDouble parentNum = bomVO.getHnparentnum();
			UFDouble nnum = bvo.getNnum();
			BomItemVO[] itemVOs = aggBomVO.getChildrenVO();
			for(BomItemVO itemVO : itemVOs){
				SaleOrderBVO clone = (SaleOrderBVO) bvo.clone();
				UFDouble nitemnum = itemVO.getNitemnum();
				clone.setNnum(nnum.multiply(nitemnum).div(parentNum));
				clone.setNqtunitnum(nnum.multiply(nitemnum).div(parentNum));
				clone.setCmaterialid(itemVO.getCmaterialid());
				clone.setCmaterialvid(itemVO.getCmaterialvid());
				clone.setCunitid(itemVO.getCmeasureid());
				clone.setCastunitid(itemVO.getCassmeasureid());
				clone.setCqtunitid(itemVO.getCassmeasureid());
				clone.setVchangerate(itemVO.getVchangerate());
				analyBVOs.add(clone);
			}
		}
		if(errMsg.size() > 0){
			StringBuffer res = new StringBuffer();
			for(String msg : errMsg){
				res.append(msg);
			}
			throw new BusinessException(res.toString());
		}
		orderVO.setChildrenVO(analyBVOs.toArray(new SaleOrderBVO[analyBVOs.size()]));
	}
	
	private String queryBomID(String pk_group, String pk_org, String pk_materail) throws BusinessException{
		StringBuffer sql = new StringBuffer();
		sql.append("select bom.cbomid from bd_bom bom inner join bd_bom_useorg userorg on bom.cbomid = userorg.cbomid ");
		sql.append("where nvl(bom.dr,0) = 0 and nvl(userorg.dr,0) = 0 and bom.pk_group = '" + pk_group + "' and bom.hbdefault = 'Y' ");
		sql.append("and bom.hcmaterialid = '" + pk_materail + "' and userorg.pk_useorg = '" + pk_org + "'");
		return (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql.toString(), new ColumnProcessor());
	}
	
	private AggBomVO queryAggBomVO(String pk_bom) throws BusinessException{
		BillQuery<AggBomVO> query = new BillQuery<AggBomVO>(AggBomVO.class);
		return query.query(new String[] { pk_bom })[0];
	}
	
	private void findPrice(SaleOrderVO saleVO) throws BusinessException{
		IFindSalePrice findpricesrv = (IFindSalePrice)NCLocator.getInstance().lookup(IFindSalePrice.class);
		SaleOrderHVO hvo = saleVO.getParentVO();
		SaleOrderBVO[] bvos = saleVO.getChildrenVO();
		FindPriceParaVO[] paraVOs = initFindPriceParaVO(hvo, bvos);
		PromoteLimitPara[] paras = initPromoteLimitPara(hvo, bvos);
		FindPPLimitPriceResultVO resvos = findpricesrv.findPrmtLimitPrice(paraVOs, paras, hvo.getPk_org());
		if(resvos.getHasErrorMsg().booleanValue()){
			throw new BusinessException(resvos.getErrorMsg());
		}
		FindPriceResultVO[] resultVOs = resvos.getResultvos();
		List<String> reMsg = new ArrayList<String>();
		for(int index = 0; index < bvos.length; index++){
			if(resultVOs[index] == null){
				String sql = "select code,name from bd_material_v where pk_material = '"+bvos[index].getCmaterialid()+"'";
				@SuppressWarnings("unchecked")
				List<Map<String, String>> res =  (List<Map<String, String>>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, 
						new MapListProcessor());
				reMsg.add("物料【"+res.get(0).get("code")+","+res.get(0).get("name")+"】询价失败！\n");
				continue;
			}
			bvos[index].setNqtorigtaxprice(resultVOs[index].getPrice());
		}
		if(reMsg.size() > 0){
			StringBuffer msg = new StringBuffer();
			for(String res : reMsg){
				msg.append(res);
			}
			throw new BusinessException(reMsg.toString());
		}
	}
	
	private FindPriceParaVO[] initFindPriceParaVO(SaleOrderHVO hvo, SaleOrderBVO[] bvos){
		FindPriceParaVO[] paraVOs = new FindPriceParaVO[bvos.length];
		for(int index = 0; index < paraVOs.length; index++){
			FindPriceParaVO paraVO = new FindPriceParaVO();
			SaleOrderBVO bvo = bvos[index];
			paraVO.setPk_marbasclass(null);
			paraVO.setPk_marsaleclass(null);
			paraVO.setPk_custclass(null);
			paraVO.setPk_custsaleclass(null);
			paraVO.setPk_areacl(null);
			paraVO.setPk_brand(null);
			paraVO.setTpricedate(new UFDateTime());
			paraVO.setPk_material(bvo.getCmaterialid());
			paraVO.setPk_customer(hvo.getCcustomerid());
			paraVO.setPk_balatype(hvo.getCbalancetypeid());
			paraVO.setPk_channeltype(hvo.getCchanneltypeid());
			paraVO.setPk_qualitylevel(bvo.getCqualitylevelid());
			paraVO.setPk_prodline(bvo.getCprodlineid());
			paraVO.setPk_currtype(bvo.getCcurrencyid());
			paraVO.setPk_unit(bvo.getCunitid());
			paraVO.setPk_group(bvo.getPk_group());
			paraVO.setPk_pricetype(bvo.getCpriceitemid());
			paraVO.setPk_sendtype(hvo.getCtransporttypeid());
			paraVO.setVsaleorgtype(hvo.getCtrantypeid());
			paraVO.setVfree1(bvo.getVfree1());
			paraVO.setVfree2(bvo.getVfree2());
			paraVO.setVfree3(bvo.getVfree3());
			paraVO.setVfree4(bvo.getVfree4());
			paraVO.setVfree5(bvo.getVfree5());
			paraVO.setVfree6(bvo.getVfree6());
			paraVO.setVfree7(bvo.getVfree7());
			paraVO.setVfree8(bvo.getVfree8());
			paraVO.setVfree9(bvo.getVfree9());
			paraVO.setVfree10(bvo.getVfree10());
			paraVOs[index] = paraVO;
		}
		return paraVOs;
	}
	
	private PromoteLimitPara[] initPromoteLimitPara(SaleOrderHVO hvo, SaleOrderBVO[] bvos){
		PromoteLimitPara[] paras = new PromoteLimitPara[bvos.length];
		for(int index = 0; index < bvos.length; index++){
			paras[index] = new PromoteLimitPara();
			paras[index].setFindpricebillbid(bvos[index].getCsaleorderbid());
			paras[index].setBilltypecode(SOBillType.Order.getCode());
			paras[index].setFindpricerowNo(bvos[index].getCrowno());
			paras[index].setFindpricebillbid(bvos[index].getCsrcbid());
			paras[index].setFindpricebillbid(bvos[index].getVsrctype());
		}
		return paras;
	}
}
