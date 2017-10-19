package th.co.baiwa.buckwaframework.preferences.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.dao.MessageDao;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Message;

@Service("messageService")
public class MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	@Autowired
	private MessageDao messageDao;
	
	public List<Message> getMessageList(Integer start, Integer length, Message message) {
		logger.info("getMessageAll");
		
		return messageDao.findAll(start, length, message);
	}
	
	public Message getMessageById(Long messageId) {
		logger.info("getMessage messageId={}", messageId);
		
		return messageDao.findById(messageId);
	}
	
	public int countMessage() {
		logger.info("countMessage");
		
		return messageDao.count();
	}
	
	public Message insertMessage(Message message) {
		logger.info("insertMessage");
		
		Long messageId = messageDao.insert(message);
		message.setMessageId(messageId);
		
		return message;
	}
	
	public void updateMessage(Message message) {
		logger.info("updateMessage");
		
		messageDao.update(message);
	}
	
	public void deleteMessage(List<Long> ids) {
		logger.info("deleteMessage");
		for(Long id : ids){
			messageDao.delete(id);
		}
	}
	
}
