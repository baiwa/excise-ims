package th.co.baiwa.excise.report.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.excise.report.bean.ReportJsonBean;
import th.co.baiwa.excise.report.service.InternalAuditReportService;

@Controller
@RequestMapping("api/internalAudit/report")
public class InternalAuditReportController {

	@Autowired
	private InternalAuditReportService internalAuditReportService;
	
	
	@PostMapping("/pdf/int/reportCheckIncome")
	public void reportCheckIncomePdf(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws IOException, JRException {
		
		String fileName = URLEncoder.encode("รายงานผลการตรวจสอบรายได้","UTF-8") ;
		
		byte[] reportFile = internalAuditReportService.reportCheckToPDF(reportJsonBean);
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename="+fileName+".pdf");
		
		IOUtils.write(reportFile, response.getOutputStream());
	}


	@PostMapping("/pdf/int/reportCheckDisburse")
	public void reportCheckDisbursePdf(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws IOException, JRException {
		
		String fileName = URLEncoder.encode("รายงานผลการตรวจสอบเบิกจ่าย","UTF-8") ;
		
		byte[] reportFile = internalAuditReportService.reportCheckToPDF(reportJsonBean);
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename="+fileName+".pdf");
		
		IOUtils.write(reportFile, response.getOutputStream());
	}
	
	
}
