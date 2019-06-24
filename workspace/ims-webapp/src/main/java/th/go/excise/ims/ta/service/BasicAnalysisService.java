package th.go.excise.ims.ta.service;

import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaH;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetHdr;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaHRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaPlanWorksheetHdrRepository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.TaPaperBaHFormVo;

@Service
public class BasicAnalysisService {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisService.class);
	
	private TaPlanWorksheetHdrRepository taPlanWorksheetHdrRepository;
	private TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository;
	private PaperSequenceService paperSequenceService;
	private TaPaperBaHRepository taPaperBaHRepository;
	
	private Map<String, AbstractBasicAnalysisService> basicAnalysisServiceMap = new LinkedHashMap<>();
	
	@Autowired
	public BasicAnalysisService(
			BasicAnalysisTaxQtyService basicAnalysisTaxQtyService,
			BasicAnalysisTaxRetailPriceService basicAnalysisTaxRetailPriceService,
			BasicAnalysisTaxValueService basicAnalysisTaxValueService,
			BasicAnalysisTaxRateService basicAnalysisTaxRateService,
			BasicAnalysisTaxAmtService basicAnalysisTaxAmtService,
			BasicAnalysisTaxFilingService basicAnalysisTaxFilingService,
			BasicAnalysisIncomeCompareLastMonthService basicAnalysisIncomeCompareLastMonthService,
			BasicAnalysisIncomeCompareLastYearService basicAnalysisIncomeCompareLastYearService,
			TaPlanWorksheetHdrRepository taPlanWorksheetHdrRepository,
			TaPlanWorksheetDtlRepository taPlanWorksheetDtlRepository,
			PaperSequenceService paperSequenceService,
			TaPaperBaHRepository taPaperBaHRepository) {
		basicAnalysisServiceMap.put("tax-qty", basicAnalysisTaxQtyService);
		basicAnalysisServiceMap.put("tax-retail-price", basicAnalysisTaxRetailPriceService);
		basicAnalysisServiceMap.put("tax-value", basicAnalysisTaxValueService);
		basicAnalysisServiceMap.put("tax-rate", basicAnalysisTaxRateService);
		basicAnalysisServiceMap.put("tax-amt", basicAnalysisTaxAmtService);
		basicAnalysisServiceMap.put("tax-filing", basicAnalysisTaxFilingService);
		basicAnalysisServiceMap.put("income-compare-last-month", basicAnalysisIncomeCompareLastMonthService);
		basicAnalysisServiceMap.put("income-compare-last-year", basicAnalysisIncomeCompareLastYearService);
		this.taPlanWorksheetHdrRepository = taPlanWorksheetHdrRepository;
		this.taPlanWorksheetDtlRepository = taPlanWorksheetDtlRepository;
		this.paperSequenceService = paperSequenceService;
		this.taPaperBaHRepository = taPaperBaHRepository;
	}
	
	public DataTableAjax<Object> inquiry(String analysisType, BasicAnalysisFormVo formVo) {
		AbstractBasicAnalysisService<Object> service = basicAnalysisServiceMap.get(analysisType);
		List<Object> voList = service.inquiry(formVo);
		
		DataTableAjax<Object> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(voList);

		return dataTableAjax;
	}
	
	@Transactional
	public void save(BasicAnalysisFormVo formVo) {
		logger.info("save");
		
		TaPaperBaH paperBaH = taPaperBaHRepository.findByPaperBaNumber(formVo.getPaperBaNumber());
		if (paperBaH == null) {
			TaPlanWorksheetDtl planWorksheeetDtl = taPlanWorksheetDtlRepository.findByAuditPlanCode(formVo.getAuditPlanCode());
			TaPlanWorksheetHdr planWorksheeetHdr = taPlanWorksheetHdrRepository.findByPlanNumber(planWorksheeetDtl.getPlanNumber());
			
			String paperBaNumber = paperSequenceService.getBasicAnalysisNumber(planWorksheeetDtl.getOfficeCode(), planWorksheeetHdr.getBudgetYear());
			LocalDate localDateStart = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]), Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
			LocalDate localDateEnd = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]), Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));
			
			paperBaH = new TaPaperBaH();
			paperBaH.setOfficeCode(planWorksheeetDtl.getOfficeCode());
			paperBaH.setBudgetYear(planWorksheeetHdr.getBudgetYear());
			paperBaH.setPlanNumber(planWorksheeetHdr.getPlanNumber());
			paperBaH.setAuditPlanCode(formVo.getAuditPlanCode());
			paperBaH.setPaperBaNumber(paperBaNumber);
			paperBaH.setNewRegId(formVo.getNewRegId());
			paperBaH.setDutyGroupId(formVo.getDutyGroupId());
			paperBaH.setStartDate(localDateStart);
			paperBaH.setEndDate(localDateEnd);
			paperBaH.setMonthIncType(formVo.getMonthIncomeType());
			paperBaH.setYearIncType(formVo.getYearIncomeType());
			paperBaH.setYearNum(Integer.parseInt(formVo.getYearNum()));
			paperBaH.setAnaResultText1(formVo.getCommentText1());
			paperBaH.setAnaResultText2(formVo.getCommentText2());
			paperBaH.setAnaResultText3(formVo.getCommentText3());
			paperBaH.setAnaResultText4(formVo.getCommentText4());
			paperBaH.setAnaResultText5(formVo.getCommentText5());
			paperBaH.setAnaResultText6(formVo.getCommentText6());
			paperBaH.setAnaResultText7(formVo.getCommentText7());
			paperBaH.setAnaResultText8(formVo.getCommentText8());
			
			formVo.setPaperBaNumber(paperBaNumber);
			for (AbstractBasicAnalysisService service : basicAnalysisServiceMap.values()) {
				service.save(formVo);
			}
		} else {
			paperBaH.setAnaResultText1(formVo.getCommentText1());
			paperBaH.setAnaResultText2(formVo.getCommentText2());
			paperBaH.setAnaResultText3(formVo.getCommentText3());
			paperBaH.setAnaResultText4(formVo.getCommentText4());
			paperBaH.setAnaResultText5(formVo.getCommentText5());
			paperBaH.setAnaResultText6(formVo.getCommentText6());
			paperBaH.setAnaResultText7(formVo.getCommentText7());
			paperBaH.setAnaResultText8(formVo.getCommentText8());
		}
		
		taPaperBaHRepository.save(paperBaH);
	}

	public List<String> getPaperBaNumberList(BasicAnalysisFormVo formVo) {
		return taPaperBaHRepository.findPaperBaNumberByAuditPlanCode(formVo.getAuditPlanCode());
	}
	
	public TaPaperBaH findBaH(TaPaperBaHFormVo form) {
//		String officeCode = UserLoginUtils.getCurrentUserBean().getOfficeCode();
//		TaPlanWorksheetDtl planWorksheeetDtl = taPlanWorksheetDtlRepository.findByAuditPlanCode(form.getAuditPlanCode());
//		TaPlanWorksheetHdr planWorksheeetHdr = taPlanWorksheetHdrRepository.findByPlanNumber(planWorksheeetDtl.getPlanNumber());
		TaPaperBaH paperBaH = taPaperBaHRepository.findByPaperBaNumber(form.getPaperBaNumber());
		if (null == paperBaH) {
			paperBaH = new TaPaperBaH();
		}
		return paperBaH;
	}
	
}
