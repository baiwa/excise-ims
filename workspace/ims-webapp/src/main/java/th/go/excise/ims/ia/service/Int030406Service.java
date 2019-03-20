package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

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
import th.go.excise.ims.ia.persistence.entity.IaRiskCheckPeriod;
import th.go.excise.ims.ia.persistence.repository.IaRiskCheckPeriodRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030405FormVo;
import th.go.excise.ims.ia.vo.Int030405Vo;
import th.go.excise.ims.ia.vo.Int030406FormVo;
import th.go.excise.ims.ia.vo.Int030406Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030406Service {

	@Autowired
	private Int030405Service int030405Service;
	
	
	@Autowired
	private IaRiskCheckPeriodRepository iaRiskCheckPeriodRepository;
	
	public List<Int030406Vo> checkPeriodList(Int030406FormVo form) {
		List<Int030406Vo> resDataCal = new ArrayList<Int030406Vo>();
		List<IaRiskCheckPeriod> systemUnworkingList = new ArrayList<IaRiskCheckPeriod>();
		systemUnworkingList = iaRiskCheckPeriodRepository.findAllOderLongTimeDesc();
		List<IaRiskCheckPeriod> res = new ArrayList<IaRiskCheckPeriod>();

		for (IaRiskCheckPeriod systemList : systemUnworkingList) {
			IaRiskCheckPeriod dataSet = new IaRiskCheckPeriod();
			dataSet.setDateStart(systemList.getDateStart());
			dataSet.setDateEnd(systemList.getDateEnd());
			dataSet.setSectorName(systemList.getSectorName());
			dataSet.setAreaName(systemList.getAreaName());
			dataSet.setLongTime(systemList.getLongTime());
			res.add(dataSet);
		}
		
		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = int030405Service.getForm0304(dataForm);

		
		int index=0;
		for (IaRiskCheckPeriod list : res) {
			Int030406Vo resDataCalSet = new Int030406Vo();
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
//			if(StringUtils.isNotBlank(list.getKpiExpenseActualAmount())) {
				risk = IntCalculateCriteriaUtil.calculateCriteria(list.getLongTime() , getForm0304.getIaRiskFactorsConfig());
//			}
			resDataCalSet.setIaRiskCheckPeriod(list);
			
			resDataCalSet.setDateFrom(ConvertDateUtils.formatDateToString(list.getDateStart(), ConvertDateUtils.DD_MM_YYYY));
			resDataCalSet.setDateTo(ConvertDateUtils.formatDateToString(list.getDateEnd(), ConvertDateUtils.DD_MM_YYYY));
			resDataCalSet.setIntCalculateCriteriaVo(risk);
			resDataCal.add(index, resDataCalSet);
			
		}

		return resDataCal;
	}
	
	
	public ByteArrayOutputStream exportInt030405(String budgetYear, BigDecimal inspectionWork, BigDecimal idConfig)
			throws IOException {
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
		long budgetYearCon = Long.parseLong(budgetYear);
		budgetYearCon = budgetYearCon - 1;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับที่" , "หน่วยงาน" , "วัมที่เริ่มตรวจ" , "วันที่ปิดตรวจ" , "ระยะเวลาการเข้าตรวจสอบ ( ปี )" , "ประเมินความเสี่ยง" , "" };
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

		String[] tbTH2 = { "" , "" , "" , "" , "" , "อัตราความเสี่ยง" , "แปลค่าความเสี่ยง" };
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
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 2, 2));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 3, 3));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 4, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 5, 6));
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		sheet.setColumnWidth(1, width * 280);
		for (int i = 2; i <= 6; i++) {	
			if(i >= 2 && i <= 3) {
				sheet.setColumnWidth(i, width * 60);
			}
			if(i == 4 ) {
				sheet.setColumnWidth(i, width * 150);
			}else {
				sheet.setColumnWidth(i, width * 76);
			}				
		}

		/* start details */
		int count = 1;
		rowNum = 2;
		cellNum = 0;

		List<Int030406Vo> datas = new ArrayList<Int030406Vo>();
		Int030406FormVo form = new Int030406FormVo();
		form.setBudgetYear(budgetYear);
		form.setInspectionWork(inspectionWork);
		form.setIdConfig(idConfig);
		datas = checkPeriodList(form);

		DecimalFormat df2 = new DecimalFormat(".##");
		for (Int030406Vo data : datas) {
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
			cell.setCellValue(data.getIaRiskCheckPeriod().getAreaName());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(ConvertDateUtils.formatDateToString(data.getIaRiskCheckPeriod().getDateStart(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(ConvertDateUtils.formatDateToString(data.getIaRiskCheckPeriod().getDateEnd(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskCheckPeriod().getLongTime().toString());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 6
			cell = row.createCell(cellNum);
			if(data.getIntCalculateCriteriaVo().getRiskRate() != null) {
				cell.setCellValue(data.getIntCalculateCriteriaVo().getRiskRate().doubleValue());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 7
			cell = row.createCell(cellNum);
			if(data.getIntCalculateCriteriaVo().getTranslatingRisk() != null) {
				cell.setCellValue(data.getIntCalculateCriteriaVo().getTranslatingRisk());
			}else {
				cell.setCellValue("-");
			}
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
