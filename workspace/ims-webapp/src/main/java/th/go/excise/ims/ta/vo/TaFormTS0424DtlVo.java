package th.go.excise.ims.ta.vo;

import java.math.BigDecimal;
import java.util.Date;

public class TaFormTS0424DtlVo {
	private String recNo;
	private String operatorOfficeName;
	private String operatorFullName;
	private String ownerFullName;
	private String newRegId;
	private String factoryTypeText;
	private String callDocNo;
	private Date callDocDate;
	private Date auditDateStart;
	private Date auditDateEnd;
	private BigDecimal taxAmt;
	private BigDecimal fineAmt;
	private BigDecimal extraAmt;
	private BigDecimal moiAmt;
	private BigDecimal netTaxAmt;
	private Integer residueNum;
	private String officerComment;
	
	
	public String getRecNo() {
		return recNo;
	}
	public void setRecNo(String recNo) {
		this.recNo = recNo;
	}
	public String getOperatorOfficeName() {
		return operatorOfficeName;
	}
	public void setOperatorOfficeName(String operatorOfficeName) {
		this.operatorOfficeName = operatorOfficeName;
	}
	public String getOperatorFullName() {
		return operatorFullName;
	}
	public void setOperatorFullName(String operatorFullName) {
		this.operatorFullName = operatorFullName;
	}
	public String getOwnerFullName() {
		return ownerFullName;
	}
	public void setOwnerFullName(String ownerFullName) {
		this.ownerFullName = ownerFullName;
	}
	public String getNewRegId() {
		return newRegId;
	}
	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}
	public String getFactoryTypeText() {
		return factoryTypeText;
	}
	public void setFactoryTypeText(String factoryTypeText) {
		this.factoryTypeText = factoryTypeText;
	}
	public String getCallDocNo() {
		return callDocNo;
	}
	public void setCallDocNo(String callDocNo) {
		this.callDocNo = callDocNo;
	}
	public Date getCallDocDate() {
		return callDocDate;
	}
	public void setCallDocDate(Date callDocDate) {
		this.callDocDate = callDocDate;
	}
	public Date getAuditDateStart() {
		return auditDateStart;
	}
	public void setAuditDateStart(Date auditDateStart) {
		this.auditDateStart = auditDateStart;
	}
	public Date getAuditDateEnd() {
		return auditDateEnd;
	}
	public void setAuditDateEnd(Date auditDateEnd) {
		this.auditDateEnd = auditDateEnd;
	}
	public BigDecimal getTaxAmt() {
		return taxAmt;
	}
	public void setTaxAmt(BigDecimal taxAmt) {
		this.taxAmt = taxAmt;
	}
	public BigDecimal getFineAmt() {
		return fineAmt;
	}
	public void setFineAmt(BigDecimal fineAmt) {
		this.fineAmt = fineAmt;
	}
	public BigDecimal getExtraAmt() {
		return extraAmt;
	}
	public void setExtraAmt(BigDecimal extraAmt) {
		this.extraAmt = extraAmt;
	}
	public BigDecimal getMoiAmt() {
		return moiAmt;
	}
	public void setMoiAmt(BigDecimal moiAmt) {
		this.moiAmt = moiAmt;
	}
	public BigDecimal getNetTaxAmt() {
		return netTaxAmt;
	}
	public void setNetTaxAmt(BigDecimal netTaxAmt) {
		this.netTaxAmt = netTaxAmt;
	}
	public Integer getResidueNum() {
		return residueNum;
	}
	public void setResidueNum(Integer residueNum) {
		this.residueNum = residueNum;
	}
	public String getOfficerComment() {
		return officerComment;
	}
	public void setOfficerComment(String officerComment) {
		this.officerComment = officerComment;
	}

}
