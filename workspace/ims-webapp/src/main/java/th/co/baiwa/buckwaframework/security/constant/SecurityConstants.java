package th.co.baiwa.buckwaframework.security.constant;

public class SecurityConstants {
	
	public static final class URL {
		public static final String LOGIN_WEB = "/backend/login.htm";
		public static final String LOGIN_WEB_FAILURE = LOGIN_WEB + "?error";
		public static final String LOGIN_WEB_SUCCESS = "/backend/welcome.htm";
		public static final String LOGIN_REST = "/api/security/login";
	}
	
	public static final String USERNAME_PARAM = "username";
	public static final String PASSWORD_PARAM = "password";
	
	// Using in Security Module, for checking this User is authenticate already
	public static final class ROLE {
		public static final String USER = "ROLE_USER";
		public static final String ADMIN = "ROLE_ADMIN";
		
		// Role for Tax Audit
		public static final String TA = "ROLE_TA";
		
		// Role for Internal Audit
		public static final String IA = "ROLE_IA";
		
		// Role for Operator Audit
		public static final String OA = "ROLE_OA";
		
		// Role for Export Audit
		public static final String EA = "ROLE_EA";
	}
	
	public static final class SYSTEM_USER {
		public static final String SYSTEM = "SYSTEM";
		public static final String BATCH = "BATCH";
	}
	
}
