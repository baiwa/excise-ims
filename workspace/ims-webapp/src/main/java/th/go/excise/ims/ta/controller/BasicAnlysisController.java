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
import th.go.excise.ims.ta.service.AnalysisTaxAmtsService;
import th.go.excise.ims.ta.service.AnalysisTaxFilingService;
import th.go.excise.ims.ta.service.AnalysisTaxQtyService;
import th.go.excise.ims.ta.service.AnalysisTaxRetailPriceService;
import th.go.excise.ims.ta.service.AnalysisTaxRateService;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastMonthVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastYearVo;
import th.go.excise.ims.ta.vo.AnalysisTaxAmtVo;
import th.go.excise.ims.ta.vo.AnalysisTaxFilingVo;
import th.go.excise.ims.ta.vo.AnalysisTaxQtyVo;
import th.go.excise.ims.ta.vo.AnalysisTaxRetailPriceVo;
import th.go.excise.ims.ta.vo.AnalysisTaxRateVo;

@Controller
@RequestMapping("/api/ta/basic-anlysis")
public class BasicAnlysisController {
	private static final Logger logger = LoggerFactory.getLogger(BasicAnlysisController.class);
	private AnalysisTaxQtyService analysisTaxQtyService;
	private AnalysisTaxRetailPriceService analysisTaxQuRetailPriceService;
	private AnalysisTaxRateService analysisTaxRateService;
	private AnalysisTaxAmtsService analysisTaxAmtsService;
	private AnalysisTaxFilingService analysisTaxFilingService;
	private AnalysisIncomeCompareLastMonthService analysisIncomeCompareLastMonthService;
	private AnalysisIncomeCompareLastYearService analysisIncomeCompareLastYearService;

	@Autowired
	public BasicAnlysisController(AnalysisTaxQtyService analysisTaxQtyService,
			AnalysisTaxRetailPriceService analysisTaxQuRetailPriceService,
			AnalysisTaxRateService analysisTaxRateService, AnalysisTaxAmtsService analysisTaxAmtsService,
			AnalysisTaxFilingService analysisTaxFilingService,
			AnalysisIncomeCompareLastMonthService analysisIncomeCompareLastMonthService,
			AnalysisIncomeCompareLastYearService analysisIncomeCompareLastYearService) {
		this.analysisTaxQtyService = analysisTaxQtyService;
		this.analysisTaxQuRetailPriceService = analysisTaxQuRetailPriceService;
		this.analysisTaxRateService = analysisTaxRateService;
		this.analysisTaxAmtsService = analysisTaxAmtsService;
		this.analysisTaxFilingService = analysisTaxFilingService;
		this.analysisIncomeCompareLastMonthService = analysisIncomeCompareLastMonthService;
		this.analysisIncomeCompareLastYearService = analysisIncomeCompareLastYearService;
	}

	// TODO 1
	@PostMapping("/analysis-tax-qty-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxQtyVo> listAnalysisTaxQty(@RequestBody AnalysisFormVo request) {
		logger.info("analysisTaxQtyService");

		DataTableAjax<AnalysisTaxQtyVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxQtyService.GetAnalysisTaxQty(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// TODO 2
	@PostMapping("/analysis-taxQuRetail-price-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxRetailPriceVo> listAnalysisTaxQuRetailPrice(@RequestBody AnalysisFormVo request) {
		logger.info("analysisTaxQuRetailPriceService");

		DataTableAjax<AnalysisTaxRetailPriceVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxQuRetailPriceService.GetAnalysisTaxQuRetailPrice(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	// TODO 3

	// TODO 4
	@PostMapping("/analysis-taxRate-service-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxRateVo> listAnalysisTaxRateService(@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisTaxRateService");

		DataTableAjax<AnalysisTaxRateVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxRateService.GetAnalysisTaxRate(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// TODO 5
	@PostMapping("/list-analysis-taxAmts-service-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxAmtVo> listAnalysisTaxAmtsService(@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisTaxAmtsService");

		DataTableAjax<AnalysisTaxAmtVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxAmtsService.GetAnalysisTaxAmt(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// TODO 6
	@PostMapping("/analysis-tax-filing-data")
	@ResponseBody
	public DataTableAjax<AnalysisTaxFilingVo> listAnalysisTaxFilingService(@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisTaxFilingService");

		DataTableAjax<AnalysisTaxFilingVo> response = new DataTableAjax<>();
		try {
			response = analysisTaxFilingService.GetAnalysisTaxFiling(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// TODO 7
	@PostMapping("/analysis-Income-compareLast-month-data")
	@ResponseBody
	public DataTableAjax<AnalysisIncomeCompareLastMonthVo> listAnalysisIncomeCompareLastMonthService(
			@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisIncomeCompareLastMonthService");

		DataTableAjax<AnalysisIncomeCompareLastMonthVo> response = new DataTableAjax<>();
		try {
			response = analysisIncomeCompareLastMonthService.GetAnalysisIncomeCompareLastMonth(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// TODO 8
	@PostMapping("/analysis-income-compareLast-year-data")
	@ResponseBody
	public DataTableAjax<AnalysisIncomeCompareLastYearVo> listAnalysisIncomeCompareLastYearService(
			@RequestBody AnalysisFormVo request) {
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