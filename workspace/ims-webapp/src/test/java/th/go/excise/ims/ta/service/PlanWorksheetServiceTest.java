package th.go.excise.ims.ta.service;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class PlanWorksheetServiceTest {
	
	@Autowired
	private PlanWorksheetService planWorksheetService;
	
	@Test
	public void test_savePlanWorksheetHdr() {
		PlanWorksheetVo formVo = new PlanWorksheetVo();
		formVo.setBudgetYear("2562");
		formVo.setAnalysisNumber("000000-2562-000023");
		formVo.setSendAllFlag(FLAG.N_FLAG);
		planWorksheetService.savePlanWorksheetHdr(formVo);
	}
	
//	@Test
	public void test_savePlanWorksheetDtl() {
		PlanWorksheetVo formVo = new PlanWorksheetVo();
		formVo.setAnalysisNumber("000000-2562-000001");
		formVo.setPlanNumber("000000-2562-000008");
		formVo.setIds(Arrays.asList(
			"2538005059",
			"2538005097",
			"2539000333",
			"2541008744"
		));
		planWorksheetService.savePlanWorksheetDtl(formVo);
	}
	
}
