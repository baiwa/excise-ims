package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8000Credit;

public interface WsIncfri8000CreditRepository extends CommonJpaCrudRepository<WsIncfri8000Credit, Long>, WsIncfri8000CreditRepositoryCustom {
	
	@Modifying
	@Query(
		value = "UPDATE WS_INCFRI8000_CREDIT SET IS_DELETED = '" + FLAG.Y_FLAG + "' WHERE DATE_TYPE = :dateType",
		nativeQuery = true
	)
	public void queryUpdateIsDeletedY(@Param("dateType") String dateType);
	
}
