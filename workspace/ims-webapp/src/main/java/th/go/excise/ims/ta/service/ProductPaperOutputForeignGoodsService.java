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
import th.go.excise.ims.ta.vo.ProductPaperOutputForeignGoodsVo;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperUnitPriceReduceTaxVo;

@Service
public class ProductPaperOutputForeignGoodsService {
	private static final Logger logger = LoggerFactory.getLogger(ProductPaperOutputForeignGoodsService.class);

	private static final Integer TOTAL = 17;
	private static final String PRODUCT_PAPER_OUTPUT_FOREIGN_GOODS = "จ่ายสินค้าสำเร็จรูปต่างประเทศ";

	public DataTableAjax<ProductPaperOutputForeignGoodsVo> listProductPaperOutputForeignGoods(CreatePaperFormVo request) {
		DataTableAjax<ProductPaperOutputForeignGoodsVo> dataTableAjax = new DataTableAjax<ProductPaperOutputForeignGoodsVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataProductPaperOutputForeignGoods(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<ProductPaperOutputForeignGoodsVo> getDataProductPaperOutputForeignGoods(int start, int length, int total) {
		logger.info("getDataPayForeignFinishedGoods");
		String desc = "จ่ายสินค้าสำเร็จรูปต่างประเทศ";
		List<ProductPaperOutputForeignGoodsVo> datalist = new ArrayList<ProductPaperOutputForeignGoodsVo>();
		ProductPaperOutputForeignGoodsVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ProductPaperOutputForeignGoodsVo();
			data.setId(Long.valueOf(1));
			data.setGoodsDesc(desc + (i + 1));
			data.setCargoDocNo("100-222-22" + (i + 1));
			data.setInvoice("GT-00" + (i + 1));
			data.setOutputDailyAccountQty("TS00" + (i + 1));
			data.setOutputMonthStatementQty("1,000.00");
			data.setOutputAuditQty("900.00");
			data.setTaxReduceQty("TS00+G" + (i + 1));
			data.setDiffOutputQty("100.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportPayForeignFinishedGoods() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_OUTPUT_FOREIGN_GOODS);
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
		String[] tbTH = { "ลำดับ", "รายการ", "ใบขนสินค้า", "INV", "ภส. ๐๗-๐๒", "งบเดือน ภส. ๐๗-๐๔", "จากการตรวจสอบ	",
				"ภส. ๐๕-๐๑", "ผลต่าง" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i != 2 && i != 3 && i != 6) {
				cell.setCellStyle(thStyle);
			} else {
				cell.setCellStyle(thColor);
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
		List<ProductPaperOutputForeignGoodsVo> dataList = getDataProductPaperOutputForeignGoods(0, TOTAL, TOTAL);
		for (ProductPaperOutputForeignGoodsVo data : dataList) {
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
			cell.setCellValue(data.getCargoDocNo());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getInvoice());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getOutputDailyAccountQty());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getOutputMonthStatementQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getOutputAuditQty());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getTaxReduceQty());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiffOutputQty());
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
	 public List<ProductPaperOutputForeignGoodsVo> readFileProductPaperOutputForeignGoods(ProductPaperOutputForeignGoodsVo request) {
		  logger.info("readFileProductPaperUnitPriceReduceTax");
		  logger.info("fileName "+request.getFile().getOriginalFilename());
		  logger.info("type "+request.getFile().getContentType());
		  List<ProductPaperOutputForeignGoodsVo> dataList = new ArrayList<>();
		  
		  try(Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));){
				Sheet sheet = workbook.getSheetAt(0);
				
				   for (Row row : sheet) {
					   ProductPaperOutputForeignGoodsVo pushdata = new ProductPaperOutputForeignGoodsVo();
					    // Skip on first row
					    if (row.getRowNum() == 0) {
					     continue;
					    } 
					    for (Cell cell : row) {
					     if (cell.getColumnIndex() == 0) {
					      // Column No.
					    	 continue;
					     } else if (cell.getColumnIndex() == 1) {
					    	 pushdata.setGoodsDesc(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex()== 2){
					    	 pushdata.setCargoDocNo(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex()== 3){
					    	 pushdata.setInvoice(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex() == 4 ){
					    	 pushdata.setOutputDailyAccountQty(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex() == 5){
					    	 pushdata.setOutputMonthStatementQty(ExcelUtils.getCellValueAsString(cell));
					     } else if (cell.getColumnIndex() == 6){
					    	 pushdata.setOutputAuditQty(ExcelUtils.getCellValueAsString(cell));
					     }else if (cell.getColumnIndex() == 7){
					    	 pushdata.setTaxReduceQty(ExcelUtils.getCellValueAsString(cell));
					     }else if (cell.getColumnIndex() == 8){
					    	 pushdata.setDiffOutputQty(ExcelUtils.getCellValueAsString(cell));
					     }
					     
					    }
						   dataList.add(pushdata);
					   }
			
				 
		  }catch(Exception e){
			  logger.error(e.getMessage(),e);
		  }
		  return dataList;
		 }
}
