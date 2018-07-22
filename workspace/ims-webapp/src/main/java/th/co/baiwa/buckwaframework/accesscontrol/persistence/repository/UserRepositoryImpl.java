package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.common.persistence.repository.AbstractCommonJpaRepository;

public class UserRepositoryImpl extends AbstractCommonJpaRepository<User, Long> implements UserRepositoryCustom {

}
