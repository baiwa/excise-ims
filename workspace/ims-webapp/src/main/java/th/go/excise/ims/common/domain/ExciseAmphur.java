package th.go.excise.ims.common.domain;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.BaseVo;

public class ExciseAmphur extends BaseVo {
	public static class Field {
		
		public static final String AMPHUR_ID = "AMPHUR_ID";
		public static final String GEO_ID = "GEO_ID";
		public static final String PROVINCE_ID = "PROVINCE_ID";
		public static final String AMPHUR_CODE = "AMPHUR_CODE";
		public static final String AMPHUR_NAME = "AMPHUR_NAME";
	}
	
	private BigDecimal amphurId;
	private BigDecimal geoId;
	private BigDecimal provinceId;
	private String amphurCode;
	private String amphurName;
	
	
	public BigDecimal getAmphurId() {
		return amphurId;
	}
	public void setAmphurId(BigDecimal amphurId) {
		this.amphurId = amphurId;
	}
	public BigDecimal getGeoId() {
		return geoId;
	}
	public void setGeoId(BigDecimal geoId) {
		this.geoId = geoId;
	}
	public BigDecimal getProvinceId() {
		return provinceId;
	}
	public void setProvinceId(BigDecimal provinceId) {
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
