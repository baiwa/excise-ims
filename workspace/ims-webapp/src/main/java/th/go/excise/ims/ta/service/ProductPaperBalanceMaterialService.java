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
import th.go.excise.ims.ta.vo.ProductPaperBalanceMaterialVo;

@Service
public class ProductPaperBalanceMaterialService {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperBalanceMaterialService.class);

	private static final Integer TOTAL = 17;
	private static final String PRODUCT_PAPER_BALANCE_MATERIAL = "ตรวจนับวัตถุดิบคงเหลือ";;

	// TODO MaterialBalance
	public DataTableAjax<ProductPaperBalanceMaterialVo> listProductPaperBalanceMaterial(CreatePaperFormVo request) {
		DataTableAjax<ProductPaperBalanceMaterialVo> dataTableAjax = new DataTableAjax<ProductPaperBalanceMaterialVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataProductPaperBalanceMaterial(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<ProductPaperBalanceMaterialVo> getDataProductPaperBalanceMaterial(int start, int length, int total) {
		logger.info("getDataRawMaterialBalance");
		String desc = "ตรวจนับวัตถุดิบคงเหลือ";
		List<ProductPaperBalanceMaterialVo> datalist = new ArrayList<ProductPaperBalanceMaterialVo>();
		ProductPaperBalanceMaterialVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ProductPaperBalanceMaterialVo();
			data.setId(Long.valueOf(1));
			data.setMaterialDesc(desc + (i + 1));
			data.setBalanceByAccountQty("");
			data.setBalanceByStock("");
			data.setBalanceByCountQty("");
			data.setMaxDiffQty1("");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportProductPaperBalanceMaterial() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_BALANCE_MATERIAL);
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

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ยอดคงเหลือ (ภส.๐๗-๐๑)", "คลังสินค้า",
				"ยอดคงเหลือจากการตรวจนับ" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH[i]);
			if (i == 2) {
				cell.setCellStyle(thColor);
			} else {
				cell.setCellStyle(thStyle);
			}

			cellNum++;
		}

		/* width */
		for (int i = 0; i < 5; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 100);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 150);
			}
		}

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<ProductPaperBalanceMaterialVo> dataList = getDataProductPaperBalanceMaterial(0, TOTAL, TOTAL);
		for (ProductPaperBalanceMaterialVo data : dataList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum);
			cell.setCellValue(no);
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaterialDesc());
			cell.setCellStyle(cellLeft);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBalanceByAccountQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBalanceByStock());
			cell.setCellStyle(cellRight);
			cellNum++;
			
			cell = row.createCell(cellNum);
			cell.setCellValue(data.getBalanceByCountQty());
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

	public List<ProductPaperBalanceMaterialVo> readFileProductPaperBalanceMaterial(
			ProductPaperBalanceMaterialVo request) {
		logger.info("readFileProductPaperBalanceMaterial");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());

		List<ProductPaperBalanceMaterialVo> dataList = new ArrayList<>();
		ProductPaperBalanceMaterialVo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()))) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new ProductPaperBalanceMaterialVo();
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
						// BalanceByAccountQty
						data.setBalanceByAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// BalanceByCountQty
						data.setBalanceByStock(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// BalanceByCountQty
						data.setBalanceByCountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// MaxDiffQty
						data.setMaxDiffQty1(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
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

}
