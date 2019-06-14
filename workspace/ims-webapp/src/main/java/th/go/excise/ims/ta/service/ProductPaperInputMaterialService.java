package th.go.excise.ims.ta.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperFormVo;
import th.go.excise.ims.ta.vo.ProductPaperInputMaterialVo;
import th.go.excise.ims.ws.persistence.repository.WsOasfri0100DRepository;
import th.go.excise.ims.ws.vo.WsOasfri0100FromVo;
import th.go.excise.ims.ws.vo.WsOasfri0100Vo;

@Service
public class ProductPaperInputMaterialService extends AbstractProductPaperService<ProductPaperInputMaterialVo> {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductPaperInputMaterialService.class);
	
	@Autowired
	private WsOasfri0100DRepository wsOasfri0100DRepository;
	
	private static final Integer TOTAL = 17;
	private static final String PRODUCT_PAPER_INPUT_MATERIAL = "ตรวจสอบการรับวัตถุดิบ";

	public DataTableAjax<ProductPaperInputMaterialVo> listProductPaperInputMaterial(CreatePaperFormVo request) {
		DataTableAjax<ProductPaperInputMaterialVo> dataTableAjax = new DataTableAjax<ProductPaperInputMaterialVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(getDataProductPaperInputMaterial(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<ProductPaperInputMaterialVo> getDataProductPaperInputMaterial(int start, int length, int total) {
		logger.info("getDataProductPaperInputMaterial");
		String desc = "ตรวจสอบรับวัตถุดิบ";
		List<ProductPaperInputMaterialVo> datalist = new ArrayList<ProductPaperInputMaterialVo>();
		ProductPaperInputMaterialVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new ProductPaperInputMaterialVo();
			data.setMaterialDesc(desc + (i + 1));
			data.setInputMaterialQty("");
			data.setDailyAccountQty("" );
			data.setMonthStatementQty("");
			data.setExternalDataQty("");
			data.setMaxDiffQty("");
			datalist.add(data);
		}
		return datalist;
	}

	public byte[] exportProductPaperInputMaterial() throws IOException {

		/* create spreadsheet */
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet(PRODUCT_PAPER_INPUT_MATERIAL);
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);

		/* call style from utils */
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle thColor = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(24, 75, 125)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		  CellStyle cellRightBgStyle = ExcelUtils.createCellColorStyle(workbook, new XSSFColor(new java.awt.Color(192, 192, 192)), HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		/* tbTH */
		String[] tbTH = { "ลำดับ", "รายการ", "ใบกำกับภาษีซื้อ", "บัญชีประจำวัน ภส. ๐๗-๐๑", "งบเดือน (ภส. ๐๗-๐๔)",
				"ข้อมูลจากภายนอก",  };
		for (int i = 0; i < tbTH.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(tbTH[i]);
			if (i > 1 && i < 5) {
				cell.setCellStyle(thColor);
			} else {
				cell.setCellStyle(thStyle);
			}
		}

		/* set sheet */
		for (int i = 0; i <= 6; i++) {
			if (i > 1) {
				sheet.setColumnWidth(i, 76 * 76);
			} else if (i == 1) {
				sheet.setColumnWidth(i, 76 * 100);
			}
		}

		/* set data */
		rowNum = 1;
		cellNum = 0;
		int no = 1;
		List<ProductPaperInputMaterialVo> dataList = getDataProductPaperInputMaterial(0, TOTAL, TOTAL);
		for (ProductPaperInputMaterialVo data : dataList) {
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
			cell.setCellValue(data.getInputMaterialQty());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getDailyAccountQty());
			cell.setCellStyle(cellCenter);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getMonthStatementQty());
			cell.setCellStyle(cellRightBgStyle);
			cellNum++;

			cell = row.createCell(cellNum);
			cell.setCellValue(data.getExternalDataQty());
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

	public List<ProductPaperInputMaterialVo> readFileProductPaperInputMaterial(ProductPaperInputMaterialVo request) {
		logger.info("readFileRawMaterialReceive");
		//logger.info("fileName " + request.getFile().getOriginalFilename());
		//logger.info("type " + request.getFile().getContentType());

		List<ProductPaperInputMaterialVo> dataList = new ArrayList<>();
		ProductPaperInputMaterialVo data = null;

		//try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()))) {
		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(null))) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				data = new ProductPaperInputMaterialVo();
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
						// InputMaterialQty
						data.setInputMaterialQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						// DailyAccountQty
						data.setDailyAccountQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						// MonthStatementQty
						data.setMonthStatementQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						// ExternalDataQty
						data.setExternalDataQty(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						// MaxDiffQty
						data.setMaxDiffQty(ExcelUtils.getCellValueAsString(cell));
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
	protected List<ProductPaperInputMaterialVo> inquiryByWs(ProductPaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]), Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
		LocalDate localDateEnd = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]), Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));
		
		WsOasfri0100FromVo wsOasfri0100FormVo = new WsOasfri0100FromVo();
		wsOasfri0100FormVo.setNewRegId(formVo.getNewRegId());
		wsOasfri0100FormVo.setDutyGroupId(formVo.getDutyGroupId());
		wsOasfri0100FormVo.setDataType(WEB_SERVICE.OASFRI0100.DATA_TYPE_MATERIAL);
		wsOasfri0100FormVo.setYearMonthStart(localDateStart.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setYearMonthEnd(localDateEnd.format(DateTimeFormatter.ofPattern("yyyyMM")));
		wsOasfri0100FormVo.setAccountName(WEB_SERVICE.OASFRI0100.PS0704_ACC05);
		
		List<WsOasfri0100Vo> wsOasfri0100VoList = wsOasfri0100DRepository.findByCriteria(wsOasfri0100FormVo);
		List<ProductPaperInputMaterialVo> voList = new ArrayList<>();
		ProductPaperInputMaterialVo vo = null;
		for (WsOasfri0100Vo wsOasfri0100Vo : wsOasfri0100VoList) {
			vo = new ProductPaperInputMaterialVo();
			vo.setMaterialDesc(wsOasfri0100Vo.getDataName());
			vo.setMonthStatementQty(wsOasfri0100Vo.getInQty().toString());
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<ProductPaperInputMaterialVo> inquiryByPaperPrNumber(ProductPaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected byte[] exportData(List<ProductPaperInputMaterialVo> voList, String exportType) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
