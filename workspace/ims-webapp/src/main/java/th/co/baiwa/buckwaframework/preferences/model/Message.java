package th.co.baiwa.buckwaframework.preferences.model;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.SysMessage;

public class Message {
	
	private Long messageId;
	private String messageCode;
	private String messageEn;
	private String messageTh;
	private String messageType;

	public Long getMessageId() {
		return messageId;
	}

	public void setMessageId(Long messageId) {
		this.messageId = messageId;
	}

	public String getMessageCode() {
		return messageCode;
	}

	public void setMessageCode(String messageCode) {
		this.messageCode = messageCode;
	}

	public String getMessageEn() {
		return messageEn;
	}

	public void setMessageEn(String messageEn) {
		this.messageEn = messageEn;
	}

	public String getMessageTh() {
		return messageTh;
	}

	public void setMessageTh(String messageTh) {
		this.messageTh = messageTh;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	
	public void fromEntity(SysMessage message) {
		this.messageId = message.getMessageId();
		this.messageCode = message.getMessageCode();
		this.messageEn = message.getMessageEn();
		this.messageTh = message.getMessageTh();
		this.messageType = message.getMessageType();
	}
	
	public SysMessage toEntity() {
		SysMessage message = new SysMessage();
		message.setMessageId(this.messageId);
		message.setMessageCode(this.messageCode);
		message.setMessageEn(this.messageEn);
		message.setMessageTh(this.messageTh);
		message.setMessageType(this.messageType);
		return message;
	}
}
