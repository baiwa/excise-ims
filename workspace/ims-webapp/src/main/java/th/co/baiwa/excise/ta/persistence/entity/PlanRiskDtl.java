
package th.co.baiwa.excise.ta.persistence.entity;

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
@Table(name = "TA_PLAN_RISK_DTL")
public class PlanRiskDtl extends BaseEntity {

	
	private static final long serialVersionUID = 4536582239238087012L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_PLAN_RISK_DTL_GEN")
	@SequenceGenerator(name = "TA_PLAN_RISK_DTL_GEN", sequenceName = "TA_PLAN_RISK_DTL_SEQ", allocationSize = 1)
	@Column(name = "TA_PLAN_RISK_DTL_ID")
	private BigDecimal taPlanRiskDtlId;
	
	@Column(name = "WORK_SHEET_HEADER_ID")
	private BigDecimal workSheetHeaderId;
	
	@Column(name = "RISK_TYPE")
	private String riskType;
	
	@Column(name = "RISK_DTL")
	private String riskDtl;
	
	@Column(name = "MONTH")
	private String month;
	
	@Column(name = "AMOUNT")
	private String amount;
	
	public PlanRiskDtl(BigDecimal workSheetHeaderId, String riskType, String riskDtl) {
		this.riskType = riskType;
		this.workSheetHeaderId = workSheetHeaderId;
		this.riskDtl = riskDtl;
	}
	public PlanRiskDtl() {
		
	}

	public BigDecimal getTaPlanRiskDtlId() {
		return taPlanRiskDtlId;
	}

	public void setTaPlanRiskDtlId(BigDecimal taPlanRiskDtlId) {
		this.taPlanRiskDtlId = taPlanRiskDtlId;
	}

	public BigDecimal getWorkSheetHeaderId() {
		return workSheetHeaderId;
	}

	public void setWorkSheetHeaderId(BigDecimal workSheetHeaderId) {
		this.workSheetHeaderId = workSheetHeaderId;
	}

	public String getRiskType() {
		return riskType;
	}

	public void setRiskType(String riskType) {
		this.riskType = riskType;
	}

	public String getRiskDtl() {
		return riskDtl;
	}

	public void setRiskDtl(String riskDtl) {
		this.riskDtl = riskDtl;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}

}
