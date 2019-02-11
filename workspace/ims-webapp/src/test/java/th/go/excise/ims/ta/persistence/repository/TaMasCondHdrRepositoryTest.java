package th.go.excise.ims.ta.persistence.repository;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.persistence.entity.TaMasCondHdr;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaMasCondHdrRepositoryTest {
	
	@Autowired
	private TaMasCondHdrRepository taMasCondHdrRepository;
	
//	@Test
	public void test_insert() {
		TaMasCondHdr masCondHdr = new TaMasCondHdr();
		masCondHdr.setBudgetYear("2570");
		masCondHdr.setMonthNum(24);
		masCondHdr.setAreaSeeFlag(FLAG.N_FLAG);
		masCondHdr.setAreaSelectFlag(FLAG.N_FLAG);
		masCondHdr.setNoAuditYearNum(1);
		
		taMasCondHdrRepository.save(masCondHdr);
	}
	
	@Test
	public void test_findByBudgetYear() {
		TaMasCondHdr masCondHdr = taMasCondHdrRepository.findByBudgetYear("2570");
		System.out.println(ToStringBuilder.reflectionToString(masCondHdr, ToStringStyle.JSON_STYLE));
	}
	
}
