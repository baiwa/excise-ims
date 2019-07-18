
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicH;

public interface IaAuditLicHRepository extends CommonJpaCrudRepository<IaAuditLicH, Long>, IaAuditLicHRepositoryCustom {

	public IaAuditLicH findByAuditLicNoAndIsDeletedOrderByAuditLicSeqDesc(String AuditLicNo, String isDelete);

	@Query(value = "SELECT * FROM IA_AUDIT_LIC_H H WHERE H.IS_DELETED = '" + FLAG.N_FLAG + "' ORDER BY AUDIT_LIC_SEQ  DESC ", nativeQuery = true)
	public List<IaAuditLicH> findIaAuditLicHAllDataActive();

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditLicNo = :auditLicNo")
	public IaAuditLicH findByAuditLicNo(@Param("auditLicNo") String auditLicNo);
}
