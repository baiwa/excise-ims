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

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.vo.Int0151FormVo;
import th.co.baiwa.excise.upload.service.ExcalService;
import th.co.baiwa.excise.ws.WebServiceExciseService;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncFri8020;
import th.co.baiwa.excise.ws.entity.response.incfri8020.IncomeList;
import th.co.baiwa.excise.ws.entity.response.incfri8020.ResponseData;

@Service
public class Int0151Service {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	@Autowired
	private ExcalService excalService;
	
	public List<IncomeList> licFri8020(Int0151FormVo formVo){
		IncFri8020 dataLicFri8020 = new IncFri8020();
		List<IncomeList> licenseList8020List = new ArrayList<IncomeList>();
		List<IncomeList> licenseList8020ListReturn = new ArrayList<IncomeList>();
		ResponseData responseData8020 = new ResponseData();
	
		dataLicFri8020 = webServiceExciseService.IncFri8020(formVo.getOfficeCode(),formVo.getYearMonthFrom(),formVo.getYearMonthTo(),formVo.getDateType(),formVo.getPageNo(),formVo.getDataPerPage());
		
		responseData8020 = dataLicFri8020.getResponseData();
		
		licenseList8020List = responseData8020.getIncomeList();
		
		
//		Group By IncomeCode
		for (IncomeList loop1 : licenseList8020List) {
				String add = "Y";
				
				
//				Check IncomeCode Repeatedly 
//				for (IncomeList loop2 : licenseList8020ListReturn) {
//					if(loop1.getIncomeCode().equals(loop2.getIncomeCode())) {
//						add="N";
//					}
//				}

				if("Y".equals(add)) {
					licenseList8020ListReturn.add(loop1);
				}
			
		
		}


		 	int yearForm = Integer.parseInt(formVo.getYearMonthFrom().substring(0,4));
		 	int yearTo   = Integer.parseInt(formVo.getYearMonthTo().substring(0,4));
		 	int yearNo   = yearTo - yearForm;

		 	int monthForm = Integer.parseInt(formVo.getYearMonthFrom().substring(4,6));
		 	int monthTo   = Integer.parseInt(formVo.getYearMonthTo().substring(4,6));

		 	int monthNo = 0 ;
		    if(yearTo>yearForm){
		      monthNo = (12-monthForm)+(12*((yearTo-yearForm)-1))+monthTo;
		    }else{
		      monthNo = monthTo-monthForm;
		    }
		   int index = 0;
		   
//			Set dataGruop
		   
		    for (IncomeList loop1 : licenseList8020ListReturn) {
		    	List<String> dataGruop = new ArrayList<String>();
		    	int yearFormLoop = yearForm;
		    	int monthArray = monthForm-1;
				    for (int i = 0; i <= monthNo; i++) {
				      if(monthArray<12){ 
//				    	    dataGruop.add(Integer.toString(monthArray+1));
//							dataGruop.add("");
//							loop1.setDataGruop(dataGruop);
//							licenseList8020ListReturn.set(index,loop1);
				    	String add = "Y";
				    	
								for (IncomeList loop2 : licenseList8020List) {
									int yyyy = (StringUtils.isNotBlank(loop2.getReceiptDate()))?Integer.parseInt(loop2.getReceiptDate().substring(0,4)):0;
									int mm = (StringUtils.isNotBlank(loop2.getReceiptDate()))?Integer.parseInt(loop2.getReceiptDate().substring(4,6)):0;
									if(loop1.getIncomeCode().equals(loop2.getIncomeCode())&&yearFormLoop+543==yyyy&&monthArray+1==mm) {
										if(loop1.getDataGruop() != null&&!loop1.getDataGruop().isEmpty()) {
											dataGruop=loop1.getDataGruop();
										}
										dataGruop.add(setDateString(loop2.getReceiptDate()));
										dataGruop.add(loop2.getNettaxAmount());
										loop2.setDataGruop(dataGruop);
										licenseList8020ListReturn.set(index,loop2);
										add="N";
									}
								}
								if("Y".equals(add)) {
									
									if(loop1.getDataGruop() != null&&!loop1.getDataGruop().isEmpty()) {
										dataGruop=loop1.getDataGruop();
									}
									dataGruop.add("");
									dataGruop.add("");
									loop1.setDataGruop(dataGruop);
									licenseList8020ListReturn.set(index,loop1);
								}
					
						
				      }else{
				    	monthArray=0;
				    	yearFormLoop++;
//			    	    dataGruop.add(Integer.toString(yearFormLoop));
//						dataGruop.add("");
//						loop1.setDataGruop(dataGruop);
//						licenseList8020ListReturn.set(index,loop1);
				    	
				    	String add = "Y";
				    	
					    	for (IncomeList loop2 : licenseList8020List) {
								int yyyy = (StringUtils.isNotBlank(loop2.getReceiptDate()))?Integer.parseInt(loop2.getReceiptDate().substring(0,4)):0;
								int mm = (StringUtils.isNotBlank(loop2.getReceiptDate()))?Integer.parseInt(loop2.getReceiptDate().substring(4,6)):0;
								
								if(loop1.getIncomeCode().equals(loop2.getIncomeCode())&&yearFormLoop+543==yyyy&&monthArray+1==mm) {
									if(loop1.getDataGruop() != null&&!loop1.getDataGruop().isEmpty()) {
										dataGruop=loop1.getDataGruop();
									}
									dataGruop.add(setDateString(loop2.getReceiptDate()));
									dataGruop.add(loop2.getNettaxAmount());
									loop2.setDataGruop(dataGruop);
									licenseList8020ListReturn.set(index,loop2);
									add="N";
								}
							}
							if("Y".equals(add)) {
								
								if(loop1.getDataGruop() != null&&!loop1.getDataGruop().isEmpty()) {
									dataGruop=loop1.getDataGruop();
								}
								dataGruop.add("");
								dataGruop.add("");
								loop1.setDataGruop(dataGruop);
								licenseList8020ListReturn.set(index,loop1);
							}
				      }
				      monthArray++;
				    }

				    index++;
			}
		    
		   
		    
		return licenseList8020ListReturn;
	}
	
	public String setDateString(String dateIn) {
		String dateOut = "";
		if(StringUtils.isNotBlank(dateIn)) {
		
		dateOut = dateIn.substring(6)+"/"+dateIn.substring(4,6)+"/"+dateIn.substring(0,4);
		}
		
		return dateOut;
	}
	
	public void exportFile(Int0151FormVo formVo, HttpServletResponse response) throws IOException {
		List<IncomeList> incomeList8020List = new ArrayList<IncomeList>();
	
		incomeList8020List = licFri8020(formVo);
		logger.info("incomeList8020List {} ",incomeList8020List.size());
		
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
			cell.setCellValue("รายงานสถิติการชำระภาษี");
			cell.setCellStyle(fontHeader);
			sheet.addMergedRegion(new CellRangeAddress(rowNum,rowNum, 0, 9)); //tr colspan=10
			rowNum++;rowNum++;


			String[] tbTH1 = { "ลำดับ","ผู้ประกอบการ","ชือรายได้"};
			row = sheet.createRow(rowNum);
			for (cellNum = 0; cellNum < tbTH1.length; cellNum++) {
				cell = row.createCell(cellNum);
				cell.setCellValue(tbTH1[cellNum]);
				cell.setCellStyle(thStyle);
			};
			 sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 0, 0)); //  rowspan=2
			 sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 1, 1)); //  rowspan=2
			 sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum+1, 2, 2)); //  rowspan=2
		
			 	int yearForm = Integer.parseInt(formVo.getYearMonthFrom().substring(0,4));
			 	int yearTo   = Integer.parseInt(formVo.getYearMonthTo().substring(0,4));
			 	int yearNo   = yearTo - yearForm;

			 	int monthForm = Integer.parseInt(formVo.getYearMonthFrom().substring(4,6));
			 	int monthTo   = Integer.parseInt(formVo.getYearMonthTo().substring(4,6));

			 	int monthNo = 0 ;
			    if(yearTo>yearForm){
			      monthNo = (12-monthForm)+(12*((yearTo-yearForm)-1))+monthTo;
			    }else{
			      monthNo = monthTo-monthForm;
			    }
			    cellNum=3;
		    	int monthArray = monthForm-1;
				    for (int i = 0; i <= monthNo; i++) {
				      if(monthArray<12){  
				    	 cell = row.createCell(cellNum);
						 cell.setCellValue(DateConstant.getMonthTextTH(monthArray));
						 cell.setCellStyle(thStyle);
						 
						 cell = row.createCell(cellNum+1);
						 cell.setCellValue("");
						 cell.setCellStyle(thStyle);
						 
						 sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, cellNum++, cellNum++)); // tr colspan=2
				      }else{
					    monthArray=0;
					     cell = row.createCell(cellNum);
						 cell.setCellValue(DateConstant.getMonthTextTH(monthArray));
						 cell.setCellStyle(thStyle);
						 
						 cell = row.createCell(cellNum+1);
						 cell.setCellValue("");
						 cell.setCellStyle(thStyle);
						 
						 sheet.addMergedRegion(new CellRangeAddress(rowNum, rowNum, cellNum++, cellNum++)); // tr colspan=2
					  }
				      	monthArray++;
				    }
			
			 
			 rowNum++;
			 row = sheet.createRow(rowNum);
			 cellNum=3;
			 for (int i = 0; i <= monthNo; i++) {

				 cell = row.createCell(cellNum++);
				 cell.setCellValue("วันที่ชำระ");
				 cell.setCellStyle(thStyle);	
				 
				 cell = row.createCell(cellNum++);
				 cell.setCellValue("ยอดภาษี");
				 cell.setCellStyle(thStyle);
		
		    }
			

			/* Detail */
//			List<LicenseList8020> exportDataList = null;
			rowNum++;
			cellNum = 0;
			int order = 1;
			for (IncomeList detail : incomeList8020List) {
				row = sheet.createRow(rowNum);
				// No.
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellCenter);cell.setCellValue(String.valueOf(order));
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft  );cell.setCellValue("ผู้ประกอบการ"+order);
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft  );cell.setCellValue((StringUtils.isNotBlank(detail.getIncomeName()))?detail.getIncomeName(): "" );
				int index = 0;
				for (int i = 0; i <= monthNo; i++) {
					cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellCenter);cell.setCellValue(detail.getDataGruop().get(index++));
					cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellCenter);cell.setCellValue(detail.getDataGruop().get(index++));
				}
				
				
				
				rowNum++;
				order++;
				cellNum = 0;
			}
			
			
			/*set	fileName*/		
			String fileName ="Report_Int0151_"+DateFormatUtils.format(new Date(),"yyyyMMdd");;
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
