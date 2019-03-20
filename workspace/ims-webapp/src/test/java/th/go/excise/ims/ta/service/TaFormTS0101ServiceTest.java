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

		byte[] reportFile = taFormTS0101Service.exportTaFormTS0101(data);
		IOUtils.write(reportFile, new FileOutputStream(
				new File(PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_01 + "." + FILE_EXTENSION.PDF)));
	}
}
