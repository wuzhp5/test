package nc.vo.cache.params;

import nc.vo.pub.SuperVO;

public class CacheVO extends SuperVO {

	private static final long serialVersionUID = 2468857054757795683L;
	private String pk_params;
	private Object param;
	private String memo;

	public CacheVO(){}
	
	public String getPk_params() {
		return pk_params;
	}

	public void setPk_params(String pk_params) {
		this.pk_params = pk_params;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

}
