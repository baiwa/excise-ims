
package th.go.excise.ims.ws.client.other.getJsonQt.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TopicDetail {

    @SerializedName("topicAnswer")
    @Expose
    private String topicAnswer;
    @SerializedName("topicCode")
    @Expose
    private String topicCode;
    @SerializedName("topicLevel")
    @Expose
    private String topicLevel;
    @SerializedName("topicName")
    @Expose
    private String topicName;
    @SerializedName("topicResult")
    @Expose
    private String topicResult;

    public String getTopicAnswer() {
        return topicAnswer;
    }

    public void setTopicAnswer(String topicAnswer) {
        this.topicAnswer = topicAnswer;
    }

    public String getTopicCode() {
        return topicCode;
    }

    public void setTopicCode(String topicCode) {
        this.topicCode = topicCode;
    }

    public String getTopicLevel() {
        return topicLevel;
    }

    public void setTopicLevel(String topicLevel) {
        this.topicLevel = topicLevel;
    }

    public String getTopicName() {
        return topicName;
    }

    public void setTopicName(String topicName) {
        this.topicName = topicName;
    }

    public String getTopicResult() {
        return topicResult;
    }

    public void setTopicResult(String topicResult) {
        this.topicResult = topicResult;
    }

}
