
package th.co.baiwa.excise.ws.entity.response.projectbase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseHeader {

    @SerializedName("sourceSystem")
    @Expose
    public String sourceSystem;
    @SerializedName("destinationSystem")
    @Expose
    public String destinationSystem;
    @SerializedName("responseCode")
    @Expose
    public String responseCode;
    @SerializedName("responseMessage")
    @Expose
    public String responseMessage;
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
