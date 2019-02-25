package th.go.excise.ims.ta.service;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaxOperatorFormVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class DraftWorksheetServiceTest {
	
	@Autowired
	private DraftWorksheetService draftWorksheetService;
	
	@Test
	public void test_getPreviewData() {
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setDateStart("05/2558");
		formVo.setDateEnd("04/2560");
		formVo.setDateRange(24);
		formVo.setStart(0);
		formVo.setLength(0);
		try {
			System.out.println(new Date());
			draftWorksheetService.getPreviewData(formVo);
			System.out.println(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void test_saveDraft() {
		TaxOperatorFormVo formVo = new TaxOperatorFormVo();
		formVo.setDateStart("05/2558");
		formVo.setDateEnd("04/2560");
		formVo.setDateRange(24);
		formVo.setStart(0);
		formVo.setLength(0);
		try {
			System.out.println(new Date());
			draftWorksheetService.saveDraft(formVo);
			System.out.println(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// @Test
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
