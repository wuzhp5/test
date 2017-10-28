package nc.impl.pub.ace;

import nc.bs.bd.baseservice.md.SingleBaseService;
import nc.bs.uapbd.supcalendar.CalulateDateProcesser;
import nc.impl.pubapp.pattern.data.vo.VOQuery;
import nc.impl.pubapp.pattern.rule.processer.AroundProcesser;
import nc.vo.pub.BusinessException;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;
import nc.vo.uapbd.supcalendar.SupCalendarBaseVO;
import nc.vo.uapbd.supcalendar.SupcalendarDateVO;
import nc.vo.uapbd.supcalendar.SupcalendarMaterialVO;

public abstract class AceSupcalendarPubServiceImpl {
	private SingleBaseService<SupCalendarBaseVO> service = new SingleBaseService<SupCalendarBaseVO>("488a4c01-f399-4322-ab70-13154179d377",
			new String[] { "supcalendar_d", "supcalendar_m" });

	// 增加方法
	public SupCalendarBaseVO inserttreeinfo(SupCalendarBaseVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<SupCalendarBaseVO> processer = new AroundProcesser<SupCalendarBaseVO>(null);
			processer.addBeforeFinalRule(new CalulateDateProcesser());
			processer.before(new SupCalendarBaseVO[] { vo });
			SupCalendarBaseVO baseVO = service.insertVO(vo);
			return baseVO;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 删除方法
	public void deletetreeinfo(SupCalendarBaseVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<SupCalendarBaseVO> processer = new AroundProcesser<SupCalendarBaseVO>(null);
			processer.before(new SupCalendarBaseVO[] { vo });
			service.deleteVO(vo);
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}

	}

	// 修改方法
	public SupCalendarBaseVO updatetreeinfo(SupCalendarBaseVO vo) throws BusinessException {
		try {
			// 添加BP规则
			AroundProcesser<SupCalendarBaseVO> processer = new AroundProcesser<SupCalendarBaseVO>(null);
			processer.addBeforeFinalRule(new CalulateDateProcesser());
			processer.before(new SupCalendarBaseVO[] { vo });
			//SupCalendarBaseVO[] originVOs = this.getTreeCardVOs(new SupCalendarBaseVO[] { vo });
			SupCalendarBaseVO baseVO = service.updateVO(vo);
			return baseVO;
		} catch (Exception e) {
			ExceptionUtils.marsh(e);
		}
		return null;
	}

	// 查询方法
	public SupCalendarBaseVO[] querytreeinfo(String whereSql) throws BusinessException {
		VOQuery<SupCalendarBaseVO> baseQuery = new VOQuery<SupCalendarBaseVO>(SupCalendarBaseVO.class);
		VOQuery<SupcalendarDateVO> dateQuery = new VOQuery<SupcalendarDateVO>(SupcalendarDateVO.class);
		VOQuery<SupcalendarMaterialVO> mateQuery = new VOQuery<SupcalendarMaterialVO>(SupcalendarMaterialVO.class);
		SupCalendarBaseVO[] baseVOs = baseQuery.query(whereSql, null);
		for(SupCalendarBaseVO baseVO : baseVOs){
			SupcalendarDateVO[] dateVOs = dateQuery.query(" and pk_supcalendar = '"+baseVO.getPk_supcalendar()+"'", null);
			baseVO.setSupcalendar_d(dateVOs);
			SupcalendarMaterialVO[] mateVOs = mateQuery.query(" and pk_supcalendar = '"+baseVO.getPk_supcalendar()+"'", null);
			baseVO.setSupcalendar_m(mateVOs);
		}
		return baseVOs;
	}
}