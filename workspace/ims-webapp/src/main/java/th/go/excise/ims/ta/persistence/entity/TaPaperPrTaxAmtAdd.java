
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
@Table(name = "TA_PAPER_PR_TAX_AMT_ADD")
public class TaPaperPrTaxAmtAdd extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2525345060669913300L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR_TAX_AMT_ADD_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR_TAX_AMT_ADD_GEN", sequenceName = "TA_PAPER_PR_TAX_AMT_ADD_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR_TAX_AMT_ADD_ID")
	private BigDecimal paperPrTaxAmtAddId;
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
	@Column(name = "TAX_QTY")
	private BigDecimal taxQty;
	@Column(name = "INFORM_PRICE")
	private BigDecimal informPrice;
	@Column(name = "TAX_VALUE")
	private BigDecimal taxValue;
	@Column(name = "TAX_RATE_BY_VALUE")
	private BigDecimal taxRateByValue;
	@Column(name = "TAX_RATE_BY_QTY")
	private BigDecimal taxRateByQty;
	@Column(name = "TAX_ADDITIONAL")
	private BigDecimal taxAdditional;
	@Column(name = "PENALTY_AMT")
	private BigDecimal penaltyAmt;
	@Column(name = "SURCHARGE_AMT")
	private BigDecimal surchargeAmt;
	@Column(name = "MOI_TAX_AMT")
	private BigDecimal moiTaxAmt;
	@Column(name = "NET_TAX_AMT")
	private BigDecimal netTaxAmt;

	public BigDecimal getPaperPrTaxAmtAddId() {
		return paperPrTaxAmtAddId;
	}

	public void setPaperPrTaxAmtAddId(BigDecimal paperPrTaxAmtAddId) {
		this.paperPrTaxAmtAddId = paperPrTaxAmtAddId;
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

	public BigDecimal getTaxQty() {
		return taxQty;
	}

	public void setTaxQty(BigDecimal taxQty) {
		this.taxQty = taxQty;
	}

	public BigDecimal getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(BigDecimal informPrice) {
		this.informPrice = informPrice;
	}

	public BigDecimal getTaxValue() {
		return taxValue;
	}

	public void setTaxValue(BigDecimal taxValue) {
		this.taxValue = taxValue;
	}

	public BigDecimal getTaxRateByValue() {
		return taxRateByValue;
	}

	public void setTaxRateByValue(BigDecimal taxRateByValue) {
		this.taxRateByValue = taxRateByValue;
	}

	public BigDecimal getTaxRateByQty() {
		return taxRateByQty;
	}

	public void setTaxRateByQty(BigDecimal taxRateByQty) {
		this.taxRateByQty = taxRateByQty;
	}

	public BigDecimal getTaxAdditional() {
		return taxAdditional;
	}

	public void setTaxAdditional(BigDecimal taxAdditional) {
		this.taxAdditional = taxAdditional;
	}

	public BigDecimal getPenaltyAmt() {
		return penaltyAmt;
	}

	public void setPenaltyAmt(BigDecimal penaltyAmt) {
		this.penaltyAmt = penaltyAmt;
	}

	public BigDecimal getSurchargeAmt() {
		return surchargeAmt;
	}

	public void setSurchargeAmt(BigDecimal surchargeAmt) {
		this.surchargeAmt = surchargeAmt;
	}

	public BigDecimal getMoiTaxAmt() {
		return moiTaxAmt;
	}

	public void setMoiTaxAmt(BigDecimal moiTaxAmt) {
		this.moiTaxAmt = moiTaxAmt;
	}

	public BigDecimal getNetTaxAmt() {
		return netTaxAmt;
	}

	public void setNetTaxAmt(BigDecimal netTaxAmt) {
		this.netTaxAmt = netTaxAmt;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
