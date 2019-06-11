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
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisTaxValueVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "ta001402", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class BasicAnalysisTaxValueServiceTest {

	@Autowired
	private BasicAnalysisTaxValueService basicAnalysisTaxValueService;
	
	@Test
	public void test_inquiry() {
		BasicAnalysisFormVo formVo = new BasicAnalysisFormVo();
		formVo.setNewRegId("01075440001081002");
		formVo.setDutyGroupId("0101");
		formVo.setStartDate("01/2562");
		formVo.setEndDate("06/2562");
		//formVo.setPaperBaNumber("PaperBaNumber");
		
		List<BasicAnalysisTaxValueVo> voList = basicAnalysisTaxValueService.inquiry(formVo);
		for (BasicAnalysisTaxValueVo vo : voList) {
			System.out.println(ToStringBuilder.reflectionToString(vo, ToStringStyle.SHORT_PREFIX_STYLE));
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
	
}
