package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_DRAFT_WORKSHEET_HDR")
public class TaDraftWorksheetHdr extends BaseEntity {

	private static final long serialVersionUID = 9124745681603965177L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_DRAFT_WORKSHEET_HDR_GEN")
	@SequenceGenerator(name = "TA_DRAFT_WORKSHEET_HDR_GEN", sequenceName = "TA_DRAFT_WORKSHEET_HDR_SEQ", allocationSize = 1)
	@Column(name = "TA_DRAFT_WORKSHEET_HDR_ID")
	private BigDecimal taDraftWorksheetHdrId;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "YEAR_MONTH_START")
	private String yearMonthStart;
	@Column(name = "YEAR_MONTH_END")
	private String yearMonthEnd;

	public BigDecimal getTaDraftWorksheetHdrId() {
		return taDraftWorksheetHdrId;
	}

	public void setTaDraftWorksheetHdrId(BigDecimal taDraftWorksheetHdrId) {
		this.taDraftWorksheetHdrId = taDraftWorksheetHdrId;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getYearMonthStart() {
		return yearMonthStart;
	}

	public void setYearMonthStart(String yearMonthStart) {
		this.yearMonthStart = yearMonthStart;
	}

	public String getYearMonthEnd() {
		return yearMonthEnd;
	}

	public void setYearMonthEnd(String yearMonthEnd) {
		this.yearMonthEnd = yearMonthEnd;
	}

}
