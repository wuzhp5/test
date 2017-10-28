package nc.itf.sync.param;

import nc.bs.framework.common.NCLocator;
import nc.vo.pub.BusinessException;
import nc.vo.sync.param.BomParams;
import nc.vo.sync.param.MaterialParams;

public interface ISyncParamService {

	public MaterialParams queryMaterialParams(String code) throws BusinessException;
	
	public BomParams queryDefaultBomParams(String pk_group, String useorgcode, String hcmaterialid, String hcmaterialvid) throws BusinessException;
	
}
