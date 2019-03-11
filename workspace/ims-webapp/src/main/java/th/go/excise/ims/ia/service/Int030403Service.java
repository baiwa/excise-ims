package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030403JdbcRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030403FormVo;
import th.go.excise.ims.ia.vo.Int030403Vo;
import th.go.excise.ims.ia.vo.Int030407Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030403Service {

	@Autowired
	Int030403JdbcRepository int030403JdbcRepository;

	@Autowired
	Int030405Service int030405Service;

	public List<Int030403Vo> list(Int030403FormVo form) {
		List<Int030403Vo> iaRiskBudgetProject = new ArrayList<Int030403Vo>();
		iaRiskBudgetProject = int030403JdbcRepository.list(form);
		int index = 0;
		for (Int030403Vo int030403Vo : iaRiskBudgetProject) {
			BigDecimal expenseBudgetAmountAll = new BigDecimal(0);
			Float ex1 = StringToFloat(int030403Vo.getIaRiskBudgetProject().getExpensebudgetamounta());

			Float ex2 = StringToFloat(int030403Vo.getIaRiskBudgetProject().getExpensebudgetamountm());

			Float ex3 = StringToFloat(int030403Vo.getIaRiskBudgetProject().getExpensebudgetamountx());

			expenseBudgetAmountAll = new BigDecimal(ex1 + ex2 + ex3);

			iaRiskBudgetProject.get(index).setExpenseBudgetAmountAll(expenseBudgetAmountAll);
			Int0301FormVo form0301 = new Int0301FormVo();
			form0301.setBudgetYear(form.getBudgetYear());
			form0301.setInspectionWork(form.getInspectionWork());
			form0301.setIdConfig(form.getIdConfig());
			Int0301Vo getForm0304 = int030405Service.getForm0304(form0301);
			IntCalculateCriteriaVo intCalculateCriteriaVo = new IntCalculateCriteriaVo();

			if (StringUtils.isNotBlank(int030403Vo.getIaRiskBudgetProject().getApprovedbudgetamount())) {
				BigDecimal Approvedbudgetamount = new BigDecimal(
						int030403Vo.getIaRiskBudgetProject().getApprovedbudgetamount().replaceAll(",", ""));
				intCalculateCriteriaVo = IntCalculateCriteriaUtil.calculateCriteria(Approvedbudgetamount,
						getForm0304.getIaRiskFactorsConfig());
			}
			iaRiskBudgetProject.get(index).setIntCalculateCriteriaVo(intCalculateCriteriaVo);
			index++;
		}
		return iaRiskBudgetProject;
	}

	public Float StringToFloat(String amount) {
		Float f = 0f;
		if (amount != "" && amount != null) {

			f = Float.valueOf(amount.replaceAll(",", ""));
		}
		return f;
	}

	public ByteArrayOutputStream exportInt030403(String projectYear, String projecttypecode) throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle tdStyle = ExcelUtils.createTdCellStyle(workbook);
		CellStyle tdLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle tdRight = ExcelUtils.createRightCellStyle(workbook);
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;

		// Row [0]
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "โครงการตามยุทธศาสตร์", "งบประมาณที่ใช้ตามแผนยุทธศาสตร์", "", "", "",
				"เงินงบประมาณที่กรมอนุมัติ", "ประเมินความเสี่ยง", "" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		// Row [1]
		row = sheet.createRow(rowNum);
		cellNum = 0;
		cell = row.createCell(cellNum);

		String[] tbTH2 = { "", "", "เงินงบประมาณ", "เงินท้องถิ่น", "เงินอื่น ๆ", "รวม", "", "อัตราความเสี่ยง",
				"แปลค่าความเสี่ยง" };
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 0, 0));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 1, 1));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 6, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 2, 5));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 7, 8));
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 9; i++) {
			if (i >= 1 && i <= 2) {
				sheet.setColumnWidth(i, width * 180);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}

		/* start details */
		int count = 1;
		rowNum = 2;
		cellNum = 0;	
		
		List<Int030403Vo> datas = new ArrayList<Int030403Vo>();
		Int030403FormVo form = new Int030403FormVo();
		form.setProjectyear(projectYear);
		form.setProjecttypecode(projecttypecode);
//		datas = list(form);
		DecimalFormat df2 = new DecimalFormat(".##");
		for (Int030403Vo data : datas) {
			// Re Initial
			cellNum = 0;
			row = sheet.createRow(rowNum);
			// Column 1
			CellStyle styleCustom = tdStyle;
			styleCustom.setAlignment(HorizontalAlignment.CENTER);
			cell = row.createCell(cellNum);
			cell.setCellValue(count++);
			cell.setCellStyle(styleCustom);
			cellNum++;
			// Column 2
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskBudgetProject().getProjectname());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskBudgetProject().getExpensebudgetamountm());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskBudgetProject().getExpensebudgetamountx());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskBudgetProject().getExpensebudgetamounta());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getExpenseBudgetAmountAll().doubleValue()));
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 7
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskBudgetProject().getApprovedbudgetamount());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 8
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIntCalculateCriteriaVo().getRiskRate().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 9
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIntCalculateCriteriaVo().getTranslatingRisk());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Next Row
			rowNum++;
		}
		/* end details */

		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}
}
