package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public interface TaWorksheetHdrRepository extends CommonJpaCrudRepository<TaWorksheetHdr, Long>, TaWorksheetHdrRepositoryCustom {

	@Query("select new java.lang.String(e.analysisNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.budgetYear = :budgetYear and e.worksheetStatus = :worksheetStatus order by e.analysisNumber desc")
	public List<String> findAllAnalysisNumberByOfficeCodeAndBudgetYearAndWorksheetStatus(@Param("officeCode") String officeCode, @Param("budgetYear") String budgetYear, @Param("worksheetStatus") String worksheetStatus);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.analysisNumber = :analysisNumber")
	public TaWorksheetHdr findByAnalysisNumber(@Param("analysisNumber") String analysisNumber);

	TaWorksheetHdr findByDraftNumber(String draftNumber);
	
	// This method for Clear Data
	@Modifying
	@Query("update #{#entityName} e set e.worksheetStatus = :worksheetStatus where e.isDeleted = '" + FLAG.N_FLAG + "' and e.budgetYear = :budgetYear")
	public void updateWorksheetStatusByBudgetYear(@Param("worksheetStatus") String worksheetStatus, @Param("budgetYear") String budgetYear);
	
}
