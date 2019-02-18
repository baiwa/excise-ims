package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;
import th.go.excise.ims.ta.persistence.entity.TaMasCondHdr;
import th.go.excise.ims.ta.persistence.repository.TaMasCondDtlTaxRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondHdrRepository;
import th.go.excise.ims.ta.vo.MasterConditionHdrDtlVo;

@Service
public class MasterConditionService {

	@Autowired
	TaMasCondHdrRepository taMasCondHdrRepository;

	@Autowired
	TaMasCondDtlTaxRepository taMasCondDtlTaxRepository;

	public void insertMaster(MasterConditionHdrDtlVo formVo) {
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

	public void updateWorkSheet(MasterConditionHdrDtlVo formVo) {
		TaMasCondDtlTax dtl = null;
		TaMasCondHdr header = taMasCondHdrRepository.findById(formVo.getHeader().getCondHdrId()).get();
		header.setBudgetYear(formVo.getHeader().getBudgetYear());
		header.setMonthNum(formVo.getHeader().getMonthNum());
		header.setNoAuditYearNum(formVo.getHeader().getNoAuditYearNum());
		header.setAreaSeeFlag(formVo.getHeader().getAreaSeeFlag());
		header.setAreaSelectFlag(formVo.getHeader().getAreaSelectFlag());

		taMasCondHdrRepository.save(header);

		if (header.getBudgetYear() != null) {
			List<TaMasCondDtlTax> list = taMasCondDtlTaxRepository.findByBudgetYear(formVo.getHeader().getBudgetYear());
//			List<TaMasCondDtlTax> listY = taMasCondDtlTaxRepository
//					.findByBudgetYearY(formVo.getHeader().getBudgetYear());

			Collections.sort(list, new Comparator<TaMasCondDtlTax>() {
				public int compare(TaMasCondDtlTax dtlTax, TaMasCondDtlTax dtlTax2) {
					int condDtlTaxId = dtlTax.getCondDtlTaxId().compareTo(dtlTax2.getCondDtlTaxId());
					if (condDtlTaxId == 0) {
						return condDtlTaxId;
					}
					return dtlTax.getCondDtlTaxId() > dtlTax2.getCondDtlTaxId() ? 1
							: dtlTax.getCondDtlTaxId() < dtlTax2.getCondDtlTaxId() ? -1 : 0;
				}
			});

			if (list.size() - formVo.getDetail().size() > 0) {
				boolean checkDelete = true;
				for (TaMasCondDtlTax obj : list) {
					checkDelete = true;
					for (TaMasCondDtlTax ob : formVo.getDetail()) {
						if (ob.getCondDtlTaxId() == obj.getCondDtlTaxId()) {
							checkDelete = false;
							break;
						}
					}
					if (checkDelete) {
						dtl = taMasCondDtlTaxRepository.findById(obj.getCondDtlTaxId()).get();
						dtl.setIsDeleted("Y");
						
						taMasCondDtlTaxRepository.save(dtl);						
					}
				}
			}
//			else if (list.size() - formVo.getDetail().size() < 0) {
//				for (TaMasCondDtlTax obj : listY) {
//					****** cannot use findById cause cannot find isDelete value is 'Y' *****
//					dtl = taMasCondDtlTaxRepository.findById(obj.getCondDtlTaxId()).get(); 
//					dtl.setIsDeleted("N");
//
//					taMasCondDtlTaxRepository.save(dtl);
//
//				}
//
//			}

			for (TaMasCondDtlTax obj : formVo.getDetail()) {
				if (obj.getCondDtlTaxId() == null) {
					dtl = new TaMasCondDtlTax();
					dtl.setBudgetYear(header.getBudgetYear());
					dtl.setCondGroup(obj.getCondGroup());
					dtl.setTaxMonthStart(obj.getTaxMonthStart());
					dtl.setTaxMonthEnd(obj.getTaxMonthEnd());
					dtl.setRangeStart(obj.getRangeStart());
					dtl.setRangeEnd(obj.getRangeEnd());
					dtl.setRiskLevel(obj.getRiskLevel());

				} else {
					dtl = taMasCondDtlTaxRepository.findById(obj.getCondDtlTaxId()).get();
					dtl.setBudgetYear(header.getBudgetYear());
					dtl.setCondGroup(obj.getCondGroup());
					dtl.setTaxMonthStart(obj.getTaxMonthStart());
					dtl.setTaxMonthEnd(obj.getTaxMonthEnd());
					dtl.setRangeStart(obj.getRangeStart());
					dtl.setRangeEnd(obj.getRangeEnd());
					dtl.setRiskLevel(obj.getRiskLevel());
				}

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

	public List<TaMasCondHdr> findAllHdr() {
		List<TaMasCondHdr> list = taMasCondHdrRepository.findAll();
		return list;
	}

}
