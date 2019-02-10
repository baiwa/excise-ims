package th.co.baiwa.buckwaframework.accesscontrol.persistence.repository;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;

public interface UserRepository extends CommonJpaCrudRepository<User, Long> {

	public User findByUsername(String username);

}
