
package th.co.baiwa.excise.ws.entity.response.licfri6010;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LicFri6010 {

	@SerializedName("Offcode")
	@Expose
	private String offcode;
	@SerializedName("YearMonthFrom")
	@Expose
	private String yearMonthFrom;
	@SerializedName("YearMonthTo")
	@Expose
	private String yearMonthTo;
	@SerializedName("PageNo")
	@Expose
	private String pageNo;
	@SerializedName("DataPerPage")
	@Expose
	private String dataPerPage;
	@SerializedName("ResponseCode")
	@Expose
	private String responseCode;
	@SerializedName("ResponseMessage")
	@Expose
	private String responseMessage;
	@SerializedName("ResponseData")
	@Expose
	private ResponseData6010 responseData;

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}

	public String getYearMonthFrom() {
		return yearMonthFrom;
	}

	public void setYearMonthFrom(String yearMonthFrom) {
		this.yearMonthFrom = yearMonthFrom;
	}

	public String getYearMonthTo() {
		return yearMonthTo;
	}

	public void setYearMonthTo(String yearMonthTo) {
		this.yearMonthTo = yearMonthTo;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getDataPerPage() {
		return dataPerPage;
	}

	public void setDataPerPage(String dataPerPage) {
		this.dataPerPage = dataPerPage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public ResponseData6010 getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData6010 responseData) {
		this.responseData = responseData;
	}

}
