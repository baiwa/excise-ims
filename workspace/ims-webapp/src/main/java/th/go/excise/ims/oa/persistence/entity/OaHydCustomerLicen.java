
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
@Table(name = "OA_HYD_CUSTOMER_LICEN")
public class OaHydCustomerLicen
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_HYD_CUSTOMER_LICEN_GEN")
    @SequenceGenerator(name = "OA_HYD_CUSTOMER_LICEN_GEN", sequenceName = "OA_HYD_CUSTOMER_LICEN_SEQ", allocationSize = 1)
    @Column(name = "OA_CUSLICENSE_ID")
    private BigDecimal oaCuslicenseId;
    @Column(name = "OA_CUSTOMER_ID")
    private BigDecimal oaCustomerId;
    @Column(name = "LICENSE_TYPE")
    private String licenseType;
    @Column(name = "LICENSE_NO")
    private String licenseNo;
    @Column(name = "LICENSE_DATE")
    private Date licenseDate;
    @Column(name = "OLD_LICENSE_YEAR")
    private String oldLicenseYear;
    @Column(name = "BANK_GUARANTEE")
    private String bankGuarantee;
    @Column(name = "BANK_GUARANTEE_NO")
    private String bankGuaranteeNo;
    @Column(name = "BANK_GUARANTEE_DATE")
    private Date bankGuaranteeDate;
    @Column(name = "OPERATE_NAME")
    private String operateName;
    @Column(name = "OPERATE_REMARK")
    private String operateRemark;
    @Column(name = "APPROVE_NAME")
    private String approveName;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "OFF_CODE")
    private String offCode;
    @Column(name = "RECEIVE_DATE")
    private Date receiveDate;
    @Column(name = "RECEIVE_NO")
    private String receiveNo;
    @Column(name = "APPROVE")
    private String approve;
    @Column(name = "LICENSE_TYPE_FOR")
    private String licenseTypeFor;
    @Column(name = "LICENSE_TYPE_DESP")
    private String licenseTypeDesp;

    public BigDecimal getOaCuslicenseId() {
        return oaCuslicenseId;
    }

    public void setOaCuslicenseId(BigDecimal oaCuslicenseId) {
        this.oaCuslicenseId = oaCuslicenseId;
    }

    public BigDecimal getOaCustomerId() {
        return oaCustomerId;
    }

    public void setOaCustomerId(BigDecimal oaCustomerId) {
        this.oaCustomerId = oaCustomerId;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public Date getLicenseDate() {
        return licenseDate;
    }

    public void setLicenseDate(Date licenseDate) {
        this.licenseDate = licenseDate;
    }

    public String getOldLicenseYear() {
        return oldLicenseYear;
    }

    public void setOldLicenseYear(String oldLicenseYear) {
        this.oldLicenseYear = oldLicenseYear;
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

    public String getOperateName() {
        return operateName;
    }

    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getOperateRemark() {
        return operateRemark;
    }

    public void setOperateRemark(String operateRemark) {
        this.operateRemark = operateRemark;
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

    public Date getReceiveDate() {
        return receiveDate;
    }

    public void setReceiveDate(Date receiveDate) {
        this.receiveDate = receiveDate;
    }

    public String getReceiveNo() {
        return receiveNo;
    }

    public void setReceiveNo(String receiveNo) {
        this.receiveNo = receiveNo;
    }

    public String getApprove() {
        return approve;
    }

    public void setApprove(String approve) {
        this.approve = approve;
    }

    public String getLicenseTypeFor() {
        return licenseTypeFor;
    }

    public void setLicenseTypeFor(String licenseTypeFor) {
        this.licenseTypeFor = licenseTypeFor;
    }

    public String getLicenseTypeDesp() {
        return licenseTypeDesp;
    }

    public void setLicenseTypeDesp(String licenseTypeDesp) {
        this.licenseTypeDesp = licenseTypeDesp;
    }

}
