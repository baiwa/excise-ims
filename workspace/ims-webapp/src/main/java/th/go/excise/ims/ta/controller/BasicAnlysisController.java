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
import th.go.excise.ims.ta.service.AnalysisTaxRateService;
import th.go.excise.ims.ta.service.AnalysisTaxRetailPriceService;
import th.go.excise.ims.ta.service.AnalysisTaxValueService;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastMonthVo;
import th.go.excise.ims.ta.vo.AnalyzeCompareOldYearVo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD1Vo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD2Vo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD3Vo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD4Vo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD5Vo;
import th.go.excise.ims.ta.vo.PaperBasicAnalysisD6Vo;

@Controller
@RequestMapping("/api/ta/basic-anlysis")
public class BasicAnlysisController {
	private static final Logger logger = LoggerFactory.getLogger(BasicAnlysisController.class);
	private AnalysisTaxQtyService analysisTaxQtyService;
	private AnalysisTaxRetailPriceService analysisTaxQuRetailPriceService;
	private AnalysisTaxValueService analysisTaxValueService;
	private AnalysisTaxRateService analysisTaxRateService;
	private AnalysisTaxAmtsService analysisTaxAmtsService;
	private AnalysisTaxFilingService analysisTaxFilingService;
	private AnalysisIncomeCompareLastMonthService analysisIncomeCompareLastMonthService;
	private AnalysisIncomeCompareLastYearService analysisIncomeCompareLastYearService;

	@Autowired
	public BasicAnlysisController(AnalysisTaxQtyService analysisTaxQtyService, AnalysisTaxRetailPriceService analysisTaxQuRetailPriceService, AnalysisTaxRateService analysisTaxRateService, AnalysisTaxAmtsService analysisTaxAmtsService, AnalysisTaxFilingService analysisTaxFilingService,
			AnalysisIncomeCompareLastMonthService analysisIncomeCompareLastMonthService, AnalysisIncomeCompareLastYearService analysisIncomeCompareLastYearService,AnalysisTaxValueService analysisTaxValueService) {
		this.analysisTaxQtyService = analysisTaxQtyService;
		this.analysisTaxQuRetailPriceService = analysisTaxQuRetailPriceService;
		this.analysisTaxRateService = analysisTaxRateService;
		this.analysisTaxValueService = analysisTaxValueService;
		this.analysisTaxAmtsService = analysisTaxAmtsService;
		this.analysisTaxFilingService = analysisTaxFilingService;
		this.analysisIncomeCompareLastMonthService = analysisIncomeCompareLastMonthService;
		this.analysisIncomeCompareLastYearService = analysisIncomeCompareLastYearService;
	}

	// TODO 1
	@PostMapping("/analysis-tax-qty-data")
	@ResponseBody
	public DataTableAjax<PaperBasicAnalysisD1Vo> listAnalysisTaxQty(@RequestBody AnalysisFormVo request) {
		logger.info("analysisTaxQtyService");

		DataTableAjax<PaperBasicAnalysisD1Vo> response = new DataTableAjax<>();
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
	public DataTableAjax<PaperBasicAnalysisD2Vo> listAnalysisTaxQuRetailPrice(@RequestBody AnalysisFormVo request) {
		logger.info("analysisTaxQuRetailPriceService");

		DataTableAjax<PaperBasicAnalysisD2Vo> response = new DataTableAjax<>();
		try {
			response = analysisTaxQuRetailPriceService.GetAnalysisTaxQuRetailPrice(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	// TODO 3
	@PostMapping("/analysis-lysisTax-value")
	@ResponseBody
	public DataTableAjax<PaperBasicAnalysisD3Vo> listAnalysisTaxValueService(@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisTaxValue");

		DataTableAjax<PaperBasicAnalysisD3Vo> response = new DataTableAjax<>();
		try {
			response = analysisTaxValueService.GetAnalysisTaxValue(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}
	// TODO 4
	@PostMapping("/analysis-taxRate-service-data")
	@ResponseBody
	public DataTableAjax<PaperBasicAnalysisD4Vo> listAnalysisTaxRateService(@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisTaxRateService");

		DataTableAjax<PaperBasicAnalysisD4Vo> response = new DataTableAjax<>();
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
	public DataTableAjax<PaperBasicAnalysisD5Vo> listAnalysisTaxAmtsService(@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisTaxAmtsService");

		DataTableAjax<PaperBasicAnalysisD5Vo> response = new DataTableAjax<>();
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
	public DataTableAjax<PaperBasicAnalysisD6Vo> listAnalysisTaxFilingService(@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisTaxFilingService");

		DataTableAjax<PaperBasicAnalysisD6Vo> response = new DataTableAjax<>();
		try {
			response = analysisTaxFilingService.GetAnalysisTaxFiling(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	// TODO 7
	@PostMapping("/analysis-income-compareLast-month-data")
	@ResponseBody
	public DataTableAjax<AnalysisIncomeCompareLastMonthVo> listAnalysisIncomeCompareLastMonthService(@RequestBody AnalysisFormVo request) {
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
	@PostMapping("/analysis-income-compareLast-year-data")
	@ResponseBody
	public DataTableAjax<AnalyzeCompareOldYearVo> listAnalysisIncomeCompareLastYearService(@RequestBody AnalysisFormVo request) {
		logger.info("listAnalysisIncomeCompareLastYearService");

		DataTableAjax<AnalyzeCompareOldYearVo> response = new DataTableAjax<>();
		try {
			response = analysisIncomeCompareLastYearService.GetAnalysisIncomeCompareLastYear(request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

}
