package th.co.baiwa.excise.ta.service;

import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.excise.constant.DateConstant;
import th.co.baiwa.excise.ia.persistence.dao.ExciseTaxReceiveDao;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetDetailDao;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetHeaderDao;
import th.co.baiwa.excise.ta.persistence.entity.PlanFromWsHeader;
import th.co.baiwa.excise.ta.persistence.entity.PlanWorksheetDetail;
import th.co.baiwa.excise.ta.persistence.entity.analysis.PlanWorksheetHeader;
import th.co.baiwa.excise.ta.persistence.repository.PlanFromWsHeaderRepository;
import th.co.baiwa.excise.ta.persistence.repository.PlanWorksheetHeaderRepository;

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

	public void findExciseIdOrderByPercenTax(Date startDate , Date endDate , int monthNotPayRisk , int percentDiff) throws SQLException{
		//budgetYear 2561  01/10/2560 -> 30/09/2561
		Calendar calendarStartDate = Calendar.getInstance(DateConstant.LOCAL_TH);
		calendarStartDate.setTime(startDate);
		Calendar calendarEndDate = Calendar.getInstance(DateConstant.LOCAL_TH);
		calendarEndDate.setTime(endDate);
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
		for (PlanWorksheetHeader plan : planWorksheetHeaderList) {
			validateCondition(plan , monthLIst , monthNotPayRisk,percentDiff);
		}
	}

	private void validateCondition(PlanWorksheetHeader plan , List<String> monthList ,int monthNotPayRisk , int percentDiff) {
		PlanWorksheetDetail search = new PlanWorksheetDetail();
		search.setAnalysNumber(plan.getAnalysNumber());
		search.setExciseId(plan.getExciseId());
		List<PlanWorksheetDetail> dtlList = planWorksheetDetailDao.queryPlanWorksheetDetailCriteria(search);
		
		// field condition 1
		int countNotPayTaxMonth = 0;
		int maxNotPay = 0;
		
		
		
		for (PlanWorksheetDetail dtl : dtlList) {
			
			//######### condition 1########################
			if(monthList.indexOf(dtl.getMonth()) > -1) {
				if(maxNotPay < countNotPayTaxMonth) {
					maxNotPay = countNotPayTaxMonth;
				}
			}else {
				countNotPayTaxMonth ++;
			}
			//######### END condition 1####################
			
		}
		if(monthNotPayRisk >= maxNotPay) {
			plan.setFlag("S");
		}
		//######### condition 2########################
		// field condition 2
		double firstAmount = 0;
		double secondAmount = 0;
		for (String month : monthList) {
			firstAmount = 0;
			secondAmount = 0;
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
				if(percenDiff > 30) {
					plan.setFlag("S");
				}
			}
			
		}		
		//######### condition 3########################
		System.out.println("#########################");
	}

}
