package th.go.excise.ims.ws.client.pcc.inquirytitle.oxm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InquiryTitle {
	@SerializedName("TITLE_CODE")
	@Expose
	private String titleCode;

	@SerializedName("TITLE_TYPE")
	@Expose
	private String titleType;

	@SerializedName("TITLE_NAME")
	@Expose
	private String titleName;

	@SerializedName("SHORT_TITLE")
	@Expose
	private String shortTitle;
	
	@SerializedName("TITLE_SEQ")
	@Expose
	private String titleSeq;
	
	@SerializedName("BEGIN_DATE")
	@Expose
	private String beginDate;
	
	@SerializedName("UPD_USERID")
	@Expose
	private String updUserId;
	
	@SerializedName("UPD_DATE")
	@Expose
	private String updDate;

	public String getTitleCode() {
		return titleCode;
	}

	public void setTitleCode(String titleCode) {
		this.titleCode = titleCode;
	}

	public String getTitleType() {
		return titleType;
	}

	public void setTitleType(String titleType) {
		this.titleType = titleType;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getShortTitle() {
		return shortTitle;
	}

	public void setShortTitle(String shortTitle) {
		this.shortTitle = shortTitle;
	}

	public String getTitleSeq() {
		return titleSeq;
	}

	public void setTitleSeq(String titleSeq) {
		this.titleSeq = titleSeq;
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