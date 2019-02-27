package th.go.excise.ims.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TaPlanWorksheetDtlRepository extends CommonJpaCrudRepository<TaPlanWorksheetDtl, Long>, TaPlanWorksheetDtlRepositoryCustom {
	
	@Query("select e from #{#entityName} e where e.analysisNumber = :analysisNumber and e.planNumber = :planNumber and e.officeCode = :officeCode")
	public List<TaPlanWorksheetDtl> findByAnalysisNumberAndPlanNumberAndOfficeCode(@Param("analysisNumber") String analysisNumber, @Param("planNumber") String planNumber, @Param("officeCode") String officeCode);

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.planNumber = :planNumber and e.officeCode = :officeCode")
	public List<TaPlanWorksheetDtl> findByPlanNumberAndOfficeCode(@Param("planNumber") String planNumber, @Param("officeCode") String officeCode);
	
}
