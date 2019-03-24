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
import th.go.excise.ims.ta.vo.TaFormTS0113Vo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS0113ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_13 + "." + FILE_EXTENSION.PDF;

//	@Autowired
//	private TaFormTS0113Service taFormTS0113Service;

	@Test
	public void test_generateReport() throws Exception {
		System.out.println(ThaiBuddhistDate.of(2562, 1, 4));
		System.out.println(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4)));
		System.out.println(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		
		TaFormTS0113Service taFormTS0113Service = new TaFormTS0113Service();

		TaFormTS0113Vo formTS0113Vo = new TaFormTS0113Vo();
		formTS0113Vo.setAuditDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS0113Vo.setAuditFinishTime("09:00");
		formTS0113Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 2, 3))));
		formTS0113Vo.setDocPlace("กรมสรรพสามิต");
		formTS0113Vo.setDocTime("09:00");
		formTS0113Vo.setFacAddrNo("45");
		formTS0113Vo.setFacAmphurName("คลองเตย");
		formTS0113Vo.setFacProvinceName("กรุงเทพมหานคร");
		formTS0113Vo.setFacSoiName("อุดมเกียรติ");
		formTS0113Vo.setFacTambolName("คลองเตย");
		formTS0113Vo.setFacThnName("ประชาชื่น");
		formTS0113Vo.setFacZipCode("10800");
		formTS0113Vo.setFactoryName("บริษัท สปอย จำกัด ");
		formTS0113Vo.setFactoryName2("บริษัท สปอย จำกัด");
		formTS0113Vo.setFactoryType("1");
		formTS0113Vo.setFormTsNumber("test");
		formTS0113Vo.setHeadOfficerFullName("ธีรวุฒิ กุลฤทธิชัย");
		formTS0113Vo.setHeadOfficerPosition("สำนักงานสรรพสามิตส่วนกลาง");
		formTS0113Vo.setNewRegId("01005150424621002");
		formTS0113Vo.setOwnerFullName("ธีรวุฒิ กุลฤทธิชัย");
		formTS0113Vo.setOwnerPosition("สำนักงานสรรพสามิตส่วนกลาง");
		formTS0113Vo.setRefBookDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 5, 1))));
		formTS0113Vo.setRefBookNumber1("test");
		formTS0113Vo.setSignOfficerFullName("test");
		formTS0113Vo.setSignOwnerFullName("ธีรวุฒิ กุลฤทธิชัย");
		formTS0113Vo.setSignWitnessFullName1("test");
		formTS0113Vo.setSignWitnessFullName2("test");

		byte[] reportFile = taFormTS0113Service.generateReport(formTS0113Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}
}
