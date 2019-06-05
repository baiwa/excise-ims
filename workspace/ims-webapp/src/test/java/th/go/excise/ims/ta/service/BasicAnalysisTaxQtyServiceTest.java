package th.go.excise.ims.ta.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisIncomeCompareLastYearVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxQtyVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class BasicAnalysisTaxQtyServiceTest {
	@Autowired
	private BasicAnalysisTaxQtyService basicAnalysisTaxQtyService;
	
//	@Test
	public void test_inquiryByWs() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("01075440001081002");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("01/2562");
		formVo.setEndDate("06/2562");
		
		List<BasicAnalysisTaxQtyVo> voList = basicAnalysisTaxQtyService.inquiryByWs(formVo);
		for (BasicAnalysisTaxQtyVo vo : voList) {
			System.out.println("GoodsDesc=" + vo.getGoodsDesc() +
				"\tTaxQty=" + vo.getTaxQty() +
				"\tStatementTaxQty=" + vo.getMonthStatementTaxQty() +
				"\tDiffTaxQty=" + vo.getDiffTaxQty());
		}
	}
	
	@Test
	public void test_save() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("01075440001081002");
		formVo.setPaperBaNumber("PaperBaNumber");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("01/2562");
		formVo.setEndDate("06/2562");
		basicAnalysisTaxQtyService.save(formVo);
		System.out.println("************************Is successfully saved************************");
	}
}
