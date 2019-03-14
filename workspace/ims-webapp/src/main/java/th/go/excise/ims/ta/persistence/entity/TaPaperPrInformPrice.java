
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
@Table(name = "TA_PAPER_PR_INFORM_PRICE")
public class TaPaperPrInformPrice extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2932703172926030853L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR_INFORM_PRICE_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR_INFORM_PRICE_GEN", sequenceName = "TA_PAPER_PR_INFORM_PRICE_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR_INFORM_PRICE_ID")
	private Long paperPrInformPriceId;
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
	@Column(name = "INFORM_PRICE")
	private BigDecimal informPrice;
	@Column(name = "EXTERNAL_PRICE")
	private BigDecimal externalPrice;
	@Column(name = "DECLARE_PRICE")
	private BigDecimal declarePrice;
	@Column(name = "RETAIL_PRICE")
	private BigDecimal retailPrice;
	@Column(name = "TAX_PRICE")
	private BigDecimal taxPrice;
	@Column(name = "DIFF_PRICE")
	private BigDecimal diffPrice;

	public Long getPaperPrInformPriceId() {
		return paperPrInformPriceId;
	}

	public void setPaperPrInformPriceId(Long paperPrInformPriceId) {
		this.paperPrInformPriceId = paperPrInformPriceId;
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

	public BigDecimal getInformPrice() {
		return informPrice;
	}

	public void setInformPrice(BigDecimal informPrice) {
		this.informPrice = informPrice;
	}

	public BigDecimal getExternalPrice() {
		return externalPrice;
	}

	public void setExternalPrice(BigDecimal externalPrice) {
		this.externalPrice = externalPrice;
	}

	public BigDecimal getDeclarePrice() {
		return declarePrice;
	}

	public void setDeclarePrice(BigDecimal declarePrice) {
		this.declarePrice = declarePrice;
	}

	public BigDecimal getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(BigDecimal retailPrice) {
		this.retailPrice = retailPrice;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getDiffPrice() {
		return diffPrice;
	}

	public void setDiffPrice(BigDecimal diffPrice) {
		this.diffPrice = diffPrice;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
