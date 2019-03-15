package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.vo.PlanMapVo;
import th.go.excise.ims.ta.vo.PlanWorksheetVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class PlanWorksheetServiceTest {

	@Autowired
	private PlanWorksheetService planWorksheetService;

	@Autowired
	private TaPlanWorksheetDtlRepository planWorksheetDtlRepository;

	// @Test
	// public void test_savePlanWorksheetHdr() {
	// PlanWorksheetVo formVo = new PlanWorksheetVo();
	// formVo.setBudgetYear("2562");
	// formVo.setAnalysisNumber("000000-2562-000023");
	// formVo.setSendAllFlag(FLAG.N_FLAG);
	// planWorksheetService.savePlanWorksheetHdr(formVo);
	// }

	// @Test
	// public void test_savePlanWorksheetDtl() {
	// PlanWorksheetVo formVo = new PlanWorksheetVo();
	// formVo.setAnalysisNumber("000000-2562-000001");
	// formVo.setPlanNumber("000000-2562-000008");
	// formVo.setIds(Arrays.asList("2538005059", "2538005097", "2539000333",
	// "2541008744"));
	// planWorksheetService.savePlanWorksheetDtl(formVo);
	// }

	@Test
	public void test_findByInBudgetYearPlanDtl() {

		PlanWorksheetVo formVo = new PlanWorksheetVo();
		List<String> bs = new ArrayList<>();
		
		bs.add("2562");
		bs.add("2561");
		bs.add("2560");
		List<PlanMapVo> list = planWorksheetDtlRepository.findByInBudgetYearPlanDtl(bs);

		Map<String, String> map = new HashMap<String, String>();
		for (PlanMapVo obj : list) {
			map.put(obj.getBudgetYear()+obj.getNewRegId(), obj.getAuditPlanCode());
		}
		System.out.println(map);
		System.out.println(map.get("2538005578"));
		System.out.println(map.get("2538005578"));
		
	}

//	public static void main(String[] args) {
//		Map<String, Integer> hm = new HashMap<String, Integer>();
//		hm.put("a", new Integer(100));
//		hm.put("b", new Integer(200));
//		hm.put("c", new Integer(300));
//		hm.put("d", new Integer(400));
//
//		// Returns Set view
//		Set<Map.Entry<String, Integer>> st = hm.entrySet();
//
//		for (Map.Entry<String, Integer> me : st) {
//			System.out.print(me.getKey() + ":");
//			System.out.println(me.getValue());
//		}
//	}
}
