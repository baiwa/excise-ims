
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
@Table(name = "TA_PAPER_PR_REL_PRD_GOODS")
public class TaPaperPrRelPrdGoods extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -642189328908131402L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PAPER_PR_REL_PRD_GOODS_GEN")
	@SequenceGenerator(name = "TA_PAPER_PR_REL_PRD_GOODS_GEN", sequenceName = "TA_PAPER_PR_REL_PRD_GOODS_SEQ", allocationSize = 1)
	@Column(name = "PAPER_PR_REL_PRD_GOODS_ID")
	private Long paperPrRelPrdGoodsId;
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
	@Column(name = "DOC_NO")
	private String docNo;
	@Column(name = "MATERIAL_DESC")
	private String materialDesc;
	@Column(name = "INPUT_MATERIAL_QTY")
	private BigDecimal inputMaterialQty;
	@Column(name = "FORMULA_MATERIAL_QTY")
	private BigDecimal formulaMaterialQty;
	@Column(name = "USED_MATERIAL_QTY")
	private BigDecimal usedMaterialQty;
	@Column(name = "REAL_USED_MATERIAL_QTY")
	private BigDecimal realUsedMaterialQty;
	@Column(name = "DIFF_MATERIAL_QTY")
	private BigDecimal diffMaterialQty;
	@Column(name = "MATERIAL_QTY")
	private BigDecimal materialQty;
	@Column(name = "GOODS_QTY")
	private BigDecimal goodsQty;
	@Column(name = "DIFF_GOODS_QTY")
	private BigDecimal diffGoodsQty;
	@Column(name = "WASTE_GOODS_PNT")
	private BigDecimal wasteGoodsPnt;
	@Column(name = "WASTE_GOODS_QTY")
	private BigDecimal wasteGoodsQty;
	@Column(name = "BALANCE_GOODS_QTY")
	private BigDecimal balanceGoodsQty;

	public Long getPaperPrRelPrdGoodsId() {
		return paperPrRelPrdGoodsId;
	}

	public void setPaperPrRelPrdGoodsId(Long paperPrRelPrdGoodsId) {
		this.paperPrRelPrdGoodsId = paperPrRelPrdGoodsId;
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

	public String getDocNo() {
		return docNo;
	}

	public void setDocNo(String docNo) {
		this.docNo = docNo;
	}

	public String getMaterialDesc() {
		return materialDesc;
	}

	public void setMaterialDesc(String materialDesc) {
		this.materialDesc = materialDesc;
	}

	public BigDecimal getInputMaterialQty() {
		return inputMaterialQty;
	}

	public void setInputMaterialQty(BigDecimal inputMaterialQty) {
		this.inputMaterialQty = inputMaterialQty;
	}

	public BigDecimal getFormulaMaterialQty() {
		return formulaMaterialQty;
	}

	public void setFormulaMaterialQty(BigDecimal formulaMaterialQty) {
		this.formulaMaterialQty = formulaMaterialQty;
	}

	public BigDecimal getUsedMaterialQty() {
		return usedMaterialQty;
	}

	public void setUsedMaterialQty(BigDecimal usedMaterialQty) {
		this.usedMaterialQty = usedMaterialQty;
	}

	public BigDecimal getRealUsedMaterialQty() {
		return realUsedMaterialQty;
	}

	public void setRealUsedMaterialQty(BigDecimal realUsedMaterialQty) {
		this.realUsedMaterialQty = realUsedMaterialQty;
	}

	public BigDecimal getDiffMaterialQty() {
		return diffMaterialQty;
	}

	public void setDiffMaterialQty(BigDecimal diffMaterialQty) {
		this.diffMaterialQty = diffMaterialQty;
	}

	public BigDecimal getMaterialQty() {
		return materialQty;
	}

	public void setMaterialQty(BigDecimal materialQty) {
		this.materialQty = materialQty;
	}

	public BigDecimal getGoodsQty() {
		return goodsQty;
	}

	public void setGoodsQty(BigDecimal goodsQty) {
		this.goodsQty = goodsQty;
	}

	public BigDecimal getDiffGoodsQty() {
		return diffGoodsQty;
	}

	public void setDiffGoodsQty(BigDecimal diffGoodsQty) {
		this.diffGoodsQty = diffGoodsQty;
	}

	public BigDecimal getWasteGoodsPnt() {
		return wasteGoodsPnt;
	}

	public void setWasteGoodsPnt(BigDecimal wasteGoodsPnt) {
		this.wasteGoodsPnt = wasteGoodsPnt;
	}

	public BigDecimal getWasteGoodsQty() {
		return wasteGoodsQty;
	}

	public void setWasteGoodsQty(BigDecimal wasteGoodsQty) {
		this.wasteGoodsQty = wasteGoodsQty;
	}

	public BigDecimal getBalanceGoodsQty() {
		return balanceGoodsQty;
	}

	public void setBalanceGoodsQty(BigDecimal balanceGoodsQty) {
		this.balanceGoodsQty = balanceGoodsQty;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
