package nc.ui.ic.m4d;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class MaterialOut_Org extends nc.ui.ic.general.model.config.GeneralCommon{
	private Map<String, Object> context = new HashMap();
public nc.ui.uif2.userdefitem.QueryParam getQueryParams1(){
 if(context.get("queryParams1")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("queryParams1");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("queryParams1",bean);
  bean.setMdfullname("ic.MaterialOutHeadVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParams2(){
 if(context.get("queryParams2")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("queryParams2");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("queryParams2",bean);
  bean.setMdfullname("ic.MaterialOutBodyVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserQueryParams1(){
 if(context.get("userQueryParams1")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("userQueryParams1");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("userQueryParams1",bean);
  bean.setMdfullname("ic.MaterialOutHeadVO");
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
  bean.setMdfullname("ic.MaterialOutBodyVO");
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
  bean.setVoClassName("nc.vo.ic.m4d.entity.MaterialOutVO");
  bean.setBillType("4D");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4d.model.MaterialOutModelService getManageModelService(){
 if(context.get("manageModelService")!=null)
 return (nc.ui.ic.m4d.model.MaterialOutModelService)context.get("manageModelService");
  nc.ui.ic.m4d.model.MaterialOutModelService bean = new nc.ui.ic.m4d.model.MaterialOutModelService();
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
  bean.setBillType("4D");
  bean.setPowerValidate(true);
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

public nc.ui.ic.m4d.deal.MaterialUIProcessorInfo getUIProcesorInfo(){
 if(context.get("UIProcesorInfo")!=null)
 return (nc.ui.ic.m4d.deal.MaterialUIProcessorInfo)context.get("UIProcesorInfo");
  nc.ui.ic.m4d.deal.MaterialUIProcessorInfo bean = new nc.ui.ic.m4d.deal.MaterialUIProcessorInfo();
  context.put("UIProcesorInfo",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

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

public nc.ui.ic.m4d.action.MaterialOutAddFromInAction getAddFromInAction(){
 if(context.get("addFromInAction")!=null)
 return (nc.ui.ic.m4d.action.MaterialOutAddFromInAction)context.get("addFromInAction");
  nc.ui.ic.m4d.action.MaterialOutAddFromInAction bean = new nc.ui.ic.m4d.action.MaterialOutAddFromInAction();
  context.put("addFromInAction",bean);
  bean.setSourceBillType("4A");
  bean.setBtShowName(getI18nFB_19e92e0());
  bean.setDestBillType("4D");
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
  bean.setPfButtonClickContext(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_19e92e0(){
 if(context.get("nc.ui.uif2.I18nFB#19e92e0")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#19e92e0");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#19e92e0",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0752");
  bean.setDefaultValue("入库单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#19e92e0",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.m4d.action.MaterialOutAddFrom4B36Action getAddFrom4B36Action(){
 if(context.get("addFrom4B36Action")!=null)
 return (nc.ui.ic.m4d.action.MaterialOutAddFrom4B36Action)context.get("addFrom4B36Action");
  nc.ui.ic.m4d.action.MaterialOutAddFrom4B36Action bean = new nc.ui.ic.m4d.action.MaterialOutAddFrom4B36Action();
  context.put("addFrom4B36Action",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setSourceBillType("4B36");
  bean.setSourceBillName(getI18nFB_1bce338());
  bean.setDestBillType("4D");
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
  bean.setPfButtonClickContext(1);
  bean.setReturnFlag("Blue_Bill");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1bce338(){
 if(context.get("nc.ui.uif2.I18nFB#1bce338")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1bce338");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1bce338",bean);  bean.setResDir("funcode");
  bean.setResId("btn_40080804_Ref4B36");
  bean.setDefaultValue("参照维修工单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1bce338",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.m4d.action.MaterialOutAddFrom4B36Action getAddFrom4B36ForBackAction(){
 if(context.get("addFrom4B36ForBackAction")!=null)
 return (nc.ui.ic.m4d.action.MaterialOutAddFrom4B36Action)context.get("addFrom4B36ForBackAction");
  nc.ui.ic.m4d.action.MaterialOutAddFrom4B36Action bean = new nc.ui.ic.m4d.action.MaterialOutAddFrom4B36Action();
  context.put("addFrom4B36ForBackAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setSourceBillType("4B36");
  bean.setBtShowName(getI18nFB_1e99239());
  bean.setDestBillType("4D");
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
  bean.setPfButtonClickContext(1);
  bean.setReturnFlag("Red_Bill");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1e99239(){
 if(context.get("nc.ui.uif2.I18nFB#1e99239")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1e99239");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1e99239",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0753");
  bean.setDefaultValue("维修工单退库");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1e99239",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction getAddFrom422XAction(){
 if(context.get("addFrom422XAction")!=null)
 return (nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction)context.get("addFrom422XAction");
  nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction bean = new nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction();
  context.put("addFrom422XAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setSourceBillType("422X");
  bean.setSourceBillName(getI18nFB_17ee144());
  bean.setDestBillType("4D");
  bean.setPfButtonClickContext(1);
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_17ee144(){
 if(context.get("nc.ui.uif2.I18nFB#17ee144")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#17ee144");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#17ee144",bean);  bean.setResDir("4001002_0");
  bean.setResId("04001002-0493");
  bean.setDefaultValue("物资需求申请单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#17ee144",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction getAddFrom4A60Action(){
 if(context.get("addFrom4A60Action")!=null)
 return (nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction)context.get("addFrom4A60Action");
  nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction bean = new nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction();
  context.put("addFrom4A60Action",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setSourceBillType("4A60");
  bean.setSourceBillName(getI18nFB_f75b01());
  bean.setDestBillType("4D");
  bean.setPfButtonClickContext(1);
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_f75b01(){
 if(context.get("nc.ui.uif2.I18nFB#f75b01")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#f75b01");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#f75b01",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0809");
  bean.setDefaultValue("领用单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#f75b01",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction getAddFrom4455Action(){
 if(context.get("addFrom4455Action")!=null)
 return (nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction)context.get("addFrom4455Action");
  nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction bean = new nc.ui.ic.m4d.action.MaterialOutAddFromSourceAction();
  context.put("addFrom4455Action",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setSourceBillType("4455");
  bean.setSourceBillName(getI18nFB_177a54d());
  bean.setDestBillType("4D");
  bean.setPfButtonClickContext(1);
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_177a54d(){
 if(context.get("nc.ui.uif2.I18nFB#177a54d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#177a54d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#177a54d",bean);  bean.setResDir("40080801");
  bean.setResId("1400808010002");
  bean.setDefaultValue("出库申请单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#177a54d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.m4d.action.RatioOutAction getRatiooutAction(){
 if(context.get("ratiooutAction")!=null)
 return (nc.ui.ic.m4d.action.RatioOutAction)context.get("ratiooutAction");
  nc.ui.ic.m4d.action.RatioOutAction bean = new nc.ui.ic.m4d.action.RatioOutAction();
  context.put("ratiooutAction",bean);
  bean.setEditModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4d.action.DoEquipCardAction getDoEquipCardAction(){
 if(context.get("doEquipCardAction")!=null)
 return (nc.ui.ic.m4d.action.DoEquipCardAction)context.get("doEquipCardAction");
  nc.ui.ic.m4d.action.DoEquipCardAction bean = new nc.ui.ic.m4d.action.DoEquipCardAction();
  context.put("doEquipCardAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4d.action.CancelEquipCardAction getCancelEquipCardAction(){
 if(context.get("cancelEquipCardAction")!=null)
 return (nc.ui.ic.m4d.action.CancelEquipCardAction)context.get("cancelEquipCardAction");
  nc.ui.ic.m4d.action.CancelEquipCardAction bean = new nc.ui.ic.m4d.action.CancelEquipCardAction();
  context.put("cancelEquipCardAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4d.action.SumPrintAction getSumPrintAction(){
 if(context.get("sumPrintAction")!=null)
 return (nc.ui.ic.m4d.action.SumPrintAction)context.get("sumPrintAction");
  nc.ui.ic.m4d.action.SumPrintAction bean = new nc.ui.ic.m4d.action.SumPrintAction();
  context.put("sumPrintAction",bean);
  bean.setModel(getIcBizModel());
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setPrintProcessor((nc.ui.pubapp.uif2app.actions.MetaDataBasedPrintAction.IBeforePrintDataProcess)findBeanInUIF2BeanFactory("printProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getAddMenu(){
 if(context.get("addMenu")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("addMenu");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("addMenu",bean);
  bean.setCode("MaintainMenu");
  bean.setName(getI18nFB_584d5());
  bean.setActions(getManagedList1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_584d5(){
 if(context.get("nc.ui.uif2.I18nFB#584d5")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#584d5");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#584d5",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0739");
  bean.setDefaultValue("新增");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#584d5",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList1(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("selfAddAction"));  list.add(getSeparatorAction_53ef40());  list.add(getAddFromInAction());  list.add(getAddFrom422XAction());  list.add(getAddFrom4455Action());  list.add(getAddFrom4B36Action());  list.add(getAddFrom4A60Action());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_53ef40(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#53ef40")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#53ef40");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#53ef40",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getAssistantFunctionEditAction_OUT(){
 if(context.get("assistantFunctionEditAction_OUT")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("assistantFunctionEditAction_OUT");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("assistantFunctionEditAction_OUT",bean);
  bean.setCode("NastMngEditAction");
  bean.setName(getI18nFB_806668());
  bean.setActions(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_806668(){
 if(context.get("nc.ui.uif2.I18nFB#806668")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#806668");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#806668",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0743");
  bean.setDefaultValue("关联功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#806668",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList2(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("displayOrhideAction"));  return list;}

public nc.funcnode.ui.action.GroupAction getPrintMngAction(){
 if(context.get("printMngAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printMngAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printMngAction",bean);
  bean.setCode("printMngAction");
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList3(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("templatePrintAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("templatePreviewAction"));  list.add(getSeparatorAction_1c35f22());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("printQAAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("mergerShowAction"));  list.add(getSumPrintAction());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("printLocAction"));  list.add(getPrintCountQueryAction());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("dirPrintBarcodeAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("printBarcodeAction"));  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1c35f22(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1c35f22")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1c35f22");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1c35f22",bean);
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
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getAssistantFunctionBrowseAction_OUT(){
 if(context.get("assistantFunctionBrowseAction_OUT")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("assistantFunctionBrowseAction_OUT");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("assistantFunctionBrowseAction_OUT",bean);
  bean.setCode("NastMngBrowseAction");
  bean.setName(getI18nFB_19ac072());
  bean.setActions(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_19ac072(){
 if(context.get("nc.ui.uif2.I18nFB#19ac072")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#19ac072");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#19ac072",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0741");
  bean.setDefaultValue("辅助功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#19ac072",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList4(){  List list = new ArrayList();  list.add(getRatiooutAction());  list.add(getAddFrom4B36ForBackAction());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("attachMentMngAction"));  return list;}

public nc.funcnode.ui.action.MenuAction getRelatFunctionBrowseAction_OUT(){
 if(context.get("relatFunctionBrowseAction_OUT")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("relatFunctionBrowseAction_OUT");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("relatFunctionBrowseAction_OUT",bean);
  bean.setCode("NrelatMngEditAction");
  bean.setName(getI18nFB_d07f3d());
  bean.setActions(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_d07f3d(){
 if(context.get("nc.ui.uif2.I18nFB#d07f3d")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#d07f3d");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#d07f3d",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0743");
  bean.setDefaultValue("关联功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#d07f3d",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList5(){  List list = new ArrayList();  list.add(getDoEquipCardAction());  list.add(getCancelEquipCardAction());  return list;}

public nc.funcnode.ui.action.MenuAction getLinkQryBrowseGroupAction(){
 if(context.get("linkQryBrowseGroupAction")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("linkQryBrowseGroupAction");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("linkQryBrowseGroupAction",bean);
  bean.setCode("linkQryAction");
  bean.setName(getI18nFB_51b8ae());
  bean.setActions(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_51b8ae(){
 if(context.get("nc.ui.uif2.I18nFB#51b8ae")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#51b8ae");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#51b8ae",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0742");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#51b8ae",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList6(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("linkQryAction"));  list.add(getSeparatorAction_133d3c8());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("displayOrhideAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("setPieceAtion"));  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_133d3c8(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#133d3c8")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#133d3c8");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#133d3c8",bean);
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
  bean.setName(getI18nFB_194f744());
  bean.setActions(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_194f744(){
 if(context.get("nc.ui.uif2.I18nFB#194f744")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#194f744");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#194f744",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0742");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#194f744",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList7(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("displayOrhideAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("setPieceAtion"));  return list;}

public nc.ui.ic.m4d.action.MaterialOutCopyAction getCopyAction(){
 if(context.get("copyAction")!=null)
 return (nc.ui.ic.m4d.action.MaterialOutCopyAction)context.get("copyAction");
  nc.ui.ic.m4d.action.MaterialOutCopyAction bean = new nc.ui.ic.m4d.action.MaterialOutCopyAction();
  context.put("copyAction",bean);
  bean.setModel(getIcBizModel());
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setEditor((nc.ui.pubapp.uif2app.view.BillForm)findBeanInUIF2BeanFactory("card"));
  bean.setInterceptor((nc.ui.uif2.actions.ActionInterceptor)findBeanInUIF2BeanFactory("cardInterceptor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer((nc.ui.uif2.components.ITabbedPaneAwareComponent)findBeanInUIF2BeanFactory("list"));  context.put("actionsOfList",bean);
  bean.setActions(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getAddMenu());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("editAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("deleteAction"));  list.add(getCopyAction());  list.add(getSeparatorAction_f59b67());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("queryAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("refreshAction"));  list.add(getSeparatorAction_ad93fe());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("maintainMenu"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("signActionMenu"));  list.add(getAssistantFunctionBrowseAction_OUT());  list.add(getSeparatorAction_1cf1b81());  list.add(getLinkQryBrowseGroupAction());  list.add(getSeparatorAction_16f3b4());  list.add(getRelatFunctionBrowseAction_OUT());  list.add(getSeparatorAction_d9eafd());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("importExportAction"));  list.add(getPrintMngAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_f59b67(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#f59b67")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#f59b67");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#f59b67",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_ad93fe(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#ad93fe")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#ad93fe");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#ad93fe",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1cf1b81(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1cf1b81")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1cf1b81");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1cf1b81",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_16f3b4(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#16f3b4")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#16f3b4");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#16f3b4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_d9eafd(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#d9eafd")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#d9eafd");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#d9eafd",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
 if(context.get("actionsOfCard")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer((nc.ui.uif2.components.ITabbedPaneAwareComponent)findBeanInUIF2BeanFactory("card"));  context.put("actionsOfCard",bean);
  bean.setModel(getIcBizModel());
  bean.setActions(getManagedList9());
  bean.setEditActions(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getAddMenu());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("editAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("deleteAction"));  list.add(getCopyAction());  list.add(getSeparatorAction_d2b7aa());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("queryAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("refreshCardAction"));  list.add(getSeparatorAction_182ea3());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("maintainMenu"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("signActionMenu"));  list.add(getAssistantFunctionBrowseAction_OUT());  list.add(getSeparatorAction_19dd9a3());  list.add(getLinkQryBrowseGroupAction());  list.add(getSeparatorAction_3b7cf6());  list.add(getRelatFunctionBrowseAction_OUT());  list.add(getSeparatorAction_18904da());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("importExportAction"));  list.add(getPrintMngAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_d2b7aa(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#d2b7aa")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#d2b7aa");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#d2b7aa",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_182ea3(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#182ea3")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#182ea3");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#182ea3",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_19dd9a3(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#19dd9a3")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#19dd9a3");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#19dd9a3",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_3b7cf6(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#3b7cf6")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#3b7cf6");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#3b7cf6",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_18904da(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#18904da")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#18904da");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#18904da",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("saveAction"));  list.add(getSeparatorAction_9dabfa());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("cancelAction"));  list.add(getSeparatorAction_185da9d());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("fetchAutoAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("pickAutoAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("manufacturedatepickautoaction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("maintainMenu"));  list.add(getSeparatorAction_d39b06());  list.add(getLinkQryEditGroupAction());  list.add(getSeparatorAction_66c516());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("importExportAction"));  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_9dabfa(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#9dabfa")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#9dabfa");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#9dabfa",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_185da9d(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#185da9d")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#185da9d");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#185da9d",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_d39b06(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#d39b06")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#d39b06");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#d39b06",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_66c516(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#66c516")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#66c516");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#66c516",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4d.handler.Pk_orgHandlerFor4D getPk_orgHandler(){
 if(context.get("pk_orgHandler")!=null)
 return (nc.ui.ic.m4d.handler.Pk_orgHandlerFor4D)context.get("pk_orgHandler");
  nc.ui.ic.m4d.handler.Pk_orgHandlerFor4D bean = new nc.ui.ic.m4d.handler.Pk_orgHandlerFor4D();
  context.put("pk_orgHandler",bean);
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setContext((nc.ui.ic.pub.env.ICUIContext)findBeanInUIF2BeanFactory("icUIContext"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4d.handler.CwarehouseidHandlerFor4D getCwarehouseidHandler(){
 if(context.get("cwarehouseidHandler")!=null)
 return (nc.ui.ic.m4d.handler.CwarehouseidHandlerFor4D)context.get("cwarehouseidHandler");
  nc.ui.ic.m4d.handler.CwarehouseidHandlerFor4D bean = new nc.ui.ic.m4d.handler.CwarehouseidHandlerFor4D();
  context.put("cwarehouseidHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4d.handler.CMaterialSubstiHandlerFor4D getCmaterialvidHandler(){
 if(context.get("cmaterialvidHandler")!=null)
 return (nc.ui.ic.m4d.handler.CMaterialSubstiHandlerFor4D)context.get("cmaterialvidHandler");
  nc.ui.ic.m4d.handler.CMaterialSubstiHandlerFor4D bean = new nc.ui.ic.m4d.handler.CMaterialSubstiHandlerFor4D();
  context.put("cmaterialvidHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4d.handler.CmffileidHandlerFor4D getCmffileidHandler(){
 if(context.get("cmffileidHandler")!=null)
 return (nc.ui.ic.m4d.handler.CmffileidHandlerFor4D)context.get("cmffileidHandler");
  nc.ui.ic.m4d.handler.CmffileidHandlerFor4D bean = new nc.ui.ic.m4d.handler.CmffileidHandlerFor4D();
  context.put("cmffileidHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap getChildCardEditHandlerMap(){
 if(context.get("childCardEditHandlerMap")!=null)
 return (nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap)context.get("childCardEditHandlerMap");
  nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap bean = new nc.ui.ic.pub.handler.card.ICCardEditEventHandlerMap();
  context.put("childCardEditHandlerMap",bean);
  bean.setHandlerMap(getManagedMap0());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap0(){  Map map = new HashMap();  map.put("cmffileid",getCmffileidHandler());  map.put("cdrawcalbodyvid",getCdrawcalbodyvidHandler_11534e4());  map.put("cdrawcalbodyoid",getCdrawcalbodyoidHandler_1438e52());  map.put("cdrawwarehouseid",getCdrawwarehouseidHandler_34ce0b());  map.put("cbizid",getCbizidHandeler_c346bf());  map.put("cdptvid",getCdptvidHandler_59a910());  map.put("cworkcenterid",getCworkcenteridHandler_6a9a3e());  map.put("cprojectid",getCprojectidHandlerFor4D_b47547());  map.put("cprojecttaskid",getCprojecttaskidHandlerFor4D_17f9c2b());  map.put("ccostcenterid",getCcostcenteridHandler_e5d406());  map.put("ccostobject",getCcostobjectHandler_c2ff68());  map.put("crcid",getCrcidHandelerFor4D_e15a5());  map.put("pk_cbsnode",getPk_cbsnodeHandlerFor4D_af35ee());  map.put("cconstructvendorid",getCconstructvendoridHandler_fda1bc());  return map;}

private nc.ui.ic.m4d.handler.CdrawcalbodyvidHandler getCdrawcalbodyvidHandler_11534e4(){
 if(context.get("nc.ui.ic.m4d.handler.CdrawcalbodyvidHandler#11534e4")!=null)
 return (nc.ui.ic.m4d.handler.CdrawcalbodyvidHandler)context.get("nc.ui.ic.m4d.handler.CdrawcalbodyvidHandler#11534e4");
  nc.ui.ic.m4d.handler.CdrawcalbodyvidHandler bean = new nc.ui.ic.m4d.handler.CdrawcalbodyvidHandler();
  context.put("nc.ui.ic.m4d.handler.CdrawcalbodyvidHandler#11534e4",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CdrawcalbodyoidHandler getCdrawcalbodyoidHandler_1438e52(){
 if(context.get("nc.ui.ic.m4d.handler.CdrawcalbodyoidHandler#1438e52")!=null)
 return (nc.ui.ic.m4d.handler.CdrawcalbodyoidHandler)context.get("nc.ui.ic.m4d.handler.CdrawcalbodyoidHandler#1438e52");
  nc.ui.ic.m4d.handler.CdrawcalbodyoidHandler bean = new nc.ui.ic.m4d.handler.CdrawcalbodyoidHandler();
  context.put("nc.ui.ic.m4d.handler.CdrawcalbodyoidHandler#1438e52",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CdrawwarehouseidHandler getCdrawwarehouseidHandler_34ce0b(){
 if(context.get("nc.ui.ic.m4d.handler.CdrawwarehouseidHandler#34ce0b")!=null)
 return (nc.ui.ic.m4d.handler.CdrawwarehouseidHandler)context.get("nc.ui.ic.m4d.handler.CdrawwarehouseidHandler#34ce0b");
  nc.ui.ic.m4d.handler.CdrawwarehouseidHandler bean = new nc.ui.ic.m4d.handler.CdrawwarehouseidHandler();
  context.put("nc.ui.ic.m4d.handler.CdrawwarehouseidHandler#34ce0b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CbizidHandeler getCbizidHandeler_c346bf(){
 if(context.get("nc.ui.ic.m4d.handler.CbizidHandeler#c346bf")!=null)
 return (nc.ui.ic.m4d.handler.CbizidHandeler)context.get("nc.ui.ic.m4d.handler.CbizidHandeler#c346bf");
  nc.ui.ic.m4d.handler.CbizidHandeler bean = new nc.ui.ic.m4d.handler.CbizidHandeler();
  context.put("nc.ui.ic.m4d.handler.CbizidHandeler#c346bf",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CdptvidHandler getCdptvidHandler_59a910(){
 if(context.get("nc.ui.ic.m4d.handler.CdptvidHandler#59a910")!=null)
 return (nc.ui.ic.m4d.handler.CdptvidHandler)context.get("nc.ui.ic.m4d.handler.CdptvidHandler#59a910");
  nc.ui.ic.m4d.handler.CdptvidHandler bean = new nc.ui.ic.m4d.handler.CdptvidHandler();
  context.put("nc.ui.ic.m4d.handler.CdptvidHandler#59a910",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CworkcenteridHandler getCworkcenteridHandler_6a9a3e(){
 if(context.get("nc.ui.ic.m4d.handler.CworkcenteridHandler#6a9a3e")!=null)
 return (nc.ui.ic.m4d.handler.CworkcenteridHandler)context.get("nc.ui.ic.m4d.handler.CworkcenteridHandler#6a9a3e");
  nc.ui.ic.m4d.handler.CworkcenteridHandler bean = new nc.ui.ic.m4d.handler.CworkcenteridHandler();
  context.put("nc.ui.ic.m4d.handler.CworkcenteridHandler#6a9a3e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CprojectidHandlerFor4D getCprojectidHandlerFor4D_b47547(){
 if(context.get("nc.ui.ic.m4d.handler.CprojectidHandlerFor4D#b47547")!=null)
 return (nc.ui.ic.m4d.handler.CprojectidHandlerFor4D)context.get("nc.ui.ic.m4d.handler.CprojectidHandlerFor4D#b47547");
  nc.ui.ic.m4d.handler.CprojectidHandlerFor4D bean = new nc.ui.ic.m4d.handler.CprojectidHandlerFor4D();
  context.put("nc.ui.ic.m4d.handler.CprojectidHandlerFor4D#b47547",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CprojecttaskidHandlerFor4D getCprojecttaskidHandlerFor4D_17f9c2b(){
 if(context.get("nc.ui.ic.m4d.handler.CprojecttaskidHandlerFor4D#17f9c2b")!=null)
 return (nc.ui.ic.m4d.handler.CprojecttaskidHandlerFor4D)context.get("nc.ui.ic.m4d.handler.CprojecttaskidHandlerFor4D#17f9c2b");
  nc.ui.ic.m4d.handler.CprojecttaskidHandlerFor4D bean = new nc.ui.ic.m4d.handler.CprojecttaskidHandlerFor4D();
  context.put("nc.ui.ic.m4d.handler.CprojecttaskidHandlerFor4D#17f9c2b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CcostcenteridHandler getCcostcenteridHandler_e5d406(){
 if(context.get("nc.ui.ic.m4d.handler.CcostcenteridHandler#e5d406")!=null)
 return (nc.ui.ic.m4d.handler.CcostcenteridHandler)context.get("nc.ui.ic.m4d.handler.CcostcenteridHandler#e5d406");
  nc.ui.ic.m4d.handler.CcostcenteridHandler bean = new nc.ui.ic.m4d.handler.CcostcenteridHandler();
  context.put("nc.ui.ic.m4d.handler.CcostcenteridHandler#e5d406",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CcostobjectHandler getCcostobjectHandler_c2ff68(){
 if(context.get("nc.ui.ic.m4d.handler.CcostobjectHandler#c2ff68")!=null)
 return (nc.ui.ic.m4d.handler.CcostobjectHandler)context.get("nc.ui.ic.m4d.handler.CcostobjectHandler#c2ff68");
  nc.ui.ic.m4d.handler.CcostobjectHandler bean = new nc.ui.ic.m4d.handler.CcostobjectHandler();
  context.put("nc.ui.ic.m4d.handler.CcostobjectHandler#c2ff68",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CrcidHandelerFor4D getCrcidHandelerFor4D_e15a5(){
 if(context.get("nc.ui.ic.m4d.handler.CrcidHandelerFor4D#e15a5")!=null)
 return (nc.ui.ic.m4d.handler.CrcidHandelerFor4D)context.get("nc.ui.ic.m4d.handler.CrcidHandelerFor4D#e15a5");
  nc.ui.ic.m4d.handler.CrcidHandelerFor4D bean = new nc.ui.ic.m4d.handler.CrcidHandelerFor4D();
  context.put("nc.ui.ic.m4d.handler.CrcidHandelerFor4D#e15a5",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.Pk_cbsnodeHandlerFor4D getPk_cbsnodeHandlerFor4D_af35ee(){
 if(context.get("nc.ui.ic.m4d.handler.Pk_cbsnodeHandlerFor4D#af35ee")!=null)
 return (nc.ui.ic.m4d.handler.Pk_cbsnodeHandlerFor4D)context.get("nc.ui.ic.m4d.handler.Pk_cbsnodeHandlerFor4D#af35ee");
  nc.ui.ic.m4d.handler.Pk_cbsnodeHandlerFor4D bean = new nc.ui.ic.m4d.handler.Pk_cbsnodeHandlerFor4D();
  context.put("nc.ui.ic.m4d.handler.Pk_cbsnodeHandlerFor4D#af35ee",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.handler.CconstructvendoridHandler getCconstructvendoridHandler_fda1bc(){
 if(context.get("nc.ui.ic.m4d.handler.CconstructvendoridHandler#fda1bc")!=null)
 return (nc.ui.ic.m4d.handler.CconstructvendoridHandler)context.get("nc.ui.ic.m4d.handler.CconstructvendoridHandler#fda1bc");
  nc.ui.ic.m4d.handler.CconstructvendoridHandler bean = new nc.ui.ic.m4d.handler.CconstructvendoridHandler();
  context.put("nc.ui.ic.m4d.handler.CconstructvendoridHandler#fda1bc",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setProcessorMap(getManagedMap1());
  bean.setModel(getIcBizModel());
  bean.setQueryAction((nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)findBeanInUIF2BeanFactory("queryAction"));
  bean.setVoClassName("nc.vo.ic.m4d.entity.MaterialOutVO");
  bean.setAutoShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent)findBeanInUIF2BeanFactory("card"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put("40",getNtbInitProcessor_18579a5());  map.put("89",getIcGenMutiPkLinkQuery());  map.put("426",getMaterialInitDataProcessor_1f55c1d());  map.put("45",getMaterialInitDataProcessor_1728242());  map.put("361",getMaterialInitDataProcessorFor4R_c5bfe6());  return map;}

private nc.ui.ic.general.view.NtbInitProcessor getNtbInitProcessor_18579a5(){
 if(context.get("nc.ui.ic.general.view.NtbInitProcessor#18579a5")!=null)
 return (nc.ui.ic.general.view.NtbInitProcessor)context.get("nc.ui.ic.general.view.NtbInitProcessor#18579a5");
  nc.ui.ic.general.view.NtbInitProcessor bean = new nc.ui.ic.general.view.NtbInitProcessor();
  context.put("nc.ui.ic.general.view.NtbInitProcessor#18579a5",bean);
  bean.setModel(getIcBizModel());
  bean.setQueryArea((nc.ui.uif2.actions.QueryAreaShell)findBeanInUIF2BeanFactory("queryArea"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.inandoutui.linkquery.ICGenMutiPkLinkQuery getIcGenMutiPkLinkQuery(){
 if(context.get("icGenMutiPkLinkQuery")!=null)
 return (nc.ui.ic.inandoutui.linkquery.ICGenMutiPkLinkQuery)context.get("icGenMutiPkLinkQuery");
  nc.ui.ic.inandoutui.linkquery.ICGenMutiPkLinkQuery bean = new nc.ui.ic.inandoutui.linkquery.ICGenMutiPkLinkQuery();
  context.put("icGenMutiPkLinkQuery",bean);
  bean.setModel(getIcBizModel());
  bean.setAutoShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent)findBeanInUIF2BeanFactory("list"));
  bean.setVoClass("nc.vo.ic.m4d.entity.MaterialOutVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.deal.MaterialInitDataProcessor getMaterialInitDataProcessor_1f55c1d(){
 if(context.get("nc.ui.ic.m4d.deal.MaterialInitDataProcessor#1f55c1d")!=null)
 return (nc.ui.ic.m4d.deal.MaterialInitDataProcessor)context.get("nc.ui.ic.m4d.deal.MaterialInitDataProcessor#1f55c1d");
  nc.ui.ic.m4d.deal.MaterialInitDataProcessor bean = new nc.ui.ic.m4d.deal.MaterialInitDataProcessor();
  context.put("nc.ui.ic.m4d.deal.MaterialInitDataProcessor#1f55c1d",bean);
  bean.setModel(getIcBizModel());
  bean.setAutoShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent)findBeanInUIF2BeanFactory("card"));
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.deal.MaterialInitDataProcessor getMaterialInitDataProcessor_1728242(){
 if(context.get("nc.ui.ic.m4d.deal.MaterialInitDataProcessor#1728242")!=null)
 return (nc.ui.ic.m4d.deal.MaterialInitDataProcessor)context.get("nc.ui.ic.m4d.deal.MaterialInitDataProcessor#1728242");
  nc.ui.ic.m4d.deal.MaterialInitDataProcessor bean = new nc.ui.ic.m4d.deal.MaterialInitDataProcessor();
  context.put("nc.ui.ic.m4d.deal.MaterialInitDataProcessor#1728242",bean);
  bean.setModel(getIcBizModel());
  bean.setAutoShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent)findBeanInUIF2BeanFactory("card"));
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.ic.m4d.deal.MaterialInitDataProcessorFor4R getMaterialInitDataProcessorFor4R_c5bfe6(){
 if(context.get("nc.ui.ic.m4d.deal.MaterialInitDataProcessorFor4R#c5bfe6")!=null)
 return (nc.ui.ic.m4d.deal.MaterialInitDataProcessorFor4R)context.get("nc.ui.ic.m4d.deal.MaterialInitDataProcessorFor4R#c5bfe6");
  nc.ui.ic.m4d.deal.MaterialInitDataProcessorFor4R bean = new nc.ui.ic.m4d.deal.MaterialInitDataProcessorFor4R();
  context.put("nc.ui.ic.m4d.deal.MaterialInitDataProcessorFor4R#c5bfe6",bean);
  bean.setModel(getIcBizModel());
  bean.setAutoShowUpComponent((nc.ui.uif2.components.IAutoShowUpComponent)findBeanInUIF2BeanFactory("card"));
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.outbound.bizcheck.OutLinkQuery getOutLinkQuery(){
 if(context.get("outLinkQuery")!=null)
 return (nc.ui.ic.outbound.bizcheck.OutLinkQuery)context.get("outLinkQuery");
  nc.ui.ic.outbound.bizcheck.OutLinkQuery bean = new nc.ui.ic.outbound.bizcheck.OutLinkQuery();
  context.put("outLinkQuery",bean);
  bean.setVoClassName("nc.vo.ic.m4d.entity.MaterialOutVO");
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.PfAddInfoLoader getPfAddInfoLoader(){
 if(context.get("pfAddInfoLoader")!=null)
 return (nc.ui.pubapp.uif2app.actions.PfAddInfoLoader)context.get("pfAddInfoLoader");
  nc.ui.pubapp.uif2app.actions.PfAddInfoLoader bean = new nc.ui.pubapp.uif2app.actions.PfAddInfoLoader();
  context.put("pfAddInfoLoader",bean);
  bean.setBillType("4D");
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4d.deal.MaterialBillScaleProcessor getScalePrcStrategy(){
 if(context.get("scalePrcStrategy")!=null)
 return (nc.ui.ic.m4d.deal.MaterialBillScaleProcessor)context.get("scalePrcStrategy");
  nc.ui.ic.m4d.deal.MaterialBillScaleProcessor bean = new nc.ui.ic.m4d.deal.MaterialBillScaleProcessor();
  context.put("scalePrcStrategy",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare getUserdefAndMarAsstCardPreparator(){
 if(context.get("userdefAndMarAsstCardPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare)context.get("userdefAndMarAsstCardPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillDataPrepare();
  context.put("userdefAndMarAsstCardPreparator",bean);
  bean.setBillDataPrepares(getManagedList11());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList11(){  List list = new ArrayList();  list.add((nc.ui.pub.bill.IBillData)findBeanInUIF2BeanFactory("userdefitemPreparator"));  list.add(getMarProdAsstPreparator());  list.add((nc.ui.pub.bill.IBillData)findBeanInUIF2BeanFactory("marAsstPreparator"));  return list;}

public nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare getUserdefAndMarAsstListPreparator(){
 if(context.get("userdefAndMarAsstListPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare)context.get("userdefAndMarAsstListPreparator");
  nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare bean = new nc.ui.pubapp.uif2app.view.CompositeBillListDataPrepare();
  context.put("userdefAndMarAsstListPreparator",bean);
  bean.setBillListDataPrepares(getManagedList12());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList12(){  List list = new ArrayList();  list.add((nc.ui.pub.bill.IBillListData)findBeanInUIF2BeanFactory("userdefitemlistPreparator"));  list.add(getMarProdAsstPreparator());  list.add((nc.ui.pub.bill.IBillListData)findBeanInUIF2BeanFactory("marAsstPreparator"));  return list;}

public nc.ui.ic.m4d.action.CancelSignAction getCancelSignAction(){
 if(context.get("cancelSignAction")!=null)
 return (nc.ui.ic.m4d.action.CancelSignAction)context.get("cancelSignAction");
  nc.ui.ic.m4d.action.CancelSignAction bean = new nc.ui.ic.m4d.action.CancelSignAction();
  context.put("cancelSignAction",bean);
  bean.setModel(getIcBizModel());
  bean.setActionName("CANCELSIGN");
  bean.setEditor((nc.ui.uif2.editor.IEditor)findBeanInUIF2BeanFactory("card"));
  bean.setBillForm((nc.ui.ic.pub.view.ICBizBillForm)findBeanInUIF2BeanFactory("card"));
  bean.setListView((nc.ui.ic.pub.view.ICBizBillListView)findBeanInUIF2BeanFactory("list"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator getMarProdAsstPreparator(){
 if(context.get("marProdAsstPreparator")!=null)
 return (nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator)context.get("marProdAsstPreparator");
  nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator bean = new nc.ui.pubapp.uif2app.view.material.assistant.MarAsstPreparator();
  context.put("marProdAsstPreparator",bean);
  bean.setModel(getIcBizModel());
  bean.setContainer((nc.ui.uif2.userdefitem.UserDefItemContainer)findBeanInUIF2BeanFactory("userdefitemContainer"));
  bean.setPrefix("vprodfree");
  bean.setMaterialField("ccostobject");
  bean.setProjectField("cprodprojectid");
  bean.setSupplierField("cprodvendorid");
  bean.setProductorField("cprodproductorid");
  bean.setCustomerField("cprodasscustid");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4d.deal.MaterialOutQueryDLGInitializer getQryDLGInitializer(){
 if(context.get("qryDLGInitializer")!=null)
 return (nc.ui.ic.m4d.deal.MaterialOutQueryDLGInitializer)context.get("qryDLGInitializer");
  nc.ui.ic.m4d.deal.MaterialOutQueryDLGInitializer bean = new nc.ui.ic.m4d.deal.MaterialOutQueryDLGInitializer();
  context.put("qryDLGInitializer",bean);
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.listener.crossrule.CrossRuleMediator getCrossRuleMediator(){
 if(context.get("crossRuleMediator")!=null)
 return (nc.ui.scmpub.listener.crossrule.CrossRuleMediator)context.get("crossRuleMediator");
  nc.ui.scmpub.listener.crossrule.CrossRuleMediator bean = new nc.ui.scmpub.listener.crossrule.CrossRuleMediator();
  context.put("crossRuleMediator",bean);
  bean.setModel(getIcBizModel());
  bean.setBillType("4D");
  bean.init();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
