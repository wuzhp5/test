package nc.sync.pfxx.utils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import nc.sync.pfxx.entity.FieldOfVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.ISuperVO;
import nc.vo.pubapp.pattern.model.entity.bill.AbstractBill;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

/**
 * @author wuzhp
 * @function 此类的主要为NC外部交换平台提供XML文件，只适用于单表档案和主子表档案
 */
public class AssemblingXML {
	private XML_Translator translator = new XML_Translator();
	private static AssemblingXML assemble = null;

	private AssemblingXML() {

	}

	public static AssemblingXML getInstance() {
		if (assemble == null) {
			assemble = new AssemblingXML();
		}
		return assemble;
	}

	/**
	 * @param doc
	 * @param field
	 * @param vo
	 * @param ID
	 * @param billType
	 * @param sender
	 * @throws BusinessException
	 */
	public void getXML(Document doc, FieldOfVO field, Object vo, String ID, String billType, String sender, ISuperVO bodyVO) throws BusinessException {
		Node billHead = null;
		if (vo instanceof ISuperVO) {
			billHead = getXMLBillHead(doc, field, (ISuperVO) vo);
		} else if (vo instanceof AbstractBill) {
			billHead = getXMLBillHead(doc, field, (AbstractBill) vo, bodyVO);
		} else {
			throw new BusinessException("VO类型不为ISuperVO或AbstractBill。");
		}
		Node bill = getXMLBill(doc, ID);
		bill.appendChild(billHead);
		Node ufinterface = getXMLUfinterface(doc, billType, sender);
		ufinterface.appendChild(bill);
		doc.appendChild(ufinterface);
	}

	/**
	 * @param doc
	 * @param billType
	 * @param sender
	 * @return
	 */
	public Node getXMLUfinterface(Document doc, String billType, String sender) {
		Element ufinterface = doc.createElement("ufinterface");
		ufinterface.setAttribute("billtype", billType);
		ufinterface.setAttribute("isexchange", "Y");
		ufinterface.setAttribute("replace", "Y");
		ufinterface.setAttribute("sender", sender);
		return ufinterface;
	}

	/**
	 * @param doc
	 * @param ID
	 * @return
	 */
	public Node getXMLBill(Document doc, String ID) {
		Element bill = doc.createElement("bill");
		bill.setAttribute("id", ID);
		return bill;
	}

	/**
	 * @param doc
	 * @param field
	 * @param vo
	 * @param bvos
	 * @return
	 */
	public Node getXMLBillHead(Document doc, FieldOfVO field, ISuperVO vo, ISuperVO[] bvos) {
		Element billHead = doc.createElement("billhead");
		Map<String, String> headDefField = field.getHeadDefField();
		List<String> headUndefField = field.getHeadUndefField();
		Map<String, Map<String, String>> bodyDefField = field.getBodyDefField();
		Map<String, List<String>> bodyUndefField = field.getBodyUndefField();
		Set<String> headKeys = headDefField.keySet();
		Set<String> bodyKeys = bodyDefField.keySet();
		for (String undefField : headUndefField) {
			translator.setElement(doc, billHead, undefField, getNullOrString(vo.getAttributeValue(undefField)));
		}
		for (String key : headKeys) {
			translator.setElement(doc, billHead, key, headDefField.get(key));
		}
		if (bvos == null) {
			ISuperVO[] vos = { vo };
			setItemElement(doc, bodyKeys, billHead, bodyDefField, bodyUndefField, vos);
		} else {
			setItemElement(doc, bodyKeys, billHead, bodyDefField, bodyUndefField, bvos);
		}
		return billHead;
	}

	/**
	 * @param doc
	 * @param field
	 * @param vo
	 * @return
	 */
	public Node getXMLBillHead(Document doc, FieldOfVO field, ISuperVO vo) {
		return getXMLBillHead(doc, field, vo, null);
	}

	/**
	 * @param doc
	 * @param field
	 * @param aggVO
	 * @return
	 */
	public Node getXMLBillHead(Document doc, FieldOfVO field, AbstractBill aggVO, ISuperVO bodyVO) {
		return getXMLBillHead(doc, field, aggVO.getParent(), aggVO.getChildren(bodyVO.getClass()));
	}

	/**
	 * @param doc
	 * @param bodyKeys
	 * @param billHead
	 * @param bodyDefField
	 * @param bodyUndefField
	 * @param bvos
	 */
	public void setItemElement(Document doc, Set<String> bodyKeys, Element billHead, Map<String, Map<String, String>> bodyDefField,
			Map<String, List<String>> bodyUndefField, ISuperVO[] bvos) {
		for (String bodyKey : bodyKeys) {
			Element body = doc.createElement(bodyKey);
			Map<String, String> bodyDefMap = bodyDefField.get(bodyKey);
			Set<String> bodyDefMapKeys = bodyDefMap.keySet();
			for (ISuperVO bvo : bvos) {
				Element item = doc.createElement("item");
				body.appendChild(item);
				for (String undefField : bodyUndefField.get(bodyKey)) {
					translator.setElement(doc, item, undefField, getNullOrString(bvo.getAttributeValue(undefField)));
				}
				for (String key : bodyDefMapKeys) {
					translator.setElement(doc, item, key, bodyDefMap.get(key));
				}
			}
			billHead.appendChild(body);
		}
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
