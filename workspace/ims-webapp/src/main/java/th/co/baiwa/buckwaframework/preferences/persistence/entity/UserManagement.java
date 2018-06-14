package th.co.baiwa.buckwaframework.preferences.persistence.entity;

public class UserManagement {
	
	private Long userId;
	private String username;
	private String password;
	private String sector;
	private String enabled;
	private String accountNonExpired;
	private String credentialsNonExpired;
	private String accountNonLocked;
	private String isDeleted;
	private String version;
	
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
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
	public String getAccountNonExpired() {
		return accountNonExpired;
	}
	public void setAccountNonExpired(String accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}
	public String getCredentialsNonExpired() {
		return credentialsNonExpired;
	}
	public void setCredentialsNonExpired(String credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}
	public String getAccountNonLocked() {
		return accountNonLocked;
	}
	public void setAccountNonLocked(String accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	public String getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
}
