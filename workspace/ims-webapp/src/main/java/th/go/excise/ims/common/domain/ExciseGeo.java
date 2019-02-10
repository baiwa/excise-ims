package th.go.excise.ims.common.domain;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.bean.BaseVo;

public class ExciseGeo extends BaseVo{

	public static class Field {

		public static final String GEO_ID = "GEO_ID";
		public static final String GEO_NAME = "GEO_NAME";
	
	}

	
	private BigDecimal geoId;
	private String geoName;
	
	public BigDecimal getGeoId() {
		return geoId;
	}
	public void setGeoId(BigDecimal geoId) {
		this.geoId = geoId;
	}
	public String getGeoName() {
		return geoName;
	}
	public void setGeoName(String geoName) {
		this.geoName = geoName;
	}
	
	

	
	
	

}
