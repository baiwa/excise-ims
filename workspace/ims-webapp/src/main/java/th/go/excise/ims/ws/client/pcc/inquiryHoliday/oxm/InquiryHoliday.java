
package th.go.excise.ims.ws.client.pcc.inquiryHoliday.oxm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InquiryHoliday {

	@SerializedName("HOSP_CODE")
	@Expose
	private String hospCode;
	@SerializedName("HOSP_TYPE")
	@Expose
	private String hospType;
	@SerializedName("HOSP_CATE")
	@Expose
	private String hospCate;
	@SerializedName("HOSP_NAME")
	@Expose
	private String hospName;
	@SerializedName("ADDRNO")
	@Expose
	private String addrno;
	@SerializedName("THNNAME")
	@Expose
	private String thnname;
	@SerializedName("TAMBOL_CODE")
	@Expose
	private String tambolCode;
	@SerializedName("ZIPCODE")
	@Expose
	private String ZIPCODE;
	@SerializedName("BEGIN_DATE")
	@Expose
	private String beginDate;
	@SerializedName("UPD_USERID")
	@Expose
	private String updUserid;
	@SerializedName("UPD_DATE")
	@Expose
	private String updDate;

	public String getHospCode() {
		return hospCode;
	}

	public void setHospCode(String hospCode) {
		this.hospCode = hospCode;
	}

	public String getHospType() {
		return hospType;
	}

	public void setHospType(String hospType) {
		this.hospType = hospType;
	}

	public String getHospCate() {
		return hospCate;
	}

	public void setHospCate(String hospCate) {
		this.hospCate = hospCate;
	}

	public String getHospName() {
		return hospName;
	}

	public void setHospName(String hospName) {
		this.hospName = hospName;
	}

	public String getAddrno() {
		return addrno;
	}

	public void setAddrno(String addrno) {
		this.addrno = addrno;
	}

	public String getThnname() {
		return thnname;
	}

	public void setThnname(String thnname) {
		this.thnname = thnname;
	}

	public String getTambolCode() {
		return tambolCode;
	}

	public void setTambolCode(String tambolCode) {
		this.tambolCode = tambolCode;
	}

	public String getZIPCODE() {
		return ZIPCODE;
	}

	public void setZIPCODE(String zIPCODE) {
		ZIPCODE = zIPCODE;
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

}
