package nc.bs.sync.log;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import nc.vo.pub.lang.UFDate;

public class Logger {

	public static String UPLOADPATH = "C:\\upload-log.log";
	public static String DOWNLOADPATH = "C:\\download-log.log";
	public static String INFO = "info";
	public static String ERROR = "error";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String billType = "4E";
		String json = "this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.this is NC system uploaded json.";
		String res = "MES receviced!";
		Logger.uploadLog("单据类型【"+billType+"】", "NC上传JSON：【"+json+"】\r\nMES回执内容：【"+res+"】");
	}
	
	public static void downloadLog(String title, String content){
		log(title, INFO, content, DOWNLOADPATH);
	}
	
	/**
	 * @param title
	 * @param content
	 */
	public static void uploadLog(String title, String content){
		log(title, INFO, content, UPLOADPATH);
	}
	
	/**
	 * @param title
	 * @param debuglevel
	 * @param content
	 * @param filePath
	 */
	public static void log(String title, String debuglevel, String content, String filePath) {
		StringBuffer msg = new StringBuffer();
		msg.append("$$ts="+new UFDate(System.currentTimeMillis())+" $$debuglevel="+debuglevel+"  $$msg="+title+"");
		if(content != null){
			msg.append("\r\n"+content);
		}
		msg.append("\r\n");
		FileWriter fw = null;
		PrintWriter pw = null;
		try {
			File f = new File(filePath);
			f.getTotalSpace();
			fw = new FileWriter(f, true);
			pw = new PrintWriter(fw);
			pw.print(msg.toString());
			pw.write("");
			pw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != fw) {
					fw.flush();
				}
				if (null != pw) {
					pw.close();
				}
				if (null != fw) {
					fw.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
