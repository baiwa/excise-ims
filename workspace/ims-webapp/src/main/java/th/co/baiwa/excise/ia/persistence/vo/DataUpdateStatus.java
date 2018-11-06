
package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataUpdateStatus {

    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("checkId")
    @Expose
    private Long checkId;
    @SerializedName("riskType")
    @Expose
    private String riskType;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("risTaskChecking")
    @Expose
    private List<RisTaskChecking> risTaskChecking = null;

    

    public String getRiskType() {
        return riskType;
    }

    public void setRiskType(String riskType) {
        this.riskType = riskType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<RisTaskChecking> getRisTaskChecking() {
        return risTaskChecking;
    }

    public void setRisTaskChecking(List<RisTaskChecking> risTaskChecking) {
        this.risTaskChecking = risTaskChecking;
    }

	public Long getCheckId() {
		return checkId;
	}

	public void setCheckId(Long checkId) {
		this.checkId = checkId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
