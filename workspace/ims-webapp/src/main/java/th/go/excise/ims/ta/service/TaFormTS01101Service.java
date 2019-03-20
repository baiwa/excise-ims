package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS01101Vo;

@Service
public class TaFormTS01101Service {
	
	public byte[] exportTaFormTS01101(TaFormTS01101Vo request) throws Exception, IOException {
		String reportName = "TA_FORM_TS01_10_1.jasper";
		Map<String, Object> params = new HashMap<>();

		// get data to report
		params.put("testimonyPageNo", request.getTestimonyPageNo());
		params.put("testimonyOf", request.getTestimonyOf());
		params.put("testimonyText", request.getTestimonyText());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportName, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}
}
