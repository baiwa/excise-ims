package th.co.baiwa.excise.domain;

public class CommonAddress {
	private String homeNumber;
	private String moo;
	private String building;
	private String level;
	private String byWay;
	private String street;
	private String tambol;
	private String district;
	private String province;
	private String zipCode;

	public String getHomeNumber() {
		return homeNumber;
	}

	public void setHomeNumber(String homeNumber) {
		this.homeNumber = homeNumber;
	}

	public String getMoo() {
		return moo;
	}

	public void setMoo(String moo) {
		this.moo = moo;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getByWay() {
		return byWay;
	}

	public void setByWay(String byWay) {
		this.byWay = byWay;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getTambol() {
		return tambol;
	}

	public void setTambol(String tambol) {
		this.tambol = tambol;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	@Override
	public String toString() {
		return "CommonAddress [homeNumber=" + homeNumber + ", moo=" + moo + ", building=" + building + ", level="
				+ level + ", byWay=" + byWay + ", street=" + street + ", tambol=" + tambol + ", district=" + district
				+ ", province=" + province + ", zipCode=" + zipCode + "]";
	}

}
