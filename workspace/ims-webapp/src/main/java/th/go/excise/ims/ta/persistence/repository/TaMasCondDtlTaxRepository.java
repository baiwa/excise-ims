package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaMasCondDtlTax;

public interface TaMasCondDtlTaxRepository extends CommonJpaCrudRepository<TaMasCondDtlTax, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear")
	public List<TaMasCondDtlTax> findByBudgetYear(@Param("budgetYear") String budgetYear);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.Y_FLAG + "' and e.budgetYear = :budgetYear")
	public List<TaMasCondDtlTax> findByBudgetYearY(@Param("budgetYear") String budgetYear);
	
}
