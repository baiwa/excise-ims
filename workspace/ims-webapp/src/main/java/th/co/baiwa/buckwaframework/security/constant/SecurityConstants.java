package th.co.baiwa.buckwaframework.security.constant;

public class SecurityConstants {
	
	public static final String LOGIN_URL = "/api/security/login";
	
	public static final String USERNAME_PARAM = "username";
	public static final String PASSWORD_PARAM = "password";
	
	// Using in Security Module, for checking this User is authenticate already
	public static final class ROLE {
		public static final String ROLE_USER = "USER";
		public static final String ROLE_ADMIN = "ADMIN";
	}
}
