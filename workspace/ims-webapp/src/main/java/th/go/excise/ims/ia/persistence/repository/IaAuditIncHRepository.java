
package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;

public interface IaAuditIncHRepository extends CommonJpaCrudRepository<IaAuditIncH, Long>, IaAuditIncHRepositoryCustom {

	public List<IaAuditIncH> findByIsDeletedOrderByAuditIncNoAsc(String isDelete);

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditIncNo = :auditIncNo")
	public IaAuditIncH findByAuditIncNo(@Param("auditIncNo") String auditIncNo);

	public List<IaAuditIncH> findByIsDeletedOrderByAuditIncNoDesc(String isDelete);
}
