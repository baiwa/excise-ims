
package th.co.baiwa.excise.ws.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import th.co.baiwa.excise.ws.entity.response.projectbase.RequestHeader;

public class RequestServiceExciseFromSubmit {

    @SerializedName("requestHeader")
    @Expose
    private RequestHeader requestHeader;
    
    
    @SerializedName("requestData")
    @Expose
    private Object requestData;

    public RequestHeader getRequestHeader() {
        return requestHeader;
    }

    public void setRequestHeader(RequestHeader requestHeader) {
        this.requestHeader = requestHeader;
    }

	public Object getRequestData() {
		return requestData;
	}

	public void setRequestData(Object requestData) {
		this.requestData = requestData;
	}

   

}
