package nc.pub.sync.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class PropertiesUtil {

	private final static Properties prop = new Properties();
	private static PropertiesUtil propertiesUtil;
	private FileInputStream fileInputStream = null;

	private PropertiesUtil() {
	}

	public final static PropertiesUtil getInstance() {
		if (propertiesUtil == null) {
			propertiesUtil = new PropertiesUtil();
		}
		return propertiesUtil;
	}

	public Properties getProperty() {
		try {
			if (this.fileInputStream != null) {
				this.fileInputStream.close();
			}
			this.fileInputStream = new FileInputStream(new File(PropertiesUtil.class.getResource(".").getPath().toString() + "proc.properties"));
			prop.load(fileInputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

}
