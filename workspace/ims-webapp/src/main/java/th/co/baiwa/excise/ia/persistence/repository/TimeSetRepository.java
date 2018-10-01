package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.TimeSet;

public interface TimeSetRepository extends CommonJpaCrudRepository<TimeSet, Long> {
	@Query(
			value = "SELECT * FROM IA_TIME_SET T WHERE T.IS_DELETED='"+FLAG.N_FLAG+"' ORDER BY T.TIME_SET_ID DESC",
			nativeQuery = true
	)
	public List<TimeSet> oderById();
	
	
	@Query(
			value = " SELECT COUNT(1) FROM IA_TIME_SET T WHERE T.STATUS = '1' AND T.IS_DELETED = '"+FLAG.N_FLAG+"' ",
			nativeQuery = true
	)
	public int countStatus();


	@Query(
			value = " SELECT * FROM IA_TIME_SET T WHERE T.STATUS = '1' AND T.IS_DELETED = '"+FLAG.N_FLAG+"' ",
			nativeQuery = true
	)
	public List<TimeSet> findStatusOpen();

}
