package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperOutputGoodsVo;

@Service
public class ProductPaperOutputGoodsService {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperOutputGoodsService.class);

	private static final Integer TOTAL = 17;
	private static final String PRODUCT_PAPER_OUTPUT_GOODS = "ตรวจสอบการจ่ายสินค้าสำเร็จรูป";;

	public DataTableAjax<ProductPaperOutputGoodsVo> listProductPaperOutputGoods(CreatePaperFormVo request) {
		DataTableAjax<ProductPaperOutputGoodsVo> dataTableAjax = new DataTableAjax<ProductPaperOutputGoodsVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataProductPaperOutputGoods(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<ProductPaperOutputGoodsVo> getDataProductPaperOutputGoods(int start, int length, int total) {
		logger.info("getDataProductPaperOutputGoods");
		String desc = "ตรวจสอบการจ่ายสินค้าสำเร็จรูป";
		List<ProductPaperOutputGoodsVo> datalist = new ArrayList<ProductPaperOutputGoodsVo>();
		ProductPaperOutputGoodsVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ProductPaperOutputGoodsVo();
			data.setId(Long.valueOf(1));
			data.setGoodsDesc(desc + (i + 1));
			data.setOutputGoodsQty("1,000.00");
			data.setOutputDailyAccountQty("1,000.00");
			data.setOutputMonthStatementQty("1,000.00");
			data.setAuditQty("900.00");
			data.setTaxGoodsQty("800.00");
			data.setDiffQty("100.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportProductPaperOutputGoods() throws IOException {

		// create spreadsheet
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_OUTPUT_GOODS);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		// call style from utils
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		// tbTH
		String[] tbTH = { "ลำดับ", "รายการ", "ปริมาณจ่ายสินค้าสำเร็จรูป" + "\n" + "ในใบกำกับภาษีขาย",
				"ปริมาณจ่ายสินค้าสำเร็จรูป" + "\n" + " (ภส. ๐๗-๐๒)",
				"ปริมาณจ่ายสินค้าสำเร็จรูป " + "\n" + "จากงบเดือน(ภส. ๐๗-๐๔)",
				"ปริมาณที่ได้จากการตรวจสอบ" + "\n" + "(1)", "ปริมาณจ่ายสินค้าสำเร็จรูป " + "\n" + "(ภส. ๐๓-๐๗ (2))",
				"ผลต่าง" + "\n" + " (1 - 2)" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i != 2 && i != 3) {
				cell.setCellStyle(thStyle);
			} else {
				cell.setCellStyle(thColor);
			}

		}

		// width
		for (int i = 0; i < 8; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 80);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		// set data
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<ProductPaperOutputGoodsVo> dataList = getDataProductPaperOutputGoods(0, TOTAL, TOTAL);
		for (ProductPaperOutputGoodsVo data : dataList) {
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
			cell.setCellValue(data.getOutputGoodsQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getOutputDailyAccountQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getOutputMonthStatementQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getAuditQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxGoodsQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiffQty());
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

	public List<ProductPaperOutputGoodsVo> readFileProductPaperOutputGoods(ProductPaperOutputGoodsVo request) {
		logger.info("readFileProductPaperInputGoods");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());

		List<ProductPaperOutputGoodsVo> dataList = new ArrayList<>();
		ProductPaperOutputGoodsVo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()))) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new ProductPaperOutputGoodsVo();
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
						// OutputGoodsQty
						data.setOutputGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// OutputDailyAccountQty
						data.setOutputDailyAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// OutputMonthStatementQty
						data.setOutputMonthStatementQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// AuditQty
						data.setAuditQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// TaxGoodsQty
						data.setTaxGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// DiffQty
						data.setDiffQty(ExcelUtils.getCellValueAsString(cell));
					}
				}
				dataList.add(data);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return dataList;
	}

}
