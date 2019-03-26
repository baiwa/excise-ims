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
import th.go.excise.ims.ta.vo.TaFormTS0112Vo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0112ServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_12 + "." + FILE_EXTENSION.PDF;

	// @Autowired
	// private TaFormTS0113Service taFormTS0113Service;

	@Test
	public void test_generateReport() throws Exception {
		System.out.println(ThaiBuddhistDate.of(2562, 1, 4));
		System.out.println(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4)));
		System.out.println(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));

		TaFormTS0112Service taFormTS0112Service = new TaFormTS0112Service();

		TaFormTS0112Vo formVo = new TaFormTS0112Vo();
		formVo.setDocPlace("กรมสรรพสามิต");
		formVo.setDocDate(new Date());
		formVo.setHeadOfficerFullName("ธีรวุฒิ กุลฤทธิชัย");
		formVo.setHeadOfficerPosition("เจ้าหน้าที่ตรวจสอบภาษี");
		formVo.setHeadOfficerOfficeName("สังกัด");
		formVo.setOfficerFullName1("สมพงษ์ คงมี");
		formVo.setOfficerPosition1("ตรวจ");
		formVo.setOfficerFullName2("จรัญ จำรูญ");
		formVo.setOfficerPosition2("ตรวจ");
		formVo.setOfficerFullName3("");
		formVo.setOfficerPosition3("");
		formVo.setOfficerFullName4("");
		formVo.setOfficerPosition4("");
		formVo.setOfficerFullName5("");
		formVo.setOfficerPosition5("");
		formVo.setFactoryName("บริษัท เชลล์แห่งประเทศไทย จำกัด ");
		formVo.setNewRegId("01005150424621001");
		formVo.setFacAddrNo("789");
		formVo.setFacSoiName("");
		formVo.setFacThnName("เพลินจิต");
		formVo.setFacTambolName("คลองเตย");
		formVo.setFacAmphurName("คลองเตย");
		formVo.setFacProvinceName("กรุงเทพมหานคร");
		formVo.setFacZipCode("10110");
		formVo.setOwnerFullName1("ผู้ดูแลระบบ000000 นามสกุล");
		formVo.setOwnerPosition("เจ้าหน้าที่ตรวจสอบภาษี");
		formVo.setOwnerOther("ผู้ดูแลระบบ000000 นามสกุล");
		formVo.setLawGroup("1");
		formVo.setSeizeDesc("รายละเอียด");
		formVo.setContactDesc("รายละเอียด");
		formVo.setOwnerFullName2("ผู้ดูแลระบบ000000 นามสกุล");
		formVo.setOwnerPosition2("เจ้าหน้าที่ตรวจสอบภาษี");
		formVo.setOwnerOther2("ผู้ดูแลระบบ000000 นามสกุล");
		formVo.setSignAuthFullName("ผู้ดูแลระบบ000000 นามสกุล");
		formVo.setSignInspectorFullName("ผู้ดูแลระบบ000000 นามสกุล");
		formVo.setSignWitnessFullName1("ผู้ดูแลระบบ000000 นามสกุล");
		formVo.setSignWitnessFullName2("ผู้ดูแลระบบ000000 นามสกุล");

		byte[] reportFile = taFormTS0112Service.generateReport(formVo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}
}
