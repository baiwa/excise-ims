
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
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
@Table(name = "IA_RISK_CHECK_PERIOD")
public class IaRiskCheckPeriod
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_CHECK_PERIOD_GEN")
    @SequenceGenerator(name = "IA_RISK_CHECK_PERIOD_GEN", sequenceName = "IA_RISK_CHECK_PERIOD_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "PROJECT_NAME")
    private String projectName;
    @Column(name = "DEPARTMENT")
    private String department;
    @Column(name = "DATE_FROM")
    private Date dateFrom;
    @Column(name = "DATE_START")
    private Date dateStart;
    @Column(name = "LONG_TIME")
    private BigDecimal longTime;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateStart() {
        return dateStart;
    }

    public void setDateStart(Date dateStart) {
        this.dateStart = dateStart;
    }

    public BigDecimal getLongTime() {
        return longTime;
    }

    public void setLongTime(BigDecimal longTime) {
        this.longTime = longTime;
    }

}
