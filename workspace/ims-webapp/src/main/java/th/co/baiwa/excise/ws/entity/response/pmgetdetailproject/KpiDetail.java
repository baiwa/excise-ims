
package th.co.baiwa.excise.ws.entity.response.pmgetdetailproject;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class KpiDetail {

    @SerializedName("kpiId")
    @Expose
    private Integer kpiId;
    @SerializedName("kpiCode")
    @Expose
    private Integer kpiCode;
    @SerializedName("kpiName")
    @Expose
    private String kpiName;
    @SerializedName("kpiActivityCode")
    @Expose
    private Integer kpiActivityCode;
    @SerializedName("kpiActivityName")
    @Expose
    private String kpiActivityName;
    @SerializedName("kpiTargetAmount")
    @Expose
    private String kpiTargetAmount;
    @SerializedName("kpiExpenseActualAmount")
    @Expose
    private String kpiExpenseActualAmount;
    @SerializedName("kpiActualAmount")
    @Expose
    private Object kpiActualAmount;

    public Integer getKpiId() {
        return kpiId;
    }

    public void setKpiId(Integer kpiId) {
        this.kpiId = kpiId;
    }

    public Integer getKpiCode() {
        return kpiCode;
    }

    public void setKpiCode(Integer kpiCode) {
        this.kpiCode = kpiCode;
    }

    public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName;
    }

    public Integer getKpiActivityCode() {
        return kpiActivityCode;
    }

    public void setKpiActivityCode(Integer kpiActivityCode) {
        this.kpiActivityCode = kpiActivityCode;
    }

    public String getKpiActivityName() {
        return kpiActivityName;
    }

    public void setKpiActivityName(String kpiActivityName) {
        this.kpiActivityName = kpiActivityName;
    }

    public String getKpiTargetAmount() {
        return kpiTargetAmount;
    }

    public void setKpiTargetAmount(String kpiTargetAmount) {
        this.kpiTargetAmount = kpiTargetAmount;
    }

    public String getKpiExpenseActualAmount() {
        return kpiExpenseActualAmount;
    }

    public void setKpiExpenseActualAmount(String kpiExpenseActualAmount) {
        this.kpiExpenseActualAmount = kpiExpenseActualAmount;
    }

    public Object getKpiActualAmount() {
        return kpiActualAmount;
    }

    public void setKpiActualAmount(Object kpiActualAmount) {
        this.kpiActualAmount = kpiActualAmount;
    }

}
