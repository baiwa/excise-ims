package th.go.excise.ims.scheduler.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.config.AppConfigTest;
import th.go.excise.ims.common.constant.ProjectConstants.SCHEDULE_CODE;
import th.go.excise.ims.common.constant.ProjectConstants.WEB_SERVICE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = AppConfigTest.class)
public class MainScheduleServiceTest {
	
	@Autowired
	private MainScheduleService mainScheduleService;
	
//	@Test
	public void test_run_INCFRI8000() {
		String[] args = new String[] {
			SCHEDULE_CODE.INCFRI8000,
			WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME,
			"201907"
		};
		mainScheduleService.run(args);
	}
	
	@Test
	public void test_run_SUM8000M() {
//		String[] args = new String[] {
//			SCHEDULE_CODE.SUM8000M,
//			WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME,
//			"201801",
//			"201803"
//		};
		String[] args = new String[] {
			SCHEDULE_CODE.SUM8000M,
			WEB_SERVICE.INCFRI8000.DATE_TYPE_INCOME,
			"201907"
		};
		mainScheduleService.run(args);
	}
	
//	@Test
	public void test_run_REGFRI4000() {
		String[] args = new String[] {
			SCHEDULE_CODE.REGFRI4000
		};
		mainScheduleService.run(args);
	}
	
}
