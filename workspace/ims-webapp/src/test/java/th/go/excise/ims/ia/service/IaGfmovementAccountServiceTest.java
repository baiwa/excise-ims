package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileNotFoundException;

import org.junit.Test;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class IaGfmovementAccountServiceTest {
//	@Autowired
//	private IaGfledgerAccountService iaGfledgerAccountService;
	
	@Test 
	public void addDataByExcel() throws FileNotFoundException {
		
		IaGfmovementAccountService iaGfledgerAccountService = new IaGfmovementAccountService();
		iaGfledgerAccountService.addDataByExcel(new File("F:\\เอกสารพี่นก\\excel\\02-05-2562\\รายงานเคลื่อนไหวเงินฝากคลัง.xlsx"));
	}
}
