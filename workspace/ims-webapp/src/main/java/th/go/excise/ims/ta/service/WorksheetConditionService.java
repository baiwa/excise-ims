package th.go.excise.ims.ta.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaMasCondHdr;
import th.go.excise.ims.ta.persistence.entity.TaWsCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaWsCondHdr;
import th.go.excise.ims.ta.persistence.repository.TaMasCondDtlTaxRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondHdrRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsCondDtlTaxRepository;
import th.go.excise.ims.ta.persistence.repository.TaWsCondHdrRepository;

@Service
public class WorksheetConditionService {
	

	@Autowired
	TaWsCondHdrRepository taWsCondHdrRepository;
	
	@Autowired
	TaWsCondDtlTaxRepository taWsCondDtlTaxRepository;
	
	@Autowired
	TaMasCondHdrRepository taMasCondHdrRepository;
	
	@Autowired
	TaMasCondDtlTaxRepository taMasCondDtlTaxRepository;
	
	@Autowired
	private TaxAuditFactorySelectionService taxAuditFactorySelectionService;
	
	public String insertWorkSheet(TaWsCondHdr formVo) throws SQLException {
		TaMasCondHdr headerMas = taMasCondHdrRepository.findByBudgetYear(formVo.getBudgetYear());
		List<TaMasCondDtlTax> dtlMas = taMasCondDtlTaxRepository.findByBudgetYear(formVo.getBudgetYear());
		TaWsCondDtlTax dtlWs = null;
		TaWsCondHdr headerWs = new TaWsCondHdr();
		String analysisNumber = ConvertDateUtils.formatDateToString(new Date(), ConvertDateUtils.YYYYMMDDHHMMSS, ConvertDateUtils.LOCAL_EN);
		formVo.setAnalysisNumber(analysisNumber);
		formVo.setMonthNum(headerMas.getMonthNum());
		formVo.setAreaSeeFlag(headerMas.getAreaSeeFlag());
		formVo.setAreaSelectFlag(headerMas.getAreaSelectFlag());
		formVo.setNoAuditYearNum(headerMas.getNoAuditYearNum());
		headerWs = taWsCondHdrRepository.save(formVo);
		if (headerWs.getBudgetYear() != null) {
			for (TaMasCondDtlTax obj : dtlMas) {
				dtlWs = new TaWsCondDtlTax();
				dtlWs.setAnalysisNumber(headerWs.getAnalysisNumber());
				dtlWs.setCondGroup(obj.getCondGroup());
				dtlWs.setTaxMonthStart(obj.getTaxMonthStart());
				dtlWs.setTaxMonthEnd(obj.getTaxMonthEnd());
				dtlWs.setRangeStart(obj.getRangeStart());
				dtlWs.setRangeEnd(obj.getRangeEnd());
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
