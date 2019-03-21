package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.vo.TaFormTS0101Vo;

public class TaFormTS0101ServiceTest {

	private TaFormTS0101Service taFormTS0101Service = new TaFormTS0101Service();

	@Test
	public void test_exportTaFormTS0101() throws Throwable, IOException {

		TaFormTS0101Vo data = new TaFormTS0101Vo();
		// set data
		data.setNewRegId("123456789");
		data.setFactoryName("บริษัท อูซูอิ อินเตอร์เนชั่นแนลคอร์ปอเรชั่น (ไทยแลนด์) จำกัด");
		data.setFactoryTypeText("ขายส่งและผู้ผลิตชิ้นส่วนและอะไหล่รถยนต์");
		data.setFactoryAddress("700/454 หมู่ 7 นิคมอุตสาหกรรมอมตะนคร ตำบลดอนหัว อำเภอเมืองชลบุรี จังหวัดชลบุรี 20000");
		data.setAnalysisDateStartStr("19/03/2562");
		data.setAnalysisDateEndStr("20/04/2562");
		data.setAnalysisData1("ข้อมูลการนำเข้าวัตถุดิบ");
		data.setAnalysisData2("ข้อมูลการส่งออกสินค้า");
		data.setAnalysisData3("");
		data.setAnalysisData4("");
		data.setAnalysisData5("");
		data.setAnalysisResultDear("นายประวิช  เจริญสุข");
		data.setAnalysisResultText("มีการชำระภาษีไม่ถูกต้อง");
		data.setCallAuditFlag("1");
		data.setOtherText("");
		data.setSignOfficerFullName("นายอภิรักษ์  ชูใจ");
		data.setSignSupOfficerFullName("นายสิทธิ์ศักดฺ์  ใจชื่น");
		data.setSignOfficerDateStr("10/03/2562");
		data.setApprovedFlag("1");
		data.setSignApprOfficerFullName("นายปฏิพงษ์  เสรีไทย");
		data.setSignApprOfficerPosition("ผู้อำนวยการเขต");
		data.setSignApprDateStr("12/03/2562");
		byte[] reportFile = taFormTS0101Service.exportTaFormTS0101(data);
		IOUtils.write(reportFile, new FileOutputStream(
				new File(PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_01 + "." + FILE_EXTENSION.PDF)));
	}
}
