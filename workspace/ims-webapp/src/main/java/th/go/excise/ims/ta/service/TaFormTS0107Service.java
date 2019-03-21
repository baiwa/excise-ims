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
import th.go.excise.ims.ta.vo.TaFormTS0107Vo;

@Service
public class TaFormTS0107Service {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);

	public byte[] exportTaFormTS0107(TaFormTS0107Vo request) throws Exception, IOException {
		logger.info("exportTaFormTS0107");

		Map<String, Object> params = new HashMap<>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("bookNumber1", request.getBookNumber1());
		params.put("bookNumber2", request.getBookNumber2());
		params.put("officeName1", request.getOfficeName1());

		params.put("officeName2", request.getOfficeName2());
		params.put("headOfficerFullName", request.getHeadOfficerFullName());
		params.put("headOfficerPosition", request.getHeadOfficerPosition());
		params.put("officerFullName1", request.getOfficerFullName1());
		params.put("officerPosition1", request.getOfficerPosition1());
		params.put("officerFullName2", request.getOfficerFullName2());
		params.put("officerPosition2", request.getOfficerPosition2());
		params.put("officerFullName3", request.getOfficerFullName3());
		params.put("officerPosition3", request.getOfficerPosition3());
		params.put("officerFullName4", request.getOfficerFullName4());
		params.put("officerPosition4", request.getOfficerPosition4());
		params.put("officerFullName5", request.getOfficerFullName5());
		params.put("officerPosition5", request.getOfficerPosition5());

		params.put("factoryType", request.getFactoryType());
		params.put("factoryName", request.getFactoryName());

		params.put("newRegId", request.getNewRegId());
		params.put("facAddrNo", request.getFacAddrNo());
		params.put("facMooNo", request.getFacMooNo());
		params.put("facSoiName", request.getFacSoiName());
		params.put("facThnName", request.getFacThnName());
		params.put("facTambolName", request.getFacTambolName());
		params.put("facAmphurName", request.getFacAmphurName());
		params.put("facProvinceName", request.getFacProvinceName());
		params.put("facZipCode", request.getFacZipCode());

		// Date
		request.setDocDate(ConvertDateUtils.parseStringToDate(request.getDocDateStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("docDate", request.getDocDate());
		
		request.setAuditDate(ConvertDateUtils.parseStringToDate(request.getAuditDateStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("auditDate", request.getAuditDate());

		params.put("lawSection", request.getLawSection());
		params.put("headOfficerPhone", request.getHeadOfficerPhone());
		params.put("signOfficerFullName", request.getSignOfficerFullName());
		params.put("signOfficerPosition", request.getSignOfficerPosition());
		params.put("otherText", request.getOtherText());
		params.put("otherPhone", request.getOtherPhone());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_07 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

}
