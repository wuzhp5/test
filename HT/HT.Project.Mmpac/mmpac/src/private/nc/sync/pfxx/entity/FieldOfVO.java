package nc.sync.pfxx.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FieldOfVO {

	private Map<String, String> headDefField = new HashMap<String, String>();
	private List<String> headUndefField = new ArrayList<String>();
	private Map<String, Map<String, String>> bodyDefField = new HashMap<String, Map<String, String>>();
	private Map<String, List<String>> bodyUndefField = new HashMap<String, List<String>>();

	public FieldOfVO() {
	}

	public Map<String, String> getHeadDefField() {
		return headDefField;
	}

	public void setHeadDefField(Map<String, String> headDefField) {
		this.headDefField = headDefField;
	}

	public List<String> getHeadUndefField() {
		return headUndefField;
	}

	public void setHeadUndefField(List<String> headUndefField) {
		this.headUndefField = headUndefField;
	}

	public Map<String, Map<String, String>> getBodyDefField() {
		return bodyDefField;
	}

	public void setBodyDefField(Map<String, Map<String, String>> bodyDefField) {
		this.bodyDefField = bodyDefField;
	}

	public Map<String, List<String>> getBodyUndefField() {
		return bodyUndefField;
	}

	public void setBodyUndefField(Map<String, List<String>> bodyUndefField) {
		this.bodyUndefField = bodyUndefField;
	}

}
