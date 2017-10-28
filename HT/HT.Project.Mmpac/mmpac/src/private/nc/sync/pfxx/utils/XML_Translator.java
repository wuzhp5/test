package nc.sync.pfxx.utils;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.Text;

public class XML_Translator {

	/** 对含有子结点的结点分行显示 */
	private boolean m_lastIsAString = false;
	/** 对只含有属性的结点显示 */
	private boolean m_hasOnlyAtrr = false;

	private String getSpace(int depth) {
		char[] ca = new char[depth];
		for (int i = 0; i < ca.length; i++) {
			ca[i] = '\t';
		}
		return new String(ca);
	}

	public void saveToFile(String fileName, StringBuffer fileContent) throws Exception {
		OutputStreamWriter out = null;
		if (fileName != null && fileContent != null) {
			try {
				nc.vo.jcom.io.FileUtil.createDirectoryAsNeeded(fileName, null);
				out = new OutputStreamWriter(new FileOutputStream(fileName, false), "UTF-8");
				out.write(fileContent.toString());
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (out != null)
					out.close();
			}
		}
	}

	public void writeXMLFormatString(StringBuffer fileBuffer, Node node, int depth) {
		depth++;
		int type = node.getNodeType();
		switch (type) {
		case Node.DOCUMENT_NODE:
			fileBuffer.append("<?xml version=\"1.0\" encoding='UTF-8'?>");
			writeXMLFormatString(fileBuffer, ((Document) node).getDocumentElement(), depth);
			break;
		case Node.ELEMENT_NODE:
			fileBuffer.append("\r\n");
			fileBuffer.append(getSpace(depth) + "<");
			fileBuffer.append(node.getNodeName());
			org.w3c.dom.NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				Node attr = attrs.item(i);
				fileBuffer.append(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
			}
			org.w3c.dom.NodeList children = node.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				if (len > 0)
					fileBuffer.append(">");
				else {
					fileBuffer.append(" />");
					m_hasOnlyAtrr = true;
				}
				for (int i = 0; i < len; i++)
					writeXMLFormatString(fileBuffer, children.item(i), depth);
			}

			break;
		case Node.ENTITY_REFERENCE_NODE:
			fileBuffer.append("&");
			fileBuffer.append(node.getNodeName());
			fileBuffer.append(";");
			break;
		case Node.CDATA_SECTION_NODE:
			fileBuffer.append(getSpace(depth) + "<![CDATA[");
			fileBuffer.append(node.getNodeValue());
			fileBuffer.append("]]>");
			break;
		case Node.TEXT_NODE:
			fileBuffer.append(node.getNodeValue());
			m_lastIsAString = true;
			break;
		case Node.PROCESSING_INSTRUCTION_NODE:
			fileBuffer.append(getSpace(depth) + "<?");
			fileBuffer.append(node.getNodeName());
			String data = node.getNodeValue();
			{
				fileBuffer.append("");
				fileBuffer.append(data);
			}
			fileBuffer.append("?>");
			break;
		}
		if (type == Node.ELEMENT_NODE) {
			if (!m_hasOnlyAtrr) {
				if (!m_lastIsAString) {
					fileBuffer.append("\r\n");
					fileBuffer.append(getSpace(depth) + "</");
				} else
					fileBuffer.append("</");
				fileBuffer.append(node.getNodeName());
				fileBuffer.append('>');
				m_lastIsAString = false;
			} else {
				m_hasOnlyAtrr = false;
			}
		}
	}

	/**
	 * @param fileBuffer
	 *            将文件内容追加到该StringBuffer中
	 * @param node
	 *            从此节点中获取XML内容
	 * @param depth
	 *            XML的第一个页签到打印面板边缘的距离，depth为0时表示距离一个‘/t’的距离。
	 * @param encode
	 *            编码方式
	 */
	public void writeXMLFormatString(StringBuffer fileBuffer, Node node, int depth, String encode) {
		/**/
		depth++;
		int type = node.getNodeType();
		switch (type) {
		case Node.DOCUMENT_NODE:
			fileBuffer.append("<?xml version=\"1.0\" encoding='" + encode + "'?>");
			writeXMLFormatString(fileBuffer, ((Document) node).getDocumentElement(), depth);
			break;
		case Node.ELEMENT_NODE:
			fileBuffer.append("\r\n");
			fileBuffer.append(getSpace(depth) + "<");
			fileBuffer.append(node.getNodeName());
			org.w3c.dom.NamedNodeMap attrs = node.getAttributes();
			for (int i = 0; i < attrs.getLength(); i++) {
				Node attr = attrs.item(i);
				fileBuffer.append(" " + attr.getNodeName() + "=\"" + attr.getNodeValue() + "\"");
			}
			org.w3c.dom.NodeList children = node.getChildNodes();
			if (children != null) {
				int len = children.getLength();
				if (len > 0)
					fileBuffer.append(">");
				else {
					fileBuffer.append(" />");
					m_hasOnlyAtrr = true;
				}
				for (int i = 0; i < len; i++)
					writeXMLFormatString(fileBuffer, children.item(i), depth);
			}

			break;
		case Node.ENTITY_REFERENCE_NODE:
			fileBuffer.append("&");
			fileBuffer.append(node.getNodeName());
			fileBuffer.append(";");
			break;
		case Node.CDATA_SECTION_NODE:
			fileBuffer.append(getSpace(depth) + "<![CDATA[");
			fileBuffer.append(node.getNodeValue());
			fileBuffer.append("]]>");
			break;
		case Node.TEXT_NODE:
			fileBuffer.append(node.getNodeValue());
			m_lastIsAString = true;
			break;
		case Node.PROCESSING_INSTRUCTION_NODE:
			fileBuffer.append(getSpace(depth) + "<?");
			fileBuffer.append(node.getNodeName());
			String data = node.getNodeValue();
			{
				fileBuffer.append("");
				fileBuffer.append(data);
			}
			fileBuffer.append("?>");
			break;
		}
		if (type == Node.ELEMENT_NODE) {
			if (!m_hasOnlyAtrr) {
				if (!m_lastIsAString) {
					fileBuffer.append("\r\n");
					fileBuffer.append(getSpace(depth) + "</");
				} else
					fileBuffer.append("</");
				fileBuffer.append(node.getNodeName());
				fileBuffer.append('>');
				m_lastIsAString = false;
			} else {
				m_hasOnlyAtrr = false;
			}
		}
	}

	public void setElement(Document document, Element entry, String name, String value) {
		Element element = document.createElement(name);
		Text text = document.createTextNode(name);
		text.setData(value == null ? "" : value);
		element.appendChild(text);
		entry.appendChild(element);
	}
}
