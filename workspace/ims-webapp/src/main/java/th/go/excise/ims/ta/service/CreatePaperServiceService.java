package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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
import th.go.excise.ims.common.util.ExcelUtils;
import th.go.excise.ims.ta.vo.CreatePaperFormVo;
import th.go.excise.ims.ta.vo.LeftInStockServiceVo;
import th.go.excise.ims.ta.vo.MemberStatusServiceVo;
import th.go.excise.ims.ta.vo.PriceServiceVo;
import th.go.excise.ims.ta.vo.QuantityServiceVo;

@Service
public class CreatePaperServiceService {
	private static final Logger logger = LoggerFactory.getLogger(CreatePaperServiceService.class);
	

	
	
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
	public byte[] exportFileQuantityServiceVo() throws IOException {
		
		List<QuantityServiceVo> dataListexportFile = new ArrayList<QuantityServiceVo>();
		dataListexportFile = listQuantityServiceVo(0, 45, 45);
		 logger.info("Data list exportFileQuantityServiceVo {} row",dataListexportFile.size());
		 
			XSSFWorkbook workbook = ExcelUtils.setUpExcel();
	
			CellStyle fontHeader = workbook.createCellStyle();
			 /* call style from utils */
			  CellStyle thStyle = ExcelUtils.getThStyle();
			  CellStyle cellCenter = ExcelUtils.getCellCenter();
			  CellStyle cellLeft = ExcelUtils.getCellLeft();
			  CellStyle cellRight = ExcelUtils.getCellRight();
			  
			Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบด้านปริมาณ");
			int rowNum = 0;
			int cellNum = 0;
			
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			String[] tbTH1 = { "ลำดับ", "รายการ", "ใบกำกับภาษีซื้อ",
					"บัญชีประจำวัน ภส. ๐๗-๐๑", "งบเดือน (ภส. ๐๗-๐๔)","ข้อมูลจากภายนอก	","ผลต่างสูงสุด" };
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
				cell.setCellStyle(cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getList()))?detail.getList(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getServiceSlip()))?detail.getServiceSlip(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getDailyAccount()))?detail.getDailyAccount(): "" );
				
			
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getFromExamination()))?detail.getFromExamination(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getTaxForm()))?detail.getTaxForm(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
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
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			byte [] cont = null;
		    workbook.write(outByteStream);
		    cont = outByteStream.toByteArray();
			return cont;
		
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
	public ByteArrayOutputStream exportFilePriceServiceVo(PriceServiceVo formVo,  HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		List<PriceServiceVo> dataListexportFile = new ArrayList<PriceServiceVo>();
		dataListexportFile = listPriceServiceVo(0, 35, 35);
		 logger.info("Data list exportFilePriceServiceVo {} row",dataListexportFile.size());
			XSSFWorkbook workbook = ExcelUtils.setUpExcel();
			
			CellStyle fontHeader = workbook.createCellStyle();
			 /* call style from utils */
			  CellStyle thStyle = ExcelUtils.getThStyle();
			  CellStyle cellCenter = ExcelUtils.getCellCenter();
			  CellStyle cellLeft = ExcelUtils.getCellLeft();
			  CellStyle cellRight = ExcelUtils.getCellRight();
			  
			Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบด้านราคาต่อหน่วย");
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
				cell.setCellStyle(cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getList()))?detail.getList(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getTaxInvoicePrice()))?detail.getTaxInvoicePrice(): "" );
			
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getNotificationService()))?detail.getNotificationService(): "" );
			
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getFromExamination()))?detail.getFromExamination(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getTaxFilingPrice()))?detail.getTaxFilingPrice(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
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
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			return outByteStream;
		
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
	public ByteArrayOutputStream exportFileMemberStatusServiceVo(MemberStatusServiceVo formVo,  HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		List<MemberStatusServiceVo> dataListexportFile = new ArrayList<MemberStatusServiceVo>();
		dataListexportFile = listMemberStatusServiceVo(0, 35, 35);
		 logger.info("Data list exportFilePriceServiceVo {} row",dataListexportFile.size());
		 
			XSSFWorkbook workbook = ExcelUtils.setUpExcel();
			
			CellStyle fontHeader = workbook.createCellStyle();
			 /* call style from utils */
			  CellStyle thStyle = ExcelUtils.getThStyle();
			  CellStyle cellCenter = ExcelUtils.getCellCenter();
			  CellStyle cellLeft = ExcelUtils.getCellLeft();
			  CellStyle cellRight = ExcelUtils.getCellRight();
			  
			Sheet sheet = workbook.createSheet("บันทึกผลการตรวจสอบสถานะสมาชิก");
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
				cell.setCellStyle(cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getMemberCode()))?detail.getMemberCode(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getSurname()))?detail.getSurname(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getStartDate()))?detail.getStartDate(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getExpiredDate()))?detail.getExpiredDate(): "" );
				

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getCoupon()))?detail.getCoupon(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
				cell.setCellValue((StringUtils.isNotBlank(detail.getServiceDate()))?detail.getServiceDate(): "" );

				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellCenter);
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
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			return outByteStream;
		
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
	public ByteArrayOutputStream exportFileLeftInStockServiceVo(LeftInStockServiceVo formVo,  HttpServletResponse response,HttpServletRequest request) throws IOException {
		
		List<LeftInStockServiceVo> dataListexportFile = new ArrayList<LeftInStockServiceVo>();
		dataListexportFile = listLeftInStockServiceVo(0, 35, 35);
		 logger.info("Data list exportFilePriceServiceVo {} row",dataListexportFile.size());
		 
			XSSFWorkbook workbook = ExcelUtils.setUpExcel();
			
			CellStyle fontHeader = workbook.createCellStyle();
			 /* call style from utils */
			  CellStyle thStyle = ExcelUtils.getThStyle();
			  CellStyle cellCenter = ExcelUtils.getCellCenter();
			  CellStyle cellLeft = ExcelUtils.getCellLeft();
			  CellStyle cellRight = ExcelUtils.getCellRight();
			  
			Sheet sheet = workbook.createSheet("บันทึกผลการตรวจนับสินค้าคงเหลือ");
			int rowNum = 0;
			int cellNum = 0;
//			int coun = "บันทึกผลการตรวจนับสินค้าคงเหลือ".length();
//			System.out.println("555555555555555555555555555556666"+coun);
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
				cell.setCellStyle(cellCenter);
				cell.setCellValue(String.valueOf(order++));
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellLeft);
				cell.setCellValue((StringUtils.isNotBlank(detail.getList()))?detail.getList(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getAccountBalance()))?detail.getAccountBalance(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
				cell.setCellValue((StringUtils.isNotBlank(detail.getInventoryBalance()))?detail.getInventoryBalance(): "" );
				
				cell = row.createCell(cellNum++);
				cell.setCellStyle(cellRight);
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
		
			ByteArrayOutputStream outByteStream = new ByteArrayOutputStream();
			workbook.write(outByteStream);
			return outByteStream;
		}
	

	

}
	
	
	
	


