package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperSv02D;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv02DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv02HRepository;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperPricePerUnitVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class ServicePaperPricePerUnitService extends AbstractServicePaperService<ServicePaperPricePerUnitVo> {

	private static final Logger logger = LoggerFactory.getLogger(ServicePaperPricePerUnitService.class);
	
	@Autowired
	private TaPaperSv02HRepository taPaperSv02HRepository;
	@Autowired
	private TaPaperSv02DRepository taPaperSv02DRepository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;

	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	@Override
	protected List<ServicePaperPricePerUnitVo> inquiryByWs(ServicePaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]), Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
		LocalDate localDateEnd = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]), Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<ServicePaperPricePerUnitVo> voList = new ArrayList<>();
		ServicePaperPricePerUnitVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new ServicePaperPricePerUnitVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setInvoicePrice("");
			vo.setInformPrice("");
			vo.setAuditPrice("");
			vo.setGoodsPrice(anafri0001Vo.getProductPrice().toString());
			vo.setDiffPrice("");
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<ServicePaperPricePerUnitVo> inquiryByPaperSvNumber(ServicePaperFormVo formVo) {
		logger.info("inquiryByPaperSvNumber paperSvNumber={}", formVo.getPaperSvNumber());
		
		List<TaPaperSv02D> entityList = taPaperSv02DRepository.findByPaperSvNumber(formVo.getPaperSvNumber());
		List<ServicePaperPricePerUnitVo> voList = new ArrayList<>();
		ServicePaperPricePerUnitVo vo = null;
		for (TaPaperSv02D entity : entityList) {
			vo = new ServicePaperPricePerUnitVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setInvoicePrice(entity.getInvoicePrice() != null ? entity.getInvoicePrice().toString() : NO_VALUE);
			vo.setInformPrice(entity.getInformPrice() != null ? entity.getInformPrice().toString() : NO_VALUE);
			vo.setAuditPrice(entity.getAuditPrice() != null ? entity.getAuditPrice().toString() : NO_VALUE);
			vo.setGoodsPrice(entity.getGoodsPrice() != null ? entity.getGoodsPrice().toString() : NO_VALUE);
			vo.setDiffPrice(entity.getDiffPrice() != null ? entity.getDiffPrice().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(List<ServicePaperPricePerUnitVo> voList, String exportType) {
		logger.info("exportData");
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		// call style from utils
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellRightBgStyle = ExcelUtils.createCellColorStyle(workbook, new XSSFColor(new java.awt.Color(192, 192, 192)), HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบด้านราคาต่อหน่วย");
		int rowNum = 0;
		int cellNum = 0;

		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "รายการ", "ราคาตามใบกำกับภาษี", "ราคาบริการตามแบบแจ้ง", "จากการตรวจสอบ", "ราคาที่ยื่นชำระภาษี", "ผลต่าง" };
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);

		row = sheet.createRow(rowNum);
		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			if (cellNum > 1 && cellNum < 3) {
				cell.setCellStyle(bgKeyIn);				
			} else if (cellNum == 6) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}
		}
		;
		rowNum++;
		cellNum = 0;
		int order = 1;
		for (ServicePaperPricePerUnitVo detail : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue(String.valueOf(order++));

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsDesc())) ? detail.getGoodsDesc() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getInvoicePrice())) ? detail.getInvoicePrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getInformPrice())) ? detail.getInformPrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getAuditPrice())) ? detail.getAuditPrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsPrice())) ? detail.getGoodsPrice() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getDiffPrice())) ? detail.getDiffPrice() : "");
			
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
	public List<ServicePaperPricePerUnitVo> upload(ServicePaperFormVo formVo) {
		List<ServicePaperPricePerUnitVo> dataList = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(formVo.getFile().getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				ServicePaperPricePerUnitVo pushdata = new ServicePaperPricePerUnitVo();
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
					} else if (cell.getColumnIndex() == 2) {
						pushdata.setInvoicePrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						pushdata.setInformPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						pushdata.setAuditPrice(ExcelUtils.getCellValueAsString(cell));
					} /*else if (cell.getColumnIndex() == 5) {
						pushdata.setTaxPrice(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						pushdata.setDiffPrice(ExcelUtils.getCellValueAsString(cell));
					}*/

				}
				dataList.add(pushdata);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataList;
	}

	@Override
	public void save(ServicePaperFormVo formVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> getPaperSvNumberList(ServicePaperFormVo formVo) {
		return taPaperSv02HRepository.findPaperSvNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}
	
}
