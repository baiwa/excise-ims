package th.co.baiwa.buckwaframework.accesscontrol.persistence.entity;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class Role extends BaseEntity {

	private Long roleId;
	private String roleCode;
	private String roleDesc;

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getRoleDesc() {
		return roleDesc;
	}

	public void setRoleDesc(String roleDesc) {
		this.roleDesc = roleDesc;
	}

}
