package th.go.excise.ims.ta.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaxOperatorDatatableVo;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class DraftWorksheetServiceTest {
	
	@Autowired
	private DraftWorksheetService draftWorksheetService;
	
	@Test
	public void test_getPreviewData() {
		long start = System.currentTimeMillis();
		
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setDateStart("05/2558");
		formVo.setDateEnd("04/2560");
		formVo.setDateRange(24);
		formVo.setStart(0);
		formVo.setLength(20);
		
		List<TaxOperatorDatatableVo> taxOperatorDatatableVoList = draftWorksheetService.getPreviewData(formVo).getDatas();
		taxOperatorDatatableVoList.forEach(System.out::println);
		
		long end = System.currentTimeMillis();
		System.out.println("Process Success, using " + ((float) (end - start) / 1000F) + " seconds");
	}
	
	//@Test
	public void test_saveDraft() {
		long start = System.currentTimeMillis();
		
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setBudgetYear("2562");
		formVo.setOfficeCode("000000");
		formVo.setDateStart("05/2558");
		formVo.setDateEnd("04/2560");
		formVo.setDateRange(24);
		formVo.setStart(0);
		formVo.setLength(0);
		try {
			draftWorksheetService.saveDraftWorksheet(formVo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		System.out.println("Process Success, using " + ((float) (end - start) / 1000F) + " seconds");
	}

	//@Test
	public void testOffice() {
		String officeCode = "010110";
		if (StringUtils.isNotBlank(officeCode) && officeCode.length() == 6) {
			if ("000000".equals(officeCode)) {
				officeCode = null;
			} else if ("00".equals(officeCode.substring(officeCode.length() - 2, officeCode.length()))) {
				if ("00".equals(officeCode.substring(officeCode.length() - 4, officeCode.length() - 2))) {
					officeCode = officeCode.substring(0, officeCode.length() - 4) + "____";
				} else {
					officeCode = officeCode.substring(0, officeCode.length() - 2) + "__";
				}
			}

		}
		System.out.println(officeCode);
	}
	
}
