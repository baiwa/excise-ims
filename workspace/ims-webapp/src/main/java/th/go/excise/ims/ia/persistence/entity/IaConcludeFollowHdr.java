
package th.go.excise.ims.ia.persistence.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_CONCLUDE_FOLLOW_HDR")
public class IaConcludeFollowHdr
    extends BaseEntity
{

	private static final long serialVersionUID = 7214732600189130473L;
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_CONCLUDE_FOLLOW_HDR_GEN")
    @SequenceGenerator(name = "IA_CONCLUDE_FOLLOW_HDR_GEN", sequenceName = "IA_CONCLUDE_FOLLOW_HDR_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private Long id;
    @Column(name = "PROJECT_NAME")
    private String projectName;
    @Column(name = "BUDGET_YEAR")
    private String budgetYear;
    @Column(name = "CHECK_TYPE")
    private String checkType;
    @Column(name = "DATE_FROM")
    private Date dateFrom;
    @Column(name = "DATE_TO")
    private Date dateTo;
    @Column(name = "APPROVERS")
    private String approvers;
    @Column(name = "CHECK_STATUS")
    private String checkStatus;
    @Column(name = "APPROVE_DATE")
    private Date approveDate;
    @Column(name = "NOTATION")
    private String notation;
    @Column(name = "SEND_STATUS")
    private String sendStatus;
    @Column(name = "INSPECTION_WORK")
    private String inspectionWork;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public String getApprovers() {
        return approvers;
    }

    public void setApprovers(String approvers) {
        this.approvers = approvers;
    }

    public String getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(String checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Date getApproveDate() {
        return approveDate;
    }

    public void setApproveDate(Date approveDate) {
        this.approveDate = approveDate;
    }

    public String getNotation() {
        return notation;
    }

    public void setNotation(String notation) {
        this.notation = notation;
    }

}
