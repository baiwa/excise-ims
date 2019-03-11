
package th.go.excise.ims.oa.persistence.entity;

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
@Table(name = "OA_CUSTOMER_LICENSE_DETAIL")
public class OaCustomerLicenDetail
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_CUSTOMER_LICENSE_DETAIL_GEN")
    @SequenceGenerator(name = "OA_CUSTOMER_LICENSE_DETAIL_GEN", sequenceName = "OA_CUSTOMER_LICENSE_DETAIL_SEQ", allocationSize = 1)
    @Column(name = "OA_CUSLICEN_DTL_ID")
    private BigDecimal oaCuslicenDtlId;
    @Column(name = "OA_CUSLICEN_ID")
    private BigDecimal oaCuslicenId;
    @Column(name = "SEQ")
    private BigDecimal seq;
    @Column(name = "NAME")
    private String name;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "AMOUNT")
    private BigDecimal amount;
    @Column(name = "LICEN_NO")
    private String licenNo;

    public BigDecimal getOaCuslicenDtlId() {
        return oaCuslicenDtlId;
    }

    public void setOaCuslicenDtlId(BigDecimal oaCuslicenDtlId) {
        this.oaCuslicenDtlId = oaCuslicenDtlId;
    }

    public BigDecimal getOaCuslicenId() {
        return oaCuslicenId;
    }

    public void setOaCuslicenId(BigDecimal oaCuslicenId) {
        this.oaCuslicenId = oaCuslicenId;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getLicenNo() {
        return licenNo;
    }

    public void setLicenNo(String licenNo) {
        this.licenNo = licenNo;
    }

}
