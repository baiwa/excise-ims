
package th.go.excise.ims.ta.persistence.entity;

import java.math.BigDecimal;
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
@Table(name = "TA_WORKSHEET_COND_MAIN_HDR")
public class TaWorksheetCondMainHdr extends BaseEntity {

	private static final long serialVersionUID = 2602780889778602147L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_WORKSHEET_COND_MAIN_HDR_GEN")
	@SequenceGenerator(name = "TA_WORKSHEET_COND_MAIN_HDR_GEN", sequenceName = "TA_WORKSHEET_COND_MAIN_HDR_SEQ", allocationSize = 1)
	@Column(name = "WORKSHEET_COND_MAIN_HDR_ID")
	private Long worksheetCondMainHdrId;
	@Column(name = "ANALYSIS_NUMBER")
	private String analysisNumber;
	@Column(name = "MONTH_NUM")
	private BigDecimal monthNum;
	@Column(name = "YEAR_MONTH_START")
	private String yearMonthStart;
	@Column(name = "YEAR_MONTH_END")
	private String yearMonthEnd;

	public Long getWorksheetCondMainHdrId() {
		return worksheetCondMainHdrId;
	}

	public void setWorksheetCondMainHdrId(Long worksheetCondMainHdrId) {
		this.worksheetCondMainHdrId = worksheetCondMainHdrId;
	}

	public String getAnalysisNumber() {
		return analysisNumber;
	}

	public void setAnalysisNumber(String analysisNumber) {
		this.analysisNumber = analysisNumber;
	}

	public BigDecimal getMonthNum() {
		return monthNum;
	}

	public void setMonthNum(BigDecimal monthNum) {
		this.monthNum = monthNum;
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

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
