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
	/* ���뷽ʽ */
	protected final String ENCODING = "UTF-8";
	// ��׼XML�ֶ���Ϣ
	private FieldOfVO fields = null;
	private XML_Translator translator = new XML_Translator();
	protected String fileName = null;
	protected ISyncParamService paramService = NCLocator.getInstance().lookup(ISyncParamService.class);
	public IUAPQueryBS bs = NCLocator.getInstance().lookup(IUAPQueryBS.class);
	/*
	 * StringBuffer fileBuffer = new StringBuffer();
	 * translator.writeXMLFormatString(fileBuffer, doc, -2, ENCODING); try {
	 * translator.saveToFile("E:\\�ⲿ����ƽ̨XML\\��������\\"+fileName+"", fileBuffer); }
	 * catch (Exception e) { e.printStackTrace(); }
	 */
	 

	// ������Ϣ���ⲿ����ƽ̨
	private String sendBillData(Object ivo, FieldOfVO field, String billType, String sender, String tablename, String pk, String pkName,
			ISuperVO bodyVO) throws BusinessException {
		/* ��ȡDocument���� */
		Document doc = new DocumentImpl();
		/* ƴװXML */
		AssemblingXML.getInstance().getXML(doc, field, ivo, null, billType, sender, bodyVO);
		StringBuffer fileBuffer = new StringBuffer();
		translator.writeXMLFormatString(fileBuffer, doc, -2, ENCODING);
		/* ����XML���ݲ��õ���ִ�ļ� */
		Document response = TransmitData.sendDocument(doc, SyncUtil.getURL(), this.ENCODING, false);
		/* ת��������ִ��Ϣת����String���� */
		String resMsg = TransmitData.toFormatedXML(response);
		Logger.debug("��ִ��Ϣ:/n" + resMsg);
		/* ��ȡ��ִ��Ϣroot�ڵ���Ϣ */
		Map<String, String> rootAttr = TransmitData.getXmlRootAttribute(response);
		if ("Y".equalsIgnoreCase(rootAttr.get("successful").trim())) {
			return SyncUtil.packSuccessInfo("����" + pk + "ͬ���ɹ���");
		} else if ("N".equalsIgnoreCase(rootAttr.get("successful").trim())) {
			return SyncUtil.packErrorInfo(getErrorInfo(response.getElementsByTagName("resultdescription").item(0).getTextContent()));
		} else {
			throw new BusinessException("��ִ��Ϣ���󣡻�ִ��Ϣ��" + resMsg);
		}
	}

	// NC����Դ
	protected BaseDAO getBaseDAO() {
		if (this.baseDAO == null) {
			this.baseDAO = new BaseDAO();
		}
		return this.baseDAO;
	}

	// ��׼XML�ֶ���Ϣ
	public FieldOfVO getFieldOfVO(String fileName, String[] itemName) throws BusinessException {
		if (this.fields == null) {
			this.fields = TemplateXMLUtil.getInstance().getAttributesOfXML(fileName, itemName);
		}
		return this.fields;
	}

	// �ϴ����ݵ��ⲿ����Ʒ̨servlet
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
		if (error != null && error.indexOf("��ʼ����...") > 0) {
			String[] strs = error.split("��ʼ����...", 2);
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
