package th.go.excise.ims.ta.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaFormTS0108Vo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0108ServiceTest {
	
	@Autowired
	private TaFormTS0108Service formTS0108Service;
	
	@Test
	public void getTS0108() {
		TaFormTS0108Vo ts = new TaFormTS0108Vo();
		ts = formTS0108Service.getTS0108();
		System.out.println("FormTsNumber ===> "+ts.getFormTsNumber());
		ts.getTaFormTS0108DtlVoList().forEach(System.out::println);
	}

}
