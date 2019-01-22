package co.th.ims.excise.domain;

import java.math.BigDecimal;

import co.th.baiwa.buckwaframework.common.bean.BaseVo;

public class ExciseSector extends BaseVo{
	
	public static class Field {
		public static final String SECTOR_ID = "SECTOR_ID";
		public static final String OFFICE_CODE = "OFFICE_CODE";
		public static final String SECTOR_NAME = "SECTOR_NAME";
		public static final String SECTOR_SHOT_NAME = "SECTOR_SHOT_NAME";
		public static final String SECTOR_NAME2 = "SECTOR_NAME2";
	}
	
	private BigDecimal sectorId; 
	private String officeCode; 
	private String sectorName; 
	private String sectorShotName; 
	private String sectorName2;
	
	public BigDecimal getSectorId() {
		return sectorId;
	}
	public void setSectorId(BigDecimal sectorId) {
		this.sectorId = sectorId;
	}
	public String getOfficeCode() {
		return officeCode;
	}
	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getSectorShotName() {
		return sectorShotName;
	}
	public void setSectorShotName(String sectorShotName) {
		this.sectorShotName = sectorShotName;
	}
	public String getSectorName2() {
		return sectorName2;
	}
	public void setSectorName2(String sectorName2) {
		this.sectorName2 = sectorName2;
	} 
	
}
