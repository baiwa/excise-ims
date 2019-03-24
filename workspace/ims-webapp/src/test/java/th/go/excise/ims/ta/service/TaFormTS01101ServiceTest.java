//package th.go.excise.ims.ta.service;
//
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//
//import org.apache.commons.io.IOUtils;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.security.test.context.support.WithUserDetails;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit4.SpringRunner;
//
//import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
//import th.go.excise.ims.Application;
//import th.go.excise.ims.ta.vo.TaFormTS01101Vo;
//
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
//public class TaFormTS01101ServiceTest {
//	
//	@Autowired
//	private TaFormTS01101Service formTS01101Service;
//
//	private static final String PATH = "/tmp/";
//	private static final String NAME = "TaFormTS01_10_1.pdf";
//	
//	@Test
//	public void test_exportTaFormTS01101() throws Throwable, IOException {
//		TaFormTS01101Service formTS01101Service = new TaFormTS01101Service();
//		TaFormTS01101Vo data = new TaFormTS01101Vo();
//		
//		data.setFormTsNumber("000000-2562-01");
//		data.setTestimonyOf("พล.อ.เทพพิทักษ์");
//		data.setTestimonyPageNo("01");
//		data.setTestimonyText("ทดสอบๆๆๆๆๆๆๆๆ");
//		
//		byte[] reportFile = formTS01101Service.exportTaFormTS01101(data);
//		IOUtils.write(reportFile, new FileOutputStream(new File(PATH + NAME)));
//	}
//}
