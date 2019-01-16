package th.co.baiwa.ims.constant;

public class MessageConstant {
	public final static class SUCCESS {
		public final static String SAVE = "บันทึกข้อมูลสำเร็จ";
		public final static String UPDATE = "แก้ไขข้อมูลสำเร็จ";
		public final static String DELETE = "ลบข้อมูลสำเร็จ";
		public final static String UPLOAD = "อัพโหลดไฟล์สำเร็จ";
	}
	public final static class ERROR {
		public final static String BACKEND = "กรุณาติดต่อผู้ดูแลระบบ";
		public final static String UPLOAD = "ไม่สามารถอัพโหลดไฟล์ได้";
		public final static String DOWNLOAD = "ไม่สามารถดาวน์โหลดไฟล์ได้";
	}
}
