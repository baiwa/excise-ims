
package th.co.baiwa.excise.ws.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import th.co.baiwa.excise.ws.entity.response.projectbase.ResponseData;
import th.co.baiwa.excise.ws.entity.response.projectbase.ResponseHeader;

public class ResponseServiceExciseFromSubmit {

    @SerializedName("responseHeader")
    @Expose
    private ResponseHeader responseHeader;
    @SerializedName("responseData")
    @Expose
    private ResponseData responseData;
    
	private String projectTypeCode;
	public ResponseHeader getResponseHeader() {
		return responseHeader;
	}
	public void setResponseHeader(ResponseHeader responseHeader) {
		this.responseHeader = responseHeader;
	}
	public ResponseData getResponseData() {
		return responseData;
	}
	public void setResponseData(ResponseData responseData) {
		this.responseData = responseData;
	}
	
	public String getProjectTypeCode() {
		return projectTypeCode;
	}
	public void setProjectTypeCode(String projectTypeCode) {
		this.projectTypeCode = projectTypeCode;
	}
	
	
	

}
