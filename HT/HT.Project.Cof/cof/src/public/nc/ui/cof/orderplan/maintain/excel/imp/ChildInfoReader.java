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
 * �ӱ��ȡ��
 *
 * @since 6.0
 * @version 2013-10-31 ����02:25:00
 * @author ruhuic
 */
public class ChildInfoReader {
  /**
   * ��ȡ��sheet
   */
  private Sheet sheet;

  /**
   * �ӱ�������
   */
  private String[] itemKeys;

  /**
   * ���������
   */
  private String[] grandkeys;

  /**
   * �ƻ�ģ��id
   */
  private String planTemplateId;

  /**
   * ����
   */
  private int childNum;

  /**
   * ���һ�е�����
   */
  private int lastRowNum;

  /**
   * ������ʶ
   */
  private List<String> ChildprimarySigns;

  /**
   * ������Ϣ��¼
   */
  private List<String> errorMsg = new ArrayList<String>();

  /**
   * ����ʱԭʼ��aggvo
   */
  private AggOrderplanVO aggvo;

  /**
   * ��֯id
   */
  private String pk_orgid;

  /**
   * �滮��
   */
  private ExcelRegulation regulation;

  /**
   * ��������Ϊ���²���������list
   */
  private ArrayList<String> curPrimaryIDs = new ArrayList<String>();

  /**
   * �ӱ���������
   */
  private List<String> ChildprimaryKeys;

  /**
   * Ѱ��λ�ķ���
   */
  private IMeasdocQueryServiceCof meadocService;

  /**
   * ������ά�ȱ���
   */
  private List<String> subDimentionCodes;

  /**
   * ����Ϊ���յ�key-<�к�-ֵ>��ֵ��
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
   * ��ȡ�ӱ�������ʶ
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
   * ��ȡ�����е��ӱ��ʶ�����б�ʶ����Ϊ�������ӱ��
   * �����ӱ�����һ��
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
   * ת����ʶ
   *
   * @param headSign
   * @return
   * @throws BusinessException
   */
  private String[] convertSign(String headSign) throws BusinessException {
    Map<String, String[]> bodysignMap =
        CreateExcelSignTool.reverceSign(headSign);
    if (bodysignMap == null) {
      String message = nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0098")/*@res "excel���ƻ���������ɵ�����̣�"*/;
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
      throw new BusinessException(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0099")/*@res "excel���ƻ���������ɵ������"*/);
    }
    return itemKeys;
  }

  /**
   * �õ�ĳһ�е�ֵ
   *
   * @param rowno��
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
   * �ó��ӱ��ֵ
   *
   * @return result=<key=�к�,value=�ж�Ӧ��ֵ>
   *         ��ʱ���к���ָ�ӱ���У�������excel���к�,����2�����ӱ�ĵڶ���
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
   * �õ�ĳһ���п�ʼ�е������е�ֵ
   *
   * @param fromcolumn����ʼ��
   * @param tocolumn��������
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
   * ����vo
   *
   * @return
   * @throws Exception
   */
  public OrderplanBVO[] createVos() throws Exception {
    // ��ȡ����ǰ�Ľ���
    this.readExcelBeforeRule();
    // �õ�����ֵ����
    Map<Integer, Map<String, Object>> valueMap = this.getChildValuesMap();
    // �������vo
    ArrayList<OrderplanBVO> result =
        new ArrayList<OrderplanBVO>(valueMap.size());

    for (int i = 0; i < valueMap.size(); i++) {
      // �õ��к�ֵ
      Map<String, Object> value = valueMap.get(Integer.valueOf(i));
      // ת����ȡ��ֵΪ����vo
      OrderplanBVO bvo = getBvo(value, i);
      result.add(bvo);
    }
    // ����������������Ϊ�������͵�����
    batchaddRefValue(result);
    // ��ȡ���ݺ��У�����
    readExcelAfterRule(result);
    // У�鵥λ
    checkUnit(result);
    return result.toArray(new OrderplanBVO[result.size()]);
  }

  /**
   * ����������������Ϊ�������͵�����
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
          this.errorMsg.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "ChildInfoReader-0000", null, new String[]{Integer.toString(crowno),refvalue.toString()})/*�����ӱ���Ϣ����:��{0}��ֵ{1}�Ҳ�����Ӧ�ĵĲο�ֵ!*/);
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
   * ��ȡ�������ݺ��У��
   *
   * @param result
   */
  private void readExcelAfterRule(ArrayList<OrderplanBVO> result) {

    // У�鵥λ����Ϊ��
    if (!this.regulation.getUpdateFlag()) {
      return;
    }
    try {
      List<String> subDimCodes = this.getsubBobject();

      for (int i = 0; i < result.size(); i++) {
        OrderplanBVO bvo = result.get(i);
        if (bvo.getCastunitid() == null) {
          this.errorMsg.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "ChildInfoReader-0001", null, new String[]{bvo.getCrowno()})/*��{0}�е�λ����Ϊ�գ�����д��*/);
        }
        for (String code : subDimCodes) {
          if (bvo.getAttributeValue(code) == null) {
            this.errorMsg.add(NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "ChildInfoReader-0002", null, new String[]{bvo.getCrowno()})/*��{0}��ά�ȵ�ֵ����Ϊ�գ�����д��*/);
          }
        }
      }
    }
    catch (Exception e) {
      // �˴��������׳��쳣�����Բ�����׽�쳣
    }

  }

  /**
   * ��ȡ��������ǰ��У��
   */
  private void readExcelBeforeRule() throws Exception {
    // У��������ݲ�Ϊ��
    checkBodyNull();
    // У�鵼�����б��뺬��������ά��
    checkDimentionExist();
    // У�鵼�����б��뺬�е�λ
    checkUnitExist();
  }

  /**
   * У�鵼�����б��뺬�е�λ
   *
   * @throws Exception
   */
  private void checkUnitExist() throws Exception {
    String[] unit = new String[] {
      OrderplanBVO.CASTUNITID
    };
    List<Integer> columns = getCheckRows(unit);
    if (columns == null || columns.size() == 0)
      throw new Exception(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0100")/*@res "�Ż����� ʱ,������Ӧ���ڵ�λ�У�����ĵ���ģ�壡"*/);
  }

  /**
   * У�鵼�����б��뺬��������ά��
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
      throw new Exception(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0101")/*@res "�Ż����� ʱ,������Ӧ���ٴ���һ������Ʒ/����/���ϻ�������/Ʒ��/��Ʒ�ߣ���"*/);
    }
  }

  /**
   * У��������ݲ�Ϊ��
   *
   * @throws Exception
   */
  private void checkBodyNull() throws Exception {
    if (!this.regulation.getUpdateFlag()) {
      return;
    }
    if (this.lastRowNum == this.regulation.getValueRowNo() - 1) {
      throw new Exception(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0102")/*@res "�Ż����� ʱ����������Ϊ�գ������µ��룡"*/);
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
                .add(NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "ChildInfoReader-0003", null, new String[]{bvo.getCrowno()})/*��{0}�У���λ����Ӧ��ֵ���ڷ�Χ֮�ڣ�����д��ȷ��ֵ*/);
          }

        }
      }
    }

  }

  /**
   * У��unitvalue�Ƿ��ڷ�Χ֮��,
   * ��λ��Χ�ļ��㣺����������ά��Ϊ��Ʒʱ����Ϊ��Ʒ�涨�ĵ�λ��Χ
   * ����������ά��Ϊ����ʱ����Ϊ����Ĭ�ϵĵ�λ��Χ,ͬ��̨һ��
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
   * �õ�Ҫɾ���ı����У���ɾ����Ϊԭ�еı�����-���еĲ���Ϊ���µı�����
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
   * ת����ȡ��ֵΪbvo
   *
   * @param value
   * @param rowno
   * @return
   */
  private OrderplanBVO getBvo(Map<String, Object> value, int rowno) {
    OrderplanBVO bvo = new OrderplanBVO();
    ExcelReadUtil util = new ExcelReadUtil();
    for (Entry<String, Object> valueentry : value.entrySet()) {
      // װ��valueΪvo
      String key = valueentry.getKey();
      Object itemValue = valueentry.getValue();
      //�Ƿ�ƽ���ֶ�islay������VO��
      if (itemValue == null || "islay".equals(key) || "days".equals(key))
        continue;
      // �ó��������ֵ����
      int modelType = bvo.getMetaData().getAttribute(key).getModelType();
      // Ϊ��������ʱ,���¼��ȥ������ͳһ����
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
        // �������ݴ������������������͵�����
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
    // �����к�ֵ
    int itemValue = (rowno + 1) * 10;
    bvo.setAttributeValue(OrderplanBVO.CROWNO, itemValue);
    // �������ֵ��״ֵ̬
    if (this.regulation.getUpdateFlag()) {
      addPrimarykey(bvo, rowno);

    }
    return bvo;

  }

  /**
   * ���������������ֵ
   *
   * @param bvo
   * @param rowno
   */
  private void addPrimarykey(OrderplanBVO bvo, int rowno) {
    // �����������ֵ
    bvo.setCorderplanid(this.aggvo.getParentVO().getCorderplanid());
    // �õ���bvo��¼���ӱ�����ֵ
    List<String> keys = getChildPrimaryKeys();
    // �Ƿ���������־
    boolean isNew;
    String primarykey = keys.get(rowno);
    // ���ݼ�¼������ֵ�ҵ�ԭ�еĶ�Ӧbvo
    OrderplanBVO respondvo = getRespondingBvo(primarykey);
    // ����ҵ�����Ϊ���±�־
    if (keys != null && keys.size() > 0 && primarykey != null
        && !primarykey.equals("null") && respondvo != null) {
      isNew = false;
    }
    else {
      isNew = true;
    }
    if (!isNew) {

      // ��Ӳ����µ�ֵ
      addNotUpdateValue(bvo, respondvo);
      // bvo.setTs(respondvo.getTs());
      // // �����������ֵ
      // bvo.setCorderplanbid(primarykey);
      curPrimaryIDs.add(primarykey);

    }
    // ��Ӳ���״̬
    addStatus(bvo, isNew);
  }

  /**
   * ����ԭ�е�vo��Ӳ����µ�ֵ
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
   * ����excel����ʱ������ģ�����ù����Ż����Ա༭���ֶο��Խ����޸ģ������Ĳ������޸�
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
   * У��洢��primarykey�Ƿ��Ǿɵ�keyֵ������ǣ���������¼�������²���ļ�¼
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
   * �����ӱ�����һ�У������Ϊ��
   * 1 �ó���������Ϊ������ά�ȵĵ������subdim���������subdim���ڵ���
   * 2 ���subdim��ֵ������ӱ�Ŀ�ʼ�����м�����ֱ�������е�subdim�����ڵ��ж�û��ֵ�����ж�Ϊ�����
   * 3 ���subdimû��ֵ����ѵ�������������Ϊsubdim�����У��ٽ��в���2
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
        throw new Exception(nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0","0ec72001-0103")/*@res "�üƻ�ģ���Ѳ����ڣ�������ɵ��빦�ܣ������excelģ��"*/);
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