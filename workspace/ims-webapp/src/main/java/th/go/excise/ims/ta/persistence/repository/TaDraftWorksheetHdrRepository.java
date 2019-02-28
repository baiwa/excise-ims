package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetHdr;

public interface TaDraftWorksheetHdrRepository extends CommonJpaCrudRepository<TaDraftWorksheetHdr, Long>, TaDraftWorksheetHdrRepositoryCustom {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.draftNumber = :draftNumber")
	public TaDraftWorksheetHdr findByDraftNumber(@Param("draftNumber") String draftNumber);
	
	@Query("select new java.lang.String(e.draftNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.budgetYear = :budgetYear order by e.draftNumber desc")
	public List<String> findAllDraftNumberByOfficeCodeAndBudgetYear(@Param("officeCode") String officeCode, @Param("budgetYear") String budgetYear);
	
}
