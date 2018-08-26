package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportDetail;

public interface QtnReportDetailRepository extends CommonJpaCrudRepository<QtnReportDetail, Long> {
	
	@Query(
		value = "SELECT E.* FROM IA_QTN_REPORT_DETAIL E WHERE E.IS_DELETED ='N' AND E.QTN_REPORT_MAN_ID = ?",
		nativeQuery = true
	)
	public List<QtnReportDetail> findByQtnReportManId(Long qtnReportManId);
	
}
