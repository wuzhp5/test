package nc.ui.pu.m21.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class order_config extends AbstractJavaBeanDefinition{
	private Map<String, Object> context = new HashMap();
public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add(getActionsOfList());  list.add(getActionsOfCard());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getListView());  context.put("actionsOfList",bean);
  bean.setModel(getManageAppModel());
  bean.setActions(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getAddMenu());  list.add(getEditAction());  list.add(getReviseAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getRefreshAction());  list.add(getSeparatorAction());  list.add(getCoordinationRawAction());  list.add(getSeparatorAction());  list.add(getSendApproveMenuAction());  list.add(getAuditMenuAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction());  list.add(getLinkBillMenuAction());  list.add(getSeparatorAction());  list.add(getRelateFunMenuAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
 if(context.get("actionsOfCard")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getBillFormEditor());  context.put("actionsOfCard",bean);
  bean.setModel(getManageAppModel());
  bean.setActions(getManagedList2());
  bean.setEditActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList2(){  List list = new ArrayList();  list.add(getAddMenu());  list.add(getEditAction());  list.add(getReviseAction());  list.add(getDeleteAction());  list.add(getCopyAction());  list.add(getSeparatorAction());  list.add(getQueryAction());  list.add(getCardRefreshAction());  list.add(getSeparatorAction());  list.add(getSendApproveMenuAction());  list.add(getAuditMenuAction());  list.add(getAssistMenuAction());  list.add(getSeparatorAction());  list.add(getLinkBillMenuAction());  list.add(getSeparatorAction());  list.add(getRelateFunMenuAction());  list.add(getSeparatorAction());  list.add(getPrintMenuAction());  list.add(getSeparatorAction());  list.add(getIMAction());  return list;}

private List getManagedList3(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSaveApproveAction());  list.add(getSeparatorAction());  list.add(getCancelAction());  list.add(getSeparatorAction());  list.add(getAssistMenuActionForCard());  list.add(getSeparatorAction());  list.add(getLinkBillMenuActionForCard());  list.add(getSeparatorAction());  list.add(getRelateFunMenuActionForCard());  return list;}

public nc.ui.pu.m21.action.interceptor.OrderBillFormInterceptor getFormInterceptor(){
 if(context.get("formInterceptor")!=null)
 return (nc.ui.pu.m21.action.interceptor.OrderBillFormInterceptor)context.get("formInterceptor");
  nc.ui.pu.m21.action.interceptor.OrderBillFormInterceptor bean = new nc.ui.pu.m21.action.interceptor.OrderBillFormInterceptor();
  context.put("formInterceptor",bean);
  bean.setShowUpComponent(getBillFormEditor());
  bean.setEditor(getBillFormEditor());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.dest.TransferBillViewProcessor getTransferBillViewProcessor(){
 if(context.get("transferBillViewProcessor")!=null)
 return (nc.ui.pubapp.billref.dest.TransferBillViewProcessor)context.get("transferBillViewProcessor");
  nc.ui.pubapp.billref.dest.TransferBillViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferBillViewProcessor();
  context.put("transferBillViewProcessor",bean);
  bean.setList(getListView());
  bean.setActionContainer(getContainer());
  bean.setBillForm(getBillFormEditor());
  bean.setTransferLogic(getDefaultBillDataLogic_22c952());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.billref.dest.DefaultBillDataLogic getDefaultBillDataLogic_22c952(){
 if(context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#22c952")!=null)
 return (nc.ui.pubapp.billref.dest.DefaultBillDataLogic)context.get("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#22c952");
  nc.ui.pubapp.billref.dest.DefaultBillDataLogic bean = new nc.ui.pubapp.billref.dest.DefaultBillDataLogic();
  context.put("nc.ui.pubapp.billref.dest.DefaultBillDataLogic#22c952",bean);
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getListInterceptor(){
 if(context.get("listInterceptor")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("listInterceptor");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("listInterceptor",bean);
  bean.setShowUpComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.billref.dest.OrderTransferListProcessor getOrderTransferListProcessor(){
 if(context.get("orderTransferListProcessor")!=null)
 return (nc.ui.pu.m21.billref.dest.OrderTransferListProcessor)context.get("orderTransferListProcessor");
  nc.ui.pu.m21.billref.dest.OrderTransferListProcessor bean = new nc.ui.pu.m21.billref.dest.OrderTransferListProcessor();
  context.put("orderTransferListProcessor",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewProcessor(){
 if(context.get("transferViewProcessor")!=null)
 return (nc.ui.pubapp.billref.dest.TransferViewProcessor)context.get("transferViewProcessor");
  nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
  context.put("transferViewProcessor",bean);
  bean.setList(getListView());
  bean.setActionContainer(getActionsOfList());
  bean.setCardActionContainer(getActionsOfCard());
  bean.setSaveAction(getSaveAction());
  bean.setCommitAction(getSendApproveAction());
  bean.setCancelAction(getCancelAction());
  bean.setBillForm(getBillFormEditor());
  bean.setQueryAreaShell(getQueryArea());
  bean.setListProcessor(getOrderTransferListProcessor());
  bean.setQueryInfoToolbarPanel(getQueryInfo());
  bean.setTransferLogic(getOrderTransferBillDataLogic_2708c3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic getOrderTransferBillDataLogic_2708c3(){
 if(context.get("nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic#2708c3")!=null)
 return (nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic)context.get("nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic#2708c3");
  nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic bean = new nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic();
  context.put("nc.ui.pu.m21.billref.dest.OrderTransferBillDataLogic#2708c3",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.processor.CopyActionProcessor getCopyActionProcessor(){
 if(context.get("copyActionProcessor")!=null)
 return (nc.ui.pu.m21.action.processor.CopyActionProcessor)context.get("copyActionProcessor");
  nc.ui.pu.m21.action.processor.CopyActionProcessor bean = new nc.ui.pu.m21.action.processor.CopyActionProcessor();
  context.put("copyActionProcessor",bean);
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

public nc.ui.pu.m21.action.AddManualAction getAddManualAction(){
 if(context.get("addManualAction")!=null)
 return (nc.ui.pu.m21.action.AddManualAction)context.get("addManualAction");
  nc.ui.pu.m21.action.AddManualAction bean = new nc.ui.pu.m21.action.AddManualAction();
  context.put("addManualAction",bean);
  bean.setSourceBillType("MANUAL");
  bean.setSourceBillName(getI18nFB_11369f6());
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setInterceptor(getFormInterceptor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_11369f6(){
 if(context.get("nc.ui.uif2.I18nFB#11369f6")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#11369f6");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#11369f6",bean);  bean.setResDir("common");
  bean.setResId("14004000-0000");
  bean.setDefaultValue("����");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#11369f6",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m21.action.AddFromSourceAction getAddFrom20Action(){
 if(context.get("addFrom20Action")!=null)
 return (nc.ui.pu.m21.action.AddFromSourceAction)context.get("addFrom20Action");
  nc.ui.pu.m21.action.AddFromSourceAction bean = new nc.ui.pu.m21.action.AddFromSourceAction();
  context.put("addFrom20Action",bean);
  bean.setSourceBillType("20");
  bean.setSourceBillName(getI18nFB_42733d());
  bean.setFlowBillType(false);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_42733d(){
 if(context.get("nc.ui.uif2.I18nFB#42733d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#42733d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#42733d",bean);  bean.setResDir("common");
  bean.setResId("14004000-0001");
  bean.setDefaultValue("�빺��");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#42733d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m21.action.AddFromContratAction getAddFromZ2Action(){
 if(context.get("addFromZ2Action")!=null)
 return (nc.ui.pu.m21.action.AddFromContratAction)context.get("addFromZ2Action");
  nc.ui.pu.m21.action.AddFromContratAction bean = new nc.ui.pu.m21.action.AddFromContratAction();
  context.put("addFromZ2Action",bean);
  bean.setSourceBillType("Z2");
  bean.setSourceBillName(getI18nFB_aa4f3e());
  bean.setFlowBillType(false);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_aa4f3e(){
 if(context.get("nc.ui.uif2.I18nFB#aa4f3e")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#aa4f3e");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#aa4f3e",bean);  bean.setResDir("common");
  bean.setResId("14004000-0004");
  bean.setDefaultValue("�ɹ���ͬ");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#aa4f3e",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m21.action.AddFromSaleOrderAction getAddFrom30Action(){
 if(context.get("addFrom30Action")!=null)
 return (nc.ui.pu.m21.action.AddFromSaleOrderAction)context.get("addFrom30Action");
  nc.ui.pu.m21.action.AddFromSaleOrderAction bean = new nc.ui.pu.m21.action.AddFromSaleOrderAction();
  context.put("addFrom30Action",bean);
  bean.setSourceBillType("30");
  bean.setSourceBillName(getI18nFB_b123f8());
  bean.setFlowBillType(false);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_b123f8(){
 if(context.get("nc.ui.uif2.I18nFB#b123f8")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#b123f8");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#b123f8",bean);  bean.setResDir("common");
  bean.setResId("14004000-0002");
  bean.setDefaultValue("ֱ�����۶���");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#b123f8",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m21.action.AddFromSaleOrderCoopAction getAddFrom30CoopAction(){
 if(context.get("addFrom30CoopAction")!=null)
 return (nc.ui.pu.m21.action.AddFromSaleOrderCoopAction)context.get("addFrom30CoopAction");
  nc.ui.pu.m21.action.AddFromSaleOrderCoopAction bean = new nc.ui.pu.m21.action.AddFromSaleOrderCoopAction();
  context.put("addFrom30CoopAction",bean);
  bean.setSourceBillType("30");
  bean.setSourceBillName(getI18nFB_53b481());
  bean.setFlowBillType(false);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_53b481(){
 if(context.get("nc.ui.uif2.I18nFB#53b481")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#53b481");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#53b481",bean);  bean.setResDir("common");
  bean.setResId("14004000-0003");
  bean.setDefaultValue("Эͬ���۶���");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#53b481",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m21.action.AddFromSourceAction getAddFrom49Action(){
 if(context.get("addFrom49Action")!=null)
 return (nc.ui.pu.m21.action.AddFromSourceAction)context.get("addFrom49Action");
  nc.ui.pu.m21.action.AddFromSourceAction bean = new nc.ui.pu.m21.action.AddFromSourceAction();
  context.put("addFrom49Action",bean);
  bean.setSourceBillType("49");
  bean.setSourceBillName(getI18nFB_1f3f1ad());
  bean.setFlowBillType(false);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1f3f1ad(){
 if(context.get("nc.ui.uif2.I18nFB#1f3f1ad")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1f3f1ad");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1f3f1ad",bean);  bean.setResDir("common");
  bean.setResId("14004000-0005");
  bean.setDefaultValue("�����뵥");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1f3f1ad",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pubapp.uif2app.actions.AddMenuAction getAddMenu(){
 if(context.get("addMenu")!=null)
 return (nc.ui.pubapp.uif2app.actions.AddMenuAction)context.get("addMenu");
  nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
  context.put("addMenu",bean);
  bean.setBillType("21");
  bean.setTooltip(getI18nFB_19a97d2());
  bean.setActions(getManagedList4());
  bean.setModel(getManageAppModel());
  bean.setPfAddInfoLoader(getPfAddInfoLoader());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_19a97d2(){
 if(context.get("nc.ui.uif2.I18nFB#19a97d2")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#19a97d2");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#19a97d2",bean);  bean.setResDir("common");
  bean.setResId("04004000-0001");
  bean.setDefaultValue("����ҵ������(Ctrl+N)");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#19a97d2",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList4(){  List list = new ArrayList();  list.add(getAddManualAction());  list.add(getSeparatorAction());  list.add(getAddFrom20Action());  list.add(getAddFrom30Action());  list.add(getAddFrom30CoopAction());  list.add(getAddFromZ2Action());  list.add(getAddFrom49Action());  return list;}

public nc.ui.pu.m21.view.OrderQueryDLGInitializer getOrderQryDLGInitializer(){
 if(context.get("orderQryDLGInitializer")!=null)
 return (nc.ui.pu.m21.view.OrderQueryDLGInitializer)context.get("orderQryDLGInitializer");
  nc.ui.pu.m21.view.OrderQueryDLGInitializer bean = new nc.ui.pu.m21.view.OrderQueryDLGInitializer();
  context.put("orderQryDLGInitializer",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction getQueryAction(){
 if(context.get("queryAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)context.get("queryAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction();
  context.put("queryAction",bean);
  bean.setDataManager(getModelDataManager());
  bean.setQryCondDLGInitializer(getOrderQryDLGInitializer());
  bean.setModel(getManageAppModel());
  bean.setShowUpComponent(getListView());
  bean.setTemplateContainer(getQueryTemplateContainer());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction getRefreshAction(){
 if(context.get("refreshAction")!=null)
 return (nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction)context.get("refreshAction");
  nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction bean = new nc.ui.pubapp.uif2app.query2.action.DefaultRefreshAction();
  context.put("refreshAction",bean);
  bean.setDataManager(getModelDataManager());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.RefreshSingleAction getCardRefreshAction(){
 if(context.get("cardRefreshAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.RefreshSingleAction)context.get("cardRefreshAction");
  nc.ui.pubapp.uif2app.actions.RefreshSingleAction bean = new nc.ui.pubapp.uif2app.actions.RefreshSingleAction();
  context.put("cardRefreshAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.pub.action.CoordinationRawAction getCoordinationRawAction(){
 if(context.get("coordinationRawAction")!=null)
 return (nc.ui.pu.pub.action.CoordinationRawAction)context.get("coordinationRawAction");
  nc.ui.pu.pub.action.CoordinationRawAction bean = new nc.ui.pu.pub.action.CoordinationRawAction();
  context.put("coordinationRawAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OrderEditAction getEditAction(){
 if(context.get("editAction")!=null)
 return (nc.ui.pu.m21.action.OrderEditAction)context.get("editAction");
  nc.ui.pu.m21.action.OrderEditAction bean = new nc.ui.pu.m21.action.OrderEditAction();
  context.put("editAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setPowercheck(true);
  bean.setPermissioncode("21");
  bean.setInterceptor(getFormInterceptor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OrderReviseInApprovingAction getReviseAction(){
 if(context.get("reviseAction")!=null)
 return (nc.ui.pu.m21.action.OrderReviseInApprovingAction)context.get("reviseAction");
  nc.ui.pu.m21.action.OrderReviseInApprovingAction bean = new nc.ui.pu.m21.action.OrderReviseInApprovingAction();
  context.put("reviseAction",bean);
  bean.setModel(getManageAppModel());
  bean.setInterceptor(getShowUpComponentInterceptor_2fc77a());
  bean.setPowercheck(true);
  bean.setPermissioncode("21");
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor getShowUpComponentInterceptor_2fc77a(){
 if(context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#2fc77a")!=null)
 return (nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor)context.get("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#2fc77a");
  nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor bean = new nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor();
  context.put("nc.ui.pubapp.uif2app.actions.interceptor.ShowUpComponentInterceptor#2fc77a",bean);
  bean.setShowUpComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OrderDeleteAction getDeleteAction(){
 if(context.get("deleteAction")!=null)
 return (nc.ui.pu.m21.action.OrderDeleteAction)context.get("deleteAction");
  nc.ui.pu.m21.action.OrderDeleteAction bean = new nc.ui.pu.m21.action.OrderDeleteAction();
  context.put("deleteAction",bean);
  bean.setSingleBillService(getOrderDeleteService_1699282());
  bean.setModel(getManageAppModel());
  bean.setPowercheck(true);
  bean.setPermissioncode("21");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.service.OrderDeleteService getOrderDeleteService_1699282(){
 if(context.get("nc.ui.pu.m21.service.OrderDeleteService#1699282")!=null)
 return (nc.ui.pu.m21.service.OrderDeleteService)context.get("nc.ui.pu.m21.service.OrderDeleteService#1699282");
  nc.ui.pu.m21.service.OrderDeleteService bean = new nc.ui.pu.m21.service.OrderDeleteService();
  context.put("nc.ui.pu.m21.service.OrderDeleteService#1699282",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OrderCopyAction getCopyAction(){
 if(context.get("copyAction")!=null)
 return (nc.ui.pu.m21.action.OrderCopyAction)context.get("copyAction");
  nc.ui.pu.m21.action.OrderCopyAction bean = new nc.ui.pu.m21.action.OrderCopyAction();
  context.put("copyAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setCopyActionProcessor(getCopyActionProcessor());
  bean.setInterceptor(getFormInterceptor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OrderSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.pu.m21.action.OrderSaveAction)context.get("saveAction");
  nc.ui.pu.m21.action.OrderSaveAction bean = new nc.ui.pu.m21.action.OrderSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("SAVEBASE");
  bean.setBillType("21");
  bean.setValidationService(getValidateService());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.validation.CompositeValidation getValidateService(){
 if(context.get("validateService")!=null)
 return (nc.ui.pubapp.uif2app.validation.CompositeValidation)context.get("validateService");
  nc.ui.pubapp.uif2app.validation.CompositeValidation bean = new nc.ui.pubapp.uif2app.validation.CompositeValidation();
  context.put("validateService",bean);
  bean.setValidators(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getPowerwritevalidservice());  return list;}

public nc.ui.pubapp.pub.power.PowerSaveValidateService getPowerwritevalidservice(){
 if(context.get("powerwritevalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerSaveValidateService)context.get("powerwritevalidservice");
  nc.ui.pubapp.pub.power.PowerSaveValidateService bean = new nc.ui.pubapp.pub.power.PowerSaveValidateService();
  context.put("powerwritevalidservice",bean);
  bean.setEditActionCode("edit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("21");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.CancelAction getCancelAction(){
 if(context.get("cancelAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.CancelAction)context.get("cancelAction");
  nc.ui.pubapp.uif2app.actions.CancelAction bean = new nc.ui.pubapp.uif2app.actions.CancelAction();
  context.put("cancelAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.SendApproveAction getSendApproveAction(){
 if(context.get("sendApproveAction")!=null)
 return (nc.ui.pu.m21.action.SendApproveAction)context.get("sendApproveAction");
  nc.ui.pu.m21.action.SendApproveAction bean = new nc.ui.pu.m21.action.SendApproveAction();
  context.put("sendApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("SAVE");
  bean.setBillType("21");
  bean.setPreActionNames(getManagedList6());
  bean.setValidationService(getSendpowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add("SAVEBASE");  return list;}

public nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction getSaveApproveAction(){
 if(context.get("saveApproveAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction)context.get("saveApproveAction");
  nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction bean = new nc.ui.pubapp.uif2app.actions.pflow.SaveAndCommitScriptAction(getSaveAction(),getSendApproveAction());  context.put("saveApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBtnName(getI18nFB_1df7d5b());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1df7d5b(){
 if(context.get("nc.ui.uif2.I18nFB#1df7d5b")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1df7d5b");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1df7d5b",bean);  bean.setResDir("common");
  bean.setResId("2SCMPUB-000027");
  bean.setDefaultValue("�����ύ");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1df7d5b",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.pub.action.UnSaveScriptAction getUnSendApproveAction(){
 if(context.get("unSendApproveAction")!=null)
 return (nc.ui.pu.pub.action.UnSaveScriptAction)context.get("unSendApproveAction");
  nc.ui.pu.pub.action.UnSaveScriptAction bean = new nc.ui.pu.pub.action.UnSaveScriptAction();
  context.put("unSendApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setActionName("UNSAVEBILL");
  bean.setBillType("21");
  bean.setFilledUpInFlow(true);
  bean.setValidationService(getUnsendpowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getSendpowervalidservice(){
 if(context.get("sendpowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("sendpowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("sendpowervalidservice",bean);
  bean.setActionCode("commit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("21");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getUnsendpowervalidservice(){
 if(context.get("unsendpowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("unsendpowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("unsendpowervalidservice",bean);
  bean.setActionCode("uncommit");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("21");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getSendApproveMenuAction(){
 if(context.get("sendApproveMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("sendApproveMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("sendApproveMenuAction",bean);
  bean.setCode("sendApproveMenuAction");
  bean.setActions(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getSendApproveAction());  list.add(getUnSendApproveAction());  return list;}

public nc.ui.pubapp.pub.power.PowerValidateService getApprovepowervalidservice(){
 if(context.get("approvepowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("approvepowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("approvepowervalidservice",bean);
  bean.setActionCode("approve");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("21");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.pub.power.PowerValidateService getUnapprovepowervalidservice(){
 if(context.get("unapprovepowervalidservice")!=null)
 return (nc.ui.pubapp.pub.power.PowerValidateService)context.get("unapprovepowervalidservice");
  nc.ui.pubapp.pub.power.PowerValidateService bean = new nc.ui.pubapp.pub.power.PowerValidateService();
  context.put("unapprovepowervalidservice",bean);
  bean.setActionCode("unapprove");
  bean.setBillCodeFiledName("vbillcode");
  bean.setPermissionCode("21");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OrderApproveAction getApproveAction(){
 if(context.get("approveAction")!=null)
 return (nc.ui.pu.m21.action.OrderApproveAction)context.get("approveAction");
  nc.ui.pu.m21.action.OrderApproveAction bean = new nc.ui.pu.m21.action.OrderApproveAction();
  context.put("approveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setActionName("APPROVE");
  bean.setBillType("21");
  bean.setEditor(getBillFormEditor());
  bean.setValidationService(getApprovepowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OrderUnApproveAction getUnApproveAction(){
 if(context.get("unApproveAction")!=null)
 return (nc.ui.pu.m21.action.OrderUnApproveAction)context.get("unApproveAction");
  nc.ui.pu.m21.action.OrderUnApproveAction bean = new nc.ui.pu.m21.action.OrderUnApproveAction();
  context.put("unApproveAction",bean);
  bean.setModel(getManageAppModel());
  bean.setActionName("UNAPPROVE");
  bean.setBillType("21");
  bean.setEditor(getBillFormEditor());
  bean.setValidationService(getUnapprovepowervalidservice());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getAuditMenuAction(){
 if(context.get("auditMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("auditMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("auditMenuAction",bean);
  bean.setCode("auditMenuAction");
  bean.setActions(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getApproveAction());  list.add(getUnApproveAction());  list.add(getSeparatorAction());  list.add(getApproveStatusAction());  return list;}

public nc.ui.pu.m21.action.RefAddRowsFromSourceAction getRefAddRowsFrom20Action(){
 if(context.get("refAddRowsFrom20Action")!=null)
 return (nc.ui.pu.m21.action.RefAddRowsFromSourceAction)context.get("refAddRowsFrom20Action");
  nc.ui.pu.m21.action.RefAddRowsFromSourceAction bean = new nc.ui.pu.m21.action.RefAddRowsFromSourceAction();
  context.put("refAddRowsFrom20Action",bean);
  bean.setSourceBillType("20");
  bean.setSourceBillName(getI18nFB_c24f75());
  bean.setFlowBillType(false);
  bean.setModel(getManageAppModel());
  bean.setEditor(getBillFormEditor());
  bean.setTransferViewProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_c24f75(){
 if(context.get("nc.ui.uif2.I18nFB#c24f75")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#c24f75");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#c24f75",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0496");
  bean.setDefaultValue("�빺��");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#c24f75",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m21.action.FreezeAction getFreezeAction(){
 if(context.get("freezeAction")!=null)
 return (nc.ui.pu.m21.action.FreezeAction)context.get("freezeAction");
  nc.ui.pu.m21.action.FreezeAction bean = new nc.ui.pu.m21.action.FreezeAction();
  context.put("freezeAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.UnfreezeAction getUnfreezeAction(){
 if(context.get("unfreezeAction")!=null)
 return (nc.ui.pu.m21.action.UnfreezeAction)context.get("unfreezeAction");
  nc.ui.pu.m21.action.UnfreezeAction bean = new nc.ui.pu.m21.action.UnfreezeAction();
  context.put("unfreezeAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.CloseAction getCloseAction(){
 if(context.get("closeAction")!=null)
 return (nc.ui.pu.m21.action.CloseAction)context.get("closeAction");
  nc.ui.pu.m21.action.CloseAction bean = new nc.ui.pu.m21.action.CloseAction();
  context.put("closeAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OpenAction getOpenAction(){
 if(context.get("openAction")!=null)
 return (nc.ui.pu.m21.action.OpenAction)context.get("openAction");
  nc.ui.pu.m21.action.OpenAction bean = new nc.ui.pu.m21.action.OpenAction();
  context.put("openAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RowCloseAction getRowCloseAction(){
 if(context.get("rowCloseAction")!=null)
 return (nc.ui.pu.m21.action.RowCloseAction)context.get("rowCloseAction");
  nc.ui.pu.m21.action.RowCloseAction bean = new nc.ui.pu.m21.action.RowCloseAction();
  context.put("rowCloseAction",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setList(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RowOpenAction getRowOpenAction(){
 if(context.get("rowOpenAction")!=null)
 return (nc.ui.pu.m21.action.RowOpenAction)context.get("rowOpenAction");
  nc.ui.pu.m21.action.RowOpenAction bean = new nc.ui.pu.m21.action.RowOpenAction();
  context.put("rowOpenAction",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setList(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.FileDocManageAction getAccessoriesAction(){
 if(context.get("accessoriesAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.FileDocManageAction)context.get("accessoriesAction");
  nc.ui.pubapp.uif2app.actions.FileDocManageAction bean = new nc.ui.pubapp.uif2app.actions.FileDocManageAction();
  context.put("accessoriesAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.AssistAction getAssistMenuAction(){
 if(context.get("assistMenuAction")!=null)
 return (nc.ui.pu.m21.action.AssistAction)context.get("assistMenuAction");
  nc.ui.pu.m21.action.AssistAction bean = new nc.ui.pu.m21.action.AssistAction();
  context.put("assistMenuAction",bean);
  bean.setActions(getManagedList9());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getFreezeAction());  list.add(getUnfreezeAction());  list.add(getCloseAction());  list.add(getOpenAction());  list.add(getRowCloseAction());  list.add(getRowOpenAction());  list.add(getSeparatorAction());  list.add(getAccessoriesAction());  return list;}

public nc.ui.pu.m21.action.AssistAction getAssistMenuActionForCard(){
 if(context.get("assistMenuActionForCard")!=null)
 return (nc.ui.pu.m21.action.AssistAction)context.get("assistMenuActionForCard");
  nc.ui.pu.m21.action.AssistAction bean = new nc.ui.pu.m21.action.AssistAction();
  context.put("assistMenuActionForCard",bean);
  bean.setActions(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getRefAddRowsFrom20Action());  return list;}

public nc.ui.pu.m21.action.KitQueryAction getKitQueryAction(){
 if(context.get("kitQueryAction")!=null)
 return (nc.ui.pu.m21.action.KitQueryAction)context.get("kitQueryAction");
  nc.ui.pu.m21.action.KitQueryAction bean = new nc.ui.pu.m21.action.KitQueryAction();
  context.put("kitQueryAction",bean);
  bean.setEditor(getBillFormEditor());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.BillSaleNumAction getBillSaleNumAction(){
 if(context.get("billSaleNumAction")!=null)
 return (nc.ui.pu.m21.action.BillSaleNumAction)context.get("billSaleNumAction");
  nc.ui.pu.m21.action.BillSaleNumAction bean = new nc.ui.pu.m21.action.BillSaleNumAction();
  context.put("billSaleNumAction",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setList(getListView());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.QueryDefaultPriceAction getQueryDefPriAction(){
 if(context.get("queryDefPriAction")!=null)
 return (nc.ui.pu.m21.action.QueryDefaultPriceAction)context.get("queryDefPriAction");
  nc.ui.pu.m21.action.QueryDefaultPriceAction bean = new nc.ui.pu.m21.action.QueryDefaultPriceAction();
  context.put("queryDefPriAction",bean);
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.QueryCoopPriceAction getQueryCoopPriAction(){
 if(context.get("queryCoopPriAction")!=null)
 return (nc.ui.pu.m21.action.QueryCoopPriceAction)context.get("queryCoopPriAction");
  nc.ui.pu.m21.action.QueryCoopPriceAction bean = new nc.ui.pu.m21.action.QueryCoopPriceAction();
  context.put("queryCoopPriAction",bean);
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.BillGrossProfitAction getBillGrossProfitAction(){
 if(context.get("billGrossProfitAction")!=null)
 return (nc.ui.pu.m21.action.BillGrossProfitAction)context.get("billGrossProfitAction");
  nc.ui.pu.m21.action.BillGrossProfitAction bean = new nc.ui.pu.m21.action.BillGrossProfitAction();
  context.put("billGrossProfitAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.ApQueryAction getApAction(){
 if(context.get("apAction")!=null)
 return (nc.ui.pu.m21.action.ApQueryAction)context.get("apAction");
  nc.ui.pu.m21.action.ApQueryAction bean = new nc.ui.pu.m21.action.ApQueryAction();
  context.put("apAction",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.PayExecStatAction getPayExecStatAction(){
 if(context.get("payExecStatAction")!=null)
 return (nc.ui.pu.m21.action.PayExecStatAction)context.get("payExecStatAction");
  nc.ui.pu.m21.action.PayExecStatAction bean = new nc.ui.pu.m21.action.PayExecStatAction();
  context.put("payExecStatAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.pub.action.PULinkQueryAction getLinkQueryAction(){
 if(context.get("linkQueryAction")!=null)
 return (nc.ui.pu.pub.action.PULinkQueryAction)context.get("linkQueryAction");
  nc.ui.pu.pub.action.PULinkQueryAction bean = new nc.ui.pu.pub.action.PULinkQueryAction();
  context.put("linkQueryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillType("21");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OrderATPAction getATPAction(){
 if(context.get("ATPAction")!=null)
 return (nc.ui.pu.m21.action.OrderATPAction)context.get("ATPAction");
  nc.ui.pu.m21.action.OrderATPAction bean = new nc.ui.pu.m21.action.OrderATPAction();
  context.put("ATPAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillForm(getBillFormEditor());
  bean.setList(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction getApproveStatusAction(){
 if(context.get("approveStatusAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction)context.get("approveStatusAction");
  nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction bean = new nc.ui.pubapp.uif2app.actions.pflow.PFApproveStatusInfoAction();
  context.put("approveStatusAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBtnName(getI18nFB_5e5ac0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_5e5ac0(){
 if(context.get("nc.ui.uif2.I18nFB#5e5ac0")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#5e5ac0");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#5e5ac0",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0579");
  bean.setDefaultValue("�鿴�������");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#5e5ac0",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.pu.m21.action.OrderLinkQueryMppAction getLinkMppAction(){
 if(context.get("linkMppAction")!=null)
 return (nc.ui.pu.m21.action.OrderLinkQueryMppAction)context.get("linkMppAction");
  nc.ui.pu.m21.action.OrderLinkQueryMppAction bean = new nc.ui.pu.m21.action.OrderLinkQueryMppAction();
  context.put("linkMppAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBillForm(getBillFormEditor());
  bean.setList(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.LinkBillMenuAction getLinkBillMenuAction(){
 if(context.get("linkBillMenuAction")!=null)
 return (nc.ui.pu.m21.action.LinkBillMenuAction)context.get("linkBillMenuAction");
  nc.ui.pu.m21.action.LinkBillMenuAction bean = new nc.ui.pu.m21.action.LinkBillMenuAction();
  context.put("linkBillMenuAction",bean);
  bean.setActions(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add(getLinkQueryAction());  list.add(getSeparatorAction());  list.add(getLinkMppAction());  list.add(getKitQueryAction());  list.add(getATPAction());  list.add(getBillSaleNumAction());  list.add(getBillGrossProfitAction());  list.add(getApAction());  list.add(getPayExecStatAction());  return list;}

public nc.ui.pu.m21.action.LinkBillMenuAction getLinkBillMenuActionForCard(){
 if(context.get("linkBillMenuActionForCard")!=null)
 return (nc.ui.pu.m21.action.LinkBillMenuAction)context.get("linkBillMenuActionForCard");
  nc.ui.pu.m21.action.LinkBillMenuAction bean = new nc.ui.pu.m21.action.LinkBillMenuAction();
  context.put("linkBillMenuActionForCard",bean);
  bean.setActions(getManagedList12());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList12(){  List list = new ArrayList();  list.add(getKitQueryAction());  list.add(getATPAction());  list.add(getBillSaleNumAction());  list.add(getBillGrossProfitAction());  list.add(getApAction());  list.add(getPayExecStatAction());  return list;}

public nc.ui.pu.m21.action.ReceivePlanAction getReceivePlanAction(){
 if(context.get("receivePlanAction")!=null)
 return (nc.ui.pu.m21.action.ReceivePlanAction)context.get("receivePlanAction");
  nc.ui.pu.m21.action.ReceivePlanAction bean = new nc.ui.pu.m21.action.ReceivePlanAction();
  context.put("receivePlanAction",bean);
  bean.setBillForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OrderPayPlanAction getPayplanAction(){
 if(context.get("payplanAction")!=null)
 return (nc.ui.pu.m21.action.OrderPayPlanAction)context.get("payplanAction");
  nc.ui.pu.m21.action.OrderPayPlanAction bean = new nc.ui.pu.m21.action.OrderPayPlanAction();
  context.put("payplanAction",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.PushCoopSaleOrderAction getPushCoop30Action(){
 if(context.get("pushCoop30Action")!=null)
 return (nc.ui.pu.m21.action.PushCoopSaleOrderAction)context.get("pushCoop30Action");
  nc.ui.pu.m21.action.PushCoopSaleOrderAction bean = new nc.ui.pu.m21.action.PushCoopSaleOrderAction();
  context.put("pushCoop30Action",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.TransportStatusAction getTransportStatusAction(){
 if(context.get("transportStatusAction")!=null)
 return (nc.ui.pu.m21.action.TransportStatusAction)context.get("transportStatusAction");
  nc.ui.pu.m21.action.TransportStatusAction bean = new nc.ui.pu.m21.action.TransportStatusAction();
  context.put("transportStatusAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.RelateFuncMenuAction getRelateFunMenuAction(){
 if(context.get("relateFunMenuAction")!=null)
 return (nc.ui.pu.m21.action.RelateFuncMenuAction)context.get("relateFunMenuAction");
  nc.ui.pu.m21.action.RelateFuncMenuAction bean = new nc.ui.pu.m21.action.RelateFuncMenuAction();
  context.put("relateFunMenuAction",bean);
  bean.setActions(getManagedList13());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList13(){  List list = new ArrayList();  list.add(getQueryDefPriAction());  list.add(getReceivePlanAction());  list.add(getPayplanAction());  list.add(getPushCoop30Action());  list.add(getTransportStatusAction());  return list;}

public nc.ui.pu.m21.action.RelateFuncMenuAction getRelateFunMenuActionForCard(){
 if(context.get("relateFunMenuActionForCard")!=null)
 return (nc.ui.pu.m21.action.RelateFuncMenuAction)context.get("relateFunMenuActionForCard");
  nc.ui.pu.m21.action.RelateFuncMenuAction bean = new nc.ui.pu.m21.action.RelateFuncMenuAction();
  context.put("relateFunMenuActionForCard",bean);
  bean.setActions(getManagedList14());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList14(){  List list = new ArrayList();  list.add(getQueryDefPriAction());  list.add(getPayplanAction());  list.add(getQueryCoopPriAction());  return list;}

public nc.ui.pu.m21.action.processor.OrderPrintProcessor getPrintProcessor(){
 if(context.get("printProcessor")!=null)
 return (nc.ui.pu.m21.action.processor.OrderPrintProcessor)context.get("printProcessor");
  nc.ui.pu.m21.action.processor.OrderPrintProcessor bean = new nc.ui.pu.m21.action.processor.OrderPrintProcessor();
  context.put("printProcessor",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.PrintAction getPreviewAction(){
 if(context.get("previewAction")!=null)
 return (nc.ui.pu.m21.action.PrintAction)context.get("previewAction");
  nc.ui.pu.m21.action.PrintAction bean = new nc.ui.pu.m21.action.PrintAction();
  context.put("previewAction",bean);
  bean.setPreview(true);
  bean.setNodeKey("4004040002");
  bean.setModel(getManageAppModel());
  bean.setDataSplit(getPrintProcessor());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.PrintAction getPrintAction(){
 if(context.get("printAction")!=null)
 return (nc.ui.pu.m21.action.PrintAction)context.get("printAction");
  nc.ui.pu.m21.action.PrintAction bean = new nc.ui.pu.m21.action.PrintAction();
  context.put("printAction",bean);
  bean.setPreview(false);
  bean.setNodeKey("4004040002");
  bean.setModel(getManageAppModel());
  bean.setDataSplit(getPrintProcessor());
  bean.setBeforePrintDataProcess(getPrintProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.OutputSpecialProcessAction getOutputAction(){
 if(context.get("outputAction")!=null)
 return (nc.ui.pu.m21.action.OutputSpecialProcessAction)context.get("outputAction");
  nc.ui.pu.m21.action.OutputSpecialProcessAction bean = new nc.ui.pu.m21.action.OutputSpecialProcessAction();
  context.put("outputAction",bean);
  bean.setNodeKey("4004040002");
  bean.setModel(getManageAppModel());
  bean.setParent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.action.CombineShowAction getCombineShowAction(){
 if(context.get("combineShowAction")!=null)
 return (nc.ui.pu.m21.action.CombineShowAction)context.get("combineShowAction");
  nc.ui.pu.m21.action.CombineShowAction bean = new nc.ui.pu.m21.action.CombineShowAction();
  context.put("combineShowAction",bean);
  bean.setOrderForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.action.SCMPrintCountQueryAction getPrintCountQueryAction(){
 if(context.get("printCountQueryAction")!=null)
 return (nc.ui.scmpub.action.SCMPrintCountQueryAction)context.get("printCountQueryAction");
  nc.ui.scmpub.action.SCMPrintCountQueryAction bean = new nc.ui.scmpub.action.SCMPrintCountQueryAction();
  context.put("printCountQueryAction",bean);
  bean.setModel(getManageAppModel());
  bean.setBilldateFieldName("dbilldate");
  bean.setBillType("21");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.GroupAction getPrintMenuAction(){
 if(context.get("printMenuAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printMenuAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printMenuAction",bean);
  bean.setCode("printMenuAction");
  bean.setActions(getManagedList15());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList15(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  list.add(getPrintCountQueryAction());  list.add(getSeparatorAction());  list.add(getCombineShowAction());  return list;}

public nc.ui.uif2.actions.IMGroupChatAction getIMAction(){
 if(context.get("IMAction")!=null)
 return (nc.ui.uif2.actions.IMGroupChatAction)context.get("IMAction");
  nc.ui.uif2.actions.IMGroupChatAction bean = new nc.ui.uif2.actions.IMGroupChatAction();
  context.put("IMAction",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.editor.org.OrgChangedEventHandler getPurhchaseOrganization(){
 if(context.get("purhchaseOrganization")!=null)
 return (nc.ui.pu.m21.editor.org.OrgChangedEventHandler)context.get("purhchaseOrganization");
  nc.ui.pu.m21.editor.org.OrgChangedEventHandler bean = new nc.ui.pu.m21.editor.org.OrgChangedEventHandler();
  context.put("purhchaseOrganization",bean);
  bean.setCardForm(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.uif2.ReviseInAppoveActionProcesser getReviseActionProcesser(){
 if(context.get("reviseActionProcesser")!=null)
 return (nc.ui.pu.uif2.ReviseInAppoveActionProcesser)context.get("reviseActionProcesser");
  nc.ui.pu.uif2.ReviseInAppoveActionProcesser bean = new nc.ui.pu.uif2.ReviseInAppoveActionProcesser();
  context.put("reviseActionProcesser",bean);
  bean.setActionContributor(getToftpanelActionContributors());
  bean.setReviseAction(getReviseAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.editor.card.beforeedit.CardHeadTailBeforeEditEventHandler getCardHeadBeforeEdit(){
 if(context.get("cardHeadBeforeEdit")!=null)
 return (nc.ui.pu.m21.editor.card.beforeedit.CardHeadTailBeforeEditEventHandler)context.get("cardHeadBeforeEdit");
  nc.ui.pu.m21.editor.card.beforeedit.CardHeadTailBeforeEditEventHandler bean = new nc.ui.pu.m21.editor.card.beforeedit.CardHeadTailBeforeEditEventHandler();
  context.put("cardHeadBeforeEdit",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.editor.card.beforeedit.CardBodyBeforeEditEventHandler getCardBodyBeforeEdit(){
 if(context.get("cardBodyBeforeEdit")!=null)
 return (nc.ui.pu.m21.editor.card.beforeedit.CardBodyBeforeEditEventHandler)context.get("cardBodyBeforeEdit");
  nc.ui.pu.m21.editor.card.beforeedit.CardBodyBeforeEditEventHandler bean = new nc.ui.pu.m21.editor.card.beforeedit.CardBodyBeforeEditEventHandler();
  context.put("cardBodyBeforeEdit",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.editor.card.afteredit.CardHeadTailAfterEditEventHandler getCardHeadAfterEdit(){
 if(context.get("cardHeadAfterEdit")!=null)
 return (nc.ui.pu.m21.editor.card.afteredit.CardHeadTailAfterEditEventHandler)context.get("cardHeadAfterEdit");
  nc.ui.pu.m21.editor.card.afteredit.CardHeadTailAfterEditEventHandler bean = new nc.ui.pu.m21.editor.card.afteredit.CardHeadTailAfterEditEventHandler();
  context.put("cardHeadAfterEdit",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.editor.card.afteredit.CardBodyAfterEditEventHandler getCardBodyAfterEdit(){
 if(context.get("cardBodyAfterEdit")!=null)
 return (nc.ui.pu.m21.editor.card.afteredit.CardBodyAfterEditEventHandler)context.get("cardBodyAfterEdit");
  nc.ui.pu.m21.editor.card.afteredit.CardBodyAfterEditEventHandler bean = new nc.ui.pu.m21.editor.card.afteredit.CardBodyAfterEditEventHandler();
  context.put("cardBodyAfterEdit",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.editor.card.afteredit.RelationCalculate getRelationCalculate(){
 if(context.get("relationCalculate")!=null)
 return (nc.ui.pu.m21.editor.card.afteredit.RelationCalculate)context.get("relationCalculate");
  nc.ui.pu.m21.editor.card.afteredit.RelationCalculate bean = new nc.ui.pu.m21.editor.card.afteredit.RelationCalculate();
  context.put("relationCalculate",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.editor.list.ListHeadRowChangedEventHandler getListHeadRowChangedEvent(){
 if(context.get("listHeadRowChangedEvent")!=null)
 return (nc.ui.pu.m21.editor.list.ListHeadRowChangedEventHandler)context.get("listHeadRowChangedEvent");
  nc.ui.pu.m21.editor.list.ListHeadRowChangedEventHandler bean = new nc.ui.pu.m21.editor.list.ListHeadRowChangedEventHandler();
  context.put("listHeadRowChangedEvent",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.AppEventHandlerMediator getAppEventHandlerMediator(){
 if(context.get("appEventHandlerMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.AppEventHandlerMediator)context.get("appEventHandlerMediator");
  nc.ui.pubapp.uif2app.model.AppEventHandlerMediator bean = new nc.ui.pubapp.uif2app.model.AppEventHandlerMediator();
  context.put("appEventHandlerMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setHandlerMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("nc.ui.pubapp.uif2app.event.OrgChangedEvent",getManagedList16());  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailBeforeEditEvent",getManagedList17());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyBeforeEditEvent",getManagedList18());  map.put("nc.ui.pubapp.uif2app.event.card.CardHeadTailAfterEditEvent",getManagedList19());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyAfterEditEvent",getManagedList20());  map.put("nc.ui.pubapp.uif2app.event.list.ListHeadRowChangedEvent",getManagedList21());  map.put("nc.ui.pubapp.uif2app.event.card.CardBodyRowChangedEvent",getManagedList22());  map.put("nc.ui.uif2.AppEvent",getManagedList23());  return map;}

private List getManagedList16(){  List list = new ArrayList();  list.add(getPurhchaseOrganization());  return list;}

private List getManagedList17(){  List list = new ArrayList();  list.add(getCardHeadBeforeEdit());  return list;}

private List getManagedList18(){  List list = new ArrayList();  list.add(getCardBodyBeforeEdit());  return list;}

private List getManagedList19(){  List list = new ArrayList();  list.add(getCardHeadAfterEdit());  return list;}

private List getManagedList20(){  List list = new ArrayList();  list.add(getCardBodyAfterEdit());  list.add(getRelationCalculate());  return list;}

private List getManagedList21(){  List list = new ArrayList();  list.add(getListHeadRowChangedEvent());  return list;}

private List getManagedList22(){  List list = new ArrayList();  list.add(getSideFormMediator());  return list;}

private List getManagedList23(){  List list = new ArrayList();  list.add(getReviseActionProcesser());  return list;}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setContext(getContext());
  bean.setModel(getManageAppModel());
  bean.setQueryAction(getQueryAction());
  bean.setVoClassName("nc.vo.pu.m21.entity.OrderVO");
  bean.setAutoShowUpComponent(getBillFormEditor());
  bean.setProcessorMap(getManagedMap1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put("19",getInitDataProcessorForSale_164dad1());  map.put("28",getInitDataProcessorFor28_1602b49());  map.put("47",getInitDataProcessorForEC_1f51f0e());  map.put("40",getInitDataProcessorForTbb_13d0eac());  map.put("89",getPUMutiPkLinkQueryProcessor());  map.put("470",getInitProcessorm4F());  return map;}

private nc.ui.pu.m21.billref.processor.InitDataProcessorForSale getInitDataProcessorForSale_164dad1(){
 if(context.get("nc.ui.pu.m21.billref.processor.InitDataProcessorForSale#164dad1")!=null)
 return (nc.ui.pu.m21.billref.processor.InitDataProcessorForSale)context.get("nc.ui.pu.m21.billref.processor.InitDataProcessorForSale#164dad1");
  nc.ui.pu.m21.billref.processor.InitDataProcessorForSale bean = new nc.ui.pu.m21.billref.processor.InitDataProcessorForSale();
  context.put("nc.ui.pu.m21.billref.processor.InitDataProcessorForSale#164dad1",bean);
  bean.setTransferProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.billref.processor.InitDataProcessorFor28 getInitDataProcessorFor28_1602b49(){
 if(context.get("nc.ui.pu.m21.billref.processor.InitDataProcessorFor28#1602b49")!=null)
 return (nc.ui.pu.m21.billref.processor.InitDataProcessorFor28)context.get("nc.ui.pu.m21.billref.processor.InitDataProcessorFor28#1602b49");
  nc.ui.pu.m21.billref.processor.InitDataProcessorFor28 bean = new nc.ui.pu.m21.billref.processor.InitDataProcessorFor28();
  context.put("nc.ui.pu.m21.billref.processor.InitDataProcessorFor28#1602b49",bean);
  bean.setTransferProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.billref.processor.InitDataProcessorForEC getInitDataProcessorForEC_1f51f0e(){
 if(context.get("nc.ui.pu.m21.billref.processor.InitDataProcessorForEC#1f51f0e")!=null)
 return (nc.ui.pu.m21.billref.processor.InitDataProcessorForEC)context.get("nc.ui.pu.m21.billref.processor.InitDataProcessorForEC#1f51f0e");
  nc.ui.pu.m21.billref.processor.InitDataProcessorForEC bean = new nc.ui.pu.m21.billref.processor.InitDataProcessorForEC();
  context.put("nc.ui.pu.m21.billref.processor.InitDataProcessorForEC#1f51f0e",bean);
  bean.setTransferProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.billref.processor.InitDataProcessorForTbb getInitDataProcessorForTbb_13d0eac(){
 if(context.get("nc.ui.pu.m21.billref.processor.InitDataProcessorForTbb#13d0eac")!=null)
 return (nc.ui.pu.m21.billref.processor.InitDataProcessorForTbb)context.get("nc.ui.pu.m21.billref.processor.InitDataProcessorForTbb#13d0eac");
  nc.ui.pu.m21.billref.processor.InitDataProcessorForTbb bean = new nc.ui.pu.m21.billref.processor.InitDataProcessorForTbb();
  context.put("nc.ui.pu.m21.billref.processor.InitDataProcessorForTbb#13d0eac",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.billref.PUMutiPkLinkQueryProcessor getPUMutiPkLinkQueryProcessor(){
 if(context.get("PUMutiPkLinkQueryProcessor")!=null)
 return (nc.ui.pu.billref.PUMutiPkLinkQueryProcessor)context.get("PUMutiPkLinkQueryProcessor");
  nc.ui.pu.billref.PUMutiPkLinkQueryProcessor bean = new nc.ui.pu.billref.PUMutiPkLinkQueryProcessor();
  context.put("PUMutiPkLinkQueryProcessor",bean);
  bean.setModel(getManageAppModel());
  bean.setAutoShowUpComponent(getListView());
  bean.setVoClass("nc.vo.pu.m21.entity.OrderVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.billref.invp.m4f.InitDataProcessor4Fto21 getInitProcessorm4F(){
 if(context.get("initProcessorm4F")!=null)
 return (nc.ui.pu.m21.billref.invp.m4f.InitDataProcessor4Fto21)context.get("initProcessorm4F");
  nc.ui.pu.m21.billref.invp.m4f.InitDataProcessor4Fto21 bean = new nc.ui.pu.m21.billref.invp.m4f.InitDataProcessor4Fto21();
  context.put("initProcessorm4F",bean);
  bean.setTransferProcessor(getTransferViewProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.pub.editor.ClientContext getContext(){
 if(context.get("context")!=null)
 return (nc.ui.pu.pub.editor.ClientContext)context.get("context");
  nc.ui.pu.pub.editor.ClientContext bean = new nc.ui.pu.pub.editor.ClientContext();
  context.put("context",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.service.OrderModelService getManageModelService(){
 if(context.get("manageModelService")!=null)
 return (nc.ui.pu.m21.service.OrderModelService)context.get("manageModelService");
  nc.ui.pu.m21.service.OrderModelService bean = new nc.ui.pu.m21.service.OrderModelService();
  context.put("manageModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory getBoadatorfactory(){
 if(context.get("boadatorfactory")!=null)
 return (nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory)context.get("boadatorfactory");
  nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory bean = new nc.ui.pubapp.uif2app.view.value.AggVOMetaBDObjectAdapterFactory();
  context.put("boadatorfactory",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.uif2.PUBillManageModel getManageAppModel(){
 if(context.get("manageAppModel")!=null)
 return (nc.ui.pu.uif2.PUBillManageModel)context.get("manageAppModel");
  nc.ui.pu.uif2.PUBillManageModel bean = new nc.ui.pu.uif2.PUBillManageModel();
  context.put("manageAppModel",bean);
  bean.setService(getManageModelService());
  bean.setBusinessObjectAdapterFactory(getBoadatorfactory());
  bean.setContext(getContext());
  bean.setBillType("21");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageModelDataManager getModelDataManager(){
 if(context.get("modelDataManager")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageModelDataManager)context.get("modelDataManager");
  nc.ui.scmpub.page.model.SCMBillPageModelDataManager bean = new nc.ui.scmpub.page.model.SCMBillPageModelDataManager();
  context.put("modelDataManager",bean);
  bean.setModel(getManageAppModel());
  bean.setPageQuery(getPageQuery());
  bean.setPageDelegator(getPageDelegator());
  bean.setPagePanel(getQueryInfo());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.components.pagination.PaginationBar getPageBar(){
 if(context.get("pageBar")!=null)
 return (nc.ui.uif2.components.pagination.PaginationBar)context.get("pageBar");
  nc.ui.uif2.components.pagination.PaginationBar bean = new nc.ui.uif2.components.pagination.PaginationBar();
  context.put("pageBar",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator getPageDelegator(){
 if(context.get("pageDelegator")!=null)
 return (nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator)context.get("pageDelegator");
  nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator bean = new nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator(getManageAppModel());  context.put("pageDelegator",bean);
  bean.setPaginationQuery(getPageQuery());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.service.OrderPageModelService getPageQuery(){
 if(context.get("pageQuery")!=null)
 return (nc.ui.pu.m21.service.OrderPageModelService)context.get("pageQuery");
  nc.ui.pu.m21.service.OrderPageModelService bean = new nc.ui.pu.m21.service.OrderPageModelService();
  context.put("pageQuery",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageMediator getPageMediator(){
 if(context.get("pageMediator")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageMediator)context.get("pageMediator");
  nc.ui.scmpub.page.model.SCMBillPageMediator bean = new nc.ui.scmpub.page.model.SCMBillPageMediator();
  context.put("pageMediator",bean);
  bean.setListView(getListView());
  bean.setRecordInPage(10);
  bean.setCachePages(10);
  bean.setPageDelegator(getPageDelegator());
  bean.init();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender getBillTemplateMender(){
 if(context.get("billTemplateMender")!=null)
 return (nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender)context.get("billTemplateMender");
  nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender bean = new nc.ui.pubapp.uif2app.funcnode.trantype.TrantypeBillTemplateMender(getContext());  context.put("billTemplateMender",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.TemplateContainer getTemplateContainer(){
 if(context.get("templateContainer")!=null)
 return (nc.ui.pubapp.uif2app.view.TemplateContainer)context.get("templateContainer");
  nc.ui.pubapp.uif2app.view.TemplateContainer bean = new nc.ui.pubapp.uif2app.view.TemplateContainer();
  context.put("templateContainer",bean);
  bean.setContext(getContext());
  bean.setBillTemplateMender(getBillTemplateMender());
  bean.load();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.QueryTemplateContainer getQueryTemplateContainer(){
 if(context.get("queryTemplateContainer")!=null)
 return (nc.ui.uif2.editor.QueryTemplateContainer)context.get("queryTemplateContainer");
  nc.ui.uif2.editor.QueryTemplateContainer bean = new nc.ui.uif2.editor.QueryTemplateContainer();
  context.put("queryTemplateContainer",bean);
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.PfAddInfoLoader getPfAddInfoLoader(){
 if(context.get("pfAddInfoLoader")!=null)
 return (nc.ui.pubapp.uif2app.actions.PfAddInfoLoader)context.get("pfAddInfoLoader");
  nc.ui.pubapp.uif2app.actions.PfAddInfoLoader bean = new nc.ui.pubapp.uif2app.actions.PfAddInfoLoader();
  context.put("pfAddInfoLoader",bean);
  bean.setBillType("21");
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.listener.BillCodeEditMediator getBillCodeMediator(){
 if(context.get("billCodeMediator")!=null)
 return (nc.ui.scmpub.listener.BillCodeEditMediator)context.get("billCodeMediator");
  nc.ui.scmpub.listener.BillCodeEditMediator bean = new nc.ui.scmpub.listener.BillCodeEditMediator();
  context.put("billCodeMediator",bean);
  bean.setBillCodeKey("vbillcode");
  bean.setBillType("21");
  bean.setBillForm(getBillFormEditor());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.RowNoMediator getRowNoMediator(){
 if(context.get("rowNoMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.RowNoMediator)context.get("rowNoMediator");
  nc.ui.pubapp.uif2app.view.RowNoMediator bean = new nc.ui.pubapp.uif2app.view.RowNoMediator();
  context.put("rowNoMediator",bean);
  bean.setEditor(getBillFormEditor());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmbd.linkquery.ScmBDLinkQueryMediator getDiscountMediator(){
 if(context.get("discountMediator")!=null)
 return (nc.ui.scmbd.linkquery.ScmBDLinkQueryMediator)context.get("discountMediator");
  nc.ui.scmbd.linkquery.ScmBDLinkQueryMediator bean = new nc.ui.scmbd.linkquery.ScmBDLinkQueryMediator();
  context.put("discountMediator",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getCcontractidMediator(){
 if(context.get("ccontractidMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("ccontractidMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("ccontractidMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("ccontractid");
  bean.setSrcBillNOField("ccontractid");
  bean.setSrcBillType("Z2");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getCecbillidMediator(){
 if(context.get("cecbillidMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("cecbillidMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("cecbillidMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("cecbillid");
  bean.setSrcBillNOField("vecbillcode");
  bean.setSrcBillType("EC47");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getCpraybillcodeMediator(){
 if(context.get("cpraybillcodeMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("cpraybillcodeMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("cpraybillcodeMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("cpraybillhid");
  bean.setSrcBillNOField("cpraybillcode");
  bean.setSrcBillType("20");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getVsourcecodeMediator(){
 if(context.get("vsourcecodeMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("vsourcecodeMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("vsourcecodeMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("csourceid");
  bean.setSrcBillNOField("vsourcecode");
  bean.setSrcBillTypeField("csourcetypecode");
  bean.setSrcBillTypeFieldPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator getVfirstcodeMediator(){
 if(context.get("vfirstcodeMediator")!=null)
 return (nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator)context.get("vfirstcodeMediator");
  nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator bean = new nc.ui.pubapp.uif2app.linkquery.LinkQueryHyperlinkMediator();
  context.put("vfirstcodeMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setSrcBillIdField("cfirstid");
  bean.setSrcBillNOField("vfirstcode");
  bean.setSrcBillTypeField("cfirsttypecode");
  bean.setSrcBillTypeFieldPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.FunNodeClosingHandler getClosingListener(){
 if(context.get("ClosingListener")!=null)
 return (nc.ui.uif2.FunNodeClosingHandler)context.get("ClosingListener");
  nc.ui.uif2.FunNodeClosingHandler bean = new nc.ui.uif2.FunNodeClosingHandler();
  context.put("ClosingListener",bean);
  bean.setModel(getManageAppModel());
  bean.setSaveaction(getSaveAction());
  bean.setCancelaction(getCancelAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.view.OrderListView getListView(){
 if(context.get("listView")!=null)
 return (nc.ui.pu.m21.view.OrderListView)context.get("listView");
  nc.ui.pu.m21.view.OrderListView bean = new nc.ui.pu.m21.view.OrderListView();
  context.put("listView",bean);
  bean.setModel(getManageAppModel());
  bean.setMultiSelectionMode(0);
  bean.setTemplateContainer(getTemplateContainer());
  bean.setPaginationBar(getPageBar());
  bean.setUserdefitemListPreparator(getUserdefAndMarAsstListPreparator());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.view.OrderBillForm getBillFormEditor(){
 if(context.get("billFormEditor")!=null)
 return (nc.ui.pu.m21.view.OrderBillForm)context.get("billFormEditor");
  nc.ui.pu.m21.view.OrderBillForm bean = new nc.ui.pu.m21.view.OrderBillForm();
  context.put("billFormEditor",bean);
  bean.setModel(getManageAppModel());
  bean.setTemplateContainer(getTemplateContainer());
  bean.setUserdefitemPreparator(getUserdefAndMarAsstCardPreparator());
  bean.setTemplateNotNullValidate(true);
  bean.setAutoAddLine(true);
  bean.setClearHyperlink(getManagedList24());
  bean.setBlankChildrenFilter(getMultiFieldsBlankChildrenFilter_b6f088());
  bean.setBodyActionMap(getManagedMap3());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList24(){  List list = new ArrayList();  list.add("vbillcode");  return list;}

private nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter getMultiFieldsBlankChildrenFilter_b6f088(){
 if(context.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#b6f088")!=null)
 return (nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter)context.get("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#b6f088");
  nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter();
  context.put("nc.ui.pubapp.uif2app.view.value.MultiFieldsBlankChildrenFilter#b6f088",bean);
  bean.setFilterMap(getManagedMap2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap2(){  Map map = new HashMap();  map.put("material",getManagedList25());  map.put("pu_order_payment",getManagedList26());  return map;}

private List getManagedList25(){  List list = new ArrayList();  list.add("pk_material");  return list;}

private List getManagedList26(){  List list = new ArrayList();  list.add("accrate");  return list;}

private Map getManagedMap3(){  Map map = new HashMap();  map.put("material",getManagedList27());  map.put("pu_order_payment",getManagedList28());  return map;}

private List getManagedList27(){  List list = new ArrayList();  list.add(getAddLineAction_7eb034());  list.add(getInsertLineAction_1cad59a());  list.add(getDelLineAction_9fe3a1());  list.add(getBodyCopyLineAction_1d672ca());  list.add(getOrderPastLineAction_e281d4());  list.add(getOrderPasteToTailAction_155e717());  list.add(getActionsBar_ActionsBarSeparator_b29137());  list.add(getBodyLineEditAction_a0e009());  list.add(getRearrangeRowNoBodyLineAction_723468());  list.add(getActionsBar_ActionsBarSeparator_4b186e());  list.add(getDefaultBodyZoomAction_7a1eb9());  return list;}

private nc.ui.pu.m21.action.AddLineAction getAddLineAction_7eb034(){
 if(context.get("nc.ui.pu.m21.action.AddLineAction#7eb034")!=null)
 return (nc.ui.pu.m21.action.AddLineAction)context.get("nc.ui.pu.m21.action.AddLineAction#7eb034");
  nc.ui.pu.m21.action.AddLineAction bean = new nc.ui.pu.m21.action.AddLineAction();
  context.put("nc.ui.pu.m21.action.AddLineAction#7eb034",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.action.InsertLineAction getInsertLineAction_1cad59a(){
 if(context.get("nc.ui.pu.m21.action.InsertLineAction#1cad59a")!=null)
 return (nc.ui.pu.m21.action.InsertLineAction)context.get("nc.ui.pu.m21.action.InsertLineAction#1cad59a");
  nc.ui.pu.m21.action.InsertLineAction bean = new nc.ui.pu.m21.action.InsertLineAction();
  context.put("nc.ui.pu.m21.action.InsertLineAction#1cad59a",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.action.DelLineAction getDelLineAction_9fe3a1(){
 if(context.get("nc.ui.pu.m21.action.DelLineAction#9fe3a1")!=null)
 return (nc.ui.pu.m21.action.DelLineAction)context.get("nc.ui.pu.m21.action.DelLineAction#9fe3a1");
  nc.ui.pu.m21.action.DelLineAction bean = new nc.ui.pu.m21.action.DelLineAction();
  context.put("nc.ui.pu.m21.action.DelLineAction#9fe3a1",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_1d672ca(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1d672ca")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1d672ca");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#1d672ca",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.action.OrderPastLineAction getOrderPastLineAction_e281d4(){
 if(context.get("nc.ui.pu.m21.action.OrderPastLineAction#e281d4")!=null)
 return (nc.ui.pu.m21.action.OrderPastLineAction)context.get("nc.ui.pu.m21.action.OrderPastLineAction#e281d4");
  nc.ui.pu.m21.action.OrderPastLineAction bean = new nc.ui.pu.m21.action.OrderPastLineAction();
  context.put("nc.ui.pu.m21.action.OrderPastLineAction#e281d4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.action.OrderPasteToTailAction getOrderPasteToTailAction_155e717(){
 if(context.get("nc.ui.pu.m21.action.OrderPasteToTailAction#155e717")!=null)
 return (nc.ui.pu.m21.action.OrderPasteToTailAction)context.get("nc.ui.pu.m21.action.OrderPasteToTailAction#155e717");
  nc.ui.pu.m21.action.OrderPasteToTailAction bean = new nc.ui.pu.m21.action.OrderPasteToTailAction();
  context.put("nc.ui.pu.m21.action.OrderPasteToTailAction#155e717",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_b29137(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#b29137")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#b29137");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#b29137",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyLineEditAction getBodyLineEditAction_a0e009(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#a0e009")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyLineEditAction)context.get("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#a0e009");
  nc.ui.pubapp.uif2app.actions.BodyLineEditAction bean = new nc.ui.pubapp.uif2app.actions.BodyLineEditAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyLineEditAction#a0e009",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction getRearrangeRowNoBodyLineAction_723468(){
 if(context.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#723468")!=null)
 return (nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction)context.get("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#723468");
  nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction bean = new nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.RearrangeRowNoBodyLineAction#723468",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_4b186e(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#4b186e")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#4b186e");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#4b186e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_7a1eb9(){
 if(context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#7a1eb9")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction)context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#7a1eb9");
  nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
  context.put("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#7a1eb9",bean);
  bean.setPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList28(){  List list = new ArrayList();  list.add(getPaymentBodyAddLineAction_1110397());  list.add(getPaymentBodyInsertLineAction_a3aa69());  list.add(getPaymentBodyDelLineAction_d54afb());  list.add(getBodyCopyLineAction_260cce());  list.add(getPaymentPastLineAction_1c4aa26());  return list;}

private nc.ui.pu.m21.action.PaymentBodyAddLineAction getPaymentBodyAddLineAction_1110397(){
 if(context.get("nc.ui.pu.m21.action.PaymentBodyAddLineAction#1110397")!=null)
 return (nc.ui.pu.m21.action.PaymentBodyAddLineAction)context.get("nc.ui.pu.m21.action.PaymentBodyAddLineAction#1110397");
  nc.ui.pu.m21.action.PaymentBodyAddLineAction bean = new nc.ui.pu.m21.action.PaymentBodyAddLineAction();
  context.put("nc.ui.pu.m21.action.PaymentBodyAddLineAction#1110397",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.action.PaymentBodyInsertLineAction getPaymentBodyInsertLineAction_a3aa69(){
 if(context.get("nc.ui.pu.m21.action.PaymentBodyInsertLineAction#a3aa69")!=null)
 return (nc.ui.pu.m21.action.PaymentBodyInsertLineAction)context.get("nc.ui.pu.m21.action.PaymentBodyInsertLineAction#a3aa69");
  nc.ui.pu.m21.action.PaymentBodyInsertLineAction bean = new nc.ui.pu.m21.action.PaymentBodyInsertLineAction();
  context.put("nc.ui.pu.m21.action.PaymentBodyInsertLineAction#a3aa69",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.action.PaymentBodyDelLineAction getPaymentBodyDelLineAction_d54afb(){
 if(context.get("nc.ui.pu.m21.action.PaymentBodyDelLineAction#d54afb")!=null)
 return (nc.ui.pu.m21.action.PaymentBodyDelLineAction)context.get("nc.ui.pu.m21.action.PaymentBodyDelLineAction#d54afb");
  nc.ui.pu.m21.action.PaymentBodyDelLineAction bean = new nc.ui.pu.m21.action.PaymentBodyDelLineAction();
  context.put("nc.ui.pu.m21.action.PaymentBodyDelLineAction#d54afb",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.BodyCopyLineAction getBodyCopyLineAction_260cce(){
 if(context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#260cce")!=null)
 return (nc.ui.pubapp.uif2app.actions.BodyCopyLineAction)context.get("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#260cce");
  nc.ui.pubapp.uif2app.actions.BodyCopyLineAction bean = new nc.ui.pubapp.uif2app.actions.BodyCopyLineAction();
  context.put("nc.ui.pubapp.uif2app.actions.BodyCopyLineAction#260cce",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pu.m21.action.PaymentPastLineAction getPaymentPastLineAction_1c4aa26(){
 if(context.get("nc.ui.pu.m21.action.PaymentPastLineAction#1c4aa26")!=null)
 return (nc.ui.pu.m21.action.PaymentPastLineAction)context.get("nc.ui.pu.m21.action.PaymentPastLineAction#1c4aa26");
  nc.ui.pu.m21.action.PaymentPastLineAction bean = new nc.ui.pu.m21.action.PaymentPastLineAction();
  context.put("nc.ui.pu.m21.action.PaymentPastLineAction#1c4aa26",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.FractionFixMediator getFractionFixMediator(){
 if(context.get("fractionFixMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.FractionFixMediator)context.get("fractionFixMediator");
  nc.ui.pubapp.uif2app.view.FractionFixMediator bean = new nc.ui.pubapp.uif2app.view.FractionFixMediator(getManagedList29(),getManagedList30());  context.put("fractionFixMediator",bean);
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList29(){  List list = new ArrayList();  list.add(getBillFormEditor());  return list;}

private List getManagedList30(){  List list = new ArrayList();  list.add(getListView());  return list;}

public nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator getMouseClickShowPanelMediator(){
 if(context.get("mouseClickShowPanelMediator")!=null)
 return (nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator)context.get("mouseClickShowPanelMediator");
  nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator bean = new nc.ui.pubapp.uif2app.view.MouseClickShowPanelMediator();
  context.put("mouseClickShowPanelMediator",bean);
  bean.setListView(getListView());
  bean.setShowUpComponent(getBillFormEditor());
  bean.setHyperLinkColumn("vbillcode");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.BillBodySortMediator getBillBodySortMediator(){
 if(context.get("billBodySortMediator")!=null)
 return (nc.ui.pubapp.uif2app.model.BillBodySortMediator)context.get("billBodySortMediator");
  nc.ui.pubapp.uif2app.model.BillBodySortMediator bean = new nc.ui.pubapp.uif2app.model.BillBodySortMediator(getManageAppModel(),getBillFormEditor(),getListView());  context.put("billBodySortMediator",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell getQueryArea(){
 if(context.get("queryArea")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)context.get("queryArea");
  nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell bean = new nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell();
  context.put("queryArea",bean);
  bean.setQueryAreaCreator(getQueryAction());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel getCardInfoPnl(){
 if(context.get("cardInfoPnl")!=null)
 return (nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel)context.get("cardInfoPnl");
  nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel bean = new nc.ui.pubapp.uif2app.tangramlayout.UECardLayoutToolbarPanel();
  context.put("cardInfoPnl",bean);
  bean.setTitleAction(getReturnaction());
  bean.setRightExActions(getManagedList31());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.UEReturnAction getReturnaction(){
 if(context.get("returnaction")!=null)
 return (nc.ui.pubapp.uif2app.actions.UEReturnAction)context.get("returnaction");
  nc.ui.pubapp.uif2app.actions.UEReturnAction bean = new nc.ui.pubapp.uif2app.actions.UEReturnAction();
  context.put("returnaction",bean);
  bean.setGoComponent(getListView());
  bean.setSaveAction(getSaveAction());
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList31(){  List list = new ArrayList();  list.add(getActionsBar_ActionsBarSeparator_2db595());  list.add(getHeadZoomAction());  return list;}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_2db595(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#2db595")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#2db595");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#2db595",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction getHeadZoomAction(){
 if(context.get("headZoomAction")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction)context.get("headZoomAction");
  nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultHeadZoomAction();
  context.put("headZoomAction",bean);
  bean.setBillForm(getBillFormEditor());
  bean.setModel(getManageAppModel());
  bean.setPos(0);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.DefaultExceptionHanler getExceptionHandler(){
 if(context.get("exceptionHandler")!=null)
 return (nc.ui.uif2.DefaultExceptionHanler)context.get("exceptionHandler");
  nc.ui.uif2.DefaultExceptionHanler bean = new nc.ui.uif2.DefaultExceptionHanler();
  context.put("exceptionHandler",bean);
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.view.sideform.OrderSideFormMediator getSideFormMediator(){
 if(context.get("sideFormMediator")!=null)
 return (nc.ui.pu.m21.view.sideform.OrderSideFormMediator)context.get("sideFormMediator");
  nc.ui.pu.m21.view.sideform.OrderSideFormMediator bean = new nc.ui.pu.m21.view.sideform.OrderSideFormMediator();
  context.put("sideFormMediator",bean);
  bean.setSideFormList(getManagedList32());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList32(){  List list = new ArrayList();  list.add(getSavingAmount());  list.add(getPriceForm());  return list;}

public nc.ui.pu.m21.view.sideform.PriceForm getPriceForm(){
 if(context.get("priceForm")!=null)
 return (nc.ui.pu.m21.view.sideform.PriceForm)context.get("priceForm");
  nc.ui.pu.m21.view.sideform.PriceForm bean = new nc.ui.pu.m21.view.sideform.PriceForm();
  context.put("priceForm",bean);
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.view.sideform.SavingAmountForm getSavingAmount(){
 if(context.get("savingAmount")!=null)
 return (nc.ui.pu.m21.view.sideform.SavingAmountForm)context.get("savingAmount");
  nc.ui.pu.m21.view.sideform.SavingAmountForm bean = new nc.ui.pu.m21.view.sideform.SavingAmountForm();
  context.put("savingAmount",bean);
  bean.setContext(getContext());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.m21.view.sideform.ShowAllAction getShowAllAction(){
 if(context.get("showAllAction")!=null)
 return (nc.ui.pu.m21.view.sideform.ShowAllAction)context.get("showAllAction");
  nc.ui.pu.m21.view.sideform.ShowAllAction bean = new nc.ui.pu.m21.view.sideform.ShowAllAction();
  context.put("showAllAction",bean);
  bean.setMediator(getSideFormMediator());
  bean.setExceptionHandler(getExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.components.widget.BesideWidget getBesidewidget(){
 if(context.get("besidewidget")!=null)
 return (nc.ui.uif2.components.widget.BesideWidget)context.get("besidewidget");
  nc.ui.uif2.components.widget.BesideWidget bean = new nc.ui.uif2.components.widget.BesideWidget();
  context.put("besidewidget",bean);
  bean.setBesideWidgetlets(getManagedList33());
  bean.setContext(getContext());
  bean.setShowAllAction(getShowAllAction());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList33(){  List list = new ArrayList();  list.add(getSavingAmount());  list.add(getPriceForm());  return list;}

public nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel getQueryInfo(){
 if(context.get("queryInfo")!=null)
 return (nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)context.get("queryInfo");
  nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel bean = new nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel();
  context.put("queryInfo",bean);
  bean.setModel(getManageAppModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setModel(getManageAppModel());
  bean.setTangramLayoutRoot(getTBNode_11af47d());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.TBNode getTBNode_11af47d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.TBNode#11af47d")!=null)
 return (nc.ui.uif2.tangramlayout.node.TBNode)context.get("nc.ui.uif2.tangramlayout.node.TBNode#11af47d");
  nc.ui.uif2.tangramlayout.node.TBNode bean = new nc.ui.uif2.tangramlayout.node.TBNode();
  context.put("nc.ui.uif2.tangramlayout.node.TBNode#11af47d",bean);
  bean.setShowMode("CardLayout");
  bean.setTabs(getManagedList34());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList34(){  List list = new ArrayList();  list.add(getHSNode_7871a9());  list.add(getHSNode_1ce889e());  return list;}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_7871a9(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#7871a9")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#7871a9");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#7871a9",bean);
  bean.setLeft(getCNode_ffabf9());
  bean.setRight(getVSNode_31d288());
  bean.setDividerLocation(0.22f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_ffabf9(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#ffabf9")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#ffabf9");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#ffabf9",bean);
  bean.setComponent(getQueryArea());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_31d288(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#31d288")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#31d288");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#31d288",bean);
  bean.setUp(getCNode_99354());
  bean.setDown(getCNode_14c09fb());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_99354(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#99354")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#99354");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#99354",bean);
  bean.setComponent(getQueryInfo());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_14c09fb(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#14c09fb")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#14c09fb");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#14c09fb",bean);
  bean.setName(getI18nFB_d08e29());
  bean.setComponent(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_d08e29(){
 if(context.get("nc.ui.uif2.I18nFB#d08e29")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#d08e29");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#d08e29",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000107");
  bean.setDefaultValue("�б�");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#d08e29",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.HSNode getHSNode_1ce889e(){
 if(context.get("nc.ui.uif2.tangramlayout.node.HSNode#1ce889e")!=null)
 return (nc.ui.uif2.tangramlayout.node.HSNode)context.get("nc.ui.uif2.tangramlayout.node.HSNode#1ce889e");
  nc.ui.uif2.tangramlayout.node.HSNode bean = new nc.ui.uif2.tangramlayout.node.HSNode();
  context.put("nc.ui.uif2.tangramlayout.node.HSNode#1ce889e",bean);
  bean.setLeft(getVSNode_e426bf());
  bean.setRight(getCNode_b7806c());
  bean.setDividerLocation(0.8f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.VSNode getVSNode_e426bf(){
 if(context.get("nc.ui.uif2.tangramlayout.node.VSNode#e426bf")!=null)
 return (nc.ui.uif2.tangramlayout.node.VSNode)context.get("nc.ui.uif2.tangramlayout.node.VSNode#e426bf");
  nc.ui.uif2.tangramlayout.node.VSNode bean = new nc.ui.uif2.tangramlayout.node.VSNode();
  context.put("nc.ui.uif2.tangramlayout.node.VSNode#e426bf",bean);
  bean.setUp(getCNode_870e49());
  bean.setDown(getCNode_112ff8d());
  bean.setDividerLocation(30f);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_870e49(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#870e49")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#870e49");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#870e49",bean);
  bean.setComponent(getCardInfoPnl());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_112ff8d(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#112ff8d")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#112ff8d");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#112ff8d",bean);
  bean.setName(getI18nFB_123cfb3());
  bean.setComponent(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_123cfb3(){
 if(context.get("nc.ui.uif2.I18nFB#123cfb3")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#123cfb3");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#123cfb3",bean);  bean.setResDir("common");
  bean.setResId("UC001-0000106");
  bean.setDefaultValue("��Ƭ");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#123cfb3",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.uif2.tangramlayout.node.CNode getCNode_b7806c(){
 if(context.get("nc.ui.uif2.tangramlayout.node.CNode#b7806c")!=null)
 return (nc.ui.uif2.tangramlayout.node.CNode)context.get("nc.ui.uif2.tangramlayout.node.CNode#b7806c");
  nc.ui.uif2.tangramlayout.node.CNode bean = new nc.ui.uif2.tangramlayout.node.CNode();
  context.put("nc.ui.uif2.tangramlayout.node.CNode#b7806c",bean);
  bean.setComponent(getBesidewidget());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader getBillLazilyLoader(){
 if(context.get("billLazilyLoader")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader)context.get("billLazilyLoader");
  nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader bean = new nc.ui.pubapp.uif2app.lazilyload.DefaultBillLazilyLoader();
  context.put("billLazilyLoader",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.pub.lazilyload.PUCardPanelLazilyLoad getCardLazySupport(){
 if(context.get("cardLazySupport")!=null)
 return (nc.ui.pu.pub.lazilyload.PUCardPanelLazilyLoad)context.get("cardLazySupport");
  nc.ui.pu.pub.lazilyload.PUCardPanelLazilyLoad bean = new nc.ui.pu.pub.lazilyload.PUCardPanelLazilyLoad();
  context.put("cardLazySupport",bean);
  bean.setBillform(getBillFormEditor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pu.pub.lazilyload.PUListPanelLazilyLoad getListLazySupport(){
 if(context.get("listLazySupport")!=null)
 return (nc.ui.pu.pub.lazilyload.PUListPanelLazilyLoad)context.get("listLazySupport");
  nc.ui.pu.pub.lazilyload.PUListPanelLazilyLoad bean = new nc.ui.pu.pub.lazilyload.PUListPanelLazilyLoad();
  context.put("listLazySupport",bean);
  bean.setListView(getListView());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad getLazyActions(){
 if(context.get("lazyActions")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad)context.get("lazyActions");
  nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad bean = new nc.ui.pubapp.uif2app.lazilyload.ActionLazilyLoad();
  context.put("lazyActions",bean);
  bean.setModel(getManageAppModel());
  bean.setActionList(getManagedList35());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList35(){  List list = new ArrayList();  list.add(getPrintAction());  list.add(getPreviewAction());  list.add(getOutputAction());  return list;}

public nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager getLasilyLodadMediator(){
 if(context.get("LasilyLodadMediator")!=null)
 return (nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager)context.get("LasilyLodadMediator");
  nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager bean = new nc.ui.pubapp.uif2app.lazilyload.LazilyLoadManager();
  context.put("LasilyLodadMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setLoader(getBillLazilyLoader());
  bean.setLazilyLoadSupporter(getManagedList36());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList36(){  List list = new ArrayList();  list.add(getCardLazySupport());  list.add(getListLazySupport());  list.add(getLazyActions());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator(){
 if(context.get("userdefAndMarAsstCardPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("userdefAndMarAsstCardPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
  context.put("userdefAndMarAsstCardPreparator",bean);
  bean.setBillDataPrepares(getManagedList37());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList37(){  List list = new ArrayList();  list.add(getUserdefitemPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator(){
 if(context.get("userdefAndMarAsstListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefAndMarAsstListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefAndMarAsstListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList38());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList38(){  List list = new ArrayList();  list.add(getUserdefitemlistPreparator());  list.add(getMarAsstPreparator());  return list;}

public nc.ui.uif2.editor.UserdefitemContainerPreparator getUserdefitemPreparator(){
 if(context.get("userdefitemPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerPreparator)context.get("userdefitemPreparator");
  nc.ui.uif2.editor.UserdefitemContainerPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerPreparator();
  context.put("userdefitemPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList39());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList39(){  List list = new ArrayList();  list.add(getUserdefQueryParam_44f9bf());  list.add(getUserdefQueryParam_44be7b());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_44f9bf(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#44f9bf")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#44f9bf");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#44f9bf",bean);
  bean.setMdfullname("pu.po_order");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_44be7b(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#44be7b")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#44be7b");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#44be7b",bean);
  bean.setMdfullname("pu.po_order_b");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefitemContainerListPreparator getUserdefitemlistPreparator(){
 if(context.get("userdefitemlistPreparator")!=null)
 return (nc.ui.uif2.editor.UserdefitemContainerListPreparator)context.get("userdefitemlistPreparator");
  nc.ui.uif2.editor.UserdefitemContainerListPreparator bean = new nc.ui.uif2.editor.UserdefitemContainerListPreparator();
  context.put("userdefitemlistPreparator",bean);
  bean.setContainer(getUserdefitemContainer());
  bean.setParams(getManagedList40());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList40(){  List list = new ArrayList();  list.add(getUserdefQueryParam_1954955());  list.add(getUserdefQueryParam_1eee286());  return list;}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1954955(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1954955")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1954955");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1954955",bean);
  bean.setMdfullname("pu.po_order");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.editor.UserdefQueryParam getUserdefQueryParam_1eee286(){
 if(context.get("nc.ui.uif2.editor.UserdefQueryParam#1eee286")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("nc.ui.uif2.editor.UserdefQueryParam#1eee286");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("nc.ui.uif2.editor.UserdefQueryParam#1eee286",bean);
  bean.setMdfullname("pu.po_order_b");
  bean.setPos(1);
  bean.setTabcode("material");
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.listener.crossrule.CrossRuleMediator getCrossRuleMediator(){
 if(context.get("crossRuleMediator")!=null)
 return (nc.ui.scmpub.listener.crossrule.CrossRuleMediator)context.get("crossRuleMediator");
  nc.ui.scmpub.listener.crossrule.CrossRuleMediator bean = new nc.ui.scmpub.listener.crossrule.CrossRuleMediator();
  context.put("crossRuleMediator",bean);
  bean.setModel(getManageAppModel());
  bean.setBillType("21");
  bean.init();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarAsstPreparator(){
 if(context.get("marAsstPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marAsstPreparator");
  nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
  context.put("marAsstPreparator",bean);
  bean.setModel(getManageAppModel());
  bean.setContainer(getUserdefitemContainer());
  bean.setPrefix("vfree");
  bean.setMaterialField("pk_material");
  bean.setProjectField("cprojectid");
  bean.setProductorField("cproductorid");
  bean.setCustomerField("casscustid");
  bean.setSignatureField("cffileid");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.UserDefItemContainer getUserdefitemContainer(){
 if(context.get("userdefitemContainer")!=null)
 return (nc.ui.uif2.userdefitem.UserDefItemContainer)context.get("userdefitemContainer");
  nc.ui.uif2.userdefitem.UserDefItemContainer bean = new nc.ui.uif2.userdefitem.UserDefItemContainer();
  context.put("userdefitemContainer",bean);
  bean.setContext(getContext());
  bean.setParams(getManagedList41());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList41(){  List list = new ArrayList();  list.add(getQueryParam_da7cc5());  list.add(getQueryParam_166ac83());  list.add(getQueryParam_66921b());  return list;}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_da7cc5(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#da7cc5")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#da7cc5");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#da7cc5",bean);
  bean.setMdfullname("pu.po_order");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_166ac83(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#166ac83")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#166ac83");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#166ac83",bean);
  bean.setMdfullname("pu.po_order_b");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.uif2.userdefitem.QueryParam getQueryParam_66921b(){
 if(context.get("nc.ui.uif2.userdefitem.QueryParam#66921b")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("nc.ui.uif2.userdefitem.QueryParam#66921b");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("nc.ui.uif2.userdefitem.QueryParam#66921b",bean);
  bean.setRulecode("materialassistant");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller getRemoteCallCombinatorCaller(){
 if(context.get("remoteCallCombinatorCaller")!=null)
 return (nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller)context.get("remoteCallCombinatorCaller");
  nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller bean = new nc.ui.uif2.editor.UIF2RemoteCallCombinatorCaller();
  context.put("remoteCallCombinatorCaller",bean);
  bean.setRemoteCallers(getManagedList42());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList42(){  List list = new ArrayList();  list.add(getQueryTemplateContainer());  list.add(getTemplateContainer());  list.add(getUserdefitemContainer());  list.add(getPfAddInfoLoader());  return list;}

}
