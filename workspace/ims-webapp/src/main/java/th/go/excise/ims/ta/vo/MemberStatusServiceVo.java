package th.go.excise.ims.ta.vo;

public class MemberStatusServiceVo {
	private String memberCode; //รหัสสมาชิก
	private String surname; //ชื่อ-นามสกุล
	private String startDate; //วันที่เริ่มต้น
	private String expiredDate; //วันหมดอายุ
	private String coupon; //คูปอง
	private String serviceDate; //วันที่ใช้บริการ
	private String membershipStatus; //สถานะการเป็นสมาชิก
	public String getMemberCode() {
		return memberCode;
	}
	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getExpiredDate() {
		return expiredDate;
	}
	public void setExpiredDate(String expiredDate) {
		this.expiredDate = expiredDate;
	}
	public String getCoupon() {
		return coupon;
	}
	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}
	public String getServiceDate() {
		return serviceDate;
	}
	public void setServiceDate(String serviceDate) {
		this.serviceDate = serviceDate;
	}
	public String getMembershipStatus() {
		return membershipStatus;
	}
	public void setMembershipStatus(String membershipStatus) {
		this.membershipStatus = membershipStatus;
	}
}
