package th.go.excise.ims.ta.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.ExporterInputItem;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleExporterInputItem;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.FILE_EXTENSION;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.IMG_NAME;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.REPORT_NAME;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;

import th.go.excise.ims.ta.vo.TaFormTS0119Vo;

@Service
public class TaFormTS0119Service {
	public byte[] exportTaFormTS019(TaFormTS0119Vo request) throws Exception, IOException {
	Map<String, Object> params = new HashMap<String, Object>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, IMG_NAME.LOGO_GARUDA + "." + FILE_EXTENSION.JPG));
		
		params.put("bookNumber1", request.getBookNumber1());
		params.put("bookNumber2", request.getBookNumber2());
		params.put("docText1", request.getDocText1());
		params.put("docText2", request.getDocText2());
		params.put("docText3", request.getDocText3());
		params.put("docDear", request.getDocDear());
		params.put("companyName", request.getCompanyName());
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
		params.put("signOfficerFullName", request.getSignOfficerFullName());
		params.put("followTypeFlag1", request.getFollowTypeFlag1());
		params.put("followTypeFlag2", request.getFollowTypeFlag2());
//		params.put("docText3", request.getDocText3());
//		params.put("docText3", request.getDocText3());
//		params.put("docText3", request.getDocText3());
//		params.put("docText3", request.getDocText3());
		request.setDocDate(ConvertDateUtils.parseStringToDate(request.getDocDateStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("docDate", request.getDocDate());
		
		request.setRefBookDate(ConvertDateUtils.parseStringToDate(request.getRefBookDateStr(), ConvertDateUtils.DD_MM_YYYY, ConvertDateUtils.LOCAL_TH));
		params.put("refBookDate", request.getRefBookDate());
		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(REPORT_NAME.TA_FORM_TS01_019 + "." + FILE_EXTENSION.JASPER, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);


		return content;
	}

}
