
package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_PAPER_SV_QTY")
public class TaPaperSvQty
    extends BaseEntity
{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_SV_QTY_GEN")
    @SequenceGenerator(name = "TA_PAPER_SV_QTY_GEN", sequenceName = "TA_PAPER_SV_QTY_SEQ", allocationSize = 1)
    @Column(name = "PAPER_SV_QTY_ID")
    private BigDecimal paperSvQtyId;
    @Column(name = "PLAN_NUMBER")
    private String planNumber;
    @Column(name = "PAPER_NUMBER")
    private String paperNumber;
    @Column(name = "OFFICE_CODE")
    private String officeCode;
    @Column(name = "NEW_REG_ID")
    private String newRegId;
    @Column(name = "START_DATE")
    private Date startDate;
    @Column(name = "END_DATE")
    private Date endDate;
    @Column(name = "UPLOAD_NUMBER")
    private String uploadNumber;
    @Column(name = "UPLOAD_DATE")
    private Date uploadDate;
    @Column(name = "SEQ_NO")
    private String seqNo;
    @Column(name = "GOODS_DESC")
    private String goodsDesc;
    @Column(name = "SERVICE_DOC_NO")
    private String serviceDocNo;
    @Column(name = "INCOME_DAILY_ACCOUNT_AMT")
    private BigDecimal incomeDailyAccountAmt;
    @Column(name = "PAYMENT_DOC_NO")
    private String paymentDocNo;
    @Column(name = "AUDIT_AMT")
    private BigDecimal auditAmt;
    @Column(name = "TAX_AMT")
    private BigDecimal taxAmt;
    @Column(name = "DIFF_AMT")
    private BigDecimal diffAmt;

    public BigDecimal getPaperSvQtyId() {
        return paperSvQtyId;
    }

    public void setPaperSvQtyId(BigDecimal paperSvQtyId) {
        this.paperSvQtyId = paperSvQtyId;
    }

    public String getPlanNumber() {
        return planNumber;
    }

    public void setPlanNumber(String planNumber) {
        this.planNumber = planNumber;
    }

    public String getPaperNumber() {
        return paperNumber;
    }

    public void setPaperNumber(String paperNumber) {
        this.paperNumber = paperNumber;
    }

    public String getOfficeCode() {
        return officeCode;
    }

    public void setOfficeCode(String officeCode) {
        this.officeCode = officeCode;
    }

    public String getNewRegId() {
        return newRegId;
    }

    public void setNewRegId(String newRegId) {
        this.newRegId = newRegId;
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

    public String getUploadNumber() {
        return uploadNumber;
    }

    public void setUploadNumber(String uploadNumber) {
        this.uploadNumber = uploadNumber;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getServiceDocNo() {
        return serviceDocNo;
    }

    public void setServiceDocNo(String serviceDocNo) {
        this.serviceDocNo = serviceDocNo;
    }

    public BigDecimal getIncomeDailyAccountAmt() {
        return incomeDailyAccountAmt;
    }

    public void setIncomeDailyAccountAmt(BigDecimal incomeDailyAccountAmt) {
        this.incomeDailyAccountAmt = incomeDailyAccountAmt;
    }

    public String getPaymentDocNo() {
        return paymentDocNo;
    }

    public void setPaymentDocNo(String paymentDocNo) {
        this.paymentDocNo = paymentDocNo;
    }

    public BigDecimal getAuditAmt() {
        return auditAmt;
    }

    public void setAuditAmt(BigDecimal auditAmt) {
        this.auditAmt = auditAmt;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public BigDecimal getDiffAmt() {
        return diffAmt;
    }

    public void setDiffAmt(BigDecimal diffAmt) {
        this.diffAmt = diffAmt;
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
    }

}
