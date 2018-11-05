
package th.co.baiwa.excise.ia.persistence.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CheckList {

    @SerializedName("seq")
    @Expose
    private Integer seq;
    @SerializedName("desc")
    @Expose
    private String desc;
    @SerializedName("code")
    @Expose
    private String code;

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
