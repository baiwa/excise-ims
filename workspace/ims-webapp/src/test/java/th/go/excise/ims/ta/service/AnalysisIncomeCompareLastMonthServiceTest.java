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
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastMonthVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class AnalysisIncomeCompareLastMonthServiceTest {

	@Autowired
	private AnalysisIncomeCompareLastMonthService analysisIncomeCompareLastMonthService;

	@Test
	public void test_CompareLastMonth() {
		try {

			AnalysisFormVo formVo = new AnalysisFormVo();
			formVo.setNewRegId("2555024870");
			formVo.setStartDate("12/2559");
			formVo.setEndDate("09/2563");

			DataTableAjax<AnalysisIncomeCompareLastMonthVo> datasList = analysisIncomeCompareLastMonthService.getAnalysisIncomeCompareLastMonth(formVo);

			for (AnalysisIncomeCompareLastMonthVo data : datasList.getData()) {
				System.out.println("TaxMonth: " + data.getTaxMonth() + ", TaxAmount: " + data.getIncomeAmt() + ", DiffIncomeAmt: " + data.getDiffIncomeAmt() + ", DiffIncomePnt: " + data.getDiffIncomePnt());
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
}
