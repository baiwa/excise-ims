package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;
import java.util.Date;

public class Int0611Vo {
	
	String refNum;//เลขที่เอกสารอ้างอิง
	Date withdrawalDate; //วันที่ขอเบิก
	String budgetType	;//ประเภทเงิน
	String activities	;//กิจกรรม
	String budgetName	;//ชื่องบ
	String categoryName;//ชื่อหมวด
	String listName	;//ชื่อรายการ
	String itemDesc	;//คำอธิบาย
	BigDecimal withdrawalAmount;//จำนวนเงินขอเบิก
	BigDecimal withholdingTax;//ภาษีหัก ณ ที่จ่าย
	BigDecimal socialSecurity;//หักประกันสังคม
	BigDecimal anotherAmount;//อื่น ๆ
	String receivedAmount;//จำนวนเงินขอรับ
	String withdrawalDocNum;//เลขที่เอกสารขอเบิก
	String paymentDocNum;//เลขที่เอกสารขอจ่าย
	String note;//หมายเหตุ
	
	
	public String getRefNum() {
		return refNum;
	}
	public void setRefNum(String refNum) {
		this.refNum = refNum;
	}
	public Date getWithdrawalDate() {
		return withdrawalDate;
	}
	public void setWithdrawalDate(Date withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}
	public String getBudgetType() {
		return budgetType;
	}
	public void setBudgetType(String budgetType) {
		this.budgetType = budgetType;
	}
	public String getActivities() {
		return activities;
	}
	public void setActivities(String activities) {
		this.activities = activities;
	}
	public String getBudgetName() {
		return budgetName;
	}
	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getListName() {
		return listName;
	}
	public void setListName(String listName) {
		this.listName = listName;
	}
	public String getItemDesc() {
		return itemDesc;
	}
	public void setItemDesc(String itemDesc) {
		this.itemDesc = itemDesc;
	}
	public BigDecimal getWithdrawalAmount() {
		return withdrawalAmount;
	}
	public void setWithdrawalAmount(BigDecimal withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}
	public BigDecimal getWithholdingTax() {
		return withholdingTax;
	}
	public void setWithholdingTax(BigDecimal withholdingTax) {
		this.withholdingTax = withholdingTax;
	}
	public BigDecimal getSocialSecurity() {
		return socialSecurity;
	}
	public void setSocialSecurity(BigDecimal socialSecurity) {
		this.socialSecurity = socialSecurity;
	}
	public BigDecimal getAnotherAmount() {
		return anotherAmount;
	}
	public void setAnotherAmount(BigDecimal anotherAmount) {
		this.anotherAmount = anotherAmount;
	}
	public String getReceivedAmount() {
		return receivedAmount;
	}
	public void setReceivedAmount(String receivedAmount) {
		this.receivedAmount = receivedAmount;
	}
	public String getWithdrawalDocNum() {
		return withdrawalDocNum;
	}
	public void setWithdrawalDocNum(String withdrawalDocNum) {
		this.withdrawalDocNum = withdrawalDocNum;
	}
	public String getPaymentDocNum() {
		return paymentDocNum;
	}
	public void setPaymentDocNum(String paymentDocNum) {
		this.paymentDocNum = paymentDocNum;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	
	
}
