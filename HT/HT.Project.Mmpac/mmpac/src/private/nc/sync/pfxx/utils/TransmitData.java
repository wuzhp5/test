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
	 * 获得连接
	 * 
	 * @param url
	 *            NC交换平台地址
	 * @param bcompress
	 *            是否压缩流的方式发送
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
			throw new IOException("在获取连接中出错:" + e.getMessage());
		}
		System.out.println(connection);
		return connection;
	}

	/**
	 * 发送Document文档到NC交换平台
	 * 
	 * @param doc
	 *            要发送的文档
	 * @param url
	 *            发送地址
	 * @param outputEncoding
	 *            编码
	 * @param bcompress
	 *            是否支持压缩
	 * @return result NC回执信息
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
			result.getElementsByTagName("infoscription").item(0).setTextContent("异常信息：" + "在单据传输过程中出错:" + e.getMessage());
		} finally {
			if (curconnection != null)
				curconnection.disconnect();
		}
		return result;
	}

	/**
	 * 取得NC交换平台回执信息
	 * 
	 * @param connection
	 *            交换平台连接
	 * @param bcompress
	 *            是否压缩率
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
			 * GZIPInputStream(connection.getInputStream())); /// 需要优化 } else {
			 * System.out.println(connection.getInputStream());
			 */
			doc = XMLUtil.getDocumentBuilder().parse(connection.getInputStream());
		} catch (Exception e) {
			throw new Exception("读回执时出错:", e);
		}
		return doc;
	}

	/**
	 * 创建标准的异常信息回执文档
	 * 
	 * @return Document 标准异常回执信息文档
	 * @throws Throwable
	 */
	public static Document createExceptionMsg() throws Throwable {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		xml.append("\n<ufinterface billtype=\"Exception\" roottag=\"exceptioninfo\" successful=\"N\">");
		xml.append("\n<exceptioninfo>");
		xml.append("\n<infocode>900001</infocode>");
		xml.append("\n<infoscription>异常信息：</infoscription>");
		xml.append("\n</exceptioninfo>");
		xml.append("\n</ufinterface>");
		return toFormatedDoc(xml.toString());
	}

	/**
	 * 查找META-INF目录下以.sql结尾的文件，并读取其中的内容
	 * 
	 * @return sql
	 */
	@SuppressWarnings("resource")
	public static String readSQLFile() throws BusinessException {
		// 根据当前类获得根目录
		String path = TransmitData.class.getResource(".").getPath().toString();
		StringBuffer sql = new StringBuffer();
		// 正式部署的时候 请删除下面语句
		/* =========================================== */
		/*
		 * String temPath_1 = path.split("private")[0]; String temPath =
		 * temPath_1.split("out")[0]; String realPath = temPath+"META-INF/";
		 */
		/* =========================================== */
		// 正式部署时使用语句
		String realPath = path.split("classes")[0];
		/* =========================================== */
		// System.out.println(realPath);
		File dir = new File(realPath); // 得到目录
		if (!dir.exists()) {

			// Logger.info("指定路径不存在！"+realPath);
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
	 * 创建标准回执信息(XML)
	 * 
	 * @return Document 处理正常的回执信息文档
	 * @throws Throwable
	 */
	public static Document createNormMsg() throws Throwable {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding='UTF-8'?>");
		xml.append("\n<ufinterface billtype=\"info\" roottag=\"norminfo\" successful=\"Y\">");
		xml.append("\n<norminfo>");
		xml.append("\n<infocode>990000</infocode>");
		xml.append("\n<infoscription>处理成功：</infoscription>");
		xml.append("\n</norminfo>");
		xml.append("\n</ufinterface>");
		return toFormatedDoc(xml.toString());
	}

	/**
	 * 创建超期回执信息XML
	 * 
	 * @return Document 初始化的超时返回XML文档
	 * @throws Throwable
	 */
	public static Document createOutTimeMsg() throws Throwable {
		StringBuffer xml = new StringBuffer("<?xml version=\"1.0\" encoding='UTF-8'?>");
		xml.append("\n<ufinterface billtype=\"msg\" filename=\"\" isexchange=\"N\" proc=\"add\" receiver=\"\" replace=\"Y\" roottag=\"sendresult\" sender=\"\" successful=\"N\">");
		xml.append("\n<sendresult>");
		xml.append("\n<resultcode>900000</resultcode>");
		xml.append("\n<resultdescription>超出凭证上传期限</resultdescription>");
		xml.append("\n</sendresult>");
		xml.append("\n</ufinterface>");
		return toFormatedDoc(xml.toString());
	}

	/**
	 * 获得XML根元素的属性集合(重载方法)
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
	 * 把Document文档解析成String字符串
	 * 
	 * @param obj
	 *            待转换文档
	 * @return String 转换后的文档
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
			if (sw != null) { // 释放资源
				sw.flush();
				sw.close();
			}
		} catch (Exception e) {
			throw new BusinessException(e);
		}
		return sb.toString();
	}

	/**
	 * 将String文档串解析为Document文档
	 * 
	 * @param str
	 *            待解析文档串
	 * @return Document 解析后文档
	 * @throws Throwable
	 */
	public static Document toFormatedDoc(String str) throws BusinessException { // /需要优化
		Document doc = null;
		InputStream strm = null; // 字节输入流
		InputSource source = null; // 资源输入流
		try {
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = builderFactory.newDocumentBuilder();
			// 将字符串转换为比特数组输入流
			strm = new ByteArrayInputStream(str.getBytes("UTF-8"));
			// 转为输入资源流
			source = new InputSource(strm);
			// 解析为 Document 文档
			doc = docBuilder.parse(source);
		} catch (Exception e) {
			throw new BusinessException("导入的XML的格式不正确，请查证!", e);

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
	 * 是否支持压缩流的转换
	 * 
	 * @param compress
	 *            代转换的编码 "Y" 压缩方式 "N" 不压缩
	 * @return Boolean
	 */
	public static Boolean toFormatedCompress(String compress) {
		return "Y".equalsIgnoreCase(compress) ? Boolean.TRUE : Boolean.FALSE;
	}
}
