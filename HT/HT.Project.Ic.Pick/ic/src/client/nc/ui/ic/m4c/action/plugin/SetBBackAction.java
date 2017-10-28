package nc.ui.ic.m4c.action.plugin;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.pubitf.ic.extend.bc.IBCICBackRelaService;
import nc.ui.ic.general.model.ICGenBizEditorModel;
import nc.ui.uif2.NCAction;
import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SetBBackAction extends NCAction {

  private ICGenBizEditorModel editorModel;

  public ICGenBizEditorModel getEditorModel() {
    return this.editorModel;
  }

  public void setEditorModel(ICGenBizEditorModel editorModel) {
    this.editorModel = editorModel;
  }

  public SetBBackAction() {
    super();
    super.setCode("setBBackAction");
    super.setBtnName("设置为已回传");
  }

  @Override
  public void doAction(ActionEvent e) throws Exception {
    if (editorModel.getICBizModel().getSelectedData() == null) {
      ExceptionUtils.wrappBusinessException("请选择单据");
    }
    ICBillBodyVO[] bodys =
        editorModel.getICBizModel().getSelectedData().getBodys();
    if (bodys == null || bodys.length == 0) {
      return;
    }
    String[] cgeneralbids = new String[bodys.length];
    for (int i = 0; i < bodys.length; i++) {
      cgeneralbids[i] = bodys[i].getCgeneralbid();
    }

    NCLocator.getInstance().lookup(IBCICBackRelaService.class)
        .setICBBack(cgeneralbids);
  }

}
