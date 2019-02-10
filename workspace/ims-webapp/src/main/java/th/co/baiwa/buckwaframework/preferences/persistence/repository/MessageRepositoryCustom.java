package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;

import th.co.baiwa.buckwaframework.preferences.domain.MessageCriteria;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

public interface MessageRepositoryCustom {
	
	public Integer countByCriteria(MessageCriteria criteria);
	
	public List<Message> findByCriteria(MessageCriteria criteria, Pageable pageable);
	
}
