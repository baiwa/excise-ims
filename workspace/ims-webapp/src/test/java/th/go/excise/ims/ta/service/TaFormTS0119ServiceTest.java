package th.go.excise.ims.ta.service;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.vo.TaFormTS0119Vo;

public class TaFormTS0119ServiceTest {
	private TaFormTS0119Service taFormTS0119Service = new TaFormTS0119Service();
	
	@Test
	public void test_exportTaFormTS0107() throws Throwable, IOException {

		TaFormTS0119Vo data = new TaFormTS0119Vo();
		data.setBookNumber1("25632");
		data.setBookNumber2("632522");
		data.setDocText1("อํานาจในการอนุมัติให้ออกตรวจ");
		data.setDocText2("อํานาจในการอนุมัติให้ออกตรวจ");
		data.setDocText3("อํานาจในการอนุมัติให้ออกตรวจ");
		data.setDocDateStr("15/03/2562");;
		data.setDocDear("ผบตร ธนพล ชัยภูมิ");
		data.setCompanyName("โรงเหล้ายโสธร");
		data.setFactoryType("3");
		data.setFacThnName("โรงผลิตเหล้ายโสธร");
		data.setNewRegId("25564541585");
		data.setFacAddrNo("หนองนาคำ");
		data.setFacMooNo("20");
		data.setFacSoiName("หนองหินห่าว");
		data.setFacThnName("โคกนาแก");
		data.setFacTambolName("โคกอิโด่ย");
		data.setFacAmphurName("นาบักเหลี่ยม");
		data.setFacProvinceName("หนองบัวลำภู");
		data.setFacZipCode("39000");
		data.setFollowTypeFlag1("1");
		data.setFollowTypeFlag2("2");
		data.setRefBookDateStr("15/06/2561");
		data.setOfficeName1("");
		// Convert String to LocalDate
		

	byte[] reportFile = taFormTS0119Service.exportTaFormTS019(data);
	IOUtils.write(reportFile, new FileOutputStream(new File(PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_019 + "." + FILE_EXTENSION.PDF)));

	}
}
