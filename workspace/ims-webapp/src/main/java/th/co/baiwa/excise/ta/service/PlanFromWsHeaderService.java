package th.co.baiwa.excise.ta.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.dao.ExciseTaxReceiveDao;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetDetailDao;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetHeaderDao;
import th.co.baiwa.excise.ta.persistence.entity.PlanFromWsHeader;
import th.co.baiwa.excise.ta.persistence.entity.PlanRiskDtl;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetDetail;
import th.co.baiwa.excise.ta.persistence.entity.analysis.PlanWorksheetHeader;
import th.co.baiwa.excise.ta.persistence.repository.PlanFromWsHeaderRepository;
import th.co.baiwa.excise.ta.persistence.repository.PlanRiskDtlRepository;
import th.co.baiwa.excise.ta.persistence.repository.PlanWorksheetHeaderRepository;
import th.co.baiwa.excise.ta.persistence.vo.PlanFromWsVo;

@Service
public class PlanFromWsHeaderService {

	@Autowired
	private PlanFromWsHeaderRepository planFromWsHeaderRepository;

	@Autowired
	private PlanWorksheetHeaderDao planWorksheetHeaderDao;

	@Autowired
	private ExciseTaxReceiveDao exciseTaxReceiveDao;

	@Autowired
	private PlanWorksheetDetailDao planWorksheetDetailDao;

	@Autowired
	private PlanWorksheetHeaderRepository planWorksheetHeaderRepository;

	@Autowired
	private PlanWorksheetHeaderService planWorksheetHeaderService;
	
	@Autowired
	private PlanRiskDtlRepository planRiskDtlRepository;
	
	private final String RISK_TYPE_NON_PAY = "NON_PAY";
	private final String RISK_TYPE_PERCENT_DIFF_SYMBOL_1 = "PERCENT_DIFF >";
	private final String RISK_TYPE_PERCENT_DIFF_SYMBOL_2 = "PERCENT_DIFF <";
	private final String RISK_TYPE_PERCENT_DIFF_SYMBOL_3 = "PERCENT_DIFF =";

	public void findExciseIdOrderByPercenTax(PlanFromWsVo vo) throws SQLException{
		//budgetYear 2561  01/10/2560 -> 30/09/2561
		Date startDate = DateConstant.convertStrToDate(vo.getDateFrom(), DateConstant.MM_YYYY);
		Date endDate = DateConstant.convertStrToDate(vo.getDateTo(), DateConstant.MM_YYYY);
		int daysBetween = (int)ChronoUnit.MONTHS.between(DateConstant.dateToLocalDadte(startDate), DateConstant.dateToLocalDadte(endDate));
		String analysNumber = DateConstant.DateToString(new Date(), DateConstant.YYYYMMDD) + "-01-"+ planWorksheetHeaderDao.getAnalysNumber();
		List<String> monthLIst = exciseTaxReceiveDao.queryMonthShotName(endDate, daysBetween);
		List<PlanFromWsHeader> planFromWsHeaderList = planFromWsHeaderRepository.findHeaderFromTemp(monthLIst, analysNumber);
		planWorksheetHeaderDao.planFromWsInsertBatch(planFromWsHeaderList, 10000);
		List<PlanWorksheetDetail> planWorksheetDetails = planWorksheetDetailDao.findDetailFromTemp(monthLIst, analysNumber);
		planWorksheetDetailDao.insertPlanWorksheetDetail(planWorksheetDetails, 10000);
		PlanWorksheetHeader planWorksheetHeader = new PlanWorksheetHeader();
		planWorksheetHeader.setAnalysNumber(analysNumber);
		List<PlanWorksheetHeader> planWorksheetHeaderList = planWorksheetHeaderDao.queryPlanWorksheetHeaderCriteria(planWorksheetHeader);
		List<Long> listHeader = new ArrayList<Long>();
		if (!planWorksheetHeaderList.isEmpty()) {
			for (PlanWorksheetHeader plan : planWorksheetHeaderList) {
				validateCondition(plan , monthLIst , vo);
				if("S".equals(plan.getFlag())) {
					listHeader.add(plan.getWorkSheetHeaderId());
				}
			}
		}
		
		
		
	}

	private void validateCondition(PlanWorksheetHeader plan , List<String> monthList ,PlanFromWsVo vo) {
		int monthNotPayRisk = Integer.parseInt(vo.getMonthNonPay());
		PlanWorksheetDetail search = new PlanWorksheetDetail();
		search.setAnalysNumber(plan.getAnalysNumber());
		search.setExciseId(plan.getExciseId());
		List<PlanWorksheetDetail> dtlList = planWorksheetDetailDao.queryPlanWorksheetDetailCriteria(search);
		
		// field condition 1
		int countNotPayTaxMonth = 0;
		int maxNotPay = 0;
		
		List<String> dtlListMonth = new ArrayList<String>();
		
		for (PlanWorksheetDetail dtl : dtlList) {
			dtlListMonth.add(dtl.getMonth());
		}
		for (String month : monthList) {
			if(dtlListMonth.indexOf(month) == -1) {
				countNotPayTaxMonth = 0;
			}else {
				countNotPayTaxMonth ++;
			}
			if(maxNotPay < countNotPayTaxMonth) {
				maxNotPay = countNotPayTaxMonth;
			}

		}
		
		if(maxNotPay >= monthNotPayRisk ) {
			plan.setFlag("S");
			PlanRiskDtl planRiskDtl = new PlanRiskDtl(new BigDecimal(plan.getWorkSheetHeaderId()), RISK_TYPE_NON_PAY, maxNotPay+"");
			planRiskDtlRepository.save(planRiskDtl);
			
		}
		//######### condition 2########################
		// field condition 2
		double firstAmount = 0;
		double secondAmount = 0;
		for (String month : monthList) {
			firstAmount = 0;
			
			for (PlanWorksheetDetail dtl : dtlList) {
				if(month.equals(dtl.getMonth())) {
					firstAmount =  dtl.getAmount() != null  ? dtl.getAmount().doubleValue() : 0;
				}
			}
			double onePercen = 0;
			double percenDiff = 0;
			if(firstAmount != 0 && secondAmount != 0) {
				if(firstAmount > secondAmount) {
					onePercen = firstAmount/100;
					percenDiff = 100 - (secondAmount/onePercen);
				}else {
					onePercen = secondAmount/100;
					percenDiff = 100 - (firstAmount/onePercen);
				}
				
				if(">".equals(vo.getSymbol1())) {
					if(percenDiff > Double.parseDouble(vo.getPercent1())) {
						plan.setFlag("S");
						PlanRiskDtl planRiskDtl = new PlanRiskDtl(new BigDecimal(plan.getWorkSheetHeaderId()), RISK_TYPE_PERCENT_DIFF_SYMBOL_1,  percenDiff + " > "+ vo.getPercent1());
						planRiskDtlRepository.save(planRiskDtl);
					}
				}else if("<".equals(vo.getSymbol1())) {
					if(percenDiff < Double.parseDouble(vo.getPercent1())) {
						plan.setFlag("S");
						PlanRiskDtl planRiskDtl = new PlanRiskDtl(new BigDecimal(plan.getWorkSheetHeaderId()), RISK_TYPE_PERCENT_DIFF_SYMBOL_2,  percenDiff + " < "+ vo.getPercent1());
						planRiskDtlRepository.save(planRiskDtl);
					}
				}else {
					if(percenDiff == Double.parseDouble(vo.getPercent1())) {
						plan.setFlag("S");
						PlanRiskDtl planRiskDtl = new PlanRiskDtl(new BigDecimal(plan.getWorkSheetHeaderId()), RISK_TYPE_PERCENT_DIFF_SYMBOL_3,  percenDiff + " = "+ vo.getPercent1());
						planRiskDtlRepository.save(planRiskDtl);
					}
				}
				
			}
			secondAmount = firstAmount;
			
		}		
		System.out.println("#########################");
	}

}
