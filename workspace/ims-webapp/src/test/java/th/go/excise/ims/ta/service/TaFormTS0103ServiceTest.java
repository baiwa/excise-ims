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
import th.go.excise.ims.ta.vo.TaFormTS0103Vo;

public class TaFormTS0103ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0103Service taFormTS0103Service = new TaFormTS0103Service();

		// set data
		TaFormTS0103Vo formTS0103Vo = new TaFormTS0103Vo();
		formTS0103Vo.setFormTsNumber("000000-2562-000126");
		formTS0103Vo.setBookNumber1("กค0003");
		formTS0103Vo.setBookNumber2("03563");
		formTS0103Vo.setOfficeName1("สำนักงานสรรพสามิตภาคที่ 1");
		formTS0103Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 31))));
		formTS0103Vo.setDocDear("ผู้จัดการ โรงงานอุตสาหกรรม");
		formTS0103Vo.setFactoryType("1");
		formTS0103Vo.setFactoryName("บริษัท ไออาร์พีซี จำกัด (มหาชน) (คลังน้ำมันอยุธยา)");
		formTS0103Vo.setFacAddrNo("99");
		formTS0103Vo.setFacSoiName("");
		formTS0103Vo.setFacThnName("ท่าเรืออยุธยา");
		formTS0103Vo.setFacTambolName("โพธิ์เอน");
		formTS0103Vo.setFacAmphurName("ท่าเรือ");
		formTS0103Vo.setFacProvinceName("พระนครศรีอยุธยา");
		formTS0103Vo.setFacZipCode("13130");
		formTS0103Vo.setNewRegId("2541-07008-8");
		formTS0103Vo.setCompAddrNo("98");
		formTS0103Vo.setCompSoiName("ท่าเรืออยุธยา");
		formTS0103Vo.setCompThnName("ท่าเรืออยุธยา");
		formTS0103Vo.setCompTambolName("โพธิ์เอน");
		formTS0103Vo.setCompAmphurName("ท่าเรือ");
		formTS0103Vo.setCompProvinceName("พระนครศรีอยุธยา");
		formTS0103Vo.setCompZipCode("13130");
		formTS0103Vo.setReasonText("เจ้าหน้าที่พบความผิดปกติ ของงบเดือน");
		formTS0103Vo.setLawSection("34");
		formTS0103Vo.setLawGroup("1");
		formTS0103Vo.setDestText("คำอธิบาย ไงคับ เข้าใจไหม");
		formTS0103Vo.setDestDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 2, 28))));
		formTS0103Vo.setDestTime("10:00");
		formTS0103Vo.setDestDocDesc("เลี้ยวขวาที่ซอยสุทธิสารวินิจฉัย");
		formTS0103Vo.setSignOfficerFullName("นาย เตชภณ หิมารัตน์");
		formTS0103Vo.setSignOfficerPosition("เจ้าหน้าที่ตรวจสอบภาษี");
		formTS0103Vo.setOfficeName2("กรมสรรพสามิต");
		formTS0103Vo.setOfficePhone("02-345-2443");
		formTS0103Vo.setHeadOfficerFullName("นายปวิช เตชะไพบูลล์");
		
		byte[] reportFile = taFormTS0103Service.generateReport(formTS0103Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_03))));
	}
	
	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS0103Service taFormTS0103Service = new TaFormTS0103Service();
		
		// set data
		TaFormTS0103Vo formTS0103Vo = new TaFormTS0103Vo();
		
		byte[] reportFile = taFormTS0103Service.generateReport(formTS0103Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_03 + "_blank"))));
	}
	
}
