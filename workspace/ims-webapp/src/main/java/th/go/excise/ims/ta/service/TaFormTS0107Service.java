package th.go.excise.ims.ta.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.go.excise.ims.ta.vo.TaFormTS0107Vo;

@Service
public class TaFormTS0107Service {
	
	public byte[] exportTaFormTS0107(TaFormTS0107Vo request) throws Exception, IOException {
		String reportName = "TA_FORM_TS0107.jasper";
		Map<String, Object> params = new HashMap<>();

		// get data to report
		params.put("logo", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "logo-garuda.jpg"));
		params.put("office", request.getBookNumber1());

		// set output
		JasperPrint jasperPrint = ReportUtils.getJasperPrint(reportName, params);
		byte[] content = JasperExportManager.exportReportToPdf(jasperPrint);
		ReportUtils.closeResourceFileInputStream(params);

		return content;
	}

}
