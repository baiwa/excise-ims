
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicD1;

public interface IaAuditLicD1Repository extends CommonJpaCrudRepository<IaAuditLicD1, Long> {

	public List<IaAuditLicD1> findByAuditLicNoOrderByRunCheck(String auditLicNo);
}
