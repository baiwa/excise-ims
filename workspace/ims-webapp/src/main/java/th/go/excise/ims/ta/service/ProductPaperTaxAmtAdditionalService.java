package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.ProductPaperTaxAmtAdditionalVo;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;

@Service
public class ProductPaperTaxAmtAdditionalService {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperTaxAmtAdditionalService.class);

	private static final Integer TOTAL = 17;
	private static final String PRODUCT_PAPER_TAX_AMT_ADDITIONAL = "คำนวณภาษีที่ต้องชำระเพิ่ม";

	public DataTableAjax<ProductPaperTaxAmtAdditionalVo> listProductPaperTaxAmtAdditional(CreatePaperFormVo request) {
		DataTableAjax<ProductPaperTaxAmtAdditionalVo> dataTableAjax = new DataTableAjax<ProductPaperTaxAmtAdditionalVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataProductPaperTaxAmtAdditional(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<ProductPaperTaxAmtAdditionalVo> getDataProductPaperTaxAmtAdditional(int start, int length, int total) {
		logger.info("getDataTax");
		String desc = "คำนวณภาษีที่ต้องชำระเพิ่ม";
		List<ProductPaperTaxAmtAdditionalVo> datalist = new ArrayList<ProductPaperTaxAmtAdditionalVo>();
		ProductPaperTaxAmtAdditionalVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ProductPaperTaxAmtAdditionalVo();
			data.setId(Long.valueOf(1));
			data.setGoodsDesc(desc + (i + 1));
			data.setTaxQty("1,000.00");
			data.setInformPrice("10,000.00");
			data.setTaxValue("10,000.00");
			data.setTaxRateByValue("10.00");
			data.setTaxRateByQty("1,000.00");
			data.setPenaltyAmt("500.00");
			data.setSurchargeAmt("100.00");
			data.setMoiTaxAmt("100.00");
			data.setNetTaxAmt("22,710.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportProductPaperTaxAmtAdditional() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_TAX_AMT_ADDITIONAL);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		// CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new
		// java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ปริมาณ", "ราคาขายปลีก", "มูลค่า", "อัตราภาษี (ร้อยละ)",
				"ภาษีที่ต้องชำระเพิ่มเติม", "เบี้ยปรับ", "เงินเพิ่ม", "ภาษีเพื่อราชการส่วนท้องถิ่น", "รวม" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(thStyle);
		}

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);
		sheet.setColumnWidth(colIndex++, 28 * 256);

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<ProductPaperTaxAmtAdditionalVo> dataList = getDataProductPaperTaxAmtAdditional(0, TOTAL, TOTAL);
		for (ProductPaperTaxAmtAdditionalVo data : dataList) {
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
			cell.setCellValue(data.getTaxQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getInformPrice());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxValue());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxRateByValue());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxRateByQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getPenaltyAmt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getSurchargeAmt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMoiTaxAmt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getNetTaxAmt());
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
