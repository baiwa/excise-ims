package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPaperPr03H;

public interface TaPaperPr03HRepository extends CommonJpaCrudRepository<TaPaperPr03H, Long> {
	
	@Query("select new java.lang.String(e.paperPrNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.auditPlanCode = :auditPlanCode order by e.paperPrNumber desc")
	public List<String> findPaperPrNumberByAuditPlanCode(@Param("auditPlanCode") String auditPlanCode);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.paperPrNumber = :paperPrNumber")
	public TaPaperPr03H findByPaperPrNumber(@Param("paperPrNumber") String paperPrNumber);
	
}
