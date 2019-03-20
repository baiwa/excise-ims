package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ta.vo.TaFormTS0107Vo;

public class TaFormTS0107ServiceTest {

	private TaFormTS0107Service taFormTS0107Service = new TaFormTS0107Service();
	private static final String PATH = "/tmp/";
	private static final String NAME = "TaFormTS01_07.pdf";

	@Test
	public void test_exportTaFormTS0107() throws Throwable, IOException {

		TaFormTS0107Vo data = new TaFormTS0107Vo();
		// set data
		data.setBookNumber1("กข02001");
		data.setBookNumber2("200");
		data.setOfficeName1("กรมสรรพสามิต");

		data.setOfficeName2("กรมสรรพสามิต");
		data.setHeadOfficerFullName("นายธนาวุธ สุทธิสาร");
		data.setHeadOfficerPosition("เจ้าหน้าที่ออกตรวจภาษี ระดับภาค");
		data.setOfficerFullName1("นายอนุชา จันทร์แก้ว");
		data.setOfficerPosition1("เจ้าหน้าที่ออกตรวจภาษี ระดับพื้นที่");
		data.setOfficerFullName2("นายชัยชนะ ใจดี");
		data.setOfficerPosition2("เจ้าหน้าที่ออกตรวจภาษีระดับเขต");
		data.setOfficerFullName3("");
		data.setOfficerPosition3("");
		data.setOfficerFullName4("");
		data.setOfficerPosition4("");
		data.setOfficerFullName5("");
		data.setOfficerPosition5("");

		data.setCompanyName("บริษัท โฮมโปรดักส์ เซ็นเตอร์ จำกัด (มหาชน)");
		// type 1 =โรงอุตสาหกรรม || 2=สถานบริการ || 3 =สถานประกอบการผู้นำเข้า
		data.setFactoryType("3");

		data.setFactoryName("ทดสอบ");
		data.setNewRegId("ทดสอบ");
		data.setFacAddrNo("96/27");
		data.setFacMooNo("9");
		data.setFacSoiName("-");
		data.setFacThnName("ประชาชื่น  ");
		data.setFacTambolName("บางเขน  ");
		data.setFacAmphurName("เมือง");
		data.setFacProvinceName("นนทบุรี ");
		data.setFacZipCode("11000");

		data.setLawSection("");
		data.setHeadOfficerPhone("");
		data.setSignFullName("");
		data.setSignPosition("");
		data.setOtherText("");
		data.setOtherPhone("");
		
		// Convert String to LocalDate
		LocalDate date1 = ConvertDateUtils.parseStringToLocalDate("12/03/2562", ConvertDateUtils.DD_MM_YYYY);
		data.setDocDate(date1);
		LocalDate date2 = ConvertDateUtils.parseStringToLocalDate("15/03/2562", ConvertDateUtils.DD_MM_YYYY);
		data.setAuditDate(date2);

		byte[] reportFile = taFormTS0107Service.exportTaFormTS0107(data);
		IOUtils.write(reportFile, new FileOutputStream(new File(PATH + NAME)));

	}
}
