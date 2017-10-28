package nc.ui.cof.orderplan.maintain.excel.imp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import nc.bs.framework.common.NCLocator;
import nc.md.model.type.IType;
import nc.ui.cof.orderplan.maintain.excel.common.CreateExcelSignTool;
import nc.ui.cof.orderplan.maintain.excel.common.ExportExcelSign;
import nc.ui.cof.orderplan.maintain.excel.common.FieldItem;
import nc.ui.cof.orderplan.maintain.excel.common.IDataHandler;
import nc.ui.cof.orderplan.maintain.excel.imp.itf.DataHandlerAgency;
import nc.vo.cof.orderplan.entity.AggOrderplanVO;
import nc.vo.cof.orderplan.entity.OrderplanBTPVO;
import nc.vo.cof.orderplan.entity.OrderplanBVO;
import nc.vo.cof.orderplan.pub.IMeasdocQueryServiceCof;
import nc.vo.ecpubapp.pattern.exception.ExceptionUtils;
import nc.vo.ml.NCLangRes4VoTransl;
import nc.vo.pub.BusinessException;
import nc.vo.pub.VOStatus;
import nc.vo.pubapp.pattern.pub.MapList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.yonyou.ec.plan.entity.BillTplConfigVO;
import com.yonyou.itf.plantemplate.IPlanTemplatePubService;
import com.yonyou.vo.ecapppub.plan.entity.AggPlantemplateVO;
import com.yonyou.vo.ecapppub.plan.entity.BdimensionVO;

/**
 * 子表读取器
 *
 * @since 6.0
 * @version 2013-10-31 下午02:25:00
 * @author ruhuic
 */
public class ChildInfoReader {
  /**
   * 读取的sheet
   */
  private Sheet sheet;

  /**
   * 子表属性项
   */
  private String[] itemKeys;

  /**
   * 孙表属性项
   */
  private String[] grandkeys;

  /**
   * 计划模板id
   */
  private String planTemplateId;

  /**
   * 行数
   */
  private int childNum;

  /**
   * 最后一行的行数
   */
  private int lastRowNum;

  /**
   * 主键标识
   */
  private List<String> ChildprimarySigns;

  /**
   * 错误信息记录
   */
  private List<String> errorMsg = new ArrayList<String>();

  /**
   * 更新时原始的aggvo
   */
  private AggOrderplanVO aggvo;

  /**
   * 组织id
   */
  private String pk_orgid;

  /**
   * 规划器
   */
  private ExcelRegulation regulation;

  /**
   * 表体数据为更新操作的主键list
   */
  private ArrayList<String> curPrimaryIDs = new ArrayList<String>();

  /**
   * 子表主键数组
   */
  private List<String> ChildprimaryKeys;

  /**
   * 寻求单位的服务
   */
  private IMeasdocQueryServiceCof meadocService;

  /**
   * 主体体维度编码
   */
  private List<String> subDimentionCodes;

  /**
   * 类型为参照的key-<行号-值>键值对
   */
  private Map<String, Map<Integer, String>> refValuemap =
      new HashMap<String, Map<Integer, String>>();

  private MapList<Integer, BillTplConfigVO> billConfigvo;

  public String[] getGrandsonKeys() {
    return this.grandkeys;
  }

  public ChildInfoReader(Sheet sheet) {
    this.sheet = sheet;
  }

  /**
   * 读取子表主键标识
   *
   * @return
   */
  public List<String> getChildprimarySigns() {
    if (this.ChildprimarySigns != null)
      return this.ChildprimarySigns;
    List<Row> bodyvalueRows = this.getBodyValueRows();
    List<String> result = new ArrayList<String>();
    for (Row bodyvaluerow : bodyvalueRows) {
      Cell cell = bodyvaluerow.getCell(0);
      ExcelReadUtil util = new ExcelReadUtil();
      try {
        Object value;
        value = util.getCellValue(cell);
        if (value == null) {
          result.add("null");
        }
        else {
          result.add((String) value);
        }
      }
      catch (Exception e) {
        getErrorMsg().add(e.getMessage());
      }

    }
    this.ChildprimarySigns = result;
    return this.ChildprimarySigns;
  }

  /**
   * 读取隐藏列的子表标识，其中标识内容为导出的子表项；
   * 计算子表的最后一列
   *
   * @return
   */
  public List<String> initSign() {
    List<String> errorMsg = new ArrayList<String>();
    Cell markCell =
        sheet.getRow(this.regulation.getSignPos()[0]).getCell(
            this.regulation.getSignPos()[1]);
    ExcelReadUtil util = new ExcelReadUtil();

    try {
      Object headSign =
          util.getCellValue(markCell, this.regulation.getSignPos()[0],
              this.regulation.getSignPos()[1]);
      String[] itemKeys = convertSign((String) headSign);
      this.itemKeys = itemKeys;
      this.lastRowNum = this.getLastRowNum();
      List<Row> bodyvalueRows = getBodyValueRows();
      this.setChildNum(bodyvalueRows.size());
    }
    catch (Exception e) {
      errorMsg.add(e.getMessage());
      return errorMsg;
    }
    return null;

  }

  private List<String> processRules() {

    this.sheet.getNumMergedRegions();

    return null;
  }

  /**
   * 转换标识
   *
   * @param headSign
   * @return
   * @throws BusinessException
   */
  private String[] convertSign(String headSign) throws BusinessException {
    Map<String, String[]> bodysignMap =
        CreateExcelSignTool.reverceSign(headSign);
    if (bodysignMap == null) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0098")/*@res "excel造破坏，不能完成导入过程！"*/;
      throw new BusinessException(message);
    }
    String[] itemKeys = null;
    for (java.util.Map.Entry<String, String[]> entry : bodysignMap.entrySet()) {
      String key = entry.getKey();
      if (!key.equals(ExportExcelSign.lanmutabcode)) {
        this.setPlanTemplateId(key);
        itemKeys = entry.getValue();
      }
      else {
        this.grandkeys = entry.getValue();
      }
    }
    if (grandkeys == null) {
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0099")/*@res "excel被破坏，不能完成导入过程"*/);
    }
    return itemKeys;
  }

  /**
   * 得到某一行的值
   *
   * @param rowno：
   * @return
   * @throws BusinessException
   */
  public Object[] getRowValues(int rowno) throws BusinessException {
    if (rowno > this.getChildNum())
      return null;
    Map<Integer, Object[]> values = this.getChildValues();
    return values.get(rowno);

  }

  /**
   * 得出子表的值
   *
   * @return result=<key=行号,value=行对应的值>
   *         此时的行号是指子表的行，并不是excel的行号,例如2代表子表的第二行
   */
  public Map<Integer, Map<String, Object>> getChildValuesMap() {
    Map<Integer, Map<String, Object>> result =
        new HashMap<Integer, Map<String, Object>>();

    List<Row> bodyvalueRows = getBodyValueRows();
    for (int i = 0; i < bodyvalueRows.size(); i++) {

      Object[] rowValues;
      try {
        rowValues =
            getValues(this.regulation.getVlaueBcolumnNo(),
                this.regulation.getVlaueBcolumnNo() + this.itemKeys.length,
                bodyvalueRows.get(i));
        Map<String, Object> valueMap = getValuekeyMap(rowValues);
        result.put(i, valueMap);
      }
      catch (Exception e) {
        this.errorMsg.add(e.getMessage());
      }

    }
    return result;

  }

  private Map<String, Object> getValuekeyMap(Object[] rowValues) {
    Map<String, Object> result = new HashMap<String, Object>();
    for (int i = 0; i < rowValues.length; i++) {
      result.put(this.itemKeys[i], rowValues[i]);
    }
    return result;
  }

  public Map<Integer, Object[]> getChildValues() throws BusinessException {

    Map<Integer, Object[]> result = new HashMap<Integer, Object[]>();

    List<Row> bodyvalueRows = getBodyValueRows();
    for (int i = 0; i < bodyvalueRows.size(); i++) {

      Object[] rowValues;
      try {
        rowValues =
            getValues(this.regulation.getVlaueBcolumnNo(),
                this.regulation.getVlaueBcolumnNo() + this.itemKeys.length,
                bodyvalueRows.get(i));
        result.put(i, rowValues);
      }
      catch (Exception e) {
        ExceptionUtils.marsh(e);
      }

    }
    return result;

  }

  /**
   * 得到某一行中开始列到结束列的值
   *
   * @param fromcolumn：开始列
   * @param tocolumn：结束列
   * @param row
   * @return
   * @throws Exception
   */
  private Object[] getValues(int fromcolumn, int tocolumn, Row row)
      throws Exception {

    ArrayList<Object> result = new ArrayList<Object>();

    for (int i = fromcolumn; i < tocolumn; i++) {
      Cell cell = row.getCell(i);
      ExcelReadUtil util = new ExcelReadUtil();

      Object value = util.getCellValue(cell);
      result.add(value);
    }
    return result.toArray(new Object[result.size()]);

  }

  /**
   * 创建vo
   *
   * @return
   * @throws Exception
   */
  public OrderplanBVO[] createVos() throws Exception {
    // 读取数据前的教养
    this.readExcelBeforeRule();
    // 得到表体值数据
    Map<Integer, Map<String, Object>> valueMap = this.getChildValuesMap();
    // 构造表体vo
    ArrayList<OrderplanBVO> result =
        new ArrayList<OrderplanBVO>(valueMap.size());

    for (int i = 0; i < valueMap.size(); i++) {
      // 得到行号值
      Map<String, Object> value = valueMap.get(Integer.valueOf(i));
      // 转换读取的值为表体vo
      OrderplanBVO bvo = getBvo(value, i);
      result.add(bvo);
    }
    // 批量处理数据类型为参照类型的数据
    batchaddRefValue(result);
    // 读取数据后的校验规则
    readExcelAfterRule(result);
    // 校验单位
    checkUnit(result);
    return result.toArray(new OrderplanBVO[result.size()]);
  }

  /**
   * 批量处理数据类型为参照类型的数据
   *
   * @param bvos
   */
  private void batchaddRefValue(ArrayList<OrderplanBVO> bvos) {
    if (bvos == null || bvos.size() == 0)
      return;
    if (this.refValuemap.size() == 0)
      return;
    for (Entry<String, Map<Integer, String>> entry : this.refValuemap
        .entrySet()) {
      String itemkey = entry.getKey();
      Map<Integer, String> value = entry.getValue();
      Collection<String> refvalues = value.values();

      IDataHandler handler = NCLocator.getInstance().lookup(IDataHandler.class);
      Map<String, Object> ref_codeValues =
          handler.handBatchData(itemkey, OrderplanBVO.class.getName(),
              refvalues.toArray(new String[0]), this.getPk_orgid());
      for (int i = 0; i < bvos.size(); i++) {
        int crowno = Integer.valueOf(bvos.get(i).getCrowno());
        Object refvalue = value.get(crowno);
        Object code = ref_codeValues.get(refvalue);
        if (code == null) {
          this.errorMsg.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "ChildInfoReader-0000", null, new String[]{Integer.toString(crowno),refvalue.toString()})/*导入子表信息错误:第{0}行值{1}找不到对应的的参考值!*/);
        }
        else {
          bvos.get(i).setAttributeValue(itemkey, code);
        }

      }
    }

  }

  private String[] getStrValues(Collection<Object> col) {
    Object[] values = col.toArray(new Object[0]);
    String[] result = new String[values.length];
    ExcelReadUtil util = new ExcelReadUtil();

    for (int i = 0; i < values.length; i++) {
      result[i] = util.getStringValue(values[i]);
    }
    return result;
  }

  /**
   *
   * 读取表体数据后的校验
   *
   * @param result
   */
  private void readExcelAfterRule(ArrayList<OrderplanBVO> result) {

    // 校验单位不能为空
    if (!this.regulation.getUpdateFlag()) {
      return;
    }
    try {
      List<String> subDimCodes = this.getsubBobject();

      for (int i = 0; i < result.size(); i++) {
        OrderplanBVO bvo = result.get(i);
        if (bvo.getCastunitid() == null) {
          this.errorMsg.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "ChildInfoReader-0001", null, new String[]{bvo.getCrowno()})/*第{0}行单位不能为空，请填写！*/);
        }
        for (String code : subDimCodes) {
          if (bvo.getAttributeValue(code) == null) {
            this.errorMsg.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "ChildInfoReader-0002", null, new String[]{bvo.getCrowno()})/*第{0}行维度的值不能为空，请填写！*/);
          }
        }
      }
    }
    catch (Exception e) {
      // 此处不可能抛出异常，所以并不捕捉异常
    }

  }

  /**
   * 读取表体数据前的校验
   */
  private void readExcelBeforeRule() throws Exception {
    // 校验标题数据不为空
    checkBodyNull();
    // 校验导入项中必须含有主体体维度
    checkDimentionExist();
    // 校验导入项中必须含有单位
    checkUnitExist();
  }

  /**
   * 校验导入项中必须含有单位
   *
   * @throws Exception
   */
  private void checkUnitExist() throws Exception {
    String[] unit = new String[] {
      OrderplanBVO.CASTUNITID
    };
    List<Integer> columns = getCheckRows(unit);
    if (columns == null || columns.size() == 0)
      throw new Exception(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0100")/*@res "门户导入 时,表体列应存在单位列，请更改导入模板！"*/);
  }

  /**
   * 校验导入项中必须含有主体体维度
   *
   * @throws Exception
   */
  private void checkDimentionExist() throws Exception {
    if (!this.regulation.getUpdateFlag()) {
      return;
    }
    String[] dimentionarray =
        new String[] {
          OrderplanBVO.CMATERIALVID, OrderplanBVO.CPRODUCTID,
          OrderplanBVO.CPRODLINEID, OrderplanBVO.CMARBASCLASSID,
          OrderplanBVO.CBRANDID
        };

    List<Integer> columns = getCheckRows(dimentionarray);
    if (columns == null || columns.size() == 0) {
      throw new Exception(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0101")/*@res "门户导入 时,表体列应至少存在一个（商品/物料/物料基本分类/品牌/产品线）！"*/);
    }
  }

  /**
   * 校验表体数据不为空
   *
   * @throws Exception
   */
  private void checkBodyNull() throws Exception {
    if (!this.regulation.getUpdateFlag()) {
      return;
    }
    if (this.lastRowNum == this.regulation.getValueRowNo() - 1) {
      throw new Exception(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0102")/*@res "门户导入 时，表体数据为空，请重新导入！"*/);
    }
  }

  private void checkUnit(ArrayList<OrderplanBVO> result) {
    String code = null;
    if (this.itemKeys != null) {
      for (int i = 0; i < itemKeys.length; i++) {
        if (itemKeys[i].equals(OrderplanBVO.CPRODUCTID)
            || itemKeys[i].equals(OrderplanBVO.CMATERIALVID)) {
          code = itemKeys[i];
        }
      }
    }
    if (code != null) {
      for (int i = 0; i < result.size(); i++) {
        OrderplanBVO bvo = result.get(i);
        String dimentionvalue = (String) bvo.getAttributeValue(code);
        if (dimentionvalue == null || dimentionvalue.length() == 0)
          continue;
        String unitid = bvo.getCastunitid();
        if (unitid != null) {
          boolean checkResult = checkUnitID(unitid, code, dimentionvalue);
          if (!checkResult) {
            bvo.setCareaclassid(null);
            this.errorMsg
                .add(NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "ChildInfoReader-0003", null, new String[]{bvo.getCrowno()})/*第{0}行，单位所对应的值不在范围之内，请填写正确的值*/);
          }

        }
      }
    }

  }

  /**
   * 校验unitvalue是否在范围之内,
   * 单位范围的计算：导入项主题维度为商品时，则为商品规定的单位范围
   * 导入项主体维度为物料时，则为物料默认的单位范围,同后台一样
   *
   * @param unitvalue
   * @param code
   * @param dimentionvalue
   * @return
   */
  private boolean checkUnitID(String unitvalue, String code,
      String dimentionvalue) {
    String wherePart = null;
    if (code.equals(OrderplanBVO.CPRODUCTID)) {
      wherePart =
          " pk_measdoc  in( select b.pk_measdoc from bd_material b, eso_product e  where b.pk_material=e.cinventoryid and e.cproductid='"
              + dimentionvalue
              + "'"
              + " union select e.pk_measdoc from eso_product e where e.cproductid='"
              + dimentionvalue + "')";
    }
    else {
      wherePart =
          " pk_measdoc in (select pk_measdoc from "
              + "bd_materialconvert where pk_material = '" + dimentionvalue
              + "' and dr = 0 union select pk_measdoc from "
              + "bd_material where pk_material = '" + dimentionvalue + "')";

    }
    IMeasdocQueryServiceCof service = getMeaddocService();
    String[] allmeasdocs = service.queryMeasdocByWhere(wherePart);
    if (allmeasdocs == null) {
      return false;
    }

    for (int i = 0; i < allmeasdocs.length; i++) {
      if (allmeasdocs[i].equals(unitvalue)) {
        return true;
      }
    }
    return false;
  }

  private IMeasdocQueryServiceCof getMeaddocService() {
    if (meadocService == null) {
      meadocService =
          NCLocator.getInstance().lookup(IMeasdocQueryServiceCof.class);

    }
    return meadocService;
  }

  /**
   * 得到要删除的表体列，其删除列为原有的表体列-现有的操作为更新的表体列
   *
   * @return
   */
  public ArrayList<OrderplanBVO> getDeleteVos() {
    if (this.regulation.getUpdateFlag()) {
      ArrayList<OrderplanBVO> vos = new ArrayList<OrderplanBVO>();
      for (OrderplanBVO vo : this.aggvo.getChildrenBVO()) {
        if (!this.curPrimaryIDs.contains(vo.getCorderplanbid())) {
          OrderplanBVO copyvo = copyVo(vo);
          vos.add(copyvo);
        }
      }
      return vos;
    }
    return null;

  }

  private OrderplanBVO copyVo(OrderplanBVO vo) {
    OrderplanBVO copyvo = (OrderplanBVO) vo.clone();
    copyvo.setStatus(VOStatus.DELETED);
    ArrayList<OrderplanBTPVO> copybtpvoList = new ArrayList<OrderplanBTPVO>();
    OrderplanBTPVO[] grandsonvos = vo.getOrderplanBTPVOs();
    if (grandsonvos != null) {
      for (OrderplanBTPVO btpvo : grandsonvos) {
        OrderplanBTPVO copybtpvo = (OrderplanBTPVO) btpvo.clone();
        copybtpvo.setStatus(VOStatus.DELETED);
        copybtpvoList.add(copybtpvo);
      }
    }
    copyvo.setOrderplanBTPVOs(copybtpvoList.toArray(new OrderplanBTPVO[0]));
    return copyvo;
  }

  /**
   * 转换读取的值为bvo
   *
   * @param value
   * @param rowno
   * @return
   */
  private OrderplanBVO getBvo(Map<String, Object> value, int rowno) {
    OrderplanBVO bvo = new OrderplanBVO();
    ExcelReadUtil util = new ExcelReadUtil();
    for (Entry<String, Object> valueentry : value.entrySet()) {
      // 装换value为vo
      String key = valueentry.getKey();
      Object itemValue = valueentry.getValue();
      //是否平铺字段islay不列入VO中
      if (itemValue == null || "islay".equals(key) || "days".equals(key))
        continue;
      // 得出导入项的值类型
      int modelType = bvo.getMetaData().getAttribute(key).getModelType();
      // 为参照类型时,则记录进去，进行统一处理
      if (modelType == IType.REF) {
        String strvalue = util.getStringValue(itemValue);
        if (refValuemap.containsKey(key)) {
          Map<Integer, String> refvalue = refValuemap.get(key);
          refvalue.put((rowno + 1) * 10, strvalue);
        }
        else {
          Map<Integer, String> refvalue = new HashMap<Integer, String>();
          refvalue.put((rowno + 1) * 10, strvalue);
          refValuemap.put(key, refvalue);
        }
      }

      else {
        // 根据数据处理代理器处理各种类型的数据
        DataHandlerAgency agency = new DataHandlerAgency(null);
        FieldItem item = new FieldItem();
        item.setItemKey(key);
        item.setModelType(modelType);
        try {
          agency.handeData(item, bvo, itemValue, this.getPk_orgid());
        }
        catch (Exception e) {

          this.errorMsg.add(e.getMessage());
        }
        // IDataHandler handler =
        // NCLocator.getInstance().lookup(IDataHandler.class);
        // try {
        // ISuperVO result =
        // handler.handData(key, modelType, bvo, itemValue,
        // this.getPk_orgid());
        // bvo = (OrderplanBVO) result;
        // }
        // catch (BusinessException e) {
        // getErrorMsg().add(e.getMessage());
        // }
      }

    }
    // 重置行号值
    int itemValue = (rowno + 1) * 10;
    bvo.setAttributeValue(OrderplanBVO.CROWNO, itemValue);
    // 添加主键值和状态值
    if (this.regulation.getUpdateFlag()) {
      addPrimarykey(bvo, rowno);

    }
    return bvo;

  }

  /**
   * 添加主键等其他的值
   *
   * @param bvo
   * @param rowno
   */
  private void addPrimarykey(OrderplanBVO bvo, int rowno) {
    // 添加主表主键值
    bvo.setCorderplanid(this.aggvo.getParentVO().getCorderplanid());
    // 得到该bvo记录的子表主键值
    List<String> keys = getChildPrimaryKeys();
    // 是否是新增标志
    boolean isNew;
    String primarykey = keys.get(rowno);
    // 根据记录的主键值找到原有的对应bvo
    OrderplanBVO respondvo = getRespondingBvo(primarykey);
    // 如果找到，则为更新标志
    if (keys != null && keys.size() > 0 && primarykey != null
        && !primarykey.equals("null") && respondvo != null) {
      isNew = false;
    }
    else {
      isNew = true;
    }
    if (!isNew) {

      // 添加不更新的值
      addNotUpdateValue(bvo, respondvo);
      // bvo.setTs(respondvo.getTs());
      // // 添加自身主键值
      // bvo.setCorderplanbid(primarykey);
      curPrimaryIDs.add(primarykey);

    }
    // 添加操作状态
    addStatus(bvo, isNew);
  }

  /**
   * 根据原有的vo添加不更新的值
   *
   * @param bvo
   * @param origivo
   */
  private void addNotUpdateValue(OrderplanBVO bvo, OrderplanBVO origivo) {

    String[] attributenames = origivo.getAttributeNames();
    String[] notupdateattri = getNotUpdateAttri(attributenames);
    for (int i = 0; i < notupdateattri.length; i++) {
      Object newValue = origivo.getAttributeValue(notupdateattri[i]);
      bvo.setAttributeValue(notupdateattri[i], newValue);
    }
  }

  private String[] getNotUpdateAttri(String[] attributenames) {
    List<String> result = new ArrayList<String>();
    String[] updateKeys = filterItemKeys(this.itemKeys);
    for (String attri : attributenames) {
      if (!contain(attri, updateKeys)) {
        result.add(attri);
      }

    }
    return result.toArray(new String[0]);
  }

  /**
   * 进行excel导入时，根据模板配置规则门户可以编辑的字段可以进行修改，其他的不允许修改
   *
   * @param itemKeys
   * @return
   */
  private String[] filterItemKeys(String[] itemKeys) {
    ArrayList<String> result = new ArrayList<String>();
    for (Entry<Integer, List<BillTplConfigVO>> entry : this.billConfigvo
        .entrySet()) {
      List<BillTplConfigVO> valueList = entry.getValue();
      for (BillTplConfigVO value : valueList) {
        String itemkey = value.getItemkey();
        if (value.getWebeditflag().booleanValue() && contain(itemkey, itemKeys)) {
          result.add(itemkey);

        }
      }

    }
    return result.toArray(new String[0]);
  }

  private boolean contain(String attri, String[] itemKeys) {
    if (itemKeys == null || itemKeys.length == 0 || attri == null)
      return false;
    for (int i = 0; i < itemKeys.length; i++) {
      if (attri.equals(itemKeys[i]))
        return true;
    }
    return false;
  }

  /**
   * 校验存储的primarykey是否是旧的key值，如果是，则这条记录则是条新插入的记录
   *
   * @param primarykey
   * @return
   */
  private OrderplanBVO getRespondingBvo(String primarykey) {
    OrderplanBVO[] bvos = aggvo.getChildrenBVO();
    for (int i = 0; i < bvos.length; i++) {
      if (bvos[i].getCorderplanbid().equals(primarykey))
        return bvos[i];
    }
    return null;
  }

  private void addStatus(OrderplanBVO bvo, boolean isNew) {
    if (!this.regulation.getUpdateFlag())
      return;
    if (isNew) {
      bvo.setStatus(VOStatus.NEW);
    }
    else {
      bvo.setStatus(VOStatus.UPDATED);
    }
  }

  private List<String> getChildPrimaryKeys() {
    if (this.ChildprimaryKeys == null) {
      List<String> childprimarySigns = this.getChildprimarySigns();
      List<String> result = new ArrayList<String>();
      for (String key : childprimarySigns) {
        if (key == null) {
          result.add("null");
        }
        else {
          String[] spitKeys = key.split("%");
          result.add(spitKeys[0]);
        }
      }
      ChildprimaryKeys = result;
    }
    return ChildprimaryKeys;
  }

  private List<Row> getBodyValueRows() {

    List<Row> bodyvalueRows = new ArrayList<Row>();
    for (int i = this.regulation.getValueRowNo(); i <= this.lastRowNum;) {
      bodyvalueRows.add(sheet.getRow(i));
      i = i + this.grandkeys.length;
    }
    return bodyvalueRows;

  }

  /**
   * 计算子表的最后一列，其规则为：
   * 1 得出导出项中为主体体维度的导出项，即subdim，并且算出subdim所在的列
   * 2 如果subdim有值，则从子表的开始行逐行检索，直到这行中的subdim项所在的列都没有值，则判断为最后列
   * 3 如果subdim没有值，则把导出的所有项作为subdim来进行，再进行操作2
   *
   * @return
   * @throws Exception
   */
  private int getLastRowNum() throws Exception {
    if (this.itemKeys == null || itemKeys.length <= 0)
      return 0;

    List<String> bDimentionvos = getsubBobject();
    List<Integer> columns = getCheckRows(bDimentionvos.toArray(new String[0]));

    int result = getLastRow(columns);
    return result;
  }

  private List<String> getsubBobject() throws Exception {
    if (this.subDimentionCodes == null) {
      List<String> bDimentionvos = new ArrayList<String>();
      IPlanTemplatePubService service =
          NCLocator.getInstance().lookup(IPlanTemplatePubService.class);
      AggPlantemplateVO aggPlanTemplateVo =
          service.getPlanTemplateByID(this.getPlanTemplateId());
      if (aggPlanTemplateVo == null)
        throw new Exception(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0103")/*@res "该计划模板已不存在，不能完成导入功能，请更换excel模板"*/);
      BdimensionVO[] bdimentionvos = aggPlanTemplateVo.getBdimensionChildVOs();
      for (BdimensionVO bdimentionvo : bdimentionvos) {
        if (bdimentionvo.getBobject().booleanValue())
          bDimentionvos.add(bdimentionvo.getVbdcode());
      }
      this.subDimentionCodes = bDimentionvos;
    }
    return subDimentionCodes;

  }

  public int getLastrownum() {
    return this.lastRowNum;
  }

  private int getLastRow(List<Integer> columns) throws Exception {
    int fromrow = ExportExcelSign.BODY_VALUE_TITLE_ROW;
    boolean isAdd = true;
    ExcelReadUtil util = new ExcelReadUtil();
    while (isAdd && fromrow <= sheet.getLastRowNum()) {
      Row row = sheet.getRow(fromrow);
      int notNull = 0;
      for (int i = 0; i < columns.size(); i++) {

        Cell cell = row.getCell(columns.get(i));
        Object value = util.getCellValue(cell);
        if (value != null)
          notNull++;
      }
      if (notNull == 0)
        isAdd = false;
      else {
        fromrow = fromrow + this.grandkeys.length;
      }

    }
    return fromrow - 1;
  }

  private List<Integer> getCheckRows(String[] bDimentionvos) {
    List<Integer> columns = new ArrayList<Integer>();
    List<Integer> allcolumns = new ArrayList<Integer>();

    for (int i = 0; i < this.itemKeys.length; i++) {

      String dimentionid = this.itemKeys[i];
      if (checkIn(dimentionid, bDimentionvos))
        columns.add(i + 1);
      allcolumns.add(i + 1);
    }
    if (columns.size() != 0)
      return columns;
    return allcolumns;
  }

  private boolean checkIn(String value, String[] values) {
    for (String val : values) {
      if (val.equals(value))
        return true;
    }
    return false;
  }

  public void setParentkeyid(AggOrderplanVO aggvo) {
    this.aggvo = aggvo;
  }

  public AggOrderplanVO getParentkeyid() {
    return this.aggvo;
  }

  public void setPk_orgid(String pk_orgid) {
    this.pk_orgid = pk_orgid;
  }

  public String getPk_orgid() {
    return this.pk_orgid;
  }

  public void setErrorMsg(List<String> errorMsg) {
    this.errorMsg = errorMsg;
  }

  public List<String> getErrorMsg() {
    return this.errorMsg;
  }

  public void setPlanTemplateId(String planTemplateId) {
    this.planTemplateId = planTemplateId;
  }

  public String getPlanTemplateId() {
    return this.planTemplateId;
  }

  public void setLastRowNum(int lastRowNum) {
    this.lastRowNum = lastRowNum;
  }

  public void setRegulation(ExcelRegulation regulation) {
    this.regulation = regulation;
  }

  public void setChildNum(int childNum) {
    this.childNum = childNum;
  }

  public int getChildNum() {
    return this.childNum;
  }

  public void setBillTPLConfigVO(MapList<Integer, BillTplConfigVO> configvo) {
    this.billConfigvo = configvo;

  }
}