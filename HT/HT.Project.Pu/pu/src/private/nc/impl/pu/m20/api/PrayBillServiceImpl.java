package nc.impl.pu.m20.api;

import java.util.HashSet;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.bd.supplier.baseinfo.ISupplierBaseInfoQryService;
import nc.pubitf.pu.m20.api.IPrayBillService;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;

public class PrayBillServiceImpl implements IPrayBillService {

	private String[] fields = new String[]{"def1","def2","def3","def4","def5","def6","def7"};
	@Override
	public void adjustRequireDate(PraybillVO[] aggVOs) throws BusinessException {
		for(PraybillVO aggVO : aggVOs){
			PraybillItemVO[] itemVOs = aggVO.getBVO();
			for(PraybillItemVO itemVO : itemVOs){
				String pk_suggestsupplier = itemVO.getPk_suggestsupplier();
				UFDate requireDate = getAdjustDate(pk_suggestsupplier, new UFDate());
				itemVO.setVbdef1(itemVO.getDreqdate().toString());
				itemVO.setDreqdate(requireDate);
			}
		}
	}

	private UFDate getAdjustDate(String pk_suggestsupplier, UFDate dreqdate){
		int days = getAdjustDays(pk_suggestsupplier, dreqdate);
		return dreqdate.getDateAfter(days);
	}
	
	private int getAdjustDays(String pk_suggestsupplier, UFDate dreqdate){
		SupplierVO supplierVO = null;
		try {
			supplierVO = NCLocator.getInstance().lookup(ISupplierBaseInfoQryService.class).querySupplierVOByPk(pk_suggestsupplier);
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		Set<Integer> set = new HashSet<Integer>();
		for(int index = 0; index < fields.length; index++){
			if("Y".equals(supplierVO.getAttributeValue(fields[index]))){
				set.add(index+1);
			}
		}
		int week = dreqdate.getWeek();
		int days = 0;
		if(set.size() > 0){
			for(; days < 7; days++){
				if(set.contains(week)){
					break;
				}
				if(week == 7){
					week = 1;
				}else{
					week++;
				}
			}
		}
		return days;
	}

}
