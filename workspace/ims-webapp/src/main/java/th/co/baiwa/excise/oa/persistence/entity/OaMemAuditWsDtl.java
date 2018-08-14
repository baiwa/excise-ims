package th.co.baiwa.excise.oa.persistence.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="OA_MEM_AUDIT_WS_DTL")
public class OaMemAuditWsDtl implements Serializable {
	
	private static final long serialVersionUID = -1533380969810925377L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OA_MEM_AUDIT_WS_DTL_GEN")
	@SequenceGenerator(name = "OA_MEM_AUDIT_WS_DTL_GEN", sequenceName = "OA_MEM_AUDIT_WS_DTL_SEQ", allocationSize = 1) 	 
	@Column(name="MEMBER_AUDIT_WS_DTL_ID")
	private Long memberAuditWsDtlId;
	
	@Column(name="MEMBER_AUDIT_WS_HDR_ID")
	private Long memberAuditWsHdrId;
	
	@Column(name="MEMBER_ID")
	private String memberId;
	
	@Column(name="MEMBER_NAME")
	private String memberName;
		
	@Column(name="START_DATE")
	private String startDate;
	
	@Column(name="END_DATE")
	private String endDate;
	
	@Column(name="MEMBER_COUPON")
	private String memberCoupon;
	
	@Column(name="DATE_SERVICE")
	private String dateService;

	public Long getMemberAuditWsDtlId() {
		return memberAuditWsDtlId;
	}

	public void setMemberAuditWsDtlId(Long memberAuditWsDtlId) {
		this.memberAuditWsDtlId = memberAuditWsDtlId;
	}

	public Long getMemberAuditWsHdrId() {
		return memberAuditWsHdrId;
	}

	public void setMemberAuditWsHdrId(Long memberAuditWsHdrId) {
		this.memberAuditWsHdrId = memberAuditWsHdrId;
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