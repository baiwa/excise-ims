package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.License;

@Repository
public interface LicenseRepository extends CommonJpaCrudRepository<License, Long>{
	
	@Query("SELECT r FROM License r WHERE r.isDeleted = '"+FLAG.N_FLAG+"' AND r.licNo =  ?1")
	public List<License> findBylicNo(String licNo);
	
}
