package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class IaGfmovementAccountServiceTest {
//	@Autowired
//	private IaGfledgerAccountService iaGfledgerAccountService;
	
	@Test 
	public void addDataByExcel() throws FileNotFoundException {
		
		IaGfledgerAccountService iaGfledgerAccountService = new IaGfledgerAccountService();
		iaGfledgerAccountService.addDataByExcel(new File("F:\\เอกสารพี่นก\\excel\\02-05-2562\\รายงานเคลื่อนไหวเงินฝากคลัง.xlsx"));
	}
}
