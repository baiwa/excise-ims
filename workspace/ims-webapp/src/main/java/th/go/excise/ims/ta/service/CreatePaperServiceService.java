package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ia.util.ExcalUtil;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.LeftInStockServiceVo;
import th.go.excise.ims.ta.vo.MemberStatusServiceVo;
import th.go.excise.ims.ta.vo.PriceServiceVo;
import th.go.excise.ims.ta.vo.QuantityServiceVo;

@Service
public class CreatePaperServiceService {
	private static final Logger logger = LoggerFactory.getLogger(CreatePaperServiceService.class);
	
	@Autowired
	private ExcalUtil excalUtil;
	
	
	public DataTableAjax<QuantityServiceVo> GetQuantityServiceVo(CreatePaperFormVo request) {
		 int total = 45;
		DataTableAjax<QuantityServiceVo> dataTableAjax = new DataTableAjax<QuantityServiceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listQuantityServiceVo(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}
	public List<QuantityServiceVo> listQuantityServiceVo(int start,int length,int total) {

		List<QuantityServiceVo> datalist = new ArrayList<QuantityServiceVo>();
		String excise = "กรมสรรพสามิตภาคที่";
		QuantityServiceVo data = null;
		for (int i = start; i<(start+length); i++) {
			if(i >= total){
				break;
			}
			data = new QuantityServiceVo();
			data.setList(excise+i);
			data.setServiceSlip("1500.00");
			data.setDailyAccount("3000.00");
			data.setPaymentSlip("4000.00");
			data.setFromExamination("5000.00");
			data.setTaxForm("6000.00");
			data.setDifference("7000.00");
			datalist.add(data);
		}
		return datalist;
	}
	public void exportFileQuantityServiceVo(QuantityServiceVo formVo,  HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		List<QuantityServiceVo> dataListexportFile = new ArrayList<QuantityServiceVo>();
		dataListexportFile = listQuantityServiceVo(0, 45, 45);
		 logger.info("Data list exportFileQuantityServiceVo {} row",dataListexportFile.size());
		 
			XSSFWorkbook workbook = excalUtil.setUpExcel();
			CellStyle thStyle = excalUtil.thStyle;
			CellStyle fontHeader = workbook.createCellStyle();
			fontHeader.setFont(excalUtil.fontHeader);
			Sheet sheet = workbook.createSheet();
			int rowNum = 0;
			int cellNum = 0;
			
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			String[] tbTH1 = { "ลำดับ", "รายการ", "ใบรอบบริการ", "บัญชีประจำวัน แสดงรายรับของสถานบริการ ภส. ๐๗-๐๕",
					"ใบนำส่งเงิน", "จากการตรวจสอบ","แบบรายการภาษี ภส. ๐๓-๐๘","ผลต่าง" };
			row = sheet.createRow(rowNum);
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};
			rowNum++;
			cellNum = 0;
			int order = 1;
			for (QuantityServiceVo detail : dataListexportFile) {
				row = sheet.createRow(rowNum);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getList()))?detail.getList(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getServiceSlip()))?detail.getServiceSlip(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getDailyAccount()))?detail.getDailyAccount(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getPaymentSlip()))?detail.getDailyAccount(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getFromExamination()))?detail.getFromExamination(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getTaxForm()))?detail.getTaxForm(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getDifference()))?detail.getDifference(): "" );
				
				rowNum++;
				cellNum = 0;
			}
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, 10 * 256);
			sheet.setColumnWidth(colIndex++, 38 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			sheet.setColumnWidth(colIndex++, 55 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			sheet.setColumnWidth(colIndex++,25 * 256);
			sheet.setColumnWidth(colIndex++, 30 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			/*set	fileName*/		
			String fileName ="TeamMate_"+DateFormatUtils.format(new Date(),"yyyyMMdd");
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			byte [] outArray = outByteStream.toByteArray();
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xlsx");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
		
		}
	
	
	// MethodPriceServiceVo
	
	public DataTableAjax<PriceServiceVo> GetPriceServiceVo(CreatePaperFormVo request) {
		int total = 35;
		DataTableAjax<PriceServiceVo> dataTableAjax = new DataTableAjax<PriceServiceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listPriceServiceVo(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<PriceServiceVo> listPriceServiceVo(int start,int length, int total) {
		String excise = "กรมสรรพสามิตภาคที่";
		List<PriceServiceVo> datalist = new ArrayList<PriceServiceVo>();

		PriceServiceVo data = null;
		for (int i = start;i<(start+length);i++) {
			if(i >= total){
				break;
			}
			data = new PriceServiceVo();
			data.setList(excise+i);
			data.setTaxInvoicePrice("3000.00");
			data.setNotificationService("2300.00");
			data.setFromExamination("6500.00");
			data.setTaxFilingPrice("7500.00");
			data.setDifference("8500.00");
			datalist.add(data);

		}
		return datalist;
	}
	public void exportFilePriceServiceVo(PriceServiceVo formVo,  HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		List<PriceServiceVo> dataListexportFile = new ArrayList<PriceServiceVo>();
		dataListexportFile = listPriceServiceVo(0, 35, 35);
		 logger.info("Data list exportFilePriceServiceVo {} row",dataListexportFile.size());
		 
			XSSFWorkbook workbook = excalUtil.setUpExcel();
			CellStyle thStyle = excalUtil.thStyle;
			CellStyle fontHeader = workbook.createCellStyle();
			fontHeader.setFont(excalUtil.fontHeader);
			Sheet sheet = workbook.createSheet();
			int rowNum = 0;
			int cellNum = 0;
			
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			String[] tbTH1 = { "ลำดับ", "รายการ", "ราคาตามใบกำกับภาษี", "ราคาบริการตามแบบแจ้ง",
					"จากการตรวจสอบ", "ราคาที่ยื่นชำระภาษี","ผลต่าง" };
			row = sheet.createRow(rowNum);
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};
			rowNum++;
			cellNum = 0;
			int order = 1;
			for (PriceServiceVo detail : dataListexportFile) {
				row = sheet.createRow(rowNum);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getList()))?detail.getList(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getTaxInvoicePrice()))?detail.getTaxInvoicePrice(): "" );
			
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getNotificationService()))?detail.getNotificationService(): "" );
			
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getFromExamination()))?detail.getFromExamination(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getTaxFilingPrice()))?detail.getTaxFilingPrice(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getDifference()))?detail.getDifference(): "" );
				
				rowNum++;
				cellNum = 0;
			}
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, 10 * 256);
			sheet.setColumnWidth(colIndex++, 38 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			sheet.setColumnWidth(colIndex++,25 * 256);
			sheet.setColumnWidth(colIndex++, 30 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			/*set	fileName*/		
			String fileName ="TeamMate_"+DateFormatUtils.format(new Date(),"yyyyMMdd");
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			byte [] outArray = outByteStream.toByteArray();
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xlsx");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
		
		}
	//Done
	
	
	//methodMemberStatusServiceVo
	public DataTableAjax<MemberStatusServiceVo> GetMemberStatusServiceVo(CreatePaperFormVo request) {
		int total = 35;
		DataTableAjax<MemberStatusServiceVo> dataTableAjax = new DataTableAjax<MemberStatusServiceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listMemberStatusServiceVo(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<MemberStatusServiceVo> listMemberStatusServiceVo(int start,int length,int total) {
		String code = "1012520";
		List<MemberStatusServiceVo> datalist = new ArrayList<MemberStatusServiceVo>();
		MemberStatusServiceVo data = null;
		for(int i = start; i <(start+length);i++){
			if(i >= total){
				break;
			}
			data = new MemberStatusServiceVo();
			data.setMemberCode(code+i);
			data.setSurname("ธนพล ชัยภูมิ");
			data.setStartDate("15/02/2561");
			data.setExpiredDate("16/06/2563");
			data.setCoupon("05264");
			data.setServiceDate("01/05/2563");
			data.setMembershipStatus("VIP");
			datalist.add(data);
		}
		

		return datalist;
	}
	public void exportFileMemberStatusServiceVo(MemberStatusServiceVo formVo,  HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		List<MemberStatusServiceVo> dataListexportFile = new ArrayList<MemberStatusServiceVo>();
		dataListexportFile = listMemberStatusServiceVo(0, 35, 35);
		 logger.info("Data list exportFilePriceServiceVo {} row",dataListexportFile.size());
		 
			XSSFWorkbook workbook = excalUtil.setUpExcel();
			CellStyle thStyle = excalUtil.thStyle;
			CellStyle fontHeader = workbook.createCellStyle();
			fontHeader.setFont(excalUtil.fontHeader);
			Sheet sheet = workbook.createSheet();
			int rowNum = 0;
			int cellNum = 0;
			
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			String[] tbTH1 = { "ลำดับ", "รหัสสมาชิก", "ชื่อ-สกุล", "วันเริ่มต้น",
					"วันหมดอายุ	", "คูปอง","วันที่ใช้บริการ","สถานะการเป็นสมาชิก" };
			row = sheet.createRow(rowNum);
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};
			rowNum++;
			cellNum = 0;
			int order = 1;
			for (MemberStatusServiceVo detail : dataListexportFile) {
				row = sheet.createRow(rowNum);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMemberCode()))?detail.getMemberCode(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getSurname()))?detail.getSurname(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getStartDate()))?detail.getStartDate(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getExpiredDate()))?detail.getExpiredDate(): "" );
				

				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getCoupon()))?detail.getCoupon(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getServiceDate()))?detail.getServiceDate(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMembershipStatus()))?detail.getMembershipStatus(): "" );
				
				rowNum++;
				cellNum = 0;
			}
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, 10 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++,25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 23 * 256);
			/*set	fileName*/		
			String fileName ="TeamMate_"+DateFormatUtils.format(new Date(),"yyyyMMdd");
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			byte [] outArray = outByteStream.toByteArray();
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xlsx");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
		
		}
	

	public DataTableAjax<LeftInStockServiceVo> GetLeftInStockServiceVo( CreatePaperFormVo request) {
		int total = 35;
		DataTableAjax<LeftInStockServiceVo> dataTableAjax = new DataTableAjax<LeftInStockServiceVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listLeftInStockServiceVo(request.getStart(),request.getLength(),total));
		dataTableAjax.setRecordsTotal(total);
		dataTableAjax.setRecordsFiltered(total);
		return dataTableAjax;
	}

	public List<LeftInStockServiceVo> listLeftInStockServiceVo(int start,int length,int total) {
		String excise = "กรมสรรพสามิตภาคที่";
		
		List<LeftInStockServiceVo> datalist = new ArrayList<LeftInStockServiceVo>();
		LeftInStockServiceVo data = null;
		for(int i = start;i<(start+length);i++){
			if(i >= total){
				break;
			}
			data = new LeftInStockServiceVo();
			data.setList(excise+i);
			data.setAccountBalance("30000.00");
			data.setInventoryBalance("25000.00");
			data.setDifference("3000.00");
			datalist.add(data);
		}
		

		return datalist;
	}
	public void exportFileLeftInStockServiceVo(LeftInStockServiceVo formVo,  HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		List<LeftInStockServiceVo> dataListexportFile = new ArrayList<LeftInStockServiceVo>();
		dataListexportFile = listLeftInStockServiceVo(0, 35, 35);
		 logger.info("Data list exportFilePriceServiceVo {} row",dataListexportFile.size());
		 
			XSSFWorkbook workbook = excalUtil.setUpExcel();
			CellStyle thStyle = excalUtil.thStyle;
			CellStyle fontHeader = workbook.createCellStyle();
			fontHeader.setFont(excalUtil.fontHeader);
			Sheet sheet = workbook.createSheet();
			int rowNum = 0;
			int cellNum = 0;
			
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			String[] tbTH1 = { "ลำดับ", "รายการ", "ยอดคงเหลือตามบัญชี", "ยอดสินค้าคงเหลือจากการตรวจนับ",
					"ผลต่าง"};
			row = sheet.createRow(rowNum);
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};
			rowNum++;
			cellNum = 0;
			int order = 1;
			for (LeftInStockServiceVo detail : dataListexportFile) {
				row = sheet.createRow(rowNum);
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getList()))?detail.getList(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getAccountBalance()))?detail.getAccountBalance(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getInventoryBalance()))?detail.getInventoryBalance(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(excalUtil.cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getDifference()))?detail.getDifference(): "" );
				
				rowNum++;
				cellNum = 0;
			}
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, 10 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 35 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			
			/*set	fileName*/		
			String fileName ="TeamMate_"+DateFormatUtils.format(new Date(),"yyyyMMdd");
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			byte [] outArray = outByteStream.toByteArray();
			response.setContentType("application/vnd.ms-excel");
			response.setContentLength(outArray.length);
			response.setHeader("Expires:", "0"); // eliminates browser caching
			response.setHeader("Content-Disposition", "attachment; filename="+fileName+".xlsx");
			OutputStream outStream = response.getOutputStream();
			outStream.write(outArray);
			outStream.flush();
			outStream.close();
		
		}
	

	

}
	
	
	
	


