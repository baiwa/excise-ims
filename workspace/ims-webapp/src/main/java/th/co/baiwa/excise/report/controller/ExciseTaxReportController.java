package th.co.baiwa.excise.report.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
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
	
	
//	@PostMapping("/pdf/checkExciseTax")
//	public void pdfTs(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws IOException, JRException {
//		byte[] reportFile = taxAuditReportService.exciseTaxToPDF(reportJsonBean);
//		
//		String fileName = URLEncoder.encode("ตรวจสอบข้อมูลด้านภาษีสรรพาสามิต","UTF-8") ;
//		
//		response.setContentType("application/pdf");
//		response.addHeader("Content-Disposition", "inline;filename="+fileName+".pdf");
//		
//		IOUtils.write(reportFile, response.getOutputStream());
//	}
	

	
}
