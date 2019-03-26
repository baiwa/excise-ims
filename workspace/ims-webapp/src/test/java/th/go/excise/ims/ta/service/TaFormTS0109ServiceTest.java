package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.Date;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.vo.TaFormTS0109Vo;
import th.go.excise.ims.ta.vo.TaFormTS0112Vo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0109ServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_09 + "." + FILE_EXTENSION.PDF;

	// @Autowired
	// private TaFormTS0113Service taFormTS0109Service;

	@Test
	public void test_generateReport() throws Exception {
		System.out.println(ThaiBuddhistDate.of(2562, 1, 4));
		System.out.println(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4)));
		System.out.println(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));

		TaFormTS0109Service taFormTS0109Service = new TaFormTS0109Service();

		TaFormTS0109Vo formVo = new TaFormTS0109Vo();

		formVo.setBookNumber1("12345");
		formVo.setBookNumber2("54321");
		formVo.setComdPlace("กรมสรรพสามิต");
		formVo.setDocDate(new Date());
		formVo.setEvidenceReason("บริษัท เชลล์แห่งประเทศไทย จำกัด ");
		formVo.setNewRegId("01005150424621001");
		formVo.setDocText1("setDocText1");
		formVo.setDocText2("setDocText2");
		formVo.setDocText3("setDocText3");
		formVo.setHeadOfficerFullName("");
		formVo.setHeadOfficerPosition("ผู้ดูแลระบบ000000 นามสกุล");
		formVo.setOfficerText("ผู้ติดตาม 1 2 3");
		formVo.setSearchPlace("ท าการตรวจค้นในสถานท");
		formVo.setSearchDate(new Date());
		formVo.setSignOfficerFullName("ผู้ดูแลระบบ000000 นามสกุล");
		formVo.setSignOfficerPosition("สำนักงานสรรพสามิตส่วนกลาง");

		byte[] reportFile = taFormTS0109Service.generateReport(formVo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}
}
