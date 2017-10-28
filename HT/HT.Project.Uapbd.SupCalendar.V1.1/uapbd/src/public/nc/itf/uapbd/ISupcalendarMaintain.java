package nc.itf.uapbd;

import nc.vo.uapbd.supcalendar.SupCalendarBaseVO;
import nc.vo.pub.BusinessException;

public interface ISupcalendarMaintain {

	public void delete(SupCalendarBaseVO vo) throws BusinessException;

	public SupCalendarBaseVO insert(SupCalendarBaseVO vo) throws BusinessException;

	public SupCalendarBaseVO update(SupCalendarBaseVO vo) throws BusinessException;

	public SupCalendarBaseVO[] query(String whereSql) throws BusinessException;
}