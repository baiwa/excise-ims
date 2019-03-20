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
		data.setBookNumber1("ทดสอบ");
		data.setBookNumber2("ทดสอบ");
		data.setOfficeName1("ทดสอบ");

		data.setOfficeName2("ทดสอบ");
		data.setHeadOfficerFullName("ทดสอบ");
		data.setHeadOfficerPosition("ทดสอบ");
		data.setOfficerFullName1("ทดสอบ");
		data.setOfficerPosition1("ทดสอบ");
		data.setOfficerFullName2("ทดสอบ");
		data.setOfficerPosition2("ทดสอบ");
		data.setOfficerFullName3("ทดสอบ");
		data.setOfficerPosition3("ทดสอบ");
		data.setOfficerFullName4("ทดสอบ");
		data.setOfficerPosition4("ทดสอบ");
		data.setOfficerFullName5("ทดสอบ");
		data.setOfficerPosition5("ทดสอบ");

		data.setCompanyName("ทดสอบ");
		// type 1 =โรงอุตสาหกรรม || 2=สถานบริการ || 3 =สถานประกอบการผู้นำเข้า
		data.setFactoryType("1");

		data.setFactoryName("ทดสอบ");
		data.setNewRegId("ทดสอบ");
		data.setFacAddrNo("ทดสอย");
		data.setFacMooNo("ทดสอบ");
		data.setFacSoiName("ทดสอบ");
		data.setFacThnName("ทดสอบ");
		data.setFacTambolName("ทดสอบ");
		data.setFacAmphurName("ทดสอบ");
		data.setFacProvinceName("ทดสอบ");
		data.setFacZipCode("ทกสอบ");

		data.setLawSection("ทดสอบ");
		data.setHeadOfficerPhone("ทดสอบ");
		data.setSignFullName("ทดสอบ");
		data.setSignPosition("ทดสอบ");
		data.setOtherText("ทดสอบ");
		data.setOtherPhone("ทดสอบ");
		
		// Convert String to LocalDate
		LocalDate date1 = ConvertDateUtils.parseStringToLocalDate("12/03/2562", ConvertDateUtils.DD_MM_YYYY);
		data.setDocDate(date1);
		LocalDate date2 = ConvertDateUtils.parseStringToLocalDate("15/03/2562", ConvertDateUtils.DD_MM_YYYY);
		data.setAuditDate(date2);

		byte[] reportFile = taFormTS0107Service.exportTaFormTS0107(data);
		IOUtils.write(reportFile, new FileOutputStream(new File(PATH + NAME)));

	}
}
