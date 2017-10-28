package nc.ui.cof.orderplan.maintain.excel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import nc.bs.framework.common.NCLocator;
import nc.ui.cof.orderplan.maintain.excel.common.FieldItem;
import nc.ui.cof.orderplan.maintain.excel.common.InputItemForCof;
import nc.ui.trade.excelimport.InputItem;
import nc.vo.pubapp.pattern.pub.MapList;

import com.yonyou.ec.commons.utils.TempletDimenManager;
import com.yonyou.ec.plan.entity.BillTplConfigVO;
import com.yonyou.ec.plan.service.ITemplateMtnPubService;

@SuppressWarnings("restriction")
public class InputItemCreater {

	public static List<InputItem> getInputItem(String planTemplateid) {
		List<InputItem> InputItemForCofs = new ArrayList<InputItem>();
		ITemplateMtnPubService service = NCLocator.getInstance().lookup(ITemplateMtnPubService.class);
		MapList<Integer, BillTplConfigVO> voMap = service.queryBillTplConfigById(planTemplateid);
		TempletDimenManager manager = TempletDimenManager.getManager(planTemplateid);
		Set<String> noHdimentions = manager.getAllDimenCodes();
		Set<String> noBdimentions = manager.getAllDimenCodes();
		noHdimentions.removeAll(manager.getHDimenCodeSet());
		noBdimentions.removeAll(manager.getBDimenCodeSet());
		for (Entry<Integer, List<BillTplConfigVO>> entry : voMap.entrySet()) {
			List<BillTplConfigVO> configVos = entry.getValue();
			for (BillTplConfigVO configvo : configVos) {
				FieldItemClient fieldItem = new FieldItemClient(configvo);
				if (fieldItem.isNotNull() || (fieldItem.isEdit() && fieldItem.isShow())) {
					boolean isAdd = filterConfigVo(configvo, fieldItem, noHdimentions, noBdimentions);
					if (isAdd) {
						InputItemForCofs.add(fieldItem);
					}
				}
			}
		}
		return InputItemForCofs;
	}

	private static boolean filterConfigVo(BillTplConfigVO configvo, FieldItemClient fieldItem, Set<String> hdimentionSet, Set<String> bdimentionSet) {
		String tabcode = fieldItem.getTabCode();
		String itemcode = fieldItem.getItemKey();
		if (tabcode.equals("orderplan")) {
			if (hdimentionSet.contains(itemcode)) {
				return false;
			}
		} else if (tabcode.equals("orderplanrowid")) {
			if (bdimentionSet.contains(itemcode)) {
				return false;
			}
		}
		return true;
	}

	public static List<InputItemForCof> convertItems(List<InputItem> inputItems) {
		List<InputItemForCof> result = new ArrayList<InputItemForCof>();
		for (InputItem item : inputItems) {
			FieldItem toItem = new FieldItem();
			FieldItemClient itemClient = (FieldItemClient) item;
			toItem.setDimention(itemClient.getDimentionFlag());
			toItem.setItemKey(itemClient.getItemKey());
			toItem.setOrder(itemClient.getOrder());
			toItem.setPos(itemClient.getPos());
			toItem.setShowName(itemClient.getShowName());
			toItem.setTabCode(itemClient.getTabCode());
			toItem.setTabName(itemClient.getTabName());
			toItem.isEdit(itemClient.isEdit());
			toItem.isMultiLang(itemClient.isMultiLang());
			toItem.isNotNull(itemClient.isNotNull());
			toItem.isShow(itemClient.isShow());
			result.add(toItem);
			if ("castunitid".equals(itemClient.getItemKey())) {
				FieldItem fieldItem = new FieldItem();
				fieldItem.setDimention(itemClient.getDimentionFlag());
				fieldItem.setItemKey("islay");
				fieldItem.setOrder(itemClient.getOrder());
				fieldItem.setPos(itemClient.getPos());
				fieldItem.setShowName(" «∑Ò∆Ω∆Ã");
				fieldItem.setTabCode(itemClient.getTabCode());
				fieldItem.setTabName(itemClient.getTabName());
				fieldItem.isEdit(itemClient.isEdit());
				fieldItem.isMultiLang(itemClient.isMultiLang());
				fieldItem.isNotNull(true);
				fieldItem.isShow(false);
				result.add(fieldItem);
			}
		}
		return result;
	}
}
