package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.vo.TaFormTS01141Vo;

public class TaFormTS01141ServiceTest {
	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_14_1 + "." + FILE_EXTENSION.PDF;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS01141Service taFormTS01141Service = new TaFormTS01141Service();

		TaFormTS01141Vo formTS01141Vo = new TaFormTS01141Vo();
		formTS01141Vo.setDocDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		formTS01141Vo.setDocDear("นาย วิทยารัตน์ สุรบดีพงษ์");
		formTS01141Vo.setFactoryName("ร้าน หอมจันทร์ฟราแกรนซ์");
		formTS01141Vo.setFactoryTypeText("ธุรกิจส่งออก");
		formTS01141Vo.setNewRegId("2557026887");
		formTS01141Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 18))));
		formTS01141Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 25))));
		formTS01141Vo.setAuditDesc("การเฝ้าดูภาวะการซื้อขายหลักทรัพย์ต่าง ๆ ในตลาดหลักทรัพย์หากพบภาวะการซื้อขายหลักทรัพย์ใดผิดไปจาก สภาพปกติที่ผ่านมา ก็จะทำการตรวจสอบข้อมูลต่าง ๆ");
		
		List<TaFormTS01141Vo> subFormTS01141VoList = new ArrayList<>();
		TaFormTS01141Vo subFormTS01141Vo = null;
		for (int i = 0; i < 2; i++) {
			subFormTS01141Vo = new TaFormTS01141Vo();
			subFormTS01141Vo.setPageNo(String.valueOf(i + 1));
			subFormTS01141Vo.setAuditDesc("ทดสอบข้อความในใบต่อ " + (i + 1));
			subFormTS01141VoList.add(subFormTS01141Vo);
		}
		formTS01141Vo.setTaFormTS01141VoList(subFormTS01141VoList);
		
		byte[] reportFile = taFormTS01141Service.generateReport(formTS01141Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}
}
