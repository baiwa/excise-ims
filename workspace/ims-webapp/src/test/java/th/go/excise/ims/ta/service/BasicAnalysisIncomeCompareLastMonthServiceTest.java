package th.go.excise.ims.ta.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisIncomeCompareLastMonthVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class BasicAnalysisIncomeCompareLastMonthServiceTest {

	@Autowired
	private BasicAnalysisIncomeCompareLastMonthService basicAnalysisIncomeCompareLastMonthService;

	@Test
	public void test_inquiry() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("07755390005721001");
		formVo.setDutyGroupId("0201");
		formVo.setStartDate("01/2561");
		formVo.setEndDate("06/2561");
		
		List<BasicAnalysisIncomeCompareLastMonthVo> voList = basicAnalysisIncomeCompareLastMonthService.inquiry(formVo);
		for (BasicAnalysisIncomeCompareLastMonthVo vo : voList) {
			System.out.println("taxMonth=" + vo.getTaxMonth() +
				"\tincomeAmt=" + vo.getIncomeAmt() +
				"\tdiffAmt=" + vo.getDiffIncomeAmt() +
				"\tdiffPnt=" + vo.getDiffIncomePnt());
		}
		
	}
	
}
