package th.co.baiwa.buckwaframework.preferences.constant;

public class ParameterConstants {
	
	// All Parameter Group
	public static final class PARAM_GROUP {
		public static final String SYSTEM_CONFIG = "SYSTEM_CONFIG";
		public static final String EXCISE_TAX_TYPE = "EXCISE_TAX_TYPE";
		public static final String EXCISE_PRODUCT_TYPE = "EXCISE_PRODUCT_TYPE";
		public static final String EXCISE_SERVICE_TYPE = "EXCISE_SERVICE_TYPE";
		public static final String EXCISE_FACTORY_TYPE = "EXCISE_FACTORY_TYPE";
		public static final String TA_MAS_COND_MAIN_DESC = "TA_MAS_COND_MAIN_DESC";
		public static final String TA_AUDIT_STATUS = "TA_AUDIT_STATUS";
		public static final String TA_CONFIG = "TA_CONFIG";
		public static final String IA_RISK_COLOR = "IA_RISK_COLOR";
	}
	
	// Parameter Group: SYSTEM_CONFIG
	public static final class SYSTEM_CONFIG {
		public static final String LOGIN_ATTEMPTS = "LOGIN_ATTEMPTS";
	}
	
	// Parameter Group: EXCISE_FACTORY_TYPE
	public static final class EXCISE_FACTORY_TYPE {
		public static final String PRODUCT = "1";
		public static final String SERVICE = "2";
		public static final String IMPORT = "3";
	}
	
	public class TA_CONFIG {
		public static final String SEE_FLAG = "SEE_FLAG";
		public static final String SELECT_FLAG = "SELECT_FLAG";
		public static final String SEND_ALL_FLAG = "SEND_ALL_FLAG";
	}
	
}
