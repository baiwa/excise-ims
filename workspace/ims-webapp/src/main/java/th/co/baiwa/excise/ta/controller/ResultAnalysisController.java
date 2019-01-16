package th.co.baiwa.excise.ta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.excise.ta.persistence.vo.ResultAnalysisRequestvo;
import th.co.baiwa.excise.ta.persistence.vo.ResultAnalysisVo;
import th.co.baiwa.excise.ta.service.ResultAnalysisService;

@Controller
@RequestMapping("api/ta/resultAnalysis")
public class ResultAnalysisController {

	@Autowired
	private ResultAnalysisService resultAnalysisService;

	@PostMapping("/findProdcutsTax")
	@ResponseBody
	public DataTableAjax<ResultAnalysisVo> findProdcutsTax(@RequestBody ResultAnalysisRequestvo vo) {
		return resultAnalysisService.findProdcutsTax(vo);
	}
	
	@PostMapping("/findPrice")
	@ResponseBody
	public DataTableAjax<ResultAnalysisVo> findPrice(@RequestBody ResultAnalysisRequestvo vo) {
		return resultAnalysisService.findPrice(vo);
	}
		
	@PostMapping("/findValueProductTax")
	@ResponseBody
	public DataTableAjax<ResultAnalysisVo> findValueProductTax(@RequestBody ResultAnalysisRequestvo vo) {
		return resultAnalysisService.findValueProductTax(vo);
	}
	
	@PostMapping("/findTaxRate")
	@ResponseBody
	public DataTableAjax<ResultAnalysisVo> findTaxRate(@RequestBody ResultAnalysisRequestvo vo) {
		return resultAnalysisService.findTaxRate(vo);
	}	
	
	@PostMapping("/findSubmitPayment")
	@ResponseBody
	public DataTableAjax<ResultAnalysisVo> findSubmitPayment(@RequestBody ResultAnalysisRequestvo vo) {
		return resultAnalysisService.findSubmitPayment(vo);
	}
	
	
	
}
