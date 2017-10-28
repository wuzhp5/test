package nc.ui.ic.m4y;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.ui.uif2.factory.AbstractJavaBeanDefinition;

public class AllocationOut_org extends nc.ui.ic.general.model.config.GeneralCommon{
	private Map<String, Object> context = new HashMap();
public nc.ui.uif2.userdefitem.QueryParam getQueryParams1(){
 if(context.get("queryParams1")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("queryParams1");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("queryParams1",bean);
  bean.setMdfullname("ic.TransOutHeadVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.userdefitem.QueryParam getQueryParams2(){
 if(context.get("queryParams2")!=null)
 return (nc.ui.uif2.userdefitem.QueryParam)context.get("queryParams2");
  nc.ui.uif2.userdefitem.QueryParam bean = new nc.ui.uif2.userdefitem.QueryParam();
  context.put("queryParams2",bean);
  bean.setMdfullname("ic.TransOutBodyVO");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.editor.UserdefQueryParam getUserQueryParams1(){
 if(context.get("userQueryParams1")!=null)
 return (nc.ui.uif2.editor.UserdefQueryParam)context.get("userQueryParams1");
  nc.ui.uif2.editor.UserdefQueryParam bean = new nc.ui.uif2.editor.UserdefQueryParam();
  context.put("userQueryParams1",bean);
  bean.setMdfullname("ic.TransOutHeadVO");
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
  bean.setMdfullname("ic.TransOutBodyVO");
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
  bean.setVoClassName("nc.vo.ic.m4y.entity.TransOutVO");
  bean.setBillType("4y");
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
  nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator bean = new nc.ui.pubapp.uif2app.actions.pagination.BillModelPaginationDelegator(getIcBizModel());  context.put("pageDelegator",bean);
  bean.setPaginationQuery(getPageQuery());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.scmpub.page.model.SCMBillPageMediator getPageMediator(){
 if(context.get("pageMediator")!=null)
 return (nc.ui.scmpub.page.model.SCMBillPageMediator)context.get("pageMediator");
  nc.ui.scmpub.page.model.SCMBillPageMediator bean = new nc.ui.scmpub.page.model.SCMBillPageMediator();
  context.put("pageMediator",bean);
  bean.setListView(getList());
  bean.setRecordInPage(10);
  bean.setCachePages(10);
  bean.setPageDelegator(getPageDelegator());
  bean.init();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.model.TransOutModelService getManageModelService(){
 if(context.get("manageModelService")!=null)
 return (nc.ui.ic.m4y.model.TransOutModelService)context.get("manageModelService");
  nc.ui.ic.m4y.model.TransOutModelService bean = new nc.ui.ic.m4y.model.TransOutModelService();
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
  bean.setBillType("4Y");
  bean.setPowerValidate(true);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.deal.TransOutQueryDLGInitializer getQryDLGInitializer(){
 if(context.get("qryDLGInitializer")!=null)
 return (nc.ui.ic.m4y.deal.TransOutQueryDLGInitializer)context.get("qryDLGInitializer");
  nc.ui.ic.m4y.deal.TransOutQueryDLGInitializer bean = new nc.ui.ic.m4y.deal.TransOutQueryDLGInitializer();
  context.put("qryDLGInitializer",bean);
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.deal.TransOutUIProcessorInfo getUIProcesorInfo(){
 if(context.get("UIProcesorInfo")!=null)
 return (nc.ui.ic.m4y.deal.TransOutUIProcessorInfo)context.get("UIProcesorInfo");
  nc.ui.ic.m4y.deal.TransOutUIProcessorInfo bean = new nc.ui.ic.m4y.deal.TransOutUIProcessorInfo();
  context.put("UIProcesorInfo",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.handler.TransOutCwarehouseidHandler getCwarehouseidHandler(){
 if(context.get("cwarehouseidHandler")!=null)
 return (nc.ui.ic.m4y.handler.TransOutCwarehouseidHandler)context.get("cwarehouseidHandler");
  nc.ui.ic.m4y.handler.TransOutCwarehouseidHandler bean = new nc.ui.ic.m4y.handler.TransOutCwarehouseidHandler();
  context.put("cwarehouseidHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.handler.CothercalbodyoidHandler getCothercalbodyoidHandlerFor4Y(){
 if(context.get("cothercalbodyoidHandlerFor4Y")!=null)
 return (nc.ui.ic.m4y.handler.CothercalbodyoidHandler)context.get("cothercalbodyoidHandlerFor4Y");
  nc.ui.ic.m4y.handler.CothercalbodyoidHandler bean = new nc.ui.ic.m4y.handler.CothercalbodyoidHandler();
  context.put("cothercalbodyoidHandlerFor4Y",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.handler.CotherwarehouseidHandler getCotherwhidHandlerFor4Y(){
 if(context.get("cotherwhidHandlerFor4Y")!=null)
 return (nc.ui.ic.m4y.handler.CotherwarehouseidHandler)context.get("cotherwhidHandlerFor4Y");
  nc.ui.ic.m4y.handler.CotherwarehouseidHandler bean = new nc.ui.ic.m4y.handler.CotherwarehouseidHandler();
  context.put("cotherwhidHandlerFor4Y",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.handler.TransOutCvmivenderidHandler getCvmivenderidHandler(){
 if(context.get("cvmivenderidHandler")!=null)
 return (nc.ui.ic.m4y.handler.TransOutCvmivenderidHandler)context.get("cvmivenderidHandler");
  nc.ui.ic.m4y.handler.TransOutCvmivenderidHandler bean = new nc.ui.ic.m4y.handler.TransOutCvmivenderidHandler();
  context.put("cvmivenderidHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.special.handler.CdptvidHandler getCdptvidHandler(){
 if(context.get("cdptvidHandler")!=null)
 return (nc.ui.ic.special.handler.CdptvidHandler)context.get("cdptvidHandler");
  nc.ui.ic.special.handler.CdptvidHandler bean = new nc.ui.ic.special.handler.CdptvidHandler();
  context.put("cdptvidHandler",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.special.handler.CbizidHandler getCbizidHandler(){
 if(context.get("cbizidHandler")!=null)
 return (nc.ui.ic.special.handler.CbizidHandler)context.get("cbizidHandler");
  nc.ui.ic.special.handler.CbizidHandler bean = new nc.ui.ic.special.handler.CbizidHandler();
  context.put("cbizidHandler",bean);
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

private Map getManagedMap0(){  Map map = new HashMap();  map.put("cothercalbodyoid",getCothercalbodyoidHandlerFor4Y());  map.put("cotherwhid",getCotherwhidHandlerFor4Y());  return map;}

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

public nc.ui.ic.m4y.view.TransOutBizView getCard(){
 if(context.get("card")!=null)
 return (nc.ui.ic.m4y.view.TransOutBizView)context.get("card");
  nc.ui.ic.m4y.view.TransOutBizView bean = new nc.ui.ic.m4y.view.TransOutBizView();
  context.put("card",bean);
  bean.setModel(getIcBizModel());
  bean.setBarCodeInputPanel((nc.ui.ic.pub.view.BarCodeInputPanel)findBeanInUIF2BeanFactory("barcodecard"));
  bean.setTemplateContainer((nc.ui.uif2.editor.TemplateContainer)findBeanInUIF2BeanFactory("templateContainer"));
  bean.setTangramContainer(getContainer());
  bean.setUserdefitemPreparator((nc.ui.pub.bill.IBillData)findBeanInUIF2BeanFactory("userdefAndMarAsstCardPreparator"));
  bean.setAutoAddLine(true);
  bean.setTemplateNotNullValidate(true);
  bean.setBlankChildrenFilter(getSingleFieldBlankChildrenFilter_be72bb());
  bean.setBodyLineActions(getManagedList0());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter getSingleFieldBlankChildrenFilter_be72bb(){
 if(context.get("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#be72bb")!=null)
 return (nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter)context.get("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#be72bb");
  nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter bean = new nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter();
  context.put("nc.ui.pubapp.uif2app.view.value.SingleFieldBlankChildrenFilter#be72bb",bean);
  bean.setFieldName("cmaterialvid");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList0(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("bodyAddLineAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("bodyInsertLineAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("bodyDelLineAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("bodyCopyLineAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("bodyPasteLineAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("bodyPasteToTailAction"));  list.add(getActionsBar_ActionsBarSeparator_15e90eb());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("rearrangeRowNoBodyLineAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("bodyLineEditAction"));  list.add(getActionsBar_ActionsBarSeparator_17c4463());  list.add(getDefaultBodyZoomAction_16dfde4());  return list;}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_15e90eb(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#15e90eb")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#15e90eb");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#15e90eb",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pub.beans.ActionsBar.ActionsBarSeparator getActionsBar_ActionsBarSeparator_17c4463(){
 if(context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#17c4463")!=null)
 return (nc.ui.pub.beans.ActionsBar.ActionsBarSeparator)context.get("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#17c4463");
  nc.ui.pub.beans.ActionsBar.ActionsBarSeparator bean = new nc.ui.pub.beans.ActionsBar.ActionsBarSeparator();
  context.put("nc.ui.pub.beans.ActionsBar.ActionsBarSeparator#17c4463",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction getDefaultBodyZoomAction_16dfde4(){
 if(context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#16dfde4")!=null)
 return (nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction)context.get("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#16dfde4");
  nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction bean = new nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction();
  context.put("nc.ui.pubapp.uif2app.actions.DefaultBodyZoomAction#16dfde4",bean);
  bean.setPos(1);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.action.TransOutSignAction getSignAction(){
 if(context.get("signAction")!=null)
 return (nc.ui.ic.m4y.action.TransOutSignAction)context.get("signAction");
  nc.ui.ic.m4y.action.TransOutSignAction bean = new nc.ui.ic.m4y.action.TransOutSignAction();
  context.put("signAction",bean);
  bean.setModel(getIcBizModel());
  bean.setActionName("SIGN");
  bean.setFilledUpInFlow(false);
  bean.setEditor(getCard());
  bean.setBillForm(getCard());
  bean.setListView(getList());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.view.TransOutBizBillListView getList(){
 if(context.get("list")!=null)
 return (nc.ui.ic.m4y.view.TransOutBizBillListView)context.get("list");
  nc.ui.ic.m4y.view.TransOutBizBillListView bean = new nc.ui.ic.m4y.view.TransOutBizBillListView();
  context.put("list",bean);
  bean.setModel(getIcBizModel());
  bean.setMultiSelectionMode(0);
  bean.setTemplateContainer((nc.ui.uif2.editor.TemplateContainer)findBeanInUIF2BeanFactory("templateContainer"));
  bean.setUserdefitemListPreparator((nc.ui.pub.bill.IBillListData)findBeanInUIF2BeanFactory("userdefAndMarAsstListPreparator"));
  bean.setPaginationBar(getPageBar());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.action.TransOutSaveAction getSaveAction(){
 if(context.get("saveAction")!=null)
 return (nc.ui.ic.m4y.action.TransOutSaveAction)context.get("saveAction");
  nc.ui.ic.m4y.action.TransOutSaveAction bean = new nc.ui.ic.m4y.action.TransOutSaveAction();
  context.put("saveAction",bean);
  bean.setModel(getIcBizModel());
  bean.setEditorModel((nc.ui.ic.pub.model.ICBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setEditor(getCard());
  bean.setActionName("WRITE");
  bean.setValidationService((nc.bs.uif2.validation.IValidationService)findBeanInUIF2BeanFactory("validateService"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.PfAddInfoLoader getPfAddInfoLoader(){
 if(context.get("pfAddInfoLoader")!=null)
 return (nc.ui.pubapp.uif2app.actions.PfAddInfoLoader)context.get("pfAddInfoLoader");
  nc.ui.pubapp.uif2app.actions.PfAddInfoLoader bean = new nc.ui.pubapp.uif2app.actions.PfAddInfoLoader();
  context.put("pfAddInfoLoader",bean);
  bean.setBillType("4Y");
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.actions.AddMenuAction getAddMenu(){
 if(context.get("addMenu")!=null)
 return (nc.ui.pubapp.uif2app.actions.AddMenuAction)context.get("addMenu");
  nc.ui.pubapp.uif2app.actions.AddMenuAction bean = new nc.ui.pubapp.uif2app.actions.AddMenuAction();
  context.put("addMenu",bean);
  bean.setActions(getManagedList1());
  bean.setTooltip(getI18nFB_14f007f());
  bean.setBillType("4Y");
  bean.setModel(getIcBizModel());
  bean.setPfAddInfoLoader(getPfAddInfoLoader());
  bean.initUI();
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList1(){  List list = new ArrayList();  list.add(getAddFrom5XAction());  list.add(getAddFrom4331Action());  return list;}

private nc.ui.ic.m4y.action.AddFrom5XAction getAddFrom5XAction(){
 if(context.get("addFrom5XAction")!=null)
 return (nc.ui.ic.m4y.action.AddFrom5XAction)context.get("addFrom5XAction");
  nc.ui.ic.m4y.action.AddFrom5XAction bean = new nc.ui.ic.m4y.action.AddFrom5XAction();
  context.put("addFrom5XAction",bean);
  bean.setSourceBillType("5X");
  bean.setSourceBillName(getI18nFB_37ab72());
  bean.setDestBillType("4Y");
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
  bean.setPfButtonClickContext(2);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_37ab72(){
 if(context.get("nc.ui.uif2.I18nFB#37ab72")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#37ab72");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#37ab72",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0501");
  bean.setDefaultValue("调拨订单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#37ab72",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private nc.ui.ic.m4y.action.AddFrom4331Action getAddFrom4331Action(){
 if(context.get("addFrom4331Action")!=null)
 return (nc.ui.ic.m4y.action.AddFrom4331Action)context.get("addFrom4331Action");
  nc.ui.ic.m4y.action.AddFrom4331Action bean = new nc.ui.ic.m4y.action.AddFrom4331Action();
  context.put("addFrom4331Action",bean);
  bean.setSourceBillType("4331");
  bean.setSourceBillName(getI18nFB_d902ec());
  bean.setDestBillType("4Y");
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setTransferViewProcessor((nc.ui.pubapp.billref.dest.TransferViewProcessor)findBeanInUIF2BeanFactory("transferViewProcessor"));
  bean.setPfButtonClickContext(2);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_d902ec(){
 if(context.get("nc.ui.uif2.I18nFB#d902ec")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#d902ec");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#d902ec",bean);  bean.setResDir("funcode");
  bean.setResId("btn_40080802_Ref4331");
  bean.setDefaultValue("发货单");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#d902ec",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private java.lang.String getI18nFB_14f007f(){
 if(context.get("nc.ui.uif2.I18nFB#14f007f")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#14f007f");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#14f007f",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0739");
  bean.setDefaultValue("新增");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#14f007f",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

public nc.ui.ic.m4y.action.TransOutReturnAction getReturnAction(){
 if(context.get("returnAction")!=null)
 return (nc.ui.ic.m4y.action.TransOutReturnAction)context.get("returnAction");
  nc.ui.ic.m4y.action.TransOutReturnAction bean = new nc.ui.ic.m4y.action.TransOutReturnAction();
  context.put("returnAction",bean);
  bean.setModel(getIcBizModel());
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setEditor(getCard());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.action.TransOutCopyAction getCopyAction(){
 if(context.get("copyAction")!=null)
 return (nc.ui.ic.m4y.action.TransOutCopyAction)context.get("copyAction");
  nc.ui.ic.m4y.action.TransOutCopyAction bean = new nc.ui.ic.m4y.action.TransOutCopyAction();
  context.put("copyAction",bean);
  bean.setModel(getIcBizModel());
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setEditor(getCard());
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
  bean.setName(getI18nFB_1a9b358());
  bean.setTooltip(getI18nFB_3d6bc6());
  bean.setActions(getManagedList2());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_1a9b358(){
 if(context.get("nc.ui.uif2.I18nFB#1a9b358")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1a9b358");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1a9b358",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0741");
  bean.setDefaultValue("辅助功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1a9b358",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private java.lang.String getI18nFB_3d6bc6(){
 if(context.get("nc.ui.uif2.I18nFB#3d6bc6")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#3d6bc6");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#3d6bc6",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0786");
  bean.setDefaultValue("辅助功能(Shift+B)");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#3d6bc6",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList2(){  List list = new ArrayList();  list.add(getReturnAction());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("attachMentMngAction"));  return list;}

public nc.funcnode.ui.action.MenuAction getAssistantFunctionEditAction_IN(){
 if(context.get("assistantFunctionEditAction_IN")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("assistantFunctionEditAction_IN");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("assistantFunctionEditAction_IN",bean);
  bean.setCode("NastMngEditAction");
  bean.setName(getI18nFB_6eb72a());
  bean.setActions(getManagedList3());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_6eb72a(){
 if(context.get("nc.ui.uif2.I18nFB#6eb72a")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#6eb72a");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#6eb72a",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0741");
  bean.setDefaultValue("辅助功能");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#6eb72a",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList3(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("displayOrhideAction"));  return list;}

public nc.funcnode.ui.action.GroupAction getPrintMngAction(){
 if(context.get("printMngAction")!=null)
 return (nc.funcnode.ui.action.GroupAction)context.get("printMngAction");
  nc.funcnode.ui.action.GroupAction bean = new nc.funcnode.ui.action.GroupAction();
  context.put("printMngAction",bean);
  bean.setCode("printMngAction");
  bean.setActions(getManagedList4());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList4(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("templatePrintAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("templatePreviewAction"));  list.add(getSeparatorAction_bb34fb());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("printQAAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("mergerShowAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("printLocAction"));  list.add(getPrintCountQueryAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_bb34fb(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#bb34fb")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#bb34fb");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#bb34fb",bean);
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

public nc.ui.ic.m4y.action.ToInfoEditAction getToInfoEditAction(){
 if(context.get("toInfoEditAction")!=null)
 return (nc.ui.ic.m4y.action.ToInfoEditAction)context.get("toInfoEditAction");
  nc.ui.ic.m4y.action.ToInfoEditAction bean = new nc.ui.ic.m4y.action.ToInfoEditAction();
  context.put("toInfoEditAction",bean);
  bean.setEditorModel((nc.ui.ic.general.model.ICGenBizEditorModel)findBeanInUIF2BeanFactory("icBizEditorModel"));
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.action.TransStatueQryAction getTransStatueQryAction(){
 if(context.get("transStatueQryAction")!=null)
 return (nc.ui.ic.m4y.action.TransStatueQryAction)context.get("transStatueQryAction");
  nc.ui.ic.m4y.action.TransStatueQryAction bean = new nc.ui.ic.m4y.action.TransStatueQryAction();
  context.put("transStatueQryAction",bean);
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.funcnode.ui.action.MenuAction getLinkQryBrowseGroupAction(){
 if(context.get("linkQryBrowseGroupAction")!=null)
 return (nc.funcnode.ui.action.MenuAction)context.get("linkQryBrowseGroupAction");
  nc.funcnode.ui.action.MenuAction bean = new nc.funcnode.ui.action.MenuAction();
  context.put("linkQryBrowseGroupAction",bean);
  bean.setCode("linkQryAction");
  bean.setName(getI18nFB_935fa1());
  bean.setTooltip(getI18nFB_16b50a8());
  bean.setActions(getManagedList5());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_935fa1(){
 if(context.get("nc.ui.uif2.I18nFB#935fa1")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#935fa1");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#935fa1",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0742");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#935fa1",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private java.lang.String getI18nFB_16b50a8(){
 if(context.get("nc.ui.uif2.I18nFB#16b50a8")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#16b50a8");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#16b50a8",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0781");
  bean.setDefaultValue("联查(Ctrl+K)");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#16b50a8",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList5(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("linkQryAction"));  list.add(getSeparatorAction_7c6725());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("displayOrhideAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("setPieceAtion"));  list.add(getTransStatueQryAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_7c6725(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#7c6725")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#7c6725");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#7c6725",bean);
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
  bean.setName(getI18nFB_157e5c7());
  bean.setTooltip(getI18nFB_1ae9aaa());
  bean.setActions(getManagedList6());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private java.lang.String getI18nFB_157e5c7(){
 if(context.get("nc.ui.uif2.I18nFB#157e5c7")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#157e5c7");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#157e5c7",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0742");
  bean.setDefaultValue("联查");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#157e5c7",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private java.lang.String getI18nFB_1ae9aaa(){
 if(context.get("nc.ui.uif2.I18nFB#1ae9aaa")!=null)
 return (java.lang.String)context.get("nc.ui.uif2.I18nFB#1ae9aaa");
  nc.ui.uif2.I18nFB bean = new nc.ui.uif2.I18nFB();
    context.put("&nc.ui.uif2.I18nFB#1ae9aaa",bean);  bean.setResDir("4008001_0");
  bean.setResId("04008001-0790");
  bean.setDefaultValue("联查(Ctrl+L)");
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
 try {
     Object product = bean.getObject();
    context.put("nc.ui.uif2.I18nFB#1ae9aaa",product);
     return (java.lang.String)product;
}
catch(Exception e) { throw new RuntimeException(e);}}

private List getManagedList6(){  List list = new ArrayList();  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("displayOrhideAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("setPieceAtion"));  return list;}

public nc.ui.uif2.actions.ActionContributors getToftpanelActionContributors(){
 if(context.get("toftpanelActionContributors")!=null)
 return (nc.ui.uif2.actions.ActionContributors)context.get("toftpanelActionContributors");
  nc.ui.uif2.actions.ActionContributors bean = new nc.ui.uif2.actions.ActionContributors();
  context.put("toftpanelActionContributors",bean);
  bean.setContributors(getManagedList7());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList7(){  List list = new ArrayList();  list.add(getActionsOfList());  list.add(getActionsOfCard());  return list;}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfList(){
 if(context.get("actionsOfList")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfList");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getList());  context.put("actionsOfList",bean);
  bean.setActions(getManagedList8());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList8(){  List list = new ArrayList();  list.add(getAddMenu());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("editAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("deleteAction"));  list.add(getSeparatorAction_2c4413());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("queryAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("refreshAction"));  list.add(getSeparatorAction_297908());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("maintainMenu"));  list.add(getToInfoEditAction());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("signActionMenu"));  list.add(getAssistantFunctionBrowseAction_IN());  list.add(getSeparatorAction_c90dbd());  list.add(getLinkQryBrowseGroupAction());  list.add(getSeparatorAction_1a51cba());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("importExportAction"));  list.add(getPrintMngAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_2c4413(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#2c4413")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#2c4413");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#2c4413",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_297908(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#297908")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#297908");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#297908",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_c90dbd(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#c90dbd")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#c90dbd");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#c90dbd",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1a51cba(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1a51cba")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1a51cba");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1a51cba",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.uif2.actions.StandAloneToftPanelActionContainer getActionsOfCard(){
 if(context.get("actionsOfCard")!=null)
 return (nc.ui.uif2.actions.StandAloneToftPanelActionContainer)context.get("actionsOfCard");
  nc.ui.uif2.actions.StandAloneToftPanelActionContainer bean = new nc.ui.uif2.actions.StandAloneToftPanelActionContainer(getCard());  context.put("actionsOfCard",bean);
  bean.setModel(getIcBizModel());
  bean.setActions(getManagedList9());
  bean.setEditActions(getManagedList10());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList9(){  List list = new ArrayList();  list.add(getAddMenu());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("editAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("deleteAction"));  list.add(getSeparatorAction_10117fa());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("queryAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("refreshCardAction"));  list.add(getSeparatorAction_cd580b());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("maintainMenu"));  list.add(getToInfoEditAction());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("signActionMenu"));  list.add(getAssistantFunctionBrowseAction_IN());  list.add(getSeparatorAction_14f7b36());  list.add(getLinkQryBrowseGroupAction());  list.add(getSeparatorAction_122450f());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("importExportAction"));  list.add(getPrintMngAction());  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_10117fa(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#10117fa")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#10117fa");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#10117fa",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_cd580b(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#cd580b")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#cd580b");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#cd580b",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_14f7b36(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#14f7b36")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#14f7b36");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#14f7b36",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_122450f(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#122450f")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#122450f");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#122450f",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private List getManagedList10(){  List list = new ArrayList();  list.add(getSaveAction());  list.add(getSeparatorAction_e3fc2a());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("cancelAction"));  list.add(getSeparatorAction_1c05c1e());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("fetchAutoAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("pickAutoAction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("manufacturedatepickautoaction"));  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("maintainMenu"));  list.add(getSeparatorAction_1dfe9c2());  list.add(getLinkQryEditGroupAction());  list.add(getSeparatorAction_16bdc36());  list.add((javax.swing.Action)findBeanInUIF2BeanFactory("importExportAction"));  return list;}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_e3fc2a(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#e3fc2a")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#e3fc2a");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#e3fc2a",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1c05c1e(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1c05c1e")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1c05c1e");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1c05c1e",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_1dfe9c2(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#1dfe9c2")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#1dfe9c2");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#1dfe9c2",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private nc.funcnode.ui.action.SeparatorAction getSeparatorAction_16bdc36(){
 if(context.get("nc.funcnode.ui.action.SeparatorAction#16bdc36")!=null)
 return (nc.funcnode.ui.action.SeparatorAction)context.get("nc.funcnode.ui.action.SeparatorAction#16bdc36");
  nc.funcnode.ui.action.SeparatorAction bean = new nc.funcnode.ui.action.SeparatorAction();
  context.put("nc.funcnode.ui.action.SeparatorAction#16bdc36",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener getInitDataListener(){
 if(context.get("InitDataListener")!=null)
 return (nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener)context.get("InitDataListener");
  nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener bean = new nc.ui.pubapp.uif2app.model.DefaultFuncNodeInitDataListener();
  context.put("InitDataListener",bean);
  bean.setModel(getIcBizModel());
  bean.setQueryAction((nc.ui.pubapp.uif2app.query2.action.DefaultQueryAction)findBeanInUIF2BeanFactory("queryAction"));
  bean.setVoClassName("nc.vo.ic.m4y.entity.TransOutVO");
  bean.setAutoShowUpComponent(getCard());
  bean.setProcessorMap(getManagedMap1());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

private Map getManagedMap1(){  Map map = new HashMap();  map.put("40",getNtbInitProcessor_4684ef());  return map;}

private nc.ui.ic.general.view.NtbInitProcessor getNtbInitProcessor_4684ef(){
 if(context.get("nc.ui.ic.general.view.NtbInitProcessor#4684ef")!=null)
 return (nc.ui.ic.general.view.NtbInitProcessor)context.get("nc.ui.ic.general.view.NtbInitProcessor#4684ef");
  nc.ui.ic.general.view.NtbInitProcessor bean = new nc.ui.ic.general.view.NtbInitProcessor();
  context.put("nc.ui.ic.general.view.NtbInitProcessor#4684ef",bean);
  bean.setModel(getIcBizModel());
  bean.setQueryArea((nc.ui.uif2.actions.QueryAreaShell)findBeanInUIF2BeanFactory("queryArea"));
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.outbound.bizcheck.OutLinkQuery getOutLinkQuery(){
 if(context.get("outLinkQuery")!=null)
 return (nc.ui.ic.outbound.bizcheck.OutLinkQuery)context.get("outLinkQuery");
  nc.ui.ic.outbound.bizcheck.OutLinkQuery bean = new nc.ui.ic.outbound.bizcheck.OutLinkQuery();
  context.put("outLinkQuery",bean);
  bean.setVoClassName("nc.vo.ic.m4y.entity.TransOutVO");
  bean.setModel(getIcBizModel());
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

public nc.ui.ic.m4y.deal.TransOutBillScaleProcessor getScalePrcStrategy(){
 if(context.get("scalePrcStrategy")!=null)
 return (nc.ui.ic.m4y.deal.TransOutBillScaleProcessor)context.get("scalePrcStrategy");
  nc.ui.ic.m4y.deal.TransOutBillScaleProcessor bean = new nc.ui.ic.m4y.deal.TransOutBillScaleProcessor();
  context.put("scalePrcStrategy",bean);
setBeanFacotryIfBeanFacatoryAware(bean);
invokeInitializingBean(bean);
return bean;
}

}
