package th.co.baiwa.excise.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.RentHouse;

public interface RentHouseRepository extends CommonJpaCrudRepository<RentHouse, BigDecimal> {
	List<RentHouse>findByStatusAndIaDisReqIdIsNull(String status);
	
	List<RentHouse> findByIaDisReqId(BigDecimal iaDisReqId);

}
