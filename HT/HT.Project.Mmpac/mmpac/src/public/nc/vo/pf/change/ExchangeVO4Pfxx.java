package nc.vo.pf.change;

import nc.vo.pub.SuperVO;

/**
 * @ClassName: nc.vo.pf.change
 * @Description:
 * @author wuzhp
 * @date 2017年3月28日 下午9:38:01
 */
public class ExchangeVO4Pfxx extends SuperVO {

	private static final long serialVersionUID = 7533825611288871744L;

	private String dest_attr;
	private String ruledata;

	public ExchangeVO4Pfxx() {
		super();
	}

	public String getDest_attr() {
		return dest_attr;
	}

	public String getRuledata() {
		return ruledata;
	}

	public void setDest_attr(String dest_attr) {
		this.dest_attr = dest_attr;
	}

	public void setRuledata(String ruledata) {
		this.ruledata = ruledata;
	}

}
