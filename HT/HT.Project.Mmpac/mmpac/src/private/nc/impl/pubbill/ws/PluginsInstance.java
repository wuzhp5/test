package nc.impl.pubbill.ws;

import java.util.HashMap;
import java.util.Map;

import nc.itf.pubbill.proc.IProcessBillData;

public class PluginsInstance {

	private Map<String, Object> map = new HashMap<String, Object>();
	public static PluginsInstance instance = null;

	private PluginsInstance() {
	}

	public static PluginsInstance getInstance() {
		if (instance == null) {
			instance = new PluginsInstance();
		}
		return instance;
	}

	public void put(String key, IProcessBillData itf) {
		if (key == null || key.trim().equals("") || itf == null) {
			return;
		}
		this.map.put(key, itf);
	}

	public IProcessBillData getInterface(String key) {
		return (IProcessBillData) map.get(key);
	}

	public Boolean hasInterface(String key) {
		return map.containsKey(key);
	}

}
