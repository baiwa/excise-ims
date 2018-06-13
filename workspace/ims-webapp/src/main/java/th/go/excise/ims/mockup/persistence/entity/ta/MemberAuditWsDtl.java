package th.go.excise.ims.mockup.persistence.entity.ta;

import java.math.BigDecimal;
import java.util.Date;

public class MemberAuditWsDtl {
    private BigDecimal taMemberAuditWsDtlId;
    private BigDecimal taMemberAuditWsHeaderId;
    private String memberId;
    private String memberName;
    private String startDate;
    private String endDate;
    private String memberCoupon;
    private String dateService;
    private String createdBy;
    private Date createdDatetime;
    private String updateBy;
    private Date updateDatetime;

    public BigDecimal getTaMemberAuditWsDtlId() {
        return taMemberAuditWsDtlId;
    }

    public void setTaMemberAuditWsDtlId(BigDecimal taMemberAuditWsDtlId) {
        this.taMemberAuditWsDtlId = taMemberAuditWsDtlId;
    }

    public BigDecimal getTaMemberAuditWsHeaderId() {
        return taMemberAuditWsHeaderId;
    }

    public void setTaMemberAuditWsHeaderId(BigDecimal taMemberAuditWsHeaderId) {
        this.taMemberAuditWsHeaderId = taMemberAuditWsHeaderId;
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getMemberCoupon() {
        return memberCoupon;
    }

    public void setMemberCoupon(String memberCoupon) {
        this.memberCoupon = memberCoupon;
    }

    public String getDateService() {
        return dateService;
    }

    public void setDateService(String dateService) {
        this.dateService = dateService;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getUpdateDatetime() {
        return updateDatetime;
    }

    public void setUpdateDatetime(Date updateDatetime) {
        this.updateDatetime = updateDatetime;
    }
}
