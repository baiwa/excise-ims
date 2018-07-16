package th.co.baiwa.excise.ia.controller;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.ia.persistence.entity.TravelCostWorkSheetHeader;
import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsIntegrate;
import th.co.baiwa.excise.ia.service.Int09Service;

@Controller
@RequestMapping("ia/int09")
public class Int09Controller {

	private Logger logger = LoggerFactory.getLogger(Int09Controller.class);

	@Autowired
	private Int09Service int09Service;

	@PostMapping("/create")
	@ResponseBody
	public TravelCostWsIntegrate create(@RequestBody TravelCostWsIntegrate travelCostWorkSheetHeader) {
		int09Service.createTravelCostService(travelCostWorkSheetHeader);
		return travelCostWorkSheetHeader;
	}

	@GetMapping("/lists")
	@ResponseBody
	public List<TravelCostWorkSheetHeader> lists() {
		List<TravelCostWorkSheetHeader> lists = int09Service
				.listTravelCostHeaderService(new TravelCostWorkSheetHeader());
		return lists;
	}

	@GetMapping("/lists/{id}")
	@ResponseBody
	public List<TravelCostWsIntegrate> lists(@PathVariable("id") String id) {
		TravelCostWorkSheetHeader travel = new TravelCostWorkSheetHeader();
		travel.setWorkSheetHeaderId(new BigDecimal(id));
		List<TravelCostWsIntegrate> lists = int09Service.listTravelCostService(travel);
		return lists;
	}

	@DeleteMapping("/lists/{id}")
	@ResponseBody
	public String deleteLists(@PathVariable("id") String id) {
		return int09Service.deleteTravelCostHeader(id).toString();
	}

	@PostMapping("/lists/{id}")
	@ResponseBody
	public TravelCostWsIntegrate updateLists(@RequestBody TravelCostWsIntegrate travelCostWorkSheetHeader) {
		return travelCostWorkSheetHeader;
	}

	@GetMapping("/pdf/{id}")
	@ResponseBody
	public void pdfSomething(@PathVariable("id") String id, HttpServletResponse response) throws Exception {

		byte[] reportFile = int09Service.contractToPDF("flame1"); // null
	
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "inline;filename=" + "Example.pdf");
		response.setContentLength(reportFile.length);

		OutputStream responseOutputStream = response.getOutputStream();
		for (byte bytes : reportFile) {
			responseOutputStream.write(bytes);
		}
	}

}
