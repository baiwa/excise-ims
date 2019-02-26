package th.go.excise.ims.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsStatus;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsConfigRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsMasterRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsRepository;
import th.go.excise.ims.ia.persistence.repository.IaRiskFactorsStatusRepository;
import th.go.excise.ims.ia.vo.Int030101FormVo;

@Service
public class Int030101Service {
//	@Autowired
//	private int030101JdbcRepository int030101JdbcRepository;

	@Autowired
	private IaRiskFactorsMasterRepository iaRiskFactorsMasterRepository;

	@Autowired
	private IaRiskFactorsRepository iaRiskFactorsRepository;

	@Autowired
	private IaRiskFactorsConfigRepository iaRiskFactorsConfigRepository;

	@Autowired
	private IaRiskFactorsStatusRepository iaRiskFactorsStatusRepository;

	private CellStyle thStyle;
	private CellStyle cellCenter;
	private CellStyle cellRight;
	private CellStyle cellLeft;

	private CellStyle topCenter;
	private CellStyle topRight;
	private CellStyle topLeft;
	private Font fontHeader;

	private XSSFWorkbook setUpExcel() {
		XSSFWorkbook workbook = new XSSFWorkbook();

		thStyle = workbook.createCellStyle();
		thStyle.setAlignment(HorizontalAlignment.CENTER);
		thStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		thStyle.setBorderBottom(BorderStyle.THIN);
		thStyle.setBorderLeft(BorderStyle.THIN);
		thStyle.setBorderRight(BorderStyle.THIN);
		thStyle.setBorderTop(BorderStyle.THIN);
		thStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		thStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

		cellCenter = workbook.createCellStyle();
		cellCenter.setAlignment(HorizontalAlignment.CENTER);
		cellCenter.setVerticalAlignment(VerticalAlignment.TOP);
		cellCenter.setBorderBottom(BorderStyle.THIN);
		cellCenter.setBorderLeft(BorderStyle.THIN);
		cellCenter.setBorderRight(BorderStyle.THIN);
		cellCenter.setBorderTop(BorderStyle.THIN);

		cellRight = workbook.createCellStyle();
		cellRight.setAlignment(HorizontalAlignment.RIGHT);
		cellRight.setVerticalAlignment(VerticalAlignment.TOP);
		cellRight.setBorderBottom(BorderStyle.THIN);
		cellRight.setBorderLeft(BorderStyle.THIN);
		cellRight.setBorderRight(BorderStyle.THIN);
		cellRight.setBorderTop(BorderStyle.THIN);

		cellLeft = workbook.createCellStyle();
		cellLeft.setAlignment(HorizontalAlignment.LEFT);
		cellLeft.setVerticalAlignment(VerticalAlignment.TOP);
		cellLeft.setBorderBottom(BorderStyle.THIN);
		cellLeft.setBorderLeft(BorderStyle.THIN);
		cellLeft.setBorderRight(BorderStyle.THIN);
		cellLeft.setBorderTop(BorderStyle.THIN);
		cellLeft.setWrapText(true);

		fontHeader = workbook.createFont();
		fontHeader.setBold(true);

		topCenter = workbook.createCellStyle();
		topCenter.setAlignment(HorizontalAlignment.CENTER);
		topCenter.setFont(fontHeader);

		topRight = workbook.createCellStyle();
		topRight.setAlignment(HorizontalAlignment.RIGHT);

		topLeft = workbook.createCellStyle();
		topLeft.setAlignment(HorizontalAlignment.LEFT);
		return workbook;
	}

	public void saveFactors(Int030101FormVo form) {
		// Save IaRiskFactorsMaster
		IaRiskFactorsMaster dataFactorsMaster = new IaRiskFactorsMaster();
		dataFactorsMaster.setBudgetYear(form.getBudgetYear());
		dataFactorsMaster.setRiskFactorsMaster(form.getRiskFactorsMaster());
		dataFactorsMaster.setInspectionWork(form.getInspectionWork());
		dataFactorsMaster.setNotDelete("N");
		IaRiskFactorsMaster dataFactorsMasterRes = iaRiskFactorsMasterRepository.save(dataFactorsMaster);

		// Save IaRiskFactorsStatus
		IaRiskFactorsStatus dataFactorsStatus = new IaRiskFactorsStatus();
		dataFactorsStatus.setIdMaster(dataFactorsMasterRes.getId());
		dataFactorsStatus.setBudgetYear(form.getBudgetYear());
		dataFactorsStatus.setStatus("Y");
		dataFactorsStatus.setInspectionWork(form.getInspectionWork());
		iaRiskFactorsStatusRepository.save(dataFactorsStatus);

		// Save IaRiskFactors
		IaRiskFactors dataFactors = new IaRiskFactors();
		dataFactors.setIdMaster(dataFactorsMasterRes.getId());
		dataFactors.setRiskFactors(form.getRiskFactorsMaster());
		dataFactors.setBudgetYear(form.getBudgetYear());
		dataFactors.setInspectionWork(form.getInspectionWork());
		dataFactors.setSide(form.getSide());
		dataFactors.setStatusScreen("ยังไม่ได้กำหนด");
		IaRiskFactors dataFactorsRes = iaRiskFactorsRepository.save(dataFactors);

		// Save IaRiskFactorsConfig
		IaRiskFactorsConfig dataFactorsConfig = new IaRiskFactorsConfig();
		dataFactorsConfig.setIdFactors(dataFactorsRes.getId());
		dataFactorsConfig.setRiskUnit(form.getRiskUnit());
		dataFactorsConfig
				.setStartDate(ConvertDateUtils.parseStringToDate(form.getDateFrom(), ConvertDateUtils.DD_MM_YYYY));// ,
																													// ConvertDateUtils.LOCAL_TH
		dataFactorsConfig.setEndDate(ConvertDateUtils.parseStringToDate(form.getDateTo(), ConvertDateUtils.DD_MM_YYYY));
		dataFactorsConfig.setInfoUsedRiskDesc(form.getDataName());
		dataFactorsConfig.setInfoUsedRisk("1");
		dataFactorsConfig.setFactorsLevel(new BigDecimal(3));
		iaRiskFactorsConfigRepository.save(dataFactorsConfig);
	}

	public ByteArrayOutputStream exportInt030101() throws IOException {
		/* create spreadsheet */
		List<IaRiskFactorsMaster> dataFactorsMasterList = new ArrayList<IaRiskFactorsMaster>();
		XSSFWorkbook workbook = setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* Header */
		String[] tbTH1 = { "ลำดับ", "หน่วยงาน", "ค่าความเสี่ยง " };

		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
		}

		/* set sheet */

		// setColumnWidth
		for (int i = 1; i <= 2; i++) {
			if (i == 1 || i == 2) {
				sheet.setColumnWidth(i, 76 * 60);
			} else {
				sheet.setColumnWidth(i, 76 * 100);
			}

		}

		/* Detail */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		for (IaRiskFactorsMaster item : dataFactorsMasterList) {
			row = sheet.createRow(rowNum);
			// No.
			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}

		/* EndDetail */

		/* set write */
		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		workbook.write(outByteStream);

		return outByteStream;
	}

}
