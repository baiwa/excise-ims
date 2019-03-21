package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS0101Vo;

@Service
public class TaFormTS0101Service {
	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0101Service.class);

	public byte[] exportTaFormTS0101(TaFormTS0101Vo request) throws Exception, IOException {
		logger.info("exportTaFormTS0101");

		Map<String, Object> params = new HashMap<>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_EXCISE + "." + FILE_EXTENSION.JPG));
		params.put("newRegId", request.getNewRegId());
		params.put("factoryName", request.getFactoryName());
		params.put("factoryTypeText", request.getFactoryTypeText());
		params.put("factoryAddress", request.getFactoryAddress());

		// Date
		request.setAnalysisDateStart(ConvertDateUtils.parseStringToDate(request.getAnalysisDateStartStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("analysisDateStart", request.getAnalysisDateStart());

		request.setAnalysisDateEnd(ConvertDateUtils.parseStringToDate(request.getAnalysisDateEndStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("analysisDateEnd", request.getAnalysisDateEnd());

		params.put("analysisData1", request.getAnalysisData1());
		params.put("analysisData2", request.getAnalysisData2());
		params.put("analysisData3", request.getAnalysisData3());
		params.put("analysisData4", request.getAnalysisData4());
		params.put("analysisData5", request.getAnalysisData5());
		params.put("analysisResultDear", request.getAnalysisResultDear());
		params.put("analysisResultText", request.getAnalysisResultText());
		params.put("callAuditFlag", request.getCallAuditFlag());
		params.put("otherText", request.getOtherText());
		params.put("signOfficerFullName", request.getSignOfficerFullName());
		params.put("signSupOfficerFullName", request.getSignSupOfficerFullName());

		// Date
		request.setSignOfficerDate(ConvertDateUtils.parseStringToDate(request.getSignOfficerDateStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("signOfficerDate", request.getSignOfficerDate());

		params.put("approvedFlag", request.getApprovedFlag());
		params.put("signApprOfficerFullName", request.getSignApprOfficerFullName());
		params.put("signApprOfficerPosition", request.getSignApprOfficerPosition());

		// Date
		request.setSignApprDate(ConvertDateUtils.parseStringToDate(request.getSignApprDateStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("signApprDate", request.getSignApprDate());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_01 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
}
