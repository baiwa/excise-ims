
package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("checkId")
    @Expose
    private Long checkId;
    @SerializedName("sectorName")
    @Expose
    private String sectorName;
    @SerializedName("sectorCode")
    @Expose
    private String sectorCode;
    @SerializedName("branchCode")
    @Expose
    private Object branchCode;
    @SerializedName("branchName")
    @Expose
    private Object branchName;
    @SerializedName("checked")
    @Expose
    private String checked;
    @SerializedName("checkList")
    @Expose
    private List<CheckList> checkList = null;

    public Long getCheckId() {
        return checkId;
    }

    public void setCheckId(Long checkId) {
        this.checkId = checkId;
    }

    public String getSectorName() {
        return sectorName;
    }

    public void setSectorName(String sectorName) {
        this.sectorName = sectorName;
    }

    public String getSectorCode() {
        return sectorCode;
    }

    public void setSectorCode(String sectorCode) {
        this.sectorCode = sectorCode;
    }

    public Object getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(Object branchCode) {
        this.branchCode = branchCode;
    }

    public Object getBranchName() {
        return branchName;
    }

    public void setBranchName(Object branchName) {
        this.branchName = branchName;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public List<CheckList> getCheckList() {
        return checkList;
    }

    public void setCheckList(List<CheckList> checkList) {
        this.checkList = checkList;
    }

}
