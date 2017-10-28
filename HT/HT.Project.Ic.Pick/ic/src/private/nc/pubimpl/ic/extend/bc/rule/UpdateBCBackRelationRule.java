package nc.pubimpl.ic.extend.bc.rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubimpl.ic.extend.bc.BCICBackRelaTool;
import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.pub.util.VOEntityUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * ����ش�NC����ʱ�����ⵥ��ʵ������������Ҫ���¹�ϵ�������Ƿ��ѻش�������Ϊ��Y��
 * 
 * @since 6.5
 * @version 2016-1-28 ����12:42:15
 * @author jilu
 */
public class UpdateBCBackRelationRule implements IRule<ICBillVO> {

  @Override
  public void process(ICBillVO[] vos) {
    if (vos == null || vos.length == 0) {
      return;
    }

    List<CircularlyAccessibleValueObject> bodyList =
        new ArrayList<CircularlyAccessibleValueObject>();
    for (ICBillVO vo : vos) {
      CircularlyAccessibleValueObject[] bodys = vo.getAllChildrenVO();
      if (bodys != null && bodys.length > 0) {
        bodyList.addAll(Arrays.asList(bodys));
      }
    }
    if (bodyList.size() == 0) {
      return;
    }

    Set<String> cgeneralbids =
        VOEntityUtil.getVOsValueSet(
            bodyList.toArray(new CircularlyAccessibleValueObject[0]),
            MetaNameConst.CGENERALBID);
    if (cgeneralbids == null || cgeneralbids.size() == 0) {
      return;
    }

    try {
      BCICBackRelaTool.setBBack(cgeneralbids.toArray(new String[0]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }
  }

}
