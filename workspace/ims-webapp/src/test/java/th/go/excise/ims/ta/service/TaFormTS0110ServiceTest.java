package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

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
import th.go.excise.ims.ta.vo.TaFormTS0110Vo;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0110ServiceTest {
	
	@Autowired
	private TaFormTS0110Service formTS0110Service;
	
	private static final String PATH = "/tmp/";
	private static final String NAME = "TaFormTS01_10.pdf";
	
	@Test
	public void test_exportTaFormTS0110() throws Throwable, IOException {

		TaFormTS0110Vo data = new TaFormTS0110Vo();
		
		//set  data
		data.setTestimonyOf("อคง.");
		data.setTestimonyTopic("เบิกงบ");
		data.setDocDate(LocalDate.now());
		data.setOfficerFullName("สมพง");
		data.setOfficerPosition("ผบทบ.");
		data.setTestimonyFullName("สมหมาย");
		data.setTestimonyAge("44");
		data.setTestimonyNationality("ไทย");
		data.setTestimonyRace("ไทย");
		data.setTestimonyAddrNo("99");
		data.setTestimonyBuildNameVillage("เทพรักษา");
		data.setTestimonyFloorNo("-");
		data.setTestimonyRoomNo("-");
		data.setTestimonySoiName("7");
		data.setTestimonyThnName("สายใย");
		data.setTestimonyTambolName("สายโยก");
		data.setTestimonyAmphurName("สายย่อ");
		data.setTestimonyProvinceName("สายแล้ว");
		data.setTestimonyZipCode("00770");
		data.setTestimonyTelNo("02777777");
		data.setTestimonyCardType("1");
//		data.setTestimonyCardOtherDesc("ไม่เลือก");
		data.setTestimonyCardNo("741472");
		data.setTestimonyCardSource("สนง.");
		data.setTestimonyCardCountry("ไทย");
		data.setTestimonyPosition("ท.");
		data.setTestimonyFactoryFullName("สมศํกดิ์");
		data.setNewRegId("2562000001");
		data.setTestimonyText("ทดสอบการพิมพ์เอกสาร เพื่อการทดสอบ จึงทดสอบมาเพื่อทดสอบ");

		byte[] reportFile = formTS0110Service.exportTaFormTS0110(data);
		IOUtils.write(reportFile, new FileOutputStream(new File(PATH + NAME)));

	}

}
