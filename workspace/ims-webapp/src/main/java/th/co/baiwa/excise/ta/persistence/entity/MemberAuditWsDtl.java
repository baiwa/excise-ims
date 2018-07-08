package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import th.co.baiwa.excise.domain.BaseEntity;

public class MemberAuditWsDtl extends BaseEntity {
	private BigDecimal taMemberAuditWsDtlId;
	private BigDecimal taMemberAuditWsHeaderId;
	private String memberId;
	private String memberName;
	private String startDate;
	private String endDate;
	private String memberCoupon;
	private String dateService;

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

}
