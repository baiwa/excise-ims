package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.VerifyAccountDetil;

public interface VerifyAccountDtlRepository extends CommonJpaCrudRepository<VerifyAccountDetil, Long> {
	
	@Query(
			value = " SELECT * FROM IA_VERIFY_ACCOUNT_DETIL DTL WHERE DTL.IS_DELETED = '" + FLAG.N_FLAG + "' AND DTL.VERIFY_ACCOUNT_HEADER_ID = ? ORDER BY DTL.VERIFY_ACCOUNT_DETIL_ID ASC ", 
			nativeQuery = true
			)

	List<VerifyAccountDetil> findByHeaderId(long verifyAccountHeaderId);

}
