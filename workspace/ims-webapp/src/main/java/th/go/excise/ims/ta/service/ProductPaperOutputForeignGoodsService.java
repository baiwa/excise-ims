package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr10D;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr10H;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr10DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr10HRepository;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputForeignGoodsVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class ProductPaperOutputForeignGoodsService extends AbstractProductPaperService<ProductPaperOutputForeignGoodsVo, TaPaperPr10H> {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductPaperOutputForeignGoodsService.class);

	private static final String PRODUCT_PAPER_OUTPUT_FOREIGN_GOODS = "จ่ายสินค้าสำเร็จรูปต่างประเทศ";
	
	@Autowired
	private TaPaperPr10HRepository taPaperPr10HRepository;
	@Autowired
	private TaPaperPr10DRepository taPaperPr10DRepository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected String getPaperCode() {
		return "10";
	}
	
	@Override
	protected Object getRepository() {
		return taPaperPr10HRepository;
	}
	
	@Override
	protected List<ProductPaperOutputForeignGoodsVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");

//		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
//		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
//
//		WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
//		wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
//		wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
//		wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
//		wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
//		wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));
//		wsOasfri0100FormVo.setAccountName(WEB_SERVICE.OASFRI0100.PS0704_ACC05);
//
//		List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
		String[] splStartDate = formVo.getStartDate().split("/");
		String cvStartDate = splStartDate[1]+splStartDate[0]+"01";
		String[] splEndDate = formVo.getEndDate().split("/");
		String cvEndDate = splEndDate[1]+splEndDate[0]+"01";
		List<WsAnafri0001Vo> wsAnafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), cvStartDate, cvEndDate);
		List<ProductPaperOutputForeignGoodsVo> voList = new ArrayList<>();
		ProductPaperOutputForeignGoodsVo vo = null;
		for (WsAnafri0001Vo wsAnafri0001Vo : wsAnafri0001VoList) {
			vo = new ProductPaperOutputForeignGoodsVo();
			vo.setGoodsDesc(wsAnafri0001Vo.getProductName());
			vo.setCargoDocNo(NO_VALUE);
			vo.setInvoiceNo(NO_VALUE);
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<ProductPaperOutputForeignGoodsVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		logger.info("inquiryByPaperPrNumber paperPrNumber={}", formVo.getPaperPrNumber());
		
		List<TaPaperPr10D> entityList = taPaperPr10DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperOutputForeignGoodsVo> voList = new ArrayList<>();
		ProductPaperOutputForeignGoodsVo vo = null;
		for (TaPaperPr10D entity : entityList) {
			vo = new ProductPaperOutputForeignGoodsVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setCargoDocNo(entity.getCargoDocNo() != null ? entity.getCargoDocNo().toString() : NO_VALUE);
			vo.setInvoiceNo(entity.getInvoiceNo() != null ? entity.getInvoiceNo().toString() : NO_VALUE);
			vo.setOutputDailyAccountQty(entity.getOutputDailyAccountQty() != null ? entity.getOutputDailyAccountQty().toString() : NO_VALUE);
			vo.setOutputMonthStatementQty(entity.getOutputMonthStatementQty() != null ? entity.getOutputMonthStatementQty().toString() : NO_VALUE);
			vo.setOutputAuditQty(entity.getOutputAuditQty() != null ? entity.getOutputAuditQty().toString() : NO_VALUE);
			vo.setTaxReduceQty(entity.getTaxReduceQty() != null ? entity.getTaxReduceQty().toString() : NO_VALUE);
			vo.setDiffOutputQty(entity.getDiffOutputQty() != null ? entity.getDiffOutputQty().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(ProductPaperFormVo formVo, List<ProductPaperOutputForeignGoodsVo> voList, String exportType) {
		logger.info("exportData");
		
		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_OUTPUT_FOREIGN_GOODS);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
		  CellStyle cellRightBgStyle = ExcelUtils.createCellColorStyle(workbook, new XSSFColor(new java.awt.Color(192, 192, 192)), HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ใบขนสินค้า", "INV", "บัญชีประจำวัน (ภส.๐๗-๐๒)", "งบเดือน (ภส.๐๗-๐๔)", "จากการตรวจสอบ","จำนวนขอยกเว้นหรือคืนภาษี(ภส.๐๕-๐๑)", "ผลต่าง"};
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i > 1 && i < 4) {
				cell.setCellStyle(bgKeyIn);
			} else if (i == 8) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}

		}

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		for (ProductPaperOutputForeignGoodsVo vo : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(vo.getGoodsDesc());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(vo.getCargoDocNo());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(vo.getInvoiceNo());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getOutputDailyAccountQty()) && !NO_VALUE.equals(vo.getOutputDailyAccountQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getOutputDailyAccountQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRightBgStyle);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getOutputMonthStatementQty()) && !NO_VALUE.equals(vo.getOutputMonthStatementQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getOutputMonthStatementQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRightBgStyle);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getOutputAuditQty()) && !NO_VALUE.equals(vo.getOutputAuditQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getOutputAuditQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRightBgStyle);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getTaxReduceQty()) && !NO_VALUE.equals(vo.getTaxReduceQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getTaxReduceQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRightBgStyle);
			cellNum++;
			
			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
			} else {
				if (StringUtils.isNotBlank(vo.getDiffOutputQty()) && !NO_VALUE.equals(vo.getDiffOutputQty())) {
					cell.setCellValue(DECIMAL_FORMAT.get().format(NumberUtils.toBigDecimal(vo.getDiffOutputQty())));
				} else {
					cell.setCellValue(NO_VALUE);
				}
			}
			cell.setCellStyle(cellRightBgStyle);
			cellNum++;


			no++;
			rowNum++;
			cellNum = 0;
		}
		
		// Create 'Criteria' Sheet
		createSheetCriteria(workbook, formVo);

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
	public List<ProductPaperOutputForeignGoodsVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("uploadData readVo filename={}", formVo.getFile().getOriginalFilename());

		List<ProductPaperOutputForeignGoodsVo> dataList = new ArrayList<>();
		ProductPaperOutputForeignGoodsVo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new ProductPaperOutputForeignGoodsVo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						// GoodsDesc
						data.setGoodsDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						// CargoDocNo
						data.setCargoDocNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// InvoiceNo
						data.setInvoiceNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// OutputDailyAccountQty
						data.setOutputDailyAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// OutputMonthStatementQty
						data.setOutputMonthStatementQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// OutputAuditQty
						data.setOutputAuditQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// TaxReduceQty
						data.setTaxReduceQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 8) {
						// DiffOutputQty
						data.setDiffOutputQty(ExcelUtils.getCellValueAsString(cell));
					}

				}
				dataList.add(data);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataList;
	}

	@Transactional(rollbackOn = {Exception.class})
	@Override
	public String save(ProductPaperFormVo formVo) {
		TaPaperPr10H entityH = new TaPaperPr10H();
		String paperPrNumber = prepareEntityH(formVo, entityH, TaPaperPr10H.class);
		logger.info("save paperPrNumber={}", paperPrNumber);
		taPaperPr10HRepository.save(entityH);
		
		List<ProductPaperOutputForeignGoodsVo> voList = gson.fromJson(formVo.getJson(), getListVoType());
		List<TaPaperPr10D> entityDList = new ArrayList<>();
		TaPaperPr10D entityD = null;
		int i = 1;
		for (ProductPaperOutputForeignGoodsVo vo : voList) {
			entityD = new TaPaperPr10D();
			entityD.setPaperPrNumber(paperPrNumber);
			entityD.setSeqNo(i);
			entityD.setGoodsDesc(vo.getGoodsDesc());
			entityD.setCargoDocNo(!NO_VALUE.equals(vo.getCargoDocNo()) ? NumberUtils.toBigDecimal(vo.getCargoDocNo()) : null);
			entityD.setInvoiceNo(vo.getInvoiceNo());
			entityD.setOutputDailyAccountQty(!NO_VALUE.equals(vo.getOutputDailyAccountQty()) ? NumberUtils.toBigDecimal(vo.getOutputDailyAccountQty()) : null);
			entityD.setOutputMonthStatementQty(!NO_VALUE.equals(vo.getOutputMonthStatementQty()) ? NumberUtils.toBigDecimal(vo.getOutputMonthStatementQty()) : null);
			entityD.setOutputAuditQty(!NO_VALUE.equals(vo.getOutputAuditQty()) ? NumberUtils.toBigDecimal(vo.getOutputAuditQty()) : null);
			entityD.setTaxReduceQty(!NO_VALUE.equals(vo.getTaxReduceQty()) ? NumberUtils.toBigDecimal(vo.getTaxReduceQty()) : null);
			entityD.setDiffOutputQty(!NO_VALUE.equals(vo.getDiffOutputQty()) ? NumberUtils.toBigDecimal(vo.getDiffOutputQty()) : null);
			entityDList.add(entityD);
			i++;
		}
		taPaperPr10DRepository.saveAll(entityDList);
		
		return paperPrNumber;
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		return taPaperPr10HRepository.findPaperPrNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}
}
