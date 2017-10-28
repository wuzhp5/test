package nc.pubimpl.ic.extend.bc.rule;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import nc.impl.pubapp.pattern.rule.IRule;
import nc.pubimpl.ic.extend.bc.BCICBackRelaTool;
import nc.vo.ic.extend.bc.ICBackRelaVO;
import nc.vo.ic.general.define.ICBillVO;
import nc.vo.ic.general.define.MetaNameConst;
import nc.vo.ic.pub.util.VOEntityUtil;
import nc.vo.pub.BusinessException;
import nc.vo.pub.CircularlyAccessibleValueObject;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

public class SignICBillCheckBCBackRelationRule implements IRule<ICBillVO> {

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
    
    ICBackRelaVO[] queryVOs = null;
    try {
      queryVOs =
          BCICBackRelaTool.queryByPK(cgeneralbids.toArray(new String[0]));
    }
    catch (BusinessException e) {
      ExceptionUtils.wrappException(e);
    }

    if (queryVOs == null || queryVOs.length == 0) {
      return;
    }

    List<String> notBackBIDs = new ArrayList<String>();
    for (ICBackRelaVO icBackRelaVO : queryVOs) {
      // �������û�лش������ݣ�������ǩ��
      if (!icBackRelaVO.getBback().booleanValue()) {
        notBackBIDs.add(icBackRelaVO.getCgeneralbid());
      }
    }

    if (notBackBIDs.size() > 0) {
      // �Ȳ���ʾ���к��ˣ��Ժ���Ҫ�ټ�
      // Map<String, String> bidAndRowNoMap = new HashMap<String, String>();
      // for (ICBillVO vo : vos) {
      // CircularlyAccessibleValueObject[] bodys = vo.getAllChildrenVO();
      // if (bodys != null && bodys.length > 0) {
      // for (CircularlyAccessibleValueObject body : bodys) {
      // String cgeneralBID =
      // (String) body.getAttributeValue(MetaNameConst.CGENERALBID);
      // String crowno =
      // (String) body.getAttributeValue(MetaNameConst.CROWNO);
      // bidAndRowNoMap.put(cgeneralBID, crowno);
      // }
      // }
      // }

      String erroStrFinal = "�õ��ݴ���û�д������豸�лش������ݣ������Խ���ǩ�֣���ش�������ɺ��ٽ���ǩ�ֲ�����";

      ExceptionUtils.wrappBusinessException(erroStrFinal);
    }
  }
}
