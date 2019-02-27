package th.go.excise.ims.ta.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

import java.util.List;

public class PlanWorksheetVo extends DataTableRequest {

    private static final long serialVersionUID = -2970785224520638999L;
    private List<String> ids;

    private String budgetYear;
    private String analysisNumber;
    private String planNumber;
    private String planStatus;
    private String authComment;
    private String planComment;

    public String getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    public String getAnalysisNumber() {
        return analysisNumber;
    }

    public void setAnalysisNumber(String analysisNumber) {
        this.analysisNumber = analysisNumber;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public String getPlanStatus() {
        return planStatus;
    }

    public void setPlanStatus(String planStatus) {
        this.planStatus = planStatus;
    }

    public String getAuthComment() {
        return authComment;
    }

    public void setAuthComment(String authComment) {
        this.authComment = authComment;
    }

    public String getPlanComment() {
        return planComment;
    }

    public void setPlanComment(String planComment) {
        this.planComment = planComment;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
