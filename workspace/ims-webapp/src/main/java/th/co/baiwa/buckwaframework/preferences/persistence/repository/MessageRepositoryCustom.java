package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

public interface MessageRepositoryCustom {

	public List<Message> findByCriteria(Message message, Pageable pageable);

}
