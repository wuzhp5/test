package nc.ui.cof.orderplan.maintain.excel.out.builder;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import nc.ui.cof.orderplan.maintain.excel.out.impl.DecorateTool;
import nc.ui.cof.orderplan.maintain.excel.out.infc.IDecorateTool;
import nc.ui.cof.orderplan.maintain.excel.out.infc.IMergeRule;
import nc.ui.cof.orderplan.maintain.excel.out.infc.Ibuild;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

/**
 * 针对合并规则的构造器
 * 
 * @since 6.0
 * @version 2013-12-17 上午10:34:49
 * @author ruhuic
 */
public class MergeBuilder implements Ibuild {

	private Sheet sheet;

	private IMergeRule rule;

	private List<Integer> rowList = new ArrayList<Integer>();

	private List<Integer> columnList = new ArrayList<Integer>();

	public MergeBuilder(Sheet sheet, IMergeRule mergerule) {
		this.sheet = sheet;
		this.rule = mergerule;
	}

	@Override
	public void build() {
		createCell();
		fillValue();

	}

	private void fillValue() {
		Map<Integer, List<Object>> value = this.rule.getValue();
		if (value == null)
			return;
		IDecorateTool decoratetool = this.rule.getDecorateTool();
		for (int i = 0; i < this.rowList.size(); i++) {
			Row row = this.sheet.getRow(this.rowList.get(i));
			List<Object> rowvalue = value.get(i);
			if (rowvalue == null)
				continue;
			for (int j = 0; j < this.columnList.size()-1; j++) {
				Cell cell = row.getCell(this.columnList.get(j));
				Object cellvalue = rowvalue.get(j);
				if (cellvalue != null) {
					if (decoratetool != null) {
						decoratetool.decorateCell(cell, cellvalue, sheet);
					} else {
						DecorateTool defaulttool = new DecorateTool();
						defaulttool.decorateCell(cell, cellvalue, sheet);
					}

				}
			}

		}

	}

	private void createCell() {
		int fromrow = this.rule.getFromRow();
		int torow = this.rule.getToRow();
		int fromcolumn = this.rule.getFromColumn();
		int tocolumn = this.rule.getToColumn();
		int rowSpan = this.rule.getMergeSpan()[0];
		int columnSpan = this.rule.getMergeSpan()[1];
		for (int i = fromrow; i <= torow; i++) {
			Row row = sheet.getRow(i) == null ? sheet.createRow(i) : sheet.getRow(i);
			for (int j = fromcolumn; j <= tocolumn; j++) {
				if (row.getCell(j) == null)
					row.createCell(j);
			}
		}
		boolean first = true;
		for (int i = fromrow; i <= torow; i = i + rowSpan) {
			int mergefromrow = i;
			int mergetorow = i + rowSpan - 1;
			this.rowList.add(i);

			for (int j = fromcolumn; j <= tocolumn - columnSpan + 1; j = j + columnSpan) {

				int mergefromcolumn = j;
				int mergetocolumn = j + columnSpan - 1;
				this.createMergeRegion(mergefromrow, mergetorow, mergefromcolumn, mergetocolumn);
				if (first) {
					this.columnList.add(j);
				}
			}
			first = false;
		}

	}

	private void createMergeRegion(int fromrow, int torow, int fromcolumn, int tocolumn) {
		CellRangeAddress region = new CellRangeAddress(fromrow, torow, fromcolumn, tocolumn);

		sheet.addMergedRegion(region);
	}

}
