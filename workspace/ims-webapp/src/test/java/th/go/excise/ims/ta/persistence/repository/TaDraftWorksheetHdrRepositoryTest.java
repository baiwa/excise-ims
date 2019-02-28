package th.go.excise.ims.ta.persistence.repository;

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
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.YearMonthVo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaDraftWorksheetHdrRepositoryTest {
	
	@Autowired
	private TaDraftWorksheetHdrRepository taDraftWorksheetHdrRepository;
	
	@Test
	public void test_findAllDraftNumber() {
		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
		String budgetYear = "2562";
		
		List<String> draftNumberList1 = taDraftWorksheetHdrRepository.findAllDraftNumberByOfficeCodeAndBudgetYear(officeCode, budgetYear);
		draftNumberList1.forEach(System.out::println);
	}
	
	@Test
	public void test_findMonthStart() {
		YearMonthVo yearMonthVo1 = taDraftWorksheetHdrRepository.findMonthStartByDraftNumber("000000-2562-000001");
		System.out.println(ToStringBuilder.reflectionToString(yearMonthVo1, ToStringStyle.JSON_STYLE));
	}
	
}
