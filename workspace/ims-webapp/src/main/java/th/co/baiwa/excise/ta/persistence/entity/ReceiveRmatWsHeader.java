package th.co.baiwa.excise.ta.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the TA_RECEIVE_RMAT_WS_HEADER database table.
 * 
 */
@Entity
@Table(name="TA_RECEIVE_RMAT_WS_HEADER")
public class ReceiveRmatWsHeader extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3168569609847439736L;

	@Id
	@SequenceGenerator(name="TA_RECEIVE_RMAT_WS_HEADER_GEN", sequenceName="TA_RECEIVE_RMAT_WS_HEADER_SEQ")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="TA_RECEIVE_RMAT_WS_HEADER_GEN")
	@Column(name="TA_RECEIVE_RMAT_HEADER_ID")
	private long taReceiveRmatHeaderId;

	@Column(name="END_DATE")
	private String endDate;

	@Column(name="EXCISE_ID")
	private String exciseId;

	@Column(name="PDT_TYPE")
	private String pdtType;

	@Column(name="START_DATE")
	private String startDate;

	@Column(name="SUB_PDT_TYPE")
	private String subPdtType;

	@Column(name="TA_ANALYSIS_ID")
	private String taAnalysisId;

	@Column(name="TAX_PLAN_ID")
	private BigDecimal taxPlanId;

	@Column(name="TAXATION_ID")
	private String taxationId;

	public long getTaReceiveRmatHeaderId() {
		return taReceiveRmatHeaderId;
	}

	public void setTaReceiveRmatHeaderId(long taReceiveRmatHeaderId) {
		this.taReceiveRmatHeaderId = taReceiveRmatHeaderId;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getPdtType() {
		return pdtType;
	}

	public void setPdtType(String pdtType) {
		this.pdtType = pdtType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getSubPdtType() {
		return subPdtType;
	}

	public void setSubPdtType(String subPdtType) {
		this.subPdtType = subPdtType;
	}

	public String getTaAnalysisId() {
		return taAnalysisId;
	}

	public void setTaAnalysisId(String taAnalysisId) {
		this.taAnalysisId = taAnalysisId;
	}

	public BigDecimal getTaxPlanId() {
		return taxPlanId;
	}

	public void setTaxPlanId(BigDecimal taxPlanId) {
		this.taxPlanId = taxPlanId;
	}

	public String getTaxationId() {
		return taxationId;
	}

	public void setTaxationId(String taxationId) {
		this.taxationId = taxationId;
	}
	
}