package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsAnafri0001D;

public interface WsAnafri0001DRepository extends CommonJpaCrudRepository<WsAnafri0001D, Long>, WsAnafri0001DRepositoryCustom {
	
	@Modifying
	@Query(
		value = " DELETE FROM WS_ANAFRI0001_D D " +
				" WHERE EXISTS ( " +
				"   SELECT 1 FROM WS_ANAFRI0001_H H " +
				"   WHERE H.NEW_REG_ID = :newRegId " +
				"     AND H.FORM_CODE = :formCode " +
				"     AND H.REG_IN_NO = D.REG_IN_NO " +
				"     AND (TRUNC(H.REG_IN_DATE) >= TO_DATE(:dateStart,'YYYYMMDD') AND TRUNC(H.REG_IN_DATE) <= TO_DATE(:dateEnd,'YYYYMMDD')) " +
				" ) ",
		nativeQuery = true
	)
	public void forceDeleteByFormCode(@Param("newRegId") String newRegId, @Param("formCode") String formCode, @Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd);
	
	@Query(
		value = " SELECT D.* " +
				" FROM WS_ANAFRI0001_D D " +
				" INNER JOIN WS_ANAFRI0001_H H ON H.NEW_REG_ID = D.NEW_REG_ID " +
				"   AND H.FORM_CODE = D.FORM_CODE " +
				"   AND H.REG_IN_NO = D.REG_IN_NO " +
				"   AND H.IS_DELETED = 'N' " +
				" WHERE D.NEW_REG_ID = :newRegId " +
				"   AND SUBSTR(D.PRODUCT_CODE,0,4) = :dutyGroupId " +
				"   AND (TRUNC(H.REG_IN_DATE) >= TO_DATE(:dateStart,'YYYYMMDD') AND TRUNC(H.REG_IN_DATE) <= TO_DATE(:dateEnd,'YYYYMMDD')) ",
		nativeQuery = true
	)
	public List<WsAnafri0001D> findProductListByBasicAnalysisFormVo(@Param("newRegId") String newRegId, @Param("dutyGroupId") String dutyGroupId, @Param("dateStart") String dateStart, @Param("dateEnd") String dateEnd);
	
}
