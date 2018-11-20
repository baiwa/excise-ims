package th.co.baiwa.excise.report.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.excise.report.bean.ReportJsonBean;
import th.co.baiwa.excise.report.service.TaxAuditReportService;

@Controller
@RequestMapping("api/exciseTax/report")
public class ExciseTaxReportController {

	@Autowired
	private TaxAuditReportService taxAuditReportService;
	
	
	@PostMapping("/updateFlag")
	@ResponseBody
	public Message updateRiskAssInfHdr(@RequestBody BigDecimal id) {
		return taxAuditReportService.updateFlag(id);
	}
	
	
	@PostMapping("/pdf/tax/checkExciseTax")
	public void pdfTs(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws IOException, JRException {
		byte[] reportFile = taxAuditReportService.exciseTaxToPDF(reportJsonBean);
		
		String fileName = URLEncoder.encode("รายงานผลการวิเคราะห์ข้อมูล","UTF-8") ;
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename="+fileName+".pdf");
		
		IOUtils.write(reportFile, response.getOutputStream());
	}
	

	
}
