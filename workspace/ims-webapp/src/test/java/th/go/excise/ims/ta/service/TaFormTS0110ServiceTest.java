package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaFormTS0110Vo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0110ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_10 + "." + FILE_EXTENSION.PDF;
	
//	@Autowired
//	private TaFormTS0110Service taFormTS0110Service;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0110Service taFormTS0110Service = new TaFormTS0110Service();
		
		// set data
		TaFormTS0110Vo formTS0110Vo = new TaFormTS0110Vo();
		formTS0110Vo.setTestimonyOf("อคง.");
		formTS0110Vo.setTestimonyTopic("เบิกงบ");
		formTS0110Vo.setDocDate(new Date());
		formTS0110Vo.setOfficerFullName("สมพง");
		formTS0110Vo.setOfficerPosition("ผบทบ.");
		formTS0110Vo.setTestimonyFullName("สมหมาย");
		formTS0110Vo.setTestimonyAge("44");
		formTS0110Vo.setTestimonyNationality("ไทย");
		formTS0110Vo.setTestimonyRace("ไทย");
		formTS0110Vo.setTestimonyAddrNo("99");
		formTS0110Vo.setTestimonyBuildNameVillage("เทพรักษา");
		formTS0110Vo.setTestimonyFloorNo("-");
		formTS0110Vo.setTestimonyRoomNo("-");
		formTS0110Vo.setTestimonySoiName("7");
		formTS0110Vo.setTestimonyThnName("สายใย");
		formTS0110Vo.setTestimonyTambolName("สายโยก");
		formTS0110Vo.setTestimonyAmphurName("สายย่อ");
		formTS0110Vo.setTestimonyProvinceName("สายแล้ว");
		formTS0110Vo.setTestimonyZipCode("00770");
		formTS0110Vo.setTestimonyTelNo("02777777");
		formTS0110Vo.setTestimonyCardType("1");
//		data.setTestimonyCardOtherDesc("ไม่เลือก");
		formTS0110Vo.setTestimonyCardNo("741472");
		formTS0110Vo.setTestimonyCardSource("สนง.");
		formTS0110Vo.setTestimonyCardCountry("ไทย");
		formTS0110Vo.setTestimonyPosition("ท.");
		formTS0110Vo.setTestimonyFactoryFullName("สมศํกดิ์");
		formTS0110Vo.setNewRegId("2562000001");
		formTS0110Vo.setTestimonyText("ทดสอบการพิมพ์เอกสาร เพื่อการทดสอบ จึงทดสอบมาเพื่อทดสอบ");

		byte[] reportFile = taFormTS0110Service.generateReport(formTS0110Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}

}
