package nc.vo.sync.param;

import nc.vo.pub.SuperVO;

public class BomParams extends SuperVO {

	private static final long serialVersionUID = -1033338566330625412L;

	private String cbomid;
	private String hbdefault;
	private String hcmaterialid;
	private String hversion;
	private String pk_org;
	private String pk_org_v;
	private String hfbomsource;
	private String hfversiontype;
	private String fbomtype;
	private String fbillstatus;
	private String pk_group;

	public final static String CBOMID = "cbomid";
	public final static String HBDEFAULT = "hbdefault";
	public final static String HCMATERIALID = "hcmaterialid";
	public final static String HVERSION = "hversion";
	public final static String PK_ORG = "pk_org";
	public final static String PK_ORG_V = "pk_org_v";
	public final static String HFBOMSOURCE = "hfbomsource";
	public final static String HFVERSIONTYPE = "hfversiontype";
	public final static String FBOMTYPE = "fbomtype";
	public final static String FBILLSTATUS = "fbillstatus";
	public final static String PK_GROUP = "pk_group";

	public BomParams() {
	}

	public String getCbomid() {
		return cbomid;
	}

	public void setCbomid(String cbomid) {
		this.cbomid = cbomid;
	}

	public String getHbdefault() {
		return hbdefault;
	}

	public void setHbdefault(String hbdefault) {
		this.hbdefault = hbdefault;
	}

	public String getHcmaterialid() {
		return hcmaterialid;
	}

	public void setHcmaterialid(String hcmaterialid) {
		this.hcmaterialid = hcmaterialid;
	}

	public String getHversion() {
		return hversion;
	}

	public void setHversion(String hversion) {
		this.hversion = hversion;
	}

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getPk_org_v() {
		return pk_org_v;
	}

	public void setPk_org_v(String pk_org_v) {
		this.pk_org_v = pk_org_v;
	}

	public String getHfbomsource() {
		return hfbomsource;
	}

	public void setHfbomsource(String hfbomsource) {
		this.hfbomsource = hfbomsource;
	}

	public String getHfversiontype() {
		return hfversiontype;
	}

	public void setHfversiontype(String hfversiontype) {
		this.hfversiontype = hfversiontype;
	}

	public String getFbomtype() {
		return fbomtype;
	}

	public void setFbomtype(String fbomtype) {
		this.fbomtype = fbomtype;
	}

	public String getFbillstatus() {
		return fbillstatus;
	}

	public void setFbillstatus(String fbillstatus) {
		this.fbillstatus = fbillstatus;
	}

	public String getPk_group() {
		return pk_group;
	}

	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}
	
	public static String getColumns4Sql(){
		return "cbomid,hbdefault,hcmaterialid,hversion,pk_org,pk_org_v,hfbomsource,hfversiontype,fbomtype,fbillstatus,pk_group";
	}

}
