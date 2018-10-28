
package th.co.baiwa.excise.ws.entity.response.pmgetdetailproject;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TaskDetail {

    @SerializedName("taskId")
    @Expose
    private Integer taskId;
    @SerializedName("taskDescriptionText")
    @Expose
    private String taskDescriptionText;
    @SerializedName("kpiDetail")
    @Expose
    private List<KpiDetail> kpiDetail = null;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTaskDescriptionText() {
        return taskDescriptionText;
    }

    public void setTaskDescriptionText(String taskDescriptionText) {
        this.taskDescriptionText = taskDescriptionText;
    }

    public List<KpiDetail> getKpiDetail() {
        return kpiDetail;
    }

    public void setKpiDetail(List<KpiDetail> kpiDetail) {
        this.kpiDetail = kpiDetail;
    }

}
