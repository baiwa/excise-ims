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
		public static final String MOCK = "mock";
		// Unit Test
		public static final String UNITTEST = "unittest";
		public static final String UNITTEST_MOCK = "unittest-mock";
		// Not Mock and Unit Test
		public static final String NOT_MOCK = "!" + MOCK;
		public static final String NOT_UNITTEST = "!" + UNITTEST;
	}
	
}
