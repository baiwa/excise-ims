package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS0107Vo;

@Service
public class TaFormTS0107Service {

	private static final Logger logger = LoggerFactory.getLogger(TaFormTS0107Service.class);

	public byte[] exportTaFormTS0107(TaFormTS0107Vo request) throws Exception, IOException {
		logger.info("exportTaFormTS0107");

		String reportName = "TA_FORM_TS01_07.jasper";
		Map<String, Object> params = new HashMap<>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "logo-garuda.jpg"));
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
		
		//Convert LocalDate to String
		String[] docDate = ConvertDateUtils.formatLocalDateToString(request.getDocDate(), ConvertDateUtils.DD_MMMM_YYYY).split(" ");
		params.put("day", docDate[0]);
		params.put("month", docDate[1]);
		params.put("year", docDate[2]);
		
		params.put("auditDate", ConvertDateUtils.formatLocalDateToString(request.getAuditDate(), ConvertDateUtils.DD_MMMM_YYYY));
		
		params.put("lawSection", request.getLawSection());
		params.put("headOfficerPhone", request.getHeadOfficerPhone());
		params.put("signFullName", request.getSignFullName());
		params.put("signPosition", request.getSignPosition());
		params.put("otherText", request.getOtherText());
		params.put("otherPhone", request.getOtherPhone());
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportName, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

}
