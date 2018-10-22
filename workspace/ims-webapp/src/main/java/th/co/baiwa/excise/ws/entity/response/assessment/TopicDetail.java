
package th.co.baiwa.excise.ws.entity.response.assessment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicDetail {

    @SerializedName("topicAnswer")
    @Expose
    public String topicAnswer;
    @SerializedName("topicCode")
    @Expose
    public String topicCode;
    @SerializedName("topicLevel")
    @Expose
    public String topicLevel;
    @SerializedName("topicName")
    @Expose
    public String topicName;

}
