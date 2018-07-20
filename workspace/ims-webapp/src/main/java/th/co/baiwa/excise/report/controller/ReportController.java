package th.co.baiwa.excise.report.controller;

import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.lowagie.text.BadElementException;

import net.sf.jasperreports.engine.JRException;
import th.co.baiwa.buckwaframework.common.util.ThaiNumberUtils;
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

	public ReportController() {
		File f = new File("c:/reports"); // initial file (folder)
		if (!f.exists()) { // check folder exists
			if (f.mkdirs()) {
				logger.info("Directory is created!");
			} else {
				logger.error("Failed to create directory!");
			}
		}
	}

	@PostMapping("/pdf/contract")
	@ResponseBody
	public byte[] pdfContract(@RequestBody ContractBean contract) throws Exception {
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
		return reportService.objectToPDF("Contract", params, null); // null
	}

	@PostMapping("/pdf/example")
	@ResponseBody
	public byte[] pdfExample() throws IOException, JRException, BadElementException {
		byte[] reportFile = reportService.exampleToPDF(); // null
		return reportFile;
	}
	
	@PostMapping("/pdf/ts/{report}")
	@ResponseBody
	public byte[] pdfTs(@PathVariable("report") String name, @RequestBody String json) throws IOException, JRException { //byte[]
		Gson gson = new Gson();
		Map<String,Object> params = new HashMap<String,Object>();
		params = (Map<String,Object>) gson.fromJson(json, params.getClass());
		byte[] report = reportService.objectToPDF(name, params, null); // null
		return report;
	}
}
