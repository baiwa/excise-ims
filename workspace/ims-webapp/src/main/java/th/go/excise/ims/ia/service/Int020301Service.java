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
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskQtnConfig;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskQtnConfigRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int020301JdbcRepository;
import th.go.excise.ims.ia.util.IntCalculateCriteriaUtil;
import th.go.excise.ims.ia.vo.Int020301DataVo;
import th.go.excise.ims.ia.vo.Int020301HeaderVo;
import th.go.excise.ims.ia.vo.Int020301InfoVo;
import th.go.excise.ims.ia.vo.IntCalculateCriteriaVo;
import th.go.excise.ims.preferences.vo.ExcelHeaderNameVo;

@Service
public class Int020301Service {

	@Autowired
	private Int020301JdbcRepository int020301JdbcRepository;

	@Autowired
	private IaQuestionnaireSideJdbcRepository iaQuestionnaireSideJdbcRep;

	@Autowired
	private IaRiskQtnConfigRepository iaRiskQtnConfigRepository;
	
	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRep;

	public List<Int020301HeaderVo> findHeaderByIdSide(String idSideStr, String budgetYear) {
		BigDecimal idSide = new BigDecimal(idSideStr);
		return int020301JdbcRepository.findHeaderByIdSide(idSide, budgetYear);
	}

	private String conditionConfigs(BigDecimal value, BigDecimal size, IaRiskQtnConfig configs) {
		double highStart = configs.getHighStart().doubleValue();
		double mediumStart = configs.getMediumStart().doubleValue();
		double mediumEnd = configs.getMediumEnd().doubleValue();
		double lowStart = configs.getHighStart().doubleValue();
		double compareValue = (value.doubleValue() / size.doubleValue()) * 100;
		if (compareValue > highStart) {
			return configs.getHighCondition();
		} else if (compareValue >= mediumStart && compareValue <= mediumEnd) {
			return configs.getMediumCondition();
		} else if (compareValue < lowStart) {
			return configs.getLowCondition();
		} else {
			return configs.getLowCondition();
		}
	}

	public List<Int020301InfoVo> findInfoByIdHdr(String idHdrStr, String budgetYear) {
		BigDecimal idHdr = new BigDecimal(idHdrStr);
		IaRiskQtnConfig configs = iaRiskQtnConfigRepository.findByIdQtnHdrAndIsDeleted(idHdr, "N");
		List<Int020301InfoVo> datas = new ArrayList<>();
		datas = int020301JdbcRepository.findInfoByIdSide(idHdr, budgetYear);
		for (Int020301InfoVo data : datas) {
			// Sides Data
			List<Int020301DataVo> sideDtls = int020301JdbcRepository.findDataByIdHdr(idHdr, budgetYear,
					data.getAreaName());
			String condition = "";
			double all = 0;
			double declineValue = 0;
			for (Int020301DataVo sideDtl : sideDtls) {
				// Calculate RiskName
				double sum = 0;
				if (sideDtl.getDeclineValue() == null) {
					sideDtl.setDeclineValue(new BigDecimal(0));
				}
				if (sideDtl.getAcceptValue() == null) {
					sideDtl.setAcceptValue(new BigDecimal(0));
				}
				if (sideDtl.getDeclineValue() != null) {
					sum = sideDtl.getDeclineValue().doubleValue();
					if (sideDtl.getAcceptValue() != null) {
						sum = sum + sideDtl.getAcceptValue().doubleValue();
					}
					all = all + sum;
					declineValue = declineValue + sideDtl.getDeclineValue().doubleValue();
					condition = conditionConfigs(sideDtl.getDeclineValue(), new BigDecimal(sum), configs);
				} else {
					if (sideDtl.getAcceptValue() != null) {
						condition = conditionConfigs(new BigDecimal(0), sideDtl.getAcceptValue(), configs);
					} else {
						condition = conditionConfigs(new BigDecimal(0), new BigDecimal(0), configs);
					}
				}
				if (">".equals(condition)) { // High
					sideDtl.setRiskName(configs.getHigh());
					sideDtl.setRiskColor(configs.getHighColor());
				} else if ("<>".equals(condition)) { // Medium
					sideDtl.setRiskName(configs.getMedium());
					sideDtl.setRiskColor(configs.getMediumColor());
				} else if ("<".equals(condition)) { // Low
					sideDtl.setRiskName(configs.getLow());
					sideDtl.setRiskColor(configs.getLowColor());
				}
			}
			data.setSideDtls(sideDtls);
			// RiskQuantity
			data.setRiskQuantity(new BigDecimal(sideDtls.size()));
			// Sum Data
			condition = conditionConfigs(new BigDecimal(declineValue), new BigDecimal(all), configs);
			double avg = (declineValue / all) * 100;
			// calculate Risk
			if (">".equals(condition)) { // High
				data.setRiskText(configs.getHigh());
				data.setRiskColor(configs.getHighColor());
			} else if ("<>".equals(condition)) { // Medium
				data.setRiskText(configs.getMedium());
				data.setRiskColor(configs.getMediumColor());
			} else if ("<".equals(condition)) { // Low
				data.setRiskText(configs.getLow());
				data.setRiskColor(configs.getLowColor());
			}
			data.setAvgRisk(new BigDecimal(Math.round(avg)));
			// Finding Sector and Area Name
			List<ExciseDept> exciseDepts = ApplicationCache.getExciseSectorList();
			data.setStatusText(ApplicationCache.getParamInfoByCode("IA_STATUS", data.getStatusText()).getValue1());
			for (ExciseDept exciseDept : exciseDepts) {
				if (exciseDept.getOfficeCode().substring(0, 2).equals(data.getSectorName().substring(0, 2))) {
					data.setSectorName(exciseDept.getDeptName());
				}
			}
			ExciseDept area = ApplicationCache.getExciseDept(data.getAreaName());
			data.setAreaName(area.getDeptName());
		}
		return datas;
	}
	
	public List<Int020301InfoVo> findInfoByIdHdrRisk(String idHdrStr, String budgetYear, String idConfigStr) {
		BigDecimal idHdr = new BigDecimal(idHdrStr);
		BigDecimal idConfig = new BigDecimal(idConfigStr);
		IaRiskQtnConfig configs = iaRiskQtnConfigRepository.findByIdQtnHdrAndIsDeleted(idHdr, "N");
		List<Int020301InfoVo> datas = new ArrayList<>();
		datas = int020301JdbcRepository.findInfoByIdSide(idHdr, budgetYear);
		Optional<IaRiskFactorsConfig> config = iaRiskFactorsConfigRep.findById(idConfig);
		if (config.isPresent()) {
			for (Int020301InfoVo data : datas) {
				// Sides Data
				List<Int020301DataVo> sideDtls = int020301JdbcRepository.findDataByIdHdr(idHdr, budgetYear,
						data.getAreaName());
				String condition = "";
				double all = 0;
				double declineValue = 0;
				for (Int020301DataVo sideDtl : sideDtls) {
					// Calculate RiskName
					double sum = 0;
					if (sideDtl.getDeclineValue() == null) {
						sideDtl.setDeclineValue(new BigDecimal(0));
					}
					if (sideDtl.getAcceptValue() == null) {
						sideDtl.setAcceptValue(new BigDecimal(0));
					}
					if (sideDtl.getDeclineValue() != null) {
						sum = sideDtl.getDeclineValue().doubleValue();
						if (sideDtl.getAcceptValue() != null) {
							sum = sum + sideDtl.getAcceptValue().doubleValue();
						}
						all = all + sum;
						declineValue = declineValue + sideDtl.getDeclineValue().doubleValue();
						condition = conditionConfigs(sideDtl.getDeclineValue(), new BigDecimal(sum), configs);
					} else {
						if (sideDtl.getAcceptValue() != null) {
							condition = conditionConfigs(new BigDecimal(0), sideDtl.getAcceptValue(), configs);
						} else {
							condition = conditionConfigs(new BigDecimal(0), new BigDecimal(0), configs);
						}
					}
					if (">".equals(condition)) { // High
						sideDtl.setRiskName(configs.getHigh());
						sideDtl.setRiskColor(configs.getHighColor());
					} else if ("<>".equals(condition)) { // Medium
						sideDtl.setRiskName(configs.getMedium());
						sideDtl.setRiskColor(configs.getMediumColor());
					} else if ("<".equals(condition)) { // Low
						sideDtl.setRiskName(configs.getLow());
						sideDtl.setRiskColor(configs.getLowColor());
					}
				}
				data.setSideDtls(sideDtls);
				// RiskQuantity
				data.setRiskQuantity(new BigDecimal(sideDtls.size()));
				// Sum Data
				double avg = (declineValue / all) * 100;
				if (avg >= 0) {
					data.setAvgRisk(new BigDecimal(avg));
				} else {
					data.setAvgRisk(new BigDecimal(0));
				}
				IntCalculateCriteriaVo risk = IntCalculateCriteriaUtil.calculateCriteria(data.getAvgRisk(), config.get());
				data.setRiskColor(risk.getColor());
				data.setRiskText(risk.getTranslatingRisk());
				data.setRiskNum(risk.getRiskRate());
				// Finding Sector and Area Name
				List<ExciseDept> exciseDepts = ApplicationCache.getExciseSectorList();
				data.setStatusText(ApplicationCache.getParamInfoByCode("IA_STATUS", data.getStatusText()).getValue1());
				for (ExciseDept exciseDept : exciseDepts) {
					if (exciseDept.getOfficeCode().substring(0, 2).equals(data.getSectorName().substring(0, 2))) {
						data.setSectorName(exciseDept.getDeptName());
					}
				}
				ExciseDept area = ApplicationCache.getExciseDept(data.getAreaName());
				data.setAreaName(area.getDeptName());
			}
		}
		return datas;
	}

	DecimalFormat formatter = new DecimalFormat("#,##0.00");

	public ByteArrayOutputStream exportInt020301(String idHdrStr, String budgetYear) throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle tdStyle = ExcelUtils.getTdStyle();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;

		// Row [0]
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		List<ExcelHeaderNameVo> headerNames = new ArrayList<>();
		String[] tbTH1 = { "ลำดับ", "สำนักงานสรรพสามิตภาค", "สำนักงานสรรพสามิตพื้นที่", "จำนวนด้านความเสี่ยง",
				"ไม่มี/ไม่ใช่ (%)", "แปลค่าความเสี่ยง" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		BigDecimal idHdr = new BigDecimal(idHdrStr);
		headerNames = iaQuestionnaireSideJdbcRep.findNameByIdHdr(idHdr);
		for (int i = 0; i < headerNames.size() * 3; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue("ด้าน");
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		String[] tbTH2 = { "ส่งเมื่อ", "สถานะการดำเนินการ" };
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		// Row [1]
		row = sheet.createRow(rowNum);
		int cellNumtbTH2 = 0;
		// Empty Cell Row [1]
		for (int i = 0; i < 6; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Text Cell Row [1]
		for (int i = 0; i < headerNames.size() * 3; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellValue(headerNames.get(i / 3).getName());
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Empty Cell Row [1]
		for (int i = 6 + headerNames.size() * 3; i < 6 + headerNames.size() * 3 + 2; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		rowNum++;

		// Row [2]
		row = sheet.createRow(rowNum);
		int cellNumtbTH3 = 0;
		// Empty Cell Row [2]
		for (int i = 0; i < 6; i++) {
			cell = row.createCell(cellNumtbTH3);
			cell.setCellStyle(thStyle);
			cellNumtbTH3++;
		}
		// Text Cell Row [2]
		String[] tbTH3 = { "มี/ใช่", "ไม่มี/ไม่ใช่", "ระดับความเสี่ยง" };
		for (int i = 0; i < headerNames.size(); i++) {
			for (int j = 0; j < tbTH3.length; j++) {
				cell = row.createCell(cellNumtbTH3);
				cell.setCellValue(tbTH3[j]);
				cell.setCellStyle(thStyle);
				cellNumtbTH3++;
			}
		}
		// Empty Cell Row [2]
		for (int i = 6 + headerNames.size() * 3; i < 6 + headerNames.size() * 3 + 2; i++) {
			cell = row.createCell(cellNumtbTH3);
			cell.setCellStyle(thStyle);
			cellNumtbTH3++;
		}

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 6, 6 + headerNames.size() * 3 - 1));
		for (int i = 1; i <= headerNames.size(); i++) {
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 6 + ((i - 1) * 3), 6 + (i * 3) - 1));
		}
		for (int i = 0; i < 6; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 2, i, i));
		}
		for (int i = headerNames.size() * 3 + 6; i <= headerNames.size() * 3 + 6 + 2; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 2, i, i));
		}
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 6; i++) {
			if (i >= 1 && i <= 2) {
				sheet.setColumnWidth(i, width * 180);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}
		for (int i = 6; i <= headerNames.size() * 3 + 6; i++) {
			if (i % 3 == 2) {
				sheet.setColumnWidth(i, width * 50);
			} else {
				sheet.setColumnWidth(i, width * 40);
			}
		}
		for (int i = headerNames.size() * 3 + 6; i < headerNames.size() * 3 + 6 + 2; i++) {
			sheet.setColumnWidth(i, width * 76);
		}

		/* start details */
		int count = 1;
		rowNum = 3;
		cellNum = 0;
		List<Int020301InfoVo> details = findInfoByIdHdr(idHdrStr, budgetYear);
		for (Int020301InfoVo detail : details) {
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
			cell.setCellValue(detail.getSectorName());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getAreaName());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getRiskQuantity().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 5
			cell = row.createCell(cellNum);
			if (detail.getStatus().equalsIgnoreCase("FINISH")) {
				cell.setCellValue(detail.getAvgRisk().doubleValue());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 6
			cell = row.createCell(cellNum);
			if (detail.getStatus().equalsIgnoreCase("FINISH")) {
				cell.setCellValue(detail.getRiskText());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;

			for (Int020301DataVo sideDtl : detail.getSideDtls()) {
				// Column cellNum+1+1
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase("FINISH")) {
					cell.setCellValue(sideDtl.getAcceptValue().doubleValue());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyle);
				cellNum++;
				// Column cellNum+1+2
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase("FINISH")) {
					cell.setCellValue(sideDtl.getDeclineValue().doubleValue());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyle);
				cellNum++;
				// Column cellNum+1+3
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase("FINISH")) {
					cell.setCellValue(sideDtl.getRiskName());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyle);
				cellNum++;
			}

			// Column detail.getSideDtls().size()+1
			cell = row.createCell(cellNum);
			if (detail.getSentDate() != null) {
				if (detail.getStatus().equalsIgnoreCase("FINISH")) {
					cell.setCellValue(
							ConvertDateUtils.formatDateToString(detail.getSentDate(), ConvertDateUtils.DD_MM_YYYY));
				} else {
					cell.setCellValue("-");
				}
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column detail.getSideDtls().size()+2
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getStatusText());
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
	
	public ByteArrayOutputStream exportInt020301On030402(String idHdrStr, String budgetYear, String idConfigStr) throws IOException {
		/* create spreadsheet */
		XSSFWorkbook workbook = ExcelUtils.setUpExcel();
		CellStyle thStyle = ExcelUtils.getThStyle();
		CellStyle tdStyle = ExcelUtils.getTdStyle();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;

		// Row [0]
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		List<ExcelHeaderNameVo> headerNames = new ArrayList<>();
		String[] tbTH1 = { "ลำดับ", "สำนักงานสรรพสามิตภาค", "สำนักงานสรรพสามิตพื้นที่", "จำนวนด้านความเสี่ยง" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		BigDecimal idHdr = new BigDecimal(idHdrStr);
		headerNames = iaQuestionnaireSideJdbcRep.findNameByIdHdr(idHdr);
		for (int i = 0; i < headerNames.size() * 3; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue("ด้าน");
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		String[] tbTH2 = { "ส่งเมื่อ", "สถานะการดำเนินการ","ไม่มี/ไม่ใช่ (%)", "อัตราความเสี่ยง", "แปลค่าความเสี่ยง" };
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH2[i]);
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		rowNum++;

		// Row [1]
		row = sheet.createRow(rowNum);
		int cellNumtbTH2 = 0;
		// Empty Cell Row [1]
		for (int i = 0; i < 4; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Text Cell Row [1]
		for (int i = 0; i < headerNames.size() * 3; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellValue(headerNames.get(i / 3).getName());
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		// Empty Cell Row [1]
		for (int i = 4 + headerNames.size() * 3; i < 4 + headerNames.size() * 3 + 5; i++) {
			cell = row.createCell(cellNumtbTH2);
			cell.setCellStyle(thStyle);
			cellNumtbTH2++;
		}
		rowNum++;

		// Row [2]
		row = sheet.createRow(rowNum);
		int cellNumtbTH3 = 0;
		// Empty Cell Row [2]
		for (int i = 0; i < 4; i++) {
			cell = row.createCell(cellNumtbTH3);
			cell.setCellStyle(thStyle);
			cellNumtbTH3++;
		}
		// Text Cell Row [2]
		String[] tbTH3 = { "มี/ใช่", "ไม่มี/ไม่ใช่", "ระดับความเสี่ยง" };
		for (int i = 0; i < headerNames.size(); i++) {
			for (int j = 0; j < tbTH3.length; j++) {
				cell = row.createCell(cellNumtbTH3);
				cell.setCellValue(tbTH3[j]);
				cell.setCellStyle(thStyle);
				cellNumtbTH3++;
			}
		}
		// Empty Cell Row [2]
		for (int i = 4 + headerNames.size() * 3; i < 4 + headerNames.size() * 3 + 5; i++) {
			cell = row.createCell(cellNumtbTH3);
			cell.setCellStyle(thStyle);
			cellNumtbTH3++;
		}

		/* set sheet */
		// merge(firstRow, lastRow, firstCol, lastCol)
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 4, 4 + headerNames.size() * 3 - 1));
		for (int i = 1; i <= headerNames.size(); i++) {
			sheet.addMergedRegion(new CellRangeAddress(1, 1, 4 + ((i - 1) * 3), 4 + (i * 3) - 1));
		}
		for (int i = 0; i < 4; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 2, i, i));
		}
		for (int i = headerNames.size() * 3 + 4; i <= headerNames.size() * 3 + 4 + 5; i++) {
			sheet.addMergedRegion(new CellRangeAddress(0, 2, i, i));
		}
		/* set sheet */

		// setColumnWidth
		int width = 76;
		sheet.setColumnWidth(0, width * 30);
		for (int i = 1; i <= 4; i++) {
			if (i >= 1 && i <= 2) {
				sheet.setColumnWidth(i, width * 180);
			} else {
				sheet.setColumnWidth(i, width * 76);
			}
		}
		for (int i = 4; i <= headerNames.size() * 3 + 4; i++) {
			if (i % 3 == 2) {
				sheet.setColumnWidth(i, width * 50);
			} else {
				sheet.setColumnWidth(i, width * 40);
			}
		}
		for (int i = headerNames.size() * 3 + 4; i < headerNames.size() * 3 + 4 + 5; i++) {
			sheet.setColumnWidth(i, width * 76);
		}

		/* start details */
		int count = 1;
		rowNum = 3;
		cellNum = 0;
		List<Int020301InfoVo> details = findInfoByIdHdrRisk(idHdrStr, budgetYear, idConfigStr);
		for (Int020301InfoVo detail : details) {
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
			cell.setCellValue(detail.getSectorName());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 3
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getAreaName());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 4
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getRiskQuantity().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;

			for (Int020301DataVo sideDtl : detail.getSideDtls()) {
				// Column cellNum+1+1
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase("FINISH")) {
					cell.setCellValue(sideDtl.getAcceptValue().doubleValue());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyle);
				cellNum++;
				// Column cellNum+1+2
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase("FINISH")) {
					cell.setCellValue(sideDtl.getDeclineValue().doubleValue());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyle);
				cellNum++;
				// Column cellNum+1+3
				cell = row.createCell(cellNum);
				if (detail.getStatus().equalsIgnoreCase("FINISH")) {
					cell.setCellValue(sideDtl.getRiskName());
				} else {
					cell.setCellValue("-");
				}
				cell.setCellStyle(tdStyle);
				cellNum++;
			}

			// Column detail.getSideDtls().size()+1
			cell = row.createCell(cellNum);
			if (detail.getSentDate() != null) {
				if (detail.getStatus().equalsIgnoreCase("FINISH")) {
					cell.setCellValue(
							ConvertDateUtils.formatDateToString(detail.getSentDate(), ConvertDateUtils.DD_MM_YYYY));
				} else {
					cell.setCellValue("-");
				}
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column detail.getSideDtls().size()+2
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getStatusText());
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column detail.getSideDtls().size()+3
			cell = row.createCell(cellNum);
			if (detail.getStatus().equalsIgnoreCase("FINISH")) {
				cell.setCellValue(detail.getAvgRisk().doubleValue());
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;

			// Column detail.getSideDtls().size()+4
			DecimalFormat df2 = new DecimalFormat(".##");
			cell = row.createCell(cellNum);
			if (detail.getStatus().equalsIgnoreCase("FINISH")) {
				cell.setCellValue(df2.format(detail.getRiskNum().doubleValue()));
			} else {
				cell.setCellValue("-");
			}
			cell.setCellStyle(tdStyle);
			cellNum++;
			
			// Column detail.getSideDtls().size()+5
			cell = row.createCell(cellNum);
			if (detail.getStatus().equalsIgnoreCase("FINISH")) {
				cell.setCellValue(detail.getRiskText());
			} else {
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
