package nc.pubimpl.so.m30.api;

import nc.impl.pubapp.pattern.data.bill.BillQuery;
import nc.itf.scmpub.reference.uap.pf.PfServiceScmUtil;
import nc.pubimpl.so.m30.api.check.SaleOrderValidator;
import nc.pubimpl.so.m30.api.fill.SaleOrderSaveDefValue;
import nc.pubitf.so.m30.api.ISaleOrderMaintain4Push;
import nc.vo.pub.BusinessException;
import nc.vo.scmpub.check.billvalidate.BillVOsCheckRule;
import nc.vo.scmpub.res.billtype.SOBillType;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.so.pub.SOConstant;
import nc.vo.so.pub.util.AggVOUtil;

/**
 * @description ���۶����־û�����ʵ��1
 * @scene
 * 
 * @param ��
 * 
 * 
 * @since 6.5
 * @version 2015-10-20 ����1:52:34
 * @author ����
 */
public class SaleOrderMaintain4PushImpl implements ISaleOrderMaintain4Push {

	@Override
	public SaleOrderVO[] insertBills(SaleOrderVO[] vos) throws BusinessException {
		SaleOrderVO[] fillvos = vos;

		// 1��������У��
		BillVOsCheckRule checker = new BillVOsCheckRule(new SaleOrderValidator());
		checker.check(fillvos);

		// 2��ǿ��Ĭ��ֵ���
		SaleOrderSaveDefValue filldatarule = new SaleOrderSaveDefValue();
		filldatarule.setDefultValue(fillvos);

		// 3����ֵ������
		SaleOrderVO[] combinBillVOs = (SaleOrderVO[]) AggVOUtil.combinBillVO(fillvos, vos);
		//��ʱ���
		for(SaleOrderVO combinBillVO : combinBillVOs){ 
			SaleOrderBVO[] bvos = combinBillVO.getChildrenVO();
			for(SaleOrderBVO bvo : bvos){
				bvo.setCrececountryid("0001Z010000000079UJJ");
			}
		}
		// 3������
		SaleOrderVO[] retvos = (SaleOrderVO[]) PfServiceScmUtil.processBatch(SOConstant.WRITE, SOBillType.Order.getCode(), combinBillVOs, null, null);
		return retvos;
	}

	@Override
	public void deleteBillsByID(String[] ids) throws BusinessException {
		BillQuery<SaleOrderVO> query = new BillQuery<>(SaleOrderVO.class);
		SaleOrderVO[] deletevos = query.query(ids);
		PfServiceScmUtil.processBatch(SOConstant.DELETE, SOBillType.Order.getCode(), deletevos, null, null);
	}

}
