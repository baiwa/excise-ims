package th.go.excise.ims.ws.client.other.getjsonpy2.service;

import java.io.IOException;
import java.util.List;

import javax.xml.crypto.Data;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ws.client.other.getjsonpy2.model.Datum;
import th.go.excise.ims.ws.client.other.getjsonpy2.model.DivDetail;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithMockUser(username = "admin", roles = { "ADMIN", "USER" })
@ActiveProfiles(value = PROFILE.UNITTEST)
public class GetJsonPy2ServiceTest {

	@Autowired
	private GetJsonPy2Service getJsonPy2Service;

	@Test
	public void xxxx() throws IOException {
		List<Datum> xxx = getJsonPy2Service.callGetJsonPy2("2561", "010000");
		for (Datum datum : xxx) {
			System.out.println(datum.getFormCode());
			List<DivDetail> divDetailList = datum.getDivDetail();
			for (DivDetail dtl : divDetailList) {
				System.out.println(dtl.getDivName());
				
			}
		}
	}
}
