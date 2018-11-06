package th.co.baiwa.excise.ia.persistence.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseApiSaveIncome {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("desc")
    @Expose
    private String desc;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
