package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAMETER_GROUP;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.go.excise.ims.common.constant.ProjectConstants.TA_MAS_COND_MAIN_TYPE;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainHdr;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainDtlRepository;
import th.go.excise.ims.ta.persistence.repository.TaMasCondMainHdrRepository;
import th.go.excise.ims.ta.vo.ConditionMessageVo;
import th.go.excise.ims.ta.vo.MasterConditionMainHdrDtlVo;

@Service
public class MasterConditionMainService {

	@Autowired
	private TaMasCondMainHdrRepository taMasCondMainHdrRepository;

	@Autowired
	private TaMasCondMainDtlRepository taMasCondMainDtlRepository;

	public void insertMaster(MasterConditionMainHdrDtlVo formVo) {
		TaMasCondMainDtl dtl = null;
		List<TaMasCondMainDtl> dtlList = new ArrayList<>();
		TaMasCondMainHdr header = new TaMasCondMainHdr();
		header = taMasCondMainHdrRepository.save(formVo.getHeader());
		if (header.getBudgetYear() != null) {
			for (TaMasCondMainDtl obj : formVo.getDetail()) {
				dtl = new TaMasCondMainDtl();
				dtl.setBudgetYear(header.getBudgetYear());
				dtl.setCondGroup(obj.getCondGroup());
				dtl.setTaxMonthStart(obj.getTaxMonthStart());
				dtl.setTaxMonthEnd(obj.getTaxMonthEnd());
				dtl.setRangeStart(obj.getRangeStart());
				dtl.setRangeEnd(obj.getRangeEnd());
				dtl.setRiskLevel(obj.getRiskLevel());
				dtl.setCondType("T");
				dtlList.add(dtl);
			}
			dtl = new TaMasCondMainDtl();
			dtl.setBudgetYear(header.getBudgetYear());
			dtl.setCondGroup(String.valueOf(formVo.getDetail().size() + 1));
			dtl.setCondType(TA_MAS_COND_MAIN_TYPE.OTHER);
			dtlList.add(dtl);
			taMasCondMainDtlRepository.saveAll(dtlList);
		}
	}

	public void updateMaster(MasterConditionMainHdrDtlVo formVo) {
		TaMasCondMainDtl dtl = null;
		List<TaMasCondMainDtl> dtlList = new ArrayList<>();
		TaMasCondMainHdr header = taMasCondMainHdrRepository.findById(formVo.getHeader().getCondMainHdrId()).get();
		header.setBudgetYear(formVo.getHeader().getBudgetYear());
		header.setMonthNum(formVo.getHeader().getMonthNum());

		taMasCondMainHdrRepository.save(header);

		if (header.getBudgetYear() != null) {
			List<TaMasCondMainDtl> list = taMasCondMainDtlRepository.findByBudgetYearAndCondType(formVo.getHeader().getBudgetYear(), TA_MAS_COND_MAIN_TYPE.TAX);
//			List<TaMasCondDtlTax> listY = taMasCondDtlTaxRepository.findByBudgetYearAndIsDeleted(formVo.getHeader().getBudgetYear(), FLAG.Y_FLAG);

			Collections.sort(list, new Comparator<TaMasCondMainDtl>() {
				public int compare(TaMasCondMainDtl dtlTax, TaMasCondMainDtl dtlTax2) {
					int condGroup = dtlTax.getCondGroup().compareTo(dtlTax2.getCondGroup());
					if (condGroup == 0) {
						return condGroup;
					}
					return  Long.valueOf(dtlTax.getCondGroup()) > Long.valueOf(dtlTax2.getCondGroup()) ? 1 : Long.valueOf(dtlTax.getCondGroup()) < Long.valueOf(dtlTax2.getCondGroup()) ? -1 : 0;
				}
			});

			if (list.size() - formVo.getDetail().size() > 0) {
				boolean checkDelete = true;
				for (TaMasCondMainDtl obj : list) {
					checkDelete = true;
					for (TaMasCondMainDtl ob : formVo.getDetail()) {
						if (ob.getCondMainDtlId() == obj.getCondMainDtlId()) {
							checkDelete = false;
							break;
						}
					}
					if (checkDelete) {
						dtl = taMasCondMainDtlRepository.findById(obj.getCondMainDtlId()).get();
						dtl.setIsDeleted(FLAG.Y_FLAG);
						dtlList.add(dtl);
					}
				}
				taMasCondMainDtlRepository.saveAll(dtlList);
			} 
//			else if (list.size() - formVo.getDetail().size() < 0) {
//				for (TaMasCondDtlTax obj : listY) {
//					dtl = taMasCondDtlTaxRepository.findById(obj.getCondDtlTaxId()).get(); 
//					dtl.setIsDeleted(FLAG.N_FLAG);
//
//					dtlList.add(dtl);
//				}
//				taMasCondDtlTaxRepository.saveAll(dtlList);
//			}
			
			dtlList = new ArrayList<>();
			for (TaMasCondMainDtl obj : formVo.getDetail()) {
				if (obj.getCondMainDtlId() == null) {
					dtl = new TaMasCondMainDtl();
					dtl.setBudgetYear(header.getBudgetYear());
					dtl.setCondGroup(obj.getCondGroup());
					dtl.setTaxMonthStart(obj.getTaxMonthStart());
					dtl.setTaxMonthEnd(obj.getTaxMonthEnd());
					dtl.setRangeStart(obj.getRangeStart());
					dtl.setRangeEnd(obj.getRangeEnd());
					dtl.setRiskLevel(obj.getRiskLevel());
					dtl.setCondType(TA_MAS_COND_MAIN_TYPE.TAX);

				} else {
					dtl = taMasCondMainDtlRepository.findById(obj.getCondMainDtlId()).get();
					dtl.setBudgetYear(header.getBudgetYear());
					dtl.setCondGroup(obj.getCondGroup());
					dtl.setTaxMonthStart(obj.getTaxMonthStart());
					dtl.setTaxMonthEnd(obj.getTaxMonthEnd());
					dtl.setRangeStart(obj.getRangeStart());
					dtl.setRangeEnd(obj.getRangeEnd());
					dtl.setRiskLevel(obj.getRiskLevel());
				}

				dtlList.add(dtl);

			}
			dtl = taMasCondMainDtlRepository.findByBudgetYearAndCondType(formVo.getHeader().getBudgetYear(), TA_MAS_COND_MAIN_TYPE.OTHER).get(0);
			dtl.setCondGroup(String.valueOf(formVo.getDetail().size() + 1));
			dtlList.add(dtl);
			taMasCondMainDtlRepository.saveAll(dtlList);
		}
	}

	public TaMasCondMainHdr findByBudgetYearHdr(TaMasCondMainHdr hdr) {
		TaMasCondMainHdr budgetYear = new TaMasCondMainHdr();
		budgetYear = taMasCondMainHdrRepository.findByBudgetYear(hdr.getBudgetYear());
		return budgetYear;
	}

	public List<TaMasCondMainDtl> findByBudgetYearDtl(TaMasCondMainDtl dtl) {
		List<TaMasCondMainDtl> budgetYear = new ArrayList<TaMasCondMainDtl>();
		budgetYear = taMasCondMainDtlRepository.findByBudgetYearAndCondType(dtl.getBudgetYear(), TA_MAS_COND_MAIN_TYPE.TAX);
		return budgetYear;
	}

	public List<TaMasCondMainHdr> findAllHdr() {
		List<TaMasCondMainHdr> list = taMasCondMainHdrRepository.findAll();
		return list;
	}

	public ConditionMessageVo conditionMessage() {
		ConditionMessageVo msgVo = new ConditionMessageVo();
		msgVo.setMsgMonth1(ApplicationCache.getParamInfoByCode(PARAMETER_GROUP.TA_MAS_COND_MAIN_DESC, "MONTH1").getValue1());
		msgVo.setMsgMonth2(ApplicationCache.getParamInfoByCode(PARAMETER_GROUP.TA_MAS_COND_MAIN_DESC, "MONTH2").getValue1());
		msgVo.setMsgTax1(ApplicationCache.getParamInfoByCode(PARAMETER_GROUP.TA_MAS_COND_MAIN_DESC, "TAX1").getValue1());
		msgVo.setMsgTax2(ApplicationCache.getParamInfoByCode(PARAMETER_GROUP.TA_MAS_COND_MAIN_DESC, "TAX2").getValue1());
		msgVo.setMsgTax3(ApplicationCache.getParamInfoByCode(PARAMETER_GROUP.TA_MAS_COND_MAIN_DESC, "TAX3").getValue1());
		return msgVo;
	}

}
