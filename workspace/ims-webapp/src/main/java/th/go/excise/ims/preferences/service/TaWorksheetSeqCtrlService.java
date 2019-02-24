package th.go.excise.ims.preferences.service;

import java.math.BigDecimal;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.preferences.persistence.entity.TaWorksheetSeqCtrl;
import th.go.excise.ims.preferences.persistence.repository.TaWorksheetSeqCtrlRepository;

@Service
public class TaWorksheetSeqCtrlService {
	
	private static final Logger logger = LoggerFactory.getLogger(TaWorksheetSeqCtrlService.class);
	public static final String RUNNING_TYPE_A = "A";
	public static final String RUNNING_TYPE_D = "D";
	
	@Autowired
	private TaWorksheetSeqCtrlRepository taWorksheetSeqCtrlRepository;

	public String getAnalysisNumber(String officeCode , String budgetYear) {
		logger.info("getAnalysisNumber of officeCode : {} || budgetYear : {}" , officeCode , budgetYear);
		return genarateRunningNumberByType(officeCode, budgetYear, RUNNING_TYPE_A);
	}

	public String getDraftNumber(String officeCode , String budgetYear) {
		logger.info("getDraftNumber of officeCode : {} || budgetYear : {}" , officeCode , budgetYear);
		return genarateRunningNumberByType(officeCode, budgetYear, RUNNING_TYPE_D);
	}
	
	
	private String genarateRunningNumberByType(String officeCode , String budgetYear, String runningType) {
		StringBuilder runningNumber = new StringBuilder(officeCode).append("-").append(budgetYear).append("-");
		TaWorksheetSeqCtrl taWorksheetSeqCtrl = taWorksheetSeqCtrlRepository.findByOfficeCodeAndBudgetYearAndRunningType(officeCode, budgetYear, runningType);
		if(taWorksheetSeqCtrl == null) {
			runningNumber.append(StringUtils.leftPad(String.valueOf(1), 6, "0"));
			taWorksheetSeqCtrl = new TaWorksheetSeqCtrl();
			taWorksheetSeqCtrl.setOfficeCode(officeCode);
			taWorksheetSeqCtrl.setBudgetYear(budgetYear);
			taWorksheetSeqCtrl.setRunningType(runningType);
			taWorksheetSeqCtrl.setRunningNumber(BigDecimal.ONE);
			
		}else {
			taWorksheetSeqCtrl.setRunningNumber(taWorksheetSeqCtrl.getRunningNumber().add(BigDecimal.ONE));
			runningNumber.append(StringUtils.leftPad(String.valueOf(taWorksheetSeqCtrl.getRunningNumber()), 6, "0"));
			
		}
		taWorksheetSeqCtrlRepository.save(taWorksheetSeqCtrl);
		logger.info("response runningNumber : {}" , runningNumber.toString());
		return runningNumber.toString();
		
	}
	
	
}
