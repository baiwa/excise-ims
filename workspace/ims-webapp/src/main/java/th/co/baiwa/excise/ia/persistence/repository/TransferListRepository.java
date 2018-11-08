package th.co.baiwa.excise.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.TransferList;

public interface TransferListRepository extends CommonJpaCrudRepository<TransferList, BigDecimal> {

	@Query(
			value = "SELECT * FROM IA_TRANSFER_LIST T WHERE T.IS_DELETED = 'N' AND T.TRANSFER_LIST = ? AND T.BUDGET_TYPE = ? AND T.ACTIVITIES = ?  ORDER BY T.TRANSFER_ID ASC",
			nativeQuery = true
	)
	List<TransferList> findByFilter(String transferList, String budgetType, String activities);
//	, Date start, Date end
//	T.IS_DELETED = '"+FLAG.N_FLAG+"' AND
	
}
