package nc.ui.ic.general.action;

import java.awt.event.ActionEvent;

import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.uap.rbac.IRoleManageQuery;
import nc.ui.pub.bill.action.BillTableLineAction;
import nc.ui.pubapp.uif2app.actions.BodyDelLineAction;
import nc.ui.pubapp.uif2app.event.card.BodyRowEditType;
import nc.ui.pubapp.uif2app.event.card.CardBodyAfterRowEditEvent;
import nc.ui.pubapp.util.CardPanelValueUtils;
import nc.ui.uif2.ShowStatusBarMsgUtil;
import nc.vo.ic.pub.define.ICPubMetaNameConst;
import nc.vo.pub.lang.UFDouble;
import nc.vo.pubapp.AppContext;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.pubapp.pattern.pub.MathTool;
import nc.vo.uap.rbac.excp.RbacException;
import nc.vo.uap.rbac.role.RoleVO;

/**
 * @author Administrator
 * 
 */
public class ICBodyDelLineAction extends BodyDelLineAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2417349169118631115L;

	@Override
	protected boolean doBeforeAction(ActionEvent e) {
		// TODO 自动生成的方法存根
		if(!super.doBeforeAction(e)){
			return false;
		}

		int[] srcrows = getCardPanel().getBodyPanel().getTable()
				.getSelectedRows();
		CardPanelValueUtils valueUtil = new CardPanelValueUtils(getCardPanel());

		String userID = AppContext.getInstance().getPkUser();
		IRoleManageQuery roleQry = NCLocator.getInstance().lookup(IRoleManageQuery.class);
		try {
			RoleVO[] roles = roleQry.queryRoleByUserID(userID, null);
			if(roles != null && roles.length > 0){
				for(RoleVO role : roles){
					String code = role.getRole_code();
					if(code.startsWith("isdel_"))
						return true;
				}
			}
		} catch (RbacException e1) {
			e1.printStackTrace();
			Logger.error(e);
		}
		for (int deleterow : srcrows) {
			UFDouble num = valueUtil.getBodyUFDoubleValue(deleterow, "nnum");
			if (MathTool.absCompareTo(num, UFDouble.ZERO_DBL) > 0) {
				ShowStatusBarMsgUtil.showStatusBarMsg("实发数量大于零不能删除", getModel()
						.getContext());
				return false;
			}
		}
		
		return true;
	}
}
