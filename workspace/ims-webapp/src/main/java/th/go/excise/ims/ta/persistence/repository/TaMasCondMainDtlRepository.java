package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaMasCondMainDtl;

public interface TaMasCondMainDtlRepository extends CommonJpaCrudRepository<TaMasCondMainDtl, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear order by e.condGroup")
	public List<TaMasCondMainDtl> findByBudgetYear(@Param("budgetYear") String budgetYear);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear and e.condType = :condType order by e.condGroup")
	public List<TaMasCondMainDtl> findByBudgetYearAndCondType(@Param("budgetYear") String budgetYear, @Param("condType") String condType);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.condNumber = :condNumber")
	public List<TaMasCondMainDtl> findByCondNumber(@Param("condNumber") String condNumber);
	
}
