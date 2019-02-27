
package th.go.excise.ims.ta.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;

public interface TaWorksheetDtlRepository extends CommonJpaCrudRepository<TaWorksheetDtl, Long> ,TaWorksheetDtlCustom{
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.analysisNumber = :analysisNumber and e.newRegId = :newRegId")
	public TaWorksheetDtl findByAnalysisNumberAndNewRegId(@Param("analysisNumber") String analysisNumber, @Param("newRegId")  String newRegId);
	
}
