
package th.co.baiwa.excise.ws.entity.response.pmgetdetailproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseHeader {

    @SerializedName("sourceSystem")
    @Expose
    private String sourceSystem;
    @SerializedName("destinationSystem")
    @Expose
    private String destinationSystem;
    @SerializedName("responseCode")
    @Expose
    private String responseCode;
    @SerializedName("responseMessage")
    @Expose
    private String responseMessage;

    public String getSourceSystem() {
        return sourceSystem;
    }

    public void setSourceSystem(String sourceSystem) {
        this.sourceSystem = sourceSystem;
    }

    public String getDestinationSystem() {
        return destinationSystem;
    }

    public void setDestinationSystem(String destinationSystem) {
        this.destinationSystem = destinationSystem;
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

}
