package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ta.vo.TaFormTS0116Vo;

public class TaFormTS0116ServiceTest {
	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_16 + "." + FILE_EXTENSION.PDF;
	@Test
	public void test_generateReport() throws Exception {
		
		TaFormTS0116Service taFormTS0116Service = new TaFormTS0116Service();

		TaFormTS0116Vo formTS0116Vo = new TaFormTS0116Vo();
		formTS0116Vo.setExtraPercent(new BigDecimal(253));
		formTS0116Vo.setBeforeTaxAmt(new BigDecimal(253));
		formTS0116Vo.setBeforeFinePercent(new BigDecimal(253));
		formTS0116Vo.setBeforeFineAmt(new BigDecimal(253));
		formTS0116Vo.setBeforeExtraAmt(new BigDecimal(253));
		formTS0116Vo.setBeforeMoiAmt(new BigDecimal(253));
		formTS0116Vo.setBeforeSumAmt(new BigDecimal(253));
		formTS0116Vo.setAfterTaxAmt(new BigDecimal(253));
		formTS0116Vo.setAfterFinePercent(new BigDecimal(253));
		formTS0116Vo.setAfterFineAmt(new BigDecimal(253));
		formTS0116Vo.setAfterExtraAmt(new BigDecimal(253));
		formTS0116Vo.setAfterMoiAmt(new BigDecimal(253));
		formTS0116Vo.setAfterSumAmt(new BigDecimal(253));
		formTS0116Vo.setFinePercent(new BigDecimal(253));
		formTS0116Vo.setSignOfficerFullName("ธนพล ชัยภูมิ");
		formTS0116Vo.setSignOfficerPosition("ตรวจสอบภาษี");
		formTS0116Vo.setHeadOfficerComment("พลังงานทดแทนจากปาล์มน้ำมัน ซึ่งเป็นผล ");
		formTS0116Vo.setSignHeadOfficerFullName("ธนพล ชัยภูมิ");
		formTS0116Vo.setSignHeadOfficerPosition("ตรวจสอบภาษี");
		formTS0116Vo.setSignHeadOfficerDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0116Vo.setApproverComment("พลังงานทดแทนจากปาล์มน้ำมัน ซึ่งเป็นผล ");
		formTS0116Vo.setSignApproverFullName("ธนพล ชัยภูมิ");
		formTS0116Vo.setSignApproverPosition("ตรวจสอบภาษี");
		formTS0116Vo.setSignApproverDate(ConvertDateUtils.parseStringToDate("01/03/2562", ConvertDateUtils.DD_MM_YYYY));
		formTS0116Vo.setDocText("");
		formTS0116Vo.setDocDear("");
		formTS0116Vo.setFactoryName("โรงงานอุตสาหกรรม");
		formTS0116Vo.setFactoryType("1");
		formTS0116Vo.setNewRegId("");
		formTS0116Vo.setRequestType("");
		formTS0116Vo.setRequestReason("พลังงานทดแทนจากปาล์มน้ำมัน ซึ่งเป็นผลผลิตภายในประเทศ พร้อมทั้ง ขอให้ผลักดันผ่านกฎหมายโดยเร็ว");
		formTS0116Vo.setRequestDesc("พลังงานทดแทนจากปาล์มน้ำมัน ซึ่งเป็นผลผลิตภายในประเทศ พร้อมทั้ง ขอให้ผลักดันผ่านกฎหมายโดยเร็ว ");
		formTS0116Vo.setFineNoFlag("");
		formTS0116Vo.setFineRefrainFlag("");
		formTS0116Vo.setFindReduceFlag("");
		formTS0116Vo.setExtraNoFlag("");
		formTS0116Vo.setExtraReduceFlag("");

		byte[] reportFile = taFormTS0116Service.generateReport(formTS0116Vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}
}
