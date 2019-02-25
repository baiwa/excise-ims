package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Int020301InfoVo {
	String sectorName;
	String areaName;
	String statusText;
	Date sentDate;
	BigDecimal riskQuantity;
	BigDecimal passValue;
	BigDecimal failValue;
	List<Int020301DataVo> sideDtls;
	public String getSectorName() {
		return sectorName;
	}
	public void setSectorName(String sectorName) {
		this.sectorName = sectorName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getStatusText() {
		return statusText;
	}
	public void setStatusText(String statusText) {
		this.statusText = statusText;
	}
	public Date getSentDate() {
		return sentDate;
	}
	public void setSentDate(Date sentDate) {
		this.sentDate = sentDate;
	}
	public BigDecimal getRiskQuantity() {
		return riskQuantity;
	}
	public void setRiskQuantity(BigDecimal riskQuantity) {
		this.riskQuantity = riskQuantity;
	}
	public BigDecimal getPassValue() {
		return passValue;
	}
	public void setPassValue(BigDecimal passValue) {
		this.passValue = passValue;
	}
	public BigDecimal getFailValue() {
		return failValue;
	}
	public void setFailValue(BigDecimal failValue) {
		this.failValue = failValue;
	}
	public List<Int020301DataVo> getSideDtls() {
		return sideDtls;
	}
	public void setSideDtls(List<Int020301DataVo> sideDtls) {
		this.sideDtls = sideDtls;
	}
}
