package th.co.baiwa.excise.ia.persistence.repository.qtn.rep;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnReportMain;

public interface QtnReportMainRepository extends CommonJpaCrudRepository<QtnReportMain, Long>, QtnReportMainRepositoryCustom {

	@Query("select r from QtnReportMain r where r.isDeleted = '" + FLAG.N_FLAG + "' and r.qtnReportHdrId = ?1")
	public List<QtnReportMain> findByHeaderId(Long headerId);
	
}
