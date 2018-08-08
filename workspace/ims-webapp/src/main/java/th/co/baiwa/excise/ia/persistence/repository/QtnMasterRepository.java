package th.co.baiwa.excise.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.QtnMaster;

public interface QtnMasterRepository extends CommonJpaCrudRepository<QtnMaster, Long>, QtnMasterRepositoryCustom {
	
}
