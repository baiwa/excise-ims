package th.go.excise.ims.ia.service;
import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class IaRepDisbPerMonthServiceTest {
	
	@Autowired
	private IaGftrialBalanceService iaRepDisbPerMonthService;
	
	@Test 
	public void addDataByExcel() {
		
		iaRepDisbPerMonthService.addDataByExcel(new File("F:/เอกสารพี่นก/รายงานงบทดลองหน่วยเบิกจ่ายรายเดือน.xlsx"));
	}
}
