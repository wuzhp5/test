package nc.vo.sync.param;

import nc.vo.pub.SuperVO;

public class MaterialParams extends SuperVO {

	private static final long serialVersionUID = 6565855373932065822L;

	private String pk_material;
	private String pk_material_v;
	private String code;
	private String name;
	private String pk_group;
	private String pk_org;
	private String pk_measdoc;
	private String pk_apartmeasdoc;
	private String measrate;

	public final static String PK_MATERIAL = "pk_material";
	public final static String PK_MATERIAL_V = "pk_material_v";
	public final static String CODE = "code";
	public final static String NAME = "name";
	public final static String PK_GROUP = "pk_group";
	public final static String PK_ORG = "pk_org";
	public final static String PK_MEASDOC = "pk_measdoc";
	public final static String PK_APARTMEASDOC = "pk_apartmeasdoc";
	public final static String MEASRATE = "measrate";

	public MaterialParams() {
	}

	public String getPk_material() {
		return pk_material;
	}

	public void setPk_material(String pk_material) {
		this.pk_material = pk_material;
	}

	public String getPk_material_v() {
		return pk_material_v;
	}

	public void setPk_material_v(String pk_material_v) {
		this.pk_material_v = pk_material_v;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPk_group() {
		return pk_group;
	}

	public void setPk_group(String pk_group) {
		this.pk_group = pk_group;
	}

	public String getPk_org() {
		return pk_org;
	}

	public void setPk_org(String pk_org) {
		this.pk_org = pk_org;
	}

	public String getPk_measdoc() {
		return pk_measdoc;
	}

	public void setPk_measdoc(String pk_measdoc) {
		this.pk_measdoc = pk_measdoc;
	}

	public String getPk_apartmeasdoc() {
		return pk_apartmeasdoc;
	}

	public void setPk_apartmeasdoc(String pk_apartmeasdoc) {
		this.pk_apartmeasdoc = pk_apartmeasdoc;
	}

	public String getMeasrate() {
		return measrate;
	}

	public void setMeasrate(String measrate) {
		this.measrate = measrate;
	}
	
	public static String getColumns2Sql(){
		return "pk_material,pk_material_v,code,name,pk_group,pk_org,pk_measdoc,pk_apartmeasdoc,measrate";
	}

}
