package th.go.excise.ims.preferences.vo;

import th.co.baiwa.buckwaframework.support.domain.ExciseAmphur;

public class ExciseAmphurVo implements ExciseAmphur {

	private Long amphurId;
	private Long geoId;
	private Long provinceId;
	private String amphurCode;
	private String amphurName;

	public Long getAmphurId() {
		return amphurId;
	}

	public void setAmphurId(Long amphurId) {
		this.amphurId = amphurId;
	}

	public Long getGeoId() {
		return geoId;
	}

	public void setGeoId(Long geoId) {
		this.geoId = geoId;
	}

	public Long getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Long provinceId) {
		this.provinceId = provinceId;
	}

	public String getAmphurCode() {
		return amphurCode;
	}

	public void setAmphurCode(String amphurCode) {
		this.amphurCode = amphurCode;
	}

	public String getAmphurName() {
		return amphurName;
	}

	public void setAmphurName(String amphurName) {
		this.amphurName = amphurName;
	}

}
