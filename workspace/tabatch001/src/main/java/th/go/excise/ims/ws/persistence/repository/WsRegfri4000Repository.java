package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsRegfri4000;

public interface WsRegfri4000Repository extends CommonJpaCrudRepository<WsRegfri4000, Long>, WsRegfri4000RepositoryCustom {
	
	@Modifying
	@Query(
		value = "TRUNCATE TABLE WS_REGFRI4000",
		nativeQuery = true
	)
	public void truncate();
	
}
