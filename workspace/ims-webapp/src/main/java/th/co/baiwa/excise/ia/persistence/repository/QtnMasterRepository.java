package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;

public interface QtnMasterRepository extends CommonJpaCrudRepository<QtnMaster, Long> {
	
	@Query("SELECT r FROM QtnMaster r WHERE r.isDeleted = '" + FLAG.N_FLAG + "' AND r.qtnSector = ?1 AND r.qtnArea = ?2 AND r.qtnYear = ?3")
	public List<QtnMaster> findBySectorAndAreaAndYear(String sector, String area, String year);
	
}
