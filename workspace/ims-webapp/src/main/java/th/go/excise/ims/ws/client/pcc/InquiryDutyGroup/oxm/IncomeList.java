
package th.go.excise.ims.ws.client.pcc.InquiryDutyGroup.oxm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IncomeList {

	@SerializedName("GROUP_ID")
	@Expose
	private String groupId;

	@SerializedName("GROUP_NAME")
	@Expose
	private String groupName;

	@SerializedName("GROUP_STATUS")
	@Expose
	private String groupStatus;

	@SerializedName("SUP_GROUP_ID")
	@Expose
	private String supGroupId;

	@SerializedName("BEGIN_DATE")
	@Expose
	private String beginDate;

	@SerializedName("UPD_USERID")
	@Expose
	private String updUserid;

	@SerializedName("UPD_DATE")
	@Expose
	private String updDate;

	@SerializedName("REG_STATUS")
	@Expose
	private String regStatus;

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupStatus() {
		return groupStatus;
	}

	public void setGroupStatus(String groupStatus) {
		this.groupStatus = groupStatus;
	}

	public String getSupGroupId() {
		return supGroupId;
	}

	public void setSupGroupId(String supGroupId) {
		this.supGroupId = supGroupId;
	}

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getUpdUserid() {
		return updUserid;
	}

	public void setUpdUserid(String updUserid) {
		this.updUserid = updUserid;
	}

	public String getUpdDate() {
		return updDate;
	}

	public void setUpdDate(String updDate) {
		this.updDate = updDate;
	}

	public String getRegStatus() {
		return regStatus;
	}

	public void setRegStatus(String regStatus) {
		this.regStatus = regStatus;
	}

}
