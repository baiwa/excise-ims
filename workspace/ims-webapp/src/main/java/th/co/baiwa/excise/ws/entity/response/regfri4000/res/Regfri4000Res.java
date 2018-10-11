
package th.co.baiwa.excise.ws.entity.response.regfri4000.res;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Regfri4000Res {

    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("ResponseData")
    @Expose
    private ResponseDataRes responseData;

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

    public ResponseDataRes getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseDataRes responseData) {
        this.responseData = responseData;
    }

}
