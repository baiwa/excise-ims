
package th.co.baiwa.excise.ia.persistence.vo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RisTaskChecking {

    @SerializedName("id")
    @Expose
    private Object id;
    @SerializedName("taskId")
    @Expose
    private Long taskId;
    @SerializedName("checked")
    @Expose
    private String checked;
    @SerializedName("desc")
    @Expose
    private String desc;
	public Object getId() {
		return id;
	}
	public void setId(Object id) {
		this.id = id;
	}
	public Long getTaskId() {
		return taskId;
	}
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}
	public String getChecked() {
		return checked;
	}
	public void setChecked(String checked) {
		this.checked = checked;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}


}
