package th.co.baiwa.excise.ws.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseServiceExcise {
	@SerializedName("ResponseCode")
	@Expose
	private String responseCode;
	@SerializedName("ResponseMessage")
	@Expose
	private String responseMessage;
	@SerializedName("ResponseData")
	@Expose
	private Object responseData;

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

	public Object getResponseData() {
		return responseData;
	}

	public void setResponseData(Object responseData) {
		this.responseData = responseData;
	}

	
}
