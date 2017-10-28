package nc.vo.sync.constrast;

import nc.vo.pub.SuperVO;

public class ConstrastVO extends SuperVO {

	private static final long serialVersionUID = 8104022185092127820L;
	
	private String pk_constrasts;
	private String billtype;
	private String billtypename;
	private String field;
	private String sysdes;
	private String exfield;
	private String exsysdes;
	private String exsystem;
	private String isnull;
	private String wherepart;
	private String memo;
	public final static String PK_CONSTRASTS = "pk_constrasts";
	public final static String BILLTYPE = "billtype";
	public final static String BILLTYPENAME = "billtypename";
	public final static String FIELD = "field";
	public final static String SYSDES = "sysdes";
	public final static String EXFIELD = "exfield";
	public final static String EXSYSDES = "exsysdes";
	public final static String EXSYSTEM = "exsystem";
	public final static String ISNULL = "isnull";
	public final static String WHEREPART = "wherepart";
	public final static String MEMO = "memo";
	
	public ConstrastVO() {
	}

	public String getPk_constrasts() {
		return pk_constrasts;
	}

	public void setPk_constrasts(String pk_constrasts) {
		this.pk_constrasts = pk_constrasts;
	}

	public String getBilltype() {
		return billtype;
	}

	public void setBilltype(String billtype) {
		this.billtype = billtype;
	}

	public String getBilltypename() {
		return billtypename;
	}

	public void setBilltypename(String billtypename) {
		this.billtypename = billtypename;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getSysdes() {
		return sysdes;
	}

	public void setSysdes(String sysdes) {
		this.sysdes = sysdes;
	}

	public String getExfield() {
		return exfield;
	}

	public void setExfield(String exfield) {
		this.exfield = exfield;
	}

	public String getExsysdes() {
		return exsysdes;
	}

	public void setExsysdes(String exsysdes) {
		this.exsysdes = exsysdes;
	}

	public String getExsystem() {
		return exsystem;
	}

	public void setExsystem(String exsystem) {
		this.exsystem = exsystem;
	}

	public String getIsnull() {
		return isnull;
	}

	public void setIsnull(String isnull) {
		this.isnull = isnull;
	}

	public String getWherepart() {
		return wherepart;
	}

	public void setWherepart(String wherepart) {
		this.wherepart = wherepart;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}
	
	public static String getColumns4Sql(){
		return "pk_constrasts,billtype,billtypename,field,sysdes,exfield,exsysdes,exsystem,isnull,wherepart,memo";
	}
	
	public static String getDefaultTable(){
		return "xx_constrasts";
	}

}
