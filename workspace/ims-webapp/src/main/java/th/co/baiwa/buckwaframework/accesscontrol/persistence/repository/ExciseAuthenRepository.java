package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.ExciseAuthen;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;

public interface ExciseAuthenRepository extends CommonJpaCrudRepository<ExciseAuthen, Long> {
	
	@Query("select e from ExciseAuthen e where e.isDeleted = '" + FLAG.N_FLAG + "' and groupAuthenName = ?1")
	public List<ExciseAuthen> findByGroupAuthenName(String groupAuthenName);
	
	
}
