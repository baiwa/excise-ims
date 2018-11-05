package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAudRpt;

public interface IncomeExciseAudRptRepository extends CommonJpaCrudRepository<IncomeExciseAudRpt,Long> {
	
	@Query("select e from IncomeExciseAudRpt e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.assignTo = ?1 and e.status = ?2 order by e.officeCode")
	public List<IncomeExciseAudRpt> findbyAssignToAndStatus(String assignTo , String status);
}