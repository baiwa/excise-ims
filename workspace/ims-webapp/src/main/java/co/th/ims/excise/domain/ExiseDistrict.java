package co.th.ims.excise.domain;

import java.math.BigDecimal;

import co.th.baiwa.buckwaframework.common.bean.BaseVo;

public class ExiseDistrict extends BaseVo{
	
	public static class Field {
		
		public static final String DISTRICT_ID = "DISTRICT_ID";
		public static final String PROVINCE_ID = "PROVINCE_ID";
		public static final String GEO_ID = "GEO_ID";
		public static final String AMPHUR_ID = "AMPHUR_ID";
		public static final String DISTRICT_CODE = "DISTRICT_CODE";
		public static final String DISTRICT_NAME = "DISTRICT_NAME";
	}
	
	private BigDecimal districtId;
	private BigDecimal provinceId;
	private BigDecimal geoId;	
	private BigDecimal amphurId;
	private String districtCode;
	private String districtName;
	
	
	public BigDecimal getDistrictId() {
		return districtId;
	}
	public void setDistrictId(BigDecimal districtId) {
		this.districtId = districtId;
	}
	public BigDecimal getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(BigDecimal provinceId) {
		this.provinceId = provinceId;
	}
	public BigDecimal getGeoId() {
		return geoId;
	}
	public void setGeoId(BigDecimal geoId) {
		this.geoId = geoId;
	}
	public BigDecimal getAmphurId() {
		return amphurId;
	}
	public void setAmphurId(BigDecimal amphurId) {
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
