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
import th.co.baiwa.excise.domain.Int0801Vo;
import th.co.baiwa.excise.domain.RiskFullDataVo;
import th.co.baiwa.excise.ia.persistence.entity.Condition;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssOtherDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsDtl;
import th.co.baiwa.excise.ia.persistence.entity.RiskAssRiskWsHdr;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssOtherDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssRiskWsDtlRepository;
import th.co.baiwa.excise.ia.persistence.repository.RiskAssRiskWsHdrRepository;
import th.co.baiwa.excise.utils.BeanUtils;
import th.co.baiwa.excise.ws.WebServiceExciseService;

@Service
public class RiskAssRiskWsService {
	// Set property Style Excel
		private CellStyle thStyle;
		private CellStyle cellCenter;
		private CellStyle cellRight;
		private CellStyle cellLeft;
		private CellStyle bgRed;
		private CellStyle bgYellow;
		private CellStyle bgGreen;
		private CellStyle topCenter;
		private CellStyle topRight;
		private CellStyle topLeft;
		private Font fontHeader;
		
		private XSSFWorkbook setUpExcel() {
			XSSFWorkbook workbook = new XSSFWorkbook();
			
			thStyle = workbook.createCellStyle();
			thStyle.setAlignment(HorizontalAlignment.CENTER);
			thStyle.setVerticalAlignment(VerticalAlignment.CENTER);
			thStyle.setBorderBottom(BorderStyle.THIN);
			thStyle.setBorderLeft(BorderStyle.THIN);
			thStyle.setBorderRight(BorderStyle.THIN);
			thStyle.setBorderTop(BorderStyle.THIN);
			thStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
			thStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			cellCenter = workbook.createCellStyle();
			cellCenter.setAlignment(HorizontalAlignment.CENTER);
			cellCenter.setBorderBottom(BorderStyle.THIN);
			cellCenter.setBorderLeft(BorderStyle.THIN);
			cellCenter.setBorderRight(BorderStyle.THIN);
			cellCenter.setBorderTop(BorderStyle.THIN);

			cellRight = workbook.createCellStyle();
			cellRight.setAlignment(HorizontalAlignment.RIGHT);
			cellRight.setBorderBottom(BorderStyle.THIN);
			cellRight.setBorderLeft(BorderStyle.THIN);
			cellRight.setBorderRight(BorderStyle.THIN);
			cellRight.setBorderTop(BorderStyle.THIN);

			cellLeft = workbook.createCellStyle();
			cellLeft.setAlignment(HorizontalAlignment.LEFT);
			cellLeft.setBorderBottom(BorderStyle.THIN);
			cellLeft.setBorderLeft(BorderStyle.THIN);
			cellLeft.setBorderRight(BorderStyle.THIN);
			cellLeft.setBorderTop(BorderStyle.THIN);

			bgRed = workbook.createCellStyle();
			bgRed.setAlignment(HorizontalAlignment.CENTER);
			bgRed.setBorderBottom(BorderStyle.THIN);
			bgRed.setBorderLeft(BorderStyle.THIN);
			bgRed.setBorderRight(BorderStyle.THIN);
			bgRed.setBorderTop(BorderStyle.THIN);
			bgRed.setFillForegroundColor(IndexedColors.RED.getIndex());
			bgRed.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			bgYellow = workbook.createCellStyle();
			bgYellow.setAlignment(HorizontalAlignment.CENTER);
			bgYellow.setBorderBottom(BorderStyle.THIN);
			bgYellow.setBorderLeft(BorderStyle.THIN);
			bgYellow.setBorderRight(BorderStyle.THIN);
			bgYellow.setBorderTop(BorderStyle.THIN);
			bgYellow.setFillForegroundColor(IndexedColors.YELLOW.getIndex());
			bgYellow.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			bgGreen = workbook.createCellStyle();
			bgGreen.setAlignment(HorizontalAlignment.CENTER);
			bgGreen.setBorderBottom(BorderStyle.THIN);
			bgGreen.setBorderLeft(BorderStyle.THIN);
			bgGreen.setBorderRight(BorderStyle.THIN);
			bgGreen.setBorderTop(BorderStyle.THIN);
			bgGreen.setFillForegroundColor(IndexedColors.GREEN.getIndex());
			bgGreen.setFillPattern(FillPatternType.SOLID_FOREGROUND);

			fontHeader = workbook.createFont();
			fontHeader.setBold(true);

			topCenter = workbook.createCellStyle();
			topCenter.setAlignment(HorizontalAlignment.CENTER);
			topCenter.setFont(fontHeader);

			topRight = workbook.createCellStyle();
			topRight.setAlignment(HorizontalAlignment.RIGHT);

			topLeft = workbook.createCellStyle();
			topLeft.setAlignment(HorizontalAlignment.LEFT);
			return workbook;
		}
		
	
	
	
	private static final Logger logger = LoggerFactory.getLogger(RiskAssRiskWsService.class);
	private final RiskAssRiskWsHdrRepository riskAssRiskWsHdrRepository;
	private final String BUDGET_YEAR = "BUDGET_YEAR";
	private final String RISK_CONFIG = "RISK_CONFIG";
	private final String INT08_1 = "INT08-1";

	@Autowired
	private WebServiceExciseService webServiceExciseService;

	@Autowired
	private LovRepository lovRepository;

	@Autowired
	private RiskAssRiskWsDtlRepository riskAssRiskWsDtlRepository;

	@Autowired
	private RiskAssOtherDtlRepository riskAssOtherDtlRepository;

	@Autowired
	private ConditionService conditionService;

	@Autowired
	public RiskAssRiskWsService(RiskAssRiskWsHdrRepository riskAssRiskWsHdrRepository) {
		this.riskAssRiskWsHdrRepository = riskAssRiskWsHdrRepository;
	}

	public Message createRiskAssRiskWsHdrRepository(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("Risk Name : " + riskAssRiskWsHdr.getRiskHdrName());
		Message message = null;
		List<RiskAssRiskWsHdr> riskWsHdrList = riskAssRiskWsHdrRepository.findByCriteria(riskAssRiskWsHdr);
		if (BeanUtils.isEmpty(riskWsHdrList) && riskWsHdrList.size() == 0) {
			RiskAssRiskWsHdr riskWsHdr = riskAssRiskWsHdrRepository.save(riskAssRiskWsHdr);
			if (BeanUtils.isNotEmpty(riskWsHdr.getRiskHrdId())) {
				message = ApplicationCache.getMessage("MSG_00002");
			} else {
				message = ApplicationCache.getMessage("MSG_00003");
			}
		} else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;
	}

	public ResponseDataTable<RiskAssRiskWsHdr> findByCriteriaForDatatable(RiskAssRiskWsHdr riskAssRiskWsHdr, DataTableRequest dataTableRequest) {

		ResponseDataTable<RiskAssRiskWsHdr> responseDataTable = new ResponseDataTable<RiskAssRiskWsHdr>();
		List<RiskAssRiskWsHdr> riskAssRiskWsHdrList = findByBudgetYear(riskAssRiskWsHdr.getBudgetYear());
		responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
		responseDataTable.setData(riskAssRiskWsHdrList);
		responseDataTable.setRecordsTotal(riskAssRiskWsHdrList.size());
		responseDataTable.setRecordsFiltered(riskAssRiskWsHdrList.size());
		return responseDataTable;

	}

	public List<RiskAssRiskWsHdr> findByBudgetYear(String year) {
		return riskAssRiskWsHdrRepository.findByBudgetYear(year);
	}

	public List<RiskAssRiskWsDtl> findRiskAssRiskDtlByWebService() {
		return webServiceExciseService.getRiskAssRiskWsDtlList(new RiskAssRiskWsDtl());

	}

	public Message deleteRiskAssRiskWsHdrRepository(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		logger.info("Risk Name : " + riskAssRiskWsHdr.getRiskHdrName());
		riskAssRiskWsHdrRepository.delete(riskAssRiskWsHdr.getRiskHrdId());
		Message message = ApplicationCache.getMessage("MSG_00005");
		return message;

	}

	public Message createBuggetYear(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		Message message = null;
		Lov lov = new Lov(BUDGET_YEAR);
		lov.setValue1(riskAssRiskWsHdr.getBudgetYear());
		lov.setSubType(INT08_1);
		List<Lov> lovList = lovRepository.queryLovByCriteria(lov, null);
		if (lovList == null || lovList.size() == 0) {
			lovRepository.save(lov);
			Lov dataInit = new Lov(RISK_CONFIG);
			dataInit.setSubType(INT08_1);
			RiskAssRiskWsHdr insertConfigData = null;
			List<Lov> lovInitList = lovRepository.queryLovByCriteria(dataInit, null);
			for (Lov lov2 : lovInitList) {
				insertConfigData = new RiskAssRiskWsHdr();
				insertConfigData.setRiskHdrName(lov2.getValue1());
				insertConfigData.setBudgetYear(riskAssRiskWsHdr.getBudgetYear());
				insertConfigData.setActive(riskAssRiskWsHdr.getActive());
				riskAssRiskWsHdrRepository.save(insertConfigData);

			}
			message = ApplicationCache.getMessage("MSG_00002");
		} else {
			message = ApplicationCache.getMessage("MSG_00004");
		}
		return message;

	}

	public List<RiskAssRiskWsDtl> findByGroupRiskHrdId(Long riskHrdId) {
		return riskAssRiskWsDtlRepository.findByGroupRiskHrdId(riskHrdId);
	}

	public void updateRiskAssRiskWsDtl(List<RiskAssRiskWsDtl> riskAssRiskWsDtls) {
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssRiskWsDtls.get(0).getRiskHrdId(), "MAIN", "int08-1-5");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssRiskWsDtl riskAssRiskWsDtl : riskAssRiskWsDtls) {
				for (Condition condition : conditionList) {
					long value = riskAssRiskWsDtl.getApproveBudget().longValue();
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssRiskWsDtl.setRl(condition.getValueRl());
						riskAssRiskWsDtl.setColor(condition.getColor());
						riskAssRiskWsDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssRiskWsDtl.setRl(condition.getValueRl());
						riskAssRiskWsDtl.setColor(condition.getColor());
						riskAssRiskWsDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssRiskWsDtl.setRl(condition.getValueRl());
						riskAssRiskWsDtl.setColor(condition.getColor());
						riskAssRiskWsDtl.setValueTranslation(condition.getConvertValue());
					}
				}

				riskAssRiskWsDtlRepository.save(riskAssRiskWsDtl);
			}
		}
	}

	public RiskAssRiskWsHdr findById(Long id) {
		return riskAssRiskWsHdrRepository.findOne(id);
	}
	
	public List<RiskAssRiskWsHdr> updatePercent(List<RiskAssRiskWsHdr> riskAssRiskWsHdrs) {
		List<RiskAssRiskWsHdr> RiskAssRiskWsHdrList = new ArrayList<RiskAssRiskWsHdr>();
		for (RiskAssRiskWsHdr riskAssRiskWsHdr : riskAssRiskWsHdrs) {
			riskAssRiskWsHdrRepository.updatePercent(riskAssRiskWsHdr.getPercent(), riskAssRiskWsHdr.getRiskHrdId());
			RiskAssRiskWsHdrList.add(riskAssRiskWsHdrRepository.findOne(riskAssRiskWsHdr.getRiskHrdId()));
		}
		return RiskAssRiskWsHdrList;
	}

	public void updateRiskAssRiskWsHdr(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		riskAssRiskWsHdrRepository.save(riskAssRiskWsHdr);
	}

	public List<RiskAssRiskWsHdr> findByCriteria(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		return riskAssRiskWsHdrRepository.findByCriteria(riskAssRiskWsHdr);
	}

	public List<RiskAssOtherDtl> findByRiskHrdId(Long riskHrdId) {
		return riskAssOtherDtlRepository.findByRiskHrdId(riskHrdId);
	}
	
	
	public void updateRiskAssOtherDtl(List<RiskAssOtherDtl> riskAssOtherDtlList) {
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssOtherDtlList.get(0).getRiskHrdId(), "OTHER", "int08-1-6");
		if (BeanUtils.isNotEmpty(conditionList)) {
			for (RiskAssOtherDtl riskAssOtherDtl : riskAssOtherDtlList) {
				for (Condition condition : conditionList) {
					long value = riskAssOtherDtl.getRiskCost().longValue();
					if ("<>".equals(condition.getCondition()) && value >= condition.getValue1().longValue() && value <= condition.getValue2().longValue()) {
						riskAssOtherDtl.setRl(condition.getValueRl());
						riskAssOtherDtl.setColor(condition.getColor());
						riskAssOtherDtl.setValueTranslation(condition.getConvertValue());
					} else if (">".equals(condition.getCondition()) && value > condition.getValue1().longValue()) {
						riskAssOtherDtl.setRl(condition.getValueRl());
						riskAssOtherDtl.setColor(condition.getColor());
						riskAssOtherDtl.setValueTranslation(condition.getConvertValue());
					} else if ("<".equals(condition.getCondition()) && value < condition.getValue1().longValue()) {
						riskAssOtherDtl.setRl(condition.getValueRl());
						riskAssOtherDtl.setColor(condition.getColor());
						riskAssOtherDtl.setValueTranslation(condition.getConvertValue());
					}
				}

			}

		}
		RiskAssOtherDtl databaseData;
		for (RiskAssOtherDtl riskAssOtherDtl : riskAssOtherDtlList) {
			if (riskAssOtherDtl.getIsDeleted().equals("N")) {
				try {
					databaseData = riskAssOtherDtlRepository.findOne(riskAssOtherDtl.getRiskOtherDtlId());
					if (databaseData != null && databaseData.getRiskOtherDtlId() != null) {
						databaseData.setProjectBase(riskAssOtherDtl.getProjectBase());
						databaseData.setDepartmentName(riskAssOtherDtl.getDepartmentName());
						databaseData.setRiskCost(riskAssOtherDtl.getRiskCost());
						databaseData.setRl(riskAssOtherDtl.getRl());
						databaseData.setValueTranslation(riskAssOtherDtl.getValueTranslation());
						databaseData.setColor(riskAssOtherDtl.getColor());
						riskAssOtherDtlRepository.save(databaseData);
					} else {
						riskAssOtherDtlRepository.save(riskAssOtherDtl);
					}
				} catch (Exception e) {
					riskAssOtherDtlRepository.save(riskAssOtherDtl);
				}
				
			} else if (riskAssOtherDtl.getIsDeleted().equals("Y")) {
				databaseData = riskAssOtherDtlRepository.findOne(riskAssOtherDtl.getRiskOtherDtlId());
				riskAssOtherDtlRepository.delete(databaseData);
			}
		}

	}

	public List<RiskFullDataVo> searchFullRiskByBudgetYear(String budgetYear , List<String> riskHdrNameList) {
		
		List<RiskFullDataVo> riskFullDataVoList= new ArrayList<RiskFullDataVo>();
		RiskFullDataVo riskFullDataVo = new RiskFullDataVo();
		List<Int0801Vo> projectDepName = riskAssRiskWsHdrRepository.findProjectBaseByBudgetYear(budgetYear);
		int index = 1;
		for (Int0801Vo projectBase : projectDepName) {
			riskFullDataVo = new RiskFullDataVo();
			int sumRl = 0;
			riskFullDataVo.setId(index+"");
			riskFullDataVo.setProjectBase(projectBase.getProjectBase());
			riskFullDataVo.setDepartmentName(projectBase.getDepartmentName());
			List<Int0801Vo> intList = riskAssRiskWsHdrRepository.findData(budgetYear, projectBase.getProjectBase() , projectBase.getDepartmentName());
			List<String> rl = new ArrayList<String>();
			String rlDate = "";
			for (String riskHdrName : riskHdrNameList) {
				rlDate = "";
				for (Int0801Vo value : intList) {
					if(value.getProjectBase().equals(riskHdrName)) {
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
			
			riskFullDataVoList.add(riskFullDataVo);
			index++;
		}
		return riskFullDataVoList;
	}

	public void updateStatusRisk(RiskAssRiskWsHdr riskAssRiskWsHdr) {
		RiskAssRiskWsHdr riskWsHdr = riskAssRiskWsHdrRepository.findOne(riskAssRiskWsHdr.getRiskHrdId());
		riskWsHdr.setActive(riskAssRiskWsHdr.getActive());
		riskAssRiskWsHdrRepository.save(riskWsHdr);
	}
			
	
	
/*	searchRisk*/
	 public ResponseDataTable<RiskAssRiskWsHdr> searchRiskCriteriaForDatatable(RiskAssRiskWsHdr riskAssRiskWsHdr, DataTableRequest dataTableRequest) {
			ResponseDataTable<RiskAssRiskWsHdr> responseDataTable = new ResponseDataTable<RiskAssRiskWsHdr>();
			List<RiskAssRiskWsHdr> riskAssInfHdrList = null;
			if("0".equals(riskAssRiskWsHdr.getRiskHdrName())||"".equals(riskAssRiskWsHdr.getRiskHdrName())) {
				riskAssInfHdrList = findByBudgetYearWsHdrActive(riskAssRiskWsHdr.getBudgetYear(),riskAssRiskWsHdr.getActive());
			}else {
				riskAssInfHdrList = findRiskByWsHdrActive(riskAssRiskWsHdr.getRiskHdrName(),riskAssRiskWsHdr.getBudgetYear(),riskAssRiskWsHdr.getActive());
			}
			
			responseDataTable.setDraw(dataTableRequest.getDraw().intValue() + 1);
			responseDataTable.setData(riskAssInfHdrList);
			responseDataTable.setRecordsTotal((int) riskAssInfHdrList.size());
			responseDataTable.setRecordsFiltered((int) riskAssInfHdrList.size());
			return responseDataTable;

		}
	 
	public List<RiskAssRiskWsHdr> findByBudgetYearWsHdrActive(String year,String active ){	
			return riskAssRiskWsHdrRepository.findByBudgetYearActive(year,active); 
		}
	
	 public List<RiskAssRiskWsHdr> findRiskByWsHdrActive(String riskHdrName,String year,String active ){	
			return riskAssRiskWsHdrRepository.findRiskByWsHdrActive(riskHdrName,year,active); 
		}

	public void exportWsOtherDtl(RiskAssOtherDtl riskAssOtherDtl, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		/*create spreadsheet*/
		XSSFWorkbook workbook = setUpExcel();
		Sheet sheet = workbook.createSheet();
		int rowNum = 0;
		int cellNum = 0;
		Row row = sheet.createRow(rowNum);
		Cell cell = row.createCell(cellNum);
		DecimalFormat formatter = new DecimalFormat("#,##0");
		System.out.println("Creating excel");
		
		/*create data spreadsheet*/	
		/*Title*/	
		RiskAssRiskWsHdr WsHdr = riskAssRiskWsHdrRepository.findOne(riskAssOtherDtl.getRiskHrdId());
		row = sheet.createRow(0);
		cell = row.createCell(cellNum);cell.setCellValue("กระดาษทำการประเมินความเสี่ยงระบบสารสนเทศฯ ของกรมสรรพสามิต");cell.setCellStyle(topCenter);
		row = sheet.createRow(1);
		cell = row.createCell(cellNum);cell.setCellValue("ปีงบประมาณ  "+WsHdr.getBudgetYear());cell.setCellStyle(topCenter);
		row = sheet.createRow(2);
		cell = row.createCell(cellNum);cell.setCellValue("ปัจจัยเสี่ยง : "+WsHdr.getRiskHdrName());cell.setCellStyle(topLeft);
		row = sheet.createRow(3);
		cell = row.createCell(cellNum);cell.setCellValue("เงื่อนไขความเสี่ยง :  ");cell.setCellStyle(topLeft);
		
		List<Condition> conditionList = conditionService.findConditionByParentId(riskAssOtherDtl.getRiskHrdId(), "OTHER", "int08-1-6");
		rowNum = 4;
		cellNum = 1;
		
		for (Condition con : conditionList) {
			row = sheet.createRow(rowNum);
			

			if("<>".equals(con.getCondition())) {
				if(con.getValue2()==null) {
					cell = row.createCell(1);cell.setCellValue("          "+WsHdr.getRiskHdrName()+"          ระหว่าง    "+con.getValue1()+"  ถึง        -   ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
				}else {
					cell = row.createCell(1);cell.setCellValue("          "+WsHdr.getRiskHdrName()+"          ระหว่าง    "+con.getValue1()+"  ถึง   "+con.getValue2()+"  ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
				}
				
			}else if (">".equals(con.getCondition())) {
				if(con.getValue2()==null) {
					cell = row.createCell(1);cell.setCellValue("          "+WsHdr.getRiskHdrName()+"          มากกว่า  " +con.getValue1()+ "  ถึง        -   ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
				}else {
					cell = row.createCell(1);cell.setCellValue("          "+WsHdr.getRiskHdrName()+"          มากกว่า  "+con.getValue1()+ "  ถึง   "+con.getValue2()+"  ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
				}
				
			}else if ("<".equals(con.getCondition())) {
				if(con.getValue2()==null) {
					cell = row.createCell(1);cell.setCellValue("          "+WsHdr.getRiskHdrName()+"          น้อยกว่า  "  +con.getValue1()+   "  ถึง        -   ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
				}else {
					cell = row.createCell(1);cell.setCellValue("          "+WsHdr.getRiskHdrName()+"          น้อยกว่า  "  +con.getValue1()+  "  ถึง   "+con.getValue2()+"  ระดับความเสี่ยง  "+con.getConvertValue()+"  คะแนนความเสี่ยง  "+con.getValueRl());
				}
				
			}
			cellNum++;
			
			rowNum++;

		}
		/* Header*/
		rowNum =9;
		row = sheet.createRow(rowNum);
		String[] tbTH = {"ลำดับ","โครงการตามยุทธศาสตร์","หน่วยงาน","ค่าความเสี่ยง","ประเมินความเสี่ยง"};
		for(cellNum =0 ;cellNum<tbTH.length;cellNum++) {
			cell = row.createCell(cellNum);cell.setCellValue(tbTH[cellNum]);cell.setCellStyle(thStyle);
		};
		
		cell = row.createCell(5);cell.setCellStyle(cellCenter);
		
		Row rowRisk = null;
		Cell cellRisk = null;
		int cellRiskNum = 4;
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
		 sheet.setColumnWidth(3, 76 * 100);
		 // merge(firstRow, lastRow, firstCol, lastCol)
		 sheet.addMergedRegion(new CellRangeAddress(9,9,4,5));
		 //merge ลำดับ -> รวม
		 for(int i=0;i<=3;i++) {
		 sheet.addMergedRegion(new CellRangeAddress(9,10,i,i));
		 }
		//merge Title
		 	sheet.addMergedRegion(new CellRangeAddress(0,0,0,5));
			sheet.addMergedRegion(new CellRangeAddress(1,1,0,5));
			sheet.addMergedRegion(new CellRangeAddress(4,4,1,5));
			sheet.addMergedRegion(new CellRangeAddress(5,5,1,5));
			sheet.addMergedRegion(new CellRangeAddress(6,6,1,5));
		/*End set sheet */			
			
			/* Detail */
			List<RiskAssOtherDtl> resultList = riskAssOtherDtlRepository.findByRiskHrdId(riskAssOtherDtl.getRiskHrdId());
			rowNum = 11;
			cellNum = 0;
			int no = 1;
			for (RiskAssOtherDtl detail : resultList) {
				row = sheet.createRow(rowNum);
				// No.
				cell = row.createCell(cellNum);
				cell.setCellValue(no);
				cell.setCellStyle(cellCenter);
				cellNum++;
				// 
				cell = row.createCell(cellNum);
				cell.setCellValue(detail.getProjectBase());
				cell.setCellStyle(cellLeft);
				cellNum++;
				
				cell = row.createCell(cellNum);
				cell.setCellValue(detail.getDepartmentName());
				cell.setCellStyle(cellCenter);
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
		String fileName ="WsOtherDtl_"+WsHdr.getBudgetYear();
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
