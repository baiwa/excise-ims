package th.co.baiwa.excise.report.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import th.co.baiwa.buckwaframework.common.constant.ReportConstants.PATH;
import th.co.baiwa.buckwaframework.common.util.ReportUtils;
import th.co.baiwa.excise.report.bean.ReportJsonBean;

@Service
public class InternalAuditReportService {

	@SuppressWarnings("unchecked")
	public byte[] reportCheckIncomeToPDF(ReportJsonBean reportJsonBean) throws IOException, JRException {

		Gson gson = new Gson();
		Map<String, Object> params = new HashMap<String, Object>();
		params = (Map<String, Object>) gson.fromJson(reportJsonBean.getJson(), params.getClass());

		params.put("logo1", ReportUtils.getResourceFile(PATH.IMAGE_PATH, "logo1.jpg"));

		JasperPrint jasperPrint = ReportUtils.exportReport("reportCheckIncome", params, new JREmptyDataSource());

		byte[] reportFile = JasperExportManager.exportReportToPdf(jasperPrint);

		ReportUtils.closeResourceFileInputStream(params);
		return reportFile;
	}
}
