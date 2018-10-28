package th.co.baiwa.excise.ws.entity.response.projectbase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestHeader {

    @SerializedName("sourceSystem")
    @Expose
    private String sourceSystem;
    @SerializedName("destinationSystem")
    @Expose
    private String destinationSystem;
    @SerializedName("options")
    @Expose
    private String options;
    @SerializedName("ipaddress")
    @Expose
    private String ipaddress;
    

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

    public String getOptions() {
        return options;
    }

    public void setOptions(String options) {
        this.options = options;
    }

    public String getIpaddress() {
        return ipaddress;
    }

    public void setIpaddress(String ipaddress) {
        this.ipaddress = ipaddress;
    }

}
