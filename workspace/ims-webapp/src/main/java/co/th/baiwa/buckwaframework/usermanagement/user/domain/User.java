package co.th.baiwa.buckwaframework.usermanagement.user.domain;

import java.math.BigDecimal;

import co.th.baiwa.buckwaframework.common.bean.BaseVo;

public class User extends BaseVo {
	private BigDecimal userId;
	private String username;
	private String password;
	private String enabled;

	public BigDecimal getUserId() {
		return userId;
	}

	public void setUserId(BigDecimal userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}
}
