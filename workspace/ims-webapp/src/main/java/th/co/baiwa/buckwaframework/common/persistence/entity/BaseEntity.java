package th.co.baiwa.buckwaframework.common.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Version;

import org.apache.commons.lang3.StringUtils;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.security.util.UserLoginUtils;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	private static final long serialVersionUID = 1390746378834335783L;

	@Column(name = "IS_DELETED")
	protected String isDeleted;

	@Version
	@Column(name = "VERSION")
	protected Integer version;

	@Column(name = "CREATED_BY", updatable = false)
	protected String createdBy;

	@Column(name = "CREATED_DATE", updatable = false)
	protected Date createdDate;

	@Column(name = "UPDATED_BY", nullable = true)
	protected String updatedBy;

	@Column(name = "UPDATED_DATE", nullable = true)
	protected Date updatedDate;

	@PrePersist
	public void prePersist() {
		isDeleted = FLAG.N_FLAG;
		version = 1;
		if (StringUtils.isBlank(createdBy)) {
			createdBy = UserLoginUtils.getCurrentUsername();
		}
		createdDate = new Date();
	}

	@PreUpdate
	public void preUpdate() {
		if (StringUtils.isBlank(updatedBy)) {
			updatedBy = UserLoginUtils.getCurrentUsername();
		}
		version++;
		updatedDate = new Date();
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	

}
