package th.co.baiwa.excise.sys.domain;

import java.util.Date;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Notification extends BaseEntity {

	private static final long serialVersionUID = -3535145934761374877L;

	private Long id;
	private String type;
	private String subject;
	private String detailMessage;
	private String status;
	private Date viewDate;
	private String referenceId;


	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	// no field in table
	private String viewStatus;

	public Date getViewDate() {
		return viewDate;
	}

	public void setViewDate(Date viewDate) {
		this.viewDate = viewDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public String getViewStatus() {
		return viewStatus;
	}

	public void setViewStatus(String viewStatus) {
		this.viewStatus = viewStatus;
	}
}
