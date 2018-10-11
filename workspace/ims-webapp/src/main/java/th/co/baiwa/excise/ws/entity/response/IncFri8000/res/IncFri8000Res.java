
package th.co.baiwa.excise.ws.entity.response.IncFri8000.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IncFri8000Res {

    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("ResponseData")
    @Expose
    private ResponseData8000Res responseData;

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

    public ResponseData8000Res getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData8000Res responseData) {
        this.responseData = responseData;
    }

}
