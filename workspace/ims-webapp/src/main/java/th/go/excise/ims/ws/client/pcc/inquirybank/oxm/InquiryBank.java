package th.go.excise.ims.ws.client.pcc.inquirybank.oxm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InquiryBank {
	@SerializedName("BANK_CODE")
	@Expose
	private String bankCode;

	@SerializedName("BANK_NAME")
	@Expose
	private String bankName;

	@SerializedName("SHORT_NAME")
	@Expose
	private String shortName;

	@SerializedName("BEGIN_DATE")
	@Expose
	private String beginDate;
	
	@SerializedName("UPD_USERID")
	@Expose
	private String updUserId;
	
	@SerializedName("UPD_DATE")
	@Expose
	private String updDate;

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getUpdUserId() {
		return updUserId;
	}

	public void setUpdUserId(String updUserId) {
		this.updUserId = updUserId;
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}
	
}