package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaMasCondHdr;
import th.go.excise.ims.ta.persistence.repository.TaMasCondDtlTaxRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondHdrRepository;
import th.go.excise.ims.ta.vo.TaMasCondHdrDtlVo;

@Service
public class TaMasCondHdrService {

	@Autowired
	TaMasCondHdrRepository taMasCondHdrRepository;

	@Autowired
	TaMasCondDtlTaxRepository taMasCondDtlTaxRepository;

	public void insertWorkSheet(TaMasCondHdrDtlVo formVo) {
		TaMasCondDtlTax dtl = null;
		TaMasCondHdr header = new TaMasCondHdr();
		header = taMasCondHdrRepository.save(formVo.getHeader());
		if (header.getBudgetYear() != null) {
			for (TaMasCondDtlTax obj : formVo.getDetail()) {
				dtl = new TaMasCondDtlTax();
				dtl.setBudgetYear(header.getBudgetYear());
				dtl.setCondGroup(obj.getCondGroup());
				dtl.setTaxMonthStart(obj.getTaxMonthStart());
				dtl.setTaxMonthEnd(obj.getTaxMonthEnd());
				dtl.setRangeStart(obj.getRangeStart());
				dtl.setRangeEnd(obj.getRangeEnd());
				dtl.setRiskLevel(obj.getRiskLevel());

				taMasCondDtlTaxRepository.save(dtl);
			}
		}
	}

	public void updateWorkSheet(TaMasCondHdrDtlVo formVo) {
		TaMasCondDtlTax dtl = null;
		TaMasCondHdr header = taMasCondHdrRepository.findById(formVo.getHeader().getCondHdrId()).get();
		header.setBudgetYear(formVo.getHeader().getBudgetYear());
		header.setMonthNum(formVo.getHeader().getMonthNum());
		header.setNoAuditYearNum(formVo.getHeader().getNoAuditYearNum());
		header.setAreaSeeFlag(formVo.getHeader().getAreaSeeFlag());
		header.setAreaSelectFlag(formVo.getHeader().getAreaSelectFlag());
		
		taMasCondHdrRepository.save(header);
		
		if (header.getBudgetYear() != null) {
			for (TaMasCondDtlTax obj : formVo.getDetail()) {
				dtl = taMasCondDtlTaxRepository.findById(obj.getCondDtlTaxId()).get();
				dtl.setBudgetYear(header.getBudgetYear());
				dtl.setCondGroup(obj.getCondGroup());
				dtl.setTaxMonthStart(obj.getTaxMonthStart());
				dtl.setTaxMonthEnd(obj.getTaxMonthEnd());
				dtl.setRangeStart(obj.getRangeStart());
				dtl.setRangeEnd(obj.getRangeEnd());
				dtl.setRiskLevel(obj.getRiskLevel());

				taMasCondDtlTaxRepository.save(dtl);
			}
		}
	}

	public TaMasCondHdr findByBudgetYearHdr(TaMasCondHdr hdr) {
		TaMasCondHdr budgetYear = new TaMasCondHdr();
		budgetYear = taMasCondHdrRepository.findByBudgetYear(hdr.getBudgetYear());
		return budgetYear;
	}

	public List<TaMasCondDtlTax> findByBudgetYearDtl(TaMasCondDtlTax dtl) {
		List<TaMasCondDtlTax> budgetYear = new ArrayList<TaMasCondDtlTax>();
		budgetYear = taMasCondDtlTaxRepository.findByBudgetYear(dtl.getBudgetYear());
		return budgetYear;
	}

}
