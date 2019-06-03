package th.go.excise.ims.ta.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.security.domain.UserBean;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaH;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaHRepository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;

@Service
public class BasicAnalysisService {
	
	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisService.class);
	
//	private Auditc
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
			TaPaperBaHRepository taPaperBaHRepository) {
		basicAnalysisServiceMap.put("tax-qty", basicAnalysisTaxQtyService);
		basicAnalysisServiceMap.put("tax-retail-price", basicAnalysisTaxRetailPriceService);
		basicAnalysisServiceMap.put("tax-value", basicAnalysisTaxValueService);
		basicAnalysisServiceMap.put("tax-rate", basicAnalysisTaxRateService);
		basicAnalysisServiceMap.put("tax-amt", basicAnalysisTaxAmtService);
		basicAnalysisServiceMap.put("tax-filing", basicAnalysisTaxFilingService);
		basicAnalysisServiceMap.put("income-compare-last-month", basicAnalysisIncomeCompareLastMonthService);
		basicAnalysisServiceMap.put("income-compare-last-year", basicAnalysisIncomeCompareLastYearService);
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
			UserBean userBean = UserLoginUtils.getCurrentUserBean();
			String paperBaNumber = "";
			
			paperBaH = new TaPaperBaH();
			paperBaH.setOfficeCode(userBean.getOfficeCode());
			//paperBaH.setBudgetYear(formVo.get);
			//paperBaH.setPlanNumber(formVo.get);
			paperBaH.setAuditPlanCode(formVo.getAuditPlanCode());
			paperBaH.setPaperBaNumber(paperBaNumber);
			paperBaH.setNewRegId(formVo.getNewRegId());
			paperBaH.setDutyGroupId(formVo.getDutyGroupId());
			//paperBaH.setBaDateStart(formVo.get);
			//paperBaH.setBaDateEnd(formVo.get);
			paperBaH.setBaText(formVo.getCommentText());
			
			//formVo.setPaperBaNumber(paperBaNumber);
			//for (AbstractBasicAnalysisService service : basicAnalysisServiceMap.values()) {
			//	service.save(formVo);
			//}
		} else {
			paperBaH.setBaText(formVo.getCommentText());
		}
		
		taPaperBaHRepository.save(paperBaH);
	}
	
}
