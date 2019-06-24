package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr02D;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr02H;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr02DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr02HRepository;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputMaterialVo;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;
import th.go.excise.ims.ws.vo.WsOasfri0100FromVo;
import th.go.excise.ims.ws.vo.WsOasfri0100Vo;

@Service
public class ProductPaperOutputMaterialService extends AbstractProductPaperService<ProductPaperOutputMaterialVo> {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperOutputMaterialService.class);

	private static final String PRODUCT_PAPER_OUTPUT_MATERIAL = "ตรวจสอบการจ่ายวัตถุดิบ";

	@Autowired
	private TaPaperPr02HRepository taPaperPr02HRepository;
	@Autowired
	private TaPaperPr02DRepository taPaperPr02DRepository;
	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected List<ProductPaperOutputMaterialVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");

		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());

		WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
		wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
		wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
		wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
		wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setAccountName(WEB_SERVICE.OASFRI0100.PS0704_ACC07);

		List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
		List<ProductPaperOutputMaterialVo> voList = new ArrayList<>();
		ProductPaperOutputMaterialVo vo = null;
		for (WsOasfri0100Vo wsOasfri0100Vo : wsOasfri0100VoList) {
			vo = new ProductPaperOutputMaterialVo();
			vo.setMaterialDesc(wsOasfri0100Vo.getDataName());
			vo.setMonthStatementQty(wsOasfri0100Vo.getInQty().toString());
			vo.setOutputMaterialQty(NO_VALUE);
			vo.setDailyAccountQty(NO_VALUE);
			vo.setExternalDataQty(NO_VALUE);
			vo.setMaxDiffQty(NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<ProductPaperOutputMaterialVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {

		List<TaPaperPr02D> entityList = taPaperPr02DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperOutputMaterialVo> voList = new ArrayList<>();
		ProductPaperOutputMaterialVo vo = null;
		for (TaPaperPr02D entity : entityList) {
			vo = new ProductPaperOutputMaterialVo();
			vo.setMaterialDesc(entity.getMaterialDesc());
			vo.setOutputMaterialQty(entity.getOutputMaterialQty() != null ? entity.getOutputMaterialQty().toString() : NO_VALUE);
			vo.setDailyAccountQty(entity.getDailyAccountQty() != null ? entity.getDailyAccountQty().toString() : NO_VALUE);
			vo.setMonthStatementQty(entity.getMonthStatementQty() != null ? entity.getMonthStatementQty().toString() : NO_VALUE);
			vo.setExternalDataQty(entity.getExternalDataQty() != null ? entity.getExternalDataQty().toString() : NO_VALUE);
			vo.setMaxDiffQty(entity.getMaxDiffQty() != null ? entity.getMaxDiffQty().toString() : NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected byte[] exportData(ProductPaperFormVo formVo, List<ProductPaperOutputMaterialVo> voList, String exportType) {
		// set format money
		DecimalFormat df = new DecimalFormat("#,##0.00");

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_OUTPUT_MATERIAL);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
//		CellStyle cellRightBgStyle = ExcelUtils.createCellColorStyle(workbook, new XSSFColor(new java.awt.Color(192, 192, 192)), HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ใบเบิกวัตถุดิบ", "บัญชีประจำวัน (ภส.๐๗-๐๑)", "งบเดือน (ภส. ๐๗-๐๔)", "จำนวนจ่ายวัตถุดิบ", "ผลต่างสูงสุด" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i == 2 || i == 3 || i == 5) {
				cell.setCellStyle(bgKeyIn);
			} else if (i == 6) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}
		}

		/* width */
		for (int i = 0; i < 7; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 100);
			}
		}

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;

		for (ProductPaperOutputMaterialVo data : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaterialDesc());
			cell.setCellStyle(cellLeft);
			cellNum++;

			// getOutputMaterialQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getOutputMaterialQty())  && !NO_VALUE.equals(data.getOutputMaterialQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getOutputMaterialQty())));
				} else {
					cell.setCellValue("");
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getDailyAccountQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getDailyAccountQty()) && !NO_VALUE.equals(data.getDailyAccountQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getDailyAccountQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			// getMonthStatementQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
				cell.setCellStyle(thStyle);
			} else {
				if (StringUtils.isNotBlank(data.getMonthStatementQty()) && !NO_VALUE.equals(data.getMonthStatementQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getMonthStatementQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
				cell.setCellStyle(cellRight);
			}
			cellNum++;

			// getExternalDataQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getExternalDataQty()) && !NO_VALUE.equals(data.getExternalDataQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getExternalDataQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			/// getMaxDiffQty
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(data.getMaxDiffQty()) && !NO_VALUE.equals(data.getMaxDiffQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getMaxDiffQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRight);
			cellNum++;

			no++;
			rowNum++;
			cellNum = 0;
		}

		// set output
		byte[] content = null;
		try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
			workbook.write(outputStream);
			content = outputStream.toByteArray();
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}

		return content;
	}

	@Override
	public List<ProductPaperOutputMaterialVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("uploadData readVo filename={}", formVo.getFile().getOriginalFilename());

		List<ProductPaperOutputMaterialVo> dataList = new ArrayList<>();
		ProductPaperOutputMaterialVo data = null;

		try (Workbook workbook = WorkbookFactory.create(formVo.getFile().getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new ProductPaperOutputMaterialVo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {

					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						// MaterialDesc
						data.setMaterialDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						// OutputMaterialQty
						data.setOutputMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// DailyAccountQty
						data.setDailyAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// MonthStatementQty
						data.setMonthStatementQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// ExternalDataQty
						data.setExternalDataQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// MaxDiffQty
						data.setMaxDiffQty(ExcelUtils.getCellValueAsString(cell));
					}
				}
				dataList.add(data);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return dataList;
	}

	@Override
	public void save(ProductPaperFormVo formVo) {
		logger.info("save");

		TaPaperPr02H entityH = new TaPaperPr02H();
		prepareEntityH(formVo, entityH, TaPaperPr02H.class);
		taPaperPr02HRepository.save(entityH);

		List<ProductPaperOutputMaterialVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperPr02D> entityDList = new ArrayList<>();
		TaPaperPr02D entityD = null;
		int i = 1;
		for (ProductPaperOutputMaterialVo vo : voList) {
			entityD = new TaPaperPr02D();
			entityD.setPaperPrNumber(entityH.getPaperPrNumber());
			entityD.setSeqNo(i);
			entityD.setMaterialDesc(vo.getMaterialDesc());
			entityD.setOutputMaterialQty(!NO_VALUE.equals(vo.getOutputMaterialQty()) ? NumberUtils.toBigDecimal(vo.getOutputMaterialQty()) : null);
			entityD.setDailyAccountQty(!NO_VALUE.equals(vo.getDailyAccountQty()) ? NumberUtils.toBigDecimal(vo.getDailyAccountQty()) : null);
			entityD.setMonthStatementQty(!NO_VALUE.equals(vo.getMonthStatementQty()) ? NumberUtils.toBigDecimal(vo.getMonthStatementQty()) : null);
			entityD.setExternalDataQty(!NO_VALUE.equals(vo.getExternalDataQty()) ? NumberUtils.toBigDecimal(vo.getExternalDataQty()) : null);
			entityD.setMaxDiffQty(!NO_VALUE.equals(vo.getMaxDiffQty()) ? NumberUtils.toBigDecimal(vo.getMaxDiffQty()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperPr02DRepository.saveAll(entityDList);
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		return taPaperPr02HRepository.findPaperPrNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}
