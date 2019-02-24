package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.common.constant.ProjectConstants.TA_MAS_COND_MAIN_TYPE;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainHdr;
import th.go.excise.ims.ta.persistence.entity.TaWsCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaWsCondHdr;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsCondDtlTaxRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsCondHdrRepository;

@Service
public class WorksheetConditionService {
	

	@Autowired
	TaWsCondHdrRepository taWsCondHdrRepository;
	
	@Autowired
	TaWsCondDtlTaxRepository taWsCondDtlTaxRepository;
	
	@Autowired
	TaMasCondMainHdrRepository taMasCondHdrRepository;
	
	@Autowired
	TaMasCondMainDtlRepository taMasCondDtlTaxRepository;
	
	@Autowired
	private TaxAuditFactorySelectionService taxAuditFactorySelectionService;
	
	public String insertWorkSheet(TaWsCondHdr formVo) throws SQLException {
		TaMasCondMainHdr headerMas = taMasCondHdrRepository.findByBudgetYear(formVo.getBudgetYear());
		List<TaMasCondMainDtl> dtlMas = taMasCondDtlTaxRepository.findByBudgetYearAndCondType(formVo.getBudgetYear(), TA_MAS_COND_MAIN_TYPE.TAX);
		TaWsCondDtlTax dtlWs = null;
		TaWsCondHdr headerWs = new TaWsCondHdr();
		String analysisNumber = ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.YYYYMMDDHHMMSS, ConvertDateUtils.LOCAL_EN);
		formVo.setAnalysisNumber(analysisNumber);
		formVo.setMonthNum(headerMas.getMonthNum());
		headerWs = taWsCondHdrRepository.save(formVo);
		if (headerWs.getBudgetYear() != null) {
			for (TaMasCondMainDtl obj : dtlMas) {
				dtlWs = new TaWsCondDtlTax();
				dtlWs.setAnalysisNumber(headerWs.getAnalysisNumber());
				dtlWs.setCondGroup(obj.getCondGroup());
				dtlWs.setTaxMonthStart(obj.getTaxMonthStart());
				dtlWs.setTaxMonthEnd(obj.getTaxMonthEnd());
				dtlWs.setRangeStart(new BigDecimal(obj.getRangeStart()));
				dtlWs.setRangeEnd(new BigDecimal(obj.getRangeEnd()));
				dtlWs.setRiskLevel(obj.getRiskLevel());

				taWsCondDtlTaxRepository.save(dtlWs);
			}
			taxAuditFactorySelectionService.selectFactoryProcess(analysisNumber);
		}
		return analysisNumber;
	}
	
	public List<TaWsCondHdr> findAllHdr() {
		List<TaWsCondHdr> list = taWsCondHdrRepository.findAll();
		return list;
	}

}
