package th.go.excise.ims.ia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import th.go.excise.ims.Application;
import th.go.excise.ims.ia.service.JobSystemUnworkingService;
import th.go.excise.ims.ws.client.pcc.systemunworking.service.SystemUnworkingService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestWsSystemUnworkingService {
	
	private Logger logger = LoggerFactory.getLogger(TestWsSystemUnworkingService.class);

	@Autowired
	private JobSystemUnworkingService systemUnworkingService;
	
	@Autowired
	private SystemUnworkingService wsSystemUnworkingService;
	
	@Test
	public void testSystemUnworkingService() throws Exception {
		try {
			
			String budgetYear = "2561";
			String month = "04";
			
			systemUnworkingService.runBatchSystemUnworking(budgetYear,month);
			
//			wsSystemUnworkingService.getRestFul(budgetYear);
		} catch (Exception e) {
			logger.info(" Test SystemUnworkingService : ",e);
		}
		
	}
}
