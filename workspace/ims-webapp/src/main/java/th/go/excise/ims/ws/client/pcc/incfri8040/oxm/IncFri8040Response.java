package th.go.excise.ims.ws.client.pcc.incfri8040.oxm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IncFri8040Response {
	@SerializedName("ResponseCode")
	@Expose
	private String responseCode;
	
	@SerializedName("ResponseMessage")
	@Expose
	private String responseMessage;
	
	@SerializedName("ResponseData")
	@Expose
	private ResponseData responseData;

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

	public ResponseData getResponseData() {
		return responseData;
	}

	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}

}
