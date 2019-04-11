package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.vo.TaFormTS0109Vo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0109ServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;

	// @Autowired
	// private TaFormTS0113Service taFormTS0109Service;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0109Service taFormTS0109Service = new TaFormTS0109Service();

		TaFormTS0109Vo formVo = new TaFormTS0109Vo();
		formVo.setFormTsNumber("000000-2562-000126");
		formVo.setBookNumber1("12345");
		formVo.setBookNumber2("54321");
		formVo.setComdPlace("กรมสรรพสามิต");
		formVo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 2, 15))));
		formVo.setEvidenceReason("บริษัท เชลล์แห่งประเทศไทย จำกัด ");
		formVo.setNewRegId("01005150424621001");
		formVo.setDocText1("setDocText1");
		formVo.setDocText2("setDocText2");
		formVo.setDocText3("setDocText3");
		formVo.setHeadOfficerFullName("ผู้ดูแลระบบ000000");
		formVo.setHeadOfficerPosition("นามสกุล");
		formVo.setOfficerText("ผู้ติดตาม 1 2 3");
		formVo.setSearchPlace("ทำการตรวจค้นในสถานที่ที่น่าเป็นที่อยู่ของผู้กระทำความผิด");
		formVo.setSearchDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 30))));
		formVo.setSignOfficerFullName("ผู้ดูแลระบบ000000 นามสกุล");
		formVo.setSignOfficerPosition("สำนักงานสรรพสามิตส่วนกลาง");

		byte[] reportFile = taFormTS0109Service.generateReport(formVo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_09))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0109Service taFormTS0109Service = new TaFormTS0109Service();
		
		TaFormTS0109Vo formVo = new TaFormTS0109Vo();
		
		byte[] reportFile = taFormTS0109Service.generateReport(formVo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_09 + "_blank"))));
	}
	
}
