
package th.co.baiwa.excise.ws.entity.response.pmgetdetailproject;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("projectId")
    @Expose
    private String projectId;
    @SerializedName("projectYear")
    @Expose
    private String projectYear;
    @SerializedName("projectName")
    @Expose
    private String projectName;
    @SerializedName("projectTypeName")
    @Expose
    private String projectTypeName;
    @SerializedName("ownerOfficeId")
    @Expose
    private String ownerOfficeId;
    @SerializedName("ownerOfficeName")
    @Expose
    private String ownerOfficeName;
    @SerializedName("taskDetail")
    @Expose
    private List<TaskDetail> taskDetail = null;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectYear() {
        return projectYear;
    }

    public void setProjectYear(String projectYear) {
        this.projectYear = projectYear;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectTypeName() {
        return projectTypeName;
    }

    public void setProjectTypeName(String projectTypeName) {
        this.projectTypeName = projectTypeName;
    }

    public String getOwnerOfficeId() {
        return ownerOfficeId;
    }

    public void setOwnerOfficeId(String ownerOfficeId) {
        this.ownerOfficeId = ownerOfficeId;
    }

    public String getOwnerOfficeName() {
        return ownerOfficeName;
    }

    public void setOwnerOfficeName(String ownerOfficeName) {
        this.ownerOfficeName = ownerOfficeName;
    }

    public List<TaskDetail> getTaskDetail() {
        return taskDetail;
    }

    public void setTaskDetail(List<TaskDetail> taskDetail) {
        this.taskDetail = taskDetail;
    }

}
