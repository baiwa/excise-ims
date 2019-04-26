package th.go.excise.ims.ws.client.pcc.inquiryHospital.oxm;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InquiryHospitalResponse {
	@SerializedName("ResponseCode")
	@Expose
	private String responseCode;

	@SerializedName("ResponseMessage")
	@Expose
	private String responseMessage;

	@SerializedName("ResponseData")
	@Expose
	private List<InquiryHospital> responseData;

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

	public List<InquiryHospital> getResponseData() {
		return responseData;
	}

	public void setResponseData(List<InquiryHospital> responseData) {
		this.responseData = responseData;
	}

}
