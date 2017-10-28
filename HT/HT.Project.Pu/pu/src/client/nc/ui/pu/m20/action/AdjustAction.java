package nc.ui.pu.m20.action;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.itf.bd.supplier.baseinfo.ISupplierBaseInfoQryService;
import nc.ui.pubapp.ClientContext;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.pubapp.uif2app.view.ShowUpableBillForm;
import nc.ui.uif2.NCAction;
import nc.vo.bd.supplier.SupplierVO;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pub.lang.UFDate;

@SuppressWarnings("restriction")
public class AdjustAction extends NCAction {

	private static final long serialVersionUID = -8499883462345223256L;
	private ShowUpableBillForm editor;
	private BillManageModel model;
	private String[] fields = new String[]{"def1","def2","def3","def4","def5","def6","def7"};
	
	public AdjustAction() {
		super();
		super.setBtnName("��ȡ��������");
		super.setCode("Adjust");
	}

	@Override
	public void doAction(ActionEvent event) throws Exception {
		int length = this.editor.getBillCardPanel().getRowCount();
		PraybillVO bill = (PraybillVO) getModel().getSelectedData();
		PraybillItemVO[] ItemVOs = bill.getBVO();
		Map<String, UFDate> map = new HashMap<String, UFDate>();
		getDate(ItemVOs, map);
		for(int index = 0; index < length; index++){
			//�������жϸ����Ƿ�Ϊ��Ч��
			String pk_srcmaterial = (String) this.editor.getBillCardPanel().getBodyValueAt(index, PraybillItemVO.PK_SRCMATERIAL);
			if(pk_srcmaterial == null){
				continue;
			}
			//�йر�У��
			UFBoolean browclose  = (UFBoolean) this.editor.getBillCardPanel().getBodyValueAt(index, PraybillItemVO.BROWCLOSE);
			if(browclose.booleanValue()){
				this.editor.getBillCardPanel().setBodyValueAt("�����Ѿ��رգ��޷��Զ���ȡ�������ڣ�", index, PraybillItemVO.VBMEMO);
				continue;
			}
			//��Ӧ�̼���
			String pk_suggestsupplier = (String) this.editor.getBillCardPanel().getBodyValueAt(index, PraybillItemVO.PK_SUGGESTSUPPLIER);
			if(pk_suggestsupplier == null){
				this.editor.getBillCardPanel().setBodyValueAt("�Զ���ȡ��������ʱ�����鹩Ӧ�̲���Ϊ�գ�", index, PraybillItemVO.VBMEMO);
				continue;
			}
			//������������
			String pk_praybill_b = (String) this.editor.getBillCardPanel().getBodyValueAt(index, PraybillItemVO.PK_PRAYBILL_B);
			if(map.keySet().contains(pk_praybill_b)){
				this.editor.getBillCardPanel().setBodyValueAt(map.get(pk_praybill_b), index, PraybillItemVO.VBDEF1);
			}
			this.editor.getBillCardPanel().setBodyValueAt(getAdjustDate(pk_suggestsupplier, map.get(pk_praybill_b)), index, PraybillItemVO.DREQDATE);
		}
	}
	
	private void getDate(PraybillItemVO[] ItemVOs, Map<String, UFDate> map){
		for(PraybillItemVO itemVO : ItemVOs){
			map.put(itemVO.getPrimaryKey(), itemVO.getDreqdate());
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

	public void setEditor(ShowUpableBillForm editor) {
		this.editor = editor;
	}

	public void setModel(BillManageModel model) {
		this.model = model;
		this.model.addAppEventListener(this);
	}

	public ShowUpableBillForm getEditor() {
		return editor;
	}

	public BillManageModel getModel() {
		return model;
	}

}
