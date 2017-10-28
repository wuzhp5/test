package nc.ui.cof.orderplan.maintain.action;

import java.awt.event.ActionEvent;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.bd.baseservice.ArrayClassConvertUtil;
import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.mes.ws.serivce.IUploadBillWSService;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.itf.sync.log.ILogger;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.pf.PfUtilClient;
import nc.ui.pubapp.uif2app.AppUiState;
import nc.ui.pubapp.uif2app.actions.pflow.SaveScriptAction;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.UIState;
import nc.vo.cof.orderplan.entity.AggOrderplanVO;
import nc.vo.cof.orderplan.entity.OrderplanBTPVO;
import nc.vo.cof.orderplan.entity.OrderplanBVO;
import nc.vo.cof.orderplan.exception.OrderplanTotalNumRewriteException;
import nc.vo.espub.res.BusinessCheck;
import nc.vo.espub.transfer.cubebill.CubeBillTransferTool;
import nc.vo.pub.AggregatedValueObject;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.pubapp.pattern.model.entity.bill.IBill;
import nc.vo.pubapp.pattern.model.meta.entity.vo.PseudoColumnAttribute;
import nc.vo.pubapp.pflow.PFReturnObject;
import nc.vo.pubapp.pflow.PfUserObject;
import nc.vo.sync.log.LoggerVO;
import net.sf.json.JSONObject;

import org.apache.commons.lang.ArrayUtils;

import com.yonyou.ec.report.view.PlanMainboard;
import com.yonyou.vo.ecapppub.plan.entity.AggPlantemplateVO;
import com.yonyou.vo.ecapppub.plan.enumeration.EnumCtrlType;

/**
 * Ҫ���ƻ����水ť��
 * 
 * @author wangshqb
 * 
 */
@SuppressWarnings("restriction")
public class OrderPlanSaveAction extends SaveScriptAction {
	private static final long serialVersionUID = 735788953901845363L;

	// ----------------------------------------------------- Instance Variables

	/**
	 * �ƻ���������
	 */
	private PlanMainboard planMainboard;

	// ----------------------------------------------------------- Constructors

	public OrderPlanSaveAction() {
		super();
	}

	// ���ο��� by wuzhp
	public void doAction(ActionEvent e) throws Exception {
		super.doAction(e);
		// ��EC722010�����ܱ���
		if ("EC722010".equals(getModel().getContext().getNodeCode()) && "ECF1".equals(getFlowContext().getBillType())) {
			Map<String, String> res = UploadBill2MES();
			MessageDialog.showHintDlg(null, "�����ϴ���ʾ", res.toString());
		}
	}

	// ���ο��� by wuzhp
	private Map<String, String> UploadBill2MES() throws BusinessException {
		IUploadBillWSService service = NCLocator.getInstance().lookup(IUploadBillWSService.class);
		BillManageModel billModel = getModel();
		Object[] objs = billModel.getSelectedOperaDatas();
		AggOrderplanVO[] planVOs = ArrayClassConvertUtil.convert(objs, AggOrderplanVO.class);
		Map<String, String> maps = new HashMap<String, String>();
		for (AggOrderplanVO planVO : planVOs) {
			String res = service.uploadBill("farplan", service.bill2Json("farplan", planVO));
			if (res != null && res.contains("successinfo")) {
				maps.put(planVO.getParentVO().getVbillcode(), "�ϴ�MES�ɹ�");
			} else {
				maps.put(planVO.getParentVO().getVbillcode(), "�ϴ�MESʧ��!MES��ִ��Ϣ����" + res + "��");
			}
		}
		return maps;
	}

	// ------------------------------------------------------------- Properties

	public void setPlanMainboard(PlanMainboard planMainboard) {
		this.planMainboard = planMainboard;
	}

	// --------------------------------------------------------- Public Methods

	@Override
	protected Object[] processBefore(Object[] vos) {
		if (ArrayUtils.isEmpty(vos)) {
			return vos;
		}
		AggOrderplanVO aggVO = (AggOrderplanVO) vos[0];
		Object newvalue = this.planMainboard.getDesigner().getValue();
		if (newvalue != null && newvalue.getClass().isArray()) {
			List<OrderplanBVO> list = new ArrayList<OrderplanBVO>();
			int i = 0;
			String name = PseudoColumnAttribute.PSEUDOCOLUMN;
			for (Object obj : (Object[]) newvalue) {
				if (obj instanceof OrderplanBVO) {
					((OrderplanBVO) obj).setAttributeValue(name, i);
					i++;
					OrderplanBTPVO[] btvos = ((OrderplanBVO) obj).getOrderplanBTPVOs();
					for (int j = 0; j < btvos.length; j++) {
						OrderplanBTPVO orderplanBTPVO = btvos[j];
						orderplanBTPVO.setAttributeValue(name, j);
					}
					list.add((OrderplanBVO) obj);
				}
			}
			if (list.size() == 0)
				ExceptionUtils.wrappBusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "0ec72001-0015")/*
																																		 * @
																																		 * res
																																		 * "���岻��Ϊ��"
																																		 */);
			aggVO.setChildrenBVO(list.toArray(new OrderplanBVO[0]));
		}
		if (this.getModel().getUiState() == UIState.EDIT) {
			return vos;
		}
		return this.produceLightVO((AbstractBill[]) vos);
	}

	@Override
	protected void processReturnObj(Object[] pretObj) throws Exception {
		Object[] retObj = pretObj;
		if (retObj == null || retObj.length == 0) {
			if (PfUtilClient.isSuccess()) {
				this.model.setAppUiState(AppUiState.NOT_EDIT);
			}
			showMsginfo(retObj);
			return;
		}
		if (pretObj instanceof PFReturnObject[]) {
			retObj = ((PFReturnObject) pretObj[0]).getBills();
		}

		if (PfUtilClient.isSuccess() && retObj[0] instanceof AggregatedValueObject) {
			// ����(ת������)
			if (UIState.ADD == this.model.getAppUiState().getUiState()) {
				new CubeBillTransferTool<IBill>().combineBillForClient((IBill[]) super.getFullOldVOs(), (IBill[]) retObj);
				this.model.directlyAdd(super.getFullOldVOs()[0]);
				this.model.setAppUiState(AppUiState.NOT_EDIT);
			} else {
				// �п�������;ȡ�����·��ص����ݳ��ȸ�ԭʼ�ĳ��Ȳ�һ�£�����Ҫ��ȡ
				IBill[] updatedVos = super.getFullOldVOs();
				if (retObj.length < super.getFullOldVOs().length) {
					updatedVos = (IBill[]) Array.newInstance(retObj[0].getClass(), retObj.length);
					System.arraycopy(super.getFullOldVOs(), 0, updatedVos, 0, retObj.length);
				}
				if (this.isLightBillUsed()) {
					new CubeBillTransferTool<IBill>().combineBillForClient(updatedVos, (IBill[]) retObj);
					this.model.directlyUpdate(updatedVos);
				} else {
					this.model.directlyUpdate(retObj);
				}
				this.model.setAppUiState(AppUiState.NOT_EDIT);

			}

		}
		showMsginfo(retObj);
	}

	@Override
	protected AbstractBill[] produceLightVO(AbstractBill[] newVO) {
		if (!super.isLightBillUsed()) {
			return newVO;
		}
		// if (!(newVO[0] instanceof IBill))
		// ExceptionUtil.throwAsIllegalArgument(new IllegalArgumentException(
		// "���ݱ���ʵ��Ibill�ӿ�"));
		CubeBillTransferTool<IBill> tool = new CubeBillTransferTool<IBill>();
		AbstractBill[] oldVO = newVO;

		IBill[] lightVOs = null;
		if (AppUiState.EDIT == this.model.getAppUiState()) {
			oldVO = new AbstractBill[] { (AbstractBill) this.model.getSelectedData() };
			lightVOs = tool.getBillForToServer(oldVO, newVO);
		} else {
			lightVOs = tool.getInsertBillForToServer(oldVO);
		}
		// ����󲹳������ֶ�
		super.fillInfoAfterLight((AbstractBill[]) lightVOs);
		return (AbstractBill[]) lightVOs;
	}

	@Override
	protected boolean isResume(IResumeException resumeInfo) {
		PfUserObject paravo = this.getFlowContext().getUserObj();
		if (null == paravo) {
			paravo = new PfUserObject();
			this.getFlowContext().setUserObj(paravo);
		}
		// �ۼ��´���������д���
		boolean isTotalNumRewriteResume = this.processTotalNumRewriteResume(resumeInfo, paravo);

		return isTotalNumRewriteResume;
	}

	/**
	 * �ۼ��´����������
	 * 
	 * @param resumeInfo
	 * @param paravo
	 * @return
	 * @author lirma
	 */
	private boolean processTotalNumRewriteResume(IResumeException resumeInfo, PfUserObject paravo) {
		if (BusinessCheck.OrderPlanTotalNumRewriteCheck.getCheckCode().equals(resumeInfo.getBusiExceptionType())) {
			AggPlantemplateVO templatevo = ((OrderplanTotalNumRewriteException) resumeInfo).getTemplatevo();
			Integer ctrType = templatevo.getParentVO().getFctrltype();

			if (ctrType.equals(EnumCtrlType.WARNING.toInteger())) {
				int back = MessageDialog.showYesNoDlg(
						WorkbenchEnvironment.getInstance().getWorkbench().getParent(),
						nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "0ec72001-0016")/*
																											 * @
																											 * res
																											 * "�ۼ��´����������"
																											 */,
						NCLangRes.getInstance().getStrByID("ec72001_0", "OrderPlanSaveAction-0000", null,
								new String[] { ((BusinessException) resumeInfo).getMessage() })/*
																								 * {
																								 * 0
																								 * }
																								 * �Ƿ������
																								 */);

				if (UIDialog.ID_YES == back) {
					Map<String, Boolean> mapCheck = paravo.getBusinessCheckMap();
					mapCheck.put(BusinessCheck.OrderPlanTotalNumRewriteCheck.getCheckCode(), Boolean.FALSE);
				} else {
					return false;
				}
				return true;
			} else if (ctrType.equals(EnumCtrlType.CONTROL.toInteger())) {
				ExceptionUtils.wrappException((Exception) resumeInfo);
			}
			return false;
		}
		return true;
	}
}