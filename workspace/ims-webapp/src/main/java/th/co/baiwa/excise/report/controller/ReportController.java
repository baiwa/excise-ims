package th.co.baiwa.excise.report.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lowagie.text.BadElementException;

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.buckwaframework.common.util.ThaiNumberUtils;
import th.co.baiwa.excise.report.bean.ContractBean;
import th.co.baiwa.excise.report.service.ReportService;
import th.co.baiwa.excise.report.service.AskForMoneyReportService;

@Controller
@RequestMapping("api/report")
public class ReportController {

	private Logger logger = LoggerFactory.getLogger(ReportController.class);

	@Autowired
	private ReportService reportService;
//
//	@Autowired
//	private ReporterService reporterService;
	
	@GetMapping("/pdf/{name}/file")
	@ResponseBody
	public void pdfSomething(@PathVariable("name") String name, HttpServletResponse response) throws Exception {
		reportService.viewPdf(name, response);
	}

	@PostMapping("/pdf/contract/try")
	@ResponseBody
	public void pdfContract(@RequestBody ContractBean contract) throws Exception {
		DecimalFormat formatter = new DecimalFormat("#,###.00");

		contract.setSumCostTxt(ThaiNumberUtils.toThaiBaht(contract.getSumCost().toString()));
		Map<String, Object> params = new HashMap<>();
		params.put("day", ThaiNumberUtils.toThaiNumber(formatter.format(contract.getDay().doubleValue())));
		params.put("allowanceCost",
				ThaiNumberUtils.toThaiNumber(formatter.format(contract.getAllowanceCost().doubleValue())));
		params.put("rentCost", ThaiNumberUtils.toThaiNumber(formatter.format(contract.getRentCost().doubleValue())));
		params.put("travelCost",
				ThaiNumberUtils.toThaiNumber(formatter.format(contract.getTravelCost().doubleValue())));
		params.put("otherCost", ThaiNumberUtils.toThaiNumber(formatter.format(contract.getOtherCost().doubleValue())));
		params.put("sumCost", ThaiNumberUtils.toThaiNumber(formatter.format(contract.getSumCost().doubleValue())));

		params.put("sumCostTxt", contract.getSumCostTxt());
		params.put("numberId", contract.getNumberId());
		params.put("loanName", contract.getLoanName());
		params.put("loanFrom", contract.getLoanFrom());
		params.put("dateFixed", contract.getDateFixed());
		params.put("locateAt", contract.getLocateAt());
		params.put("positionName", contract.getPositionName());
		params.put("presentTo", contract.getPresentTo());
		params.put("reasonTxt", contract.getReasonTxt());
		params.put("sendTo", contract.getSendTo());
		byte[] reportFile = reportService.objectToPDF("Contract", params, null);
		// return reportService.objectToPDF("Contract", params, null); // null
	}

	@GetMapping("/pdf/example/{num}")
	@ResponseBody //byte[]
	public void pdfExample(@PathVariable("num") Integer num) throws IOException, JRException, BadElementException {
		byte[] reportFile = reportService.exampleToPDF(num); // null
		// return reportFile;
	}

	@PostMapping("/pdf/ts/{report}")
	@ResponseBody // byte[]
	public void pdfTs(@PathVariable("report") String name, @RequestBody String json) throws IOException, JRException { // byte[]
		byte[] report = reportService.objectToPDF(name, json); // null
		//return report;
	}
//	
//	@PostMapping("/pdf/ts/{report}")
//	@ResponseBody // byte[]
//	public void pdfTsReport(@PathVariable("reporter") String name, @RequestBody String json) throws IOException, JRException { // byte[]
//		byte[] report = reporterService.objectToPDF(name, json); // null
//		//return report;
//	}
}
