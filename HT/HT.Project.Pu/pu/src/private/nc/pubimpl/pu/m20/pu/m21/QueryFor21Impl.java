package nc.pubimpl.pu.m20.pu.m21;

import java.util.List;

import nc.bs.pu.m20.query.QueryFor21BP;
import nc.impl.pubapp.env.BSContext;
import nc.pubitf.pu.m20.pu.m21.IQuery20For21;
import nc.ui.querytemplate.filter.IFilter;
import nc.ui.querytemplate.querytree.IQueryScheme;
import nc.ui.querytemplate.value.IFieldValueElement;
import nc.vo.pu.m20.entity.PraybillItemVO;
import nc.vo.pu.m20.entity.PraybillVO;
import nc.vo.pu.pub.util.VOSortUtils;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * <p>
 * <b>������Ҫ������¹��ܣ�</b>
 * <ul>
 * <li>�빺�����ɹ�������ת����ѯʵ����
 * </ul>
 * <p>
 * <p>
 * 
 * @version 6.0
 * @since 6.0
 * @author duy
 * @time 2010-1-31 ����03:28:13
 */
public class QueryFor21Impl implements IQuery20For21 {

	@Override
	public PraybillVO[] queryPrayBills(IQueryScheme queryScheme)
			throws BusinessException {
		try {
			// 2012.4.19 lixyp Ϊ�޸ķ����Ľ�������ת����ѯ��ѯ���˷Ǵ˽������͵����ݶ������޸�
			// ������ر��޷��Ը�����֮��ǩ���ļ����ؼӴ�ע�͡�
			
			//���ͻ�ͨ"���Э��"���󿪷�
			QueryFor21BP bp = new QueryFor21BP(queryScheme);
			IFilter[] filters = (IFilter[]) queryScheme.get("filters");
			String value = findIsrefValue(filters);
			if (value != null) {
				bp.getPsor().appendWhere(appendWhereSql(value));
			}
			PraybillVO[] prayVOs = bp.queryPrayBills();
			if (prayVOs == null) {
				return null;
			}
			for (PraybillVO prayVO : prayVOs) {
				PraybillItemVO[] itemVOs = prayVO.getBVO();
				VOSortUtils.ascSort(itemVOs, PraybillItemVO.CROWNO);
				prayVO.setBVO(itemVOs);
			}
			return prayVOs;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}
	
	private String findIsrefValue(IFilter[] filters){
		  for(IFilter filter : filters){
			  if("isref".equals(filter.getFilterMeta().getFieldCode())){
				  List<IFieldValueElement> elements = filter.getFieldValue().getFieldValues();
				  if(elements == null || elements.size() == 0){
					  return "N";
				  }else{
					  return elements.get(0).getSqlString();
				  }
			  }
		  }
		  return null;
	  }
	
	private String appendWhereSql(String flag){
		StringBuffer sql = new StringBuffer();
		UFDate date = BSContext.getInstance().getDate();
		if("Y".equals(flag)){
			sql.append(" and (exists " );
		}else{
			sql.append(" and (not exists " );
		}
		sql.append("(select null from purp_supplierprice prm where nvl(prm.dr,0)= 0 and  prm.pk_material = T1.pk_material ");
		sql.append("and prm.pk_srcmaterial = T1.pk_srcmaterial and prm.pk_supplier = T1.pk_suggestsupplier and prm.pk_group = T1.pk_group ");
		sql.append("and prm.pk_org = ((select target from org_relation where nvl(dr,0) = 0 and pk_relationtype = 'PURSTOCKCONSIGN00000' and sourcer = T1.pk_org)) ");
		sql.append("and (prm.dinvaliddate >  '"+date+"'  or prm.dinvaliddate is null) and (prm.dvaliddate  < '"+date+"' or prm.dvaliddate is null))) ");
		return sql.toString();
	}

}
