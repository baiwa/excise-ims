
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
@Table(name = "TA_PAPER_PR_RED_TAX")
public class TaPaperPrRedTax extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4578154021356804665L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR_RED_TAX_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR_RED_TAX_GEN", sequenceName = "TA_PAPER_PR_RED_TAX_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR_RED_TAX_ID")
	private BigDecimal paperPrRedTaxId;
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
	@Column(name = "TAX_REDUCE_AMT")
	private BigDecimal taxReduceAmt;
	@Column(name = "TAX_REDUCE_QTY")
	private BigDecimal taxReduceQty;
	@Column(name = "TAX_REDUCE_PER_UNIT_AMT")
	private BigDecimal taxReducePerUnitAmt;
	@Column(name = "BILL_NO")
	private BigDecimal billNo;
	@Column(name = "BILL_TAX_AMT")
	private BigDecimal billTaxAmt;
	@Column(name = "BILL_TAX_QTY")
	private BigDecimal billTaxQty;
	@Column(name = "BILL_TAX_PER_UNIT")
	private BigDecimal billTaxPerUnit;
	@Column(name = "DIFF_TAX_REDUCE_AMT")
	private BigDecimal diffTaxReduceAmt;

	public BigDecimal getPaperPrRedTaxId() {
		return paperPrRedTaxId;
	}

	public void setPaperPrRedTaxId(BigDecimal paperPrRedTaxId) {
		this.paperPrRedTaxId = paperPrRedTaxId;
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

	public BigDecimal getTaxReduceAmt() {
		return taxReduceAmt;
	}

	public void setTaxReduceAmt(BigDecimal taxReduceAmt) {
		this.taxReduceAmt = taxReduceAmt;
	}

	public BigDecimal getTaxReduceQty() {
		return taxReduceQty;
	}

	public void setTaxReduceQty(BigDecimal taxReduceQty) {
		this.taxReduceQty = taxReduceQty;
	}

	public BigDecimal getTaxReducePerUnitAmt() {
		return taxReducePerUnitAmt;
	}

	public void setTaxReducePerUnitAmt(BigDecimal taxReducePerUnitAmt) {
		this.taxReducePerUnitAmt = taxReducePerUnitAmt;
	}

	public BigDecimal getBillNo() {
		return billNo;
	}

	public void setBillNo(BigDecimal billNo) {
		this.billNo = billNo;
	}

	public BigDecimal getBillTaxAmt() {
		return billTaxAmt;
	}

	public void setBillTaxAmt(BigDecimal billTaxAmt) {
		this.billTaxAmt = billTaxAmt;
	}

	public BigDecimal getBillTaxQty() {
		return billTaxQty;
	}

	public void setBillTaxQty(BigDecimal billTaxQty) {
		this.billTaxQty = billTaxQty;
	}

	public BigDecimal getBillTaxPerUnit() {
		return billTaxPerUnit;
	}

	public void setBillTaxPerUnit(BigDecimal billTaxPerUnit) {
		this.billTaxPerUnit = billTaxPerUnit;
	}

	public BigDecimal getDiffTaxReduceAmt() {
		return diffTaxReduceAmt;
	}

	public void setDiffTaxReduceAmt(BigDecimal diffTaxReduceAmt) {
		this.diffTaxReduceAmt = diffTaxReduceAmt;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
