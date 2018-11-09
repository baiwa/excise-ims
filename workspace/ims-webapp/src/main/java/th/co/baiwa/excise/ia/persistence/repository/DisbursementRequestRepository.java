package th.co.baiwa.excise.ia.persistence.repository;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.DisbursementRequest;

public interface DisbursementRequestRepository extends CommonJpaCrudRepository<DisbursementRequest, BigDecimal> {

}
