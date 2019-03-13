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
import th.go.excise.ims.ta.vo.ProductPaperUnitPriceReduceTaxVo;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;

@Service
public class ProductPaperUnitPriceReduceTaxService {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperUnitPriceReduceTaxService.class);

	private static final Integer TOTAL = 17;
	private static final String PRODUCT_PAPER_UNIT_PRICE_REDUCE_TAX = "ราคาต่อหน่วยสินค้า";

	public DataTableAjax<ProductPaperUnitPriceReduceTaxVo> listProductPaperUnitPriceReduceTax(CreatePaperFormVo request) {
		DataTableAjax<ProductPaperUnitPriceReduceTaxVo> dataTableAjax = new DataTableAjax<ProductPaperUnitPriceReduceTaxVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataProductPaperUnitPriceReduceTax(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<ProductPaperUnitPriceReduceTaxVo> getDataProductPaperUnitPriceReduceTax(int start, int length, int total) {
		logger.info("getDataProductPaperUnitPriceReduceTax");
		String desc = "ราคาต่อหน่วยสินค้าที่ขอลดหย่อนภาษี";
		List<ProductPaperUnitPriceReduceTaxVo> datalist = new ArrayList<ProductPaperUnitPriceReduceTaxVo>();
		ProductPaperUnitPriceReduceTaxVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ProductPaperUnitPriceReduceTaxVo();
			data.setId(Long.valueOf(1));
			data.setGoodsDesc(desc + (i + 1));
			data.setTaxReduceAmt("1,000.00");
			data.setTaxReduceQty("100.00");
			data.setTaxReducePerUnitAmt("10.00");
			data.setBillNo("001-22-70" + (i + 1));
			data.setBillTaxAmt("1,000.00");
			data.setBillTaxQty("100.00");
			data.setBillTaxPerUnit("10.00");
			data.setDiffTaxReduceAmt("0.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportProductPaperUnitPriceReduceTax() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_UNIT_PRICE_REDUCE_TAX);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from Utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "รายการ", "ขอลดหย่อนตามแบบ ภส. ๐๕-๐๓", "", "", "ใบเสร็จรับเงิน", "", "", "",
				"ผลต่าง" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i > 4 && i < 9) {
				cell.setCellStyle(thColor);
			} else {
				cell.setCellStyle(thStyle);
			}

		}

		/* tbTH2 */
		String[] tbTH2 = { "", "", "ภาษีรวม", "ปริมาณ", "ภาษีต่อหน่วย", "เลขที่ใบเสร็จ", "ภาษีรวม", "ปริมาณ",
				"ภาษีต่อหน่วย" };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			if (i > 1) {
				cell = row.createCell(i);
				cell.setCellValue(tbTH2[i]);
				if (i > 4 && i < 9) {
					cell.setCellStyle(thColor);
				} else {
					cell.setCellStyle(thStyle);
				}
			}
		}

		/* width */
		for (int i = 0; i < 10; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 2, 4));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 8));

		for (int i = 0; i < 10; i++) {
			if (i < 2 || i > 8) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		List<ProductPaperUnitPriceReduceTaxVo> dataList = getDataProductPaperUnitPriceReduceTax(0, TOTAL, TOTAL);
		for (ProductPaperUnitPriceReduceTaxVo data : dataList) {
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
			cell.setCellValue(data.getTaxReduceAmt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxReduceQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxReducePerUnitAmt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBillNo());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBillTaxAmt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBillTaxQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBillTaxPerUnit());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiffTaxReduceAmt());
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
