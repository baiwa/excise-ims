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
import th.go.excise.ims.ta.vo.TaFormTS0104Vo;

public class TaFormTS0104ServiceTest {
	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_04 + "." + FILE_EXTENSION.PDF;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0104Service taFormTS0104Service = new TaFormTS0104Service();

		// set data
		TaFormTS0104Vo formTS0104Vo = new TaFormTS0104Vo();
		formTS0104Vo.setBookNumber1("กข0002");
		formTS0104Vo.setBookNumber2("0001");
		formTS0104Vo.setSubject1("");
		formTS0104Vo.setSubject2("");
		formTS0104Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 19))));
		formTS0104Vo.setDocTopic("การตรวจสอบภาษีสรรพสามิต");
		formTS0104Vo.setDocDear("คุณ สมชาย ใจดี");
		formTS0104Vo.setDocReference("ผู้จัดการบริษัท");
		formTS0104Vo.setDocRequire("เรื่องการส่งออกสินค้า");
		formTS0104Vo.setDestText("กรมสรรพสามิต");
		formTS0104Vo.setDestDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 20))));
		formTS0104Vo.setDestTime("09.30");
		formTS0104Vo.setDocPaper("เอกสารประกิจการที่เกี่ยวข้องกับภาษี");
		formTS0104Vo.setSignOfficerFullName("นายธนากร สรชัย");
		formTS0104Vo.setSignOfficerPosition("เจ้าหน้าภาษี");
		formTS0104Vo.setOtherText("");
		formTS0104Vo.setOtherPhone("082-4562626");
		
		byte[] reportFile = taFormTS0104Service.generateReport(formTS0104Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}
}
