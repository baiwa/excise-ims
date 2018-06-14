package th.go.excise.ims.mockup.persistence.entity;

import org.springframework.stereotype.Repository;

import th.go.excise.ims.mockup.persistence.entity.ta.ExciseFileUpload;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ExciseDetail {

    private Integer exciseRegisttionNumberId;
    private String exciseId;
    private String exciseOperatorName;
    private String exciseIdenNumber;
    private String exciseFacName;
    private String exciseFacAddress;
    private String exciseArea;
    private Integer exciseRegisCapital;
    private String exciseRemark;
    private String createdBy;
    private LocalDateTime createdDatetime;
    private String updateBy;
    private LocalDateTime updateDatetime;
    private String taexciseProductType;
    private String taexciseSectorArea;
    private String 	taxpayment1;
    private String  taxpayment2;
    private String  change ;
    private String payingtax;
    private String 	no1;
    private String  no2;
    private String  no3 ;
    private String paymentMonth;
    private String  sector;
    private String 	coordinates;
    private String  industrialAddress;
    private String  registeredCapital;
    private String  status;

    private ArrayList<ExciseTax> exciseTax;
    
    private List<ExciseFileUpload> file;

	public Integer getExciseRegisttionNumberId() {
		return exciseRegisttionNumberId;
	}

	public void setExciseRegisttionNumberId(Integer exciseRegisttionNumberId) {
		this.exciseRegisttionNumberId = exciseRegisttionNumberId;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getExciseOperatorName() {
		return exciseOperatorName;
	}

	public void setExciseOperatorName(String exciseOperatorName) {
		this.exciseOperatorName = exciseOperatorName;
	}

	public String getExciseIdenNumber() {
		return exciseIdenNumber;
	}

	public void setExciseIdenNumber(String exciseIdenNumber) {
		this.exciseIdenNumber = exciseIdenNumber;
	}

	public String getExciseFacName() {
		return exciseFacName;
	}

	public void setExciseFacName(String exciseFacName) {
		this.exciseFacName = exciseFacName;
	}

	public String getExciseFacAddress() {
		return exciseFacAddress;
	}

	public void setExciseFacAddress(String exciseFacAddress) {
		this.exciseFacAddress = exciseFacAddress;
	}

	public String getExciseArea() {
		return exciseArea;
	}

	public void setExciseArea(String exciseArea) {
		this.exciseArea = exciseArea;
	}

	public Integer getExciseRegisCapital() {
		return exciseRegisCapital;
	}

	public void setExciseRegisCapital(Integer exciseRegisCapital) {
		this.exciseRegisCapital = exciseRegisCapital;
	}

	public String getExciseRemark() {
		return exciseRemark;
	}

	public void setExciseRemark(String exciseRemark) {
		this.exciseRemark = exciseRemark;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public LocalDateTime getCreatedDatetime() {
		return createdDatetime;
	}

	public void setCreatedDatetime(LocalDateTime createdDatetime) {
		this.createdDatetime = createdDatetime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public LocalDateTime getUpdateDatetime() {
		return updateDatetime;
	}

	public void setUpdateDatetime(LocalDateTime updateDatetime) {
		this.updateDatetime = updateDatetime;
	}

	public String getTaexciseProductType() {
		return taexciseProductType;
	}

	public void setTaexciseProductType(String taexciseProductType) {
		this.taexciseProductType = taexciseProductType;
	}

	public String getTaexciseSectorArea() {
		return taexciseSectorArea;
	}

	public void setTaexciseSectorArea(String taexciseSectorArea) {
		this.taexciseSectorArea = taexciseSectorArea;
	}

	public String getTaxpayment1() {
		return taxpayment1;
	}

	public void setTaxpayment1(String taxpayment1) {
		this.taxpayment1 = taxpayment1;
	}

	public String getTaxpayment2() {
		return taxpayment2;
	}

	public void setTaxpayment2(String taxpayment2) {
		this.taxpayment2 = taxpayment2;
	}

	public String getChange() {
		return change;
	}

	public void setChange(String change) {
		this.change = change;
	}

	public String getPayingtax() {
		return payingtax;
	}

	public void setPayingtax(String payingtax) {
		this.payingtax = payingtax;
	}

	public String getNo1() {
		return no1;
	}

	public void setNo1(String no1) {
		this.no1 = no1;
	}

	public String getNo2() {
		return no2;
	}

	public void setNo2(String no2) {
		this.no2 = no2;
	}

	public String getNo3() {
		return no3;
	}

	public void setNo3(String no3) {
		this.no3 = no3;
	}

	public String getPaymentMonth() {
		return paymentMonth;
	}

	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getIndustrialAddress() {
		return industrialAddress;
	}

	public void setIndustrialAddress(String industrialAddress) {
		this.industrialAddress = industrialAddress;
	}

	public String getRegisteredCapital() {
		return registeredCapital;
	}

	public void setRegisteredCapital(String registeredCapital) {
		this.registeredCapital = registeredCapital;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ArrayList<ExciseTax> getExciseTax() {
		return exciseTax;
	}

	public void setExciseTax(ArrayList<ExciseTax> exciseTax) {
		this.exciseTax = exciseTax;
	}

	public List<ExciseFileUpload> getFile() {
		return file;
	}

	public void setFile(List<ExciseFileUpload> file) {
		this.file = file;
	}

}