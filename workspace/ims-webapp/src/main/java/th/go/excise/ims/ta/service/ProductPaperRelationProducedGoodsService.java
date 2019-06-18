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

import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperRelationProducedGoodsVo;

@Service
public class ProductPaperRelationProducedGoodsService extends AbstractProductPaperService<ProductPaperRelationProducedGoodsVo> {
	private static final Logger logger = LoggerFactory.getLogger(ProductPaperRelationProducedGoodsService.class);
	private static final String PRODUCT_PAPER_RELATION_PRODUCED_GOODS = "ความสัมพันธ์การเบิกใช้วัตถุดิบ";

//	public List<ProductPaperRelationProducedGoodsVo> readFileProductPaperRelationProducedGoods(
//			ProductPaperRelationProducedGoodsVo request) {
//		logger.info("readFileProductPaperRelationProducedGoods");
//		logger.info("fileName " + request.getFile().getOriginalFilename());
//		logger.info("type " + request.getFile().getContentType());
//
//		List<ProductPaperRelationProducedGoodsVo> dataList = new ArrayList<>();
//		ProductPaperRelationProducedGoodsVo data = null;
//
//		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()))) {
//			Sheet sheet = workbook.getSheetAt(0);
//
//			for (Row row : sheet) {
//				data = new ProductPaperRelationProducedGoodsVo();
//				// Skip on first row
//				if (row.getRowNum() == 0) {
//					continue;
//				}
//				for (Cell cell : row) {
//
//					if (cell.getColumnIndex() == 0) {
//						// Column No.
//						continue;
//					} else if (cell.getColumnIndex() == 1) {
//						// DocNo
//						data.setDocNo(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 2) {
//						// MaterialDesc
//						data.setMaterialDesc(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 3) {
//						// InputMaterialQty
//						data.setInputMaterialQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 4) {
//						// FormulaMaterialQty
//						data.setFormulaMaterialQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 5) {
//						// UsedMaterialQty
//						data.setUsedMaterialQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 6) {
//						// RealUsedMaterialQty
//						data.setRealUsedMaterialQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 7) {
//						// DiffMaterialQty
//						data.setDiffMaterialQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 8) {
//						// MaterialQty
//						data.setMaterialQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 9) {
//						// GoodsQty
//						data.setGoodsQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 9) {
//						// DiffGoodsQty
//						data.setDiffGoodsQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 10) {
//						// WasteGoodsPnt
//						data.setWasteGoodsPnt(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 11) {
//						// WasteGoodsQty
//						data.setWasteGoodsQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 12) {
//						// BalanceGoodsQty
//						data.setBalanceGoodsQty(ExcelUtils.getCellValueAsString(cell));
//					}
//				}
//				dataList.add(data);
//			}
//
//		} catch (Exception e) {
//			logger.error(e.getMessage(), e);
//		}
//
//		return dataList;
//	}

	@Override
	protected List<ProductPaperRelationProducedGoodsVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");
		List<ProductPaperRelationProducedGoodsVo> voList = new ArrayList<>();
		ProductPaperRelationProducedGoodsVo vo = new ProductPaperRelationProducedGoodsVo();
		voList.add(vo);
		return voList;
	}

	@Override
	protected List<ProductPaperRelationProducedGoodsVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] exportData(List<ProductPaperRelationProducedGoodsVo> voList, String exportType) {
		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_RELATION_PRODUCED_GOODS);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH1 */
		String[] tbTH1 = { "ลำดับ", "เลขที่ใบสำคัญ", "รายการ", "จำนวนรับ" + "\n" + "(ตามบัญชี ภส. ๐๗-๐๒)", "สูตรจากการผลิต", "เบิกตามสูตร", "เบิกใช้จริง", "ผลต่างวัตถุดิบ", "ผลิตได้ตามสูตร", "", "ผลต่างสินค้าสำเร็จรูป", "หักสูญเสีย", "", "คงเหลือ" };
		for (int i = 0; i < tbTH1.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH1[i]);
			if (i == 0) {
				cell.setCellStyle(thStyle);
			} else if (i >= 1 && i <= 4 || i == 6) {
				cell.setCellStyle(bgKeyIn);
			} else if (i == 5 || i == 7) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}
		}

		/* tbTH2 */
		String[] tbTH2 = { "", "", "", "", "", "", "", "", "แยกตามวัตถุดิบ", "จำนวนสินค้าสำเร็จรูป", "", "เปอร์เซ็นต์", "จำนวน" };
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

		for (ProductPaperRelationProducedGoodsVo data : voList) {
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

	@Override
	protected List<ProductPaperRelationProducedGoodsVo> uploadData(ProductPaperFormVo formVo) {
		logger.info("readFileProductPaperRelationProducedGoods");
		logger.info("fileName " + formVo.getFile().getOriginalFilename());
		logger.info("type " + formVo.getFile().getContentType());

		List<ProductPaperRelationProducedGoodsVo> dataList = new ArrayList<>();
		ProductPaperRelationProducedGoodsVo data = null;

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(formVo.getFile().getBytes()))) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new ProductPaperRelationProducedGoodsVo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {

					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						// DocNo
						data.setDocNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						// MaterialDesc
						data.setMaterialDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// InputMaterialQty
						data.setInputMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// FormulaMaterialQty
						data.setFormulaMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// UsedMaterialQty
						data.setUsedMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// RealUsedMaterialQty
						data.setRealUsedMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						// DiffMaterialQty
						data.setDiffMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 8) {
						// MaterialQty
						data.setMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 9) {
						// GoodsQty
						data.setGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 9) {
						// DiffGoodsQty
						data.setDiffGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 10) {
						// WasteGoodsPnt
						data.setWasteGoodsPnt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 11) {
						// WasteGoodsQty
						data.setWasteGoodsQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 12) {
						// BalanceGoodsQty
						data.setBalanceGoodsQty(ExcelUtils.getCellValueAsString(cell));
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
