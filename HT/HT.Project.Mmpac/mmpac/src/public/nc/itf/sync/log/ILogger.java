package nc.itf.sync.log;

import nc.vo.pub.BusinessException;
import nc.vo.sync.log.LoggerVO;

public interface ILogger {

	public void log_RequiresNew(LoggerVO vo) throws BusinessException;
}
