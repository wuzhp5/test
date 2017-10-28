package nc.sync.pfxx.entity;

public class PfxxServiceBaseInfo {
	private String billType;
	private String tableName;
	private String fileName;
	private String[] itemNames;
	private String billCodeField;
	private String sender;
	private String rowField;

	public PfxxServiceBaseInfo() {
	}

	public String getBillType() {
		return billType;
	}

	public void setBillType(String billType) {
		this.billType = billType;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String[] getItemNames() {
		return itemNames;
	}

	public void setItemNames(String[] itemNames) {
		this.itemNames = itemNames;
	}

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getBillCodeField() {
		return billCodeField;
	}

	public void setBillCodeField(String billCodeField) {
		this.billCodeField = billCodeField;
	}

	public String getRowField() {
		return rowField;
	}

	public void setRowField(String rowField) {
		this.rowField = rowField;
	}

}
