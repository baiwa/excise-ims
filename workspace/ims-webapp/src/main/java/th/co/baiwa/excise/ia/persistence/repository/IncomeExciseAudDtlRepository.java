package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAudDtl;

public interface IncomeExciseAudDtlRepository extends CommonJpaCrudRepository<IncomeExciseAudDtl,Long> , IncomeExciseAudDtlRepositoryCustom {
	
	@Query(
			value = " select  SUBSTR(a.RECEIPT_NO,11,7) AS RECEIPT_SUB_NO,a.* from IA_INCOME_EXCISE_AUD_DTL a where a.IA_INCOME_EXCISE_AUD_ID = ? ORDER BY RECEIPT_SUB_NO ",
			nativeQuery = true
		)
	public List<IncomeExciseAudDtl> findByIaIncomeExciseAudId(Long iaIncomeExciseAudId);
}

