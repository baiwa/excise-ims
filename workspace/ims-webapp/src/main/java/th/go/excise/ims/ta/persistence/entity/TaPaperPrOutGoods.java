
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
@Table(name = "TA_PAPER_PR_OUT_GOODS")
public class TaPaperPrOutGoods extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5260150319212594372L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR_OUT_GOODS_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR_OUT_GOODS_GEN", sequenceName = "TA_PAPER_PR_OUT_GOODS_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR_OUT_GOODS_ID")
	private Long paperPrOutGoodsId;
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
	@Column(name = "OUTPUT_GOODS_QTY")
	private BigDecimal outputGoodsQty;
	@Column(name = "OUTPUT_DAILY_ACCOUNT_QTY")
	private BigDecimal outputDailyAccountQty;
	@Column(name = "OUTPUT_MONTH_STATEMENT_QTY")
	private BigDecimal outputMonthStatementQty;
	@Column(name = "AUDIT_QTY")
	private BigDecimal auditQty;
	@Column(name = "TAX_GOODS_QTY")
	private BigDecimal taxGoodsQty;
	@Column(name = "DIFF_QTY")
	private BigDecimal diffQty;

	public Long getPaperPrOutGoodsId() {
		return paperPrOutGoodsId;
	}

	public void setPaperPrOutGoodsId(Long paperPrOutGoodsId) {
		this.paperPrOutGoodsId = paperPrOutGoodsId;
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

	public BigDecimal getOutputGoodsQty() {
		return outputGoodsQty;
	}

	public void setOutputGoodsQty(BigDecimal outputGoodsQty) {
		this.outputGoodsQty = outputGoodsQty;
	}

	public BigDecimal getOutputDailyAccountQty() {
		return outputDailyAccountQty;
	}

	public void setOutputDailyAccountQty(BigDecimal outputDailyAccountQty) {
		this.outputDailyAccountQty = outputDailyAccountQty;
	}

	public BigDecimal getOutputMonthStatementQty() {
		return outputMonthStatementQty;
	}

	public void setOutputMonthStatementQty(BigDecimal outputMonthStatementQty) {
		this.outputMonthStatementQty = outputMonthStatementQty;
	}

	public BigDecimal getAuditQty() {
		return auditQty;
	}

	public void setAuditQty(BigDecimal auditQty) {
		this.auditQty = auditQty;
	}

	public BigDecimal getTaxGoodsQty() {
		return taxGoodsQty;
	}

	public void setTaxGoodsQty(BigDecimal taxGoodsQty) {
		this.taxGoodsQty = taxGoodsQty;
	}

	public BigDecimal getDiffQty() {
		return diffQty;
	}

	public void setDiffQty(BigDecimal diffQty) {
		this.diffQty = diffQty;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
