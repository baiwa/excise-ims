
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
@Table(name = "OA_CUSTOMER")
public class OaCustomer
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_CUSTOMER_GEN")
    @SequenceGenerator(name = "OA_CUSTOMER_GEN", sequenceName = "OA_CUSTOMER_SEQ", allocationSize = 1)
    @Column(name = "OA_CUSTOMER_ID")
    private BigDecimal oaCustomerId;
    @Column(name = "IDENTIFY_NO")
    private String identifyNo;
    @Column(name = "IDENTIFY_TYPE")
    private String identifyType;
    @Column(name = "NAME")
    private String name;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Column(name = "MOBILE")
    private String mobile;
    @Column(name = "OLD_CUSTOMER")
    private String oldCustomer;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "WAREHOUSE_ADDRESS")
    private String warehouseAddress;

    public BigDecimal getOaCustomerId() {
        return oaCustomerId;
    }

    public void setOaCustomerId(BigDecimal oaCustomerId) {
        this.oaCustomerId = oaCustomerId;
    }

    public String getIdentifyNo() {
        return identifyNo;
    }

    public void setIdentifyNo(String identifyNo) {
        this.identifyNo = identifyNo;
    }

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getOldCustomer() {
        return oldCustomer;
    }

    public void setOldCustomer(String oldCustomer) {
        this.oldCustomer = oldCustomer;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getWarehouseAddress() {
        return warehouseAddress;
    }

    public void setWarehouseAddress(String warehouseAddress) {
        this.warehouseAddress = warehouseAddress;
    }

}
