package th.go.excise.ims.ta.service;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaWorksheetSeqCtrl;
import th.go.excise.ims.ta.persistence.repository.TaWorksheetSeqCtrlRepository;

@Service
public class WorksheetSequenceService {

	private static final Logger logger = LoggerFactory.getLogger(WorksheetSequenceService.class);
	
	private static final String RUNNING_TYPE_ANALYSIS = "A";
	private static final String RUNNING_TYPE_PLAN = "P";
	private static final int RUNNING_RANGE = 6;

	@Autowired
	private TaWorksheetSeqCtrlRepository taWorksheetSeqCtrlRepository;

	public String getAnalysisNumber(String officeCode, String budgetYear) {
		logger.info("getAnalysisNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		return genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_ANALYSIS);
	}

	public String getPlanNumber(String officeCode, String budgetYear) {
		logger.info("getDraftNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		return genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_PLAN);
	}

	private String genarateRunningNumber(String officeCode, String budgetYear, String runningType) {
		StringBuilder runningNumber = new StringBuilder(officeCode).append("-").append(budgetYear).append("-");
		TaWorksheetSeqCtrl taWorksheetSeqCtrl = taWorksheetSeqCtrlRepository.findByOfficeCodeAndBudgetYearAndRunningType(officeCode, budgetYear, runningType);
		if (taWorksheetSeqCtrl == null) {
			runningNumber.append(StringUtils.leftPad(String.valueOf(1), RUNNING_RANGE, "0"));
			taWorksheetSeqCtrl = new TaWorksheetSeqCtrl();
			taWorksheetSeqCtrl.setOfficeCode(officeCode);
			taWorksheetSeqCtrl.setBudgetYear(budgetYear);
			taWorksheetSeqCtrl.setRunningType(runningType);
			taWorksheetSeqCtrl.setRunningNumber(1);
		} else {
			taWorksheetSeqCtrl.setRunningNumber(taWorksheetSeqCtrl.getRunningNumber() + 1);
			runningNumber.append(StringUtils.leftPad(String.valueOf(taWorksheetSeqCtrl.getRunningNumber()), RUNNING_RANGE, "0"));
		}
		
		taWorksheetSeqCtrlRepository.save(taWorksheetSeqCtrl);
		logger.info("response runningNumber : {}", runningNumber.toString());
		
		return runningNumber.toString();
	}

}
