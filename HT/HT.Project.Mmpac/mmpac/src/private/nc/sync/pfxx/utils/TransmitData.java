package nc.sync.pfxx.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import nc.vo.jcom.xml.XMLUtil;
import nc.vo.pub.BusinessException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

public class TransmitData {

	/**
	 * �������
	 * 
	 * @param url
	 *            NC����ƽ̨��ַ
	 * @param bcompress
	 *            �Ƿ�ѹ�����ķ�ʽ����
	 * 
	 */
	public static HttpURLConnection getConnection(String url, Boolean bcompress) throws IOException {
		HttpURLConnection connection = null;
		URL realURL = null;
		try {
			realURL = new URL(bcompress ? url + "&compress=true" : url);
			connection = (HttpURLConnection) realURL.openConnection();
			connection.setDoOutput(true);
			connection.setRequestProperty("Content-type", "text/xml");
			connection.setRequestMethod("POST");
		} catch (IOException e) {
			throw new IOException("�ڻ�ȡ�����г���:" + e.getMessage());
		}
		System.out.println(connection);
		return connection;
	}

	/**
	 * ����Document�ĵ���NC����ƽ̨
	 * 
	 * @param doc
	 *            Ҫ���͵��ĵ�
	 * @param url
	 *            ���͵�ַ
	 * @param outputEncoding
	 *            ����
	 * @param bcompress
	 *            �Ƿ�֧��ѹ��
	 * @return result NC��ִ��Ϣ
	 * @throws Exception
	 */
	public static Document sendDocument(Document doc, String url, String outputEncoding, Boolean bcompress) throws BusinessException {
		Document result = null;
		HttpURLConnection curconnection = null;
		Writer writer = null;
		try {
			curconnection = getConnection(url, bcompress);
			if (curconnection != null)
				curconnection.disconnect();
			curconnection = getConnection(url, false);
			writer = new OutputStreamWriter(curconnection.getOutputStream(), outputEncoding);
			XMLUtil.printDOMTree(writer, doc, 0, outputEncoding);
			writer.close();
			result = receiveResponse(curconnection, bcompress.booleanValue());
		} catch (Exception e) {

			try {
				result = createExceptionMsg();
			} catch (Throwable e1) {
				throw new BusinessException(e1);
			}
			System.out.println("====================================================");
			result.getElementsByTagName("infoscription").item(0).setTextContent("�쳣��Ϣ��" + "�ڵ��ݴ�������г���:" + e.getMessage());
		} finally {
			if (curconnection != null)
				curconnection.disconnect();
		}
		return result;
	}

	/**
	 * ȡ��NC����ƽ̨��ִ��Ϣ
	 * 
	 * @param connection
	 *            ����ƽ̨����
	 * @param bcompress
	 *            �Ƿ�ѹ����
	 * @return
	 * @throws Exception
	 * 
	 * @throws IOException
	 */
	public static Document receiveResponse(HttpURLConnection connection, boolean bcompress) throws Exception {
		Document doc = null;
		try {
			/*
			 * if (bcompress) { doc = XMLUtil.getDocumentBuilder().parse( new
			 * GZIPInputStream(connection.getInputStream())); /// ��Ҫ�Ż� } else {
			 * System.out.println(connection.getInputStream());
			 */
			doc = XMLUtil.getDocumentBuilder().parse(connection.getInputStream());
		} catch (Exception e) {
			throw new Exception("����ִʱ����:", e);
		}
		return doc;
	}

	/**
	 * ������׼���쳣��Ϣ��ִ�ĵ�
	 * 
	 * @return Document ��׼�쳣��ִ��Ϣ�ĵ�
	 * @throws Throwable
	 */
	public static Document createExceptionMsg() throws Throwable {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("\n<ufinterface billtype=\"Exception\" roottag=\"exceptioninfo\" successful=\"N\">");
		xml.append("\n<exceptioninfo>");
		xml.append("\n<infocode>900001</infocode>");
		xml.append("\n<infoscription>�쳣��Ϣ��</infoscription>");
		xml.append("\n</exceptioninfo>");
		xml.append("\n</ufinterface>");
		return toFormatedDoc(xml.toString());
	}

	/**
	 * ����META-INFĿ¼����.sql��β���ļ�������ȡ���е�����
	 * 
	 * @return sql
	 */
	@SuppressWarnings("resource")
	public static String readSQLFile() throws BusinessException {
		// ���ݵ�ǰ���ø�Ŀ¼
		String path = TransmitData.class.getResource(".").getPath().toString();
		StringBuffer sql = new StringBuffer();
		// ��ʽ�����ʱ�� ��ɾ���������
		/* =========================================== */
		/*
		 * String temPath_1 = path.split("private")[0]; String temPath =
		 * temPath_1.split("out")[0]; String realPath = temPath+"META-INF/";
		 */
		/* =========================================== */
		// ��ʽ����ʱʹ�����
		String realPath = path.split("classes")[0];
		/* =========================================== */
		// System.out.println(realPath);
		File dir = new File(realPath); // �õ�Ŀ¼
		if (!dir.exists()) {

			// Logger.info("ָ��·�������ڣ�"+realPath);
		} else {
			File[] files = dir.listFiles();
			File s = null;
			for (File f : files) {
				if (f.getName().endsWith(".sql")) {
					s = f;
					break;
				}
			}
			FileReader reader = null;
			try {
				reader = new FileReader(s);
				BufferedReader br = new BufferedReader(reader);
				while (br.ready()) {
					String tmp = br.readLine();
					sql.append(tmp);
				}
			} catch (IOException e) {
				throw new BusinessException(e);
			} finally {
				if (reader != null) {
					try {
						reader.close();
					} catch (IOException e) {
						throw new BusinessException(e);
					}
				}
			}
		}
		return sql.toString();

	}

	/**
	 * ������׼��ִ��Ϣ(XML)
	 * 
	 * @return Document ���������Ļ�ִ��Ϣ�ĵ�
	 * @throws Throwable
	 */
	public static Document createNormMsg() throws Throwable {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding='UTF-8'?>");
		xml.append("\n<ufinterface billtype=\"info\" roottag=\"norminfo\" successful=\"Y\">");
		xml.append("\n<norminfo>");
		xml.append("\n<infocode>990000</infocode>");
		xml.append("\n<infoscription>����ɹ���</infoscription>");
		xml.append("\n</norminfo>");
		xml.append("\n</ufinterface>");
		return toFormatedDoc(xml.toString());
	}

	/**
	 * �������ڻ�ִ��ϢXML
	 * 
	 * @return Document ��ʼ���ĳ�ʱ����XML�ĵ�
	 * @throws Throwable
	 */
	public static Document createOutTimeMsg() throws Throwable {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding='UTF-8'?>");
		xml.append("\n<ufinterface billtype=\"msg\" filename=\"\" isexchange=\"N\" proc=\"add\" receiver=\"\" replace=\"Y\" roottag=\"sendresult\" sender=\"\" successful=\"N\">");
		xml.append("\n<sendresult>");
		xml.append("\n<resultcode>900000</resultcode>");
		xml.append("\n<resultdescription>����ƾ֤�ϴ�����</resultdescription>");
		xml.append("\n</sendresult>");
		xml.append("\n</ufinterface>");
		return toFormatedDoc(xml.toString());
	}

	/**
	 * ���XML��Ԫ�ص����Լ���(���ط���)
	 * 
	 * @param ori_doc
	 * @return Map
	 * @throws Exception
	 */
	public static Map<String, String> getXmlRootAttribute(Document ori_doc) {
		Map<String, String> result = new HashMap<String, String>();
		Node ufinterface = ori_doc.getElementsByTagName("ufinterface").item(0);
		NamedNodeMap attrs = ufinterface.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			Node temp = attrs.item(i);
			result.put(temp.getNodeName(), temp.getNodeValue());
		}
		return result;
	}

	/**
	 * ��Document�ĵ�������String�ַ���
	 * 
	 * @param obj
	 *            ��ת���ĵ�
	 * @return String ת������ĵ�
	 * @throws Exception
	 */
	public static String toFormatedXML(Document obj) throws BusinessException {
		StringBuffer sb = new StringBuffer();
		try {
			Document doc = (Document) obj;
			TransformerFactory transFactory = TransformerFactory.newInstance();
			Transformer transFormer = transFactory.newTransformer();
			transFormer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
			DOMSource domSource = new DOMSource(doc);
			StringWriter sw = new StringWriter();
			StreamResult xmlResult = new StreamResult(sw);
			transFormer.transform(domSource, xmlResult);
			sb.append(sw.toString());
			if (sw != null) { // �ͷ���Դ
				sw.flush();
				sw.close();
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return sb.toString();
	}

	/**
	 * ��String�ĵ�������ΪDocument�ĵ�
	 * 
	 * @param str
	 *            �������ĵ���
	 * @return Document �������ĵ�
	 * @throws Throwable
	 */
	public static Document toFormatedDoc(String str) throws BusinessException { // /��Ҫ�Ż�
		Document doc = null;
		InputStream strm = null; // �ֽ�������
		InputSource source = null; // ��Դ������
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
			// ���ַ���ת��Ϊ��������������
			strm = new ByteArrayInputStream(str.getBytes("UTF-8"));
			// תΪ������Դ��
			source = new InputSource(strm);
			// ����Ϊ Document �ĵ�
			doc = docBuilder.parse(source);
		} catch (Exception e) {
			throw new BusinessException("�����XML�ĸ�ʽ����ȷ�����֤!", e);

		} finally {
			if (strm != null) {
				try {
					strm.close();
				} catch (IOException e) {
					throw new BusinessException(e);
				}
			}
		}
		return doc;
	}

	/**
	 * �Ƿ�֧��ѹ������ת��
	 * 
	 * @param compress
	 *            ��ת���ı��� "Y" ѹ����ʽ "N" ��ѹ��
	 * @return Boolean
	 */
	public static Boolean toFormatedCompress(String compress) {
		return "Y".equalsIgnoreCase(compress) ? Boolean.TRUE : Boolean.FALSE;
	}
}
