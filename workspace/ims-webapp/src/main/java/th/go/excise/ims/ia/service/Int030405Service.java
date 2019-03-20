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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskSystemUnworking;
import th.go.excise.ims.ia.persistence.repository.IaRiskSystemUnworkingRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int030405JdbcRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030404FormVo;
import th.go.excise.ims.ia.vo.Int030404Vo;
import th.go.excise.ims.ia.vo.Int030405FormVo;
import th.go.excise.ims.ia.vo.Int030405Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030405Service {
	private Logger logger = LoggerFactory.getLogger(Int030405Service.class);

	@Autowired
	private IaRiskSystemUnworkingRepository iaRiskSystemUnworkingRepository;

	@Autowired
	private Int030405JdbcRepository int030405JdbcRepository;

	@Autowired
	private IntCalculateCriteriaUtil intCalculateCriteriaUtil;

	public List<Int030405Vo> systemUnworkingList(Int030405FormVo form) {
		List<Int030405Vo> resDataCal = new ArrayList<Int030405Vo>();
		List<IaRiskSystemUnworking> systemUnworkingList = new ArrayList<IaRiskSystemUnworking>();
		systemUnworkingList = iaRiskSystemUnworkingRepository.findByBudgetYear(form.getBudgetYear());
		List<IaRiskSystemUnworking> res = new ArrayList<IaRiskSystemUnworking>();

		for (IaRiskSystemUnworking iaRiskSystemUnworking : systemUnworkingList) {
			IaRiskSystemUnworking dataSet = new IaRiskSystemUnworking();
			dataSet.setBudgetYear(form.getBudgetYear());
			dataSet.setBudgetYear(form.getBudgetYear());
			dataSet.setSystemcode(iaRiskSystemUnworking.getSystemcode());
			dataSet.setSystemname(iaRiskSystemUnworking.getSystemname());
			dataSet.setCountall(iaRiskSystemUnworking.getCountall());
			dataSet.setCounterror(iaRiskSystemUnworking.getCounterror());
			dataSet.setErrordetailError10(iaRiskSystemUnworking.getErrordetailError10());
			dataSet.setErrordetailError11(iaRiskSystemUnworking.getErrordetailError11());
			dataSet.setErrordetailError12(iaRiskSystemUnworking.getErrordetailError12());
			dataSet.setErrordetailError01(iaRiskSystemUnworking.getErrordetailError01());
			dataSet.setErrordetailError02(iaRiskSystemUnworking.getErrordetailError02());
			dataSet.setErrordetailError03(iaRiskSystemUnworking.getErrordetailError03());
			dataSet.setErrordetailError04(iaRiskSystemUnworking.getErrordetailError04());
			dataSet.setErrordetailError05(iaRiskSystemUnworking.getErrordetailError05());
			dataSet.setErrordetailError06(iaRiskSystemUnworking.getErrordetailError06());
			dataSet.setErrordetailError07(iaRiskSystemUnworking.getErrordetailError07());
			dataSet.setErrordetailError08(iaRiskSystemUnworking.getErrordetailError08());
			dataSet.setErrordetailError09(iaRiskSystemUnworking.getErrordetailError09());
			res.add(dataSet);
		}

		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = getForm0304(dataForm);

		int index = 0;
		for (IaRiskSystemUnworking iaRiskSystemUnworking : res) {
			Int030405Vo resDataCalSet = new Int030405Vo();
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
			if (StringUtils.isNoneBlank(iaRiskSystemUnworking.getCountall())) {
				risk = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(iaRiskSystemUnworking.getCountall()),
						getForm0304.getIaRiskFactorsConfig());
			}
			resDataCalSet.setIaRiskSystemUnworking(iaRiskSystemUnworking);
			resDataCalSet.setIntCalculateCriteriaVo(risk);
			resDataCal.add(index, resDataCalSet);
		}
//		
//		intCalculateCriteriaUtil.IntCalculateCriteriaVo()
//		resDataCal
//		IntCalculateCriteriaVo risk = IntCalculateCriteriaUtil.calculateCriteria(rateAmount, config.get());
		return resDataCal;
	}

	public Int0301Vo getForm0304(Int0301FormVo form) {
		Int0301Vo int0301Vo = new Int0301Vo();
		List<Int0301Vo> listServiceRes = new ArrayList<Int0301Vo>();
		listServiceRes = int030405JdbcRepository.getForm0304(form);
		if (listServiceRes.size() != 0) {
			int0301Vo = listServiceRes.get(0);
		}
		return int0301Vo;
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
		String[] tbTH1 = { "ลำดับที่" , "ระบบสารสนเทศฯ ของกรมสรรพสามิต" , "ปี " + budgetYearCon , "" , "" , "ปี " + budgetYear , "", "", "", "", "", "", "", "" , "รวม" , "ประเมินความเสี่ยง" , ""  };
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

		String[] tbTH2 = { "" , "" , "ต.ค.", "พ.ย." , "ธ.ค." , "ม.ค.", "ก.พ.", "มี.ค.", "เม.ย.", "พ.ค.", "มิ.ย.", "ก.ค.", "ส.ค.", "ก.ย." , "" , "อัตราความเสี่ยง" , "แปลค่าความเสี่ยง" };
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
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 5, 13));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 14, 14));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 15, 16));
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		sheet.setColumnWidth(1, width * 280);
		for (int i = 15; i <= 16; i++) {	
				sheet.setColumnWidth(i, width * 76);
		}

		/* start details */
		int count = 1;
		rowNum = 2;
		cellNum = 0;

		List<Int030405Vo> datas = new ArrayList<Int030405Vo>();
		Int030405FormVo form = new Int030405FormVo();
		form.setBudgetYear(budgetYear);
		form.setInspectionWork(inspectionWork);
		form.setIdConfig(idConfig);
		datas = systemUnworkingList(form);

		DecimalFormat df2 = new DecimalFormat(".##");
		for (Int030405Vo data : datas) {
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
			cell.setCellValue(data.getIaRiskSystemUnworking().getSystemname());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError01());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError02());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError03());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError04());
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 7
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError05());
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 8
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError06());
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 9
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError07());
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 10
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError08());
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 11
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError09());
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 12
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError10());
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 13
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError11());
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 12
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskSystemUnworking().getErrordetailError12());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 13
			cell = row.createCell(cellNum);
			if (data.getIaRiskSystemUnworking().getCountall() != null) {
				cell.setCellValue(data.getIaRiskSystemUnworking().getCountall().toString());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 14
			cell = row.createCell(cellNum);
			if(data.getIntCalculateCriteriaVo().getRiskRate() != null) {
				cell.setCellValue(data.getIntCalculateCriteriaVo().getRiskRate().doubleValue());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 15
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
