
package th.go.excise.ims.ws.client.other.getjsonpy2.model;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DivDetail {

    @SerializedName("divName")
    @Expose
    private String divName;
    @SerializedName("jobDetail")
    @Expose
    private List<JobDetail> jobDetail = null;
    @SerializedName("divSeq")
    @Expose
    private String divSeq;

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public List<JobDetail> getJobDetail() {
        return jobDetail;
    }

    public void setJobDetail(List<JobDetail> jobDetail) {
        this.jobDetail = jobDetail;
    }

    public String getDivSeq() {
        return divSeq;
    }

    public void setDivSeq(String divSeq) {
        this.divSeq = divSeq;
    }

}
