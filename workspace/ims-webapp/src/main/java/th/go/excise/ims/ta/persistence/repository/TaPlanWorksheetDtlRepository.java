package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;

public interface TaPlanWorksheetDtlRepository extends CommonJpaCrudRepository<TaPlanWorksheetDtl, Long>, TaPlanWorksheetDtlRepositoryCustom {

	@Query("select new java.lang.String(e.newRegId) from #{#entityName} e where e.officeCode = :officeCode and e.planNumber = :planNumber")
	public List<String> findNewRegIdByOfficeCodeAndPlanNumber(@Param("officeCode") String officeCode, @Param("planNumber") String planNumber);
	
	@Query("select new java.lang.String(e.newRegId) from #{#entityName} e where e.isDeleted = '"+ FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.planNumber = :planNumber")
	public List<String> findNewRegIdByOfficeCodeAndPlanNumberAndIsDeletedFlagN(@Param("officeCode") String officeCode, @Param("planNumber") String planNumber);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.planNumber = :planNumber")
	public List<TaPlanWorksheetDtl> findByOfficeCodeAndPlanNumber(@Param("officeCode") String officeCode, @Param("planNumber") String planNumber);

	@Modifying
	@Query(value = "update TA_PLAN_WORKSHEET_DTL set IS_DELETED ='Y' where PLAN_NUMBER	= :planNumber and NEW_REG_ID = :newRegId", nativeQuery = true)
	public void deleteByPlanNumberAndNewRegId(@Param("planNumber") String planNumber, @Param("newRegId") String newRegId);
	
	@Modifying
	@Query("update #{#entityName} e set e.isDeleted = :isDelete where e.planNumber = :planNumber and e.newRegId = :newRegId")
	public void updateIsDeletedByPlanNumberAndNewRegId(@Param("isDelete") String isDelete, @Param("planNumber") String planNumber, @Param("newRegId") String newRegId);
	
	@Modifying
	@Query(value = "DELETE FROM TA_PLAN_WORKSHEET_DTL WHERE PLAN_NUMBER IN (SELECT PLAN_NUMBER FROM TA_PLAN_WORKSHEET_HDR WHERE BUDGET_YEAR = :budgetYear)", nativeQuery = true)
	public void forceDeleteByBudgetYear(@Param("budgetYear") String budgetYear);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.newRegId = :newRegId")
	public TaPlanWorksheetDtl findByOfficeCodeAndNewRegId(@Param("officeCode") String officeCode, @Param("newRegId") String newRegId);
	
	@Modifying
//	@Query(value = "update TA_PLAN_WORKSHEET_DTL set AUDIT_STATUS = :auditStatus where PLAN_NUMBER	= :planNumber ", nativeQuery = true)
	@Query("update #{#entityName} e set e.auditStatus = :auditStatus where e.planNumber = :planNumber ")
	public void updateAuditStatusByPlanNumber(@Param("auditStatus") String auditStatus, @Param("planNumber") String planNumber);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode LIKE :officeCode and e.planNumber = :planNumber")
	public List<TaPlanWorksheetDtl> findByOfficeCodeAndPlanNumberLike(@Param("officeCode") String officeCode, @Param("planNumber") String planNumber);
	
}
