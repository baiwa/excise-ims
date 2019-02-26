package th.co.baiwa.buckwaframework.common.constant;

public class MessageContants {

	public static final class STATUS {
		public static final String SUCCESS = "SUCCESS";
		public static final String FAILED = "FAILED";
	}

	public static final class IA {
		public static final String SEND_QTN_WAIT = "รอส่งแบบสอบถาม";
		public static final String SEND_QTN_SUCCESS = "ส่งแบบสอบถามเรียบร้อย";
		public static final String SEND_QTN_FAIL = "ยกเลิกแบบสอบถาม";
		public static final String QTN_CREATED = "ยังไม่ได้ทำแบบสอบถาม";
		public static final String QTN_WAIT = "รอทำแบบสอบถาม";
		public static final String QTN_FINISH = "ทำแบบสอบถามสำเร็จ";
		public static String qtnStatus(String status) {
			if ("CREATED".equalsIgnoreCase(status)) {
				return QTN_CREATED;
			}
			if ("WAIT".equalsIgnoreCase(status)) {
				return QTN_WAIT;
			}
			if ("FINISH".equalsIgnoreCase(status)) {
				return QTN_FINISH;
			}
			if ("WAIT_HDR".equalsIgnoreCase(status)) {
				return SEND_QTN_WAIT;
			}
			if ("SUCCESS_HDR".equalsIgnoreCase(status)) {
				return SEND_QTN_SUCCESS;
			}
			if ("FAIL_HDR".equalsIgnoreCase(status)) {
				return SEND_QTN_FAIL;
			}
			return QTN_CREATED;
		}
	}
}
