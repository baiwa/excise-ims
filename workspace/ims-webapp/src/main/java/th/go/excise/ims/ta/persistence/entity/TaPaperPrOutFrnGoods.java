
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
@Table(name = "TA_PAPER_PR_OUT_FRN_GOODS")
public class TaPaperPrOutFrnGoods extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7136837359221058756L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR_OUT_FRN_GOODS_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR_OUT_FRN_GOODS_GEN", sequenceName = "TA_PAPER_PR_OUT_FRN_GOODS_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR_OUT_FRN_GOODS_ID")
	private Long paperPrOutFrnGoodsId;
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
	@Column(name = "CARGO_DOC_NO")
	private BigDecimal cargoDocNo;
	@Column(name = "INVOICE_NO")
	private String invoiceNo;
	@Column(name = "OUTPUT_DAILY_ACCOUNT_QTY")
	private BigDecimal outputDailyAccountQty;
	@Column(name = "OUTPUT_MONTH_STATEMENT_QTY")
	private BigDecimal outputMonthStatementQty;
	@Column(name = "OUTPUT_AUDIT_QTY")
	private BigDecimal outputAuditQty;
	@Column(name = "TAX_REDUCE_QTY")
	private BigDecimal taxReduceQty;
	@Column(name = "DIFF_OUTPUT_QTY")
	private BigDecimal diffOutputQty;

	public Long getPaperPrOutFrnGoodsId() {
		return paperPrOutFrnGoodsId;
	}

	public void setPaperPrOutFrnGoodsId(Long paperPrOutFrnGoodsId) {
		this.paperPrOutFrnGoodsId = paperPrOutFrnGoodsId;
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

	public BigDecimal getCargoDocNo() {
		return cargoDocNo;
	}

	public void setCargoDocNo(BigDecimal cargoDocNo) {
		this.cargoDocNo = cargoDocNo;
	}

	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
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

	public BigDecimal getOutputAuditQty() {
		return outputAuditQty;
	}

	public void setOutputAuditQty(BigDecimal outputAuditQty) {
		this.outputAuditQty = outputAuditQty;
	}

	public BigDecimal getTaxReduceQty() {
		return taxReduceQty;
	}

	public void setTaxReduceQty(BigDecimal taxReduceQty) {
		this.taxReduceQty = taxReduceQty;
	}

	public BigDecimal getDiffOutputQty() {
		return diffOutputQty;
	}

	public void setDiffOutputQty(BigDecimal diffOutputQty) {
		this.diffOutputQty = diffOutputQty;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
