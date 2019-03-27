package th.go.excise.ims.ta.service;

import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.junit.Test;

import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ta.vo.TaFormTS01142DtlVo;
import th.go.excise.ims.ta.vo.TaFormTS01142Vo;

public class TaFormTS01142ServiceTest {
	private static final String REPORT_FILE = PATH.TEST_PATH + REPORT_NAME.TA_FORM_TS01_14_2 + "." + FILE_EXTENSION.PDF;

	@Test
	public void test_generateReport() throws Exception {
		TaFormTS01142Service formTS01142Service = new TaFormTS01142Service();

		TaFormTS01142Vo vo = new TaFormTS01142Vo();
		vo.setOwnerFullName("ธนพล ชัยภูมิ");
		vo.setFactoryType("");
		vo.setFactoryName("");
		vo.setAuditDateStart(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setAuditDateEnd(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setDutyTypeText("");
		vo.setNewRegId("");
		vo.setExtraAmtDate(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
		vo.setSignOwnerFullName("");
		vo.setSignOfficerFullName("");

		List<TaFormTS01142DtlVo> listItem = new ArrayList<>();
		TaFormTS01142DtlVo itemVo =new TaFormTS01142DtlVo();
		itemVo.setRecNo("");
		itemVo.setRecDate(ConvertDateUtils.parseStringToDate("04/01/2562", ConvertDateUtils.DD_MM_YYYY));
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
		IOUtils.write(reportFile, new FileOutputStream(new File(REPORT_FILE)));
	}
}
