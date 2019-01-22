package co.th.baiwa.buckwaframework.common.bean;

import java.util.Date;

public abstract class BaseVo {

	public static class Field {
		public static final String IS_DELETED = "IS_DELETED";
		public static final String CREATED_BY = "CREATED_BY";
		public static final String CREATED_DATE = "CREATED_DATE";
		public static final String UPDATED_BY = "UPDATED_BY";
		public static final String UPDATED_DATE = "UPDATED_DATE";
	}

	private String createdBy;
	private Date createdDate;
	private String updatedBy;
	private Date updatedDate;
	private String isDeleted;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
}
