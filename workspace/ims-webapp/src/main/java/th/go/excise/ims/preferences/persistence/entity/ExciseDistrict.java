package th.go.excise.ims.preferences.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "EXCISE_DISTRICT")
public class ExciseDistrict extends BaseEntity {

	private static final long serialVersionUID = -3842361723874645749L;

	public static class Field {
		public static final String DISTRICT_ID = "DISTRICT_ID";
		public static final String GEO_ID = "GEO_ID";
		public static final String PROVINCE_ID = "PROVINCE_ID";
		public static final String AMPHUR_ID = "AMPHUR_ID";
		public static final String DISTRICT_CODE = "DISTRICT_CODE";
		public static final String DISTRICT_NAME = "DISTRICT_NAME";
	}

	@Id
	@Column(name = "DISTRICT_ID")
	private Long districtId;
	@Column(name = "PROVINCE_ID")
	private Long provinceId;
	@Column(name = "GEO_ID")
	private Long geoId;
	@Column(name = "AMPHUR_ID")
	private Long amphurId;
	@Column(name = "DISTRICT_CODE")
	private String districtCode;
	@Column(name = "DISTRICT_NAME")
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
