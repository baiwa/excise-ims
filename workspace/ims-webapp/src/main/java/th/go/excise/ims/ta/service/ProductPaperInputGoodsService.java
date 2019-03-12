package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperInputGoodsVo;

@Service
public class ProductPaperInputGoodsService {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperInputGoodsService.class);

	private static final Integer TOTAL = 17;
	private static final String PRODUCT_PAPER_INPUT_GOODS = "รับสินค้าสำเร็จรูป";

	public DataTableAjax<ProductPaperInputGoodsVo> listProductPaperInputGoods(CreatePaperFormVo request) {
		DataTableAjax<ProductPaperInputGoodsVo> dataTableAjax = new DataTableAjax<ProductPaperInputGoodsVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataProductPaperInputGoods(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<ProductPaperInputGoodsVo> getDataProductPaperInputGoods(int start, int length, int total) {
		logger.info("getDataProductPaperInputGoods");
		String desc = "ตรวจสอบการรับสินค้าสำเร็จรูป";
		List<ProductPaperInputGoodsVo> datalist = new ArrayList<ProductPaperInputGoodsVo>();
		ProductPaperInputGoodsVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ProductPaperInputGoodsVo();
			data.setId(Long.valueOf(1));
			data.setGoodsDesc(desc + (i + 1));
			data.setInputGoodsQty("02-00-0" + (i + 1));
			data.setInputMonthStatementQty("1,000.00");
			data.setInputDailyAccountQty("500.00");
			data.setMaxDiffQty("500.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportProductPaperInputGoods() {

		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_INPUT_GOODS);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);
		;

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "รายการ", "ใบรับสินค้าสำเร็จรูป", "ปริมาณรับจากการผลิต", "", "ผลต่างสูงสุด" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i != 3 && i != 4) {
				cell.setCellStyle(thStyle);
			} else {
				cell.setCellStyle(thColor);
			}

		}

		/* tbTH2 */
		String[] tbTH2 = { "", "", "", "งบเดือน (ภส. ๐๗-๐๔)", "(ภส. ๐๗-๐๒)" };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			if (i > 2) {
				cell = row.createCell(i);
				cell.setCellValue(tbTH2[i]);
				cell.setCellStyle(thColor);
			}
		}

		/* width */
		for (int i = 0; i < 6; i++) {
			if (i > 2) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			} else if (i == 2) {
				sheet.setColumnWidth(i, 76 * 80);
			}
		}

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 3, 4));

		for (int i = 0; i < 6; i++) {
			if (i != 3 && i != 4) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		List<ProductPaperInputGoodsVo> dataList = getDataProductPaperInputGoods(0, TOTAL, TOTAL);
		for (ProductPaperInputGoodsVo data : dataList) {
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
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getInputDailyAccountQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaxDiffQty());
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
}
