package th.co.baiwa.excise.epa.persistence.vo;

import java.util.ArrayList;
import java.util.List;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Epa012FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 789989652574034396L;
	private String exciseId;
	private String exciseId2;
	private String exciseName;
	private String exciseName2;
	private String dateOut2;
	private String destination2;
	private String exportType;
	private String officeArea;
	private String productName2;
	private String quantity;
	private String taxReNumber2;
	private String vehicleNo;
	private String logisticWay;
	private List<String> stampNo = new ArrayList<>();
	private List<String> stampName = new ArrayList<>();
	private String result;
	private String remark;
	private String searchFlag;

	public String getExciseId() {
		return exciseId;
	}

	public String getExciseId2() {
		return exciseId2;
	}

	public String getExciseName() {
		return exciseName;
	}

	public String getExciseName2() {
		return exciseName2;
	}

	public String getDateOut2() {
		return dateOut2;
	}

	public String getDestination2() {
		return destination2;
	}

	public String getExportType() {
		return exportType;
	}

	public String getOfficeArea() {
		return officeArea;
	}

	public String getProductName2() {
		return productName2;
	}

	public String getQuantity() {
		return quantity;
	}

	public String getTaxReNumber2() {
		return taxReNumber2;
	}

	public String getVehicleNo() {
		return vehicleNo;
	}

	public String getLogisticWay() {
		return logisticWay;
	}

	public List<String> getStampNo() {
		return stampNo;
	}

	public List<String> getStampName() {
		return stampName;
	}

	public String getResult() {
		return result;
	}

	public String getRemark() {
		return remark;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public void setExciseId2(String exciseId2) {
		this.exciseId2 = exciseId2;
	}

	public void setExciseName(String exciseName) {
		this.exciseName = exciseName;
	}

	public void setExciseName2(String exciseName2) {
		this.exciseName2 = exciseName2;
	}

	public void setDateOut2(String dateOut2) {
		this.dateOut2 = dateOut2;
	}

	public void setDestination2(String destination2) {
		this.destination2 = destination2;
	}

	public void setExportType(String exportType) {
		this.exportType = exportType;
	}

	public void setOfficeArea(String officeArea) {
		this.officeArea = officeArea;
	}

	public void setProductName2(String productName2) {
		this.productName2 = productName2;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public void setTaxReNumber2(String taxReNumber2) {
		this.taxReNumber2 = taxReNumber2;
	}

	public void setVehicleNo(String vehicleNo) {
		this.vehicleNo = vehicleNo;
	}

	public void setLogisticWay(String logisticWay) {
		this.logisticWay = logisticWay;
	}

	public void setStampNo(List<String> stampNo) {
		this.stampNo = stampNo;
	}

	public void setStampName(List<String> stampName) {
		this.stampName = stampName;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

}
