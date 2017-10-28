package nc.ui.mmpps.plo.action.busi;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import nc.bs.framework.common.NCLocator;
import nc.desktop.ui.WorkbenchEnvironment;
import nc.itf.pubapp.pub.exception.IResumeException;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.ui.mmf.framework.action.ActionInitializer;
import nc.ui.mmpps.plo.action.PLOActionRegistry;
import nc.ui.mmpps.plo.action.PloActionCode;
import nc.ui.mmpps.plo.action.exceptionhandler.PloReleaseHandler;
import nc.ui.mmpps.plo.action.util.PloReleaseActionUtil;
import nc.ui.mmpps.plo.action.util.PoActionsUtil;
import nc.ui.mmpps.plo.serviceproxy.PloClientServiceProxy;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pubapp.uif2app.model.BillManageModel;
import nc.ui.uif2.IExceptionHandler;
import nc.ui.uif2.NCAction;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.ui.uif2.actions.ActionInfo;
import nc.util.mmf.framework.base.MMValueCheck;
import nc.vo.mmpps.mpm.res.MpmRes;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFBoolean;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;
import nc.vo.scmpub.exp.AtpNotEnoughException;
import nc.vo.scmpub.res.BusinessCheck;

/**
 * <b> 计划订单下达按钮 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-3-23
 * 
 * @author:songdan
 */
@SuppressWarnings("restriction")
public class PloReleaseAction extends NCAction {
    private static final long serialVersionUID = 1L;

    public PloReleaseAction() {
        ActionInfo info = PLOActionRegistry.getActionInfo(PloActionCode.PORELEASEACTION);
        ActionInitializer.initializeAction(this, info.getCode(), info.getName(), info.getShort_description(),
                info.getKeyStroke());
        this.putValue(PloActionCode.PORELEASEACTION, MpmRes.BTN_RELEASE());
    }

    @Override
    public void doAction(ActionEvent e) throws Exception {
        // 判空
        Object[] objs = this.getModel().getSelectedOperaDatas();
        if (null == objs || MMValueCheck.isEmpty(objs)) {
            return;
        }
        List<AbstractBill> aggs = new ArrayList<AbstractBill>();
        for (Object obj : objs) {
            AbstractBill agg = (AbstractBill) obj;
            aggs.add(agg);
        }
        // 过滤计划、完成态的计划订单
        List<AbstractBill> retaggs = PloReleaseActionUtil.filtPlos(aggs.toArray(new AbstractBill[0]));
        retaggs = filtePlosByMaterialType(retaggs);
        if (MMValueCheck.isEmpty(retaggs)) {
            return;
        }
        // 远程调用进行下达
        boolean flag = false;
        AggregatedPoVO[] returnaggs = null;
        try {
            returnaggs =
                    PloClientServiceProxy.getPoReleaseService().doPoRelease(retaggs.toArray(new AggregatedPoVO[] {}),
                            UFBoolean.TRUE);
        }
        catch (Exception e1) {
            Throwable ex = this.getThrowable(e1);
            // 提示
            if (BusinessCheck.ATPCheck.getCheckCode().equals(((IResumeException) ex).getBusiExceptionType())) {
                if (this.isOKForATPCheckDialog(ex)) {
                    returnaggs =
                            PloClientServiceProxy.getPoReleaseService().doPoRelease(
                                    retaggs.toArray(new AggregatedPoVO[] {}), UFBoolean.FALSE);
                }
                else {
                    return;
                }
            }
            else {
                ExceptionUtils.wrappException(e1);
                return;
            }
        }
        // 更新界面
        this.getModel().directlyUpdate(returnaggs);
        if (!flag) {
            ShowStatusBarMsgUtil.showStatusBarMsg(MpmRes.getPloReleaseSuccessMsg(), this.getModel().getContext())/*
                                                                                                                  * @
                                                                                                                  * res计划订单下达成功
                                                                                                                  */;
        }
    }

    @Override
    public IExceptionHandler getExceptionHandler() {
        if (this.exceptionHandler == null) {
            PloReleaseHandler handler = new PloReleaseHandler();
            handler.setErrormsg(MpmRes.getReleaseErrorMsg());
            handler.setContext(this.getModel().getContext());
            this.exceptionHandler = handler;
        }
        return this.exceptionHandler;
    }

    private BillManageModel model;

    /**
     * 获得 PloManageAppModel
     */
    public BillManageModel getModel() {
        return this.model;
    }

    public void setModel(BillManageModel model) {
        this.model = model;
        model.addAppEventListener(this);
    }

    private String msg = null;

    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    protected boolean isActionEnable() {
        Object[] objs = this.getModel().getSelectedOperaDatas();
        return PoActionsUtil.isRealeaseActionEnable(objs, this.getModel());
    }

    /**
     * 获取Throwable
     */
    private Throwable getThrowable(Exception e) {
        if (e instanceof IResumeException) {
            return ExceptionUtils.unmarsh(e);
        }
        if (e instanceof AtpNotEnoughException) {
            return ExceptionUtils.unmarsh(e);
        }
        if (e.getCause() instanceof AtpNotEnoughException) {
            return ExceptionUtils.unmarsh(e);
        }

        ExceptionUtils.wrappException(e);
        return null;
    }

    /**
     * 可用量检查对话框是否返回Yes
     */
    private boolean isOKForATPCheckDialog(Throwable e) {

        return UIDialog.ID_YES == MessageDialog.showYesNoDlg(WorkbenchEnvironment.getInstance().getWorkbench()
                .getParent(), MpmRes.getAtpCheck_DIALOGTITLE(), ((AtpNotEnoughException) e).getMessage());
    }
    
    private List<AbstractBill> filtePlosByMaterialType(List<AbstractBill> aggVOs){
    	List<AbstractBill> newAggVOs = new ArrayList<AbstractBill>();
    	for(AbstractBill aggVO : aggVOs){
    		String cmaterialvid = (String) aggVO.getParent().getAttributeValue("cmaterialvid");
    		String pk_org = (String) aggVO.getParent().getAttributeValue("pk_org");
    		String sql = "SELECT martype FROM bd_materialstock WHERE pk_material = '"+cmaterialvid+"' AND NVL(DR,0) = 0 AND pk_org = '"+pk_org+"'";
    		String martype = null;
    		try {
    			martype = (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnProcessor());
			} catch (BusinessException e) {
				e.printStackTrace();
				throw new RuntimeException(e.getMessage());
			}
    		if(!"MR".equals(martype)){
    			newAggVOs.add(aggVO);
    		}
    	}
    	return newAggVOs;
    }
}
