package th.go.excise.ims.ta.persistence.repository;

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
@WithUserDetails(value = "ta001401", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaWsInc8000MRepositoryImplTest {
	
	@Autowired
	private TaWsInc8000MRepository taWsInc8000MRepository;
	
	@Test
	public void test_findByMonthRangeDuty() {
		String newRegId = "01075470007111001";
		String dutyCode = "0101";
		String startMonth = "201505";
		String endMonth = "201704";
		
		long start = System.currentTimeMillis();
		taWsInc8000MRepository.findByMonthRangeDuty(newRegId, dutyCode, startMonth, endMonth);
		long end = System.currentTimeMillis();
		System.out.println(String.format("Process Success, using %s seconds", (float) (end - start) / 1000F));
	}
	
}
