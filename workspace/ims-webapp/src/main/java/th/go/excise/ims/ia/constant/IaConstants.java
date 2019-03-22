package th.go.excise.ims.ia.constant;

public class IaConstants {

//	************************* QUESTIONNAIRE *************************
	public class IA_STATUS {
		public static final String PARAM_GROUP_CODE = "IA_STATUS";

		public static final String STATUS_1_CODE = "1";
		public static final String STATUS_1_DESC = "สร้างแบบสอบถาม";

		public static final String STATUS_2_CODE = "2";
		public static final String STATUS_2_DESC = "รอตรวจสอบแบบสอบถาม";

		public static final String STATUS_3_CODE = "3";
		public static final String STATUS_3_DESC = "รอส่งแบบสอบถาม";

		public static final String STATUS_4_CODE = "4";
		public static final String STATUS_4_DESC = "รอผลตอบแบบสอบถาม";

		public static final String STATUS_5_CODE = "5";
		public static final String STATUS_5_DESC = "ตอบแบบสอบถามเรียบร้อย";

		public static final String STATUS_6_CODE = "6";
		public static final String STATUS_6_DESC = "สรุปผลแบบสอบถามแล้ว";

		public static final String STATUS_7_CODE = "7";
		public static final String STATUS_7_DESC = "ยกเลิกส่งแบบสอบถาม";

	}

	public class IA_STATUS_REPLY_QTN {
		public static final String PARAM_GROUP_CODE = "IA_STATUS_REPLY_QTN";

		public static final String STATUS_1_CODE = "1";
		public static final String STATUS_1_DESC = "รอผลตอบแบบสอบถาม";

		public static final String STATUS_2_CODE = "2";
		public static final String STATUS_2_DESC = "กำลังดำเนินการตอบแบบสอบถาม";

		public static final String STATUS_3_CODE = "3";
		public static final String STATUS_3_DESC = "ตอบแบบสอบถามเรียบร้อยและส่งกลับ";

		public static final String STATUS_4_CODE = "4";
		public static final String STATUS_4_DESC = "ยกเลิกแบบสอบถาม";

	}

//	************************* Risk Factors *************************

	public class IA_DATA_EVALUATE {
		public static final String NEW = "NEW";
		public static final String QUESTIONNAIRE = "questionnaire";
		public static final String BUDGET_PROJECT = "budget_project";
		public static final String PROJECT_EFFICIENCY = "project_efficiency";
		public static final String SYSTEM_UNWORKING = "system_unworking";
		public static final String CHECK_PERIOD = "check_period";
		public static final String INCOME_PERFORM = "income_perform";
		public static final String SUPPRESSION = "suppression";
	}

	public class IA_RISK_COLOR {
		public static final String COLOR1 = "เขียวเข้ม";
		public static final String COLOR1_CODE = "#22911ef5";

		public static final String COLOR2 = "เขียว";
		public static final String COLOR2_CODE = "#66d13cf5";

		public static final String COLOR3 = "เหลือง";
		public static final String COLOR3_CODE = "#f5f114";

		public static final String COLOR4 = "ส้ม";
		public static final String COLOR4_CODE = "#f58941";

		public static final String COLOR5 = "แดง";
		public static final String COLOR5_CODE = "#ff231fe3";

	}

	public class IA_STATUS_RISK_FACTORS {

		public static final String PARAM_GROUP_CODE = "IA_STATUS_RISK_FACTORS";

		public static final String STATUS_1_CODE = "1";
		public static final String STATUS_1_DESC = "ยังไม่กำหนดเกณฑ์";

		public static final String STATUS_2_CODE = "2";
		public static final String STATUS_2_DESC = "กำหนดเกณฑ์เรียบร้อย";

		public static final String STATUS_3_CODE = "3";
		public static final String STATUS_3_DESC = "กำหนดเงื่อนไขความเสี่ยงทั้งหมดเรียบร้อย";

	}

//	************************* PLAN HDR *************************
	
	public class PLAN_HDR_STATUS {

		public static final String PARAM_GROUP_CODE = "IA_PLAN_HDR_STATUS";

		public static final String STATUS_1_CODE = "1";
		public static final String STATUS_1_DESC = "อนุมัติ";

		public static final String STATUS_2_CODE = "2";
		public static final String STATUS_2_DESC = "ไม่อนุมัติ";

	}
}
