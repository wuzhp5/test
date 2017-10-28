/*** Eclipse Class Decompiler plugin, copyright (c) 2012 Chao Chen (cnfree2000@hotmail.com) ***/
package nc.vo.ic.flowaccount.view;

import nc.impl.pubapp.env.BSContext;
import nc.vo.ic.flowaccount.entity.FlowAccountVO;
import nc.vo.ic.material.define.OutPriority;
import nc.vo.ic.pub.define.ViewField;
import nc.vo.ic.pub.util.StringUtil;
import nc.vo.pub.JavaType;
import nc.vo.scmf.ic.mbatchcode.BatchcodeVO;

public class KeepDetailViewForSearch extends KeepDetailView {
	private static final long serialVersionUID = 2010090614150001L;

	private boolean bwithloc = false;

	private boolean bSortByBatchCode = false;
	private OutPriority outpriority;

	// 基本信息表字段
	protected final String[] flowfields = {
			// FlowAccountVO.CBILLTYPECODE,
			FlowAccountVO.CMATERIALOID,
			FlowAccountVO.CMATERIALVID,
			FlowAccountVO.CPRODUCTORID,
			FlowAccountVO.CPROJECTID,
			FlowAccountVO.CASSCUSTID,
			FlowAccountVO.CSTATEID,
			FlowAccountVO.CTPLCUSTOMERID,
			FlowAccountVO.CASTUNITID,
			FlowAccountVO.CVENDORID,
			FlowAccountVO.CVMIVENDERID,
			FlowAccountVO.CWAREHOUSEID,
			// FlowAccountVO.NINASSISTNUM,
			// FlowAccountVO.NINGROSSNUM,
			// FlowAccountVO.NINNUM,
			// FlowAccountVO.NOUTASSISTNUM,
			// FlowAccountVO.NOUTGROSSNUM,
			// FlowAccountVO.NOUTNUM,
			FlowAccountVO.PK_BATCHCODE, FlowAccountVO.PK_GROUP,
			FlowAccountVO.PK_ORG, FlowAccountVO.PK_ORG_V,
			FlowAccountVO.PK_PACKSORT, FlowAccountVO.VBATCHCODE,
			FlowAccountVO.VCHANGERATE, FlowAccountVO.VFREE1,
			FlowAccountVO.VFREE10, FlowAccountVO.VFREE2, FlowAccountVO.VFREE3,
			FlowAccountVO.VFREE4, FlowAccountVO.VFREE5, FlowAccountVO.VFREE6,
			FlowAccountVO.VFREE7, FlowAccountVO.VFREE8, FlowAccountVO.VFREE9,
			FlowAccountVO.DBIZDATE, FlowAccountVO.VHASHCODE,
			FlowAccountVO.VBODYUSERDEF4 };

	public KeepDetailViewForSearch(OutPriority priority, boolean bwithloc,
			boolean bsortbybatchcode) {
		this.bwithloc = bwithloc;
		this.outpriority = priority;
		this.bSortByBatchCode = bsortbybatchcode;
	}

	public void initView() {
		addBasehandField();
		if (this.bwithloc) {
			addFlowDetailFields(new String[] { "clocationid" });

			addViewField("whloc", "outpriority", "outpriority",
					JavaType.Integer);
			
			addViewField("whloc", "innercode", "innercode",
					JavaType.String);
			
			addViewField("whloc", "name", "rackname",
					JavaType.String);
		}

		String nonhandnum = null;
		String nonhandastnum = null;
		String ngrossnum = null;
		if (this.bwithloc) {
			nonhandnum = "sum(COALESCE(flowdetail.ninnum,0.0)-COALESCE(flowdetail.noutnum,0.0))";

			nonhandastnum = "sum(COALESCE(flowdetail.ninassistnum,0.0)-COALESCE(flowdetail.noutassistnum,0.0))";

			ngrossnum = "sum(COALESCE(flowdetail.ningrossnum,0.0)-COALESCE(flowdetail.noutgrossnum,0.0))";
		} else {
			nonhandnum = "sum(COALESCE(flow.ninnum,0.0)-COALESCE(flow.noutnum,0.0))";

			nonhandastnum = "sum(COALESCE(flow.ninassistnum,0.0)-COALESCE(flow.noutassistnum,0.0))";

			ngrossnum = "sum(COALESCE(flow.ningrossnum,0.0)-COALESCE(flow.noutgrossnum,0.0))";
		}

		setViewSumFields(new ViewField[] {
				new ViewField("nonhandnum", nonhandnum, JavaType.UFDouble),
				new ViewField("nonhandastnum", nonhandastnum, JavaType.UFDouble),
				new ViewField("ngrossnum", ngrossnum, JavaType.UFDouble) });

		if (this.outpriority != OutPriority.FIFO) {
			addHavingConditon(null, nonhandnum, ">", "0.0");
			addHavingConditon(null,
					new StringBuilder().append(" or ").append(nonhandastnum)
							.toString(), ">", "0.0");
		}

		Object obj = BSContext.getInstance().getSession(
				"ManufactureDatePickAutoAction");
		if (obj == null) {
			addViewSortFields("flow", true, new String[] { "pk_org_v",
					"cwarehouseid", "cmaterialvid" });

			if ((this.outpriority == OutPriority.LIFO)
					|| (this.outpriority == OutPriority.FIFO)) {
				addViewSortFields("flow", this.outpriority == OutPriority.FIFO,
						new String[] { "dbizdate" });
			}

			if (this.bSortByBatchCode) {
				addViewSortFields("flow", true, new String[] { "vbatchcode" });
			}

			addViewSortFields("batch", true, new String[] { "dvalidate" });

			if (!(this.bwithloc))
				return;
			addViewSortFields("whloc", true, new String[] { "outpriority" });
			addViewSortFields("whloc", true, new String[] { "name" });
		} else {
			//按生产日期排序查询的时候再拼接
		}
	}

	protected String getJoinPart() {
		StringBuilder bf = new StringBuilder(getViewFlowJoinPart());
		bf.append(new StringBuilder().append(" ").append(addJoinBatch())
				.toString());
		if (this.bwithloc) {
			bf.append(addJoinFlowDetail(true));
			bf.append(new StringBuilder().append(" left outer join ")
					.append(AbstractFlowView.location).append(" ")
					.append("whloc").toString());

			bf.append(" on(flowdetail.clocationid");

			bf.append("=whloc.pk_rack) ");
		}

		return bf.toString();
	}

	@Override
	protected void addBasehandField() {

		Object obj = BSContext.getInstance().getSession(
				"ManufactureDatePickAutoAction");
		if (obj == null) {
			this.addFlowFields(super.flowfields);
		} else {
			this.addFlowFields(this.flowfields);
		}
		this.addBatchFields(new String[] { BatchcodeVO.CQUALITYLEVELID,
				BatchcodeVO.DPRODUCEDATE, BatchcodeVO.DVALIDATE, });
	}
	
}