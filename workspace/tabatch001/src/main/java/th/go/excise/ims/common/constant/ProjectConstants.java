package th.go.excise.ims.common.constant;

public class ProjectConstants {
	
	public static final class SCHEDULE_CODE {
		public static final String INCFRI8000 = "INCFRI8000";
		public static final String SUM8000M   = "SUM8000M";
		public static final String REGFRI4000 = "REGFRI4000";
		public static final String VERREG4000 = "VERREG4000";
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
			public static final String DATA_TYPE_MATERIAL = "M";
			public static final String DATA_TYPE_PRODUCT = "P";
			public static final String PS0704_ACC05 = "รับเดือนนี้";
			public static final String PS0704_ACC07 = "ผลิตสินค้าตามพิกัดฯ";
			public static final String PS0704_ACC14 = "รับจากการผลิต";
			public static final String PS0704_ACC18 = "จำหน่ายในประเทศ";
			public static final String PS0704_ACC19 = "จำหน่ายต่างประเทศ";
		}
		
		public class ANAFRI0001 {
			public static final String FORM_CODE_PS0307 = "ภส0307";
			public static final String FORM_CODE_PS0308 = "ภส0308";
		}
	}
	
}
