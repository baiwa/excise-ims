
package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepartment;

public interface ExciseDepartmentRepository extends CommonJpaCrudRepository<ExciseDepartment, Long> {
	
	@Query(" SELECT e FROM ExciseDepartment e WHERE e.isDeleted = '"+FLAG.N_FLAG+"' AND e.officeCode LIKE ?1 ")
//	@Query("select e from ExciseAuthen e where e.isDeleted = '" + FLAG.N_FLAG + "' and groupAuthenName = ?1")
	public List<ExciseDepartment> findExciseDepartmentListByOffice(String officeCode);
}
