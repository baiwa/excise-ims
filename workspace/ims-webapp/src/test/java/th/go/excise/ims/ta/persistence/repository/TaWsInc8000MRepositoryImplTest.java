package th.go.excise.ims.ta.persistence.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_CONFIG;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.ParameterInfoRepository;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001401", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaWsInc8000MRepositoryImplTest {
	
	@Autowired
	private TaWsInc8000MRepository taWsInc8000MRepository;
	@Autowired
	private ParameterInfoRepository parameterInfoRepository;
	
	@Test
	public void test_findByMonthRangeDuty() {
		String newRegId = "01075470007111001";
		String dutyCode = "0101";
		String startMonth = "201505";
		String endMonth = "201704";
		
		//==> Check TAX, NET
		String incomeTaxType = null;
		ParameterInfo taxType = parameterInfoRepository.findByParamGroupCodeAndParamCode(PARAM_GROUP.TA_CONFIG, TA_CONFIG.INCOME_TYPE);
		if (taxType != null) {
			incomeTaxType = taxType.getValue1();
		}
		
		long start = System.currentTimeMillis();
		taWsInc8000MRepository.findByMonthRangeDuty(newRegId, dutyCode, startMonth, endMonth, incomeTaxType);
		long end = System.currentTimeMillis();
		System.out.println(String.format("Process Success, using %s seconds", (float) (end - start) / 1000F));
	}
	
}
