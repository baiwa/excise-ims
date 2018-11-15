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
import th.co.baiwa.excise.report.service.OperationAuditReportService;

@Controller
@RequestMapping("api/exciseOperation/report")
public class ExciseOperationReportController {

	@Autowired
	private OperationAuditReportService operationAuditReportService;
	
	
//	@PostMapping("/updateFlag")
//	@ResponseBody
//	public Message updateRiskAssInfHdr(@RequestBody BigDecimal id) {
//		return taxAuditReportService.updateFlag(id);
//	}
	
	
	@PostMapping("/pdf/operation/checkExciseOperation")
	public void pdfTs(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws IOException, JRException {
		byte[] reportFile = operationAuditReportService.exciseTaxToPDF(reportJsonBean);
		
		String fileName = URLEncoder.encode("รายงานผลการตรวจปฏิบัติการ","UTF-8") ;
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename="+fileName+".pdf");
		
		IOUtils.write(reportFile, response.getOutputStream());
	}
	

	
}
