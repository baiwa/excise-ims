package th.go.excise.ims.ta.persistence.repository.jdbc;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaMasCondDtlTaxJdbcRepositoryTest {

	@Autowired 
	TaMasCondDtlTaxJdbcRepository taMasCondDtlTaxJdbcRepository;
	
//	@Test
//	public void xxxxx() {
//		System.out.println(new Date());
//		taMasCondDtlTaxJdbcRepository.calPack();
//		System.out.println(new Date());
//	}
	
}
