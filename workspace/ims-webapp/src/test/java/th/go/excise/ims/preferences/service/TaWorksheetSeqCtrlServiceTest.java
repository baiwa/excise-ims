package th.go.excise.ims.preferences.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaWorksheetSeqCtrlServiceTest {
	private static final Logger logger = LoggerFactory.getLogger(TaWorksheetSeqCtrlServiceTest.class);
	
	@Autowired
	private TaWorksheetSeqCtrlService taWorksheetSeqCtrlService;
	
	
	@Test
	public void getAnalysisNumber() {
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010001", "2561"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2561"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010001", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2564"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010001", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2564"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("020000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("020001", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getAnalysisNumber("010000", "2562"));
	}
	
	
	@Test
	public void getDraftNumber() {
		logger.info(taWorksheetSeqCtrlService.getDraftNumber("010000", "2561"));
		logger.info(taWorksheetSeqCtrlService.getDraftNumber("010000", "2561"));
		logger.info(taWorksheetSeqCtrlService.getDraftNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getDraftNumber("020000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getDraftNumber("010000", "2569"));
		logger.info(taWorksheetSeqCtrlService.getDraftNumber("020000", "2569"));
		logger.info(taWorksheetSeqCtrlService.getDraftNumber("010000", "2562"));
		logger.info(taWorksheetSeqCtrlService.getDraftNumber("010000", "2562"));
		
	}
	
}
