package th.go.excise.ims.scheduler.service;

import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.pcc.common.exception.PccRestfulException;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class SyncWsOasfri0100ServiceTest {
	
	@Autowired
	private SyncWsOasfri0100Service syncWsOasfri0100Service;
	
	@Test
	public void test_syncData() throws PccRestfulException {
		String newRegId = "09920020600391004";
		LocalDate dateStart = LocalDate.of(2018, 9, 1);
		LocalDate dateEnd = LocalDate.of(2018, 11, 1);
		List<LocalDate> localDateList = LocalDateUtils.getLocalDateRange(dateStart, dateEnd);
		
		for (LocalDate localDate : localDateList) {
			try {
				syncWsOasfri0100Service.syncData(newRegId, localDate);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
	
}
