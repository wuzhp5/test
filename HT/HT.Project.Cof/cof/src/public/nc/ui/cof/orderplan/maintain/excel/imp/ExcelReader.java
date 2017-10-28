package nc.ui.cof.orderplan.maintain.excel.imp;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import nc.vo.cof.orderplan.entity.AggOrderplanVO;
import nc.vo.cof.orderplan.entity.OrderplanBVO;
import nc.vo.cof.orderplan.entity.OrderplanVO;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.poi.ss.usermodel.Sheet;

import com.yonyou.ec.plan.entity.BillTplConfigVO;

/**
 * excel读取器
 *
 * @since 6.0
 * @version 2013-10-31 下午02:25:24
 * @author ruhuic
 */
public class ExcelReader {
  private ReadExcelResult resultMessage = new ReadExcelResult();

  private Sheet sheet;

  private List<ICheckRule> checkRules = new ArrayList<ICheckRule>();

  private ParentInfoReader parentreader;

  private ChildInfoReader childReader;

  private GrandsonInfoReader grandsonReader;

  public void setSheet(Sheet sheet) {
    this.sheet = sheet;
  }

  public Sheet getSheet() {
    return this.sheet;
  }

  public void addCheckRule(ICheckRule rule) {
    this.checkRules.add(rule);
  }

  public void setParentReader(ParentInfoReader parentreader) {
    this.parentreader = parentreader;
  }

  public void setChildReader(ChildInfoReader childReader) {
    this.childReader = childReader;
  }

  public ReadExcelResult read() {
    // 校验sheet
    List<String> checkErrorMsg = this.processRules();
    if (checkErrorMsg != null && checkErrorMsg.size() > 0) {
      resultMessage.setSeriousError(checkErrorMsg);
      return resultMessage;
    }
    // 主表校验

    List<String> parentCheckMsg = this.parentreader.initSign();
    if (parentCheckMsg != null && parentCheckMsg.size() > 0) {
      resultMessage.setSeriousError(parentCheckMsg);
      return resultMessage;
    }
    // 子表校验
    List<String> childCheckMsg = this.childReader.initSign();
    if (childCheckMsg != null && childCheckMsg.size() > 0) {
      resultMessage.setSeriousError(childCheckMsg);
      return resultMessage;
    }
    // 孙表校验
    List<String> grandsonCheckMsg = this.grandsonReader.initSign();
    if (grandsonCheckMsg != null && grandsonCheckMsg.size() > 0) {
      resultMessage.setSeriousError(grandsonCheckMsg);
      return resultMessage;
    }
    List<String> errorMsg = new ArrayList<String>();
    OrderplanVO parentVO;

    try {
      // 读取主表信息
      parentreader.setPlantemplateid(this.childReader.getPlanTemplateId());
      parentVO = this.parentreader.createParentVo();
      if (parentVO == null) {
        errorMsg.add(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0107")/*@res "没有生成主表信息，请检查excel！"*/);
        resultMessage.setSeriousError(errorMsg);
        return resultMessage;
      }
    }
    catch (Exception e) {
      errorMsg.add(e.getMessage());
      resultMessage.setSeriousError(errorMsg);
      return resultMessage;
    }
    List<String> parentMsg = this.parentreader.getErrorMsg();
    if (parentMsg != null && parentMsg.size() > 0) {
      errorMsg.addAll(parentMsg);
    }
    // 读取子表信息

    try {
      AggOrderplanVO parentkeyid = this.parentreader.getAggorderplanvo();
      childReader.setParentkeyid(parentkeyid);
      MapList<Integer, BillTplConfigVO> configvo = this.parentreader.getBillTPLConfigVO();
      this.childReader.setBillTPLConfigVO(configvo);
      String pk_orgid;
      pk_orgid = (String) this.parentreader.getPk_orgid();
      this.childReader.setPk_orgid(pk_orgid);
      OrderplanBVO[] childVo = this.childReader.createVos();
      parentVO.setCplantemplateid(this.childReader.getPlanTemplateId());
      List<String> childMsg = this.childReader.getErrorMsg();
      if (childMsg != null && childMsg.size() > 0) {
        errorMsg.addAll(childMsg);
      }
      if (childVo != null) {
        // 读取孙表信息
        this.grandsonReader.setStartTime(parentVO.getDbegindate());
        this.grandsonReader.setPlantemplateid(this.childReader
            .getPlanTemplateId());
        this.grandsonReader.setChildVo(childVo);
        this.grandsonReader.setPk_orgid(pk_orgid);
        this.grandsonReader.setGrandsonKeys(childReader.getGrandsonKeys());
        this.grandsonReader.setChildNum(this.childReader.getChildNum());
        this.grandsonReader.setChildPrimaryKeys(this.childReader
            .getChildprimarySigns());
        this.grandsonReader.setOriginAggvo(parentkeyid);
        this.grandsonReader.setChildReader(childReader);
        this.grandsonReader.setParentreader(parentreader);

        this.grandsonReader.setBillTPLConfigVO(configvo);
        this.grandsonReader.createVos();
        List<String> grandsonMsg = this.grandsonReader.getErrorMsg();
        if (grandsonMsg != null && grandsonMsg.size() > 0) {
          errorMsg.addAll(grandsonMsg);
        }
      }
      OrderplanBVO[] finalvos = getFinalchildVOS(childVo);
      resultMessage.setParentvo((OrderplanVO) parentVO);
      resultMessage.setChildvos(finalvos);
      resultMessage.setOriAggvo(parentkeyid);
      resultMessage.setOrdinaryError(errorMsg);
    }
    catch (Exception e) {
      errorMsg.add(e.getMessage());
      resultMessage.setSeriousError(errorMsg);
      return resultMessage;
    }
    return resultMessage;
  }

  private OrderplanBVO[] getFinalchildVOS(OrderplanBVO[] childVo) {
    OrderplanBVO[] finalvos = null;
    ArrayList<OrderplanBVO> deletedvos = this.childReader.getDeleteVos();
    if (deletedvos == null || deletedvos.size() == 0) {
      finalvos = childVo;
    }
    else {
      for (OrderplanBVO vo : childVo) {
        deletedvos.add(vo);
      }
      finalvos = deletedvos.toArray(new OrderplanBVO[0]);
    }
    return finalvos;
  }

  private List<String> processRules() {
    Iterator<ICheckRule> iterator = this.checkRules.iterator();
    while (iterator.hasNext()) {
      ICheckRule obj = iterator.next();
      List<String> msg = obj.check(sheet);
      if (msg != null && msg.size() != 0)
        return msg;

    }
    return null;
  }

  public void setGrandsonReader(GrandsonInfoReader grandsonReader) {
    this.grandsonReader = grandsonReader;
  }
}