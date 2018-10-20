package th.co.baiwa.excise.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.excise.domain.datatable.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.ResultAnalysisVo;
import th.co.baiwa.excise.ta.service.ResultAnalysisService;

@Controller
@RequestMapping("api/ta/resultAnalysis")
public class ResultAnalysisController {

	@Autowired
	private ResultAnalysisService resultAnalysisService;

	@PostMapping("/findProdcutsTax")
	@ResponseBody
	public DataTableAjax<ResultAnalysisVo> findProdcutsTax() {
		return resultAnalysisService.findProdcutsTax();
	}
	
	@PostMapping("/findPrice")
	@ResponseBody
	public DataTableAjax<ResultAnalysisVo> findPrice() {
		return resultAnalysisService.findProdcutsTax();
	}
		
	@PostMapping("/findValueProductTax")
	@ResponseBody
	public DataTableAjax<ResultAnalysisVo> findValueProductTax() {
		return resultAnalysisService.findValueProductTax();
	}
	
	@PostMapping("/findTaxRate")
	@ResponseBody
	public DataTableAjax<ResultAnalysisVo> findTaxRate() {
		return resultAnalysisService.findTaxRate();
	}	
	
	@PostMapping("/findSubmitPayment")
	@ResponseBody
	public DataTableAjax<ResultAnalysisVo> findSubmitPayment() {
		return resultAnalysisService.findSubmitPayment();
	}
	
	
	
}
