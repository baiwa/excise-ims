package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;

public interface QtnMasterRepository extends CommonJpaCrudRepository<QtnMaster, Long>, QtnMasterRepositoryCustom {
	
	@Query("SELECT r FROM QtnMaster r WHERE r.isDeleted = '" + FLAG.N_FLAG + "' AND r.qtnName = ?1 AND r.qtnYear = ?2 AND r.qtnFinished= ?3")
	public List<QtnMaster> findBySectorAndAreaAndYear(String sector, String year, String finished);
	
	@Query("select r from QtnMaster r where r.isDeleted = '" + FLAG.N_FLAG + "' AND r.qtnYear = ?1 AND r.qtnFinished = '" + FLAG.Y_FLAG + "'")
	public List<QtnMaster> findByYear(String year);
	
}
