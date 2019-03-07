package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Int020301InfoVo {
	String sectorName;
	String areaName;
	String status;
	String statusText;
	Date sentDate;
	BigDecimal riskQuantity;
	BigDecimal passValue;
	BigDecimal failValue;
	BigDecimal avgRisk;
	String riskText;
	String riskColor;
	List<Int020301DataVo> sideDtls;
	BigDecimal riskNum;

	public BigDecimal getRiskNum() {
		return riskNum;
	}

	public void setRiskNum(BigDecimal riskNum) {
		this.riskNum = riskNum;
	}

	public String getRiskColor() {
		return riskColor;
	}

	public void setRiskColor(String riskColor) {
		this.riskColor = riskColor;
	}

	public BigDecimal getAvgRisk() {
		return avgRisk;
	}

	public void setAvgRisk(BigDecimal avgRisk) {
		this.avgRisk = avgRisk;
	}

	public String getRiskText() {
		return riskText;
	}

	public void setRiskText(String riskText) {
		this.riskText = riskText;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

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
