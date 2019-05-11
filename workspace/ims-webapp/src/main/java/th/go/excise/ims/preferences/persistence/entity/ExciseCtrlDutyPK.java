
package th.go.excise.ims.preferences.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Embeddable
public class ExciseCtrlDutyPK implements Serializable {
	
	private static final long serialVersionUID = 9076467808415736509L;
	
	@Column(name = "DUTY_GROUP_CODE")
	private String dutyGroupCode;
	@Column(name = "DUTY_GROUP_NAME")
	private String dutyGroupName;
	@Column(name = "RES_OFFCODE")
	private String resOffcode;

	public String getDutyGroupCode() {
		return dutyGroupCode;
	}

	public void setDutyGroupCode(String dutyGroupCode) {
		this.dutyGroupCode = dutyGroupCode;
	}

	public String getDutyGroupName() {
		return dutyGroupName;
	}

	public void setDutyGroupName(String dutyGroupName) {
		this.dutyGroupName = dutyGroupName;
	}

	public String getResOffcode() {
		return resOffcode;
	}

	public void setResOffcode(String resOffcode) {
		this.resOffcode = resOffcode;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
