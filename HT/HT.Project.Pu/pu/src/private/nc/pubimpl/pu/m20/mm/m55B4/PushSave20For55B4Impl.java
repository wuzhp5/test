package nc.pubimpl.pu.m20.mm.m55B4;

import nc.bs.framework.common.NCLocator;
import nc.impl.pu.m20.api.PrayBillServiceImpl;
import nc.pubimpl.pu.m20.mm.action.PushSave20For55B4Action;
import nc.pubitf.pu.m20.api.IPrayBillService;
import nc.pubitf.pu.m20.mm.m55B4.IPushSave20For55B4;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�ƻ�������ʽ�����빺��
 * <li>55B4 �ƻ�����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.1
 * @since 6.1
 * @author lixyp
 * @time 2011-12-26 ����04:17:37
 */
public class PushSave20For55B4Impl implements IPushSave20For55B4 {

	@Override
	public void pushSaveBills(PraybillVO[] praybills) throws BusinessException {
		try {
			// ������������ by wzp
			new PrayBillServiceImpl().adjustRequireDate(praybills);
			new PushSave20For55B4Action().pushSave(praybills);
		} catch (Exception ex) {
			ExceptionUtils.marsh(ex);
		}
	}
}
