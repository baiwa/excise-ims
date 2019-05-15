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
	}
	
}
