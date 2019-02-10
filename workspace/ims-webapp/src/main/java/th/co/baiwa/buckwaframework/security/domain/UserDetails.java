package th.co.baiwa.buckwaframework.security.domain;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class UserDetails extends User implements UserBean {

	private static final long serialVersionUID = 2637807472705815470L;

	private Long userId;
	// Add More Information about USER here.

	private String[] exciseBaseControl;
	private String userThaiId;
	private String userThaiName;
	private String userThaiSurname;
	private String userEngName;
	private String userEngSurname;
	private String title;
	private String email;
	private String cnName;
	private String telephoneNo;
	private String officeId;
	private String accessAttr;
	private String role;

	

	// Constructor
	public UserDetails(String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}

	// Constructor
	public UserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

	// ==================================================
	// Getter & Setter Method
	// ==================================================
	@Override
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String[] getExciseBaseControl() {
		return exciseBaseControl;
	}

	public void setExciseBaseControl(String[] exciseBaseControl) {
		this.exciseBaseControl = exciseBaseControl;
	}
	@Override
	public String getUserThaiId() {
		return userThaiId;
	}

	public void setUserThaiId(String userThaiId) {
		this.userThaiId = userThaiId;
	}
	@Override
	public String getUserThaiName() {
		return userThaiName;
	}

	public void setUserThaiName(String userThaiName) {
		this.userThaiName = userThaiName;
	}
	@Override
	public String getUserThaiSurname() {
		return userThaiSurname;
	}

	public void setUserThaiSurname(String userThaiSurname) {
		this.userThaiSurname = userThaiSurname;
	}
	@Override
	public String getUserEngName() {
		return userEngName;
	}

	public void setUserEngName(String userEngName) {
		this.userEngName = userEngName;
	}
	@Override
	public String getUserEngSurname() {
		return userEngSurname;
	}

	public void setUserEngSurname(String userEngSurname) {
		this.userEngSurname = userEngSurname;
	}
	@Override
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}
	@Override
	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}
	@Override
	public String getOfficeId() {
		return officeId;
	}

	public void setOfficeId(String officeId) {
		this.officeId = officeId;
	}
	@Override
	public String getAccessAttr() {
		return accessAttr;
	}

	public void setAccessAttr(String accessAttr) {
		this.accessAttr = accessAttr;
	}
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
