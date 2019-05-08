
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD3;

public interface IaAuditIncD3Repository extends CommonJpaCrudRepository<IaAuditIncD3, BigDecimal> , IaAuditIncD3RepositoryCustom {
	public List<IaAuditIncD3> findByAuditIncNoOrderByTaxCode(String auditIncNo);
}
