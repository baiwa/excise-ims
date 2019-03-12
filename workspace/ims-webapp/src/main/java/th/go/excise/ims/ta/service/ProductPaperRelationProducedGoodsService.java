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
import th.go.excise.ims.ta.vo.ProductPaperRelationProducedGoodsVo;

@Service
public class ProductPaperRelationProducedGoodsService {
	private static final Logger logger = LoggerFactory.getLogger(ProductPaperRelationProducedGoodsService.class);

	private static final Integer TOTAL = 17;
	private static final String PRODUCT_PAPER_RELATION_PRODUCED_GOODS = "ความสัมพันธ์การเบิกใช้วัตถุดิบ";

	public DataTableAjax<ProductPaperRelationProducedGoodsVo> listProductPaperRelationProducedGoods(
			CreatePaperFormVo request) {
		DataTableAjax<ProductPaperRelationProducedGoodsVo> dataTableAjax = new DataTableAjax<ProductPaperRelationProducedGoodsVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataProductPaperRelationProducedGoods(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<ProductPaperRelationProducedGoodsVo> getDataProductPaperRelationProducedGoods(int start, int length,
			int total) {
		logger.info("getDataProductPaperRelationProducedGoods");
		String desc = "การเบิกใช้วัตถุดิบกับการรับสินค้าสำเร็จรูป";
		List<ProductPaperRelationProducedGoodsVo> datalist = new ArrayList<ProductPaperRelationProducedGoodsVo>();
		ProductPaperRelationProducedGoodsVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ProductPaperRelationProducedGoodsVo();
			data.setId(Long.valueOf(1));
			data.setDocNo("1001" + (i + 1));
			data.setMaterialDesc(desc + (i + 1));
			data.setInputMaterialQty("1,000.00");
			data.setFormulaMaterialQty("E25+E15+E15");
			data.setUsedMaterialQty("700.00");
			data.setRealUsedMaterialQty("500.00");
			data.setDiffMaterialQty("200.00");
			data.setMaterialQty("400.00");
			data.setGoodsQty("300.00");
			data.setDiffGoodsQty("100.00");
			data.setWasteGoodsPnt("5%");
			data.setWasteGoodsQty("100.00");
			data.setBalanceGoodsQty("600.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportProductPaperRelationProducedGoods() {

		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_RELATION_PRODUCED_GOODS);
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

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "เลขที่ใบสำคัญ", "รายการ", "จำนวนรับ" + "\n" + "(ตามบัญชี ภส. ๐๗-๐๒)",
				"สูตรจากการผลิต", "เบิกตามสูตร", "เบิกใช้จริง", "ผลต่างวัตถุดิบ", "ผลิตได้ตามสูตร", "",
				"ผลต่างสินค้าสำเร็จรูป", "หักสูญเสีย", "", "คงเหลือ" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i != 3 && i != 4 && i != 5) {
				cell.setCellStyle(thStyle);
			} else {
				cell.setCellStyle(thColor);
			}

		}

		/* tbTH2 */
		String[] tbTH2 = { "", "", "", "", "", "", "", "", "แยกตามวัตถุดิบ", "จำนวนสินค้าสำเร็จรูป", "", "เปอร์เซ็นต์",
				"จำนวน" };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			if (i > 7 && i != 10) {
				cell = row.createCell(i);
				cell.setCellValue(tbTH2[i]);
				cell.setCellStyle(thStyle);
			}
		}

		/* width */
		for (int i = 0; i < 14; i++) {
			if (i > 2) {
				sheet.setColumnWidth(i, 76 * 70);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 60);
			} else if (i == 2) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 8, 9));
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 11, 12));

		for (int i = 0; i < 14; i++) {
			if (i != 8 && i != 9 && i != 11 && i != 12) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		/* set data */
		rowNum = 2;
		cellNum = 0;
		int no = 1;
		List<ProductPaperRelationProducedGoodsVo> dataList = getDataProductPaperRelationProducedGoods(0, TOTAL, TOTAL);
		for (ProductPaperRelationProducedGoodsVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDocNo());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaterialDesc());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getInputMaterialQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getFormulaMaterialQty());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getUsedMaterialQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRealUsedMaterialQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiffMaterialQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaterialQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getGoodsQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiffGoodsQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getWasteGoodsPnt());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getWasteGoodsQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBalanceGoodsQty());
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
