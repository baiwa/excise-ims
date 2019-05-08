package th.go.excise.ims.ia.service;

import java.io.File;
import java.io.FileOutputStream;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ia.vo.IaEmpWorkingHdrVo;

public class Int090102ServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;

	@Test
	public void test_generateReport() throws Exception {
		Int090102Service int090102Service = new Int090102Service();

		IaEmpWorkingHdrVo formVo = new IaEmpWorkingHdrVo();
		formVo.setWorkingMonth("05/05/2562");

		byte[] reportFile = int090102Service.generateReport(formVo);
		IOUtils.write(reportFile,
				new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.IA_EMP_WORKING))));
	}
}
