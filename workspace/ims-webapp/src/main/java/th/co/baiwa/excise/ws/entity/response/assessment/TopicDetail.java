
package th.co.baiwa.excise.ws.entity.response.assessment;

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
    
    

}
