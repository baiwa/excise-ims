package th.go.excise.ims.ws.client.pcc.inquiryEdOffice.oxm;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InquiryEdOfficeResponse {
	@SerializedName("ResponseCode")
	@Expose
	private String responseCode;

	@SerializedName("ResponseMessage")
	@Expose
	private String responseMessage;

	@SerializedName("ResponseData")
	@Expose
	private List<EdOffice> responseData;

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

	public List<EdOffice> getResponseData() {
		return responseData;
	}

	public void setResponseData(List<EdOffice> responseData) {
		this.responseData = responseData;
	}

}
