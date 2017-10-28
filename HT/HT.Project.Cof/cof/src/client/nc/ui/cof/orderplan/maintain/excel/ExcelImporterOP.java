package nc.ui.cof.orderplan.maintain.excel;

import java.awt.Container;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.prefs.Preferences;

import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import nc.bs.framework.common.NCLocator;
import nc.itf.trade.excelimport.IImportableEditor;
import nc.itf.uap.IUAPQueryBS;
import nc.jdbc.framework.processor.MapListProcessor;
import nc.ui.cof.orderplan.maintain.excel.common.InputItemForCof;
import nc.ui.cof.orderplan.maintain.excel.imp.ImportMessage;
import nc.ui.cof.orderplan.maintain.excel.manager.IDataFileManager;
import nc.ui.cof.orderplan.maintain.excel.manager.XLSDataFileManager;
import nc.ui.cof.orderplan.maintain.excel.manager.XsmlDataFileManager;
import nc.ui.ml.NCLangRes;
import nc.ui.pub.beans.MessageDialog;
import nc.ui.pub.beans.UIDialog;
import nc.ui.pub.beans.UIFileChooser;
import nc.ui.trade.excelimport.ExcelImportInfo;
import nc.ui.trade.excelimport.ImportTypeDismatchException;
import nc.ui.trade.excelimport.InputItem;
import nc.ui.trade.excelimport.parser.IFileParserConstants;
import nc.vo.cof.orderplan.entity.AggOrderplanVO;
import nc.vo.pub.BusinessException;
import nc.vo.pub.lang.UFDate;
import nc.vo.pubapp.pattern.exception.ExceptionUtils;

/**
 * excel导入导出器，主要完成excel的导入和导出功能的实现
 * 
 * @since 6.0
 * @version 2013-8-9 上午10:13:19
 * @author ruhuic
 */
public class ExcelImporterOP {

	private OrderPlanExprotDialog dlg;

	private File exportFile;

	private static final String KEY_DEFAULT_DIR = "default";

	private static final String XLS_SUFFIX = IFileParserConstants.XLS_SUFFIX;

	// private static final String XLSX_SUFFIX =
	// IFileParserConstants.XLSX_SUFFIX;

	// 为了生成和导入文件同一目录下的导入日志文件而设
	private String importFileFullName = ".";

	private UIFileChooser chooser;

	// 为了自动记住上次用户选择的文件目录(使用偏好)
	private Preferences preferences;

	private String funcode = null;

	/**
	 * 导出excel模板文件
	 * 
	 * @param inputItems
	 *            输入项列表
	 */
	public void exportExcelTemplate(Container parent, List<InputItem> inputItems) throws IOException {

		OrderPlanExprotDialog dlg = new OrderPlanExprotDialog(parent, inputItems, funcode, null, true);
		dlg.initialize();
		if (dlg.showModal() == UIDialog.ID_OK) {
			File file = getSaveExcelFile(parent);
			if (file == null)
				return;
			IDataFileManager dataFileManager = getDateFileManager(file);

			List<InputItemForCof> fieldItems = InputItemCreater.convertItems(dlg.getInputItems());
			dataFileManager.writeTemplet(file, fieldItems, dlg.getPlantemplateid(), dlg.getStartTime());
		}
	}

	public boolean beforeExport(IImportableEditor editor) {
		boolean ret = false;
		JComponent parent = editor.getJComponent();
		List<InputItem> items = editor.getInputItems();

		this.dlg = new OrderPlanExprotDialog(parent, items, funcode, null, false, false);

		dlg.initialize();
		if (dlg.showModal() == UIDialog.ID_OK) {
			List<InputItem> itemsd = dlg.getInputItems();
			if (itemsd == null || itemsd.size() == 0) {
				return false;
			}
			ret = getSaveExcelFile(parent) != null;
		}

		return ret;
	}

	public void exportToExcel(final IImportableEditor editor) throws Exception {
		boolean export = this.beforeExport(editor);
		if (!export)
			return;
		final List<InputItem> items = dlg.getInputItems();
		if (items == null || items.size() == 0)
			return;
		exportToFile(editor, items, exportFile);
	}

	/**
	 * @throws Exception
	 ****/
	private void exportToFile(IImportableEditor editor, List<InputItem> items, File file) throws Exception {
		AggOrderplanVO aggvo = ((OrderplanImportableEditor) editor).getAggOrderplanVO();
		String pk_group = aggvo.getParentVO().getPk_group();
		String pk_org = aggvo.getParentVO().getPk_org();
		Map<String, String> map = getPlanTemplateMap(pk_group, pk_org);
		File dataFile = getFileofIndex(file, 0);
		Object res = MessageDialog.showSelectDlg(null, "导出模板选择", "请选择导出模板", map.keySet().toArray(), 50);
		if(res == null){
			ExceptionUtils.wrappBusinessException("系统已经终止要货计划导出！");
		}
		IDataFileManager dataFileManager = getDateFileManager(dataFile);
		UFDate starttime = ((OrderplanImportableEditor) editor).getstarttime();
		String plantemplateid = ((OrderplanImportableEditor) editor).getplantemplateid();
		plantemplateid = map.get(res);
		List<InputItemForCof> publicitem = InputItemCreater.convertItems(items);
		dataFileManager.writeData(file, publicitem, plantemplateid, starttime, aggvo);

	}
	
	private Map<String, String> getPlanTemplateMap(String pk_group, String pk_org){
		StringBuffer sql = new StringBuffer();
		sql.append("select cplantemplateid,vname from ecapppub_plantemplate where nvl(dr,0) = 0 and enablestate  = 2 ");
		sql.append(" and pk_group = '"+pk_group+"' and pk_org in ('"+pk_org+"','"+pk_group+"')");
		List<Map<String, String>> results = new ArrayList<Map<String, String>>();
		try {
			results = (List<Map<String, String>>) NCLocator.getInstance().lookup(IUAPQueryBS.class).executeQuery(sql.toString(), new MapListProcessor());
		} catch (BusinessException e) {
			ExceptionUtils.wrappBusinessException(e.getMessage());
		}
		Map<String, String> res = new HashMap<String, String>();
		for(Map<String, String> result : results){
			res.put(result.get("vname"), result.get("cplantemplateid"));
		}
		return res;
	}

	private IDataFileManager getDateFileManager(File file) {

		if (file == null)
			return null;
		String filename = file.getName().toLowerCase();
		if (filename.endsWith(IFileParserConstants.XLS_SUFFIX)) {
			return new XsmlDataFileManager();
		} else if (filename.endsWith(IFileParserConstants.XLSX_SUFFIX)) {
			return new XLSDataFileManager();
		}
		return null;
	}

	/******/

	private File getFileofIndex(File firstFile, int index) throws IOException {
		if (index == 0)
			return firstFile;
		String path = firstFile.getPath();
		int dotIndex = path.indexOf('.');
		String fileName = path.substring(0, dotIndex);
		String fullName = fileName + "_" + index + path.substring(dotIndex);
		File file = new File(fullName);
		if (!file.exists())
			file.createNewFile();
		return file;
	}

	// 如果用户输入文件的后缀名为用户选择的FileFilter的后缀，就不用加后缀名，否则就要加一个后缀
	private File getSelectedExcelFile(File selectedFile, String filterfilesuffix) {
		File selectedFiles = null;
		if (selectedFile.getPath().endsWith(filterfilesuffix)) {
			selectedFiles = new File(selectedFile.getPath());
		} else {
			selectedFiles = new File(selectedFile.getPath() + filterfilesuffix);
		}
		return selectedFiles;
	}

	protected File getSaveExcelFile(Container parent) {
		while (getFileChooser().showSaveDialog(parent) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = getFileChooser().getSelectedFile();
			if (selectedFile != null) {
				// 根据FileFilter来过滤文件

				if (getFileChooser().getFileFilter().getDescription()
						.endsWith(NCLangRes.getInstance().getStrByID("excelimport", "ExcelImporter-000012")/* "Excel97-2003工作薄(*.xls)" */)) {
					selectedFile = getSelectedExcelFile(selectedFile, XLS_SUFFIX);
				}

				if (selectedFile.exists()) {
					int result = MessageDialog
							.showYesNoDlg(parent, NCLangRes.getInstance().getStrByID("excelimport", "ExcelImporter-000006")/* 提示 */, NCLangRes
									.getInstance().getStrByID("excelimport", "ExcelImporter-000007", null, new String[] { selectedFile.getName() })/*
																																					 * 文件
																																					 * {
																																					 * 0
																																					 * }
																																					 * 已存在是否覆盖？
																																					 */);
					if (result != MessageDialog.ID_YES) {
						continue;
					}
				}
				getPreferences().put(KEY_DEFAULT_DIR, selectedFile.getParent());
				this.exportFile = selectedFile;
				return selectedFile;
			}
		}
		return null;
	}

	private File getOpenExcelFile(Container parent) {
		while (getFileChooser().showOpenDialog(parent) == JFileChooser.APPROVE_OPTION) {
			File selectedFile = getFileChooser().getSelectedFile();
			if (selectedFile != null) {
				if (selectedFile.exists()) {
					if (!isSuffixRight(selectedFile)) {
						MessageDialog.showErrorDlg(parent, nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "0ec72001-0019")/*
																																			 * @
																																			 * res
																																			 * "错误提示"
																																			 */,
								nc.vo.ml.NCLangRes4VoTransl.getNCLangRes().getStrByID("ec72001_0", "0ec72001-0020")/*
																													 * @
																													 * res
																													 * "目前只支持导入xls格式的数据文件"
																													 */);
						continue;
					}
					getPreferences().put(KEY_DEFAULT_DIR, selectedFile.getParent());

					String path = selectedFile.getPath().toLowerCase();
					String suffix = null;

					suffix = XLS_SUFFIX;

					importFileFullName = path.substring(0, path.indexOf(suffix));
					return selectedFile;
				}
			}
		}
		return null;
	}

	private static boolean isXLSFile(File file) {
		String name = file.getName().toLowerCase();
		return name.endsWith(XLS_SUFFIX);
	}

	protected static boolean isSuffixXLSRight(File file) {
		return isXLSFile(file);
	}

	private static boolean isSuffixRight(File file) {
		return isXLSFile(file);
	}

	public OrderPlanExprotDialog getDlg() {
		return dlg;
	}

	public void setBillItemImportSetDlg(OrderPlanExprotDialog dlg) {
		this.dlg = dlg;
	}

	private UIFileChooser getFileChooser() {
		if (chooser == null) {
			chooser = new UIFileChooser();
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
			chooser.setAcceptAllFileFilterUsed(false);

			chooser.addChoosableFileFilter(new FileFilter() {

				public boolean accept(File f) {
					return f.isDirectory() || isSuffixXLSRight(f);
				}

				@Override
				public String getDescription() {
					return NCLangRes.getInstance().getStrByID("excelimport", "ExcelImporter-000012")/* "Excel97-2003工作薄(*.xls)" */;
				}
			});

		}
		// 用户选择文件目录偏好
		String preferencesDir = getPreferences().get(KEY_DEFAULT_DIR, System.getProperty("user.dir"));
		chooser.setCurrentDirectory(new File(preferencesDir));
		return chooser;
	}

	protected Preferences getPreferences() {
		if (preferences == null) {
			preferences = Preferences.userNodeForPackage(this.getClass());
		}
		return preferences;
	}

	/**
	 * 返回导入日志文件名(全路径名)
	 * 
	 * 格式为：导入文件名_log_日期时间
	 */
	public String getLogFileName() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_hhmmss");
		String name = sdf.format(new Date()).toString();
		return importFileFullName + "_log_" + name;
	}

	public void setFuncode(String funcode) {

		this.funcode = funcode;
	}

	/**
	 * 导入excel数据
	 * 
	 * @param parent
	 * @return
	 */
	public ImportMessage importFromExcel(JComponent parent) throws FileNotFoundException, IOException, ImportTypeDismatchException, BusinessException {

		File selectedFile = getOpenExcelFile(parent);
		if (selectedFile == null) {
			return null;
		}
		ExcelImportInfo info = new ExcelImportInfo();
		info.setFile(selectedFile);
		ImportMessage vos = doImport(selectedFile);
		return vos;
	}

	/**
	 * 对指定文件进行读取和解析，返回解析后的EAVO列表
	 */
	public ImportMessage doImport(File file) throws FileNotFoundException, IOException, ImportTypeDismatchException, BusinessException {
		ImportMessage result;
		IDataFileManager dataFileManager = getDateFileManager(file);
		result = dataFileManager.readImportData(file);

		return result;

	}
}