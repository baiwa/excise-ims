package th.go.excise.ims.ta.vo;

import java.util.List;

public class PlanWorkSheetVo {
    private Boolean typeCheckedAll = false;
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

    public Boolean getTypeCheckedAll() {
        return typeCheckedAll;
    }

    public void setTypeCheckedAll(Boolean typeCheckedAll) {
        this.typeCheckedAll = typeCheckedAll;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
