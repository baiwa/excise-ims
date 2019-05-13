package th.go.excise.ims.preferences.vo;
import
th.go.excise.ims.preferences.persistence.entity.ExciseCtrlDuty;

public class Ed03FormVo extends ExciseCtrlDuty {
	private String dutyGroupCode;
	private String dutyGroupName;
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

}
