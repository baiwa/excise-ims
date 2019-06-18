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
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperTaxAmtAdditionalVo;

@Service
public class ProductPaperTaxAmtAdditionalService extends AbstractProductPaperService<ProductPaperTaxAmtAdditionalVo> {

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
			data.setTaxAdditional("100.00");
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

		/* tbTH 1*/
		String[] tbTH = {"ลำดับ", "รายการ", "ปริมาณ", "ราคาขายปลีก", "มูลค่า", "อัตราภาษี", "ภาษีที่ต้องชำระเพิ่มเติม" , "ภาษีที่ต้องชำระเพิ่มเติม", "เบี้ยปรับ", "เงินเพิ่ม", "ภาษีเพื่อราชการส่วนท้องถิ่น", "รวม"};
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			cell.setCellStyle(thStyle);
		}
		
		/* tbTH2 */
		String[] tbTH2 = {"ตามมูลค่า", "ตามปริมาณ", };
		rowNum++;
		row = sheet.createRow(rowNum);
		for (int i = 0; i < tbTH2.length; i++) {
			cell = row.createCell(i+5);
			cell.setCellValue(tbTH2[i]);
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
		sheet.setColumnWidth(colIndex++, 28 * 256);
		
		/* merge(firstRow, lastRow, firstCol, lastCol) */
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 5, 6));
		
		for (int i = 0; i < 12; i++) {
			if (i < 5 || i > 6) {
				sheet.addMergedRegion(new CellRangeAddress(0, 1, i, i));
				cell = row.createCell(i);
				cell.setCellStyle(thStyle);
			}
		}

		/* set data */
		rowNum = 2;
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
			cell.setCellValue(data.getTaxAdditional());
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

	public List<ProductPaperTaxAmtAdditionalVo> readFileProductPaperTaxAmtAdditional(
			ProductPaperTaxAmtAdditionalVo request) {
		logger.info("readFileProductPaperUnitPriceReduceTax");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());

		List<ProductPaperTaxAmtAdditionalVo> dataList = new ArrayList<>();
		ProductPaperTaxAmtAdditionalVo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new ProductPaperTaxAmtAdditionalVo();
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
						// TaxQty
						data.setTaxQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// InformPrice
						data.setInformPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// TaxValue
						data.setTaxValue(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// TaxRateByValue
						data.setTaxRateByValue(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// TaxRateByQty
						data.setTaxRateByQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// PenaltyAmt
						data.setPenaltyAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 8) {
						// SurchargeAmt
						data.setSurchargeAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 9) {
						// MoiTaxAmt
						data.setMoiTaxAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 10) {
						// NetTaxAmt
						data.setNetTaxAmt(ExcelUtils.getCellValueAsString(cell));
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
	protected List<ProductPaperTaxAmtAdditionalVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");
//		
//		LocalDate localDateStart = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]), Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
//		LocalDate localDateEnd = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]), Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));
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
//		List<ProductPaperInputMaterialVo> voList = new ArrayList<>();
//		ProductPaperInputMaterialVo vo = null;
//		for (WsOasfri0100Vo wsOasfri0100Vo : wsOasfri0100VoList) {
//			vo = new ProductPaperInputMaterialVo();
//			vo.setMaterialDesc(wsOasfri0100Vo.getDataName());
//			vo.setMonthStatementQty(wsOasfri0100Vo.getInQty().toString());
//			voList.add(vo);
//		}
		List<ProductPaperTaxAmtAdditionalVo> datalist = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			String desc = "คำนวณภาษีที่ต้องชำระเพิ่ม";
			ProductPaperTaxAmtAdditionalVo data = new ProductPaperTaxAmtAdditionalVo();
			data = new ProductPaperTaxAmtAdditionalVo();
			data.setId(Long.valueOf(1));
			data.setGoodsDesc(desc + (i + 1));
			data.setTaxQty("1,000.00");
			data.setInformPrice("10,000.00");
			data.setTaxValue("10,000.00");
			data.setTaxRateByValue("10.00");
			data.setTaxRateByQty("1,000.00");
			data.setTaxAdditional("100.00");
			data.setPenaltyAmt("500.00");
			data.setSurchargeAmt("100.00");
			data.setMoiTaxAmt("100.00");
			data.setNetTaxAmt("22,710.00");
			datalist.add(data);
		}
		
		return datalist;
	}

	@Override
	protected List<ProductPaperTaxAmtAdditionalVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] exportData(List<ProductPaperTaxAmtAdditionalVo> voList, String exportType) {
		logger.info("exportData");
		byte[] file = null;
		try {
			file = exportProductPaperTaxAmtAdditional();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	@Override
	protected List<ProductPaperTaxAmtAdditionalVo> uploadData(ProductPaperFormVo formVo) {
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
