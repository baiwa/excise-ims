package th.go.excise.ims.ta.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastYearVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class AnalysisIncomeCompareLastYearServiceTest {
	
	@Autowired
	private AnalysisIncomeCompareLastYearService analysisIncomeCompareLastYearService;
	
	@Test
	public void test_inquiry() {
		AnalysisFormVo formVo = new AnalysisFormVo();
		formVo.setNewRegId("07755390005721001");
		formVo.setDutyGroupId("0201");
		formVo.setStartDate("01/2561");
		formVo.setEndDate("06/2561");
		
		DataTableAjax<AnalysisIncomeCompareLastYearVo> dataTable = analysisIncomeCompareLastYearService.inquiry(formVo);
		for (AnalysisIncomeCompareLastYearVo vo : dataTable.getData()) {
			System.out.println("taxMonth=" + vo.getTaxMonth() +
				"\tlast=" + vo.getIncomeLastYearAmt() +
				"\tcurrent=" + vo.getIncomeCurrentYearAmt() +
				"\tdiffAmt=" + vo.getDiffIncomeAmt() +
				"\tdiffPnt=" + vo.getDiffIncomePnt());
		}
		
	}
	
}
