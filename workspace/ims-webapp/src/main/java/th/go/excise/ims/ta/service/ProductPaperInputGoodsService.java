package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperInputGoodsVo;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;
import th.go.excise.ims.ws.vo.WsOasfri0100FromVo;
import th.go.excise.ims.ws.vo.WsOasfri0100Vo;

@Service
public class ProductPaperInputGoodsService extends AbstractProductPaperService<ProductPaperInputGoodsVo> {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperInputGoodsService.class);
	private static final String PRODUCT_PAPER_INPUT_GOODS = "รับสินค้าสำเร็จรูป";

	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;

	@Override
	protected List<ProductPaperInputGoodsVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");

		LocalDate localDateStart = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]), Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
		LocalDate localDateEnd = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]), Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));

		WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
		wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
		wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
		wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
		wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setAccountName(WEB_SERVICE.OASFRI0100.PS0704_ACC14);

		List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
		List<ProductPaperInputGoodsVo> voList = new ArrayList<>();
		ProductPaperInputGoodsVo vo = null;
		for (WsOasfri0100Vo wsOasfri0100Vo : wsOasfri0100VoList) {
			vo = new ProductPaperInputGoodsVo();
			vo.setGoodsDesc(wsOasfri0100Vo.getDataName());
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<ProductPaperInputGoodsVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] exportData(List<ProductPaperInputGoodsVo> voList, String exportType) {
		// set format money
		DecimalFormat df = new DecimalFormat("#,##0.00");

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_INPUT_GOODS);
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
		CellStyle cellRightBgStyle = ExcelUtils.createCellColorStyle(workbook, new XSSFColor(new java.awt.Color(192, 192, 192)), HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "รายการ", "ใบรับสินค้าสำเร็จรูป (1)", "ปริมาณรับจากการผลิต", " ", "ผลต่างข้อมูลคู่ที่ 1 (2) - (1)", "ผลต่างข้อมูลคู่ที่ 2 (2) - (3)" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i == 2) {
				cell.setCellStyle(bgKeyIn);
			} else if (i == 5 || i == 6) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}

		}

		/* tbTH2 */
		rowNum++;
		row = sheet.createRow(rowNum);
		cell = row.createCell(3);
		cell.setCellValue("งบเดือน (ภส. ๐๗-๐๔) (2)");
		cell.setCellStyle(thStyle);
		cell = row.createCell(4);
		cell.setCellValue("บัญชีประจำวัน (ภส. ๐๗-๐๒) (3)");
		cell.setCellStyle(bgKeyIn);

		/* width */
		int column = 1;
		sheet.setColumnWidth(column, 76 * 150);
		column++;
		sheet.setColumnWidth(column, 76 * 80);
		column++;
		sheet.setColumnWidth(column, 76 * 90);
		column++;
		sheet.setColumnWidth(column, 76 * 90);
		column++;
		sheet.setColumnWidth(column, 76 * 100);
		column++;
		sheet.setColumnWidth(column, 76 * 100);

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));
		for (int i = 0; i < 7; i++) {
			if (i != 3 && i != 4) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				if (i < 2) {
					cell.setCellStyle(thStyle);
				} else if (i > 2) {
					cell.setCellStyle(bgCal);
				} else if (i == 2) {
					cell.setCellStyle(bgKeyIn);
				}
			}
		}
		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		for (ProductPaperInputGoodsVo data : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getGoodsDesc());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getInputGoodsQty());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getInputMonthStatementQty());
			cell.setCellStyle(cellRightBgStyle);
			cellNum++;

			cell = row.createCell(cellNum);
			if (EXPORT_TYPE_CREATE.equals(exportType)) {
				cell.setCellValue("");
				cell.setCellStyle(thStyle);
			} else {
				if (StringUtils.isNotBlank(data.getInputMonthStatementQty())) {
					cell.setCellValue(df.format(NumberUtils.toBigDecimal(data.getInputMonthStatementQty())));
				} else {
					cell.setCellValue("");
				}
				cell.setCellStyle(cellRightBgStyle);
			}
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getInputDailyAccountQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaxDiffQty1());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaxDiffQty1());
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
	public List<ProductPaperInputGoodsVo> upload(ProductPaperFormVo formVo) {
		logger.info("readFileProductPaperInputGoods");
		logger.info("fileName " + formVo.getFile().getOriginalFilename());
		logger.info("type " + formVo.getFile().getContentType());

		List<ProductPaperInputGoodsVo> dataList = new ArrayList<>();
		ProductPaperInputGoodsVo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()))) {
			Sheet sheet = workbook.getSheetAt(0);
			for (Row row : sheet) {
				data = new ProductPaperInputGoodsVo();
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
						// nputGoodsQty
						data.setInputGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// InputMonthStatementQty
						data.setInputMonthStatementQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// InputDailyAccountQty
						data.setInputDailyAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// MaxDiffQty
						data.setMaxDiffQty1(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// MaxDiffQty
						data.setMaxDiffQty2(ExcelUtils.getCellValueAsString(cell));
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
		// TODO Auto-generated method stub

	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}
}
