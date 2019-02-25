package th.go.excise.ims.ta.persistence.entity;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

import javax.persistence.*;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

@Entity
@Table(name = "TA_DRAFT_WORKSHEET_DTL")
public class TaDraftWorksheetDtl extends BaseEntity {

	private static final long serialVersionUID = 1299545505531306595L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_DRAFT_WORKSHEET_GEN")
	@SequenceGenerator(name = "TA_DRAFT_WORKSHEET_GEN", sequenceName = "TA_DRAFT_WORKSHEET_DTL_SEQ", allocationSize = 1)
	@Column(name = "DRAFT_WORKSHEET_DTL_ID")
	private Long draftWorksheetDtlId;
	@Column(name = "DRAFT_NUMBER")
	private String draftNumber;
	@Column(name = "NEW_REG_ID")
	private String newRegId;
	@Column(name = "SUM_TAX_AMT_G1")
	private BigDecimal sumTaxAmtG1;
	@Column(name = "SUM_TAX_AMT_G2")
	private BigDecimal sumTaxAmtG2;
	@Column(name = "TAX_AMT_CHN_PNT")
	private BigDecimal taxAmtChnPnt;
	@Column(name = "TAX_AMT_SD")
	private BigDecimal taxAmtSd;
	@Column(name = "TAX_MONTH_NO")
	private Integer taxMonthNo;
	@Column(name = "TAX_AUDIT_LAST3")
	private String taxAuditLast3;
	@Column(name = "TAX_AUDIT_LAST2")
	private String taxAuditLast2;
	@Column(name = "TAX_AUDIT_LAST1")
	private String taxAuditLast1;
	@Column(name = "TAX_AMT_MEAN")
	private BigDecimal taxAmtMean;
	@Column(name = "TAX_AMT_MAX_PNT")
	private BigDecimal taxAmtMaxPnt;
	@Column(name = "TAX_AMT_MIN_PNT")
	private BigDecimal taxAmtMinPnt;
	@Column(name = "TAX_AMT_G1_M1")
	private String taxAmtG1M1;
	@Column(name = "TAX_AMT_G1_M2")
	private String taxAmtG1M2;
	@Column(name = "TAX_AMT_G1_M3")
	private String taxAmtG1M3;
	@Column(name = "TAX_AMT_G1_M4")
	private String taxAmtG1M4;
	@Column(name = "TAX_AMT_G1_M5")
	private String taxAmtG1M5;
	@Column(name = "TAX_AMT_G1_M6")
	private String taxAmtG1M6;
	@Column(name = "TAX_AMT_G1_M7")
	private String taxAmtG1M7;
	@Column(name = "TAX_AMT_G1_M8")
	private String taxAmtG1M8;
	@Column(name = "TAX_AMT_G1_M9")
	private String taxAmtG1M9;
	@Column(name = "TAX_AMT_G1_M10")
	private String taxAmtG1M10;
	@Column(name = "TAX_AMT_G1_M11")
	private String taxAmtG1M11;
	@Column(name = "TAX_AMT_G1_M12")
	private String taxAmtG1M12;
	@Column(name = "TAX_AMT_G2_M1")
	private String taxAmtG2M1;
	@Column(name = "TAX_AMT_G2_M2")
	private String taxAmtG2M2;
	@Column(name = "TAX_AMT_G2_M3")
	private String taxAmtG2M3;
	@Column(name = "TAX_AMT_G2_M4")
	private String taxAmtG2M4;
	@Column(name = "TAX_AMT_G2_M5")
	private String taxAmtG2M5;
	@Column(name = "TAX_AMT_G2_M6")
	private String taxAmtG2M6;
	@Column(name = "TAX_AMT_G2_M7")
	private String taxAmtG2M7;
	@Column(name = "TAX_AMT_G2_M8")
	private String taxAmtG2M8;
	@Column(name = "TAX_AMT_G2_M9")
	private String taxAmtG2M9;
	@Column(name = "TAX_AMT_G2_M10")
	private String taxAmtG2M10;
	@Column(name = "TAX_AMT_G2_M11")
	private String taxAmtG2M11;
	@Column(name = "TAX_AMT_G2_M12")
	private String taxAmtG2M12;

	public Long getDraftWorksheetDtlId() {
		return draftWorksheetDtlId;
	}

	public void setDraftWorksheetDtlId(Long draftWorksheetDtlId) {
		this.draftWorksheetDtlId = draftWorksheetDtlId;
	}

	public String getDraftNumber() {
		return draftNumber;
	}

	public void setDraftNumber(String draftNumber) {
		this.draftNumber = draftNumber;
	}

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public BigDecimal getSumTaxAmtG1() {
		return sumTaxAmtG1;
	}

	public void setSumTaxAmtG1(BigDecimal sumTaxAmtG1) {
		this.sumTaxAmtG1 = sumTaxAmtG1;
	}

	public BigDecimal getSumTaxAmtG2() {
		return sumTaxAmtG2;
	}

	public void setSumTaxAmtG2(BigDecimal sumTaxAmtG2) {
		this.sumTaxAmtG2 = sumTaxAmtG2;
	}

	public BigDecimal getTaxAmtChnPnt() {
		return taxAmtChnPnt;
	}

	public void setTaxAmtChnPnt(BigDecimal taxAmtChnPnt) {
		this.taxAmtChnPnt = taxAmtChnPnt;
	}

	public BigDecimal getTaxAmtSd() {
		return taxAmtSd;
	}

	public void setTaxAmtSd(BigDecimal taxAmtSd) {
		this.taxAmtSd = taxAmtSd;
	}

	public Integer getTaxMonthNo() {
		return taxMonthNo;
	}

	public void setTaxMonthNo(Integer taxMonthNo) {
		this.taxMonthNo = taxMonthNo;
	}

	public String getTaxAuditLast3() {
		return taxAuditLast3;
	}

	public void setTaxAuditLast3(String taxAuditLast3) {
		this.taxAuditLast3 = taxAuditLast3;
	}

	public String getTaxAuditLast2() {
		return taxAuditLast2;
	}

	public void setTaxAuditLast2(String taxAuditLast2) {
		this.taxAuditLast2 = taxAuditLast2;
	}

	public String getTaxAuditLast1() {
		return taxAuditLast1;
	}

	public void setTaxAuditLast1(String taxAuditLast1) {
		this.taxAuditLast1 = taxAuditLast1;
	}

	public BigDecimal getTaxAmtMean() {
		return taxAmtMean;
	}

	public void setTaxAmtMean(BigDecimal taxAmtMean) {
		this.taxAmtMean = taxAmtMean;
	}

	public BigDecimal getTaxAmtMaxPnt() {
		return taxAmtMaxPnt;
	}

	public void setTaxAmtMaxPnt(BigDecimal taxAmtMaxPnt) {
		this.taxAmtMaxPnt = taxAmtMaxPnt;
	}

	public BigDecimal getTaxAmtMinPnt() {
		return taxAmtMinPnt;
	}

	public void setTaxAmtMinPnt(BigDecimal taxAmtMinPnt) {
		this.taxAmtMinPnt = taxAmtMinPnt;
	}

	public String getTaxAmtG1M1() {
		return taxAmtG1M1;
	}

	public void setTaxAmtG1M1(String taxAmtG1M1) {
		this.taxAmtG1M1 = taxAmtG1M1;
	}

	public String getTaxAmtG1M2() {
		return taxAmtG1M2;
	}

	public void setTaxAmtG1M2(String taxAmtG1M2) {
		this.taxAmtG1M2 = taxAmtG1M2;
	}

	public String getTaxAmtG1M3() {
		return taxAmtG1M3;
	}

	public void setTaxAmtG1M3(String taxAmtG1M3) {
		this.taxAmtG1M3 = taxAmtG1M3;
	}

	public String getTaxAmtG1M4() {
		return taxAmtG1M4;
	}

	public void setTaxAmtG1M4(String taxAmtG1M4) {
		this.taxAmtG1M4 = taxAmtG1M4;
	}

	public String getTaxAmtG1M5() {
		return taxAmtG1M5;
	}

	public void setTaxAmtG1M5(String taxAmtG1M5) {
		this.taxAmtG1M5 = taxAmtG1M5;
	}

	public String getTaxAmtG1M6() {
		return taxAmtG1M6;
	}

	public void setTaxAmtG1M6(String taxAmtG1M6) {
		this.taxAmtG1M6 = taxAmtG1M6;
	}

	public String getTaxAmtG1M7() {
		return taxAmtG1M7;
	}

	public void setTaxAmtG1M7(String taxAmtG1M7) {
		this.taxAmtG1M7 = taxAmtG1M7;
	}

	public String getTaxAmtG1M8() {
		return taxAmtG1M8;
	}

	public void setTaxAmtG1M8(String taxAmtG1M8) {
		this.taxAmtG1M8 = taxAmtG1M8;
	}

	public String getTaxAmtG1M9() {
		return taxAmtG1M9;
	}

	public void setTaxAmtG1M9(String taxAmtG1M9) {
		this.taxAmtG1M9 = taxAmtG1M9;
	}

	public String getTaxAmtG1M10() {
		return taxAmtG1M10;
	}

	public void setTaxAmtG1M10(String taxAmtG1M10) {
		this.taxAmtG1M10 = taxAmtG1M10;
	}

	public String getTaxAmtG1M11() {
		return taxAmtG1M11;
	}

	public void setTaxAmtG1M11(String taxAmtG1M11) {
		this.taxAmtG1M11 = taxAmtG1M11;
	}

	public String getTaxAmtG1M12() {
		return taxAmtG1M12;
	}

	public void setTaxAmtG1M12(String taxAmtG1M12) {
		this.taxAmtG1M12 = taxAmtG1M12;
	}

	public String getTaxAmtG2M1() {
		return taxAmtG2M1;
	}

	public void setTaxAmtG2M1(String taxAmtG2M1) {
		this.taxAmtG2M1 = taxAmtG2M1;
	}

	public String getTaxAmtG2M2() {
		return taxAmtG2M2;
	}

	public void setTaxAmtG2M2(String taxAmtG2M2) {
		this.taxAmtG2M2 = taxAmtG2M2;
	}

	public String getTaxAmtG2M3() {
		return taxAmtG2M3;
	}

	public void setTaxAmtG2M3(String taxAmtG2M3) {
		this.taxAmtG2M3 = taxAmtG2M3;
	}

	public String getTaxAmtG2M4() {
		return taxAmtG2M4;
	}

	public void setTaxAmtG2M4(String taxAmtG2M4) {
		this.taxAmtG2M4 = taxAmtG2M4;
	}

	public String getTaxAmtG2M5() {
		return taxAmtG2M5;
	}

	public void setTaxAmtG2M5(String taxAmtG2M5) {
		this.taxAmtG2M5 = taxAmtG2M5;
	}

	public String getTaxAmtG2M6() {
		return taxAmtG2M6;
	}

	public void setTaxAmtG2M6(String taxAmtG2M6) {
		this.taxAmtG2M6 = taxAmtG2M6;
	}

	public String getTaxAmtG2M7() {
		return taxAmtG2M7;
	}

	public void setTaxAmtG2M7(String taxAmtG2M7) {
		this.taxAmtG2M7 = taxAmtG2M7;
	}

	public String getTaxAmtG2M8() {
		return taxAmtG2M8;
	}

	public void setTaxAmtG2M8(String taxAmtG2M8) {
		this.taxAmtG2M8 = taxAmtG2M8;
	}

	public String getTaxAmtG2M9() {
		return taxAmtG2M9;
	}

	public void setTaxAmtG2M9(String taxAmtG2M9) {
		this.taxAmtG2M9 = taxAmtG2M9;
	}

	public String getTaxAmtG2M10() {
		return taxAmtG2M10;
	}

	public void setTaxAmtG2M10(String taxAmtG2M10) {
		this.taxAmtG2M10 = taxAmtG2M10;
	}

	public String getTaxAmtG2M11() {
		return taxAmtG2M11;
	}

	public void setTaxAmtG2M11(String taxAmtG2M11) {
		this.taxAmtG2M11 = taxAmtG2M11;
	}

	public String getTaxAmtG2M12() {
		return taxAmtG2M12;
	}

	public void setTaxAmtG2M12(String taxAmtG2M12) {
		this.taxAmtG2M12 = taxAmtG2M12;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
