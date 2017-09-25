package th.co.baiwa.buckwaframework.preferences.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.model.Message;
import th.co.baiwa.buckwaframework.preferences.persistence.dao.MessageDao;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysMessage;

@Service("messageService")
public class MessageService {
	
	private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
	
	@Autowired
	private MessageDao messageDao;
	
	public List<Message> getMessageAll() {
		logger.info("getMessageAll");
		
		List<Message> messageList = new ArrayList<Message>();
		Message message = null;
		List<SysMessage> sysMessageList = messageDao.findAll();
		for (SysMessage sysMessage : sysMessageList) {
			message = new Message();
			message.fromEntity(sysMessage);
			messageList.add(message);
		}
		
		return messageList;
	}
	
	public Message getMessageById(Long messageId) {
		logger.info("getMessage messageId=?", messageId);
		
		SysMessage sysMessage = messageDao.findById(messageId);
		Message message = new Message();
		message.fromEntity(sysMessage);
		
		return message;
	}
	
	public int countMessage() {
		logger.info("countMessage");
		
		return messageDao.count();
	}
	
	public Message insertMessage(Message message) {
		logger.info("insertMessage");
		
		Long messageId = messageDao.insert(message.toEntity());
		message.setMessageId(messageId);
		
		return message;
	}
	
	public void updateMessage(Message message) {
		logger.info("updateMessage");
		
		messageDao.update(message.toEntity());
	}
	
	public void deleteMessage(Long messageId) {
		logger.info("deleteMessage");
		
		messageDao.delete(messageId);
	}
	
}
