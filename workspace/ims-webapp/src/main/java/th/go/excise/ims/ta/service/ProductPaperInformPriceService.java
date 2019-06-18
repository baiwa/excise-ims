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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr09D;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr09DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperPr09HRepository;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperInformPriceVo;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;

@Service
public class ProductPaperInformPriceService extends AbstractProductPaperService<ProductPaperInformPriceVo> {
	private static final Logger logger = LoggerFactory.getLogger(ProductPaperInformPriceService.class);

	private static final Integer TOTAL = 17;
	private static final String PRODUCT_PAPER_IN_FORM_PRICE = "ตรวจสอบด้านราคา";
	private static final String NO_VALUE = "-";
	
	@Autowired
	private TaPaperPr09HRepository taPaperPr09HRepository;
	@Autowired
	private TaPaperPr09DRepository taPaperPr09DRepository;
	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;

	public DataTableAjax<ProductPaperInformPriceVo> listProductPaperInformPrice(CreatePaperFormVo request) {
		DataTableAjax<ProductPaperInformPriceVo> dataTableAjax = new DataTableAjax<ProductPaperInformPriceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataProductPaperInformPrice(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<ProductPaperInformPriceVo> getDataProductPaperInformPrice(int start, int length, int total) {
		logger.info("getDataProductPaperInformPrice");
		String desc = "ตรวจสอบด้านราคา";
		List<ProductPaperInformPriceVo> datalist = new ArrayList<ProductPaperInformPriceVo>();
		ProductPaperInformPriceVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ProductPaperInformPriceVo();
			data.setGoodsDesc(desc + (i + 1));
			data.setInformPrice("1,000.00");
			data.setExternalPrice("1,500.00");
			data.setDeclarePrice("1,400.00");
			data.setRetailPrice("1,400.00");
			data.setTaxPrice("1,000.00");
			data.setDiffPrice("100.00");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportProductPaperInformPrice() {

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_IN_FORM_PRICE);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH */
		String[] tbTH = { "ลำดับ", "ชื่อสินค้า (ภส.๐๓-๐๗)", "ราคาตามแบบแจ้ง ภส. ๐๒-๐๑", "ราคาจากข้อมูลภายนอก",
				"ราคาต่อหน่วยตามประกาศกรม", "ราคาขายปลีกแนะนำจาก ภส. ๐๓-๐๗", "ผลต่างราคา" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i > 2 && i < 4) {
				cell.setCellStyle(bgKeyIn);				
			} else if (i == 6) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}

		}

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
//		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<ProductPaperInformPriceVo> dataList = getDataProductPaperInformPrice(0, TOTAL, TOTAL);
		for (ProductPaperInformPriceVo data : dataList) {
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
			cell.setCellValue(data.getInformPrice());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getExternalPrice());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDeclarePrice());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getRetailPrice());
			cell.setCellStyle(cellRight);
			cellNum++;

//			cell = row.createCell(cellNum);
//			cell.setCellValue(data.getTaxPrice());
//			cell.setCellStyle(cellRight);
//			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDiffPrice());
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

	/*public List<ProductPaperInformPriceVo> readFileProductPaperInformPrice(ProductPaperInformPriceVo request) {
		logger.info("readFileProductPaperInformPrice");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());

		List<ProductPaperInformPriceVo> dataList = new ArrayList<>();
		ProductPaperInformPriceVo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new ProductPaperInformPriceVo();
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
						// InformPrice
						data.setInformPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// ExternalPrice
						data.setExternalPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// DeclarePrice
						data.setDeclarePrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// RetailPrice
						data.setRetailPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// TaxPrice
						data.setTaxPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// DiffPrice
						data.setDiffPrice(ExcelUtils.getCellValueAsString(cell));
					}

				}
				dataList.add(data);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataList;
	}*/

	@Override
	protected List<ProductPaperInformPriceVo> inquiryByWs(ProductPaperFormVo formVo) {
		List<ProductPaperInformPriceVo> datalist = new ArrayList<>();
		ProductPaperInformPriceVo data = null;
		String desc = "ตรวจสอบด้านราคา";
		for (int i = 0; i < 5; i++) {
			data = new ProductPaperInformPriceVo();
			data.setGoodsDesc(desc + (i + 1));
			data.setInformPrice("1,000.00");
			data.setExternalPrice("1,500.00");
			data.setDeclarePrice("1,400.00");
			data.setRetailPrice("1,400.00");
			data.setTaxPrice("1,000.00");
			data.setDiffPrice("100.00");
			datalist.add(data);
		}
		return datalist;
	}

	@Override
	protected List<ProductPaperInformPriceVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		logger.info("inquiryByPaperPrNumber paperPrNumber={}", formVo.getPaperPrNumber());
		
		List<TaPaperPr09D> entityList = taPaperPr09DRepository.findByPaperPrNumber(formVo.getPaperPrNumber());
		List<ProductPaperInformPriceVo> voList = new ArrayList<>();
		ProductPaperInformPriceVo vo = null;
		for (TaPaperPr09D entity : entityList) {
			vo = new ProductPaperInformPriceVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setInformPrice(entity.getInformPrice() != null ? entity.getInformPrice().toString() : NO_VALUE);
			vo.setExternalPrice(entity.getExternalPrice() != null ? entity.getExternalPrice().toString() : NO_VALUE);
			vo.setDeclarePrice(entity.getDeclarePrice() != null ? entity.getDeclarePrice().toString() : NO_VALUE);
			vo.setRetailPrice(entity.getRetailPrice() != null ? entity.getRetailPrice().toString() : NO_VALUE);
			vo.setTaxPrice(entity.getTaxPrice() != null ? entity.getTaxPrice().toString() : NO_VALUE);
			vo.setDiffPrice(entity.getDiffPrice() != null ? entity.getDiffPrice().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(List<ProductPaperInformPriceVo> voList, String exportType) {
		logger.info("exportData");
		byte[] file = exportProductPaperInformPrice();
		return file;
	}

	@Override
	public List<ProductPaperInformPriceVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("readFileProductPaperInformPrice");
		logger.info("fileName " + formVo.getFile().getOriginalFilename());
		logger.info("type " + formVo.getFile().getContentType());

		List<ProductPaperInformPriceVo> dataList = new ArrayList<>();
		ProductPaperInformPriceVo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new ProductPaperInformPriceVo();
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
						// InformPrice
						data.setInformPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// ExternalPrice
						data.setExternalPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// DeclarePrice
						data.setDeclarePrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// RetailPrice
						data.setRetailPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// TaxPrice
						data.setTaxPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// DiffPrice
						data.setDiffPrice(ExcelUtils.getCellValueAsString(cell));
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
	protected void saveData(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

}
