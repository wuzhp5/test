package nc.vo.sync.log;

import nc.vo.pub.lang.UFBoolean;

public class LoggerVO {

	public static String PK_BILL = "pk_bill";
	public static String VBILLCODE = "vbillcode";
	public static String MESCODE = "mescode";
	public static String BILLTYPE = "billtype";
	public static String ISSUCCESS = "issuccess";
	public static String CONTENT = "content";
	public static String RES = "res";

	private String pk_bill;
	private String vbillcode;
	private String mescode;
	private String billtype;
	private UFBoolean issuccess;
	private String content;
	private String res;

	public LoggerVO() {
	}

	public String getPk_bill() {
		return pk_bill == null ? "~" : pk_bill;
	}

	public void setPk_bill(String pk_bill) {
		this.pk_bill = pk_bill;
	}

	public String getVbillcode() {
		return vbillcode == null ? "~" : vbillcode;
	}

	public void setVbillcode(String vbillcode) {
		this.vbillcode = vbillcode;
	}

	public String getMescode() {
		return mescode  == null ? "~" : mescode;
	}

	public void setMescode(String mescode) {
		this.mescode = mescode;
	}

	public String getBilltype() {
		return billtype  == null ? "~" : billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public String getIssuccess() {
		return issuccess == null ? "~" : issuccess.toString();
	}

	public void setIssuccess(UFBoolean issuccess) {
		this.issuccess = issuccess;
	}

	public String getContent() {
		return content == null ? "~" : content;
	}

	public void setContent(String content) {
		if (content == null) {
			this.content = null;
		} else {
			this.content = content.trim();
		}
	}

	public String getRes() {
		return res  == null ? "~" : res;
	}

	public void setRes(String res) {
		this.res = res;
	}

}
