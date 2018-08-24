package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.Condition;

public interface ConditionRepository extends CommonJpaCrudRepository<Condition, Long>{

	@Query("select r from Condition r where r.isDeleted = '" + FLAG.N_FLAG + "' and r.parentId = ?1 and r.riskType = ?2 and r.page = ?3")
	public List<Condition> findConditionByParentId(Long parentId , String riskType , String page);
}
