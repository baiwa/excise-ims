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
import th.go.excise.ims.ta.vo.BasicAnalysisIncomeCompareLastYearVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class BasicAnalysisIncomeCompareLastYearServiceTest {
	
	@Autowired
	private BasicAnalysisIncomeCompareLastYearService basicAnalysisIncomeCompareLastYearService;
	
	@Test
	public void test_inquiry() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("07755390005721001");
		formVo.setDutyGroupId("0201");
		formVo.setStartDate("01/2561");
		formVo.setEndDate("06/2561");
		
		List<BasicAnalysisIncomeCompareLastYearVo> voList = basicAnalysisIncomeCompareLastYearService.inquiry(formVo);
		for (BasicAnalysisIncomeCompareLastYearVo vo : voList) {
			System.out.println("taxMonth=" + vo.getTaxMonth() +
				"\tlast=" + vo.getIncomeLastYearAmt() +
				"\tcurrent=" + vo.getIncomeCurrentYearAmt() +
				"\tdiffAmt=" + vo.getDiffIncomeAmt() +
				"\tdiffPnt=" + vo.getDiffIncomePnt());
		}
		
	}
	
}
