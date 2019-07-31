package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000Duty;

public interface WsRegfri4000DutyRepository extends CommonJpaCrudRepository<WsRegfri4000Duty, Long>, WsRegfri4000DutyRepositoryCustom {
	
	@Modifying
	@Query(
		value = "TRUNCATE TABLE WS_REGFRI4000_DUTY",
		nativeQuery = true
	)
	public void truncate();
	
}
