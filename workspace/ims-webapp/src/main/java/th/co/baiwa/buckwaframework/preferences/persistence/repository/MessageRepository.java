package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

public interface MessageRepository extends CommonJpaCrudRepository<Message, Long>, MessageRepositoryCustom {

}
