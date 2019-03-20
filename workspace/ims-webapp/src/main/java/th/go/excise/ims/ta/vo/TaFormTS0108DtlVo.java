package th.go.excise.ims.ta.vo;

import java.time.LocalDate;

public class TaFormTS0108DtlVo {
	private Integer recNo;
	private LocalDate auditDate;
	private String officerFullName;
	private String officerPosition;
	private String auditTime;
	private String auditDest;
	private String auditTopic;
	private String approvedAck;
	private String officerAck;
	private String auditResultDocNo;
	private LocalDate auditResultDate;
	private String auditComment;
	
	public Integer getRecNo() {
		return recNo;
	}
	public void setRecNo(Integer recNo) {
		this.recNo = recNo;
	}
	public LocalDate getAuditDate() {
		return auditDate;
	}
	public void setAuditDate(LocalDate auditDate) {
		this.auditDate = auditDate;
	}
	public String getOfficerFullName() {
		return officerFullName;
	}
	public void setOfficerFullName(String officerFullName) {
		this.officerFullName = officerFullName;
	}
	public String getOfficerPosition() {
		return officerPosition;
	}
	public void setOfficerPosition(String officerPosition) {
		this.officerPosition = officerPosition;
	}
	public String getAuditTime() {
		return auditTime;
	}
	public void setAuditTime(String auditTime) {
		this.auditTime = auditTime;
	}
	public String getAuditDest() {
		return auditDest;
	}
	public void setAuditDest(String auditDest) {
		this.auditDest = auditDest;
	}
	public String getAuditTopic() {
		return auditTopic;
	}
	public void setAuditTopic(String auditTopic) {
		this.auditTopic = auditTopic;
	}
	public String getApprovedAck() {
		return approvedAck;
	}
	public void setApprovedAck(String approvedAck) {
		this.approvedAck = approvedAck;
	}
	public String getOfficerAck() {
		return officerAck;
	}
	public void setOfficerAck(String officerAck) {
		this.officerAck = officerAck;
	}
	public String getAuditResultDocNo() {
		return auditResultDocNo;
	}
	public void setAuditResultDocNo(String auditResultDocNo) {
		this.auditResultDocNo = auditResultDocNo;
	}
	public LocalDate getAuditResultDate() {
		return auditResultDate;
	}
	public void setAuditResultDate(LocalDate auditResultDate) {
		this.auditResultDate = auditResultDate;
	}
	public String getAuditComment() {
		return auditComment;
	}
	public void setAuditComment(String auditComment) {
		this.auditComment = auditComment;
	}
	
}
