package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000;

public interface WsIncfri8000Repository extends CommonJpaCrudRepository<WsIncfri8000, Long>, WsIncfri8000RepositoryCustom {
	
	@Modifying
	@Query(
		value = "UPDATE WS_INCFRI8000 SET IS_DELETED = '" + FLAG.Y_FLAG + "' WHERE DATE_TYPE = :dateType",
		nativeQuery = true
	)
	public void queryUpdateIsDeletedY(@Param("dateType") String dateType);
	
}
