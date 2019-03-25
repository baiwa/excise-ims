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
import th.go.excise.ims.ta.vo.TaFormTS0107Vo;

public class TaFormTS0107ServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_07 + "." + FILE_EXTENSION.PDF;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0107Service taFormTS0107Service = new TaFormTS0107Service();

		// set data
		TaFormTS0107Vo formTS0107Vo = new TaFormTS0107Vo();
		formTS0107Vo.setBookNumber1("กข02001");
		formTS0107Vo.setBookNumber2("200");
		formTS0107Vo.setOfficeName1("กรมสรรพสามิต");
		formTS0107Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		formTS0107Vo.setOfficeName2("กรมสรรพสามิต");
		formTS0107Vo.setHeadOfficerFullName("กรมสรรพสามิต");
		formTS0107Vo.setHeadOfficerPosition("เจ้าหน้าที่ออกตรวจภาษี ระดับภาค");
		formTS0107Vo.setOfficerFullName1("นายอนุชา จันทร์แก้ว");
		formTS0107Vo.setOfficerPosition1("เจ้าหน้าที่ออกตรวจภาษี ระดับพื้นที่");
		formTS0107Vo.setOfficerFullName2("นายชัยชนะ ใจดี");
		formTS0107Vo.setOfficerPosition2("เจ้าหน้าที่ออกตรวจภาษีระดับเขต");
		formTS0107Vo.setOfficerFullName3("");
		formTS0107Vo.setOfficerPosition3("");
		formTS0107Vo.setOfficerFullName4("");
		formTS0107Vo.setOfficerPosition4("");
		formTS0107Vo.setOfficerFullName5("");
		formTS0107Vo.setOfficerPosition5("");

		formTS0107Vo.setCompanyName("บริษัท โฮมโปรดักส์ เซ็นเตอร์ จำกัด (มหาชน)");
		// type 1 =โรงอุตสาหกรรม || 2=สถานบริการ || 3 =สถานประกอบการผู้นำเข้า
		formTS0107Vo.setFactoryType("3");

		formTS0107Vo.setFactoryName("โฮมโปรดักส์ เซ็นเตอร์ จำกัด (มหาชน) ");
		formTS0107Vo.setNewRegId("1002223344");
		formTS0107Vo.setFacAddrNo("96/27");
		formTS0107Vo.setFacMooNo("9");
		formTS0107Vo.setFacSoiName("-");
		formTS0107Vo.setFacThnName("ประชาชื่น  ");
		formTS0107Vo.setFacTambolName("บางเขน  ");
		formTS0107Vo.setFacAmphurName("เมือง");
		formTS0107Vo.setFacProvinceName("นนทบุรี ");
		formTS0107Vo.setFacZipCode("11000");
		formTS0107Vo.setAuditDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 17))));
		formTS0107Vo.setLawSection("7");
		formTS0107Vo.setHeadOfficerPhone("092-2344545");
		formTS0107Vo.setSignOfficerFullName("นายมงคล อาสว่าง");
		formTS0107Vo.setSignOfficerPosition("ผู้อำนวยการเขต");
		formTS0107Vo.setOtherText("");
		formTS0107Vo.setOtherPhone("");

		byte[] reportFile = taFormTS0107Service.generateReport(formTS0107Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}

}
