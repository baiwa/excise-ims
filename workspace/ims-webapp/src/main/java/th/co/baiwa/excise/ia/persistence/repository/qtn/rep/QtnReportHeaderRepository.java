package th.co.baiwa.excise.ia.persistence.repository.qtn.rep;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.qtn.QtnReportHeader;

public interface QtnReportHeaderRepository extends CommonJpaCrudRepository<QtnReportHeader, Long>, QtnReportHeaderRepositoryCustom {

	@Query("select r from QtnReportHeader r where r.isDeleted = '" + FLAG.N_FLAG + "' and r.qtnMasterId = ?1")
	public List<QtnReportHeader> findbyMasterId(Long masterId);
	
}
