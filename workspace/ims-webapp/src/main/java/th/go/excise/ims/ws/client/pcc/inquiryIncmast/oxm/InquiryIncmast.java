
package th.go.excise.ims.ws.client.pcc.inquiryIncmast.oxm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InquiryIncmast {

	@SerializedName("INC_CODE")
	@Expose
	private String incCode;
	@SerializedName("INC_NAME")
	@Expose
	private String incName;
	@SerializedName("INCGRP_FLAG")
	@Expose
	private String incgrpFlag;
	@SerializedName("INCGRP_CODE")
	@Expose
	private String incgrpCode;
	@SerializedName("ACC_CODE")
	@Expose
	private String accCode;
	@SerializedName("LOC_FLAG")
	@Expose
	private String locFlag;
	@SerializedName("CD_FLAG")
	@Expose
	private String cdFlag;
	@SerializedName("INC_FLAG")
	@Expose
	private String incFlag;
	@SerializedName("REC_FLAG")
	@Expose
	private String recFlag;
	@SerializedName("INCCOD_FLAG")
	@Expose
	private String inccodFlag;
	@SerializedName("MONEY_TYPE")
	@Expose
	private String moneyType;
	@SerializedName("BEGIN_DATE")
	@Expose
	private String beginDate;
	@SerializedName("UPD_USERID")
	@Expose
	private String updUserid;
	@SerializedName("UPD_DATE")
	@Expose
	private String updDate;
	@SerializedName("INC_NAME_PRINT")
	@Expose
	private String incNamePrint;

	public String getIncCode() {
		return incCode;
	}

	public void setIncCode(String incCode) {
		this.incCode = incCode;
	}

	public String getIncName() {
		return incName;
	}

	public void setIncName(String incName) {
		this.incName = incName;
	}

	public String getIncgrpFlag() {
		return incgrpFlag;
	}

	public void setIncgrpFlag(String incgrpFlag) {
		this.incgrpFlag = incgrpFlag;
	}

	public String getIncgrpCode() {
		return incgrpCode;
	}

	public void setIncgrpCode(String incgrpCode) {
		this.incgrpCode = incgrpCode;
	}

	public String getAccCode() {
		return accCode;
	}

	public void setAccCode(String accCode) {
		this.accCode = accCode;
	}

	public String getLocFlag() {
		return locFlag;
	}

	public void setLocFlag(String locFlag) {
		this.locFlag = locFlag;
	}

	public String getCdFlag() {
		return cdFlag;
	}

	public void setCdFlag(String cdFlag) {
		this.cdFlag = cdFlag;
	}

	public String getIncFlag() {
		return incFlag;
	}

	public void setIncFlag(String incFlag) {
		this.incFlag = incFlag;
	}

	public String getRecFlag() {
		return recFlag;
	}

	public void setRecFlag(String recFlag) {
		this.recFlag = recFlag;
	}

	public String getInccodFlag() {
		return inccodFlag;
	}

	public void setInccodFlag(String inccodFlag) {
		this.inccodFlag = inccodFlag;
	}

	public String getMoneyType() {
		return moneyType;
	}

	public void setMoneyType(String moneyType) {
		this.moneyType = moneyType;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getUpdUserid() {
		return updUserid;
	}

	public void setUpdUserid(String updUserid) {
		this.updUserid = updUserid;
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

	public String getIncNamePrint() {
		return incNamePrint;
	}

	public void setIncNamePrint(String incNamePrint) {
		this.incNamePrint = incNamePrint;
	}

}
