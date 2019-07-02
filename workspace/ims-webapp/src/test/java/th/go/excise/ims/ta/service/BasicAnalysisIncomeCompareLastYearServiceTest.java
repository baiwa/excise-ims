package th.go.excise.ims.ta.service;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_CONFIG;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisIncomeCompareLastYearVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class BasicAnalysisIncomeCompareLastYearServiceTest {
	
	@Autowired
	private BasicAnalysisIncomeCompareLastYearService basicAnalysisIncomeCompareLastYearService;
	
//	@Test
	public void test_inquiry() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("01075440001081002");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("07/2561");
		formVo.setEndDate("12/2561");
		formVo.setYearIncomeType(TA_CONFIG.INCOME_TYPE_NET);
		formVo.setYearNum("3");
		//formVo.setPaperBaNumber("PaperBaNumber");
		
		List<BasicAnalysisIncomeCompareLastYearVo> voList = basicAnalysisIncomeCompareLastYearService.inquiry(formVo);
		for (BasicAnalysisIncomeCompareLastYearVo vo : voList) {
			System.out.println(ToStringBuilder.reflectionToString(vo, ToStringStyle.SHORT_PREFIX_STYLE));
		}
	}
	
	@Test
	public void test_save() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("01075440001081002");
		formVo.setPaperBaNumber("PaperBaNumber");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("07/2561");
		formVo.setEndDate("12/2561");
		formVo.setYearIncomeType(TA_CONFIG.INCOME_TYPE_NET);
		formVo.setYearNum("3");
		basicAnalysisIncomeCompareLastYearService.save(formVo);
		System.out.println("************************Is successfully saved************************");
	}
	
}
