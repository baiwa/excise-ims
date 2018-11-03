package th.co.baiwa.excise.epa.persistence.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Epa013Vo {

	private BigDecimal id;
	private String exportName;
	private String checkPointDest;
	private Date dateOut;
	private Date dateIn;
	private String exciseId;
	private String cusName;
	private String tin;
	private String vatNo;
	private String facname;
	private String cusNewRegid;
	private String remark;
	private String route; 
	private String transportName;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public String getExportName() {
		return exportName;
	}

	public void setExportName(String exportName) {
		this.exportName = exportName;
	}

	public String getCheckPointDest() {
		return checkPointDest;
	}

	public void setCheckPointDest(String checkPointDest) {
		this.checkPointDest = checkPointDest;
	}

	public Date getDateOut() {
		return dateOut;
	}

	public void setDateOut(Date dateOut) {
		this.dateOut = dateOut;
	}

	public Date getDateIn() {
		return dateIn;
	}

	public void setDateIn(Date dateIn) {
		this.dateIn = dateIn;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getCusName() {
		return cusName;
	}

	public void setCusName(String cusName) {
		this.cusName = cusName;
	}

	public String getTin() {
		return tin;
	}

	public void setTin(String tin) {
		this.tin = tin;
	}

	public String getVatNo() {
		return vatNo;
	}

	public void setVatNo(String vatNo) {
		this.vatNo = vatNo;
	}

	public String getFacname() {
		return facname;
	}

	public void setFacname(String facname) {
		this.facname = facname;
	}

	public String getCusNewRegid() {
		return cusNewRegid;
	}

	public void setCusNewRegid(String cusNewRegid) {
		this.cusNewRegid = cusNewRegid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getRoute() {
		return route;
	}

	public void setRoute(String route) {
		this.route = route;
	}

	public String getTransportName() {
		return transportName;
	}

	public void setTransportName(String transportName) {
		this.transportName = transportName;
	}

}
