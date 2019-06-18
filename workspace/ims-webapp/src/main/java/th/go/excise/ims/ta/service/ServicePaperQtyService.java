package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
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
import th.go.excise.ims.ta.persistence.entity.TaPaperSv01D;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv01DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv01HRepository;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperQtyVo;
import th.go.excise.ims.ws.persistence.repository.WsAnafri0001DRepository;
import th.go.excise.ims.ws.vo.WsAnafri0001Vo;

@Service
public class ServicePaperQtyService extends AbstractServicePaperService<ServicePaperQtyVo> {
	
	private static final Logger logger = LoggerFactory.getLogger(ServicePaperQtyService.class);
	
	@Autowired
	private TaPaperSv01HRepository taPaperSv01HRepository;
	@Autowired
	private TaPaperSv01DRepository taPaperSv01DRepository;
	@Autowired
	private WsAnafri0001DRepository wsAnafri0001DRepository;
	
	@Override
	protected Logger getLogger() {
		return logger;
	}
	
	@Override
	protected List<ServicePaperQtyVo> inquiryByWs(ServicePaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateStart = toLocalDate(formVo.getStartDate());
		LocalDate localDateEnd = toLocalDate(formVo.getEndDate());
		String dateStart = localDateStart.with(TemporalAdjusters.firstDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		String dateEnd = localDateEnd.with(TemporalAdjusters.lastDayOfMonth()).format(DateTimeFormatter.BASIC_ISO_DATE);
		
		List<WsAnafri0001Vo> anafri0001VoList = wsAnafri0001DRepository.findProductList(formVo.getNewRegId(), formVo.getDutyGroupId(), dateStart, dateEnd);
		
		List<ServicePaperQtyVo> voList = new ArrayList<>();
		ServicePaperQtyVo vo = null;
		for (WsAnafri0001Vo anafri0001Vo : anafri0001VoList) {
			vo = new ServicePaperQtyVo();
			vo.setGoodsDesc(anafri0001Vo.getProductName());
			vo.setServiceDocNoQty("");
			vo.setIncomeDailyAccountQty("");
			vo.setPaymentDocNoQty("");
			vo.setAuditQty("");
			vo.setGoodsQty(anafri0001Vo.getProductQty().toString());
			vo.setDiffQty("");
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<ServicePaperQtyVo> inquiryByPaperSvNumber(ServicePaperFormVo formVo) {
		logger.info("inquiryByPaperSvNumber paperSvNumber={}", formVo.getPaperSvNumber());
		
		List<TaPaperSv01D> entityList = taPaperSv01DRepository.findByPaperSvNumber(formVo.getPaperSvNumber());
		List<ServicePaperQtyVo> voList = new ArrayList<>();
		ServicePaperQtyVo vo = null;
		for (TaPaperSv01D entity : entityList) {
			vo = new ServicePaperQtyVo();
			vo.setGoodsDesc(entity.getGoodsDesc());
			vo.setServiceDocNoQty(entity.getServiceDocNoQty() != null ? entity.getServiceDocNoQty().toString() : NO_VALUE);
			vo.setIncomeDailyAccountQty(entity.getIncomeDailyAccountQty() != null ? entity.getIncomeDailyAccountQty().toString() : NO_VALUE);
			vo.setPaymentDocNoQty(entity.getPaymentDocNoQty() != null ? entity.getPaymentDocNoQty().toString() : NO_VALUE);
			vo.setAuditQty(entity.getAuditQty() != null ? entity.getAuditQty().toString() : NO_VALUE);
			vo.setGoodsQty(entity.getGoodsQty() != null ? entity.getGoodsQty().toString() : NO_VALUE);
			vo.setDiffQty(entity.getDiffQty() != null ? entity.getDiffQty().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(List<ServicePaperQtyVo> voList, String exportType) {
		logger.info("exportData");
		
		XSSFWorkbook workbook = new XSSFWorkbook();

		// call style from utils
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellRightBgStyle = ExcelUtils.createCellColorStyle(workbook, new XSSFColor(new java.awt.Color(192, 192, 192)), HorizontalAlignment.RIGHT, VerticalAlignment.TOP);
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		Sheet sheet = workbook.createSheet("กระดาษทำการตรวจสอบด้านปริมาณ");
		int rowNum = 0;
		int cellNum = 0;

		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "รายการ", "ใบรอบบริการ", "บัญชีประจำวัน " + "\n" + "แสดงรายรับของสถานบริการ " + "\n" + "(ภส.๐๗-๐๕)", "ใบนำส่งเงิน", "จากการตรวจสอบ", "แบบรายการภาษี (ภส.๐๓-๐๘)", "ผลต่าง" };

		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			if (cellNum > 1 && cellNum < 5) {
				cell.setCellStyle(bgKeyIn);				
			} else if (cellNum == 7) {
				cell.setCellStyle(bgCal);
			} else {
				cell.setCellStyle(thStyle);
			}
		};

		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 38 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 30 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);

		rowNum++;
		cellNum = 0;
		int order = 1;
		for (ServicePaperQtyVo detail : voList) {
			row = sheet.createRow(rowNum);
			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue(String.valueOf(order++));

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsDesc())) ? detail.getGoodsDesc() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getServiceDocNoQty())) ? detail.getServiceDocNoQty() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getIncomeDailyAccountQty())) ? detail.getIncomeDailyAccountQty() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRight);
			cell.setCellValue((StringUtils.isNotBlank(detail.getPaymentDocNoQty())) ? detail.getPaymentDocNoQty() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getAuditQty())) ? detail.getAuditQty() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getGoodsQty())) ? detail.getGoodsQty() : "");
			
			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellRightBgStyle);
			cell.setCellValue((StringUtils.isNotBlank(detail.getDiffQty())) ? detail.getDiffQty() : "");

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
	public List<ServicePaperQtyVo> upload(ServicePaperFormVo formVo) {
		List<ServicePaperQtyVo> dataList = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(formVo.getFile().getInputStream())) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				ServicePaperQtyVo pushdata = new ServicePaperQtyVo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} /*else if (cell.getColumnIndex() == 1) {
						pushdata.setGoodsDesc(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						pushdata.setServiceDocNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						pushdata.setIncomeDailyAccountAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						pushdata.setPaymentDocNo(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						pushdata.setAuditAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						pushdata.setTaxAmt(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 7) {
						pushdata.setDiffAmt(ExcelUtils.getCellValueAsString(cell));
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
		return taPaperSv01HRepository.findPaperSvNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}

}