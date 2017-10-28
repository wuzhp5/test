package nc.impl.uapbd;

import nc.impl.pub.ace.AceSupcalendarPubServiceImpl;
import nc.itf.uapbd.ISupcalendarMaintain;
import nc.vo.uapbd.supcalendar.SupCalendarBaseVO;
import nc.vo.pub.BusinessException;

public class SupcalendarMaintainImpl extends AceSupcalendarPubServiceImpl implements ISupcalendarMaintain {

      @Override
    public void delete(SupCalendarBaseVO vos) throws BusinessException {
        super.deletetreeinfo(vos);
    }
  
      @Override
    public SupCalendarBaseVO insert(SupCalendarBaseVO vos) throws BusinessException {
        return super.inserttreeinfo(vos);
    }
    
      @Override
    public SupCalendarBaseVO update(SupCalendarBaseVO vos) throws BusinessException {
        return super.updatetreeinfo(vos);    
    }
  
      @Override
    public SupCalendarBaseVO[] query(String whereSql)
        throws BusinessException {
        return super.querytreeinfo(whereSql);
    }

  
}
