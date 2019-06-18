package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.ProductPaperBalanceMaterialVo;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;
import th.go.excise.ims.ws.vo.WsOasfri0100FromVo;
import th.go.excise.ims.ws.vo.WsOasfri0100Vo;

@Service
public class ProductPaperBalanceMaterialService extends AbstractProductPaperService<ProductPaperBalanceMaterialVo> {

	private static final Logger logger = LoggerFactory.getLogger(ProductPaperBalanceMaterialService.class);
	private static final String PRODUCT_PAPER_BALANCE_MATERIAL = "ตรวจนับวัตถุดิบคงเหลือ";;

	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;

//	public List<ProductPaperBalanceMaterialVo> readFileProductPaperBalanceMaterial(
//			ProductPaperBalanceMaterialVo request) {
//		logger.info("readFileProductPaperBalanceMaterial");
//		logger.info("fileName " + request.getFile().getOriginalFilename());
//		logger.info("type " + request.getFile().getContentType());
//
//		List<ProductPaperBalanceMaterialVo> dataList = new ArrayList<>();
//		ProductPaperBalanceMaterialVo data = null;
//
//		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()))) {
//			Sheet sheet = workbook.getSheetAt(0);
//
//			for (Row row : sheet) {
//				data = new ProductPaperBalanceMaterialVo();
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
//						// MaterialDesc
//						data.setMaterialDesc(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 2) {
//						// BalanceByAccountQty
//						data.setBalanceByAccountQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 3) {
//						// BalanceByCountQty
//						data.setBalanceByStock(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 4) {
//						// BalanceByCountQty
//						data.setBalanceByCountQty(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 5) {
//						// MaxDiffQty
//						data.setMaxDiffQty1(ExcelUtils.getCellValueAsString(cell));
//					} else if (cell.getColumnIndex() == 5) {
//						// MaxDiffQty
//						data.setMaxDiffQty2(ExcelUtils.getCellValueAsString(cell));
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
	protected List<ProductPaperBalanceMaterialVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");
		LocalDate localDateStart = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]), Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
		LocalDate localDateEnd = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]), Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));

		WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
		wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
		wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
		wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
		wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));

		List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
		List<ProductPaperBalanceMaterialVo> voList = new ArrayList<>();
		ProductPaperBalanceMaterialVo vo = null;
		for (WsOasfri0100Vo wsOasfri0100Vo : wsOasfri0100VoList) {
			vo = new ProductPaperBalanceMaterialVo();
			vo.setMaterialDesc(wsOasfri0100Vo.getDataName());
			voList.add(vo);
		}

		return voList;
	}

	@Override
	protected List<ProductPaperBalanceMaterialVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] exportData(List<ProductPaperBalanceMaterialVo> voList, String exportType) {

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_BALANCE_MATERIAL);
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

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ยอดคงเหลือ (ภส.๐๗-๐๑)", "คลังสินค้า", "ยอดคงเหลือจากการตรวจนับ", "ผลต่างยอดคงเหลือ", "ผลต่างคลังสินค้า" };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH[i]);
			if (i > 1 && i < 5) {
				cell.setCellStyle(bgKeyIn);
			} else if (i > 4) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}

			cellNum++;
		}

		/* width */
		for (int i = 0; i < 7; i++) {
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

		for (ProductPaperBalanceMaterialVo data : voList) {
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

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaxDiffQty1());
			cell.setCellStyle(cellRight);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMaxDiffQty2());
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
	public List<ProductPaperBalanceMaterialVo> upload(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void save(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getPaperPrNumberList(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

}
