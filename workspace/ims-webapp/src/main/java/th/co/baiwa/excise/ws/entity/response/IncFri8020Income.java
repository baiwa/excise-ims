package th.co.baiwa.excise.ws.entity.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IncFri8020Income {
	@SerializedName("DepositDate")
	@Expose
	private String depositDate;
	@SerializedName("SendDate")
	@Expose
	private String sendDate;
	@SerializedName("ReceiptDate")
	@Expose
	private String receiptDate;
	@SerializedName("IncomeName")
	@Expose
	private String incomeName;
	@SerializedName("ReceiptNo")
	@Expose
	private String receiptNo;
	@SerializedName("NettaxAmount")
	@Expose
	private String nettaxAmount;
	@SerializedName("NetLocAmount")
	@Expose
	private String netLocAmount;
	@SerializedName("LocOthAmount")
	@Expose
	private String locOthAmount;
	@SerializedName("LocExpAmount")
	@Expose
	private String locExpAmount;
	@SerializedName("OlderFundAmount")
	@Expose
	private String olderFundAmount;
	@SerializedName("TpbsFundAmount")
	@Expose
	private String tpbsFundAmount;
	@SerializedName("SendAmount")
	@Expose
	private String sendAmount;
	@SerializedName("StampAmount")
	@Expose
	private String stampAmount;
	@SerializedName("CustomAmount")
	@Expose
	private String customAmount;

	public String getDepositDate() {
	return depositDate;
	}

	public void setDepositDate(String depositDate) {
	this.depositDate = depositDate;
	}

	public String getSendDate() {
	return sendDate;
	}

	public void setSendDate(String sendDate) {
	this.sendDate = sendDate;
	}

	public String getReceiptDate() {
	return receiptDate;
	}

	public void setReceiptDate(String receiptDate) {
	this.receiptDate = receiptDate;
	}

	public String getIncomeName() {
	return incomeName;
	}

	public void setIncomeName(String incomeName) {
	this.incomeName = incomeName;
	}

	public String getReceiptNo() {
	return receiptNo;
	}

	public void setReceiptNo(String receiptNo) {
	this.receiptNo = receiptNo;
	}

	public String getNettaxAmount() {
	return nettaxAmount;
	}

	public void setNettaxAmount(String nettaxAmount) {
	this.nettaxAmount = nettaxAmount;
	}

	public String getNetLocAmount() {
	return netLocAmount;
	}

	public void setNetLocAmount(String netLocAmount) {
	this.netLocAmount = netLocAmount;
	}

	public String getLocOthAmount() {
	return locOthAmount;
	}

	public void setLocOthAmount(String locOthAmount) {
	this.locOthAmount = locOthAmount;
	}

	public String getLocExpAmount() {
	return locExpAmount;
	}

	public void setLocExpAmount(String locExpAmount) {
	this.locExpAmount = locExpAmount;
	}

	public String getOlderFundAmount() {
	return olderFundAmount;
	}

	public void setOlderFundAmount(String olderFundAmount) {
	this.olderFundAmount = olderFundAmount;
	}

	public String getTpbsFundAmount() {
	return tpbsFundAmount;
	}

	public void setTpbsFundAmount(String tpbsFundAmount) {
	this.tpbsFundAmount = tpbsFundAmount;
	}

	public String getSendAmount() {
	return sendAmount;
	}

	public void setSendAmount(String sendAmount) {
	this.sendAmount = sendAmount;
	}

	public String getStampAmount() {
	return stampAmount;
	}

	public void setStampAmount(String stampAmount) {
	this.stampAmount = stampAmount;
	}

	public String getCustomAmount() {
	return customAmount;
	}

	public void setCustomAmount(String customAmount) {
	this.customAmount = customAmount;
	}
}
