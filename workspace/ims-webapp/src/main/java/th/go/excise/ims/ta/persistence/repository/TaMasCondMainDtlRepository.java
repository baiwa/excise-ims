package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;

public interface TaMasCondMainDtlRepository extends CommonJpaCrudRepository<TaMasCondMainDtl, Long> {
	
	
	public List<TaMasCondMainDtl> findByBudgetYear(String budgetYear);
	
	public List<TaMasCondMainDtl> findByBudgetYearAndIsDeleted(String budgetYear, String isDeleted);
	
	public List<TaMasCondMainDtl> findByBudgetYearAndCondType(String budgetYear , String condType);
	
	public List<TaMasCondMainDtl> findByBudgetYearAndCondTypeAndIsDeleted(String budgetYear , String condType, String isDeleted);
}
