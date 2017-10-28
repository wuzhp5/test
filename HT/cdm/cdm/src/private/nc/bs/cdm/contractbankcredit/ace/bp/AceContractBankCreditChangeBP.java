package nc.bs.cdm.contractbankcredit.ace.bp;

import java.util.ArrayList;
import java.util.List;

import nc.bs.cdm.contractbankcredit.plugin.bpplugin.ContractBankCreditPluginPoint;
import nc.bs.cdm.contractbankcredit.rule.AfterSaveWriteBackRule;
import nc.bs.cdm.contractbankcredit.rule.AfterUpdateWriteBackRule;
import nc.bs.framework.common.NCLocator;
import nc.bs.pub.contract.rule.BankCreditContractBillIspayreleaseCheckRule;
import nc.bs.pub.contract.rule.ContractBillCodeCheckRule;
import nc.bs.pub.contract.rule.ContractChangeAfterRule;
import nc.bs.pub.contract.rule.ContractExtendDateRule;
import nc.bs.pub.contract.rule.ContractGuaEndDateRule;
import nc.bs.pub.contract.rule.ContractRePlanRule;
import nc.bs.pub.contract.rule.ContractUseCCRule;
import nc.bs.pub.contract.rule.ContractVersionDateRule;
import nc.bs.pub.util.ContractVOConvert;
import nc.bs.pubapp.pub.rule.FieldLengthCheckRule;
import nc.bs.pubapp.pub.rule.FillUpdateDataRule;
import nc.bs.tmpub.contract.rule.RateStopCheckRule;
import nc.bs.tmpub.version.VersionInsertBP;
import nc.impl.pubapp.pattern.data.bill.template.UpdateBPTemplate;
import nc.impl.pubapp.pattern.rule.IRule;
import nc.impl.pubapp.pattern.rule.processer.CompareAroundProcesser;
import nc.pubitf.gpm.guarantee.IGuaConMaintainService;
import nc.vo.cdm.contractbankcredit.AggContractBankCreditVO;
import nc.vo.cdm.contractbankcredit.ContractBankCreditGpmInfoVO;
import nc.vo.jcom.lang.StringUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.BusinessRuntimeException;
import nc.vo.pub.VOStatus;
import nc.vo.pub.contract.AggContractVO;
import nc.vo.pub.contractversion.AggContractVersionVO;
import nc.vo.pub.lang.UFDate;
import nc.vo.pub.lang.UFDouble;
import nc.vo.tm.pub.TMBusConstant;
import nc.vo.tm.pub.TMPublicUtil;

/**
 * 变更保存的BP
 * 
 */
@SuppressWarnings("unchecked")
public class AceContractBankCreditChangeBP {

	public AggContractBankCreditVO[] change(AggContractBankCreditVO[] bills,
			AggContractBankCreditVO[] originBills) {

		setHeadVOStatus(bills);
		// 调用修改模板
		UpdateBPTemplate<AggContractBankCreditVO> bp = new UpdateBPTemplate<AggContractBankCreditVO>(
				ContractBankCreditPluginPoint.UPDATE);

		// 执行前规则
		this.addBeforeRule(bp.getAroundProcesser());

		// 执行后规则
		this.addAfterRule(bp.getAroundProcesser());
		AggContractBankCreditVO[] aggvo = bp.update(bills, originBills);
		
		//变更增加版本
		VersionInsertBP<AggContractBankCreditVO, AggContractVersionVO> verBp = new VersionInsertBP<AggContractBankCreditVO, AggContractVersionVO>();
		AggContractBankCreditVO[] changeBills = verBp.insert(aggvo);
		
		List<AggContractVO> list = new ArrayList<AggContractVO>();
		for(int i=0;i<changeBills.length;i++){
			if(!StringUtil.isEmptyWithTrim(changeBills[i].getParentVO().getPk_guarantee()) || ("36FB".equals(changeBills[i].getParentVO().getPk_billtypecode()) && changeBills[i].getChildren(ContractBankCreditGpmInfoVO.class)!=null && changeBills[i].getChildren(ContractBankCreditGpmInfoVO.class).length>0)){
				list.add(changeBills[i]);
			}
		}
		//占用担保接口
		try {
			// 判断担保管理是否启用
			if (originBills != null && originBills.length > 0 && checkGuaContract(changeBills[0],originBills[0])) {
				String pk_group = originBills[0].getParentVO().getPk_group();
				if (TMPublicUtil.isEnable(pk_group,
						TMBusConstant.OUTER_FUNCODE_GPM)) {
					IGuaConMaintainService guaservice = NCLocator.getInstance()
							.lookup(IGuaConMaintainService.class);
					guaservice.freeGuaContract(ContractVOConvert
							.convertGuaContractQuoteVO(originBills));
					if (list == null || list.isEmpty()) {
						return changeBills;
					}
					guaservice.quoteGuaContract(ContractVOConvert
							.convertGuaContractQuoteVO(changeBills));
				}
			}
		} catch (BusinessException e) {
			throw new BusinessRuntimeException(e.getMessage(), e);
		}
		return changeBills;
	}
	
	private void setHeadVOStatus(AggContractBankCreditVO[] clientBills) {
        for (AggContractBankCreditVO clientBill : clientBills) {
            clientBill.getParentVO().setStatus(VOStatus.UPDATED);
        }
    }

	private void addAfterRule(CompareAroundProcesser<AggContractBankCreditVO> processer) {
		//wuzhp 回写收函登记
		processer.addAfterRule(new AfterUpdateWriteBackRule());
	}

	private void addBeforeRule(
			CompareAroundProcesser<AggContractBankCreditVO> processer) {
		// 修改保存时的数据补充规则
		IRule<AggContractBankCreditVO> updateDataRule = new FillUpdateDataRule();
		processer.addBeforeRule(updateDataRule);
		// 展期日期校验规则
		IRule<AggContractBankCreditVO> contractExtendDateRule = new ContractExtendDateRule();
		processer.addBeforeRule(contractExtendDateRule);
		// 展期日期和担保债务日期校验规则
		IRule<AggContractBankCreditVO> contractGuaEndDateRule = new ContractGuaEndDateRule();
		processer.addBeforeRule(contractGuaEndDateRule);
		// 单据字段长度规则
		IRule<AggContractBankCreditVO> lengthCheckRule = new FieldLengthCheckRule();
		processer.addBeforeRule(lengthCheckRule);
		// 单据号规则验证
		IRule<AggContractBankCreditVO> billCodeDuplicateRule = new ContractBillCodeCheckRule();
		processer.addBeforeRule(billCodeDuplicateRule);
		//还本释放额度验证
		IRule<AggContractBankCreditVO> bankCreditContractBillIspayreleaseCheckRule = new BankCreditContractBillIspayreleaseCheckRule();
		processer.addBeforeRule(bankCreditContractBillIspayreleaseCheckRule);
		// 版本日期校验规则
		IRule<AggContractBankCreditVO> versionDateRule = new ContractVersionDateRule();
		processer.addBeforeRule(versionDateRule);
		
		//补充还款计划中放款计划PK
		IRule<AggContractBankCreditVO> replanRule = new ContractRePlanRule();
		processer.addBeforeRule(replanRule);
		
		IRule<AggContractBankCreditVO> changeRule = new ContractChangeAfterRule();
		processer.addBeforeRule(changeRule);
		
		//利率停用检查
		processer.addBeforeRule(new RateStopCheckRule());
	}
	/**
	 * 检查业务单据的担保合同
	 * @param changeBills
	 * @param origBills
	 * @return
	 */
	private boolean checkGuaContract(AggContractBankCreditVO changeBill , AggContractBankCreditVO origBill)
 {
		ContractBankCreditGpmInfoVO[] newGpminfos = (ContractBankCreditGpmInfoVO[]) changeBill.getChildren(ContractBankCreditGpmInfoVO.class);
		ContractBankCreditGpmInfoVO[] oldGpminfos = (ContractBankCreditGpmInfoVO[]) origBill.getChildren(ContractBankCreditGpmInfoVO.class);
		// 变更前后担保数量不同：需要变更担保信息
		if (newGpminfos.length != oldGpminfos.length) {
			return true;
		}
		for (ContractBankCreditGpmInfoVO newgpminfo : newGpminfos) {
			String newPk_guaContract = newgpminfo.getPk_guarantee();
			UFDouble new_guaranteeamount = newgpminfo.getGuaranteeamount();
			int i=0;
			for (ContractBankCreditGpmInfoVO oldgpminfo : oldGpminfos) {
				String oldPk_guaContract = oldgpminfo.getPk_guarantee();
				UFDouble old_guaranteeamount = oldgpminfo.getGuaranteeamount();
				if (checkEquals(newPk_guaContract, oldPk_guaContract)) {
					// V63 增加展期时，债务结束日期取展期到期日处理
					UFDate new_extenddate = changeBill.getParentVO().getExtenddate();
					UFDate old_extenddate = origBill.getParentVO().getExtenddate();
					if (!((new_extenddate == null && old_extenddate == null) || (new_extenddate != null && old_extenddate != null && new_extenddate.isSameDate(old_extenddate)))) {
						return true;
					}
					if (!checkEquals(new_guaranteeamount, old_guaranteeamount)) {
						return true;
					}
					break;
				}else{
					i=i+1;
				}
				if(i==oldGpminfos.length){
					return true;
				}
			}
		}
		/*
		 * String newPk_guaContract =
		 * changeBill.getParentVO().getPk_guarantee(); String oldPk_guaContract
		 * = origBill.getParentVO().getPk_guarantee();
		 * if(checkEquals(newPk_guaContract,oldPk_guaContract)) { // V63
		 * 增加展期时，债务结束日期取展期到期日处理 UFDate new_extenddate =
		 * changeBill.getParentVO().getExtenddate(); UFDate old_extenddate =
		 * origBill.getParentVO().getExtenddate(); if (!((new_extenddate == null
		 * && old_extenddate == null) || (new_extenddate != null &&
		 * old_extenddate != null && new_extenddate
		 * .isSameDate(old_extenddate)))) { return true; }
		 * 
		 * UFDouble new_guaranteeamount =
		 * changeBill.getParentVO().getGuaranteeamount(); UFDouble
		 * old_guaranteeamount = origBill.getParentVO().getGuaranteeamount();
		 * if(checkEquals(new_guaranteeamount,old_guaranteeamount)) { flag =
		 * false; } }
		 */
		return false;
	}
	
	private boolean checkEquals(String oneStr , String nextStr)
	{
		boolean flag = false;
		if(null == oneStr)
		{
			oneStr = "";
		}
		if(null == nextStr)
		{
			nextStr = "";
		}
		if(oneStr.equals(nextStr))
		{
			flag = true;
		}
		return flag;
	}
	private boolean checkEquals(UFDouble oneDouble,UFDouble nextDouble)
	{
		boolean flag = false;
		if(null == oneDouble)
		{
			oneDouble = UFDouble.ZERO_DBL;
		}
		if(null == nextDouble)
		{
			nextDouble = UFDouble.ZERO_DBL;
		}
		if(oneDouble.equals(nextDouble))
		{
			flag = true;
		}
		return flag;
	}

}
