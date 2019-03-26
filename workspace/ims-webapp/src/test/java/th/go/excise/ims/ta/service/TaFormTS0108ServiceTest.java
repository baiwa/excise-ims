package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.ta.vo.TaFormTS0108DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS0108Vo;

public class TaFormTS0108ServiceTest {
	
	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_08 + "." + FILE_EXTENSION.PDF;
	
	@Test
	public void test_generateReport() throws Exception {
		TaFormTS0108Service service08 = new TaFormTS0108Service();
		
		TaFormTS0108Vo formTS0108Vo = new TaFormTS0108Vo();
		List<TaFormTS0108DtlVo> formTS0108DtlVoList = new ArrayList<>();
		TaFormTS0108DtlVo formTS0108DtlVo = null;
		for (int i = 0; i < 10; i++) {
			formTS0108DtlVo = new TaFormTS0108DtlVo();
			formTS0108DtlVo.setRecNo("1");
			formTS0108DtlVo.setAuditDate(new Date());
			formTS0108DtlVo.setOfficerFullName("ธนพล ชัยภูมิ");
			formTS0108DtlVo.setOfficerPosition("ตรวจสอบภาษี");
			formTS0108DtlVo.setAuditTime("13:30");
			formTS0108DtlVo.setAuditDest("โรงผลิตเหล้า");
			formTS0108DtlVo.setAuditTopic("การหมักเหล้า");
			formTS0108DtlVo.setApprovedAck("สุรศักดิ์ ");
			formTS0108DtlVo.setAuditComment("");
			formTS0108DtlVoList.add(formTS0108DtlVo);
		}
		formTS0108Vo.setTaFormTS0108DtlVoList(formTS0108DtlVoList);

		byte[] reportFile = service08.generateReport(formTS0108Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}
	
}
