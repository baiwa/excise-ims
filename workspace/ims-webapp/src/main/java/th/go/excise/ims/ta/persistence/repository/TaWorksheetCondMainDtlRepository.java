package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;

public interface TaWorksheetCondMainDtlRepository extends CommonJpaCrudRepository<TaWorksheetCondMainDtl, Long> {

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.draftNumber = :draftNumber order by e.condGroup")
	public List<TaWorksheetCondMainDtl> findByDraftNumber(@Param("draftNumber") String draftNumber);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.analysisNumber = :analysisNumber order by e.condGroup")
	public List<TaWorksheetCondMainDtl> findByAnalysisNumber(@Param("analysisNumber") String analysisNumber);

}
