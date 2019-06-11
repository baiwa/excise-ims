
package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseOrgGfmis;

public interface ExciseOrgGfmisRepository extends CommonJpaCrudRepository<ExciseOrgGfmis, String> {
	
	@Modifying
	@Query(value = "SELECT GF_DISBURSE_UNIT , GF_EXCISE_NAME , GF_EXCISE_NAME FROM EXCISE_ORG_GFMIS WHERE GF_DISBURSE_UNIT = GF_COST_CENTER AND IS_DELETED = '" + FLAG.N_FLAG + "' ORDER BY GF_EXCISE_CODE", nativeQuery = true)
	public List<ExciseOrgGfmis> findGfDisburseUnitAndName();
	
}
