package nc.ui.ic.general.model.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class GeneralButtonAction extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.ic.general.action.GeneralAddAction getAddAction(){
 if(context.get("addAction")!=null)
 return (nc.ui.ic.general.action.GeneralAddAction)context.get("addAction");
  nc.ui.ic.general.action.GeneralAddAction bean = new nc.ui.ic.general.action.GeneralAddAction();
  context.put("addAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setInterceptor(getCompositeActionInterceptor_7ffa19());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_7ffa19(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#7ffa19")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#7ffa19");
  nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#7ffa19",bean);
  bean.setInterceptors(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add((nc.ui.uif2.actions.ActionInterceptor)findBeanInUIF2BeanFactory("cardInterceptor"));  return list;}

public nc.ui.ic.general.action.GeneralSelfAddAction getSelfAddAction(){
 if(context.get("selfAddAction")!=null)
 return (nc.ui.ic.general.action.GeneralSelfAddAction)context.get("selfAddAction");
  nc.ui.ic.general.action.GeneralSelfAddAction bean = new nc.ui.ic.general.action.GeneralSelfAddAction();
  context.put("selfAddAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setInterceptor(getCompositeActionInterceptor_1fc8097());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_1fc8097(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1fc8097")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1fc8097");
  nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#1fc8097",bean);
  bean.setInterceptors(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add((nc.ui.uif2.actions.ActionInterceptor)findBeanInUIF2BeanFactory("cardInterceptor"));  return list;}

public nc.ui.ic.general.action.GeneralAddManualAction getAddManualAction(){
 if(context.get("addManualAction")!=null)
 return (nc.ui.ic.general.action.GeneralAddManualAction)context.get("addManualAction");
  nc.ui.ic.general.action.GeneralAddManualAction bean = new nc.ui.ic.general.action.GeneralAddManualAction();
  context.put("addManualAction",bean);
  bean.setSourceBillType("MANUAL");
  bean.setSourceBillName(getI18nFB_5e49e8());
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setInterceptor(getCompositeActionInterceptor_465d1b());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_5e49e8(){
 if(context.get("nc.ui.uif2.I18nFB#5e49e8")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#5e49e8");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#5e49e8",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0335");
  bean.setDefaultValue("自制");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#5e49e8",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor getCompositeActionInterceptor_465d1b(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#465d1b")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#465d1b");
  nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.CompositeActionInterceptor#465d1b",bean);
  bean.setInterceptors(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add((nc.ui.uif2.actions.ActionInterceptor)findBeanInUIF2BeanFactory("cardInterceptor"));  return list;}

public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessor(){
 if(context.get("transferViewProcessor")!=null)
 return (nc.ui.pubapp.billref.dest.TransferViewProcessor)context.get("transferViewProcessor");
  nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
  context.put("transferViewProcessor",bean);
  bean.setList((nc.ui.pubapp.uif2app.view.ShowUpableBillListView)findBeanInUIF2BeanFactory("list"));
  bean.setBillForm((nc.ui.pubapp.uif2app.view.PubShowUpableBillForm)findBeanInUIF2BeanFactory("card"));
  bean.setActionContainer((nc.ui.uif2.actions.IActionContributor)findBeanInUIF2BeanFactory("actionsOfList"));
  bean.setCardActionContainer((nc.ui.uif2.actions.AbstractToftPanelActionContainer)findBeanInUIF2BeanFactory("actionsOfCard"));
  bean.setSaveAction(getSaveAction());
  bean.setCancelAction(getCancelAction());
  bean.setTransferLogic(getICDefaultBillDataLogic_18abaa1());
  bean.setQueryAreaShell((nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)findBeanInUIF2BeanFactory("queryArea"));
  bean.setQueryInfoToolbarPanel((nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)findBeanInUIF2BeanFactory("queryInfo"));
  bean.setListProcessor(getTransferListProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.general.util.ICDefaultBillDataLogic getICDefaultBillDataLogic_18abaa1(){
 if(context.get("nc.ui.ic.general.util.ICDefaultBillDataLogic#18abaa1")!=null)
 return (nc.ui.ic.general.util.ICDefaultBillDataLogic)context.get("nc.ui.ic.general.util.ICDefaultBillDataLogic#18abaa1");
  nc.ui.ic.general.util.ICDefaultBillDataLogic bean = new nc.ui.ic.general.util.ICDefaultBillDataLogic();
  context.put("nc.ui.ic.general.util.ICDefaultBillDataLogic#18abaa1",bean);
  bean.setBillForm((nc.ui.pubapp.uif2app.view.PubShowUpableBillForm)findBeanInUIF2BeanFactory("card"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.deal.TransferListProcessor getTransferListProcessor(){
 if(context.get("transferListProcessor")!=null)
 return (nc.ui.ic.general.deal.TransferListProcessor)context.get("transferListProcessor");
  nc.ui.ic.general.deal.TransferListProcessor bean = new nc.ui.ic.general.deal.TransferListProcessor();
  context.put("transferListProcessor",bean);
  bean.setStrategy(getScalePrcStrategy());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.deal.GenBillScalePrcStrategy getScalePrcStrategy(){
 if(context.get("scalePrcStrategy")!=null)
 return (nc.ui.ic.general.deal.GenBillScalePrcStrategy)context.get("scalePrcStrategy");
  nc.ui.ic.general.deal.GenBillScalePrcStrategy bean = new nc.ui.ic.general.deal.GenBillScalePrcStrategy();
  context.put("scalePrcStrategy",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getCommitActionMenu(){
 if(context.get("commitActionMenu")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("commitActionMenu");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("commitActionMenu",bean);
  bean.setCode("ApproveMenu");
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add(getCommitAction());  list.add(getUnCommitAction());  return list;}

public nc.ui.ic.general.action.GeneralCommitAction getCommitAction(){
 if(context.get("commitAction")!=null)
 return (nc.ui.ic.general.action.GeneralCommitAction)context.get("commitAction");
  nc.ui.ic.general.action.GeneralCommitAction bean = new nc.ui.ic.general.action.GeneralCommitAction();
  context.put("commitAction",bean);
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setActionName("SAVE");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralSaveCommitAction getSavecommitAction(){
 if(context.get("savecommitAction")!=null)
 return (nc.ui.ic.general.action.GeneralSaveCommitAction)context.get("savecommitAction");
  nc.ui.ic.general.action.GeneralSaveCommitAction bean = new nc.ui.ic.general.action.GeneralSaveCommitAction(getSaveAction(),getCommitAction());  context.put("savecommitAction",bean);
  bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralUnCommitAction getUnCommitAction(){
 if(context.get("unCommitAction")!=null)
 return (nc.ui.ic.general.action.GeneralUnCommitAction)context.get("unCommitAction");
  nc.ui.ic.general.action.GeneralUnCommitAction bean = new nc.ui.ic.general.action.GeneralUnCommitAction();
  context.put("unCommitAction",bean);
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setActionName("UNSAVE");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getApproveActionMenu(){
 if(context.get("approveActionMenu")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("approveActionMenu");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("approveActionMenu",bean);
  bean.setCode("ApproveMenu");
  bean.setActions(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getApproveAction());  list.add(getUnApproveAction());  return list;}

public nc.ui.ic.general.action.GeneralApproveAction getApproveAction(){
 if(context.get("approveAction")!=null)
 return (nc.ui.ic.general.action.GeneralApproveAction)context.get("approveAction");
  nc.ui.ic.general.action.GeneralApproveAction bean = new nc.ui.ic.general.action.GeneralApproveAction();
  context.put("approveAction",bean);
  bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralUnApproveAction getUnApproveAction(){
 if(context.get("unApproveAction")!=null)
 return (nc.ui.ic.general.action.GeneralUnApproveAction)context.get("unApproveAction");
  nc.ui.ic.general.action.GeneralUnApproveAction bean = new nc.ui.ic.general.action.GeneralUnApproveAction();
  context.put("unApproveAction",bean);
  bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralAddFromSourceAction getAddFromSourceAction(){
 if(context.get("addFromSourceAction")!=null)
 return (nc.ui.ic.general.action.GeneralAddFromSourceAction)context.get("addFromSourceAction");
  nc.ui.ic.general.action.GeneralAddFromSourceAction bean = new nc.ui.ic.general.action.GeneralAddFromSourceAction();
  context.put("addFromSourceAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.ic.general.action.GeneralEditAction)context.get("editAction");
  nc.ui.ic.general.action.GeneralEditAction bean = new nc.ui.ic.general.action.GeneralEditAction();
  context.put("editAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setInterceptor((nc.ui.uif2.actions.ActionInterceptor)findBeanInUIF2BeanFactory("cardInterceptor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.ic.general.action.GeneralSaveAction)context.get("saveAction");
  nc.ui.ic.general.action.GeneralSaveAction bean = new nc.ui.ic.general.action.GeneralSaveAction();
  context.put("saveAction",bean);
  bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setEditor((nc.ui.uif2.editor.IEditor)findBeanInUIF2BeanFactory("card"));
  bean.setActionName("WRITE");
  bean.setValidationService((nc.bs.uif2.validation.IValidationService)findBeanInUIF2BeanFactory("validateService"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralDeleteAction getDeleteAction(){
 if(context.get("deleteAction")!=null)
 return (nc.ui.ic.general.action.GeneralDeleteAction)context.get("deleteAction");
  nc.ui.ic.general.action.GeneralDeleteAction bean = new nc.ui.ic.general.action.GeneralDeleteAction();
  context.put("deleteAction",bean);
  bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setEditor((nc.ui.uif2.editor.IEditor)findBeanInUIF2BeanFactory("card"));
  bean.setActionName("DELETE");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralCopyAction getCopyAction(){
 if(context.get("copyAction")!=null)
 return (nc.ui.ic.general.action.GeneralCopyAction)context.get("copyAction");
  nc.ui.ic.general.action.GeneralCopyAction bean = new nc.ui.ic.general.action.GeneralCopyAction();
  context.put("copyAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setEditor((nc.ui.pubapp.uif2app.view.BillForm)findBeanInUIF2BeanFactory("card"));
  bean.setInterceptor((nc.ui.uif2.actions.ActionInterceptor)findBeanInUIF2BeanFactory("cardInterceptor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralCancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.ic.general.action.GeneralCancelAction)context.get("cancelAction");
  nc.ui.ic.general.action.GeneralCancelAction bean = new nc.ui.ic.general.action.GeneralCancelAction();
  context.put("cancelAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralLocationMngAction getLocationMngAction(){
 if(context.get("locationMngAction")!=null)
 return (nc.ui.ic.general.action.GeneralLocationMngAction)context.get("locationMngAction");
  nc.ui.ic.general.action.GeneralLocationMngAction bean = new nc.ui.ic.general.action.GeneralLocationMngAction();
  context.put("locationMngAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralSerialMngAction getSerialMngAction(){
 if(context.get("serialMngAction")!=null)
 return (nc.ui.ic.general.action.GeneralSerialMngAction)context.get("serialMngAction");
  nc.ui.ic.general.action.GeneralSerialMngAction bean = new nc.ui.ic.general.action.GeneralSerialMngAction();
  context.put("serialMngAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralLocationAndSerialMngAction getLocationAndSerialMngAction(){
 if(context.get("locationAndSerialMngAction")!=null)
 return (nc.ui.ic.general.action.GeneralLocationAndSerialMngAction)context.get("locationAndSerialMngAction");
  nc.ui.ic.general.action.GeneralLocationAndSerialMngAction bean = new nc.ui.ic.general.action.GeneralLocationAndSerialMngAction();
  context.put("locationAndSerialMngAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getSignActionMenu(){
 if(context.get("signActionMenu")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("signActionMenu");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("signActionMenu",bean);
  bean.setCode("SignMenu");
  bean.setActions(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getSignAction());  list.add(getCancelSignAction());  return list;}

public nc.ui.ic.general.action.GeneralSignAction getSignAction(){
 if(context.get("signAction")!=null)
 return (nc.ui.ic.general.action.GeneralSignAction)context.get("signAction");
  nc.ui.ic.general.action.GeneralSignAction bean = new nc.ui.ic.general.action.GeneralSignAction();
  context.put("signAction",bean);
  bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setActionName("SIGN");
  bean.setEditor((nc.ui.uif2.editor.IEditor)findBeanInUIF2BeanFactory("card"));
  bean.setBillForm((nc.ui.ic.pub.view.ICBizBillForm)findBeanInUIF2BeanFactory("card"));
  bean.setListView((nc.ui.ic.pub.view.ICBizBillListView)findBeanInUIF2BeanFactory("list"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralCancelSignAction getCancelSignAction(){
 if(context.get("cancelSignAction")!=null)
 return (nc.ui.ic.general.action.GeneralCancelSignAction)context.get("cancelSignAction");
  nc.ui.ic.general.action.GeneralCancelSignAction bean = new nc.ui.ic.general.action.GeneralCancelSignAction();
  context.put("cancelSignAction",bean);
  bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setActionName("CANCELSIGN");
  bean.setEditor((nc.ui.uif2.editor.IEditor)findBeanInUIF2BeanFactory("card"));
  bean.setBillForm((nc.ui.ic.pub.view.ICBizBillForm)findBeanInUIF2BeanFactory("card"));
  bean.setListView((nc.ui.ic.pub.view.ICBizBillListView)findBeanInUIF2BeanFactory("list"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.ic.general.action.GeneralQueryAction)context.get("queryAction");
  nc.ui.ic.general.action.GeneralQueryAction bean = new nc.ui.ic.general.action.GeneralQueryAction();
  context.put("queryAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractUIAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setDataManager((nc.ui.pubapp.uif2app.query2.model.IModelDataManager)findBeanInUIF2BeanFactory("modelDataManager"));
  bean.setQryCondDLGInitializer((nc.ui.pubapp.uif2app.query2.IQueryConditionDLGInitializer)findBeanInUIF2BeanFactory("qryDLGInitializer"));
  bean.setShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent)findBeanInUIF2BeanFactory("list"));
  bean.setTemplateContainer((nc.ui.uif2.editor.QueryTemplateContainer)findBeanInUIF2BeanFactory("queryTemplateContainer"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.SeparatorAction getSeparatorAction(){
 if(context.get("separatorAction")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("separatorAction");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("separatorAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.ic.general.action.GeneralRefreshAction)context.get("refreshAction");
  nc.ui.ic.general.action.GeneralRefreshAction bean = new nc.ui.ic.general.action.GeneralRefreshAction();
  context.put("refreshAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractUIAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setDataManager((nc.ui.pubapp.uif2app.query2.model.IModelDataManager)findBeanInUIF2BeanFactory("modelDataManager"));
  bean.setInterceptor((nc.ui.uif2.actions.ActionInterceptor)findBeanInUIF2BeanFactory("listInterceptor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralRefreshCardAction getRefreshCardAction(){
 if(context.get("refreshCardAction")!=null)
 return (nc.ui.ic.general.action.GeneralRefreshCardAction)context.get("refreshCardAction");
  nc.ui.ic.general.action.GeneralRefreshCardAction bean = new nc.ui.ic.general.action.GeneralRefreshCardAction();
  context.put("refreshCardAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractAppModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralBarcodeAction getBarcodeAction(){
 if(context.get("barcodeAction")!=null)
 return (nc.ui.ic.general.action.GeneralBarcodeAction)context.get("barcodeAction");
  nc.ui.ic.general.action.GeneralBarcodeAction bean = new nc.ui.ic.general.action.GeneralBarcodeAction();
  context.put("barcodeAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralBarCodeOpenAction getBarcodeOpenAction(){
 if(context.get("barcodeOpenAction")!=null)
 return (nc.ui.ic.general.action.GeneralBarCodeOpenAction)context.get("barcodeOpenAction");
  nc.ui.ic.general.action.GeneralBarCodeOpenAction bean = new nc.ui.ic.general.action.GeneralBarCodeOpenAction();
  context.put("barcodeOpenAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralBarCodeCloseAction getBarcodeCloseAction(){
 if(context.get("barcodeCloseAction")!=null)
 return (nc.ui.ic.general.action.GeneralBarCodeCloseAction)context.get("barcodeCloseAction");
  nc.ui.ic.general.action.GeneralBarCodeCloseAction bean = new nc.ui.ic.general.action.GeneralBarCodeCloseAction();
  context.put("barcodeCloseAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralAttachmentAction getAttachMentMngAction(){
 if(context.get("attachMentMngAction")!=null)
 return (nc.ui.ic.general.action.GeneralAttachmentAction)context.get("attachMentMngAction");
  nc.ui.ic.general.action.GeneralAttachmentAction bean = new nc.ui.ic.general.action.GeneralAttachmentAction();
  context.put("attachMentMngAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractUIAppModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralFetchAutoAction getFetchAutoAction(){
 if(context.get("fetchAutoAction")!=null)
 return (nc.ui.ic.general.action.GeneralFetchAutoAction)context.get("fetchAutoAction");
  nc.ui.ic.general.action.GeneralFetchAutoAction bean = new nc.ui.ic.general.action.GeneralFetchAutoAction();
  context.put("fetchAutoAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setInterceptor((nc.ui.uif2.actions.ActionInterceptor)findBeanInUIF2BeanFactory("cacheLockInterceptor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.PickAutoAction getPickAutoAction(){
 if(context.get("pickAutoAction")!=null)
 return (nc.ui.ic.general.action.PickAutoAction)context.get("pickAutoAction");
  nc.ui.ic.general.action.PickAutoAction bean = new nc.ui.ic.general.action.PickAutoAction();
  context.put("pickAutoAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setInterceptor((nc.ui.uif2.actions.ActionInterceptor)findBeanInUIF2BeanFactory("cacheLockInterceptor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.ManufactureDatePickAutoAction getManufacturedatepickautoaction(){
 if(context.get("manufacturedatepickautoaction")!=null)
 return (nc.ui.ic.general.action.ManufactureDatePickAutoAction)context.get("manufacturedatepickautoaction");
  nc.ui.ic.general.action.ManufactureDatePickAutoAction bean = new nc.ui.ic.general.action.ManufactureDatePickAutoAction();
  context.put("manufacturedatepickautoaction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralMDPreviewAction getTemplatePreviewAction(){
 if(context.get("templatePreviewAction")!=null)
 return (nc.ui.ic.general.action.GeneralMDPreviewAction)context.get("templatePreviewAction");
  nc.ui.ic.general.action.GeneralMDPreviewAction bean = new nc.ui.ic.general.action.GeneralMDPreviewAction();
  context.put("templatePreviewAction",bean);
  bean.setPreview(true);
  bean.setModel((nc.ui.uif2.model.AbstractUIAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setBeforePrintDataProcess((nc.ui.pubapp.uif2app.actions.BaseMetaDataBasedPrintAction.IBeforePrintDataProcess)findBeanInUIF2BeanFactory("printProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralMDPrintAction getTemplatePrintAction(){
 if(context.get("templatePrintAction")!=null)
 return (nc.ui.ic.general.action.GeneralMDPrintAction)context.get("templatePrintAction");
  nc.ui.ic.general.action.GeneralMDPrintAction bean = new nc.ui.ic.general.action.GeneralMDPrintAction();
  context.put("templatePrintAction",bean);
  bean.setPreview(false);
  bean.setModel((nc.ui.uif2.model.AbstractUIAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setBeforePrintDataProcess((nc.ui.pubapp.uif2app.actions.BaseMetaDataBasedPrintAction.IBeforePrintDataProcess)findBeanInUIF2BeanFactory("printProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralLinkQueryAction getLinkQryAction(){
 if(context.get("linkQryAction")!=null)
 return (nc.ui.ic.general.action.GeneralLinkQueryAction)context.get("linkQryAction");
  nc.ui.ic.general.action.GeneralLinkQueryAction bean = new nc.ui.ic.general.action.GeneralLinkQueryAction();
  context.put("linkQryAction",bean);
  bean.setModel((nc.ui.ic.general.model.ICGenBizModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralOnhandnumQueryAction getOnhandnumQryAction(){
 if(context.get("onhandnumQryAction")!=null)
 return (nc.ui.ic.general.action.GeneralOnhandnumQueryAction)context.get("onhandnumQryAction");
  nc.ui.ic.general.action.GeneralOnhandnumQueryAction bean = new nc.ui.ic.general.action.GeneralOnhandnumQueryAction();
  context.put("onhandnumQryAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralSetPieceAction getSetPieceAtion(){
 if(context.get("setPieceAtion")!=null)
 return (nc.ui.ic.general.action.GeneralSetPieceAction)context.get("setPieceAtion");
  nc.ui.ic.general.action.GeneralSetPieceAction bean = new nc.ui.ic.general.action.GeneralSetPieceAction();
  context.put("setPieceAtion",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralExcelExportAction getExportExcelAction(){
 if(context.get("exportExcelAction")!=null)
 return (nc.ui.ic.general.action.GeneralExcelExportAction)context.get("exportExcelAction");
  nc.ui.ic.general.action.GeneralExcelExportAction bean = new nc.ui.ic.general.action.GeneralExcelExportAction();
  context.put("exportExcelAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractUIAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setShowProgress(true);
  bean.setImportableEditor(getImportableEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.view.ICImportablePanel getImportableEditor(){
 if(context.get("importableEditor")!=null)
 return (nc.ui.ic.general.view.ICImportablePanel)context.get("importableEditor");
  nc.ui.ic.general.view.ICImportablePanel bean = new nc.ui.ic.general.view.ICImportablePanel();
  context.put("importableEditor",bean);
  bean.setAddAction(getAddAction());
  bean.setCancelAction(getCancelAction());
  bean.setBillcardPanelEditor((nc.ui.uif2.editor.IBillCardPanelEditor)findBeanInUIF2BeanFactory("card"));
  bean.setSaveAction(getSaveAction());
  bean.setAppModel((nc.ui.uif2.model.AbstractUIAppModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.OutputAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.OutputAction)context.get("outputAction");
  nc.ui.pubapp.uif2app.actions.OutputAction bean = new nc.ui.pubapp.uif2app.actions.OutputAction();
  context.put("outputAction",bean);
  bean.setModel((nc.ui.uif2.model.AbstractUIAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setParent((java.awt.Container)findBeanInUIF2BeanFactory("card"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralXmlExportAction getExprotXmlAction(){
 if(context.get("exprotXmlAction")!=null)
 return (nc.ui.ic.general.action.GeneralXmlExportAction)context.get("exprotXmlAction");
  nc.ui.ic.general.action.GeneralXmlExportAction bean = new nc.ui.ic.general.action.GeneralXmlExportAction();
  context.put("exprotXmlAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralImportMainBCAction getImportMainBCAction(){
 if(context.get("importMainBCAction")!=null)
 return (nc.ui.ic.general.action.GeneralImportMainBCAction)context.get("importMainBCAction");
  nc.ui.ic.general.action.GeneralImportMainBCAction bean = new nc.ui.ic.general.action.GeneralImportMainBCAction();
  context.put("importMainBCAction",bean);
  bean.setModel((nc.ui.ic.general.model.ICGenBizModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralImportSrcBCAction getImportSrcBCAction(){
 if(context.get("importSrcBCAction")!=null)
 return (nc.ui.ic.general.action.GeneralImportSrcBCAction)context.get("importSrcBCAction");
  nc.ui.ic.general.action.GeneralImportSrcBCAction bean = new nc.ui.ic.general.action.GeneralImportSrcBCAction();
  context.put("importSrcBCAction",bean);
  bean.setModel((nc.ui.ic.pub.model.ICBizModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralInOutMergerShowAction getMergerShowAction(){
 if(context.get("mergerShowAction")!=null)
 return (nc.ui.ic.general.action.GeneralInOutMergerShowAction)context.get("mergerShowAction");
  nc.ui.ic.general.action.GeneralInOutMergerShowAction bean = new nc.ui.ic.general.action.GeneralInOutMergerShowAction();
  context.put("mergerShowAction",bean);
  bean.setModel((nc.ui.ic.pub.model.ICBizModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditor((nc.ui.ic.pub.view.ICBizBillForm)findBeanInUIF2BeanFactory("card"));
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setCardPanelInit((nc.ui.ic.general.deal.GeneralCardPanelInit)findBeanInUIF2BeanFactory("cardPanelInit"));
  bean.setTitle(getI18nFB_e2d522());
  bean.setPtMaterialNameItemKey("name");
  bean.setMaterialPKColumn("cmaterialvid");
  bean.setIsMaterialClassificationl(true);
  bean.setNodeKey("mergershow");
  bean.setNumKey("nnum");
  bean.setGroupRightItemKeys(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_e2d522(){
 if(context.get("nc.ui.uif2.I18nFB#e2d522")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#e2d522");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#e2d522",bean);  bean.setResDir("4008014_0");
  bean.setResId("04008014-0008");
  bean.setDefaultValue("合并显示");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#e2d522",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList6(){  List list = new ArrayList();  list.add("cmaterialvid");  list.add("cmaterialvid.name");  list.add("cmaterialvid.materialspec");  list.add("cmaterialvid.materialtype");  return list;}

public nc.ui.ic.general.action.GeneralPrintLocAction getPrintLocAction(){
 if(context.get("printLocAction")!=null)
 return (nc.ui.ic.general.action.GeneralPrintLocAction)context.get("printLocAction");
  nc.ui.ic.general.action.GeneralPrintLocAction bean = new nc.ui.ic.general.action.GeneralPrintLocAction();
  context.put("printLocAction",bean);
  bean.setCode("btnPrintLoc");
  bean.setModel((nc.ui.uif2.model.AbstractUIAppModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setNodeKey("prloc");
  bean.setBeforePrintDataProcess((nc.ui.pubapp.uif2app.actions.BaseMetaDataBasedPrintAction.IBeforePrintDataProcess)findBeanInUIF2BeanFactory("printProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralPrintCertBillAction getPrintQAAction(){
 if(context.get("printQAAction")!=null)
 return (nc.ui.ic.general.action.GeneralPrintCertBillAction)context.get("printQAAction");
  nc.ui.ic.general.action.GeneralPrintCertBillAction bean = new nc.ui.ic.general.action.GeneralPrintCertBillAction();
  context.put("printQAAction",bean);
  bean.setModel((nc.ui.uif2.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralSumPrintAction getSumPrintAction(){
 if(context.get("sumPrintAction")!=null)
 return (nc.ui.ic.general.action.GeneralSumPrintAction)context.get("sumPrintAction");
  nc.ui.ic.general.action.GeneralSumPrintAction bean = new nc.ui.ic.general.action.GeneralSumPrintAction();
  context.put("sumPrintAction",bean);
  bean.setModel((nc.ui.ic.pub.model.ICBizModel)findBeanInUIF2BeanFactory("icBizModel"));
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setPrintProcessor((nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess)findBeanInUIF2BeanFactory("printProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.pub.action.ICMenuAction getMaintainMenu(){
 if(context.get("maintainMenu")!=null)
 return (nc.ui.ic.pub.action.ICMenuAction)context.get("maintainMenu");
  nc.ui.ic.pub.action.ICMenuAction bean = new nc.ui.ic.pub.action.ICMenuAction();
  context.put("maintainMenu",bean);
  bean.setCode("MaintainMenu");
  bean.setName(getI18nFB_16ce83());
  bean.setTooltip(getI18nFB_14ffe9b());
  bean.setActions(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_16ce83(){
 if(context.get("nc.ui.uif2.I18nFB#16ce83")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#16ce83");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#16ce83",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0746");
  bean.setDefaultValue("维护");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#16ce83",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private java.lang.String getI18nFB_14ffe9b(){
 if(context.get("nc.ui.uif2.I18nFB#14ffe9b")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#14ffe9b");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#14ffe9b",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0783");
  bean.setDefaultValue("维护(Alt+7)");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#14ffe9b",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList7(){  List list = new ArrayList();  list.add(getLocationMngAction());  list.add(getSerialMngAction());  list.add(getLocationAndSerialMngAction());  return list;}

public nc.funcnode.ui.action.MenuAction getAssistantFunctionBrowseAction_IN(){
 if(context.get("assistantFunctionBrowseAction_IN")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("assistantFunctionBrowseAction_IN");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("assistantFunctionBrowseAction_IN",bean);
  bean.setCode("NastMngBrowseAction");
  bean.setName(getI18nFB_d47afe());
  bean.setActions(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_d47afe(){
 if(context.get("nc.ui.uif2.I18nFB#d47afe")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#d47afe");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#d47afe",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0743");
  bean.setDefaultValue("关联功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#d47afe",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList8(){  List list = new ArrayList();  list.add(getDisplayOrhideAction());  list.add(getAttachMentMngAction());  return list;}

public nc.funcnode.ui.action.MenuAction getAssistantFunctionEditAction_IN(){
 if(context.get("assistantFunctionEditAction_IN")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("assistantFunctionEditAction_IN");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("assistantFunctionEditAction_IN",bean);
  bean.setCode("NastMngEditAction");
  bean.setName(getI18nFB_1d6c774());
  bean.setActions(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1d6c774(){
 if(context.get("nc.ui.uif2.I18nFB#1d6c774")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1d6c774");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1d6c774",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0743");
  bean.setDefaultValue("关联功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1d6c774",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList9(){  List list = new ArrayList();  list.add(getDisplayOrhideAction());  return list;}

public nc.funcnode.ui.action.MenuAction getAssistantFunctionBrowseAction_OUT(){
 if(context.get("assistantFunctionBrowseAction_OUT")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("assistantFunctionBrowseAction_OUT");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("assistantFunctionBrowseAction_OUT",bean);
  bean.setCode("NastMngBrowseAction");
  bean.setName(getI18nFB_1c0cc16());
  bean.setActions(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1c0cc16(){
 if(context.get("nc.ui.uif2.I18nFB#1c0cc16")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1c0cc16");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1c0cc16",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0743");
  bean.setDefaultValue("关联功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1c0cc16",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList10(){  List list = new ArrayList();  list.add(getDisplayOrhideAction());  list.add(getAttachMentMngAction());  return list;}

public nc.funcnode.ui.action.MenuAction getAssistantFunctionEditAction_OUT(){
 if(context.get("assistantFunctionEditAction_OUT")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("assistantFunctionEditAction_OUT");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("assistantFunctionEditAction_OUT",bean);
  bean.setCode("NastMngEditAction");
  bean.setName(getI18nFB_96c58f());
  bean.setActions(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_96c58f(){
 if(context.get("nc.ui.uif2.I18nFB#96c58f")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#96c58f");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#96c58f",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0743");
  bean.setDefaultValue("关联功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#96c58f",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList11(){  List list = new ArrayList();  list.add(getDisplayOrhideAction());  list.add(getPickAutoAction());  list.add(getManufacturedatepickautoaction());  return list;}

public nc.funcnode.ui.action.MenuAction getLinkQryBrowseGroupAction(){
 if(context.get("linkQryBrowseGroupAction")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("linkQryBrowseGroupAction");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("linkQryBrowseGroupAction",bean);
  bean.setCode("linkQryAction");
  bean.setName(getI18nFB_845069());
  bean.setActions(getManagedList12());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_845069(){
 if(context.get("nc.ui.uif2.I18nFB#845069")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#845069");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#845069",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0742");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#845069",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList12(){  List list = new ArrayList();  list.add(getLinkQryAction());  list.add(getSeparatorAction_bb5421());  list.add(getSetPieceAtion());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_bb5421(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#bb5421")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#bb5421");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#bb5421",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getLinkQryEditGroupAction(){
 if(context.get("linkQryEditGroupAction")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("linkQryEditGroupAction");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("linkQryEditGroupAction",bean);
  bean.setCode("linkQryAction");
  bean.setName(getI18nFB_904d29());
  bean.setActions(getManagedList13());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_904d29(){
 if(context.get("nc.ui.uif2.I18nFB#904d29")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#904d29");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#904d29",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0742");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#904d29",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList13(){  List list = new ArrayList();  list.add(getSetPieceAtion());  return list;}

public nc.ui.ic.pub.action.ICMenuAction getImportExportAction(){
 if(context.get("importExportAction")!=null)
 return (nc.ui.ic.pub.action.ICMenuAction)context.get("importExportAction");
  nc.ui.ic.pub.action.ICMenuAction bean = new nc.ui.ic.pub.action.ICMenuAction();
  context.put("importExportAction",bean);
  bean.setCode("importExportAction");
  bean.setName(getI18nFB_1b9ca51());
  bean.setTooltip(getI18nFB_91bae3());
  bean.setActions(getManagedList14());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1b9ca51(){
 if(context.get("nc.ui.uif2.I18nFB#1b9ca51")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1b9ca51");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1b9ca51",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0749");
  bean.setDefaultValue("导入");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1b9ca51",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private java.lang.String getI18nFB_91bae3(){
 if(context.get("nc.ui.uif2.I18nFB#91bae3")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#91bae3");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#91bae3",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0784");
  bean.setDefaultValue("导入(Shift+F2)");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#91bae3",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList14(){  List list = new ArrayList();  list.add(getImportMainBCAction());  list.add(getImportSrcBCAction());  return list;}

public nc.funcnode.ui.action.GroupAction getPrintMngAction(){
 if(context.get("printMngAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printMngAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printMngAction",bean);
  bean.setCode("printMngAction");
  bean.setActions(getManagedList15());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList15(){  List list = new ArrayList();  list.add(getTemplatePrintAction());  list.add(getTemplatePreviewAction());  list.add(getOutputAction());  list.add(getSeparatorAction_1848e66());  list.add(getMergerShowAction());  list.add(getPrintLocAction());  list.add(getDirPrintBarcodeAction());  list.add(getPrintBarcodeAction());  list.add(getPrintCountQueryAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1848e66(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1848e66")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1848e66");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1848e66",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.pub.action.QueryOnhandAction getDisplayOrhideAction(){
 if(context.get("displayOrhideAction")!=null)
 return (nc.ui.ic.pub.action.QueryOnhandAction)context.get("displayOrhideAction");
  nc.ui.ic.pub.action.QueryOnhandAction bean = new nc.ui.ic.pub.action.QueryOnhandAction();
  context.put("displayOrhideAction",bean);
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralGenerateEquipCardAction getGenerateEquipCardAction(){
 if(context.get("generateEquipCardAction")!=null)
 return (nc.ui.ic.general.action.GeneralGenerateEquipCardAction)context.get("generateEquipCardAction");
  nc.ui.ic.general.action.GeneralGenerateEquipCardAction bean = new nc.ui.ic.general.action.GeneralGenerateEquipCardAction();
  context.put("generateEquipCardAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralCancelGenerateEquipCardAction getCancelGenerateEquipCardAction(){
 if(context.get("cancelGenerateEquipCardAction")!=null)
 return (nc.ui.ic.general.action.GeneralCancelGenerateEquipCardAction)context.get("cancelGenerateEquipCardAction");
  nc.ui.ic.general.action.GeneralCancelGenerateEquipCardAction bean = new nc.ui.ic.general.action.GeneralCancelGenerateEquipCardAction();
  context.put("cancelGenerateEquipCardAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setModel((nc.ui.pubapp.uif2app.model.BillManageModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralBodyAddLineAction getBodyAddLineAction(){
 if(context.get("bodyAddLineAction")!=null)
 return (nc.ui.ic.general.action.GeneralBodyAddLineAction)context.get("bodyAddLineAction");
  nc.ui.ic.general.action.GeneralBodyAddLineAction bean = new nc.ui.ic.general.action.GeneralBodyAddLineAction();
  context.put("bodyAddLineAction",bean);
  bean.setIcBizView((nc.ui.ic.general.view.ICBizView)findBeanInUIF2BeanFactory("card"));
  bean.setContext((nc.ui.ic.pub.env.ICUIContext)findBeanInUIF2BeanFactory("icUIContext"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.ICBodyDelLineAction getBodyDelLineAction(){
 if(context.get("bodyDelLineAction")!=null)
 return (nc.ui.ic.general.action.ICBodyDelLineAction)context.get("bodyDelLineAction");
  nc.ui.ic.general.action.ICBodyDelLineAction bean = new nc.ui.ic.general.action.ICBodyDelLineAction();
  context.put("bodyDelLineAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralBodyInsertLineAction getBodyInsertLineAction(){
 if(context.get("bodyInsertLineAction")!=null)
 return (nc.ui.ic.general.action.GeneralBodyInsertLineAction)context.get("bodyInsertLineAction");
  nc.ui.ic.general.action.GeneralBodyInsertLineAction bean = new nc.ui.ic.general.action.GeneralBodyInsertLineAction();
  context.put("bodyInsertLineAction",bean);
  bean.setIcBizView((nc.ui.ic.general.view.ICBizView)findBeanInUIF2BeanFactory("card"));
  bean.setContext((nc.ui.ic.pub.env.ICUIContext)findBeanInUIF2BeanFactory("icUIContext"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction(){
 if(context.get("bodyCopyLineAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("bodyCopyLineAction");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("bodyCopyLineAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralBodyPasteLineAction getBodyPasteLineAction(){
 if(context.get("bodyPasteLineAction")!=null)
 return (nc.ui.ic.general.action.GeneralBodyPasteLineAction)context.get("bodyPasteLineAction");
  nc.ui.ic.general.action.GeneralBodyPasteLineAction bean = new nc.ui.ic.general.action.GeneralBodyPasteLineAction();
  context.put("bodyPasteLineAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction getBodyPasteToTailAction(){
 if(context.get("bodyPasteToTailAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction)context.get("bodyPasteToTailAction");
  nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction bean = new nc.ui.pubapp.uif2app.actions.BodyPasteToTailAction();
  context.put("bodyPasteToTailAction",bean);
  bean.setClearItems(getManagedList16());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList16(){  List list = new ArrayList();  list.add("cgeneralbid");  list.add("ts");  return list;}

public nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction getRearrangeRowNoBodyLineAction(){
 if(context.get("rearrangeRowNoBodyLineAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction)context.get("rearrangeRowNoBodyLineAction");
  nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction bean = new nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction();
  context.put("rearrangeRowNoBodyLineAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction(){
 if(context.get("bodyLineEditAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("bodyLineEditAction");
  nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
  context.put("bodyLineEditAction",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.NoDirectPrintBarCodeAction getPrintBarcodeAction(){
 if(context.get("printBarcodeAction")!=null)
 return (nc.ui.ic.general.action.NoDirectPrintBarCodeAction)context.get("printBarcodeAction");
  nc.ui.ic.general.action.NoDirectPrintBarCodeAction bean = new nc.ui.ic.general.action.NoDirectPrintBarCodeAction();
  context.put("printBarcodeAction",bean);
  bean.setEditor((nc.ui.uif2.editor.IEditor)findBeanInUIF2BeanFactory("card"));
  bean.setList((nc.ui.pubapp.uif2app.view.ShowUpableBillListView)findBeanInUIF2BeanFactory("list"));
  bean.setModel((nc.ui.ic.general.model.ICGenBizModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.DirectPrintBarCodeAction getDirPrintBarcodeAction(){
 if(context.get("dirPrintBarcodeAction")!=null)
 return (nc.ui.ic.general.action.DirectPrintBarCodeAction)context.get("dirPrintBarcodeAction");
  nc.ui.ic.general.action.DirectPrintBarCodeAction bean = new nc.ui.ic.general.action.DirectPrintBarCodeAction();
  context.put("dirPrintBarcodeAction",bean);
  bean.setEditor((nc.ui.uif2.editor.IEditor)findBeanInUIF2BeanFactory("card"));
  bean.setList((nc.ui.pubapp.uif2app.view.ShowUpableBillListView)findBeanInUIF2BeanFactory("list"));
  bean.setModel((nc.ui.ic.general.model.ICGenBizModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.pub.action.ICPrintCountQueryAction getPrintCountQueryAction(){
 if(context.get("printCountQueryAction")!=null)
 return (nc.ui.ic.pub.action.ICPrintCountQueryAction)context.get("printCountQueryAction");
  nc.ui.ic.pub.action.ICPrintCountQueryAction bean = new nc.ui.ic.pub.action.ICPrintCountQueryAction();
  context.put("printCountQueryAction",bean);
  bean.setBilldateFieldName("");
  bean.setModel((nc.ui.uif2.model.AbstractAppModel)findBeanInUIF2BeanFactory("icBizModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
