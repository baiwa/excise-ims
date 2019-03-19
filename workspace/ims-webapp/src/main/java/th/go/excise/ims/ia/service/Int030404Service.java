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
import org.springframework.transaction.annotation.Transactional;

import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskProEf;
import th.go.excise.ims.ia.persistence.entity.IaRiskProEfKpi;
import th.go.excise.ims.ia.persistence.entity.IaRiskProEfTask;
import th.go.excise.ims.ia.persistence.repository.IaRiskProEfKpiRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskProEfRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskProEfTaskRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.IaRiskProEfKpiVo;
import th.go.excise.ims.ia.vo.IaRiskProEfTaskVo;
import th.go.excise.ims.ia.vo.IaRiskProEfVo;
import th.go.excise.ims.ia.vo.Int0301FormVo;
import th.go.excise.ims.ia.vo.Int0301Vo;
import th.go.excise.ims.ia.vo.Int030403FormVo;
import th.go.excise.ims.ia.vo.Int030403Vo;
import th.go.excise.ims.ia.vo.Int030404FormVo;
import th.go.excise.ims.ia.vo.Int030404Vo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;

@Service
public class Int030404Service {
	@Autowired
	private IaRiskProEfRepository iaRiskProEfRepository;

	@Autowired
	private IaRiskProEfTaskRepository iaRiskProEfTaskRepository;

	@Autowired
	private IaRiskProEfKpiRepository iaRiskProEfKpiRepository;

	@Autowired
	private Int030405Service int030405Service;

	@Autowired
	private IntCalculateCriteriaUtil intCalculateCriteriaUtil;

	private Logger logger = LoggerFactory.getLogger(Int030404Service.class);

	@Transactional
	public List<Int030404Vo> projectEfficiencyList(Int030404FormVo form) {

		Int0301FormVo dataForm = new Int0301FormVo();
		dataForm.setBudgetYear(form.getBudgetYear());
		dataForm.setIdConfig(form.getIdConfig());
		dataForm.setInspectionWork(form.getInspectionWork());
		Int0301Vo getForm0304 = int030405Service.getForm0304(dataForm);

		List<Int030404Vo> pedata = new ArrayList<Int030404Vo>();
		Int030404Vo vo = null;

		List<IaRiskProEf> peList = iaRiskProEfRepository.findByProjectYear(form.getBudgetYear());
		IaRiskProEfVo peSet = null;

		for (IaRiskProEf peData : peList) {
			vo = new Int030404Vo();
			int kpiCount = 0;
			double ruleOfThreeSum = 0d;
			peSet = new IaRiskProEfVo();

			List<IaRiskProEfTask> tastList = iaRiskProEfTaskRepository.findByPeId(peData.getId());

			List<IaRiskProEfTaskVo> tastSetList = new ArrayList<IaRiskProEfTaskVo>();
			IaRiskProEfTaskVo tastSet = null;

			if (tastList.size() == 0) {
//				List<IaRiskProEfKpiVo> kpiSetList = new ArrayList<IaRiskProEfKpiVo>();
//				tastSet = new IaRiskProEfTaskVo();
//				tastSet.setTaskId(null);
//				tastSet.setTaskDescriptionText(null);
//				tastSet.setIaRiskProEfKpiVo(kpiSetList);
//				tastSetList.add(tastSet);

				peSet.setProjectId(peData.getProjectId());
				peSet.setProjectYear(peData.getProjectYear());
				peSet.setProjectName(peData.getProjectName());
				peSet.setProjectTypeName(peData.getProjectTypeName());
				peSet.setOwnerOfficeId(peData.getOwnerOfficeId());
				peSet.setOwnerOfficeName(peData.getOwnerOfficeName());
//				peSet.setCountKpi(null );
//				peSet.setAverageData(null);
//				peSet.setIaRiskProEfTaskVo(tastSetList);
				IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();
				vo.setIntCalculateCriteriaVo(risk);
				vo.setIaRiskProEfVo(peSet);
				pedata.add(vo);
				continue;
			}

			for (IaRiskProEfTask tastData : tastList) {
				tastSet = new IaRiskProEfTaskVo();
				List<IaRiskProEfKpi> kpiList = iaRiskProEfKpiRepository.findByTaskId(tastData.getId());

				List<IaRiskProEfKpiVo> kpiSetList = new ArrayList<IaRiskProEfKpiVo>();
				IaRiskProEfKpiVo kpiSet = null;

				if (kpiList.size() == 0) {
					continue;
				}

				for (IaRiskProEfKpi kpiData : kpiList) {
					kpiSet = new IaRiskProEfKpiVo();
					kpiSet.setKpiId(kpiData.getKpiId());
					kpiSet.setKpiCode(kpiData.getKpiCode());
					kpiSet.setKpiName(kpiData.getKpiName());
					kpiSet.setKpiActivityCode(kpiData.getKpiActivityCode());
					kpiSet.setKpiActivityName(kpiData.getKpiActivityName());
					kpiSet.setKpiTargetAmount(kpiData.getKpiTargetAmount());
					kpiSet.setKpiExpenseActualAmount(kpiData.getKpiExpenseActualAmount());
					kpiSetList.add(kpiSet);
					ruleOfThreeSum += calRuleOfThree(kpiData.getKpiExpenseActualAmount().doubleValue(),
							kpiData.getKpiTargetAmount().doubleValue());
					kpiCount++;
				}
				tastSet.setTaskId(tastData.getTaskId());
				tastSet.setTaskDescriptionText(tastData.getTaskDescriptionText());
				tastSet.setIaRiskProEfKpiVo(kpiSetList);
				tastSetList.add(tastSet);
			}
			peSet.setProjectId(peData.getProjectId());
			peSet.setProjectYear(peData.getProjectYear());
			peSet.setProjectName(peData.getProjectName());
			peSet.setProjectTypeName(peData.getProjectTypeName());
			peSet.setOwnerOfficeId(peData.getOwnerOfficeId());
			peSet.setOwnerOfficeName(peData.getOwnerOfficeName());

			peSet.setCountKpi(new BigDecimal(kpiCount));
			double average = ruleOfThreeSum / kpiCount;
			logger.info(new BigDecimal(average).toString());
			peSet.setAverageData(new BigDecimal(average));
			peSet.setIaRiskProEfTaskVo(tastSetList);
			IntCalculateCriteriaVo risk = new IntCalculateCriteriaVo();

			if (average != 0) {
				risk = IntCalculateCriteriaUtil.calculateCriteria(new BigDecimal(average),
						getForm0304.getIaRiskFactorsConfig());
			}

			vo.setIntCalculateCriteriaVo(risk);
			vo.setIaRiskProEfVo(peSet);
			pedata.add(vo);

		}
		return pedata;
	}

	private double calRuleOfThree(double kpiExpenseActualAmount, double kpiTargetAmount) {
		double ruleOfThree = 0d;

		if (kpiExpenseActualAmount > kpiTargetAmount) {
			ruleOfThree = (((kpiExpenseActualAmount / kpiTargetAmount) * 100) - 100) * -1;
		} else {
			ruleOfThree = (kpiExpenseActualAmount / kpiTargetAmount) * 100;
		}

		return ruleOfThree;
	}

	public ByteArrayOutputStream exportInt030404(String budgetYear, BigDecimal inspectionWork, BigDecimal idConfig)
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
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "ปีแผนงาน", "ชื่อแผนงานโครงการ", "ประเภทแผนงานโครงการ", "รหัสหน่วยงานที่รับผิดชอบ",
				"ชื่อหน่วยงานที่รับผิดชอบ", "ค่าประสิทธิภาพ", "ประเมินความเสี่ยง", "" };
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

		String[] tbTH2 = { "", "", "", "", "", "", "", "อัตราความเสี่ยง", "แปลค่าความเสี่ยง" };
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
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 5, 5));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 1, 6, 6));
		sheet.addMergedRegion(new CellRangeAddress(rowNum - 2, rowNum - 2, 7, 8));
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 9; i++) {
			if (i >= 2 && i <= 3) {
				sheet.setColumnWidth(i, width * 180);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}

		/* start details */
		int count = 1;
		rowNum = 2;
		cellNum = 0;

		List<Int030404Vo> datas = new ArrayList<Int030404Vo>();
		Int030404FormVo form = new Int030404FormVo();
		form.setBudgetYear(budgetYear);
		form.setInspectionWork(inspectionWork);
		form.setIdConfig(idConfig);
		datas = projectEfficiencyList(form);

		DecimalFormat df2 = new DecimalFormat(".##");
		for (Int030404Vo data : datas) {
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
			cell.setCellValue(data.getIaRiskProEfVo().getProjectYear());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskProEfVo().getProjectName());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskProEfVo().getProjectTypeName());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 5
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskProEfVo().getOwnerOfficeId());
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getIaRiskProEfVo().getOwnerOfficeName());
			cell.setCellStyle(tdLeft);
			cellNum++;

			// Column 7
			cell = row.createCell(cellNum);
			if (data.getIaRiskProEfVo().getAverageData() != null) {
				cell.setCellValue(data.getIaRiskProEfVo().getAverageData().toString());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column 8
			cell = row.createCell(cellNum);
			if(data.getIntCalculateCriteriaVo().getRiskRate() != null) {
				cell.setCellValue(data.getIntCalculateCriteriaVo().getRiskRate().doubleValue());
			}else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column 9
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
