package th.go.excise.ims.preferences.vo;

import th.co.baiwa.buckwaframework.support.domain.ExciseDistrict;

public class ExciseDistrictVo implements ExciseDistrict {

	private Long districtId;
	private Long provinceId;
	private Long geoId;
	private Long amphurId;
	private String districtCode;
	private String districtName;

	public Long getDistrictId() {
		return districtId;
	}

	public void setDistrictId(Long districtId) {
		this.districtId = districtId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public Long getGeoId() {
		return geoId;
	}

	public void setGeoId(Long geoId) {
		this.geoId = geoId;
	}

	public Long getAmphurId() {
		return amphurId;
	}

	public void setAmphurId(Long amphurId) {
		this.amphurId = amphurId;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

}
