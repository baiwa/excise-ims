package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseDutyGroup;

public interface ExciseDutyGroupRepository extends CommonJpaCrudRepository<ExciseDutyGroup, Long>, ExciseDutyGroupRepositoryCustom {
	
	@Modifying
	@Query(
		value = "UPDATE EXCISE_DUTY_GROUP SET IS_DELETED = '" + FLAG.Y_FLAG + "'",
		nativeQuery = true
	)
	public void ExciseDutyGroup();
	
	List<ExciseDutyGroup> findAllByDutyGroupStatus(String status);
	
}
