package th.go.excise.ims.common.domain;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.BaseVo;

public class ExciseProvince extends BaseVo{

	public static class Field {

		public static final String PROVINCE_ID = "PROVINCE_ID";
		public static final String GEO_ID = "GEO_ID";
		public static final String PROVINCE_CODE = "PROVINCE_CODE";
		public static final String PROVINCE_NAME = "PROVINCE_NAME";
	
	}

	private BigDecimal proviceId;
	private BigDecimal geoId;
	private String proviceCode;
	private String proviceName;
	
	
	public BigDecimal getProviceId() {
		return proviceId;
	}
	public void setProviceId(BigDecimal proviceId) {
		this.proviceId = proviceId;
	}
	public BigDecimal getGeoId() {
		return geoId;
	}
	public void setGeoId(BigDecimal geoId) {
		this.geoId = geoId;
	}
	public String getProviceCode() {
		return proviceCode;
	}
	public void setProviceCode(String proviceCode) {
		this.proviceCode = proviceCode;
	}
	public String getProviceName() {
		return proviceName;
	}
	public void setProviceName(String proviceName) {
		this.proviceName = proviceName;
	}
	
	
	

}
