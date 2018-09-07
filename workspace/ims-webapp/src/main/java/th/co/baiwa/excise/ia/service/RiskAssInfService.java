package th.co.baiwa.excise.ia.service;

import java.io.ByteArrayOutputStream;

import java.io.IOException;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import java.util.List;

import javax.servlet.http.HttpServletResponse;


import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import th.co.baiwa.buckwaframework.common.bean.ResponseDataTable;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.LovRepository;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

import th.co.baiwa.excise.domain.DataTableRequest;
import th.co.baiwa.excise.domain.Int0802Vo;
import th.co.baiwa.excise.domain.RiskFullDataInt0802Vo;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfHdr;
import th.co.baiwa.excise.ia.persistence.entity.Condition;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssInfOtherDtl;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssInfHdrRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssInfDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssInfOtherDtlRepository;

import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;


@Service
public class RiskAssInfService {
	
	private static final Logger logger = LoggerFactory.getLogger(RiskAssInfService.class);

	private final RiskAssInfHdrRepository riskAssInfHdrRepository;
	private final String BUDGET_YEAR = "BUDGET_YEAR";
	private final String RISK_CONFIG = "RISK_CONFIG";
	private final String INT08_2 = "INT08-2";
	
	@Autowired
	private WebServiceExciseService webServiceExciseService;
	
	@Autowired
	private LovRepository lovRepository;
	
	@Autowired
	private RiskAssInfOtherDtlRepository riskAssInfOtherDtlRepository;
	
	@Autowired
	private RiskAssInfDtlRepository riskAssInfDtlRepository;
	
	@Autowired
	private ConditionService conditionService;
	
	@Autowired
	public RiskAssInfService(RiskAssInfHdrRepository riskAssInfHdrRepository) {
		this.riskAssInfHdrRepository = riskAssInfHdrRepository;
	}

	public Message createRiskAssInfHdrRepository(RiskAssInfHdr riskAssInfHdr) {
		logger.info("Risk Name : " + riskAssInfHdr.getRiskAssInfHdrName());
		Message message = null;
		List<RiskAssInfHdr> riskAssInfHdrList = riskAssInfHdrRepository.findByCriteria(riskAssInfHdr);

		if (BeanUtils.isEmpty(riskAssInfHdrList) && riskAssInfHdrList.size() == 0) {

			RiskAssInfHdr riskInfHdr = riskAssInfHdrRepository.save(riskAssInfHdr);

			if (BeanUtils.isNotEmpty(riskInfHdr.getRiskAssInfHdrId())) {
				message = ApplicationCache.getMessage("MSG_00002");
			} else {
				message = ApplicationCache.getMessage("MSG_00003");
			}
		} else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
	}
	
	
	public ResponseDataTable<RiskAssInfHdr> findByCriteriaForDatatable(RiskAssInfHdr riskAssInfHdr, DataTableRequest dataTableRequest) {
		
		ResponseDataTable<RiskAssInfHdr> responseDataTable = new ResponseDataTable<RiskAssInfHdr>();
		List<RiskAssInfHdr> riskAssInfHdrList = findByBudgetYear(riskAssInfHdr.getBudgetYear());

		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(riskAssInfHdrList);
		responseDataTable.setRecordsTotal((int) riskAssInfHdrList.size());
		responseDataTable.setRecordsFiltered((int) riskAssInfHdrList.size());
		return responseDataTable;

	}
	
	public Message deleteRiskAssInfHdrRepository(RiskAssInfHdr riskAssInfHdr) {
		logger.info("id : "+riskAssInfHdr.getRiskAssInfHdrId());
		riskAssInfHdrRepository.delete(riskAssInfHdr.getRiskAssInfHdrId());
		Message message = ApplicationCache.getMessage("MSG_00005");
		return message;

	}
	
	
	public Message createBudgetYear(RiskAssInfHdr riskAssInfHdr) {
		Message message = null;
		Lov lov = new Lov(BUDGET_YEAR);
		lov.setValue1(riskAssInfHdr.getBudgetYear());
		lov.setSubType(INT08_2);
		
		List<Lov> lovList = lovRepository.queryLovByCriteria(lov, null);
		if(lovList == null || lovList.size() == 0) {
			lovRepository.save(lov);
			Lov dataInit = new Lov(RISK_CONFIG);
			dataInit.setSubType(INT08_2);

			RiskAssInfHdr insertConfigData = null;
			List<Lov> lovInitList = lovRepository.queryLovByCriteria(dataInit, null);
			for (Lov lov2 : lovInitList) {
				insertConfigData = new RiskAssInfHdr();
				insertConfigData.setRiskAssInfHdrName(lov2.getValue1());
				insertConfigData.setBudgetYear(riskAssInfHdr.getBudgetYear());
				riskAssInfHdrRepository.save(insertConfigData);	
			}
			message = ApplicationCache.getMessage("MSG_00002");
		}else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
		
	}
	
	
	
	public List<RiskAssInfDtl> findRiskAssInfDtlByWebService() {
	return webServiceExciseService.getRiskAssInfDtlList(new RiskAssInfDtl());
		
	}
	
	
	public RiskAssInfHdr findById(Long id) {
		return  riskAssInfHdrRepository.findOne(id);
	}
	
	public void updateRiskAssInfHdr(RiskAssInfHdr riskAssInfHdr) {
		riskAssInfHdrRepository.save(riskAssInfHdr);
	}

	public List<RiskAssInfDtl> findByGroupRiskInfHrdId(Long riskInfHrdId) {
		return riskAssInfDtlRepository.findByGroupRiskInfHrdId(riskInfHrdId);
	}
	
	public void updateRiskAssInfDtl(List<RiskAssInfDtl> riskAssInfDtls) {
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssInfDtls.get(0).getRiskInfHrdId(), "MAIN", "int08-2-3");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssInfDtl riskAssInfDtl : riskAssInfDtls) {
				for (Condition condition : conditionList) {
					long value = riskAssInfDtl.getTotal().longValue();
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssInfDtl.setRl(condition.getValueRl());
						riskAssInfDtl.setColor(condition.getColor());
						riskAssInfDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssInfDtl.setRl(condition.getValueRl());
						riskAssInfDtl.setColor(condition.getColor());
						riskAssInfDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssInfDtl.setRl(condition.getValueRl());
						riskAssInfDtl.setColor(condition.getColor());
						riskAssInfDtl.setValueTranslation(condition.getConvertValue());
					}
				}

				riskAssInfDtlRepository.save(riskAssInfDtl);
			}
		}
	}
	
	public List<RiskAssInfOtherDtl> findByOtherRiskHrdId(Long riskInfHrdId) {
		return riskAssInfOtherDtlRepository.findByRiskInfHrdId(riskInfHrdId);
	}
	public void updateStatusRisk(RiskAssInfHdr riskAssInfHdr) {
		RiskAssInfHdr riskInfHrd = riskAssInfHdrRepository.findOne(riskAssInfHdr.getRiskAssInfHdrId());
		riskInfHrd.setActive(riskAssInfHdr.getActive());
		riskAssInfHdrRepository.save(riskInfHrd);
		
	}
	

	public void updateRiskAssInfOtherDtl(List<RiskAssInfOtherDtl> riskAssInfOtherDtlList) {
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssInfOtherDtlList.get(0).getRiskInfHrdId(), "OTHER", "int08-2-4");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssInfOtherDtl riskAssInfOtherDtl : riskAssInfOtherDtlList) {
				for (Condition condition : conditionList) {
					long value = riskAssInfOtherDtl.getRiskCost().longValue();
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssInfOtherDtl.setRl(condition.getValueRl());
						riskAssInfOtherDtl.setColor(condition.getColor());
						riskAssInfOtherDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssInfOtherDtl.setRl(condition.getValueRl());
						riskAssInfOtherDtl.setColor(condition.getColor());
						riskAssInfOtherDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssInfOtherDtl.setRl(condition.getValueRl());
						riskAssInfOtherDtl.setColor(condition.getColor());
						riskAssInfOtherDtl.setValueTranslation(condition.getConvertValue());
					}
				}

			}

		}
		RiskAssInfOtherDtl databaseData;
		for (RiskAssInfOtherDtl riskAssInfOtherDtl : riskAssInfOtherDtlList) {
			if (riskAssInfOtherDtl.getIsDeleted().equals("N")) {
				try {
					databaseData = riskAssInfOtherDtlRepository.findOne(riskAssInfOtherDtl.getRiskAssInfOtherId());
					if (databaseData != null && databaseData.getRiskAssInfOtherId() != null) {
						databaseData.setInfName(riskAssInfOtherDtl.getInfName());
						databaseData.setRiskCost(riskAssInfOtherDtl.getRiskCost());
						databaseData.setRl(riskAssInfOtherDtl.getRl());
						databaseData.setValueTranslation(riskAssInfOtherDtl.getValueTranslation());
						databaseData.setColor(riskAssInfOtherDtl.getColor());
						
						riskAssInfOtherDtlRepository.save(databaseData);
					} else {
						riskAssInfOtherDtlRepository.save(riskAssInfOtherDtl);
					}
				} catch (Exception e) {
					riskAssInfOtherDtlRepository.save(riskAssInfOtherDtl);
				}
				
			} else if (riskAssInfOtherDtl.getIsDeleted().equals("Y")) {
				databaseData = riskAssInfOtherDtlRepository.findOne(riskAssInfOtherDtl.getRiskAssInfOtherId());
				riskAssInfOtherDtlRepository.delete(databaseData);
			}
		}

	}
	
	
public List<RiskFullDataInt0802Vo> searchFullRiskByBudgetYear(String budgetYear , List<String> riskAssInfHdrNameList) {
	   
	Long budgetYearL = Long.parseLong(budgetYear);
		List<Condition> conditionList = conditionService.findConditionByParentId(budgetYearL,"ALL", "int08-2-5");
	
	
		List<RiskFullDataInt0802Vo> riskFullDataVoList = new ArrayList<RiskFullDataInt0802Vo>();
		RiskFullDataInt0802Vo riskFullDataVo = new RiskFullDataInt0802Vo();
		List<Int0802Vo> infNameList = riskAssInfHdrRepository.findInfNameByBudgetYear(budgetYear);
		int index = 1;
		for (Int0802Vo infName : infNameList) {
			riskFullDataVo = new RiskFullDataInt0802Vo();
			int sumRl = 0;
			riskFullDataVo.setId(index+"");
			riskFullDataVo.setInfName(infName.getInfName());
			List<Int0802Vo> intList = riskAssInfHdrRepository.findData(budgetYear, infName.getInfName());
			List<String> rl = new ArrayList<String>();
			String rlDate = "";
			for (String riskAssInfHdrName : riskAssInfHdrNameList) {
				rlDate = "";
				for (Int0802Vo value : intList) {
					if(value.getInfName().equals(riskAssInfHdrName)) {
						sumRl += Integer.parseInt(value.getRl());
						rlDate = value.getRl();
						break;
					}
				}
				if(BeanUtils.isNotEmpty(rlDate)) {
					rl.add(rlDate); 
				}else {
					rl.add("0");
				}
			}
			riskFullDataVo.setRl(rl);
			riskFullDataVo.setSumRiskCost(sumRl+"");
			
			long value =Integer.parseInt(riskFullDataVo.getSumRiskCost());
			for (Condition condition : conditionList) {
				
				if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
					riskFullDataVo.setValueRl(condition.getValueRl());
					riskFullDataVo.setColor(condition.getColor());
					riskFullDataVo.setConvertValue(condition.getConvertValue());
				} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
					riskFullDataVo.setValueRl(condition.getValueRl());
					riskFullDataVo.setColor(condition.getColor());
					riskFullDataVo.setConvertValue(condition.getConvertValue());
				} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
					riskFullDataVo.setValueRl(condition.getValueRl());
					riskFullDataVo.setColor(condition.getColor());
					riskFullDataVo.setConvertValue(condition.getConvertValue());
				}
				
			}
			
			riskFullDataVoList.add(riskFullDataVo);
			index++;
		}
		return riskFullDataVoList;
	}
	
	public List<RiskAssInfHdr> findByBudgetYear(String year){
		return riskAssInfHdrRepository.findByBudgetYear(year);
	}
	
	public List<RiskAssInfHdr> updatePercent(List<RiskAssInfHdr> riskAssInfHdrs) {
		List<RiskAssInfHdr> riskAssInfHdrList = new ArrayList<RiskAssInfHdr>();
		for (RiskAssInfHdr riskAssInfHdr : riskAssInfHdrs) {
			riskAssInfHdrRepository.updatePercent(riskAssInfHdr.getPercent(), riskAssInfHdr.getRiskAssInfHdrId());
			riskAssInfHdrList.add(riskAssInfHdrRepository.findOne(riskAssInfHdr.getRiskAssInfHdrId()));
		}
		return riskAssInfHdrList;
	}


	public void exportInfOtherDtl(RiskAssInfHdr riskAssInfHdr, HttpServletResponse response) throws IOException {
	
		/*create spreadsheet*/
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		DecimalFormat formatter = new DecimalFormat("#,##0");
		System.out.println("Creating excel");
	
		
		/*create CellStyle*/
		CellStyle thStyle = workbook.createCellStyle();
		thStyle.setAlignment(HorizontalAlignment.CENTER);
		thStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		thStyle.setBorderBottom(BorderStyle.THIN);
		thStyle.setBorderLeft(BorderStyle.THIN);
		thStyle.setBorderRight(BorderStyle.THIN);
		thStyle.setBorderTop(BorderStyle.THIN);
		thStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		thStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle cellCenter = workbook.createCellStyle();
		cellCenter.setAlignment(HorizontalAlignment.CENTER);
		cellCenter.setBorderBottom(BorderStyle.THIN);
		cellCenter.setBorderLeft(BorderStyle.THIN);
		cellCenter.setBorderRight(BorderStyle.THIN);
		cellCenter.setBorderTop(BorderStyle.THIN);
		
		CellStyle cellRight = workbook.createCellStyle();
		cellRight.setAlignment(HorizontalAlignment.RIGHT);
		cellRight.setBorderBottom(BorderStyle.THIN);
		cellRight.setBorderLeft(BorderStyle.THIN);
		cellRight.setBorderRight(BorderStyle.THIN);
		cellRight.setBorderTop(BorderStyle.THIN);
		
		CellStyle cellLeft = workbook.createCellStyle();
		cellLeft.setAlignment(HorizontalAlignment.LEFT);
		cellLeft.setBorderBottom(BorderStyle.THIN);
		cellLeft.setBorderLeft(BorderStyle.THIN);
		cellLeft.setBorderRight(BorderStyle.THIN);
		cellLeft.setBorderTop(BorderStyle.THIN);
		
		
		CellStyle bgRed = workbook.createCellStyle();
		bgRed.setAlignment(HorizontalAlignment.CENTER);
		bgRed.setBorderBottom(BorderStyle.THIN);
		bgRed.setBorderLeft(BorderStyle.THIN);
		bgRed.setBorderRight(BorderStyle.THIN);
		bgRed.setBorderTop(BorderStyle.THIN);
		bgRed.setFillForegroundColor(IndexedColors.RED.getIndex());
		bgRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle bgYellow = workbook.createCellStyle();
		bgYellow.setAlignment(HorizontalAlignment.CENTER);
		bgYellow.setBorderBottom(BorderStyle.THIN);
		bgYellow.setBorderLeft(BorderStyle.THIN);
		bgYellow.setBorderRight(BorderStyle.THIN);
		bgYellow.setBorderTop(BorderStyle.THIN);
		bgYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		bgYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle bgGreen = workbook.createCellStyle();
		bgGreen.setAlignment(HorizontalAlignment.CENTER);
		bgGreen.setBorderBottom(BorderStyle.THIN);
		bgGreen.setBorderLeft(BorderStyle.THIN);
		bgGreen.setBorderRight(BorderStyle.THIN);
		bgGreen.setBorderTop(BorderStyle.THIN);
		bgGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		bgGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		Font fontHeader = workbook.createFont();
		fontHeader.setBold(true);
		
		CellStyle topCenter = workbook.createCellStyle();
		topCenter.setAlignment(HorizontalAlignment.CENTER);
		topCenter.setFont(fontHeader);
		
		CellStyle topRight = workbook.createCellStyle();
		topRight.setAlignment(HorizontalAlignment.RIGHT);
		
		CellStyle topLeft = workbook.createCellStyle();
		topLeft.setAlignment(HorizontalAlignment.LEFT);
		
		/*create data spreadsheet*/	
				
				/*Title*/
				RiskAssInfHdr InfHdr =  riskAssInfHdrRepository.findOne(riskAssInfHdr.getRiskAssInfHdrId());	
				
				row = sheet.createRow(0);
				cell = row.createCell(cellNum);cell.setCellValue("กระดาษทำการประเมินความเสี่ยงระบบสารสนเทศฯ ของกรมสรรพสามิต");cell.setCellStyle(topCenter);
				row = sheet.createRow(1);
				cell = row.createCell(cellNum);cell.setCellValue("ปีงบประมาณ  "+InfHdr.getBudgetYear());cell.setCellStyle(topCenter);
				row = sheet.createRow(2);
				cell = row.createCell(cellNum);cell.setCellValue("ปัจจัยเสี่ยง : "+InfHdr.getRiskAssInfHdrName());cell.setCellStyle(topLeft);
				row = sheet.createRow(3);
				cell = row.createCell(cellNum);cell.setCellValue("เงื่อนไขความเสี่ยง :  ");cell.setCellStyle(topLeft);
				
				List<Condition> conditionList = conditionService.findConditionByParentId(riskAssInfHdr.getRiskAssInfHdrId(), "OTHER", "int08-2-4");
				rowNum = 4;
				cellNum = 1;
		
				for (Condition con : conditionList) {
					row = sheet.createRow(rowNum);
					
		
					if("<>".equals(con.getCondition())) {
						if(con.getValue2()==null) {
							cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          ระหว่าง    "+con.getValue1()+"  ถึง        -   ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
						}else {
							cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          ระหว่าง    "+con.getValue1()+"  ถึง   "+con.getValue2()+"  ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
						}
						
					}else if (">".equals(con.getCondition())) {
						if(con.getValue2()==null) {
							cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          มากกว่า  " +con.getValue1()+ "  ถึง        -   ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
						}else {
							cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          มากกว่า  "+con.getValue1()+ "  ถึง   "+con.getValue2()+"  ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
						}
						
					}else if ("<".equals(con.getCondition())) {
						if(con.getValue2()==null) {
							cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          น้อยกว่า  "  +con.getValue1()+   "  ถึง        -   ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
						}else {
							cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          น้อยกว่า  "  +con.getValue1()+  "  ถึง   "+con.getValue2()+"  ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
						}
						
					}
					cellNum++;
					
					rowNum++;
		
				}
		
		
				/* Header*/
				rowNum =9;
				row = sheet.createRow(rowNum);
				String[] tbTH = {"ลำดับ","ระบบสารสนเทศฯ ของกรมสรรพสามิต","ค่าความเสี่ยง","ประเมินความเสี่ยง"};
				for(cellNum =0 ;cellNum<tbTH.length;cellNum++) {
					cell = row.createCell(cellNum);cell.setCellValue(tbTH[cellNum]);cell.setCellStyle(thStyle);
				};
				
				cell = row.createCell(4);cell.setCellStyle(cellCenter);
				
				Row rowRisk = null;
				Cell cellRisk = null;
				int cellRiskNum = 3;
				 rowRisk = sheet.createRow(10);
				 cellRisk = rowRisk.createCell(cellRiskNum);cellRisk.setCellValue("RL");cellRisk.setCellStyle(thStyle);
				 cellRiskNum++;
				 cellRisk = rowRisk.createCell(cellRiskNum);cellRisk.setCellValue("แปลค่า");cellRisk.setCellStyle(thStyle);
		
				 for(cellNum =0 ;cellNum<tbTH.length-1;cellNum++) {
						cell = rowRisk.createCell(cellNum);cell.setCellStyle(thStyle);
				 };
				/* EndHeader*/
				 
				 
		/*set sheet */
				 /*setColumnWidth*/
				 sheet.setColumnWidth(1, 76 * 255);
				 sheet.setColumnWidth(2, 76 * 100);
				 // merge(firstRow, lastRow, firstCol, lastCol)
				 sheet.addMergedRegion(new CellRangeAddress(9,9,3,4));
				 //merge ลำดับ -> รวม
				 for(int i=0;i<=2;i++) {
				 sheet.addMergedRegion(new CellRangeAddress(9,10,i,i));
				 }
				//merge Title
				 	sheet.addMergedRegion(new CellRangeAddress(0,0,0,4));
					sheet.addMergedRegion(new CellRangeAddress(1,1,0,4));
					sheet.addMergedRegion(new CellRangeAddress(4,4,1,4));
					sheet.addMergedRegion(new CellRangeAddress(5,5,1,4));
					sheet.addMergedRegion(new CellRangeAddress(6,6,1,4));
		/*End set sheet */		 
				 
				 
					/* Detail */
					List<RiskAssInfOtherDtl> resultList = riskAssInfOtherDtlRepository.findByRiskInfHrdId(riskAssInfHdr.getRiskAssInfHdrId());
					rowNum = 11;
					cellNum = 0;
					int no = 1;
					for (RiskAssInfOtherDtl detail : resultList) {
						row = sheet.createRow(rowNum);
						// No.
						cell = row.createCell(cellNum);
						cell.setCellValue(no);
						cell.setCellStyle(cellCenter);
						cellNum++;
						// 
						cell = row.createCell(cellNum);
						cell.setCellValue(detail.getInfName());
						cell.setCellStyle(cellLeft);
						cellNum++;
						
						cell = row.createCell(cellNum);
						cell.setCellValue(formatter.format(detail.getRiskCost().longValue()));
						cell.setCellStyle(cellRight);
						cellNum++;
						
						cell = row.createCell(cellNum);
						cell.setCellValue(detail.getRl());
						if("แดง".equals(detail.getColor())) {
							cell.setCellStyle(bgRed);
						}else if("เหลือง".equals(detail.getColor())) {
							cell.setCellStyle(bgYellow);
						}else if("เขียว".equals(detail.getColor())) {
							cell.setCellStyle(bgGreen);
						}
						cellNum++;
						
						cell = row.createCell(cellNum);
						cell.setCellValue(detail.getValueTranslation());
						if("แดง".equals(detail.getColor())) {
							cell.setCellStyle(bgRed);
						}else if("เหลือง".equals(detail.getColor())) {
							cell.setCellStyle(bgYellow);
						}else if("เขียว".equals(detail.getColor())) {
							cell.setCellStyle(bgGreen);
						}
						cellNum++;

						no++;
						rowNum++;
						cellNum = 0;
					}

					/* EndDetail */
				 
		/*End create data spreadsheet*/
		/*set	fileName*/		
					String fileName ="RiskAssInfOtherDtl_"+InfHdr.getBudgetYear();
					System.out.println(fileName);
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
		
		System.out.println("Done");
		
	}
	
	public void exportInfWebService(RiskAssInfHdr riskAssInfHdr, HttpServletResponse response) throws IOException {
		
		/*create spreadsheet*/
		XSSFWorkbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		DecimalFormat formatter = new DecimalFormat("#,##0");
		System.out.println("Creating excel");
		
		/*create CellStyle*/
		CellStyle thStyle = workbook.createCellStyle();
		thStyle.setAlignment(HorizontalAlignment.CENTER);
		thStyle.setVerticalAlignment(VerticalAlignment.CENTER);
		thStyle.setBorderBottom(BorderStyle.THIN);
		thStyle.setBorderLeft(BorderStyle.THIN);
		thStyle.setBorderRight(BorderStyle.THIN);
		thStyle.setBorderTop(BorderStyle.THIN);
		thStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		thStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle cellCenter = workbook.createCellStyle();
		cellCenter.setAlignment(HorizontalAlignment.CENTER);
		cellCenter.setBorderBottom(BorderStyle.THIN);
		cellCenter.setBorderLeft(BorderStyle.THIN);
		cellCenter.setBorderRight(BorderStyle.THIN);
		cellCenter.setBorderTop(BorderStyle.THIN);
		
		CellStyle cellRight = workbook.createCellStyle();
		cellRight.setAlignment(HorizontalAlignment.RIGHT);
		cellRight.setBorderBottom(BorderStyle.THIN);
		cellRight.setBorderLeft(BorderStyle.THIN);
		cellRight.setBorderRight(BorderStyle.THIN);
		cellRight.setBorderTop(BorderStyle.THIN);
		
		CellStyle cellLeft = workbook.createCellStyle();
		cellLeft.setAlignment(HorizontalAlignment.LEFT);
		cellLeft.setBorderBottom(BorderStyle.THIN);
		cellLeft.setBorderLeft(BorderStyle.THIN);
		cellLeft.setBorderRight(BorderStyle.THIN);
		cellLeft.setBorderTop(BorderStyle.THIN);
		
		
		CellStyle bgRed = workbook.createCellStyle();
		bgRed.setAlignment(HorizontalAlignment.CENTER);
		bgRed.setBorderBottom(BorderStyle.THIN);
		bgRed.setBorderLeft(BorderStyle.THIN);
		bgRed.setBorderRight(BorderStyle.THIN);
		bgRed.setBorderTop(BorderStyle.THIN);
		bgRed.setFillForegroundColor(IndexedColors.RED.getIndex());
		bgRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle bgYellow = workbook.createCellStyle();
		bgYellow.setAlignment(HorizontalAlignment.CENTER);
		bgYellow.setBorderBottom(BorderStyle.THIN);
		bgYellow.setBorderLeft(BorderStyle.THIN);
		bgYellow.setBorderRight(BorderStyle.THIN);
		bgYellow.setBorderTop(BorderStyle.THIN);
		bgYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
		bgYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		CellStyle bgGreen = workbook.createCellStyle();
		bgGreen.setAlignment(HorizontalAlignment.CENTER);
		bgGreen.setBorderBottom(BorderStyle.THIN);
		bgGreen.setBorderLeft(BorderStyle.THIN);
		bgGreen.setBorderRight(BorderStyle.THIN);
		bgGreen.setBorderTop(BorderStyle.THIN);
		bgGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		bgGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		Font fontHeader = workbook.createFont();
		fontHeader.setBold(true);
		
		CellStyle topCenter = workbook.createCellStyle();
		topCenter.setAlignment(HorizontalAlignment.CENTER);
		topCenter.setFont(fontHeader);
		
		CellStyle topRight = workbook.createCellStyle();
		topRight.setAlignment(HorizontalAlignment.RIGHT);
		
		CellStyle topLeft = workbook.createCellStyle();
		topLeft.setAlignment(HorizontalAlignment.LEFT);
		
		/*create data spreadsheet*/	
			/*Title*/
			RiskAssInfHdr InfHdr =  riskAssInfHdrRepository.findOne(riskAssInfHdr.getRiskAssInfHdrId());	
			
			row = sheet.createRow(0);
			cell = row.createCell(cellNum);cell.setCellValue("กระดาษทำการประเมินความเสี่ยงระบบสารสนเทศฯ ของกรมสรรพสามิต");cell.setCellStyle(topCenter);
			row = sheet.createRow(1);
			cell = row.createCell(cellNum);cell.setCellValue("ปีงบประมาณ  "+InfHdr.getBudgetYear());cell.setCellStyle(topCenter);
			row = sheet.createRow(2);
			cell = row.createCell(cellNum);cell.setCellValue("ปัจจัยเสี่ยง : "+InfHdr.getRiskAssInfHdrName());cell.setCellStyle(topLeft);
			row = sheet.createRow(3);
			cell = row.createCell(cellNum);cell.setCellValue("เงื่อนไขความเสี่ยง :  ");cell.setCellStyle(topLeft);
			
			List<Condition> conditionList = conditionService.findConditionByParentId(riskAssInfHdr.getRiskAssInfHdrId(), "MAIN", "int08-2-3");
			rowNum = 4;
			cellNum = 1;
	
			for (Condition con : conditionList) {
				row = sheet.createRow(rowNum);
				
	
				if("<>".equals(con.getCondition())) {
					if(con.getValue2()==null) {
						cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          ระหว่าง    "+con.getValue1()+"  ถึง        -   ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
					}else {
						cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          ระหว่าง    "+con.getValue1()+"  ถึง   "+con.getValue2()+"  ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
					}
					
				}else if (">".equals(con.getCondition())) {
					if(con.getValue2()==null) {
						cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          มากกว่า  " +con.getValue1()+ "  ถึง        -   ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
					}else {
						cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          มากกว่า  "+con.getValue1()+ "  ถึง   "+con.getValue2()+"  ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
					}
					
				}else if ("<".equals(con.getCondition())) {
					if(con.getValue2()==null) {
						cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          น้อยกว่า  "  +con.getValue1()+   "  ถึง        -   ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
					}else {
						cell = row.createCell(1);cell.setCellValue("          "+InfHdr.getRiskAssInfHdrName()+"          น้อยกว่า  "  +con.getValue1()+  "  ถึง   "+con.getValue2()+"  ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
					}
					
				}
				cellNum++;
				
				rowNum++;
	
			}
			
			/* Header*/
			rowNum =9;
			row = sheet.createRow(rowNum);
			String[] tbTH = {"ลำดับ","ระบบสารสนเทศฯ ของกรมสรรพสามิต","ม.ค.","ก.พ.","มี.ค.","เม.ย.","พ.ค.","มิ.ย.","ก.ค.","ส.ค.","ก.ย.","ต.ค.","พ.ย.","ธ.ค.","รวม","ประเมินความเสี่ยง"};
			for(cellNum =0 ;cellNum<tbTH.length;cellNum++) {
				cell = row.createCell(cellNum);cell.setCellValue(tbTH[cellNum]);cell.setCellStyle(thStyle);
			};
			
			cell = row.createCell(16);cell.setCellStyle(cellCenter);
			
			Row rowRisk = null;
			Cell cellRisk = null;
			int cellRiskNum = 15;
			 rowRisk = sheet.createRow(10);
			 cellRisk = rowRisk.createCell(cellRiskNum);cellRisk.setCellValue("RL");cellRisk.setCellStyle(thStyle);
			 cellRiskNum++;
			 cellRisk = rowRisk.createCell(cellRiskNum);cellRisk.setCellValue("แปลค่า");cellRisk.setCellStyle(thStyle);

			 for(cellNum =0 ;cellNum<tbTH.length-1;cellNum++) {
					cell = rowRisk.createCell(cellNum);cell.setCellStyle(thStyle);
				};
			 
			 
			/*set sheet */
			sheet.setColumnWidth(1, 76 * 255);
			// merge(firstCol, firstRow, lastCol, lastRow)
			sheet.addMergedRegion(new CellRangeAddress(9,9,15,16));
			//merge ลำดับ -> รวม
			for(int i=0;i<=14;i++) {
				sheet.addMergedRegion(new CellRangeAddress(9,10,i,i));
			}
			//merge Title
			sheet.addMergedRegion(new CellRangeAddress(0,0,0,16));
			sheet.addMergedRegion(new CellRangeAddress(1,1,0,16));
			sheet.addMergedRegion(new CellRangeAddress(4,4,1,5));
			sheet.addMergedRegion(new CellRangeAddress(5,5,1,5));
			sheet.addMergedRegion(new CellRangeAddress(6,6,1,5));
			
			/* Detail */
			List<RiskAssInfDtl> resultList = riskAssInfDtlRepository.findByGroupRiskInfHrdId(riskAssInfHdr.getRiskAssInfHdrId());
			rowNum = 11;
			cellNum = 0;
			int no = 1;
			for (RiskAssInfDtl detail : resultList) {
				row = sheet.createRow(rowNum);
				// No.
				cell = row.createCell(cellNum);
				cell.setCellValue(no);
				cell.setCellStyle(cellCenter);
				cellNum++;
				// 
				cell = row.createCell(cellNum);
				cell.setCellValue(detail.getInfName());
				cell.setCellStyle(cellLeft);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getJan().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getFeb().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getMar().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getApril().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getMay().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getJun().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getJul().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getAug().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getSep().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getOct().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getNov().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getDec().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(formatter.format(detail.getTotal().longValue()));
				cell.setCellStyle(cellRight);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(detail.getRl());
				if("แดง".equals(detail.getColor())) {
					cell.setCellStyle(bgRed);
				}else if("เหลือง".equals(detail.getColor())) {
					cell.setCellStyle(bgYellow);
				}else if("เขียว".equals(detail.getColor())) {
					cell.setCellStyle(bgGreen);
				}
				cellNum++;

				cell = row.createCell(cellNum);
				cell.setCellValue(detail.getValueTranslation());
				if("แดง".equals(detail.getColor())) {
					cell.setCellStyle(bgRed);
				}else if("เหลือง".equals(detail.getColor())) {
					cell.setCellStyle(bgYellow);
				}else if("เขียว".equals(detail.getColor())) {
					cell.setCellStyle(bgGreen);
				}
				cellNum++;
				
				
				
				no++;
				rowNum++;
				cellNum = 0;
			}
			
			/* EndDetail */
			 
			/*End create data spreadsheet*/
			/*set	fileName*/		
			String fileName ="RiskAssInfWebService_"+InfHdr.getBudgetYear();
			System.out.println(fileName);
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
			
			System.out.println("Done");
			
	}
	
}
