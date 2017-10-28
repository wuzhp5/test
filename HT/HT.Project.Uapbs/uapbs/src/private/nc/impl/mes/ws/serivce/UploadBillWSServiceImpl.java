package nc.impl.mes.ws.serivce;

import nc.bs.sync.log.Logger;
import nc.itf.mes.ws.serivce.IUploadBillWSService;
import nc.vo.cof.orderplan.entity.AggOrderplanVO;
import nc.vo.ic.m4e.entity.TransInVO;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.pub.BusinessException;

import org.tempuri.Ncws;
import org.tempuri.NcwsSoap;

public class UploadBillWSServiceImpl implements IUploadBillWSService{

	private Ncws proxy;
	private NcwsSoap soap;
	
	public UploadBillWSServiceImpl(){
		proxy = new Ncws();
		soap = proxy.getNcwsSoap();
	}
	
	@Override
	public String uploadBill(String billType, String json)
			throws BusinessException {
		String res = null;
		try{
			res = soap.uploadNCPlan(billType, json);
		}catch(Exception e){
			throw new BusinessException("请求MES【"+billType+"】服务出错:"+e.getMessage());
		}
		Logger.uploadLog("单据类型【"+billType+"】", "NC上传JSON：【"+json+"】\r\nMES回执内容：【"+res+"】");
		return res;
	}
	
	@Override
	public String bill2Json(String billType, Object bill) throws BusinessException {
		if("farplan".equalsIgnoreCase(billType)){
			return TranslatorBill2JSONUtil.planBill2Json((AggOrderplanVO) bill);
		}else if("mainplan".equalsIgnoreCase(billType)){
			return TranslatorBill2JSONUtil.ploBill2Json((AggregatedPoVO) bill);
		}else if("transbill".equalsIgnoreCase(billType)){
			return TranslatorBill2JSONUtil.transBill2Json((TransInVO) bill);
		}
		throw new BusinessException("【"+billType+"】无效接口类型！");
	}

}
