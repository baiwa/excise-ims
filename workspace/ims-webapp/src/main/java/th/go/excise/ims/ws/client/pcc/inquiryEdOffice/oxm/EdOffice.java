
package th.go.excise.ims.ws.client.pcc.inquiryEdOffice.oxm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EdOffice {

	@SerializedName("OFFCODE")
	@Expose
	private String offcode;

	@SerializedName("OFFNAME")
	@Expose
	private String offname;

	@SerializedName("SHORT_NAME")
	@Expose
	private String shortName;

	@SerializedName("INDC_OFF")
	@Expose
	private String indcOff;

	@SerializedName("TAMBOL_CODE")
	@Expose
	private String tambolCode;

	@SerializedName("SUPOFFCODE")
	@Expose
	private String supoffcode;

	@SerializedName("BEGIN_DATE")
	@Expose
	private String begin_date;

	@SerializedName("UPD_USERID")
	@Expose
	private String updUserid;

	@SerializedName("UPD_DATE")
	@Expose
	private String updDate;

	@SerializedName("TELNO")
	@Expose
	private String telno;

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getOffname() {
		return offname;
	}

	public void setOffname(String offname) {
		this.offname = offname;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getIndcOff() {
		return indcOff;
	}

	public void setIndcOff(String indcOff) {
		this.indcOff = indcOff;
	}

	public String getTambolCode() {
		return tambolCode;
	}

	public void setTambolCode(String tambolCode) {
		this.tambolCode = tambolCode;
	}

	public String getSupoffcode() {
		return supoffcode;
	}

	public void setSupoffcode(String supoffcode) {
		this.supoffcode = supoffcode;
	}

	public String getBegin_date() {
		return begin_date;
	}

	public void setBegin_date(String begin_date) {
		this.begin_date = begin_date;
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

	public String getTelno() {
		return telno;
	}

	public void setTelno(String telno) {
		this.telno = telno;
	}

}
