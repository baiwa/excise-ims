
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
@Table(name = "TA_PAPER_PR_OUT_MAT")
public class TaPaperPrOutMat extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2002496047999290992L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR_OUT_MAT_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR_OUT_MAT_GEN", sequenceName = "TA_PAPER_PR_OUT_MAT_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR_OUT_MAT_ID")
	private Long paperPrOutMatId;
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
	@Column(name = "MATERIAL_DESC")
	private String materialDesc;
	@Column(name = "OUTPUT_MATERIAL_QTY")
	private BigDecimal outputMaterialQty;
	@Column(name = "DAILY_ACCOUNT_QTY")
	private BigDecimal dailyAccountQty;
	@Column(name = "MONTH_STATEMENT_QTY")
	private BigDecimal monthStatementQty;
	@Column(name = "EXTERNAL_DATA_QTY")
	private BigDecimal externalDataQty;
	@Column(name = "MAX_DIFF_QTY")
	private BigDecimal maxDiffQty;

	public Long getPaperPrOutMatId() {
		return paperPrOutMatId;
	}

	public void setPaperPrOutMatId(Long paperPrOutMatId) {
		this.paperPrOutMatId = paperPrOutMatId;
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

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public BigDecimal getOutputMaterialQty() {
		return outputMaterialQty;
	}

	public void setOutputMaterialQty(BigDecimal outputMaterialQty) {
		this.outputMaterialQty = outputMaterialQty;
	}

	public BigDecimal getDailyAccountQty() {
		return dailyAccountQty;
	}

	public void setDailyAccountQty(BigDecimal dailyAccountQty) {
		this.dailyAccountQty = dailyAccountQty;
	}

	public BigDecimal getMonthStatementQty() {
		return monthStatementQty;
	}

	public void setMonthStatementQty(BigDecimal monthStatementQty) {
		this.monthStatementQty = monthStatementQty;
	}

	public BigDecimal getExternalDataQty() {
		return externalDataQty;
	}

	public void setExternalDataQty(BigDecimal externalDataQty) {
		this.externalDataQty = externalDataQty;
	}

	public BigDecimal getMaxDiffQty() {
		return maxDiffQty;
	}

	public void setMaxDiffQty(BigDecimal maxDiffQty) {
		this.maxDiffQty = maxDiffQty;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
