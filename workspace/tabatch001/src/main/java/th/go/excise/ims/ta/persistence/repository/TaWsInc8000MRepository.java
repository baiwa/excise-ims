package th.go.excise.ims.ta.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;

public interface TaWsInc8000MRepository extends CommonJpaCrudRepository<TaWsInc8000M, Long>, TaWsInc8000MRepositoryCustom {
	
	@Modifying
	@Query(
		value = "DELETE FROM TA_WS_INC8000_M WHERE TAX_YEAR = :taxYear AND TAX_MONTH = :taxMonth",
		nativeQuery = true
	)
	public void forceDeleteByTaxYearAndTaxMonth(@Param("taxYear") String taxYear, @Param("taxMonth") String taxMonth);
	
}
