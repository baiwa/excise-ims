package th.co.baiwa.excise.oa.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.oa.persistence.entity.OaOperPlan;

public interface OaOperPlanRepository extends CommonJpaCrudRepository<OaOperPlan, Long>  ,OaOperPlanRepositoryCustom{
	
		@Query(
			value = " SELECT * "
				  + " FROM oa_oper_plan WHERE is_deleted = 'N' AND plan_start = ?",
			nativeQuery = true
		)
		public List<OaOperPlan> findByPlanStart(String PlanStart);

}