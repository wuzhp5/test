package nc.impl.sync.param;

import nc.bs.framework.common.NCLocator;
import nc.itf.sync.param.ISyncParamService;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.BeanProcessor;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.pub.PubAppTool;
import nc.vo.sync.param.BomParams;
import nc.vo.sync.param.MaterialParams;

public class SyncParamServiceImpl implements ISyncParamService{

	private IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);

	@Override
	public MaterialParams queryMaterialParams(String code) throws BusinessException {
		if(PubAppTool.isNull(code)){
			throw new BusinessException("方法【nc.impl.sync.param.SyncParamServiceImpl.queryMaterialParams(String code)】的参数不能为空！");
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select mate.pk_material,mate.pk_source as pk_material_v,mate.code,mate.name,mate.pk_group,");
		sql.append("mate.pk_org,mate.pk_measdoc,conv.pk_measdoc as pk_apartmeasdoc,conv.measrate ");
		sql.append("from bd_material_v mate left join bd_materialconvert conv on mate.pk_material = conv.pk_material ");
		sql.append("where mate.code = '"+code.trim()+"' ");
		MaterialParams param = (MaterialParams) this.bs.executeQuery(sql.toString(), new BeanProcessor(MaterialParams.class));
		if(param == null){
			throw new BusinessException("NC不存在物料编码【"+code+"】！");
		}
		return param;
	}

	@Override
	public BomParams queryDefaultBomParams(String pk_group, String useorgcode, String hcmaterialid, String hcmaterialvid) throws BusinessException {
		if(PubAppTool.isNull(pk_group) || PubAppTool.isNull(useorgcode) || PubAppTool.isNull(hcmaterialid) || PubAppTool.isNull(hcmaterialvid)){
			throw new BusinessException("方法【nc.impl.sync.param.SyncParamServiceImpl.queryDefaultBomParams(String pk_group, String pk_useorg," +
					" String hcmaterialid, String hcmaterialvid)】的参数都不能为空！");
		}
		StringBuffer sql = new StringBuffer();
		sql.append("select cbomid,hbdefault,hcmaterialid,hversion,pk_org,pk_org_v,hfbomsource,hfversiontype,fbomtype,fbillstatus,pk_group ");
		sql.append("from bd_bom ");
		sql.append("where pk_org in ( select useorg.pk_org from bd_bom_useorg useorg  where cbomid=useorg.cbomid ");
		sql.append("and useorg.pk_useorg=(select pk_org from org_orgs where dr = 0 and pk_group = '"+pk_group+"' ");
		sql.append(" and enablestate = '2' and islastversion  = 'Y' and code = '"+useorgcode+"') and useorg.dr=0) ");		
		sql.append(" and pk_group = '"+pk_group+"' ");
		sql.append("and hcmaterialid='"+hcmaterialid+"'  and hcmaterialvid = '"+hcmaterialvid+"' and fbomtype=1  ");
		sql.append("and hbdefault='Y'  and hfversiontype=1  and dr=0");
		BomParams param = (BomParams) this.bs.executeQuery(sql.toString(), new BeanProcessor(BomParams.class));
		if(param == null){
			throw new BusinessException("成品未在NC中建立BOM档案，NC无法生成单据！");
		}
		return param;
	}
	
}
