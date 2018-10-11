package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.vo.Int0171FormVo;
import th.co.baiwa.excise.upload.service.ExcalService;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicFri6010;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicenseList6010;

@Service
public class Int0171Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	@Autowired
	private ExcalService excalService;

	public DataTableAjax<LicenseList6010> licFri6010(Int0171FormVo formVo){
		
		LicFri6010 dataLicFri6010 = new LicFri6010();
		List<LicenseList6010> licenseList6010List = new ArrayList<LicenseList6010>();
		DataTableAjax<LicenseList6010> dataTableAjax= new DataTableAjax<>();
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
		dataLicFri6010 = webServiceExciseService.licFri6010(formVo.getOffcode(),formVo.getYearMonthFrom(),formVo.getYearMonthTo(),formVo.getPageNo(),formVo.getDataPerPage());
		
		licenseList6010List = dataLicFri6010.getResponseData().getLicenseList();
		
			dataTableAjax.setData(licenseList6010List);
		}
		return dataTableAjax;
	}
	
	public String setDateString(String dateIn) {
		String dateOut = "";
		if(StringUtils.isNotBlank(dateIn)) {
			int yyyy = Integer.parseInt(dateIn.substring(0,4))+543;
		
		dateOut = dateIn.substring(6,8)+"/"+dateIn.substring(4,6)+"/"+String.valueOf(yyyy);
		}
		
		return dateOut;
	} 
	public void exportFile(Int0171FormVo formVo, HttpServletResponse response) throws IOException {

		LicFri6010 dataLicFri6010 = new LicFri6010();
		List<LicenseList6010> licenseList6010List = new ArrayList<LicenseList6010>();
	
		dataLicFri6010 = webServiceExciseService.licFri6010(formVo.getOffcode(),formVo.getYearMonthFrom(),formVo.getYearMonthTo(),formVo.getPageNo(),formVo.getDataPerPage());
		
		licenseList6010List = dataLicFri6010.getResponseData().getLicenseList();
		
//		dataTestList = formVo.getDataT();
		
			/* create spreadsheet */
			XSSFWorkbook workbook = excalService.setUpExcel();
			CellStyle thStyle = excalService.thStyle;

			CellStyle fontHeader = workbook.createCellStyle();
			fontHeader.setFont(excalService.fontHeader);
			
			Sheet sheet = workbook.createSheet();
			int rowNum = 0;
			int cellNum = 0;
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			logger.info("Creating excel");
			
			 
			/* create data spreadsheet */

			/* Header */
//			String[] tbTH1 = formVo.getTrHtml1();
			
			row = sheet.createRow(rowNum);
			cell = row.createCell(0);
			cell.setCellValue("รายงานสถิติการพิมพ์ใบอนุญาตซ้ำ");
			cell.setCellStyle(fontHeader);
			sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum, 0, 9)); //tr colspan=10
			rowNum++;rowNum++;


			String[] tbTH1 = { "ลำดับ","ผู้ประกอบการ","ประเภทใบอนุญาต","แบบพิมพ์สรรพสามิต","เลขใบอนุญาต","จำนวนครั้งที่พิมพ์ซ้ำ"};
			row = sheet.createRow(rowNum);
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};

			/* Detail */
//			List<LicenseList6010> exportDataList = null;
			rowNum++;
			cellNum = 0;
			int order = 1;
			for (LicenseList6010 detail : licenseList6010List) {
				row = sheet.createRow(rowNum);
				// No.
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellCenter);cell.setCellValue(String.valueOf(order));
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft  );cell.setCellValue((StringUtils.isNotBlank(detail.getCusFullName()))?detail.getCusFullName(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft  );cell.setCellValue((StringUtils.isNotBlank(detail.getLicName()))?detail.getLicName(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellCenter);cell.setCellValue((StringUtils.isNotBlank(detail.getLicCode()))?detail.getLicCode(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellCenter);cell.setCellValue((StringUtils.isNotBlank(detail.getLicType()))?detail.getLicType(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellRight );cell.setCellValue((StringUtils.isNotBlank(detail.getPrintCount()))?detail.getPrintCount(): "" );
				
				
				rowNum++;
				order++;
				cellNum = 0;
			}
			
			
			/*set	fileName*/		
			String fileName ="Report_Int0171_"+DateFormatUtils.format(new Date(),"yyyyMMdd");;
			logger.info(fileName);
			
			/* write it as an excel attachment */
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
			
			logger.info("Done");
		}

	
}
