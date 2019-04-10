
package th.go.excise.ims.ws.client.pcc.systemunworking.oxm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DataList {

	@SerializedName("systemCode")
	@Expose
	private String systemCode;

	@SerializedName("systemName")
	@Expose
	private String systemName;

	@SerializedName("startDate")
	@Expose
	private String startDate;

	@SerializedName("endDate")
	@Expose
	private String endDate;

	@SerializedName("countAll")
	@Expose
	private String countAll;

	@SerializedName("countNormal")
	@Expose
	private String countNormal;

	@SerializedName("countError")
	@Expose
	private String countError;

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}

	public String getSystemName() {
		return systemName;
	}

	public void setSystemName(String systemName) {
		this.systemName = systemName;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getCountAll() {
		return countAll;
	}

	public void setCountAll(String countAll) {
		this.countAll = countAll;
	}

	public String getCountNormal() {
		return countNormal;
	}

	public void setCountNormal(String countNormal) {
		this.countNormal = countNormal;
	}

	public String getCountError() {
		return countError;
	}

	public void setCountError(String countError) {
		this.countError = countError;
	}

}
