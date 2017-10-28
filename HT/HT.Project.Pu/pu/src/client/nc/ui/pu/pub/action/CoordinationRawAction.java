package nc.ui.pu.pub.action;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.bs.bd.baseservice.ArrayClassConvertUtil;
import nc.bs.framework.common.NCLocator;
import nc.funcnode.ui.AbstractFunclet;
import nc.funcnode.ui.FuncletInitData;
import nc.funcnode.ui.FuncletWindowLauncher;
import nc.itf.so.m30.self.ISaleOrderMaintainApp;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanListProcessor;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.pubitf.pu.m20.api.IPushSave21ForM30;
import nc.pubitf.so.m30.api.ISaleOrderMaintain4Push;
import nc.ui.pu.uif2.PUBillManageModel;
import nc.ui.sm.power.FuncRegisterCacheAccessor;
import nc.ui.so.m30.billui.view.SaleOrderBillForm;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.UIState;
import nc.vo.bd.bom.bom0202.entity.BomItemVO;
import nc.vo.bd.bom.bom0202.entity.BomVO;
import nc.vo.pu.m21.entity.OrderHeaderVO;
import nc.vo.pu.m21.entity.OrderItemVO;
import nc.vo.pu.m21.entity.OrderVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.sm.funcreg.FuncRegisterVO;
import nc.vo.so.m30.entity.SaleOrderBVO;
import nc.vo.so.m30.entity.SaleOrderVO;
import nc.vo.uif2.LoginContext;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.linkoperate.ILinkType;
import nc.ui.pub.msg.PfLinkData;
import nc.ui.pubapp.uif2app.view.BillForm;

@SuppressWarnings("restriction")
public class CoordinationRawAction extends NCAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 470962592601394468L;
	
	private PUBillManageModel model;
	private BillForm editor;
	private SaleOrderBillForm billform;
	
	public CoordinationRawAction() {
		super();
		this.setCode("coordinationRaw");
		this.setBtnName("协同原材料");
	}

	
	@Override
	public void doAction(ActionEvent e) throws Exception {
		PUBillManageModel model = getModel();  
		Object[] objs = model.getSelectedOperaDatas();
		if(objs == null){
			MessageDialog.showErrorDlg(getModel().getContext().getEntranceUI(),"错误","请选中行后进行操作"); 
			return;
		}
		OrderVO[] orderVOs = ArrayClassConvertUtil.convert(objs, OrderVO.class);
		NCLocator.getInstance().lookup(IPushSave21ForM30.class).analyseOrder2SaleOrder(orderVOs);
	}


	public SaleOrderBillForm getBillform() {
		return billform;
	}

	public void setBillform(SaleOrderBillForm billform) {
		this.billform = billform;
	}

	public BillForm getEditor() {
		return this.editor;
	}
	public void setEditor(BillForm editor) {
		
		this.editor =editor;
	}
	
	public PUBillManageModel getModel() {
		return model;
	}
	public void setModel(PUBillManageModel model) {
		this.model = model;
	}
	

	protected boolean isActionEnable(){
		return this.getModel().getUiState() == UIState.NOT_EDIT;
		       
	}
}
