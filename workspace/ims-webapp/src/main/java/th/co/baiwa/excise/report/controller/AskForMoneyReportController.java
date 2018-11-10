package th.co.baiwa.excise.report.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.excise.report.bean.ReportJsonBean;
import th.co.baiwa.excise.report.service.AskForMoneyReportService;

@Controller
@RequestMapping("api/report")
public class AskForMoneyReportController {
	
	@Autowired
	private AskForMoneyReportService askForMoneyReportService;
	
	@PostMapping("/pdf/oa/ask-for-money-report")
	public void pdfTs(@ModelAttribute ReportJsonBean reportJsonBean, HttpServletResponse response) throws IOException, JRException {
		byte[] reportFile = askForMoneyReportService.objectToPDF(reportJsonBean);
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=ask-for-money-report.pdf");
		
		IOUtils.write(reportFile, response.getOutputStream());
	}
	
}
