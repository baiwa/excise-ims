package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.IaWithdrawalPersons;

public interface IaWithdrawalPersonsRepository extends CommonJpaCrudRepository<IaWithdrawalPersons, Long> {

	@Query(value = "SELECT H.* FROM IA_WITHDRAWAL_PERSONS H WHERE H.IS_DELETED='" + FLAG.N_FLAG
			+ "' AND H.WITHDRAWAL_ID=? ORDER BY H.WITHDRAWAL_PERSONS_ID ASC", nativeQuery = true)
	public List<IaWithdrawalPersons> findByWithDrawalId(Long withDrawalId);

}
