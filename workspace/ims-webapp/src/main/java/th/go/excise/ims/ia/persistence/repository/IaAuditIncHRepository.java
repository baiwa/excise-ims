
package th.go.excise.ims.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;

public interface IaAuditIncHRepository extends CommonJpaCrudRepository<IaAuditIncH, Long> , IaAuditIncHRepositoryCustom{

}
