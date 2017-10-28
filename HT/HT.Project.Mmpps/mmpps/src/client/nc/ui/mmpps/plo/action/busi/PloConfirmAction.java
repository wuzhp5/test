package nc.ui.mmpps.plo.action.busi;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.Map;

import nc.bs.bd.baseservice.ArrayClassConvertUtil;
import nc.bs.framework.common.NCLocator;
import nc.itf.mes.ws.serivce.IUploadBillWSService;
import nc.ui.mmf.framework.action.ActionInitializer;
import nc.ui.mmf.framework.batch.BatchProcessAction;
import nc.ui.mmpps.plo.action.PLOActionRegistry;
import nc.ui.mmpps.plo.action.PloActionCode;
import nc.ui.mmpps.plo.action.util.PoActionsUtil;
import nc.ui.mmpps.plo.model.PloManageAppModel;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.uif2.actions.ActionInfo;
import nc.vo.mmpps.mpm.res.MpmRes;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.mmpps.mps0202.BillstatusEnum;
import nc.vo.mmpps.mps0202.PoVO;
import nc.vo.pub.BusinessException;
import net.sf.json.JSONObject;

/**
 * <b> 计划订单【订单确认】按钮 </b>
 * <p>
 * 详细描述功能
 * </p>
 * 创建日期:2010-3-26
 * 
 * @author:zhaohyc
 */
public class PloConfirmAction extends BatchProcessAction {

    private static final long serialVersionUID = -729647395317395274L;

    public void doAction(ActionEvent e) throws Exception{
    	super.doAction(e);
    	//"50040006"MPS计划订单功能编码
    	if("50040006".equals(getModel().getContext().getNodeCode())){
    		Map<String, String> res = UploadBill2MES();
    		MessageDialog.showHintDlg(null, "数据上传提示", res.toString());
    	}
    }
    private Map<String, String> UploadBill2MES() throws BusinessException{
    	IUploadBillWSService service = NCLocator.getInstance().lookup(IUploadBillWSService.class);
    	PloManageAppModel model = (PloManageAppModel) getModel();
    	Object[] objs = model.getSelectedOperaDatas();
    	AggregatedPoVO[] poVOs = ArrayClassConvertUtil.convert(objs, AggregatedPoVO.class);
    	Map<String, String> maps = new HashMap<String, String>();
    	for(AggregatedPoVO poVO : poVOs){
    		String res = service.uploadBill("mainplan", service.bill2Json("mainplan", poVO));
    		if(res != null && res.contains("successinfo")){
    			maps.put(poVO.getParentVO().getVbillcode(), "上传成功");
    		}else{
    			maps.put(poVO.getParentVO().getVbillcode(), "上传MES失败:【"+res+"】");
    		}
    	}
    	return maps;
    }
    
    public PloConfirmAction() {
        ActionInfo info = PLOActionRegistry.getActionInfo(PloActionCode.PLOCONFIRMACTION);
        ActionInitializer.initializeAction(this, info.getCode(), info.getName(), info.getShort_description(), info.getKeyStroke());
        this.putValue(PloActionCode.PLOCONFIRMACTION, MpmRes.BTN_CONFIRM());
    }

    @Override
    protected boolean isActionEnable() {
        if (!super.isActionEnable()) {
            return false;
        }
        // 判空
        Object[] objs = this.getBatchProcessor().getModel().getSelectedOperaDatas();
        // 状态控制公共类
        return PoActionsUtil.isActionEnable(objs, BillstatusEnum.PLAN.intValue(), this.getBatchProcessor().getModel());
    }

}
