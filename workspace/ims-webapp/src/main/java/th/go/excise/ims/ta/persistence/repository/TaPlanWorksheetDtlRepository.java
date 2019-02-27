package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;

public interface TaPlanWorksheetDtlRepository extends CommonJpaCrudRepository<TaPlanWorksheetDtl, Long>, TaPlanWorksheetDtlRepositoryCustom {

	@Query("select e from #{#entityName} e where e.analysisNumber = :analysisNumber and e.planNumber = :planNumber and e.officeCode = :officeCode")
	public List<TaPlanWorksheetDtl> findByAnalysisNumberAndPlanNumberAndOfficeCode(@Param("analysisNumber") String analysisNumber, @Param("planNumber") String planNumber, @Param("officeCode") String officeCode);

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.planNumber = :planNumber and e.officeCode = :officeCode")
	public List<TaPlanWorksheetDtl> findByPlanNumberAndOfficeCode(@Param("planNumber") String planNumber, @Param("officeCode") String officeCode);

	@Modifying
	@Query(value = "update TA_PLAN_WORKSHEET_DTL set IS_DELETED ='Y' where PLAN_NUMBER	= :planNumber and NEW_REG_ID = :newRegId", nativeQuery = true)
	public void deleteByPlanNumberAndNewRegId(@Param("planNumber") String planNumber, @Param("newRegId") String newRegId);
}
