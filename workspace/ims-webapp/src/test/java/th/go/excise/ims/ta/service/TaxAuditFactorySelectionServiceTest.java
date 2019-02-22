package th.go.excise.ims.ta.service;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;
import th.go.excise.ims.ta.vo.TaxOperatorVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaxAuditFactorySelectionServiceTest {

	@Autowired
	private TaxAuditFactorySelectionService taxAuditFactorySelectionService;
	
//	@Test
	public void test() {
//		taxAuditFactorySelectionService.selectFactoryProcess("20190211221720");
		try {
			System.out.println(new Date());
			taxAuditFactorySelectionService.selectFactoryProcess("20190214141302");
			System.out.println(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	@Test
	public void test_getPreviewData() {
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setDateStart("05/2558");
		formVo.setDateEnd("04/2560");
		formVo.setDateRange(24);
		
		try {
			System.out.println(new Date());
			TaxOperatorVo vo = taxAuditFactorySelectionService.getPreviewData(formVo);
			System.out.println(new Date());
			System.out.println(vo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
