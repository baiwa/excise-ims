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
	
	public static final String RUNNING_TYPE_ANALYSIS = "A";
	public static final String RUNNING_TYPE_DRAFT = "D";

	@Autowired
	private TaWorksheetSeqCtrlRepository taWorksheetSeqCtrlRepository;

	public String getAnalysisNumber(String officeCode, String budgetYear) {
		logger.info("getAnalysisNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		return genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_ANALYSIS);
	}

	public String getDraftNumber(String officeCode, String budgetYear) {
		logger.info("getDraftNumber of officeCode : {} || budgetYear : {}", officeCode, budgetYear);
		return genarateRunningNumber(officeCode, budgetYear, RUNNING_TYPE_DRAFT);
	}

	private String genarateRunningNumber(String officeCode, String budgetYear, String runningType) {
		StringBuilder runningNumber = new StringBuilder(officeCode).append("-").append(budgetYear).append("-");
		TaWorksheetSeqCtrl taWorksheetSeqCtrl = taWorksheetSeqCtrlRepository.findByOfficeCodeAndBudgetYearAndRunningType(officeCode, budgetYear, runningType);
		if (taWorksheetSeqCtrl == null) {
			runningNumber.append(StringUtils.leftPad(String.valueOf(1), 6, "0"));
			taWorksheetSeqCtrl = new TaWorksheetSeqCtrl();
			taWorksheetSeqCtrl.setOfficeCode(officeCode);
			taWorksheetSeqCtrl.setBudgetYear(budgetYear);
			taWorksheetSeqCtrl.setRunningType(runningType);
			taWorksheetSeqCtrl.setRunningNumber(1);
		} else {
			taWorksheetSeqCtrl.setRunningNumber(taWorksheetSeqCtrl.getRunningNumber() + 1);
			runningNumber.append(StringUtils.leftPad(String.valueOf(taWorksheetSeqCtrl.getRunningNumber()), 6, "0"));
		}
		
		taWorksheetSeqCtrlRepository.save(taWorksheetSeqCtrl);
		logger.info("response runningNumber : {}", runningNumber.toString());
		
		return runningNumber.toString();
	}

}
