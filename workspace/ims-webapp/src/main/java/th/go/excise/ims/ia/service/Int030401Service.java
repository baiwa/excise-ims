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
import th.go.excise.ims.common.util.ExciseUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsData;
import th.go.excise.ims.ia.persistence.entity.IaRiskSelectCase;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsDataRepository;
import th.go.excise.ims.ia.util.ExcelUtil;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030401FormVo;
import th.go.excise.ims.ia.vo.Int030401Vo;
import th.go.excise.ims.ia.vo.Int030403FormVo;
import th.go.excise.ims.ia.vo.Int030403Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030401Service {
	
	@Autowired 
	private IaRiskFactorsDataRepository iaRiskFactorsDataRepository;
	
	@Autowired 
	private Int030405Service int030405Service;
	@Autowired 
	private Int030101Service int030101Service;
//	@Autowired
//	private IntCalculateCriteriaUtil intCalculateCriteriaUtil;
	@Autowired
	private ExcelUtil excelUtil;
	
	public List<Int030401Vo> factorsDataList(Int030401FormVo form) {
		List<Int030401Vo>  Int030401VoList = new ArrayList<>();
		List<IaRiskFactorsData> iaRiskFactorsDataList = new ArrayList<IaRiskFactorsData>();
		iaRiskFactorsDataList = iaRiskFactorsDataRepository.findByIdFactorsByInspectionWorkByBudgetYear(form.getIdFactors(), form.getInspectionWork(), form.getBudgetYear());
		
		List<IaRiskFactorsData> iaRiskFactorsDataListRes = new ArrayList<IaRiskFactorsData>();
		for (IaRiskFactorsData iaRiskFactorsData : iaRiskFactorsDataList) {
			IaRiskFactorsData datanew = new IaRiskFactorsData();
			datanew.setIdFactors(iaRiskFactorsData.getIdFactors());
			datanew.setProjectCode(iaRiskFactorsData.getProjectCode());
			datanew.setProject(iaRiskFactorsData.getProject());
			datanew.setExciseCode(iaRiskFactorsData.getExciseCode());
			datanew.setSector(iaRiskFactorsData.getSector());
			datanew.setArea(iaRiskFactorsData.getArea());
			datanew.setRiskCost(iaRiskFactorsData.getRiskCost());
			datanew.setCreatedDate(iaRiskFactorsData.getCreatedDate());
			iaRiskFactorsDataListRes.add(datanew);
		}
		
		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = int030405Service.getForm0304(dataForm);
		
		int index=0;
		for (IaRiskFactorsData iaRiskFactorsData : iaRiskFactorsDataListRes) {
			
			Int030401Vo resDataCalSet = new Int030401Vo();
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
			if(StringUtils.isNotBlank(iaRiskFactorsData.getRiskCost())) {
				risk = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(iaRiskFactorsData.getRiskCost()) , getForm0304.getIaRiskFactorsConfig());
			}
			resDataCalSet.setIaRiskFactorsData(iaRiskFactorsData);
			resDataCalSet.setIntCalculateCriteriaVo(risk);
			Int030401VoList.add(index, resDataCalSet);
			index++;
		}
		
		return Int030401VoList;
	}
	
	public ByteArrayOutputStream exportInt030401(BigDecimal idFactors, BigDecimal idConfig ,String budgetYear , BigDecimal inspectionWork) throws IOException {

		
		
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
		String[] tbTH1 = { "ลำดับ", "รหัสสรรพสามิต", "ภาค", "พื้นที่", "ค่าความเสี่ยง", "ประเมินความเสี่ยง", "" };
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

		String[] tbTH2 = { "", "", "", "", "", "อัตราความเสี่ยง", "แปลค่าความเสี่ยง" };
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
		for (int i = 1; i <= 7; i++) {
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
		
		Int030401FormVo form = new Int030401FormVo();
		form.setBudgetYear(budgetYear);
		form.setIdFactors(idFactors);
		form.setIdConfig(idConfig);
		form.setInspectionWork(inspectionWork);
		
		List<Int030401Vo> iaRiskFactorsMasterList = factorsDataList(form);
		
		
		DecimalFormat df2 = new DecimalFormat(".##");
		for (Int030401Vo data : iaRiskFactorsMasterList) {
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
			cell.setCellValue(data.getIaRiskFactorsData().getExciseCode());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskFactorsData().getSector());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskFactorsData().getArea());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskFactorsData().getRiskCost());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIntCalculateCriteriaVo().getRiskRate().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 7
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
