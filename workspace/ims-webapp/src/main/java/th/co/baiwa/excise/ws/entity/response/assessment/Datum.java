
package th.co.baiwa.excise.ws.entity.response.assessment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum {

    @SerializedName("summary")
    @Expose
    public String summary;
    @SerializedName("updateDate")
    @Expose
    public String updateDate;
    @SerializedName("offCode")
    @Expose
    public String offCode;
    @SerializedName("formCode")
    @Expose
    public String formCode;
    @SerializedName("formRound")
    @Expose
    public String formRound;
    @SerializedName("processPosition")
    @Expose
    public String processPosition;
    @SerializedName("formName")
    @Expose
    public String formName;
    @SerializedName("processDate")
    @Expose
    public String processDate;
    @SerializedName("formStatus")
    @Expose
    public String formStatus;
    @SerializedName("processBy")
    @Expose
    public String processBy;
    @SerializedName("formStatusDesc")
    @Expose
    public String formStatusDesc;
    @SerializedName("topicDetail")
    @Expose
    public List<TopicDetail> topicDetail = null;
    @SerializedName("offName")
    @Expose
    public String offName;
    @SerializedName("formYear")
    @Expose
    public String formYear;

}
