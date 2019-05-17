
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditLicdupH;

public interface IaAuditLicdupHRepository extends CommonJpaCrudRepository<IaAuditLicdupH, Long> {
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' order by e.auditLicdupSeq desc")
	public List<IaAuditLicdupH> findIaAuditLicdupHAllDataActive();

}
