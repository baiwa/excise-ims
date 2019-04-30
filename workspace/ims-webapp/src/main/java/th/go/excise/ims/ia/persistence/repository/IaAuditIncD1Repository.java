
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;

public interface IaAuditIncD1Repository extends CommonJpaCrudRepository<IaAuditIncD1, Long> ,IaAuditIncD1RepositoryCuston  {

	public List<IaAuditIncD1> findByAuditIncNoOrderByReceiptNo(String auditIncNo);
	
}
