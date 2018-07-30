package th.co.baiwa.buckwaframework.preferences.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.repository.MessageRepository;

@Service
public class MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	private final MessageRepository messageRepository;
	
	@Autowired
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public List<Message> getMessageList(Message message, Integer start, Integer length) {
		logger.info("getMessageAll");
		
		return messageRepository.findByCriteria(message, start, length);
	}
	
	public Message getMessageById(Long messageId) {
		logger.info("getMessage messageId={}", messageId);
		
		return messageRepository.findOne(messageId);
	}
	
	public int countMessage() {
		logger.info("countMessage");
		
		return (int) messageRepository.count();
	}
	
	public Message insertMessage(Message message) {
		logger.info("insertMessage");
		
		messageRepository.save(message);
		
		return message;
	}
	
	public void updateMessage(Message message) {
		logger.info("updateMessage");
		
		messageRepository.save(message);
	}
	
	public void deleteMessage(List<Long> ids) {
		logger.info("deleteMessage");
		
		messageRepository.delete(ids);
	}
	
}
