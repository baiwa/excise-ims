package th.co.baiwa.excise.constant;

public class IaConstant {
    public static class REPORT {
        public static final String TABLE_INT09213 = "TABLE INT09213";
    }

    public static class VALUE {
        public static final String DAY ="วัน";
        public static final String HOUR ="ชั่วโมง";
        public static final String HOME_OFFICE_TRAVEL="บ้าน - สำนักงาน";
        public static final String OFFICE_HOME_TRAVEL="สำนักงาน - บ้าน";
        public static final String OFFICE_OFFICE_TRAVEL="สำนักงาน - สำนักงาน";

        public static class PREFIX {
            public static final String MR = "นาย";
            public static final String MS = "นางสาว";
            public static final String MRS = "นาง";
        }

        public static class TYPE {
            public static final String NORMAL_TYPE = "1";
            public static final String NORMAL_TYPE_DESC = "กรณีปกติ";
            public static final String PACKAGE_TYPE = "2";
            public static final String PACKAGE_TYPE_DESC = "กรณีเหมาจ่าย";

            public static final String TRAINING_TYPE = "3";
            public static final String TRAINING_TYPE_DESC = "กรณีฝึกอบรม";
        }


        public static class TYPEWITHDRAWAL {
            public static final String NORMAL = "1";
            public static final String NORMAL_DESC = "ปกติ";


            public static final String PAY_ALL = "2";
            public static final String PAY_ALL_DESC = "เหมาจ่าย";

            public static final String TRAINING_A = "3";
            public static final String TRAINING_A_DESC = "อบรมประเภท ก";

            public static final String TRAINING_B = "4";
            public static final String TRAINING_B_DESC = "อบรมประเภท ข";
        }
        
        public static class TYPE_ROOM {
            public static final String ONE = "1";
            public static final String ONE_DESC = "ห้องเดี่ยว";

            public static final String TWO = "2";
            public static final String TWO_DESC = "ห้องคู่";
        }
        
        public static class IAPROOF_OF_PAYMENT_DOCUMENT_TYPE {
            
            public static final String ONE_DESC   = "หลักฐานการจ่ายเงินค่าใช้จ่ายในการเดินทางไปราชการ";
            public static final String ONE_CODE   = "1";

            public static final String TWO_DESC   = "ใบเบิกค่าใช้จ่ายในการเดินทางไปราชการ";
            public static final String TWO_CODE   = "2";
            
            public static final String THREE_DESC = "เอกสารหลักฐานแนบ";
            public static final String THREE_CODE = "3";
        }
    }
    
    public static class IA_REGIS_TRACK_CONTROL {
    	
    	public static class STATUS {
        	public static final String TRACKING_FIRST_CODE = "1";
        	public static final String TRACKING_FIRST_DESC = "แจ้งติดตามครั่งที่ 1";
        	
        	public static final String RESULT_OPT_FIRST_CODE = "2";
        	public static final String RESULT_OPT_FIRST_DESC = "แจ้งผลการดำเนินงานครั้งที่ 1";
        	
        	public static final String REPORT_TRACKING_FIRST_CODE = "3";
        	public static final String REPORT_TRACKING_FIRST_DESC = "รายงานการติดตามครั้งที่ 1";
        	
        	public static final String TRACKING_SECOND_CODE = "4";
        	public static final String TRACKING_SECOND_DESC = "แจ้งติดตามครั้งที่ 2";
        	
        	public static final String RESULT_OPT_SECOND_CODE = "5";
        	public static final String RESULT_OPT_SECOND_DESC = "แจ้งผลการดำเนินงานครั้งที่ 2";
        	
        	public static final String REPORT_TRACKING_SECOND_CODE = "6";
        	public static final String REPORT_TRACKING_SECOND_DESC = "รายงานการติดตามครั้งที่ 2";
        	
        	public static final String COMPLETE_CODE = "7";
        	public static final String COMPLETE_DESC = "เสร็จสิ้น";
    	}
    }
}

