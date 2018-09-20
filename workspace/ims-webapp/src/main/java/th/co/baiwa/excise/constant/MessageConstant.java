package th.co.baiwa.excise.constant;

import org.springframework.stereotype.Service;

@Service
public class MessageConstant {
    public static class MSG {
        public static class STATUS {
            public static class SAVE {
                public static final String SUCCESS = "บันทึกเรียบร้อยแล้ว";
                public static final String SUCCESS_CODE = "MSG_00002";

                public static final String FAIL = "บันทึกล้มเหลว";
                public static final String CODE = "MSG_00003";
            }
            public static class DELETE {
                public static final String SUCCESS = "ลบเรียบร้อยแล้ว";
                public static final String SUCCESS_CODE = "MSG_00005";

                public static final String FAIL = "ลบไม่สำเร็จ";
                public static final String CODE = "MSG_00006";
            }
        }
    }
}
