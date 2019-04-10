package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.PROFILE;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.go.excise.ims.Application;
import th.go.excise.ims.ta.vo.TaFormTS01142DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS01142Vo;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Application.class)
//@WithUserDetails(value = "admin", userDetailsServiceBeanName = "userDetailService")
//@ActiveProfiles(value = PROFILE.UNITTEST)
public class TaFormTS01142ServiceTest {

	private static final String REPORT_FILE = PATH.TEST_PATH + "%s" + "." + FILE_EXTENSION.PDF;

	@Autowired
	private TaFormTS01142Service taFormTS01142Service;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS01142Service formTS01142Service = new TaFormTS01142Service();

		TaFormTS01142Vo vo = new TaFormTS01142Vo();
		vo.setOwnerFullName("ธนพล ชัยภูมิ");
		vo.setFactoryType("");
		vo.setFactoryName("");
		vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
		vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 9))));
		vo.setDutyTypeText("");
		vo.setNewRegId("");
		vo.setExtraAmtDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 4, 1))));
		vo.setSignOwnerFullName("");
		vo.setSignOfficerFullName("");

		List<TaFormTS01142DtlVo> listItem = new ArrayList<>();
		TaFormTS01142DtlVo itemVo = new TaFormTS01142DtlVo();
		itemVo.setRecNo("");
		itemVo.setRecDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		itemVo.setDutyTypeText("");
		itemVo.setValueFromAudit(new BigDecimal(253));
		itemVo.setTaxRate(new BigDecimal(253));
		itemVo.setAuditTaxAmt(new BigDecimal(253));
		itemVo.setPaidTaxAmt(new BigDecimal(253));
		itemVo.setAddTaxAmt(new BigDecimal(253));
		itemVo.setAddFineAmt(new BigDecimal(253));
		itemVo.setAddExtraAmt(new BigDecimal(253));
		itemVo.setAddSumTaxAmt(new BigDecimal(253));
		itemVo.setAddMoiAmt(new BigDecimal(253));
		itemVo.setAddSumAllTaxAmt(new BigDecimal(253));
		itemVo.setAddMonthNum("");
		listItem.add(itemVo);
		vo.setTaFormTS01142DtlVoList(listItem);

		byte[] reportFile = formTS01142Service.generateReport(vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_14_2))));
	}

	@Test
	public void test_generateReport_Blank() throws Exception {
		TaFormTS01142Service formTS01142Service = new TaFormTS01142Service();

		TaFormTS01142Vo vo = new TaFormTS01142Vo();

		byte[] reportFile = formTS01142Service.generateReport(vo);
		IOUtils.write(reportFile, new FileOutputStream(new File(String.format(REPORT_FILE, REPORT_NAME.TA_FORM_TS01_14_2 + "_blank"))));
	}

	//@Test
	public void test_saveFormTS() throws Exception {
		TaFormTS01142Vo formTS01142Vo = new TaFormTS01142Vo();
		formTS01142Vo.setOwnerFullName("ธนพล ชัยภูมิ");
		formTS01142Vo.setFactoryType("");
		formTS01142Vo.setFactoryName("");
		formTS01142Vo.setAuditDateStart(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 4))));
		formTS01142Vo.setAuditDateEnd(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 1, 9))));
		formTS01142Vo.setDutyTypeText("");
		formTS01142Vo.setNewRegId("");
		formTS01142Vo.setExtraAmtDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 2, 15))));
		formTS01142Vo.setSignOwnerFullName("");
		formTS01142Vo.setSignOfficerFullName("");

		List<TaFormTS01142DtlVo> formTS01142DtlVoList = new ArrayList<>();
		TaFormTS01142DtlVo formTS01142DtlVo = null;
		for (int i = 0; i < 3; i++) {
			formTS01142DtlVo = new TaFormTS01142DtlVo();
			formTS01142DtlVo.setRecNo("");
			formTS01142DtlVo.setRecDate(java.sql.Date.valueOf(LocalDate.from(ThaiBuddhistDate.of(2562, 3, 15))));
			formTS01142DtlVo.setDutyTypeText("0101");
			formTS01142DtlVo.setValueFromAudit(new BigDecimal(253));
			formTS01142DtlVo.setTaxRate(new BigDecimal(253));
			formTS01142DtlVo.setAuditTaxAmt(new BigDecimal(253));
			formTS01142DtlVo.setPaidTaxAmt(new BigDecimal(253));
			formTS01142DtlVo.setAddTaxAmt(new BigDecimal(253));
			formTS01142DtlVo.setAddFineAmt(new BigDecimal(253));
			formTS01142DtlVo.setAddExtraAmt(new BigDecimal(253));
			formTS01142DtlVo.setAddSumTaxAmt(new BigDecimal(253));
			formTS01142DtlVo.setAddMoiAmt(new BigDecimal(253));
			formTS01142DtlVo.setAddSumAllTaxAmt(new BigDecimal(253));
			formTS01142DtlVo.setAddMonthNum(String.valueOf(i + 1));
			formTS01142DtlVoList.add(formTS01142DtlVo);
		}
		formTS01142Vo.setTaFormTS01142DtlVoList(formTS01142DtlVoList);

		taFormTS01142Service.saveFormTS(formTS01142Vo);
	}

}
