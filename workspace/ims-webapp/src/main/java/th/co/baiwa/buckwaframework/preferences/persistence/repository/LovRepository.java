package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;

public interface LovRepository extends CommonJpaCrudRepository<Lov, Long>, LovRepositoryCustom {

}
