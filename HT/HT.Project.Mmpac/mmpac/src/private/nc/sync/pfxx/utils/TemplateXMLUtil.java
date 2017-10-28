package nc.sync.pfxx.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import nc.sync.pfxx.entity.FieldOfVO;
import nc.vo.pub.BusinessException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

public class TemplateXMLUtil {

	private static TemplateXMLUtil templateXMLUtils = null;

	private TemplateXMLUtil() {
	}

	public static TemplateXMLUtil getInstance() {
		if (templateXMLUtils == null) {
			templateXMLUtils = new TemplateXMLUtil();
		}
		return templateXMLUtils;
	}

	public String getStringOfXML(String fileName) {
		InputStreamReader isr = null;
		BufferedReader br = null;
		StringBuffer sb = new StringBuffer();
		String readline = null;
		try {
			isr = new InputStreamReader(new FileInputStream(new File(this.getClass().getResource(".").getPath().toString() + fileName + ".xml")),
					"UTF-8");
			br = new BufferedReader(isr);
			while ((readline = br.readLine()) != null) {
				sb.append(readline + "\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				isr.close();
				br.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	/**
	 * @param xmlFileName
	 *            XML�ļ���������bd_material������Ҫ��չ������
	 * @param txtFileName
	 *            Ҫ���ɵ�TXT�ļ���������bd_material������Ҫ��չ����
	 * @param filePath
	 *            Ҫ����TXT�ļ���·�� �����硰E:\\NC_HOME\\����
	 * @param itemName
	 *            �ӱ������ƣ����硰{"materialconvert"}��
	 * @throws Exception
	 */
	public void saveAsFile(String xmlFileName, String txtFileName, String filePath, String[] itemName) throws Exception {
		/* ��ȡָ��·���ļ����� */
		File file = new File(filePath + txtFileName + ".txt");
		/* �ж��ļ��Ƿ���� */
		if (!file.exists()) {
			/* �����ڣ������ļ� */
			file.createNewFile();
		}
		/* �����ļ������ */
		FileOutputStream fos = null;
		/* �����ֽ��� */
		OutputStreamWriter writer = null;
		try {
			/* ��ʼ���ļ������ */
			fos = new FileOutputStream(file);
			/* ���»��ֽ��� */
			writer = new OutputStreamWriter(fos, "utf-8");
			/* ��ȡbillhead�������� */
			NodeList billHead = getBillHead(xmlFileName);
			for (int i = 0; i < billHead.getLength(); i++) {
				/* ��ȡ�ڵ��� */
				String nodeName = billHead.item(i).getNodeName();
				/* У��Ԫ�ֱ��� */
				if (!checkItem(itemName, nodeName)) {
					/* У��ڵ��� */
					if (checkNode(nodeName)) {
						/* �ڵ���д���ļ� */
						writer.append(nodeName + "\r\n");
					}
				} else {
					writer.append("\r\n");
					NodeList item = billHead.item(i).getChildNodes();
					NodeList itemList = item.item(1).getChildNodes();
					for (int j = 0; j < itemList.getLength(); j++) {
						String itemNodeName = itemList.item(j).getNodeName();
						if (checkNode(itemNodeName)) {
							writer.append(itemNodeName + "\r\n");
						}
					}
				}
			}
		} finally {
			writer.close();
			fos.close();
		}
	}

	public FieldOfVO getAttributesOfXML(String xmlFileName, String[] itemName) throws BusinessException {
		FieldOfVO field = new FieldOfVO();
		Map<String, String> headDefField = new HashMap<String, String>();
		List<String> headUndefField = new ArrayList<String>();
		Map<String, Map<String, String>> bodyDefField = new HashMap<String, Map<String, String>>();
		Map<String, List<String>> bodyUndefField = new HashMap<String, List<String>>();
		/* ��ȡbillhead�������� */
		NodeList billHead = getBillHead(xmlFileName);
		for (int i = 0; i < billHead.getLength(); i++) {
			/* ��ȡ�ڵ��� */
			String nodeName = billHead.item(i).getNodeName();
			String value = billHead.item(i).getTextContent().trim();
			/* У��Ԫ�ֱ��� */
			if (!checkItem(itemName, nodeName)) {
				/* У��ڵ��� */
				if (checkNode(nodeName)) {
					if (value == null || value.equals("")) {
						headUndefField.add(nodeName);
					} else {
						headDefField.put(nodeName, value);
					}
				}
			} else {
				Map<String, String> bodyDefValue = new HashMap<String, String>();
				List<String> bodyUndefValue = new ArrayList<String>();
				NodeList item = billHead.item(i).getChildNodes();
				NodeList itemList = item.item(1).getChildNodes();
				for (int j = 0; j < itemList.getLength(); j++) {
					String itemNodeName = itemList.item(j).getNodeName();
					String itemValue = itemList.item(j).getTextContent().trim();
					if (checkNode(itemNodeName)) {
						if (value == null || itemValue.equals("")) {
							bodyUndefValue.add(itemNodeName);
						} else {
							bodyDefValue.put(itemNodeName, itemValue);
						}
					}
				}
				bodyDefField.put(nodeName, bodyDefValue);
				bodyUndefField.put(nodeName, bodyUndefValue);
			}
		}
		field.setHeadDefField(headDefField);
		field.setHeadUndefField(headUndefField);
		field.setBodyDefField(bodyDefField);
		field.setBodyUndefField(bodyUndefField);
		return field;
	}

	private NodeList getBillHead(String xmlFileName) throws BusinessException {
		String file = getStringOfXML(xmlFileName);
		Document doc = TransmitData.toFormatedDoc(file);
		NodeList xml = doc.getChildNodes();
		NodeList bill = xml.item(0).getChildNodes();
		NodeList billhead = bill.item(1).getChildNodes();
		return billhead.item(1).getChildNodes();
	}

	private boolean checkNode(String nodeName) {
		if (nodeName.trim().equalsIgnoreCase("#text")) {
			return false;
		} else if (nodeName.trim().equalsIgnoreCase("#comment")) {
			return false;
		} else {
			return true;
		}
	}

	private boolean checkItem(String[] itemName, String nodeName) {
		for (String item : itemName) {
			if (item.trim().equalsIgnoreCase(nodeName)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param obj
	 * @return
	 */
	protected String getNullOrString(Object obj) {
		if (obj == null) {
			return null;
		} else {
			return obj.toString().trim();
		}
	}

}
