package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.bean.ReportJsonBean;
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

	public byte[] exportTaFormTS0107(ReportJsonBean reportJsonBean) throws Exception, IOException {
		logger.info("exportTaFormTS0107");

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();
		TaFormTS0107Vo formTs = gson.fromJson(reportJsonBean.getJson(), TaFormTS0107Vo.class);

		Map<String, Object> params = new HashMap<>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		params.put("bookNumber1", formTs.getBookNumber1());
		params.put("bookNumber2", formTs.getBookNumber2());
		params.put("officeName1", formTs.getOfficeName1());

		params.put("officeName2", formTs.getOfficeName2());
		params.put("headOfficerFullName", formTs.getHeadOfficerFullName());
		params.put("headOfficerPosition", formTs.getHeadOfficerPosition());
		params.put("officerFullName1", formTs.getOfficerFullName1());
		params.put("officerPosition1", formTs.getOfficerPosition1());
		params.put("officerFullName2", formTs.getOfficerFullName2());
		params.put("officerPosition2", formTs.getOfficerPosition2());
		params.put("officerFullName3", formTs.getOfficerFullName3());
		params.put("officerPosition3", formTs.getOfficerPosition3());
		params.put("officerFullName4", formTs.getOfficerFullName4());
		params.put("officerPosition4", formTs.getOfficerPosition4());
		params.put("officerFullName5", formTs.getOfficerFullName5());
		params.put("officerPosition5", formTs.getOfficerPosition5());

		params.put("factoryType", formTs.getFactoryType());
		params.put("factoryName", formTs.getFactoryName());

		params.put("newRegId", formTs.getNewRegId());
		params.put("facAddrNo", formTs.getFacAddrNo());
		params.put("facMooNo", formTs.getFacMooNo());
		params.put("facSoiName", formTs.getFacSoiName());
		params.put("facThnName", formTs.getFacThnName());
		params.put("facTambolName", formTs.getFacTambolName());
		params.put("facAmphurName", formTs.getFacAmphurName());
		params.put("facProvinceName", formTs.getFacProvinceName());
		params.put("facZipCode", formTs.getFacZipCode());

		// Date
		formTs.setDocDate(ConvertDateUtils.parseStringToDate(formTs.getDocDateStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("docDate", formTs.getDocDate());

		formTs.setAuditDate(ConvertDateUtils.parseStringToDate(formTs.getAuditDateStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("auditDate", formTs.getAuditDate());

		params.put("lawSection", formTs.getLawSection());
		params.put("headOfficerPhone", formTs.getHeadOfficerPhone());
		params.put("signOfficerFullName", formTs.getSignOfficerFullName());
		params.put("signOfficerPosition", formTs.getSignOfficerPosition());
		params.put("otherText", formTs.getOtherText());
		params.put("otherPhone", formTs.getOtherPhone());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_07 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

}
