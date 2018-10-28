
package th.co.baiwa.excise.ws.entity.response.projectbase;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListPMProject {

    @SerializedName("projectId")
    @Expose
    public String projectId;
    @SerializedName("projectYear")
    @Expose
    public String projectYear;
    @SerializedName("projectName")
    @Expose
    public String projectName;
    @SerializedName("projectTypeName")
    @Expose
    public String projectTypeName;
    @SerializedName("ownerOfficeId")
    @Expose
    public String ownerOfficeId;
    @SerializedName("ownerOfficeName")
    @Expose
    public String ownerOfficeName;
    @SerializedName("expenseBudgetAmountM")
    @Expose
    public String expenseBudgetAmountM;
    @SerializedName("expenseBudgetAmountX")
    @Expose
    public String expenseBudgetAmountX;
    @SerializedName("expenseBudgetAmountA")
    @Expose
    public String expenseBudgetAmountA;
    @SerializedName("approvedBudgetAmount")
    @Expose
    public String approvedBudgetAmount;
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getProjectYear() {
		return projectYear;
	}
	public void setProjectYear(String projectYear) {
		this.projectYear = projectYear;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectTypeName() {
		return projectTypeName;
	}
	public void setProjectTypeName(String projectTypeName) {
		this.projectTypeName = projectTypeName;
	}
	public String getOwnerOfficeId() {
		return ownerOfficeId;
	}
	public void setOwnerOfficeId(String ownerOfficeId) {
		this.ownerOfficeId = ownerOfficeId;
	}
	public String getOwnerOfficeName() {
		return ownerOfficeName;
	}
	public void setOwnerOfficeName(String ownerOfficeName) {
		this.ownerOfficeName = ownerOfficeName;
	}
	public String getExpenseBudgetAmountM() {
		return expenseBudgetAmountM;
	}
	public void setExpenseBudgetAmountM(String expenseBudgetAmountM) {
		this.expenseBudgetAmountM = expenseBudgetAmountM;
	}
	public String getExpenseBudgetAmountX() {
		return expenseBudgetAmountX;
	}
	public void setExpenseBudgetAmountX(String expenseBudgetAmountX) {
		this.expenseBudgetAmountX = expenseBudgetAmountX;
	}
	public String getExpenseBudgetAmountA() {
		return expenseBudgetAmountA;
	}
	public void setExpenseBudgetAmountA(String expenseBudgetAmountA) {
		this.expenseBudgetAmountA = expenseBudgetAmountA;
	}
	public String getApprovedBudgetAmount() {
		return approvedBudgetAmount;
	}
	public void setApprovedBudgetAmount(String approvedBudgetAmount) {
		this.approvedBudgetAmount = approvedBudgetAmount;
	}

}
