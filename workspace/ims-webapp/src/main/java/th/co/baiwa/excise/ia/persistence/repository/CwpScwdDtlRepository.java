package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;

public interface CwpScwdDtlRepository extends CommonJpaCrudRepository<CwpScwdDtl, Long> {
	
//	@Query(
//			value = " SELECT DISTINCT C.BUDGET_CODE FROM IA_CWP_SCWD_DTL C WHERE C.CWP_SCWD_HDR_ID = ?1 ",
//			nativeQuery = true
//	)
//	public List<CwpScwdDtl> findByHDRId(long idFile1);

//	@Query(
//			value = " SELECT  C.BUDGET_CODE , to_char(C.RECORD_DATE , 'yyyy/mm') "
//					+ "FROM IA_CWP_SCWD_DTL C"
//					+ " WHERE C.BUDGET_CODE = ? "
//					+ " GROUP BY BUDGET_CODE , to_char(C.RECORD_DATE , 'yyyy/mm') "
//					+ " ORDER BY C.BUDGET_CODE ,to_char(C.RECORD_DATE , 'yyyy/mm') ",
//			nativeQuery = true
//	)
//	public List<Object> findDevideMonth(String budgetCode);

	@Query(
			value = " SELECT * FROM IA_CWP_SCWD_DTL C "
					+ "WHERE C.BUDGET_CODE = ?1 "
					+ "AND to_char(C.RECORD_DATE , 'yyyy/mm') = ?2 ",
			nativeQuery = true
	)
	public List<CwpScwdDtl> findGroupMonth(String budgetCode, String calMonth);

}
