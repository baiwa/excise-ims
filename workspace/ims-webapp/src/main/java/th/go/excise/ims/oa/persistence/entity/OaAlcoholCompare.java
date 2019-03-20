
package th.go.excise.ims.oa.persistence.entity;

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
@Table(name = "OA_ALCOHOL_COMPARE")
public class OaAlcoholCompare
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ALCOHOL_COMPARE_GEN")
    @SequenceGenerator(name = "OA_ALCOHOL_COMPARE_GEN", sequenceName = "OA_ALCOHOL_COMPARE_SEQ", allocationSize = 1)
    @Column(name = "OA_ACH_COMPARE_ID")
    private BigDecimal oaAchCompareId;
    @Column(name = "OA_ALCOHOL_ID")
    private BigDecimal oaAlcoholId;
    @Column(name = "SEQ")
    private BigDecimal seq;
    @Column(name = "NAME")
    private String name;
    @Column(name = "SUMARY_DATE")
    private Date sumaryDate;
    @Column(name = "AUDIT_DATE")
    private Date auditDate;
    @Column(name = "REMARK")
    private String remark;
    @Column(name = "SUMARY_STOCK")
    private BigDecimal sumaryStock;
    @Column(name = "AUDIT_STOCK")
    private BigDecimal auditStock;
    @Column(name = "OVER_RATE")
    private BigDecimal overRate;

    public BigDecimal getOaAchCompareId() {
        return oaAchCompareId;
    }

    public void setOaAchCompareId(BigDecimal oaAchCompareId) {
        this.oaAchCompareId = oaAchCompareId;
    }

    public BigDecimal getOaAlcoholId() {
        return oaAlcoholId;
    }

    public void setOaAlcoholId(BigDecimal oaAlcoholId) {
        this.oaAlcoholId = oaAlcoholId;
    }

    public BigDecimal getSeq() {
        return seq;
    }

    public void setSeq(BigDecimal seq) {
        this.seq = seq;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getSumaryDate() {
        return sumaryDate;
    }

    public void setSumaryDate(Date sumaryDate) {
        this.sumaryDate = sumaryDate;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public BigDecimal getSumaryStock() {
        return sumaryStock;
    }

    public void setSumaryStock(BigDecimal sumaryStock) {
        this.sumaryStock = sumaryStock;
    }

    public BigDecimal getAuditStock() {
        return auditStock;
    }

    public void setAuditStock(BigDecimal auditStock) {
        this.auditStock = auditStock;
    }

    public BigDecimal getOverRate() {
        return overRate;
    }

    public void setOverRate(BigDecimal overRate) {
        this.overRate = overRate;
    }

}
