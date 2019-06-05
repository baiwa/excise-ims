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
import th.go.excise.ims.ta.vo.BasicAnalysisTaxValueVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class BasicAnalysisTaxValueServiceTest {

	@Autowired
	private BasicAnalysisTaxValueService basicAnalysisTaxValueService;
	
//	@Test
	public void test_inquiryByWs() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("01075440001081002");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("01/2562");
		formVo.setEndDate("06/2562");
		
		List<BasicAnalysisTaxValueVo> voList = basicAnalysisTaxValueService.inquiryByWs(formVo);
		for (BasicAnalysisTaxValueVo vo : voList) {
			System.out.println("GoodsDescText=" + vo.getGoodsDescText() +
				"\tTaxQty=" + vo.getTaxQty() +
				"\tInformPrice=" + vo.getInformPrice() +
				"\tGoodsValueAmt=" + vo.getGoodsValueAmt());
		}		
	}
	
//	@Test
	public void test_save() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("01075440001081002");
		formVo.setPaperBaNumber("PaperBaNumber");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("01/2562");
		formVo.setEndDate("06/2562");
		basicAnalysisTaxValueService.save(formVo);
		System.out.println("************************Is successfully saved************************");
	}
	
	@Test
	public void test_inquiryByPaperBaNumber() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("01075440001081002");
		formVo.setPaperBaNumber("PaperBaNumber");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("01/2562");
		formVo.setEndDate("06/2562");
		List<BasicAnalysisTaxValueVo> voList = basicAnalysisTaxValueService.inquiryByPaperBaNumber(formVo);
		for (BasicAnalysisTaxValueVo vo : voList) {
			System.out.println("GoodsDescText=" + vo.getGoodsDescText() +
					"\tTaxQty=" + vo.getTaxQty() +
					"\tInformPrice=" + vo.getInformPrice() +
					"\tGoodsValueAmt=" + vo.getGoodsValueAmt());
		}
	}
}
