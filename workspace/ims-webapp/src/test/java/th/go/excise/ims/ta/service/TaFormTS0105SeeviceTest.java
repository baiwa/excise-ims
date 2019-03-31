package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ta.vo.TaFormTS0105Vo;

public class TaFormTS0105SeeviceTest {
	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_05 + "." + FILE_EXTENSION.PDF;

	@Test
	public void test_generateReport() throws Exception {

		TaFormTS0105Service taFormTS0105Service = new TaFormTS0105Service();
		TaFormTS0105Vo formTS0105Vo = new TaFormTS0105Vo();
		formTS0105Vo.setBookNumber1("253");
		formTS0105Vo.setBookNumber2("325");
		formTS0105Vo.setOfficeName("yyyyyy");
		formTS0105Vo.setDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setDocDear("yyyyyyy");
		formTS0105Vo.setRefBookNumber1("523");
		formTS0105Vo.setRefBookNumber2("632");
		formTS0105Vo.setRefDocDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setRefDocSend("yyyyyyy");
		formTS0105Vo.setCaseDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setCaseTime("13.00");
		formTS0105Vo.setDestText("yyyyyyy");
		formTS0105Vo.setDestDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0105Vo.setDestTime("11.30");
		formTS0105Vo.setSignOfficerFullName("ธนพล ชัยภูมิ");
		formTS0105Vo.setSignOfficerPosition("yyyyyyy");
		formTS0105Vo.setOtherText("");
		formTS0105Vo.setOtherPhone("0933413252");
		
		byte[] reportFile = taFormTS0105Service.generateReport(formTS0105Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));

	}
}
