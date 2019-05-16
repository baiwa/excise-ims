package th.go.excise.ims.ws.persistence.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsOasfri0100D2;

public interface WsOasfri0100D2Repository extends CommonJpaCrudRepository<WsOasfri0100D2, Long>, WsOasfri0100D2RepositoryCustom {
	
	@Modifying
	@Query(
		value = "DELETE WS_OASFRI0100_D2 WHERE FORMDOC_REC0142_NO = :docNo",
		nativeQuery = true
	)
	public void forceDeleteByDocNo(@Param("docNo") String docNo);
	
}
