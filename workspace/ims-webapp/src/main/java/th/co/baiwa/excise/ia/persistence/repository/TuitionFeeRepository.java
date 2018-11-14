package th.co.baiwa.excise.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.TuitionFee;

public interface TuitionFeeRepository extends CommonJpaCrudRepository<TuitionFee, BigDecimal>{

	List<TuitionFee> findByStatusCheckAndIaDisReqIdIsNull(String string);

}
