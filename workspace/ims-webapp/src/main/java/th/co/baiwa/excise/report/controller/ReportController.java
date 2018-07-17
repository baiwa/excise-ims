package th.co.baiwa.excise.report.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lowagie.text.BadElementException;

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.excise.report.bean.ContractBean;
import th.co.baiwa.excise.report.service.ReportService;

@Controller
@RequestMapping("api/report")
public class ReportController {

	private Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private ReportService reportService;

	// @GetMapping("/pdf/contract/{id}")
	// @ResponseBody
	// public void pdfSomething(@PathVariable("id") String id, HttpServletResponse
	// response) throws Exception {
	//
	// File file = new File("c:/out/" + id + ".pdf");
	// byte[] reportFile = IOUtils.toByteArray(new FileInputStream(file)); // null
	//
	// response.setContentType("application/pdf");
	// response.addHeader("Content-Disposition", "inline;filename=" + id + ".pdf");
	// response.setContentLength(reportFile.length);
	//
	// OutputStream responseOutputStream = response.getOutputStream();
	// for (byte bytes : reportFile) {
	// responseOutputStream.write(bytes);
	// }
	// }

	@PostMapping("/pdf/contract")
	@ResponseBody
	public byte[] pdfContract(@RequestBody ContractBean contract) throws Exception {

		byte[] reportFile = reportService.contractToPDF("flame1", contract); // null

		return reportFile;
	}

	@PostMapping("/pdf/example")
	@ResponseBody
	public byte[] pdfExample() throws IOException, JRException, BadElementException {
		byte[] reportFile = reportService.exampleToPDF(); // null
		return reportFile;
	}
}
