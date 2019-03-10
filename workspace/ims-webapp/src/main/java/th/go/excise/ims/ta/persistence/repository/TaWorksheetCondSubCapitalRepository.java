package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondSubCapital;

public interface TaWorksheetCondSubCapitalRepository extends CommonJpaCrudRepository<TaWorksheetCondSubCapital, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.draftNumber = :draftNumber")
	public List<TaWorksheetCondSubCapital> findByDraftNumber(@Param("draftNumber") String draftNumber);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.draftNumber = :draftNumber and e.dutyCode = :dutyCode")
	public TaWorksheetCondSubCapital findByDraftNumberAndDutyCode(@Param("draftNumber") String draftNumber, @Param("dutyCode") String dutyCode);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.analysisNumber = :analysisNumber and e.dutyCode = :dutyCode")
	public TaWorksheetCondSubCapital findByAnalysisNumberAndDutyCode(@Param("analysisNumber") String analysisNumber, @Param("dutyCode") String dutyCode);
	
}
