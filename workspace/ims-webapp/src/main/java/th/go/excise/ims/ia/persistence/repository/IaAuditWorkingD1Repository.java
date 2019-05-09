
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingD1;

public interface IaAuditWorkingD1Repository
    extends CommonJpaCrudRepository<IaAuditWorkingD1, Long>
{
	public List<IaAuditWorkingD1> findByAuditWorkingNo(String auditWorkingNo);
}
