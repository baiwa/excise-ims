package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS0116Vo;

@Service
public class TaFormTS0116Service {

	public byte[] generateReport(TaFormTS0116Vo formTS0116Vo) throws Exception, IOException {
		Map<String, Object> params = new HashMap<String, Object>();

		params.put("docText",formTS0116Vo.getDocText());
		params.put("docDear",formTS0116Vo.getDocDear());
		params.put("factoryName",formTS0116Vo.getFactoryName());
		params.put("factoryType",formTS0116Vo.getFactoryType());
		params.put("newRegId",formTS0116Vo.getNewRegId());
		params.put("requestDate",formTS0116Vo.getRequestDate());
		params.put("requestType",formTS0116Vo.getRequestType());
		params.put("requestReason",formTS0116Vo.getRequestReason());
		params.put("requestDesc",formTS0116Vo.getRequestDesc());
		params.put("fineNoFlag",formTS0116Vo.getFineNoFlag());
		params.put("fineRefrainFlag",formTS0116Vo.getFineRefrainFlag());
		params.put("findReduceFlag",formTS0116Vo.getFindReduceFlag());
		params.put("finePercent",formTS0116Vo.getFinePercent());
		params.put("extraNoFlag",formTS0116Vo.getExtraNoFlag());
		params.put("extraReduceFlag",formTS0116Vo.getExtraReduceFlag());
		params.put("extraPercent",formTS0116Vo.getExtraPercent());
		params.put("beforeTaxAmt",formTS0116Vo.getBeforeTaxAmt());
		params.put("beforeFinePercent",formTS0116Vo.getBeforeFinePercent());
		params.put("beforeFineAmt",formTS0116Vo.getBeforeFineAmt());
		params.put("beforeExtraAmt",formTS0116Vo.getBeforeExtraAmt());
		params.put("beforeMoiAmt",formTS0116Vo.getBeforeMoiAmt());
		params.put("beforeSumAmt",formTS0116Vo.getBeforeSumAmt());
		params.put("afterTaxAmt",formTS0116Vo.getAfterTaxAmt());
		params.put("afterFinePercent",formTS0116Vo.getAfterFinePercent());
		params.put("afterFineAmt",formTS0116Vo.getAfterFineAmt());
		params.put("afterExtraAmt",formTS0116Vo.getAfterExtraAmt());
	 	params.put("afterMoiAmt",formTS0116Vo.getAfterMoiAmt());
		params.put("afterSumAmt",formTS0116Vo.getAfterSumAmt());
		params.put("signOfficerFullName",formTS0116Vo.getSignOfficerFullName());
		params.put("signOfficerPosition",formTS0116Vo.getSignOfficerPosition());
		params.put("headOfficerComment",formTS0116Vo.getHeadOfficerComment());
		params.put("signHeadOfficerFullName",formTS0116Vo.getSignHeadOfficerFullName());
		params.put("signHeadOfficerPosition",formTS0116Vo.getSignHeadOfficerPosition());
		params.put("signHeadOfficerDate",formTS0116Vo.getSignHeadOfficerDate());
		params.put("approverComment",formTS0116Vo.getApproverComment());
		params.put("signApproverFullName",formTS0116Vo.getSignApproverFullName());
		params.put("signApproverPosition",formTS0116Vo.getSignApproverPosition());
		params.put("signApproverDate",formTS0116Vo.getSignApproverDate());
		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));

	
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_16 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
}
