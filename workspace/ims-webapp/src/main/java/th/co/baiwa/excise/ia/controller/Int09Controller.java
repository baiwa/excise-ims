package th.co.baiwa.excise.ia.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.jasperreports.JasperReportsPdfView;

import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleHtmlExporterOutput;
import th.co.baiwa.excise.ia.persistence.entity.TravelCostWorkSheetHeader;
import th.co.baiwa.excise.ia.persistence.entity.TravelCostWsIntegrate;
import th.co.baiwa.excise.ia.service.Int09Service;

@Controller
@RequestMapping("ia/int09")
public class Int09Controller {

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

	@GetMapping("/test/{txt}")
	@ResponseBody
	public void test(@PathVariable("txt") String txt, HttpServletResponse response) throws JRException, IOException {
		// Get Compiled Report File
		File reportFile = new File("src/main/resources/reports/a.jrxml");
		FileInputStream inputStream = new FileInputStream(reportFile);
		
		// mapping
		Map<String, Object> mapping = new HashMap<String, Object>();
		mapping.put("eiei", txt);
		
		// data binary complied
		JasperPrint jasperPrint = JasperFillManager.fillReport(JasperCompileManager.compileReport(inputStream), mapping,
				new JREmptyDataSource());
		
		// export to pdf
		OutputStream output = new FileOutputStream(new File("c:/JasperReport.pdf"));
		JasperExportManager.exportReportToPdfStream(jasperPrint, output);

		// export to html
		HtmlExporter exporter = new HtmlExporter(DefaultJasperReportsContext.getInstance());
		exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
		exporter.setExporterOutput(new SimpleHtmlExporterOutput(response.getWriter()));
		exporter.exportReport();
	}

}
