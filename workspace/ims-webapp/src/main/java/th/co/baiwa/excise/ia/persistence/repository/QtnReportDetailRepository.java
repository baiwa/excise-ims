package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.QtnReportDetail;

public interface QtnReportDetailRepository extends CommonJpaCrudRepository<QtnReportDetail, Long>, QtnReportDetailRepositoryCustom {
	
	@Query("SELECT r FROM QtnReportDetail r WHERE r.isDeleted = '" + FLAG.N_FLAG  + "' AND r.qtnReportManId = ?1")
	public List<QtnReportDetail> findByQtnReportManId(Long qtnReportManId);
	
}
