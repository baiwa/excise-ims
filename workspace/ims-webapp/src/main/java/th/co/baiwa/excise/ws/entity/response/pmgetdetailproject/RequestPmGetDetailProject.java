
package th.co.baiwa.excise.ws.entity.response.pmgetdetailproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestPmGetDetailProject {

    @SerializedName("projectId")
    @Expose
    private String projectId;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

}
