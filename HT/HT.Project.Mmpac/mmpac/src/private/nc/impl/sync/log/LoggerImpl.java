package nc.impl.sync.log;

import nc.bs.dao.BaseDAO;
import nc.itf.sync.log.ILogger;
import nc.vo.pub.BusinessException;
import nc.vo.sync.log.LoggerVO;

public class LoggerImpl implements ILogger{

	BaseDAO baseDAO = new BaseDAO();
	@Override
	public void log_RequiresNew(LoggerVO vo) throws BusinessException {
		StringBuffer sql =  new StringBuffer();
		sql.append("insert into xx_sync_log ("+LoggerVO.PK_BILL+","+LoggerVO.VBILLCODE+","+LoggerVO.MESCODE+","+LoggerVO.BILLTYPE+","+LoggerVO.ISSUCCESS+","+LoggerVO.CONTENT+","+LoggerVO.RES+") values ");
		sql.append("('"+vo.getPk_bill()+"','"+vo.getVbillcode()+"','"+vo.getMescode()+"','"+vo.getBilltype()+"','"+vo.getIssuccess().toString()+"','"+vo.getContent()+"','"+vo.getRes()+"')");
		baseDAO.executeUpdate(sql.toString());
	}

}
