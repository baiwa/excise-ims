package th.co.baiwa.excise.constant;

public class IaConstant {
    public static class REPORT {
        public static final String TABLE_INT09213 = "TABLE INT09213";
    }

    public static class VALUE {

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


    }
}

