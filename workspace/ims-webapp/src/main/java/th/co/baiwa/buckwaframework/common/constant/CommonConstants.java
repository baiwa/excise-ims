package th.co.baiwa.buckwaframework.common.constant;

public class CommonConstants {
	
	public static final class FLAG {
		public static final String Y_FLAG = "Y";
		public static final String N_FLAG = "N";
	}
	
	// Spring Profiles
	public static final class PROFILE {
		// Application
		public static final String DEV = "dev";
		public static final String SIT = "sit";
		public static final String UAT = "uat";
		public static final String PROD = "prod";
		// Unit Test
		public static final String UNITTEST = "unittest";
		public static final String UNITTEST_MOCK = "unittest-mock";
	}
	
	public static final class JsonStatus {
		public static final String ERROR = "1";
		public static final String SUCCESS = "0";
	}
	
	public static final class MESSAGE_CODE {
		public static final String MSG_SYSTEM = "MSG_SYSTEM";
		
		public static final String MSG00009 = "MSG_00009";
		public static final String MSG00010 = "MSG_00010";
	}
	
	public static final class COMMON_STATUS {
		public static final String ERROR = "1";
		public static final String SUCCESS = "0";
		public static final String ERROR_DESC = "Internal server error see in log";

	}
	
}
