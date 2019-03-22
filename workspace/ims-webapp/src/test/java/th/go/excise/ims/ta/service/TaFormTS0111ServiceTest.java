package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaFormTS0111Vo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0111ServiceTest {
	
	@Autowired
	private TaFormTS0111Service taFormTS0111Service;

	private static final String PATH = "/tmp/";
	private static final String NAME = "TaFormTS01_11.pdf";
	private static final String NAME2 = "TaFormTS01_11-2.pdf";
	
	@Test
	public void test_exportTaFormTS0111() throws Throwable, IOException {
		
		TaFormTS0111Vo data = new TaFormTS0111Vo();
		
		data.setDocPlace("สำนักงาน");
		data.setDocDate("22/03/2019");
		
		byte[] reportFile = taFormTS0111Service.exportTaFormTS0111(data);
		IOUtils.write(reportFile, new FileOutputStream(new File(PATH + NAME)));
	}
	@Test
	public void test_exportTaFormTS01112() throws Throwable, IOException {
		
		TaFormTS0111Vo data = new TaFormTS0111Vo();
	
		data.setAuthDate("22/03/2019");
		data.setAuthPosition("1");
		
		byte[] reportFile = formTS0111Service.exportTaFormTS01112(data);
		IOUtils.write(reportFile, new FileOutputStream(new File(PATH + NAME2)));
	}
}
