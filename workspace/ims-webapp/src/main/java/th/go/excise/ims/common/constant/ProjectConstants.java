package th.go.excise.ims.common.constant;

public class ProjectConstants {
	
	public class TA_MAS_COND_MAIN_TYPE {
		public static final String TAX = "T";
		public static final String OTHER = "O";
	}
	
	public class EXCISE_OFFICE_CODE {
		public static final String CENTRAL = "000000";
		public static final String TA_CENTRAL = "001400";
		public static final String TA_CENTRAL_SELECTOR = "001401";
		public static final String TA_CENTRAL_OPERATOR1 = "001402";
		public static final String TA_CENTRAL_OPERATOR2 = "001403";
	}
	
	public class EXCISE_SUBDEPT_LEVEL {
		public static final String LV1 = "1";
		public static final String LV2 = "2";
		public static final String LV3 = "3";
	}
	
	public class TAX_COMPARE_TYPE {
		public static final String HALF = "1";
		public static final String MONTH = "2";
	}
	
	public class TA_WORKSHEET_STATUS {
		public static final String DRAFT = "D";
		public static final String CONDITION = "C";
		public static final String SELECTION = "S";
	}
	
	public class TA_PLAN_STATUS {
		public static final String CODE_0100 = "0100";
		public static final String CODE_0200 = "0200";
	}
	
	public class TA_AUDIT_STATUS {
		public static final String CODE_0100 = "0100";
		public static final String CODE_0101 = "0101";
		public static final String CODE_0200 = "0200";
		public static final String CODE_0300 = "0300";
		public static final String CODE_0301 = "0301";
		public static final String CODE_0400 = "0400";
		public static final String CODE_0401 = "0401";
	}
	
	public static class QUARTER {
		public static final String[] Q1 = {"10","11","12"};
		public static final String[] Q2 = {"01","02","03"};
		public static final String[] Q3 = {"04","05","06"};
		public static final String[] Q4 = {"07","08","09"};
	}
	
	public class WEB_SERVICE {
		public static final String PCC_RESPONSE_CODE_OK = "OK";
		
		public class INCFRI8000 {
			public static final String DATE_TYPE_RECEIPT = "Receipt";
			public static final String DATE_TYPE_INCOME = "Income";
			public static final String DATE_TYPE_RECEIPT_CODE = "R";
			public static final String DATE_TYPE_INCOME_CODE = "I";
		}
		
		public class OASFRI0100 {
			public static final String DATA_TYPE_MATERIAL_CODE = "M";
			public static final String DATA_TYPE_PRODUCT_CODE = "P";
		}
		
		public class ANAFRI0001 {
			public static final String FORM_CODE_PS0307 = "ภส0307";
			public static final String FORM_CODE_PS0308 = "ภส0308";
		}
	}
	
	public static class DUTY_GROUP_TYPE {
		public static final String PRODUCT = "1";
		public static final String SERVICE = "2";
		public static final String OTHER = "3";
		public static final String SURA = "4";
	}
	
	public static class TA_RISK_LEVEL {
		public static final String LOWER = "2";
		public static final String LOW = "3";
		public static final String MEDIUM = "4";
		public static final String HIGH = "5";
		public static final String HIGHER = "6";
	}
	
}
