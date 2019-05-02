package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

public class IaAuditIncD2Vo {

    private String id;
    private String iaAuditIncD2Id;
    private String receiptDate;
    private BigDecimal amount;
    private BigDecimal printPerDay;
    private String auditCheck;
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIaAuditIncD2Id() {
        return iaAuditIncD2Id;
    }

    public void setIaAuditIncD2Id(String iaAuditIncD2Id) {
        this.iaAuditIncD2Id = iaAuditIncD2Id;
    }

    public String getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(String receiptDate) {
        this.receiptDate = receiptDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrintPerDay() {
        return printPerDay;
    }

    public void setPrintPerDay(BigDecimal printPerDay) {
        this.printPerDay = printPerDay;
    }

    public String getAuditCheck() {
        return auditCheck;
    }

    public void setAuditCheck(String auditCheck) {
        this.auditCheck = auditCheck;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
