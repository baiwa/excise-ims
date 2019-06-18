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
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperReduceTaxVo;

@Service
public class ProductPaperReduceTaxService extends AbstractProductPaperService<ProductPaperReduceTaxVo> {
	private static final Logger logger = LoggerFactory.getLogger(ProductPaperReduceTaxService.class);

	private static final Integer TOTAL = 17;
	private static final String PRODUCT_PAPER_REDUCE_TAX = "วัตถุดิบที่ขอลดหย่อนภาษี";

	public DataTableAjax<ProductPaperReduceTaxVo> listProductPaperReduceTax(CreatePaperFormVo request) {
		DataTableAjax<ProductPaperReduceTaxVo> dataTableAjax = new DataTableAjax<ProductPaperReduceTaxVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataProductPaperReduceTax(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<ProductPaperReduceTaxVo> getDataProductPaperReduceTax(int start, int length, int total) {
		logger.info("getDataProductPaperReduceTax");
		String desc = "วัตถุดิบที่ขอลดหย่อนภาษี";
		List<ProductPaperReduceTaxVo> datalist = new ArrayList<ProductPaperReduceTaxVo>();
		ProductPaperReduceTaxVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ProductPaperReduceTaxVo();
			data.setId(Long.valueOf(1));
			data.setMaterialDesc(desc + (i + 1));
			data.setTaxReduceAmt("1,000.00");
			data.setTaxReduceQty("100.00");
			data.setTaxReducePerUnitAmt("15.00");
			data.setBillNo("100-23-" + (i + 1));
			data.setBillTaxAmt("500.00");
			data.setBillTaxQty("100.00");
			data.setBillTaxPerUnit("200.00");
			data.setDiffTaxReduceAmt("400.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportProductPaperReduceTax() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_REDUCE_TAX);
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
		String[] tbTH1 = { "ลำดับ", "รายการวัตถุดิบ", "ขอลดหย่อนตามแบบ ภส. ๐๕-๐๓", "", "", "ใบเสร็จรับเงิน", "", "", "",
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
		String[] tbTH2 = { "", "", "จำนวนภาษี", "ปริมาณที่ใช้", "ภาษีต่อหน่วย", "เลขที่ใบเสร็จ", "ภาษีรวม", "ปริมาณ",
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
		List<ProductPaperReduceTaxVo> dataList = getDataProductPaperReduceTax(0, TOTAL, TOTAL);
		for (ProductPaperReduceTaxVo data : dataList) {
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

	public List<ProductPaperReduceTaxVo> readFileProductPaperReduceTax(ProductPaperReduceTaxVo request) {
		logger.info("readFileProductPaperReduceTax");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());
		
		List<ProductPaperReduceTaxVo> dataList = new ArrayList<>();
		ProductPaperReduceTaxVo data = null;
		
		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				 data = new ProductPaperReduceTaxVo();
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
						// TaxReduceAmt
						data.setTaxReduceAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// TaxReduceQty
						data.setTaxReduceQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// TaxReducePerUnitAmt
						data.setTaxReducePerUnitAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// BillNo
						data.setBillNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// BillTaxAmt
						data.setBillTaxAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// BillTaxQty
						data.setBillTaxQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 8) {
						// BillTaxQty
						data.setBillTaxPerUnit(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 9) {
						// DiffTaxReduceAmt
						data.setDiffTaxReduceAmt(ExcelUtils.getCellValueAsString(cell));
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
	protected List<ProductPaperReduceTaxVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");
		String desc = "วัตถุดิบที่ขอลดหย่อนภาษี";
		List<ProductPaperReduceTaxVo> datalist = new ArrayList<ProductPaperReduceTaxVo>();
		ProductPaperReduceTaxVo data = null;
		for (int i = 0; i < 10; i++) {
			data = new ProductPaperReduceTaxVo();
			data.setId(Long.valueOf(1));
			data.setMaterialDesc(desc + (i + 1));
			data.setTaxReduceAmt("1,000.00");
			data.setTaxReduceQty("100.00");
			data.setTaxReducePerUnitAmt("15.00");
			data.setBillNo("100-23-" + (i + 1));
			data.setBillTaxAmt("500.00");
			data.setBillTaxQty("100.00");
			data.setBillTaxPerUnit("200.00");
			data.setDiffTaxReduceAmt("400.00");
			datalist.add(data);
		}
		return datalist;
	}

	@Override
	protected List<ProductPaperReduceTaxVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] exportData(List<ProductPaperReduceTaxVo> voList, String exportType) {
		logger.info("exportData");
		byte[] file = null;
		try {
			file = exportProductPaperReduceTax();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	@Override
	protected List<ProductPaperReduceTaxVo> uploadData(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void saveData(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}
}
