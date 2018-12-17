package th.co.baiwa.excise.sys.domain;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Notification extends BaseEntity {
	
	private static final long serialVersionUID = -3535145934761374877L;
	
	
	private BigDecimal id;
	private String type;
	private String subject;
	private String detailMessage;
	private String status;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getDetailMessage() {
		return detailMessage;
	}
	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
}
