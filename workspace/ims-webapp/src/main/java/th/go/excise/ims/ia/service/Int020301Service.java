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

import th.co.baiwa.buckwaframework.common.constant.MessageContants.IA;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ExciseDept;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ia.persistence.repository.jdbc.IaQuestionnaireSideJdbcRepository;
import th.go.excise.ims.ia.persistence.repository.jdbc.Int020301JdbcRepository;
import th.go.excise.ims.ia.vo.Int020301DataVo;
import th.go.excise.ims.ia.vo.Int020301HeaderVo;
import th.go.excise.ims.ia.vo.Int020301InfoVo;
import th.go.excise.ims.preferences.vo.ExcelHeaderNameVo;

@Service
public class Int020301Service {

	@Autowired
	private Int020301JdbcRepository int020301JdbcRepository;

	@Autowired
	private IaQuestionnaireSideJdbcRepository iaQuestionnaireSideJdbcRep;

	public List<Int020301HeaderVo> findHeaderByIdSide(String idSideStr, String budgetYear) {
		BigDecimal idSide = new BigDecimal(idSideStr);
		return int020301JdbcRepository.findHeaderByIdSide(idSide, budgetYear);
	}

	public List<Int020301InfoVo> findInfoByIdHdr(String idHdrStr, String budgetYear) {
		BigDecimal idHdr = new BigDecimal(idHdrStr);
		List<Int020301InfoVo> datas = new ArrayList<>();
		datas = int020301JdbcRepository.findInfoByIdSide(idHdr, budgetYear);
		for (Int020301InfoVo data : datas) {
			// Sides Data
			int passValue = 0;
			int failValue = 0;
			List<Int020301DataVo> sideDtls = int020301JdbcRepository.findDataByIdHdr(idHdr, budgetYear,
					data.getAreaName());
			for (Int020301DataVo sideDtl : sideDtls) {
				// Calculate RiskName
				if (sideDtl.getAcceptValue() != null && sideDtl.getDeclineValue() != null) {
					if (sideDtl.getAcceptValue().doubleValue() < sideDtl.getDeclineValue().doubleValue()) {
						sideDtl.setRiskName("สูง");
						sideDtl.setRisk("HIGH");
						failValue++;
					} else {
						sideDtl.setRiskName("ต่ำ");
						sideDtl.setRisk("LOW");
						passValue++;
					}
				} else if (sideDtl.getAcceptValue() != null) {
					sideDtl.setRiskName("ต่ำ");
					sideDtl.setRisk("LOW");
					sideDtl.setDeclineValue(new BigDecimal(0));
					passValue++;
				} else if (sideDtl.getDeclineValue() != null) {
					sideDtl.setRiskName("สูง");
					sideDtl.setRisk("HIGH");
					sideDtl.setAcceptValue(new BigDecimal(0));
					failValue++;
				} else {
					sideDtl.setRiskName("สูง");
					sideDtl.setRisk("HIGH");
					sideDtl.setAcceptValue(new BigDecimal(0));
					sideDtl.setDeclineValue(new BigDecimal(0));
					failValue++;
				}
			}
			data.setSideDtls(sideDtls);
			// RiskQuantity
			data.setRiskQuantity(new BigDecimal(sideDtls.size()));
			// Sum Data
			data.setPassValue(new BigDecimal(passValue));
			data.setFailValue(new BigDecimal(failValue));
			// Finding Sector and Area Name
			List<ExciseDept> exciseDepts = ApplicationCache.getExciseSectorList();
			data.setStatusText(IA.qtnStatus(data.getStatusText()));
			for (ExciseDept exciseDept : exciseDepts) {
				if (exciseDept.getOfficeCode().substring(0, 2).equals(data.getSectorName().substring(0, 2))) {
					data.setSectorName(exciseDept.getDeptName());
				}
			}
			for (ExciseDept exciseDept : exciseDepts) {
				if (exciseDept.getOfficeCode().substring(2, 4).equals(data.getAreaName().substring(2, 4))) {
					data.setAreaName(exciseDept.getDeptName());
				}
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
		String[] tbTH1 = { "NO", "FECT_NAME", "FECT_NAME2", "RISK_QUANTITY", "PASS_VALUE", "FAIL_VALUE" };
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
			cell.setCellValue("TEST");
			cell.setCellStyle(thStyle);
			cellNum++;
		}
		String[] tbTH2 = { "SENT_DATE", "STATUS" };
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
		String[] tbTH3 = { "A", "B", "C" };
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
		sheet.setColumnWidth(0, width * 20);
		for (int i = 1; i <= 6; i++) {
			if (i >= 1 && i <= 2) {
				sheet.setColumnWidth(i, width * 200);
			} else {
				sheet.setColumnWidth(i, width * 50);
			}
		}
		for (int i = 6; i <= headerNames.size() * 3 + 6; i++) {
			sheet.setColumnWidth(i, width * 30);
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
			cell.setCellValue(detail.getPassValue().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;
			// Column 6
			cell = row.createCell(cellNum);
			cell.setCellValue(detail.getFailValue().doubleValue());
			cell.setCellStyle(tdStyle);
			cellNum++;

			for (Int020301DataVo sideDtl : detail.getSideDtls()) {
				// Column cellNum+1+1
				cell = row.createCell(cellNum);
				cell.setCellValue(sideDtl.getAcceptValue().doubleValue());
				cell.setCellStyle(tdStyle);
				cellNum++;
				// Column cellNum+1+2
				cell = row.createCell(cellNum);
				cell.setCellValue(sideDtl.getDeclineValue().doubleValue());
				cell.setCellStyle(tdStyle);
				cellNum++;
				// Column cellNum+1+3
				cell = row.createCell(cellNum);
				cell.setCellValue(sideDtl.getRiskName());
				cell.setCellStyle(tdStyle);
				cellNum++;
			}

			// Column detail.getSideDtls().size()+1
			cell = row.createCell(cellNum);
			if (detail.getSentDate() != null) {
				cell.setCellValue(
						ConvertDateUtils.formatDateToString(detail.getSentDate(), ConvertDateUtils.DD_MM_YYYY));
			} else {
				cell.setCellValue("");
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
}
