package th.co.baiwa.excise.ws.entity.response.projectbase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestData {

    @SerializedName("projectYear")
    @Expose
    private String projectYear;
    @SerializedName("projectTypeCode")
    @Expose
    private String projectTypeCode;

    public String getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(String projectYear) {
        this.projectYear = projectYear;
    }

    public String getProjectTypeCode() {
        return projectTypeCode;
    }

    public void setProjectTypeCode(String projectTypeCode) {
        this.projectTypeCode = projectTypeCode;
    }

}
