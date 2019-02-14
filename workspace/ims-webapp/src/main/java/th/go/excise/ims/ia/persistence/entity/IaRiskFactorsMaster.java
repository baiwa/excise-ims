
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_RISK_FACTORS_MASTER")
public class IaRiskFactorsMaster
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_FACTORS_MASTER_GEN")
    @SequenceGenerator(name = "IA_RISK_FACTORS_MASTER_GEN", sequenceName = "IA_RISK_FACTORS_MASTER_SEQ", allocationSize = 1)
    @Column(name = "ID")
    private BigDecimal id;
    @Column(name = "RISK_FACTORS_MASTER")
    private String riskFactorsMaster;
    @Column(name = "BUDGET_YEAR")
    private String budgetYear;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "STATUS_CHICKING")
    private BigDecimal statusChicking;

    public BigDecimal getId() {
        return id;
    }

    public void setId(BigDecimal id) {
        this.id = id;
    }

    public String getRiskFactorsMaster() {
        return riskFactorsMaster;
    }

    public void setRiskFactorsMaster(String riskFactorsMaster) {
        this.riskFactorsMaster = riskFactorsMaster;
    }

    public String getBudgetYear() {
        return budgetYear;
    }

    public void setBudgetYear(String budgetYear) {
        this.budgetYear = budgetYear;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getStatusChicking() {
        return statusChicking;
    }

    public void setStatusChicking(BigDecimal statusChicking) {
        this.statusChicking = statusChicking;
    }

}
