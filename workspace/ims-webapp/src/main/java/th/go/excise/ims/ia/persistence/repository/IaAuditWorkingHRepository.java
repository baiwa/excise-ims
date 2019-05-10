
package th.go.excise.ims.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingH;

public interface IaAuditWorkingHRepository extends CommonJpaCrudRepository<IaAuditWorkingH, Long> {

	public IaAuditWorkingH findByAuditWorkingNo(String auditWorkingNo);
}
