package th.co.baiwa.excise.epa.persistence.vo;

import java.math.BigDecimal;

public class InvhdrVo {
	
	private String exportName;
	private String exciseSrc;
	private String checkPointDest;
	private String exciseDest;
	private String dateOut;
	private String productName;
	private BigDecimal goodsNum;
	private String transportName;
	private String route;
	private String checkingResult;
	private String remark;
	private String refNo;
	private String invType;

	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public String getExciseSrc() {
		return exciseSrc;
	}

	public void setExciseSrc(String exciseSrc) {
		this.exciseSrc = exciseSrc;
	}

	public String getCheckPointDest() {
		return checkPointDest;
	}

	public void setCheckPointDest(String checkPointDest) {
		this.checkPointDest = checkPointDest;
	}

	public String getExciseDest() {
		return exciseDest;
	}

	public void setExciseDest(String exciseDest) {
		this.exciseDest = exciseDest;
	}

	public String getDateOut() {
		return dateOut;
	}

	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public BigDecimal getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(BigDecimal goodsNum) {
		this.goodsNum = goodsNum;
	}

	public String getTransportName() {
		return transportName;
	}

	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getCheckingResult() {
		return checkingResult;
	}

	public void setCheckingResult(String checkingResult) {
		this.checkingResult = checkingResult;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRefNo() {
		return refNo;
	}

	public void setRefNo(String refNo) {
		this.refNo = refNo;
	}

	public String getInvType() {
		return invType;
	}

	public void setInvType(String invType) {
		this.invType = invType;
	}

}
