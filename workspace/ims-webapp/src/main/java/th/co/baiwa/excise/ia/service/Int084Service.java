package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
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

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.cop.persistence.vo.Cop0711FormVo;
import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ia.persistence.dao.IaIncomeExciseAudDao;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAud;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAudDtl;
import th.co.baiwa.excise.ia.persistence.repository.IncomeExciseAudDtlRepository;
import th.co.baiwa.excise.ia.persistence.vo.Int084FormVo;
import th.co.baiwa.excise.ia.persistence.vo.Int084Vo;
import th.co.baiwa.excise.upload.service.ExcalService;
import th.co.baiwa.excise.utils.BeanUtils;

@Service
public class Int084Service {
	private static Logger log = LoggerFactory.getLogger(Int084Service.class);
	
	@Autowired
	private IaIncomeExciseAudDao iaIncomeExciseAudDao;
	
	@Autowired
	private ExcalService excalService;
	
	@Autowired
	private IncomeExciseAudService incomeExciseAudService;
	
	@Autowired
	private IncomeExciseAudDtlRepository incomeExciseAudDtlRepository;
	

	public DataTableAjax<Int084Vo> findAll(Int084FormVo formVo) {
		
		DataTableAjax<Int084Vo> dataTableAjax = new DataTableAjax<>();
		
		List<Int084Vo> list = new ArrayList<Int084Vo>();
		List<Int084Vo> listReturn = new ArrayList<Int084Vo>();
		long count = 0l;
		
		// query data
		if ("TRUE".equalsIgnoreCase(formVo.getSearchFlag())) {
			Long id = IncFri8020(formVo);
			List<IncomeExciseAudDtl> incomeExciseAudDtlList = incomeExciseAudDtlRepository.findByIaIncomeExciseAudId(id);
			
			for (IncomeExciseAudDtl incomeExciseAudDtl : incomeExciseAudDtlList) {
				String add = "Y";
				Int084Vo dataDtl = new Int084Vo();
				for (Int084Vo int084Vo : list) {
					if(incomeExciseAudDtl.getOfficeCode().equals(int084Vo.getOfficeCode())) {
						 add = "N";
					}
					
				}
				
				if("Y".equals(add)) {
					Long billNo = 0l;
					Long billNo2 = 0l;
					Long billWaste = 0l;
					Long billAll = 0l;
					Float riskSum = 0f;
					
					for (IncomeExciseAudDtl incomeExciseAudDtl2 : incomeExciseAudDtlList) {
						if(incomeExciseAudDtl2.getOfficeCode().equals(incomeExciseAudDtl.getOfficeCode())) {
							billAll++;
							String bill = (!BeanUtils.isEmpty(incomeExciseAudDtl2.getReceiptNo()))?incomeExciseAudDtl2.getReceiptNo().split("/")[1]:"0";
							if(NumberUtils.isNumber(bill)) {
							if(billNo==0l&&billNo2==0l) {
								billNo = Long.valueOf((!BeanUtils.isEmpty(incomeExciseAudDtl2.getReceiptNo()))?incomeExciseAudDtl2.getReceiptNo().split("/")[1]:"0");
							}else {
								billNo2 = Long.valueOf((!BeanUtils.isEmpty(incomeExciseAudDtl2.getReceiptNo()))?incomeExciseAudDtl2.getReceiptNo().split("/")[1]:"0");
								//log.info("OfficeCode : {} billNo : {} billNo2 : {} ",incomeExciseAudDtl.getOfficeCode(),billNo,billNo2);
								
								if(billNo2!=0&&billNo2>billNo) {
									Long sum = (billNo2-1)-billNo;
									billWaste +=sum;
									billNo = billNo2;
								}
							}
							}
						}
					}
					billAll+=billWaste;
					dataDtl.setIdHead(id);
					dataDtl.setOfficeCode(incomeExciseAudDtl.getOfficeCode());
					List<Lov> officeCodeList = ApplicationCache.getListOfValueByValueType("SECTOR_LIST", incomeExciseAudDtl.getOfficeCode());
					dataDtl.setOfficeName((officeCodeList.size()!=0)?officeCodeList.get(0).getSubTypeDescription():"");
					dataDtl.setStartDate(formVo.getStartDate());
					dataDtl.setStartDate(formVo.getStartDate());
					dataDtl.setEndDate(formVo.getEndDate());
					dataDtl.setBillAll(billAll.toString());
					dataDtl.setBillWaste(billWaste.toString());
					dataDtl.setRiskNumber("1");
					dataDtl.setRiskScore("1");
					
					Float per = (Float.valueOf(billWaste)/Float.valueOf(billAll))*100f;
					String perS = String.format("%.02f", per);
					dataDtl.setRiskPersen(perS);
					dataDtl.setRiskRemark("จำนวนใบเสร็จเสีย "+perS+" %");
					
					list.add(dataDtl);
					count++;
				}
			
			}
			
			List<Int084Vo> list2 = new ArrayList<Int084Vo>();
			for (Int084Vo int084Vo : list) {
				if(!BeanUtils.isEmpty(int084Vo.getOfficeName())){
					
					list2.add(int084Vo);
					
				}
			}
			// set data table
		
			// dataTableAjax.setDraw(formVo.getDraw() + 1);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);
			
			if("1".equals(formVo.getSector())) {
				
				Collections.sort(list2, new Comparator<Int084Vo>() {
					@Override
					public int compare(final Int084Vo object1, final Int084Vo object2) {
						return object1.getOfficeCode().compareTo(object2.getOfficeCode());
					}
				});
				dataTableAjax.setData(list2);
			}else {
				for (Int084Vo int084Vo : list2) {
					
//					if(Float.valueOf(int084Vo.getRiskPersen())>=Float.valueOf(formVo.getBillLost())&&int084Vo.getOfficeName()!=""){
					String sec = (!BeanUtils.isEmpty(int084Vo.getOfficeCode()))?int084Vo.getOfficeCode().substring(0, 2):"0";
					if(formVo.getSector().equals(sec)){
						
						listReturn.add(int084Vo);
						
					}
				}
				Collections.sort(listReturn, new Comparator<Int084Vo>() {
					@Override
					public int compare(final Int084Vo object1, final Int084Vo object2) {
						return object1.getOfficeCode().compareTo(object2.getOfficeCode());
					}
				});
				dataTableAjax.setData(listReturn);
			}
			
			
		}
		
		

		return dataTableAjax;
	}
	
	
	public Long save(List<Int084Vo> int084VoList) throws ParseException {
		Long id = 0l;
		
		Long count = iaIncomeExciseAudDao.countSaveInt084(int084VoList.get(0).getIdHead());
		log.info(" save data ID : {}",int084VoList.get(0).getIdHead());
		log.info(" save count : {}",count);
		if(count==0) {
			
			for (Int084Vo int084Vo : int084VoList) {
				iaIncomeExciseAudDao.saveDataInt084(int084Vo);
			}
		}
		
		return id ;
	}
	
	public Long IncFri8020(Int084FormVo formVo) {
		IncomeExciseAud incomeExciseAud = new IncomeExciseAud();
		
		String startDate = formVo.getStartDateTM().split("/")[2]+formVo.getStartDateTM().split("/")[0];
		String endDate = formVo.getEndDateTM().split("/")[2]+formVo.getEndDateTM().split("/")[0];
		
		incomeExciseAud.setStartMonth(startDate);
		incomeExciseAud.setEndMonth(endDate);
		
		incomeExciseAud = incomeExciseAudService.createIncomeExciseAud(incomeExciseAud);
		if(BeanUtils.isNotEmpty(incomeExciseAud)) {
			log.info("IaIncomeExciseAudId : {}",incomeExciseAud.getIaIncomeExciseAudId());
		}else {
			log.info("insert Fail.");
		}
		return incomeExciseAud.getIaIncomeExciseAudId();

	}
	
 
	
	public void exportFile(Int084FormVo formVo, HttpServletResponse response) throws IOException {
		List<Int084Vo> dataList084 = new ArrayList<Int084Vo>();
		
		dataList084 = iaIncomeExciseAudDao.findAllInt084(formVo);
		 log.info("Data list exportFile {} row",dataList084.size());
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
			log.info("Creating excel");
			
			 
			/* create data spreadsheet */

			/* Header */
//			String[] tbTH1 = formVo.getTrHtml1();
			

			String[] tbTH1 = {"Title","Code","Group","Type","Location","Scope","Risk",
					"Origin","StaffType","EstStartDate","EstEndDate","BudgetHours",
					"RiskScore","InherentScore","ResidualScore","EstCostResource",
					"EstCostExternal","EstCostExpense","EstResources","Chargeable",
					"Objective","BackGround","PlanInfo","SecondaryRate"};
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
			for (Int084Vo detail : dataList084) {
				row = sheet.createRow(rowNum);
				// No.
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getOfficeName()))?detail.getOfficeName(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getOfficeCode()))?detail.getOfficeCode(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getRisk()))?detail.getRisk(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getOrigin()))?detail.getOrigin(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
		
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(formVo.getStartDateTM()))?formVo.getStartDateTM(): "" );
				
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(formVo.getEndDateTM()))?formVo.getEndDateTM(): "" );
				
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getRiskScore()))?detail.getRiskScore(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue("");
				
				rowNum++;
				cellNum = 0;
			}
			
			
			/*set	fileName*/		
			String fileName ="TeamMate_"+DateFormatUtils.format(new Date(),"yyyyMMdd");;
			log.info(fileName);
			
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
			
			log.info("Done");
		}
	public void exportFile2(Int084FormVo formVo, HttpServletResponse response) throws IOException {
		List<Int084Vo> dataList084 = new ArrayList<Int084Vo>();
		
		dataList084 = iaIncomeExciseAudDao.findAllInt084(formVo);
		 log.info("Data list exportFile {} row",dataList084.size());
//		dataTestList = formVo.getDataT();
		
			/* create spreadsheet */
			XSSFWorkbook workbook = excalService.setUpExcel();
			CellStyle thStyle = excalService.thStyle;

			CellStyle fontHeader = workbook.createCellStyle();
			fontHeader.setFont(excalService.fontHeader);
			
			Sheet sheet = workbook.createSheet("ใบเสร็จเสีย");
			int rowNum = 0;
			int cellNum = 0;
			Row row = sheet.createRow(rowNum);
			Cell cell = row.createCell(cellNum);
			log.info("Creating excel");
			
			 
			/* create data spreadsheet */

			/* Header */
//			String[] tbTH1 = formVo.getTrHtml1();
			

			String[] tbTH1 = {"รหัสสำนักงานสรรพสามิต","ชื่อสำนักงานสรรพสามิต","ช่วงเดือนที่","ถึงเดือนที่","จำนวนใบเสร็จ","จำนวนใบเสีย","รายละเอียดความเสี่ยง"};
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
			for (Int084Vo detail : dataList084) {
				row = sheet.createRow(rowNum);
				// No.
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getOfficeCode()))?detail.getOfficeCode(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getOfficeName()))?detail.getOfficeName(): "" );
				
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getStartDate()))?detail.getStartDate(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getEndDate()))?detail.getEndDate(): "" );
				
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getBillAll()))?detail.getBillAll(): "" );
				cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getBillWaste()))?detail.getBillWaste(): "" );
				
				Float persenRow = Float.valueOf((!BeanUtils.isEmpty(detail.getRiskPersen())?detail.getRiskPersen():"0")) ;
				Float persen = Float.valueOf((!BeanUtils.isEmpty(formVo.getBillLost())?formVo.getBillLost():"0"));
//	             log.info("persenRow {} persen {}",persenRow,persen);   
				if(persenRow>=persen) {
					cell = row.createCell(cellNum++);cell.setCellStyle(excalService.bgRed);cell.setCellValue((StringUtils.isNotBlank(detail.getRiskRemark()))?detail.getRiskRemark(): "" );
				}else {
					cell = row.createCell(cellNum++);cell.setCellStyle(excalService.cellLeft);cell.setCellValue((StringUtils.isNotBlank(detail.getRiskRemark()))?detail.getRiskRemark(): "" );
				}
				
				
				rowNum++;
				cellNum = 0;
			}
			
			// Column Width
			int colIndex = 0;
			sheet.setColumnWidth(colIndex++, 25 * 256);
			sheet.setColumnWidth(colIndex++, 38 * 256);
			sheet.setColumnWidth(colIndex++, 11 * 256);
			sheet.setColumnWidth(colIndex++, 11 * 256);
			sheet.setColumnWidth(colIndex++, 14 * 256);
			sheet.setColumnWidth(colIndex++, 14 * 256);
			sheet.setColumnWidth(colIndex++, 25 * 256);
			
			
			/*set	fileName*/		
			String fileName ="Bill_Lost_"+DateFormatUtils.format(new Date(),"yyyyMMdd");;
			log.info(fileName);
			
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
			
			log.info("Done");
		}

}




