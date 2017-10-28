package nc.ui.ic.m4i;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class GeneralOut_Org extends nc.ui.ic.general.model.config.GeneralCommon{
	private Map<String, Object> context = new HashMap();
public nc.ui.uif2.userdefitem.QueryParam getQueryParams1(){
 if(context.get("queryParams1")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("queryParams1");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("queryParams1",bean);
  bean.setMdfullname("ic.GeneralOutHeadVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParams2(){
 if(context.get("queryParams2")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("queryParams2");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("queryParams2",bean);
  bean.setMdfullname("ic.GeneralOutBodyVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserQueryParams1(){
 if(context.get("userQueryParams1")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("userQueryParams1");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("userQueryParams1",bean);
  bean.setMdfullname("ic.GeneralOutHeadVO");
  bean.setPos(0);
  bean.setPrefix("vdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserQueryParams2(){
 if(context.get("userQueryParams2")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("userQueryParams2");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("userQueryParams2",bean);
  bean.setMdfullname("ic.GeneralOutBodyVO");
  bean.setPos(1);
  bean.setPrefix("vbdef");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.model.ICGenRevisePageService getPageQuery(){
 if(context.get("pageQuery")!=null)
 return (nc.ui.ic.general.model.ICGenRevisePageService)context.get("pageQuery");
  nc.ui.ic.general.model.ICGenRevisePageService bean = new nc.ui.ic.general.model.ICGenRevisePageService();
  context.put("pageQuery",bean);
  bean.setVoClassName("nc.vo.ic.m4i.entity.GeneralOutVO");
  bean.setBillType("4I");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.action.GeneralOutAddFromInAction getAddFromInAction(){
 if(context.get("addFromInAction")!=null)
 return (nc.ui.ic.m4i.action.GeneralOutAddFromInAction)context.get("addFromInAction");
  nc.ui.ic.m4i.action.GeneralOutAddFromInAction bean = new nc.ui.ic.m4i.action.GeneralOutAddFromInAction();
  context.put("addFromInAction",bean);
  bean.setSourceBillType("4A");
  bean.setBtShowName(getI18nFB_a01aaf());
  bean.setDestBillType("4I");
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
  bean.setPfButtonClickContext(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_a01aaf(){
 if(context.get("nc.ui.uif2.I18nFB#a01aaf")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#a01aaf");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#a01aaf",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0752");
  bean.setDefaultValue("入库单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#a01aaf",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.m4i.action.GeneralOutAddFromRM getAddFrom4A62(){
 if(context.get("addFrom4A62")!=null)
 return (nc.ui.ic.m4i.action.GeneralOutAddFromRM)context.get("addFrom4A62");
  nc.ui.ic.m4i.action.GeneralOutAddFromRM bean = new nc.ui.ic.m4i.action.GeneralOutAddFromRM();
  context.put("addFrom4A62",bean);
  bean.setSourceBillType("4A62");
  bean.setSourceBillName(getI18nFB_ae1e32());
  bean.setDestBillType("4I");
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
  bean.setPfButtonClickContext(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_ae1e32(){
 if(context.get("nc.ui.uif2.I18nFB#ae1e32")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#ae1e32");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#ae1e32",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0810");
  bean.setDefaultValue("借用单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#ae1e32",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.m4i.action.GeneralOutAddFromRM getAddFrom4A71(){
 if(context.get("addFrom4A71")!=null)
 return (nc.ui.ic.m4i.action.GeneralOutAddFromRM)context.get("addFrom4A71");
  nc.ui.ic.m4i.action.GeneralOutAddFromRM bean = new nc.ui.ic.m4i.action.GeneralOutAddFromRM();
  context.put("addFrom4A71",bean);
  bean.setSourceBillType("4A71");
  bean.setSourceBillName(getI18nFB_1409a4e());
  bean.setDestBillType("4I");
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
  bean.setPfButtonClickContext(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1409a4e(){
 if(context.get("nc.ui.uif2.I18nFB#1409a4e")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1409a4e");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1409a4e",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0811");
  bean.setDefaultValue("租出单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1409a4e",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.general.action.GeneralAddFromSourceAction getAddFrom4455Action(){
 if(context.get("addFrom4455Action")!=null)
 return (nc.ui.ic.general.action.GeneralAddFromSourceAction)context.get("addFrom4455Action");
  nc.ui.ic.general.action.GeneralAddFromSourceAction bean = new nc.ui.ic.general.action.GeneralAddFromSourceAction();
  context.put("addFrom4455Action",bean);
  bean.setSourceBillType("4455");
  bean.setSourceBillName(getI18nFB_52b826());
  bean.setDestBillType("4I");
  bean.setPfButtonClickContext(1);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_52b826(){
 if(context.get("nc.ui.uif2.I18nFB#52b826")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#52b826");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#52b826",bean);  bean.setResDir("40080801");
  bean.setResId("1400808010002");
  bean.setDefaultValue("出库申请单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#52b826",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.m4i.action.GeneralOutAddFrom4642Action getAddFrom4642Action(){
 if(context.get("addFrom4642Action")!=null)
 return (nc.ui.ic.m4i.action.GeneralOutAddFrom4642Action)context.get("addFrom4642Action");
  nc.ui.ic.m4i.action.GeneralOutAddFrom4642Action bean = new nc.ui.ic.m4i.action.GeneralOutAddFrom4642Action();
  context.put("addFrom4642Action",bean);
  bean.setSourceBillType("4642");
  bean.setSourceBillName(getI18nFB_b7fd52());
  bean.setDestBillType("4I");
  bean.setPfButtonClickContext(1);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_b7fd52(){
 if(context.get("nc.ui.uif2.I18nFB#b7fd52")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#b7fd52");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#b7fd52",bean);  bean.setResDir("4038003_2");
  bean.setResId("2403800202-0108");
  bean.setDefaultValue("促销品申请单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#b7fd52",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.funcnode.ui.action.MenuAction getAddMenu(){
 if(context.get("addMenu")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("addMenu");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("addMenu",bean);
  bean.setCode("MaintainMenu");
  bean.setName(getI18nFB_158420c());
  bean.setActions(getManagedList0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_158420c(){
 if(context.get("nc.ui.uif2.I18nFB#158420c")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#158420c");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#158420c",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0739");
  bean.setDefaultValue("新增");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#158420c",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList0(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("selfAddAction"));  list.add(getSeparatorAction_17b2030());  list.add(getAddFromInAction());  list.add(getAddFrom4A62());  list.add(getAddFrom4A71());  list.add(getAddFrom4455Action());  list.add(getAddFrom4642Action());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_17b2030(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#17b2030")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#17b2030");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#17b2030",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getAssistantFunctionBrowseAction_IN(){
 if(context.get("assistantFunctionBrowseAction_IN")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("assistantFunctionBrowseAction_IN");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("assistantFunctionBrowseAction_IN",bean);
  bean.setCode("NastMngBrowseAction");
  bean.setName(getI18nFB_7e5607());
  bean.setActions(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_7e5607(){
 if(context.get("nc.ui.uif2.I18nFB#7e5607")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#7e5607");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#7e5607",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0741");
  bean.setDefaultValue("辅助功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#7e5607",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList1(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("attachMentMngAction"));  return list;}

public nc.funcnode.ui.action.MenuAction getLinkQryBrowseGroupAction(){
 if(context.get("linkQryBrowseGroupAction")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("linkQryBrowseGroupAction");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("linkQryBrowseGroupAction",bean);
  bean.setCode("linkQryAction");
  bean.setName(getI18nFB_41e5d1());
  bean.setActions(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_41e5d1(){
 if(context.get("nc.ui.uif2.I18nFB#41e5d1")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#41e5d1");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#41e5d1",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0742");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#41e5d1",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList2(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("linkQryAction"));  list.add(getSeparatorAction_5730c9());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("setPieceAtion"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("displayOrhideAction"));  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_5730c9(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#5730c9")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#5730c9");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#5730c9",bean);
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
  bean.setName(getI18nFB_4415bd());
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_4415bd(){
 if(context.get("nc.ui.uif2.I18nFB#4415bd")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#4415bd");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#4415bd",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0742");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#4415bd",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList3(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("setPieceAtion"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("displayOrhideAction"));  return list;}

public nc.ui.ic.general.action.BatchSaveAction getBatchsaveAction(){
 if(context.get("batchsaveAction")!=null)
 return (nc.ui.ic.general.action.BatchSaveAction)context.get("batchsaveAction");
  nc.ui.ic.general.action.BatchSaveAction bean = new nc.ui.ic.general.action.BatchSaveAction();
  context.put("batchsaveAction",bean);
  bean.setAddFromSourceAction(getInAndOutAddFromSpecialAction());
  bean.setBillType("4I");
  bean.setActionName("WRITE");
  bean.setBactch(true);
  bean.setIcUIContext((nc.ui.ic.pub.env.ICUIContext)findBeanInUIF2BeanFactory("icUIContext"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.BatchSignAction getBatchsignAction(){
 if(context.get("batchsignAction")!=null)
 return (nc.ui.ic.general.action.BatchSignAction)context.get("batchsignAction");
  nc.ui.ic.general.action.BatchSignAction bean = new nc.ui.ic.general.action.BatchSignAction();
  context.put("batchsignAction",bean);
  bean.setAddFromSourceAction(getInAndOutAddFromSpecialAction());
  bean.setBillType("4I");
  bean.setActionName("SIGN");
  bean.setBactch(true);
  bean.setIcUIContext((nc.ui.ic.pub.env.ICUIContext)findBeanInUIF2BeanFactory("icUIContext"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.model.GeneralOutModelService getManageModelService(){
 if(context.get("manageModelService")!=null)
 return (nc.ui.ic.m4i.model.GeneralOutModelService)context.get("manageModelService");
  nc.ui.ic.m4i.model.GeneralOutModelService bean = new nc.ui.ic.m4i.model.GeneralOutModelService();
  context.put("manageModelService",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.model.ICGenBizModel getIcBizModel(){
 if(context.get("icBizModel")!=null)
 return (nc.ui.ic.general.model.ICGenBizModel)context.get("icBizModel");
  nc.ui.ic.general.model.ICGenBizModel bean = new nc.ui.ic.general.model.ICGenBizModel();
  context.put("icBizModel",bean);
  bean.setService(getManageModelService());
  bean.setBusinessObjectAdapterFactory((nc.vo.bd.meta.IBDObjectAdapterFactory)findBeanInUIF2BeanFactory("boadatorfactory"));
  bean.setIcUIContext((nc.ui.ic.pub.env.ICUIContext)findBeanInUIF2BeanFactory("icUIContext"));
  bean.setBillType("4I");
  bean.setPowerValidate(true);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.deal.GeneralOutUIProcessorInfo getUIProcesorInfo(){
 if(context.get("UIProcesorInfo")!=null)
 return (nc.ui.ic.m4i.deal.GeneralOutUIProcessorInfo)context.get("UIProcesorInfo");
  nc.ui.ic.m4i.deal.GeneralOutUIProcessorInfo bean = new nc.ui.ic.m4i.deal.GeneralOutUIProcessorInfo();
  context.put("UIProcesorInfo",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.TangramContainer getContainer(){
 if(context.get("container")!=null)
 return (nc.ui.uif2.TangramContainer)context.get("container");
  nc.ui.uif2.TangramContainer bean = new nc.ui.uif2.TangramContainer();
  context.put("container",bean);
  bean.setTangramLayoutRoot((nc.ui.uif2.tangramlayout.node.TangramLayoutNode)findBeanInUIF2BeanFactory("vsnodequery"));
  bean.setModel(getIcBizModel());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add(getActionsOfList());  list.add(getActionsOfCard());  return list;}

public nc.ui.ic.m4i.action.GeneralOutEditAction getEdit4IAction(){
 if(context.get("edit4IAction")!=null)
 return (nc.ui.ic.m4i.action.GeneralOutEditAction)context.get("edit4IAction");
  nc.ui.ic.m4i.action.GeneralOutEditAction bean = new nc.ui.ic.m4i.action.GeneralOutEditAction();
  context.put("edit4IAction",bean);
  bean.setModel(getIcBizModel());
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setInterceptor((nc.ui.uif2.actions.ActionInterceptor)findBeanInUIF2BeanFactory("cardInterceptor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer((nc.ui.uif2.components.ITabbedPaneAwareComponent)findBeanInUIF2BeanFactory("list"));  context.put("actionsOfList",bean);
  bean.setActions(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList5(){  List list = new ArrayList();  list.add(getAddMenu());  list.add(getEdit4IAction());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("deleteAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("copyAction"));  list.add(getSeparatorAction_17bb3f3());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("queryAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("refreshAction"));  list.add(getSeparatorAction_634885());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("maintainMenu"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("signActionMenu"));  list.add(getAssistantFunctionBrowseAction_IN());  list.add(getSeparatorAction_2e13f5());  list.add(getLinkQryBrowseGroupAction());  list.add(getSeparatorAction_1207670());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("importExportAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("printMngAction"));  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_17bb3f3(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#17bb3f3")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#17bb3f3");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#17bb3f3",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_634885(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#634885")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#634885");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#634885",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_2e13f5(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#2e13f5")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#2e13f5");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#2e13f5",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1207670(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1207670")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1207670");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1207670",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
 if(context.get("actionsOfCard")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer((nc.ui.uif2.components.ITabbedPaneAwareComponent)findBeanInUIF2BeanFactory("card"));  context.put("actionsOfCard",bean);
  bean.setModel(getIcBizModel());
  bean.setActions(getManagedList6());
  bean.setEditActions(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList6(){  List list = new ArrayList();  list.add(getAddMenu());  list.add(getEdit4IAction());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("deleteAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("copyAction"));  list.add(getSeparatorAction_3fd998());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("queryAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("refreshCardAction"));  list.add(getSeparatorAction_1e6ab1c());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("maintainMenu"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("signActionMenu"));  list.add(getAssistantFunctionBrowseAction_IN());  list.add(getSeparatorAction_ebcba0());  list.add(getLinkQryBrowseGroupAction());  list.add(getSeparatorAction_85a716());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("importExportAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("printMngAction"));  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_3fd998(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#3fd998")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#3fd998");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#3fd998",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1e6ab1c(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1e6ab1c")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1e6ab1c");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1e6ab1c",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_ebcba0(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#ebcba0")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#ebcba0");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#ebcba0",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_85a716(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#85a716")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#85a716");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#85a716",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSeparatorAction_9ca1ed());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("cancelAction"));  list.add(getSeparatorAction_14b0756());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("fetchAutoAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("pickAutoAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("manufacturedatepickautoaction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("maintainMenu"));  list.add(getSeparatorAction_ced728());  list.add(getLinkQryEditGroupAction());  list.add(getSeparatorAction_18fb0a4());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("importExportAction"));  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_9ca1ed(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#9ca1ed")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#9ca1ed");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#9ca1ed",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_14b0756(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#14b0756")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#14b0756");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#14b0756",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_ced728(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#ced728")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#ced728");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#ced728",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_18fb0a4(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#18fb0a4")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#18fb0a4");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#18fb0a4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.billref.dest.TransferViewProcessor getTransferViewSpecialProcessor(){
 if(context.get("transferViewSpecialProcessor")!=null)
 return (nc.ui.pubapp.billref.dest.TransferViewProcessor)context.get("transferViewSpecialProcessor");
  nc.ui.pubapp.billref.dest.TransferViewProcessor bean = new nc.ui.pubapp.billref.dest.TransferViewProcessor();
  context.put("transferViewSpecialProcessor",bean);
  bean.setList((nc.ui.pubapp.uif2app.view.ShowUpableBillListView)findBeanInUIF2BeanFactory("list"));
  bean.setBillForm((nc.ui.pubapp.uif2app.view.PubShowUpableBillForm)findBeanInUIF2BeanFactory("card"));
  bean.setActionContainer(getActionsOfList());
  bean.setCardActionContainer(getActionsOfCard());
  bean.setSaveAction(getSaveAction());
  bean.setCancelAction((nc.ui.uif2.NCAction)findBeanInUIF2BeanFactory("cancelAction"));
  bean.setTransferLogic(getICDefaultBillDataLogic_961a41());
  bean.setQueryAreaShell((nc.ui.pubapp.uif2app.tangramlayout.UEQueryAreaShell)findBeanInUIF2BeanFactory("queryArea"));
  bean.setQueryInfoToolbarPanel((nc.ui.uif2.tangramlayout.CardLayoutToolbarPanel)findBeanInUIF2BeanFactory("queryInfo"));
  bean.setListProcessor((nc.ui.pubapp.billref.dest.ITransferListViewProcessor)findBeanInUIF2BeanFactory("transferListProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.general.util.ICDefaultBillDataLogic getICDefaultBillDataLogic_961a41(){
 if(context.get("nc.ui.ic.general.util.ICDefaultBillDataLogic#961a41")!=null)
 return (nc.ui.ic.general.util.ICDefaultBillDataLogic)context.get("nc.ui.ic.general.util.ICDefaultBillDataLogic#961a41");
  nc.ui.ic.general.util.ICDefaultBillDataLogic bean = new nc.ui.ic.general.util.ICDefaultBillDataLogic();
  context.put("nc.ui.ic.general.util.ICDefaultBillDataLogic#961a41",bean);
  bean.setBillForm((nc.ui.pubapp.uif2app.view.PubShowUpableBillForm)findBeanInUIF2BeanFactory("card"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.InAndOutAddFromSpecialAction getInAndOutAddFromSpecialAction(){
 if(context.get("inAndOutAddFromSpecialAction")!=null)
 return (nc.ui.ic.general.action.InAndOutAddFromSpecialAction)context.get("inAndOutAddFromSpecialAction");
  nc.ui.ic.general.action.InAndOutAddFromSpecialAction bean = new nc.ui.ic.general.action.InAndOutAddFromSpecialAction();
  context.put("inAndOutAddFromSpecialAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setTransferViewProcessor(getTransferViewSpecialProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.transferComm.GenOutOperProxy getOutProxyProcessor(){
 if(context.get("outProxyProcessor")!=null)
 return (nc.ui.ic.m4i.transferComm.GenOutOperProxy)context.get("outProxyProcessor");
  nc.ui.ic.m4i.transferComm.GenOutOperProxy bean = new nc.ui.ic.m4i.transferComm.GenOutOperProxy();
  context.put("outProxyProcessor",bean);
  bean.setAddFromSourceAction(getInAndOutAddFromSpecialAction());
  bean.setSaveAction(getSaveAction());
  bean.setCancelAction((nc.ui.ic.general.action.GeneralCancelAction)findBeanInUIF2BeanFactory("cancelAction"));
  bean.setSignAction((nc.ui.ic.general.action.GeneralSignAction)findBeanInUIF2BeanFactory("signAction"));
  bean.setDelRowAction((nc.ui.pubapp.uif2app.actions.BodyDelLineAction)findBeanInUIF2BeanFactory("bodyDelLineAction"));
  bean.setPickAutoAction((nc.ui.ic.general.action.PickAutoAction)findBeanInUIF2BeanFactory("pickAutoAction"));
  bean.setLocationMngAction((nc.ui.ic.general.action.GeneralLocationMngAction)findBeanInUIF2BeanFactory("locationMngAction"));
  bean.setSerialMngAction((nc.ui.ic.general.action.GeneralSerialMngAction)findBeanInUIF2BeanFactory("serialMngAction"));
  bean.setBarcodeAction((nc.ui.ic.general.action.GeneralLocationAndSerialMngAction)findBeanInUIF2BeanFactory("locationAndSerialMngAction"));
  bean.setBatchsaveAction(getBatchsaveAction());
  bean.setBatchsignAction(getBatchsignAction());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.transferComm.InitTransferData getInitProcessor(){
 if(context.get("initProcessor")!=null)
 return (nc.ui.ic.m4i.transferComm.InitTransferData)context.get("initProcessor");
  nc.ui.ic.m4i.transferComm.InitTransferData bean = new nc.ui.ic.m4i.transferComm.InitTransferData();
  context.put("initProcessor",bean);
  bean.setOutOperProxy(getOutProxyProcessor());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setProcessorMap(getManagedMap0());
  bean.setQueryAction((nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)findBeanInUIF2BeanFactory("queryAction"));
  bean.setModel(getIcBizModel());
  bean.setContext((nc.vo.uif2.LoginContext)findBeanInUIF2BeanFactory("context"));
  bean.setService(getOutLinkQuery());
  bean.setVoClassName("nc.vo.ic.m4i.entity.GeneralOutVO");
  bean.setAutoShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent)findBeanInUIF2BeanFactory("card"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("99",getInitProcessor());  return map;}

public nc.ui.ic.m4a.handler.DefaultExceptionHanlerFor4A getMyExceptionHandler(){
 if(context.get("MyExceptionHandler")!=null)
 return (nc.ui.ic.m4a.handler.DefaultExceptionHanlerFor4A)context.get("MyExceptionHandler");
  nc.ui.ic.m4a.handler.DefaultExceptionHanlerFor4A bean = new nc.ui.ic.m4a.handler.DefaultExceptionHanlerFor4A();
  context.put("MyExceptionHandler",bean);
  bean.setContext((nc.vo.uif2.LoginContext)findBeanInUIF2BeanFactory("context"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.general.action.GeneralSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.ic.general.action.GeneralSaveAction)context.get("saveAction");
  nc.ui.ic.general.action.GeneralSaveAction bean = new nc.ui.ic.general.action.GeneralSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getIcBizModel());
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setEditor((nc.ui.uif2.editor.IEditor)findBeanInUIF2BeanFactory("card"));
  bean.setActionName("WRITE");
  bean.setValidationService((nc.bs.uif2.validation.IValidationService)findBeanInUIF2BeanFactory("validateService"));
  bean.setExceptionHandler(getMyExceptionHandler());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.outbound.bizcheck.OutLinkQuery getOutLinkQuery(){
 if(context.get("outLinkQuery")!=null)
 return (nc.ui.ic.outbound.bizcheck.OutLinkQuery)context.get("outLinkQuery");
  nc.ui.ic.outbound.bizcheck.OutLinkQuery bean = new nc.ui.ic.outbound.bizcheck.OutLinkQuery();
  context.put("outLinkQuery",bean);
  bean.setVoClassName("nc.vo.ic.m4i.entity.GeneralOutVO");
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.handler.CwarehouseidHandlerFor4I getCwarehouseidHandler(){
 if(context.get("cwarehouseidHandler")!=null)
 return (nc.ui.ic.m4i.handler.CwarehouseidHandlerFor4I)context.get("cwarehouseidHandler");
  nc.ui.ic.m4i.handler.CwarehouseidHandlerFor4I bean = new nc.ui.ic.m4i.handler.CwarehouseidHandlerFor4I();
  context.put("cwarehouseidHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.handler.CvmivenderidHandler getCvmivenderidHandler(){
 if(context.get("cvmivenderidHandler")!=null)
 return (nc.ui.ic.m4i.handler.CvmivenderidHandler)context.get("cvmivenderidHandler");
  nc.ui.ic.m4i.handler.CvmivenderidHandler bean = new nc.ui.ic.m4i.handler.CvmivenderidHandler();
  context.put("cvmivenderidHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.handler.CtplcustomeridHandler getCtplcustomeridHandler(){
 if(context.get("ctplcustomeridHandler")!=null)
 return (nc.ui.ic.m4i.handler.CtplcustomeridHandler)context.get("ctplcustomeridHandler");
  nc.ui.ic.m4i.handler.CtplcustomeridHandler bean = new nc.ui.ic.m4i.handler.CtplcustomeridHandler();
  context.put("ctplcustomeridHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.handler.CMaterialvidHandlerFor4I getCmaterialvidHandler(){
 if(context.get("cmaterialvidHandler")!=null)
 return (nc.ui.ic.m4i.handler.CMaterialvidHandlerFor4I)context.get("cmaterialvidHandler");
  nc.ui.ic.m4i.handler.CMaterialvidHandlerFor4I bean = new nc.ui.ic.m4i.handler.CMaterialvidHandlerFor4I();
  context.put("cmaterialvidHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.handler.VreceiveaddressHandlerFor4I getVreceiveaddressHandler(){
 if(context.get("vreceiveaddressHandler")!=null)
 return (nc.ui.ic.m4i.handler.VreceiveaddressHandlerFor4I)context.get("vreceiveaddressHandler");
  nc.ui.ic.m4i.handler.VreceiveaddressHandlerFor4I bean = new nc.ui.ic.m4i.handler.VreceiveaddressHandlerFor4I();
  context.put("vreceiveaddressHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4i.handler.GeneralPkorgHandler getPk_orgHandler(){
 if(context.get("pk_orgHandler")!=null)
 return (nc.ui.ic.m4i.handler.GeneralPkorgHandler)context.get("pk_orgHandler");
  nc.ui.ic.m4i.handler.GeneralPkorgHandler bean = new nc.ui.ic.m4i.handler.GeneralPkorgHandler();
  context.put("pk_orgHandler",bean);
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setContext((nc.ui.ic.pub.env.ICUIContext)findBeanInUIF2BeanFactory("icUIContext"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap getChildCardEditHandlerMap(){
 if(context.get("childCardEditHandlerMap")!=null)
 return (nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap)context.get("childCardEditHandlerMap");
  nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap bean = new nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap();
  context.put("childCardEditHandlerMap",bean);
  bean.setHandlerMap(getManagedMap1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put("cvmivenderid",getCvmivenderidHandler());  map.put("ctplcustomerid",getCtplcustomeridHandler());  map.put("cdptvid",getCdptvidHandler_e5b89());  map.put("cprojectid",getCprojectidHandlerFor4I_9ba975());  map.put("cprojecttaskid",getCprojecttaskidHandlerFor4I_1925734());  map.put("ctrantypeid",getCtrantypeidHandlerFor4I_9b271d());  map.put("vreceiveaddress",getVreceiveaddressHandler());  return map;}

private nc.ui.ic.m4i.handler.CdptvidHandler getCdptvidHandler_e5b89(){
 if(context.get("nc.ui.ic.m4i.handler.CdptvidHandler#e5b89")!=null)
 return (nc.ui.ic.m4i.handler.CdptvidHandler)context.get("nc.ui.ic.m4i.handler.CdptvidHandler#e5b89");
  nc.ui.ic.m4i.handler.CdptvidHandler bean = new nc.ui.ic.m4i.handler.CdptvidHandler();
  context.put("nc.ui.ic.m4i.handler.CdptvidHandler#e5b89",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4i.handler.CprojectidHandlerFor4I getCprojectidHandlerFor4I_9ba975(){
 if(context.get("nc.ui.ic.m4i.handler.CprojectidHandlerFor4I#9ba975")!=null)
 return (nc.ui.ic.m4i.handler.CprojectidHandlerFor4I)context.get("nc.ui.ic.m4i.handler.CprojectidHandlerFor4I#9ba975");
  nc.ui.ic.m4i.handler.CprojectidHandlerFor4I bean = new nc.ui.ic.m4i.handler.CprojectidHandlerFor4I();
  context.put("nc.ui.ic.m4i.handler.CprojectidHandlerFor4I#9ba975",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4i.handler.CprojecttaskidHandlerFor4I getCprojecttaskidHandlerFor4I_1925734(){
 if(context.get("nc.ui.ic.m4i.handler.CprojecttaskidHandlerFor4I#1925734")!=null)
 return (nc.ui.ic.m4i.handler.CprojecttaskidHandlerFor4I)context.get("nc.ui.ic.m4i.handler.CprojecttaskidHandlerFor4I#1925734");
  nc.ui.ic.m4i.handler.CprojecttaskidHandlerFor4I bean = new nc.ui.ic.m4i.handler.CprojecttaskidHandlerFor4I();
  context.put("nc.ui.ic.m4i.handler.CprojecttaskidHandlerFor4I#1925734",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4i.handler.CtrantypeidHandlerFor4I getCtrantypeidHandlerFor4I_9b271d(){
 if(context.get("nc.ui.ic.m4i.handler.CtrantypeidHandlerFor4I#9b271d")!=null)
 return (nc.ui.ic.m4i.handler.CtrantypeidHandlerFor4I)context.get("nc.ui.ic.m4i.handler.CtrantypeidHandlerFor4I#9b271d");
  nc.ui.ic.m4i.handler.CtrantypeidHandlerFor4I bean = new nc.ui.ic.m4i.handler.CtrantypeidHandlerFor4I();
  context.put("nc.ui.ic.m4i.handler.CtrantypeidHandlerFor4I#9b271d",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.PfAddInfoLoader getPfAddInfoLoader(){
 if(context.get("pfAddInfoLoader")!=null)
 return (nc.ui.pubapp.uif2app.actions.PfAddInfoLoader)context.get("pfAddInfoLoader");
  nc.ui.pubapp.uif2app.actions.PfAddInfoLoader bean = new nc.ui.pubapp.uif2app.actions.PfAddInfoLoader();
  context.put("pfAddInfoLoader",bean);
  bean.setBillType("4I");
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
