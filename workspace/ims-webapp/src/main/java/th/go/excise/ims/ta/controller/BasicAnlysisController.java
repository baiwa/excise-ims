package th.go.excise.ims.ta.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.service.AnalysisIncomeCompareLastMonthService;
import th.go.excise.ims.ta.service.AnalysisIncomeCompareLastYearService;
import th.go.excise.ims.ta.service.AnalysisTaxAmtService;
import th.go.excise.ims.ta.service.AnalysisTaxFilingService;
import th.go.excise.ims.ta.service.AnalysisTaxQtyService;
import th.go.excise.ims.ta.service.AnalysisTaxRateService;
import th.go.excise.ims.ta.service.AnalysisTaxRetailPriceService;
import th.go.excise.ims.ta.service.AnalysisTaxValueService;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastMonthVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastYearVo;
import th.go.excise.ims.ta.vo.AnalysisTaxAmtVo;
import th.go.excise.ims.ta.vo.AnalysisTaxFilingVo;
import th.go.excise.ims.ta.vo.AnalysisTaxQtyVo;
import th.go.excise.ims.ta.vo.AnalysisTaxRateVo;
import th.go.excise.ims.ta.vo.AnalysisTaxRetailPriceVo;
import th.go.excise.ims.ta.vo.AnalysisTaxValueVo;

@Controller
@RequestMapping("/api/ta/basic-anlysis")
public class BasicAnlysisController {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicAnlysisController.class);
	
	private AnalysisTaxQtyService analysisTaxQtyService;
	private AnalysisTaxRetailPriceService analysisTaxRetailPriceService;
	private AnalysisTaxValueService analysisTaxValueService;
	private AnalysisTaxRateService analysisTaxRateService;
	private AnalysisTaxAmtService analysisTaxAmtService;
	private AnalysisTaxFilingService analysisTaxFilingService;
	private AnalysisIncomeCompareLastMonthService analysisIncomeCompareLastMonthService;
	private AnalysisIncomeCompareLastYearService analysisIncomeCompareLastYearService;

	@Autowired
	public BasicAnlysisController(
			AnalysisTaxQtyService analysisTaxQtyService,
			AnalysisTaxRetailPriceService analysisTaxRetailPriceService,
			AnalysisTaxRateService analysisTaxRateService,
			AnalysisTaxAmtService analysisTaxAmtService,
			AnalysisTaxFilingService analysisTaxFilingService,
			AnalysisIncomeCompareLastMonthService analysisIncomeCompareLastMonthService,
			AnalysisIncomeCompareLastYearService analysisIncomeCompareLastYearService,
			AnalysisTaxValueService analysisTaxValueService) {
		this.analysisTaxQtyService = analysisTaxQtyService;
		this.analysisTaxRetailPriceService = analysisTaxRetailPriceService;
		this.analysisTaxRateService = analysisTaxRateService;
		this.analysisTaxValueService = analysisTaxValueService;
		this.analysisTaxAmtService = analysisTaxAmtService;
		this.analysisTaxFilingService = analysisTaxFilingService;
		this.analysisIncomeCompareLastMonthService = analysisIncomeCompareLastMonthService;
		this.analysisIncomeCompareLastYearService = analysisIncomeCompareLastYearService;
	}

	// TODO 1
	@PostMapping("/tax-qty-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxQtyVo> taxQtyData(@RequestBody AnalysisFormVo request) {
		logger.info("taxQtyData");

		DataTableAjax<AnalysisTaxQtyVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxQtyService.getAnalysisTaxQty(request);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		
		return response;
	}

	// TODO 2
	@PostMapping("/tax-retail-price-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxRetailPriceVo> taxRetailPriceData(@RequestBody AnalysisFormVo request) {
		logger.info("taxRetailPriceData");

		DataTableAjax<AnalysisTaxRetailPriceVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxRetailPriceService.GetAnalysisTaxQuRetailPrice(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return response;
	}
	// TODO 3
	@PostMapping("/tax-value-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxValueVo> taxValueData(@RequestBody AnalysisFormVo request) {
		logger.info("taxValueData");

		DataTableAjax<AnalysisTaxValueVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxValueService.GetAnalysisTaxValue(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	// TODO 4
	@PostMapping("/tax-rate-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxRateVo> taxRateData(@RequestBody AnalysisFormVo request) {
		logger.info("taxRateData");

		DataTableAjax<AnalysisTaxRateVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxRateService.GetAnalysisTaxRate(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// TODO 5
	@PostMapping("/tax-amt-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxAmtVo> taxAmtData(@RequestBody AnalysisFormVo request) {
		logger.info("taxAmtData");

		DataTableAjax<AnalysisTaxAmtVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxAmtService.GetAnalysisTaxAmt(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// TODO 6
	@PostMapping("/tax-filing-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxFilingVo> taxFilingData(@RequestBody AnalysisFormVo request) {
		logger.info("taxFilingData");

		DataTableAjax<AnalysisTaxFilingVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxFilingService.GetAnalysisTaxFiling(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// TODO 7
	@PostMapping("/income-compare-last-month-data")
	@ResponseBody
	public DataTableAjax<AnalysisIncomeCompareLastMonthVo> incomeCompareLastMonthData(@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisIncomeCompareLastMonthService");

		DataTableAjax<AnalysisIncomeCompareLastMonthVo> response = new DataTableAjax<>();
		try {
			response = analysisIncomeCompareLastMonthService.getAnalysisIncomeCompareLastMonth(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// TODO 8
	@PostMapping("/income-compare-last-year-data")
	@ResponseBody
	public DataTableAjax<AnalysisIncomeCompareLastYearVo> incomeCompareLastYearData(@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisIncomeCompareLastYearService");

		DataTableAjax<AnalysisIncomeCompareLastYearVo> response = new DataTableAjax<>();
		try {
			response = analysisIncomeCompareLastYearService.GetAnalysisIncomeCompareLastYear(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
