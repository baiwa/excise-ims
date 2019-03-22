package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskIncomePerform;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskIncomePerformRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int020301DataVo;
import th.go.excise.ims.ia.vo.Int020301InfoVo;
import th.go.excise.ims.ia.vo.Int030407Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;
import th.go.excise.ims.preferences.vo.ExcelHeaderNameVo;

@Service
public class Int030407Service {

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRep;

	@Autowired
	private IaRiskIncomePerformRepository iaRiskIncomePerformRep;

	public List<Int030407Vo> findByBudgetYear(String budgetYear, String idConfigStr) {
		BigDecimal idConfig = new BigDecimal(idConfigStr);
		List<IaRiskIncomePerform> incomes = iaRiskIncomePerformRep.findByBudgetYear(budgetYear);
		Optional<IaRiskFactorsConfig> config = iaRiskFactorsConfigRep.findById(idConfig);
		List<Int030407Vo> lists = new ArrayList<>();
		Int030407Vo list;
		for (IaRiskIncomePerform income : incomes) {
			BigDecimal diffAmount = income.getSumAmount().subtract(income.getForecaseAmount());
			BigDecimal rateAmount = new BigDecimal(
					(diffAmount.doubleValue() * 100) / income.getForecaseAmount().doubleValue());
			list = new Int030407Vo();
			list.setArea(income.getArea());
			list.setBudgetYear(income.getBudgetYear());
			list.setDiffAmount(diffAmount);
			list.setForecaseAmount(income.getForecaseAmount());
			list.setId(income.getId());
			list.setIdFactors(income.getIdFactors());
			list.setOfficeCode(income.getOfficeCode());
			list.setRateAmount(rateAmount);
			list.setSector(income.getSector());
			list.setSumAmount(income.getSumAmount());
			// CALCULATE
			if (config.isPresent()) {
				IntCalculateCriteriaVo risk = IntCalculateCriteriaUtil.calculateCriteria(rateAmount, config.get());
				list.setColorRisk(risk.getColor());
				list.setRateRisk(risk.getRiskRate());
				list.setTextRisk(risk.getTranslatingRisk());
				list.setIntCalculateCriteriaVo(risk);
			}
			lists.add(list);
		}
		return lists;
	}

	public ByteArrayOutputStream exportInt030407(String budgetYear, String idConfigStr) throws IOException {
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
		String[] tbTH1 = { "ลำดับ", "หน่วยงาน", "หน่วยงาน", "ผลการจัดเก็บรายได้", "ประมาณรายได้", "ผลต่าง",
				"อัตราสูงต่ำ", "ประเมินความเสี่ยง", "ประเมินความเสี่ยง" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		// Row [1]
		row = sheet.createRow(rowNum);
		int cellNumtbTH2 = 0;
		String[] tbTH2 = { "สรรพสามิตภาค", "สรรพสามิตพื้นที่" };
		// Empty Cell Row [1]
		for (int i = 0; i < 1; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Text Cell Row [1]
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Empty Cell Row [1]
		for (int i = 0; i < 4; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Text Cell Row [1]
		String[] tbTH3 = { "อัตราความเสี่ยง", "แปลงค่าความเสี่ยง" };
		for (int i = 0; i < tbTH3.length; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cell.setCellValue(tbTH3[i]);
			cellNumtbTH2++;
		}
		rowNum++;

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 1, 2));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 7, 8));
		for (int i = 0; i < 1; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
		}
		for (int i = 3; i < 7; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
		}
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
		List<Int030407Vo> datas = findByBudgetYear(budgetYear, idConfigStr);
		DecimalFormat df2 = new DecimalFormat(".##");
		for (Int030407Vo data : datas) {
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
			cell.setCellValue(data.getSector());
			cell.setCellStyle(tdLeft);
			cellNum++;
			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getArea());
			cell.setCellStyle(tdLeft);
			cellNum++;
			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getSumAmount().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;
			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getForecaseAmount().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;
			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getDiffAmount().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;
			// Column 7
			cell = row.createCell(cellNum);
			cell.setCellValue(df2.format(data.getRateAmount().doubleValue()));
			cell.setCellStyle(tdRight);
			cellNum++;
			// Column 8
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRateRisk().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 9
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTextRisk());
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
