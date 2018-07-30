package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;

public interface ParameterInfoRepository extends CommonJpaCrudRepository<ParameterInfo, Long> {

	public List<ParameterInfo> findByParamGroupId(Long paramGroupId);

}
