package th.co.baiwa.buckwaframework.accesscontrol.persistence.entity;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Operation extends BaseEntity {

	private Long operationId;
	private String operationCode;
	private String operationDesc;
	private Long parentId;

	public Long getOperationId() {
		return operationId;
	}

	public void setOperationId(Long operationId) {
		this.operationId = operationId;
	}

	public String getOperationCode() {
		return operationCode;
	}

	public void setOperationCode(String operationCode) {
		this.operationCode = operationCode;
	}

	public String getOperationDesc() {
		return operationDesc;
	}

	public void setOperationDesc(String operationDesc) {
		this.operationDesc = operationDesc;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

}
