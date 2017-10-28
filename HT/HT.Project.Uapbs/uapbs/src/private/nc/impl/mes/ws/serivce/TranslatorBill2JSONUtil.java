package nc.impl.mes.ws.serivce;

import nc.bs.framework.common.NCLocator;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.ColumnProcessor;
import nc.vo.cof.orderplan.entity.AggOrderplanVO;
import nc.vo.cof.orderplan.entity.OrderplanBTPVO;
import nc.vo.cof.orderplan.entity.OrderplanBVO;
import nc.vo.cof.orderplan.entity.OrderplanVO;
import nc.vo.ic.general.define.ICBillBodyVO;
import nc.vo.ic.general.define.ICBillHeadVO;
import nc.vo.ic.m4e.entity.TransInVO;
import nc.vo.mmpps.mps0202.AggregatedPoVO;
import nc.vo.mmpps.mps0202.PoVO;
import nc.vo.pub.BusinessException;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TranslatorBill2JSONUtil {

	/**
	 * ������ⵥ
	 * @param inVO
	 * @return
	 */
	public static String transBill2Json(TransInVO inVO) {
		ICBillHeadVO headVO = inVO.getParentVO();
		ICBillBodyVO[] bodyVOs = inVO.getChildrenVO();
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		json.put("vtranscode", headVO.getVbillcode());
		json.put("mesrequestcode", headVO.getVdef4() == null ? "" : headVO.getVdef4());
		json.put("cdeptid",translator4Stockorg(headVO.getPk_org()));
		for (int i = 0; i < bodyVOs.length; i++) {
			JSONObject item = new JSONObject();
			item.put("materialcode", translator4Material(bodyVOs[i].getCmaterialvid()));
			item.put("outcount", bodyVOs[i].getNnum().toString());
			item.put("unitcode", translator4Unit(bodyVOs[i].getCmaterialvid()));
			item.put("outdt", bodyVOs[i].getDbizdate().toString());
			json.put("erpstaffcode", translator4Maker(headVO.getBillmaker()));
			array.add(i, item);
		}
		json.put("item", array);
		return json.toString();
	}

	/**
	 * 4�¼ƻ�����
	 * @param aggVO
	 * @return
	 */
	public static String planBill2Json(AggOrderplanVO aggVO){
		OrderplanVO planvo = aggVO.getParentVO();
		OrderplanBVO[] planbvo = aggVO.getChildrenBVO();
		JSONArray arrayb = new JSONArray();
		JSONObject orderobj = new JSONObject();
		//�ƻ����
		orderobj.put("vplancode",planvo.getVbillcode());
		orderobj.put("cdeptid",translator4Salesorg(planvo.getPk_org()));
		orderobj.put("creator",translator4Maker(planvo.getBillmaker()));
		for(int i = 0;i<planbvo.length;i++){
		  JSONObject orderobjb = new JSONObject();	
		  //����
		  orderobjb.put("productcode", translator4Material(planbvo[i].getCmaterialvid()));
		  //����λ	
		  orderobjb.put("unitcode", translator4Unit(planbvo[i].getCmaterialvid()));
		  OrderplanBTPVO[] planbtpvos = planbvo[i].getOrderplanBTPVOs();
		  JSONArray arraybtp = new JSONArray();
		  for(OrderplanBTPVO planbtpvo : planbtpvos){
			  if(planbtpvo.getNconfirmedastnum() == null){
				  continue;
			  }
			  JSONObject orderobjbpt = new JSONObject();
			  //��ʼ����
			  orderobjbpt.put("vplanstartdt", planbtpvo.getDbegindate().toString() );
			  //��������
			  orderobjbpt.put("vplanenddt", planbtpvo.getDenddate().toString());
			  // �ƻ�������
			  orderobjbpt.put("plancount", planbtpvo.getNconfirmedastnum().toString());
			  arraybtp.add(orderobjbpt);
		  }
		  orderobjb.put("orderplanbtp", arraybtp);
		  arrayb.add(i, orderobjb);
		}
		orderobj.put("orderplanb", arrayb);
		String strobj = orderobj.toString();
		return  strobj;
	}

	/**
	 * mps�ƻ�����
	 * 
	 * @param po
	 * @return
	 */
	public static String ploBill2Json(AggregatedPoVO aggVO) {
		PoVO po = aggVO.getParentVO();
		JSONObject json = new JSONObject();
		json.put("vplancode", po.getVbillcode()); // �ƻ�������
		json.put("cdeptid",translator4Factory(po.getCstockorgid()));
		json.put("creator", translator4Maker(po.getBillmaker()));
		json.put("vplanstartdt", po.getDplanstarttime().toString()); // �ƻ���ʼ����
		json.put("vplanenddt", po.getDplansupplytime().toString()); // ������������
		JSONObject item = new JSONObject();
		item.put("productcode", translator4Material(po.getCmaterialvid())); // ���ϱ���
		item.put("unitcode", translator4Unit(po.getCmaterialvid())); // ����λ
		item.put("manudt", po.getDbilltime().toString()); // �µ�����
		item.put("plancount", po.getNnumber().toString()); // �ƻ�����������
		JSONArray array = new JSONArray();
		array.add(item);
		json.put("item", array);
		return json.toString();
	}

	private static String translator(String sql) {
		try {
			return (String) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql, new ColumnProcessor());
		} catch (BusinessException e) {
			throw new RuntimeException(e.getMessage());
		}
	}

	private static String translator4Maker(String billmaker) {
		if (billmaker == null) {
			return null;
		}
		String sql = "select user_code from sm_user where cuserid = '" + billmaker + "'";
		return translator(sql);
	}

	private static String translator4Unit(String pk_material) {
		if (pk_material == null) {
			return null;
		}
		String sql = "select name from bd_measdoc where pk_measdoc in (select pk_measdoc from bd_material where pk_material = '" + pk_material + "')";
		return translator(sql);
	}

	private static String translator4Material(String pk_material) {
		if (pk_material == null) {
			return null;
		}
		String sql = "select code from bd_material where pk_material = '" + pk_material + "'";
		return translator(sql);
	}
	
	private static String translator4Salesorg(String pk_salesorg){
		if(pk_salesorg == null){
			return null;
		}
		String sql = "select code from org_salesorg where pk_salesorg = '"+pk_salesorg+"'";
		return translator(sql);
	}
	
	private static String translator4Stockorg(String pk_stockorg){
		if(pk_stockorg == null){
			return null;
		}
		String sql = "select code from org_stockorg where pk_stockorg = '"+pk_stockorg+"'";
		return translator(sql);
	}
	
	private static String translator4Factory(String pk_factory){
		if(pk_factory == null){
			return null;
		}
		String sql = "select code from org_factory where pk_factory = '"+pk_factory+"'";
		return translator(sql);
	}

}
