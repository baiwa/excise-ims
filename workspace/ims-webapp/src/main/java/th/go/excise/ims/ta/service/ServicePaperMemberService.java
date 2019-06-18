package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
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

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperSv03D;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv03DRepository;
import th.go.excise.ims.ta.persistence.repository.TaPaperSv03HRepository;
import th.go.excise.ims.ta.vo.ServicePaperFormVo;
import th.go.excise.ims.ta.vo.ServicePaperMemberVo;

@Service
public class ServicePaperMemberService extends AbstractServicePaperService<ServicePaperMemberVo> {

	private static final Logger logger = LoggerFactory.getLogger(ServicePaperMemberService.class);
	
	private static final String NO_VALUE = "-";
	
	@Autowired
	private TaPaperSv03HRepository taPaperSv03HRepository;
	@Autowired
	private TaPaperSv03DRepository taPaperSv03DRepository;

	public byte[] exportFileMemberStatusServiceVo(List<ServicePaperMemberVo> voList, String exportType) throws IOException {
		logger.info("Data list exportFilePriceServiceVo {} row", voList.size());

		XSSFWorkbook workbook = new XSSFWorkbook();

		// call style from utils
		CellStyle thStyle = ExcelUtils.createThCellStyle(workbook);
		CellStyle bgKeyIn = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(91, 241, 218)));
		CellStyle bgCal = ExcelUtils.createThColorStyle(workbook, new XSSFColor(new java.awt.Color(251, 189, 8)));
		CellStyle cellCenter = ExcelUtils.createCenterCellStyle(workbook);
		CellStyle cellLeft = ExcelUtils.createLeftCellStyle(workbook);
		CellStyle cellRight = ExcelUtils.createRightCellStyle(workbook);

		Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบสถานะสมาชิก");
		int rowNum = 0;
		int cellNum = 0;

		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		String[] tbTH1 = { "ลำดับ", "รหัสสมาชิก", "ชื่อ-สกุล", "วันเริ่มต้น", "วันหมดอายุ	", "คูปอง", "วันที่ใช้บริการ", "สถานะการเป็นสมาชิก" };
		row = sheet.createRow(rowNum);
		int colIndex = 0;
		sheet.setColumnWidth(colIndex++, 10 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 25 * 256);
		sheet.setColumnWidth(colIndex++, 23 * 256);

		for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
			cell = row.createCell(cellNum);
			cell.setCellValue(tbTH1[cellNum]);
			cell.setCellStyle(thStyle);
//			if (cellNum > 1 && cellNum < 3) {
//				cell.setCellStyle(bgKeyIn);				
//			} else if (cellNum == 6) {
//				cell.setCellStyle(bgCal);
//			} else {
//				cell.setCellStyle(thStyle);
//			}
		}
		;
		rowNum++;
		cellNum = 0;
		int order = 1;
		for (ServicePaperMemberVo detail : voList) {
			row = sheet.createRow(rowNum);

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue(String.valueOf(order++));

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberCode())) ? detail.getMemberCode() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellLeft);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberFullName())) ? detail.getMemberFullName() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberStartDate())) ? detail.getMemberStartDate() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberEndDate())) ? detail.getMemberEndDate() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberCoupon())) ? detail.getMemberCoupon() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberUsedDate())) ? detail.getMemberUsedDate() : "");

			cell = row.createCell(cellNum++);
			cell.setCellStyle(cellCenter);
			cell.setCellValue((StringUtils.isNotBlank(detail.getMemberStatus())) ? detail.getMemberStatus() : "");

			rowNum++;
			cellNum = 0;
		}

		ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
		byte[] cont = null;
		workbook.write(outByteStream);
		cont = outByteStream.toByteArray();
		return cont;
	}
	/*
	public List<ServicePaperMemberVo> readFileServicePaperMemberVo(ServicePaperMemberVo request) {
		logger.info("readFileServicePaperMemberVo");
		logger.info("fileName " + request.getFile().getOriginalFilename());
		logger.info("type " + request.getFile().getContentType());
		List<ServicePaperMemberVo> dataList = new ArrayList<>();

		try (Workbook workbook = WorkbookFactory.create(new ByteArrayInputStream(request.getFile().getBytes()));) {
			Sheet sheet = workbook.getSheetAt(0);

			for (Row row : sheet) {
				ServicePaperMemberVo pushdata = new ServicePaperMemberVo();
				// Skip on first row
				if (row.getRowNum() == 0) {
					continue;
				}
				for (Cell cell : row) {
					if (cell.getColumnIndex() == 0) {
						// Column No.
						continue;
					} else if (cell.getColumnIndex() == 1) {
						pushdata.setMemberCode(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 2) {
						pushdata.setMemberFullName(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 3) {
						pushdata.setMemberStartDate(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 4) {
						pushdata.setMemberEndDate(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 5) {
						pushdata.setMemberCoupon(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						pushdata.setMemberUsedDate(ExcelUtils.getCellValueAsString(cell));
					} else if (cell.getColumnIndex() == 6) {
						pushdata.setMemberStatus(ExcelUtils.getCellValueAsString(cell));
					}

				}
				dataList.add(pushdata);
			}

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataList;
	}*/

	@Override
	protected List<ServicePaperMemberVo> inquiryByWs(ServicePaperFormVo formVo) {
		logger.info("inquiryByWs");
		
		List<ServicePaperMemberVo> voList = new ArrayList<>();
		
		return voList;
	}

	@Override
	protected List<ServicePaperMemberVo> inquiryByPaperSvNumber(ServicePaperFormVo formVo) {
		logger.info("inquiryByPaperSvNumber paperSvNumber={}", formVo.getPaperSvNumber());
		
		List<TaPaperSv03D> entityList = taPaperSv03DRepository.findByPaperSvNumber(formVo.getPaperSvNumber());
		List<ServicePaperMemberVo> voList = new ArrayList<>();
		ServicePaperMemberVo vo = null;
		for (TaPaperSv03D entity : entityList) {
			vo = new ServicePaperMemberVo();
			vo.setMemberCode(entity.getMemberCode());
			vo.setMemberFullName(entity.getMemberFullName() != null ? entity.getMemberFullName().toString() : NO_VALUE);
			vo.setMemberStartDate(entity.getMemberStartDate() != null ? ConvertDateUtils.formatLocalDateToString(entity.getMemberStartDate(), ConvertDateUtils.DD_MMM_YYYY_SPAC, ConvertDateUtils.LOCAL_TH) : NO_VALUE);
			vo.setMemberEndDate(entity.getMemberEndDate() != null ? ConvertDateUtils.formatLocalDateToString(entity.getMemberEndDate(), ConvertDateUtils.DD_MMM_YYYY_SPAC, ConvertDateUtils.LOCAL_TH) : NO_VALUE);
			vo.setMemberCoupon(entity.getMemberCoupon() != null ? entity.getMemberCoupon().toString() : NO_VALUE);
			vo.setMemberUsedDate(entity.getMemberUsedDate() != null ? ConvertDateUtils.formatLocalDateToString(entity.getMemberUsedDate(), ConvertDateUtils.DD_MMM_YYYY_SPAC, ConvertDateUtils.LOCAL_TH) : NO_VALUE);
			vo.setMemberStatus(entity.getMemberStatus() != null ? entity.getMemberStatus().toString() : NO_VALUE);
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected byte[] exportData(List<ServicePaperMemberVo> voList, String exportType) {
		logger.info("exportData");
		byte[] file = null;
		try {
			file = exportFileMemberStatusServiceVo(voList, exportType);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	@Override
	protected List<ServicePaperMemberVo> uploadData(ServicePaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void saveData(ServicePaperFormVo formVo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected List<String> getPaperSvNumberList(ServicePaperFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

}
