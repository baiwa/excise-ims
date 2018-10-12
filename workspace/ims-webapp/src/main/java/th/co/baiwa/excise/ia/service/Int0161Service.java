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
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.ia.persistence.vo.Int0161FormVo;
import th.co.baiwa.excise.upload.service.ExcalService;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicFri6010;
import th.co.baiwa.excise.ws.entity.response.licfri6010.LicenseList6010;
import th.co.baiwa.excise.ws.entity.response.licfri6010.ResponseData6010;

@Service
public class Int0161Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	@Autowired
	private ExcalService excalService;


	public List<LicenseList6010> licFri6010(Int0161FormVo formVo){
		LicFri6010 dataLicFri6010 = new LicFri6010();
		List<LicenseList6010> licenseList6010List = new ArrayList<LicenseList6010>();
		List<LicenseList6010> licenseList6010ListYear = new ArrayList<LicenseList6010>();
		ResponseData6010 responseData6010 = new ResponseData6010();
		int pageNo = 1;
		String dataPerPage = "50";
		do {
		dataLicFri6010 = webServiceExciseService.licFri6010(formVo.getOffcode(),formVo.getYearMonthFrom(),formVo.getYearMonthTo(),Integer.toString(pageNo),dataPerPage);
		
		responseData6010 = dataLicFri6010.getResponseData();
		
		licenseList6010List.addAll(responseData6010.getLicenseList());
		logger.info("pageNo {} size {}",pageNo,responseData6010.getLicenseList().size());
		pageNo+=280;
//		}while(responseData6010.getLicenseList().size()==10000);
		}while(pageNo<282);
		
		for(int iForm = formVo.getYearForm(); iForm <= formVo.getYearTo(); iForm++) {
			for (LicenseList6010 loop1 : licenseList6010List) {
				int yyyy = (StringUtils.isNotBlank(loop1.getExpDate()))?Integer.parseInt(loop1.getExpDate().substring(0,4))+543:0;
				
				if(iForm==yyyy) {
					
					String add = "Y";
					
					for (LicenseList6010 loop2 : licenseList6010ListYear) {
						if(loop1.getLicNo().equals(loop2.getLicNo())) {
							add="N";
						}
					}
	
					if("Y".equals(add)) {
						licenseList6010ListYear.add(loop1);
					}
				}
			
			}
		}
		
		for(int iForm = formVo.getYearForm(); iForm <= formVo.getYearTo(); iForm++) {
			int index = 0;
			
			for (LicenseList6010 loop1 : licenseList6010ListYear) {
				List<String> year = new ArrayList<String>();
				String add = "Y";
					for (LicenseList6010 loop2 : licenseList6010List) {
						int yyyy = (StringUtils.isNotBlank(loop2.getExpDate()))?Integer.parseInt(loop2.getExpDate().substring(0,4))+543:0;
						if(loop1.getLicNo().equals(loop2.getLicNo())&&iForm==yyyy) {
							if(loop1.getYear() != null&&!loop1.getYear().isEmpty()) {
								year=loop1.getYear();
							}
							year.add(setDateString(loop2.getSendDate()));
							year.add(setDateString(loop2.getExpDate()));
							loop2.setYear(year);
							licenseList6010ListYear.set(index,loop2);
							add="N";
						}
					}
					if("Y".equals(add)) {
						
						if(loop1.getYear() != null&&!loop1.getYear().isEmpty()) {
							year=loop1.getYear();
						}
						year.add("");
						year.add("");
						loop1.setYear(year);
						licenseList6010ListYear.set(index,loop1);
					}
					index++;
			}
			
		}
		
		
		
		return licenseList6010ListYear;
		}
	
	public String setDateString(String dateIn) {
		String dateOut = "";
		if(StringUtils.isNotBlank(dateIn)) {
			int yyyy = Integer.parseInt(dateIn.substring(0,4))+543;
		
		dateOut = dateIn.substring(6,8)+"/"+dateIn.substring(4,6)+"/"+String.valueOf(yyyy);
		}
		
		return dateOut;
	} 
	
	public List<LicenseList6010> licFri60102(Int0161FormVo formVo){
		LicFri6010 dataLicFri6010 = new LicFri6010();
		List<LicenseList6010> licenseList6010List = new ArrayList<LicenseList6010>();
		ResponseData6010 responseData6010 = new ResponseData6010();
	
		dataLicFri6010 = webServiceExciseService.licFri6010(formVo.getOffcode(),formVo.getYearMonthFrom(),formVo.getYearMonthTo(),formVo.getPageNo(),formVo.getDataPerPage());
		
		responseData6010 = dataLicFri6010.getResponseData();
		
		licenseList6010List = responseData6010.getLicenseList();
		
		return licenseList6010List;
	}
	
	public void exportFile(Int0161FormVo formVo, HttpServletResponse response) throws IOException {
		List<LicenseList6010> licenseList6010List = new ArrayList<LicenseList6010>();
	
		licenseList6010List = licFri6010(formVo);
		logger.info("licenseList6010List {} ",licenseList6010List.size());
		
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
			cell.setCellValue("รายงานสถิติการต่ออายุใบอนุญาต");
			cell.setCellStyle(fontHeader);
			sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum, 0, 9)); //tr colspan=10
			rowNum++;rowNum++;


			String[] tbTH1 = { "ลำดับ","ผู้ประกอบการ","ประเภทใบอนุญาต"};
			row = sheet.createRow(rowNum);
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};
			 sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 0, 0)); //  rowspan=2
			 sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 1, 1)); //  rowspan=2
			 sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 2, 2)); //  rowspan=2
			
			int yearForm = formVo.getYearForm();
			int yearTo = formVo.getYearTo();
			
			cellNum=3;
			
			 for (int i = yearForm; i <= yearTo; i++) {
				 
					 cell = row.createCell(cellNum);
					 cell.setCellValue(String.valueOf(i));
					 cell.setCellStyle(thStyle);
					 
					 cell = row.createCell(cellNum+1);
					 cell.setCellValue("");
					 cell.setCellStyle(thStyle);
					 
					 sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, cellNum++, cellNum++)); // tr colspan=2
	
			    }
			 rowNum++;
			 row = sheet.createRow(rowNum);
			 cellNum=3;
			 for (int i = yearForm; i <= yearTo; i++) {

				 cell = row.createCell(cellNum++);
				 cell.setCellValue("วันที่มีผลบังคับใช้");
				 cell.setCellStyle(thStyle); 
				 
				 cell = row.createCell(cellNum++);
				 cell.setCellValue("วันที่หมดอายุ");
				 cell.setCellStyle(thStyle);  
		
		    }
			

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
				int index = 0;
				for (int i = yearForm; i <= yearTo; i++) {
					cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellCenter);cell.setCellValue(detail.getYear().get(index++));
					cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellRight );cell.setCellValue(detail.getYear().get(index++));
				}
				
				
				
				rowNum++;
				order++;
				cellNum = 0;
			}
			
			
			/*set	fileName*/		
			String fileName ="Report_Int0161_"+DateFormatUtils.format(new Date(),"yyyyMMdd");;
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
