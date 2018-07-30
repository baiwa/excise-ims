package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

public interface MessageRepositoryCustom {

	public List<Message> findByCriteria(Message message, Integer start, Integer length);

}
