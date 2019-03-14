
package th.go.excise.ims.ta.persistence.entity;

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
@Table(name = "TA_PAPER_SV_MEMBER")
public class TaPaperSvMember extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5050130710614885053L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_SV_MEMBER_GEN")
	@SequenceGenerator(name = "TA_PAPER_SV_MEMBER_GEN", sequenceName = "TA_PAPER_SV_MEMBER_SEQ", allocationSize = 1)
	@Column(name = "PAPER_SV_MEMBER_ID")
	private Long paperSvMemberId;
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
	@Column(name = "MEMBER_CODE")
	private String memberCode;
	@Column(name = "MEMBER_FULL_NAME")
	private String memberFullName;
	@Column(name = "MEMBER_START_DATE")
	private Date memberStartDate;
	@Column(name = "MEMBER_END_DATE")
	private Date memberEndDate;
	@Column(name = "MEMBER_COUPON")
	private String memberCoupon;
	@Column(name = "MEMBER_USED_DATE")
	private Date memberUsedDate;
	@Column(name = "MEMBER_STATUS")
	private String memberStatus;

	public Long getPaperSvMemberId() {
		return paperSvMemberId;
	}

	public void setPaperSvMemberId(Long paperSvMemberId) {
		this.paperSvMemberId = paperSvMemberId;
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

	public String getMemberCode() {
		return memberCode;
	}

	public void setMemberCode(String memberCode) {
		this.memberCode = memberCode;
	}

	public String getMemberFullName() {
		return memberFullName;
	}

	public void setMemberFullName(String memberFullName) {
		this.memberFullName = memberFullName;
	}

	public Date getMemberStartDate() {
		return memberStartDate;
	}

	public void setMemberStartDate(Date memberStartDate) {
		this.memberStartDate = memberStartDate;
	}

	public Date getMemberEndDate() {
		return memberEndDate;
	}

	public void setMemberEndDate(Date memberEndDate) {
		this.memberEndDate = memberEndDate;
	}

	public String getMemberCoupon() {
		return memberCoupon;
	}

	public void setMemberCoupon(String memberCoupon) {
		this.memberCoupon = memberCoupon;
	}

	public Date getMemberUsedDate() {
		return memberUsedDate;
	}

	public void setMemberUsedDate(Date memberUsedDate) {
		this.memberUsedDate = memberUsedDate;
	}

	public String getMemberStatus() {
		return memberStatus;
	}

	public void setMemberStatus(String memberStatus) {
		this.memberStatus = memberStatus;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
