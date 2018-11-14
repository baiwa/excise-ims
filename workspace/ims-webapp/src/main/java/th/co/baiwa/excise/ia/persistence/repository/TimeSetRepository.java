package th.co.baiwa.excise.ia.persistence.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.TimeSet;

public interface TimeSetRepository extends CommonJpaCrudRepository<TimeSet, Long> {
	@Query(value = "SELECT TIME_SET_ID , START_DATE_TIME ,  END_DATE_TIME ,(case when START_DATE_TIME < sysdate and END_DATE_TIME > sysdate then 1 else 0 end) status ,CREATED_BY , CREATED_DATE ,  UPDATED_BY ,  UPDATED_DATE ,  IS_DELETED ,VERSION FROM ia_time_set where IS_DELETED = '"
			+ FLAG.N_FLAG + "' ORDER BY TIME_SET_ID desc", nativeQuery = true)
	public List<TimeSet> oderById();

	@Query(value = " SELECT COUNT(1) FROM IA_TIME_SET T WHERE T.STATUS = '1' AND T.IS_DELETED = '" + FLAG.N_FLAG
			+ "' ", nativeQuery = true)
	public int countStatus();

	@Query(value = "SELECT COUNT(1) FROM IA_TIME_SET t WHERE  t.IS_DELETED = '" + FLAG.N_FLAG
			+ "' AND ( (t.START_DATE_TIME <= ?1 AND t.END_DATE_TIME >= ?1) OR (t.START_DATE_TIME <= ?2 AND t.END_DATE_TIME >= ?2)OR ( t.START_DATE_TIME BETWEEN ?1 and ?2 or t.END_DATE_TIME BETWEEN ?1 and ?2 )  )", nativeQuery = true)
	public int countOverlap(Date startDate, Date endDate);

	@Query(value = " SELECT * FROM IA_TIME_SET t WHERE t.IS_DELETED = '" + FLAG.N_FLAG + "' AND ( (t.START_DATE_TIME <= sysdate AND t.END_DATE_TIME => sysdate))",
			nativeQuery = true)
	public List<TimeSet> findStatusOpen();

	@Query(value = "SELECT COUNT(1) FROM IA_TIME_SET t WHERE  t.IS_DELETED = '" + FLAG.N_FLAG
			+ "' AND TIME_SET_ID <> ?3 AND ( (t.START_DATE_TIME <= ?1 AND t.END_DATE_TIME >= ?1) OR (t.START_DATE_TIME <= ?2 AND t.END_DATE_TIME >= ?2)OR ( t.START_DATE_TIME BETWEEN ?1 and ?2 or t.END_DATE_TIME BETWEEN ?1 and ?2 )  )", nativeQuery = true)
	public int countOverlapNotCurrentID(Date start, Date end, long timeSetId);  

}
