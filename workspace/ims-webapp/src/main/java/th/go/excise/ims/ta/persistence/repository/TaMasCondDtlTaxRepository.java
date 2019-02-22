package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;

public interface TaMasCondDtlTaxRepository extends CommonJpaCrudRepository<TaMasCondDtlTax, Long> {
	
	
	public List<TaMasCondDtlTax> findByBudgetYear(String budgetYear);
	
	public List<TaMasCondDtlTax> findByBudgetYearAndIsDeleted(String budgetYear, String isDeleted);
	
	public List<TaMasCondDtlTax> findByBudgetYearAndCondType(String budgetYear , String condType);
	
	public List<TaMasCondDtlTax> findByBudgetYearAndCondTypeAndIsDeleted(String budgetYear , String condType, String isDeleted);
}
