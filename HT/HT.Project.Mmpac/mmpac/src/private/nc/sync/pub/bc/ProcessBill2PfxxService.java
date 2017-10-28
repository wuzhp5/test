package nc.sync.pub.bc;

import java.util.Map;
import java.util.Set;

import nc.bs.dao.BaseDAO;
import nc.bs.framework.common.NCLocator;
import nc.bs.logging.Logger;
import nc.itf.sync.param.ISyncParamService;
import nc.itf.uap.IUAPQueryBS;
import nc.pub.sync.utils.SyncUtil;
import nc.sync.pfxx.entity.FieldOfVO;
import nc.sync.pfxx.entity.PfxxServiceBaseInfo;
import nc.sync.pfxx.utils.AssemblingXML;
import nc.sync.pfxx.utils.TemplateXMLUtil;
import nc.sync.pfxx.utils.TransmitData;
import nc.sync.pfxx.utils.XML_Translator;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.apache.xerces.dom.DocumentImpl;
import org.w3c.dom.Document;

public abstract class ProcessBill2PfxxService {
	// basedao
	private BaseDAO baseDAO = null;
	/* 编码方式 */
	protected final String ENCODING = "UTF-8";
	// 标准XML字段信息
	private FieldOfVO fields = null;
	private XML_Translator translator = new XML_Translator();
	protected String fileName = null;
	protected ISyncParamService paramService = NCLocator.getInstance().lookup(ISyncParamService.class);
	public IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
	/*
	 * StringBuffer fileBuffer = new StringBuffer();
	 * translator.writeXMLFormatString(fileBuffer, doc, -2, ENCODING); try {
	 * translator.saveToFile("E:\\外部交换平台XML\\生产制造\\"+fileName+"", fileBuffer); }
	 * catch (Exception e) { e.printStackTrace(); }
	 */
	 

	// 传输信息到外部交换平台
	private String sendBillData(Object ivo, FieldOfVO field, String billType, String sender, String tablename, String pk, String pkName,
			ISuperVO bodyVO) throws BusinessException {
		/* 获取Document对象 */
		Document doc = new DocumentImpl();
		/* 拼装XML */
		AssemblingXML.getInstance().getXML(doc, field, ivo, null, billType, sender, bodyVO);
		StringBuffer fileBuffer = new StringBuffer();
		translator.writeXMLFormatString(fileBuffer, doc, -2, ENCODING);
		/* 发送XML数据并得到回执文件 */
		Document response = TransmitData.sendDocument(doc, SyncUtil.getURL(), this.ENCODING, false);
		/* 转换器讲回执信息转换成String类型 */
		String resMsg = TransmitData.toFormatedXML(response);
		Logger.debug("回执信息:/n" + resMsg);
		/* 获取回执信息root节点信息 */
		Map<String, String> rootAttr = TransmitData.getXmlRootAttribute(response);
		if ("Y".equalsIgnoreCase(rootAttr.get("successful").trim())) {
			return SyncUtil.packSuccessInfo("单据" + pk + "同步成功！");
		} else if ("N".equalsIgnoreCase(rootAttr.get("successful").trim())) {
			return SyncUtil.packErrorInfo(getErrorInfo(response.getElementsByTagName("resultdescription").item(0).getTextContent()));
		} else {
			throw new BusinessException("回执信息错误！回执信息：" + resMsg);
		}
	}

	// NC数据源
	protected BaseDAO getBaseDAO() {
		if (this.baseDAO == null) {
			this.baseDAO = new BaseDAO();
		}
		return this.baseDAO;
	}

	// 标准XML字段信息
	public FieldOfVO getFieldOfVO(String fileName, String[] itemName) throws BusinessException {
		if (this.fields == null) {
			this.fields = TemplateXMLUtil.getInstance().getAttributesOfXML(fileName, itemName);
		}
		return this.fields;
	}

	// 上传数据到外部交换品台servlet
	public String uploadBillData(PfxxServiceBaseInfo info, ISuperVO bodyVO, AbstractBill aggVO) throws BusinessException {
		String billCode = (String) aggVO.getParent().getAttributeValue(info.getBillCodeField());
		String pk = billCode;
		return sendBillData(aggVO, getFieldOfVO(info.getFileName(), info.getItemNames()), info.getBillType(), info.getSender(), info.getTableName(),
				pk, info.getBillCodeField(), bodyVO);
	}

	public void initSameData(ISuperVO vo, String[] fields, Object value) {
		for (String field : fields) {
			vo.setAttributeValue(field, value);
		}
	}

	public String getErrorInfo(String error) {
		if (error != null && error.indexOf("开始处理...") > 0) {
			String[] strs = error.split("开始处理...", 2);
			return strs[1];
		} else {
			return error;
		}
	}

	public String limitLengthOfString(String error) {
		if (error != null && error.length() > 500) {
			return error.substring(0, 500);
		} else {
			return error;
		}
	}

	public String getNullOrString(Object obj) {
		if (obj != null) {
			return obj.toString().trim();
		}
		return null;
	}
	
}
