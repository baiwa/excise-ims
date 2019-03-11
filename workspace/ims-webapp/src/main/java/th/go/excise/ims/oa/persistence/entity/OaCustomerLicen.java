
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
@Table(name = "OA_CUSTOMER_LICENSE")
public class OaCustomerLicen
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_CUSTOMER_LICENSE_GEN")
    @SequenceGenerator(name = "OA_CUSTOMER_LICENSE_GEN", sequenceName = "OA_CUSTOMER_LICESE_SEQ", allocationSize = 1)
    @Column(name = "OA_CUSLICEN_ID")
    private BigDecimal oaCuslicenId;
    @Column(name = "OA_CUSTOMER_ID")
    private BigDecimal oaCustomerId;
    @Column(name = "LICEN_TYPE")
    private String licenType;
    @Column(name = "LICEN_NO")
    private String licenNo;
    @Column(name = "LICEN_DATE")
    private Date licenDate;
    @Column(name = "OLD_LICEN_YEAR")
    private String oldLicenYear;
    @Column(name = "BANK_GUARANTEE")
    private String bankGuarantee;
    @Column(name = "BANK_GUARANTEE_NO")
    private String bankGuaranteeNo;
    @Column(name = "BANK_GUARANTEE_DATE")
    private Date bankGuaranteeDate;
    @Column(name = "OPERRATE_NAME")
    private String operrateName;
    @Column(name = "OPERRATE_REMARK")
    private String operrateRemark;
    @Column(name = "APPROVE_NAME")
    private String approveName;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "OFF_CODE")
    private String offCode;
    @Column(name = "RECIVE_DATE")
    private Date reciveDate;
    @Column(name = "RECIVE_NO")
    private String reciveNo;
    @Column(name = "APPROVE")
    private String approve;

    public BigDecimal getOaCuslicenId() {
        return oaCuslicenId;
    }

    public void setOaCuslicenId(BigDecimal oaCuslicenId) {
        this.oaCuslicenId = oaCuslicenId;
    }

    public BigDecimal getOaCustomerId() {
        return oaCustomerId;
    }

    public void setOaCustomerId(BigDecimal oaCustomerId) {
        this.oaCustomerId = oaCustomerId;
    }

    public String getLicenType() {
        return licenType;
    }

    public void setLicenType(String licenType) {
        this.licenType = licenType;
    }

    public String getLicenNo() {
        return licenNo;
    }

    public void setLicenNo(String licenNo) {
        this.licenNo = licenNo;
    }

    public Date getLicenDate() {
        return licenDate;
    }

    public void setLicenDate(Date licenDate) {
        this.licenDate = licenDate;
    }

    public String getOldLicenYear() {
        return oldLicenYear;
    }

    public void setOldLicenYear(String oldLicenYear) {
        this.oldLicenYear = oldLicenYear;
    }

    public String getBankGuarantee() {
        return bankGuarantee;
    }

    public void setBankGuarantee(String bankGuarantee) {
        this.bankGuarantee = bankGuarantee;
    }

    public String getBankGuaranteeNo() {
        return bankGuaranteeNo;
    }

    public void setBankGuaranteeNo(String bankGuaranteeNo) {
        this.bankGuaranteeNo = bankGuaranteeNo;
    }

    public Date getBankGuaranteeDate() {
        return bankGuaranteeDate;
    }

    public void setBankGuaranteeDate(Date bankGuaranteeDate) {
        this.bankGuaranteeDate = bankGuaranteeDate;
    }

    public String getOperrateName() {
        return operrateName;
    }

    public void setOperrateName(String operrateName) {
        this.operrateName = operrateName;
    }

    public String getOperrateRemark() {
        return operrateRemark;
    }

    public void setOperrateRemark(String operrateRemark) {
        this.operrateRemark = operrateRemark;
    }

    public String getApproveName() {
        return approveName;
    }

    public void setApproveName(String approveName) {
        this.approveName = approveName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getOffCode() {
        return offCode;
    }

    public void setOffCode(String offCode) {
        this.offCode = offCode;
    }

    public Date getReciveDate() {
        return reciveDate;
    }

    public void setReciveDate(Date reciveDate) {
        this.reciveDate = reciveDate;
    }

    public String getReciveNo() {
        return reciveNo;
    }

    public void setReciveNo(String reciveNo) {
        this.reciveNo = reciveNo;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

}
