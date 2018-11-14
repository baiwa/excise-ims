package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.HealthCareWelFareEntity;

public interface HealthCareWelFareRepository extends CommonJpaCrudRepository<HealthCareWelFareEntity, Long> {

	List<HealthCareWelFareEntity> findByStatusCheckAndIaDisReqIdIsNull(String string);

	List<HealthCareWelFareEntity> findByIaDisReqId(BigDecimal iaDisReqId);
}
