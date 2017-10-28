package nc.impl.pub.ace;

import nc.impl.pubapp.pattern.data.vo.VODelete;
import nc.impl.pubapp.pattern.data.vo.VOInsert;
import nc.impl.pubapp.pattern.data.vo.VOUpdate;
import nc.vo.pub.ISuperVO;
import nc.vo.uapbd.supcalendar.SupCalendarBaseVO;
import nc.vo.uapbd.supcalendar.SupcalendarDateVO;
import nc.vo.uapbd.supcalendar.SupcalendarMaterialVO;

public class AcePubService {

	public static VOInsert<SupCalendarBaseVO> baseIns = new VOInsert<SupCalendarBaseVO>();
	public static VOInsert<SupcalendarDateVO> dateIns = new VOInsert<SupcalendarDateVO>();
	public static VOInsert<SupcalendarMaterialVO> mateIns = new VOInsert<SupcalendarMaterialVO>();
	public static VOUpdate<SupCalendarBaseVO> baseUpd = new VOUpdate<SupCalendarBaseVO>();
	public static VOUpdate<SupcalendarDateVO> dateUpd = new VOUpdate<SupcalendarDateVO>();
	public static VOUpdate<SupcalendarMaterialVO> mateUpd = new VOUpdate<SupcalendarMaterialVO>();
	public static VODelete<SupCalendarBaseVO> baseDel = new VODelete<SupCalendarBaseVO>();
	public static VODelete<SupcalendarDateVO> dateDel = new VODelete<SupcalendarDateVO>();
	public static VODelete<SupcalendarMaterialVO> mateDel = new VODelete<SupcalendarMaterialVO>();
	
	public static void fillParentPK(String column, String pk_parent, ISuperVO[] vos){
		if(vos == null || vos.length == 0){
			return;
		}
		for(ISuperVO vo : vos){
			vo.setAttributeValue(column, pk_parent);
		}
	}

}
