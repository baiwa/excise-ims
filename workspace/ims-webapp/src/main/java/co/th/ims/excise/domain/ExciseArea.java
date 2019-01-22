package co.th.ims.excise.domain;

import java.math.BigDecimal;

import co.th.baiwa.buckwaframework.common.bean.BaseVo;

public class ExciseArea extends BaseVo{
	public static class Field {
		
		public static final String AREA_ID = "AREA_ID";
		public static final String SECTOR_ID = "SECTOR_ID";
		public static final String OFFICE_CODE = "OFFICE_CODE";
		public static final String AREA_NAME = "AREA_NAME";
		public static final String AREA_SHOT_NAME = "AREA_SHOT_NAME";
		public static final String AREA_SHOT_NAME2 = "AREA_SHOT_NAME2";
	}
	
	private BigDecimal areaId; 
	private BigDecimal sectorId; 
	private String officeCode; 
	private String areaName; 
	private String areaShotName; 
	private String areaShotName2;
	
	
	public BigDecimal getAreaId() {
		return areaId;
	}
	public void setAreaId(BigDecimal areaId) {
		this.areaId = areaId;
	}
	
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaShotName() {
		return areaShotName;
	}
	public void setAreaShotName(String areaShotName) {
		this.areaShotName = areaShotName;
	}
	public String getAreaShotName2() {
		return areaShotName2;
	}
	public void setAreaShotName2(String areaShotName2) {
		this.areaShotName2 = areaShotName2;
	}
	public BigDecimal getSectorId() {
		return sectorId;
	}
	public void setSectorId(BigDecimal sectorId) {
		this.sectorId = sectorId;
	}

}
