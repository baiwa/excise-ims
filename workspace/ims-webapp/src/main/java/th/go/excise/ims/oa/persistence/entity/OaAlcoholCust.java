
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
@Table(name = "OA_ALCOHOL_CUST")
public class OaAlcoholCust
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_ALCOHOL_CUST_GEN")
    @SequenceGenerator(name = "OA_ALCOHOL_CUST_GEN", sequenceName = "OA_ALCOHOL_CUST_SEQ", allocationSize = 1)
    @Column(name = "OA_ALCOHOL_CUST_ID")
    private BigDecimal oaAlcoholCustId;
    @Column(name = "OA_ALCOHOL_ID")
    private BigDecimal oaAlcoholId;
    @Column(name = "CUST_NAME")
    private String custName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "MOBILE")
    private String mobile;

    public BigDecimal getOaAlcoholCustId() {
        return oaAlcoholCustId;
    }

    public void setOaAlcoholCustId(BigDecimal oaAlcoholCustId) {
        this.oaAlcoholCustId = oaAlcoholCustId;
    }

    public BigDecimal getOaAlcoholId() {
        return oaAlcoholId;
    }

    public void setOaAlcoholId(BigDecimal oaAlcoholId) {
        this.oaAlcoholId = oaAlcoholId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
