package th.co.baiwa.excise.ta.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "TA_TAX_REDUCE_WS_HDR")
public class TaxReduceWsHdr extends BaseEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5892318605230547836L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "TA_TAX_REDUCE_WS_HDR_GEN")
	@SequenceGenerator(name = "TA_TAX_REDUCE_WS_HDR_GEN", sequenceName = "TA_TAX_REDUCE_WS_HDR_SEQ", allocationSize = 1)

	@Column(name = "TA_TAX_REDUCE_WS_HDR_ID")
	private Long taTaxReduceWsHdrId;

	@Column(name = "EXCISE_ID")
	private String exciseId;

	@Column(name = "TA_ANALYSIS_ID")
	private String taAnalysisId;

	@Column(name = "TAXATION_ID")
	private String taxationId;

	@Column(name = "START_DATE")
	private String startDate;

	@Column(name = "END_DATE")
	private String endDate;

	@Column(name = "PDT_TYPE")
	private String pdtType;

	@Column(name = "SUB_PDT_TYPE")
	private String subPdtType;

	public Long getTaTaxReduceWsHdrId() {
		return taTaxReduceWsHdrId;
	}

	public void setTaTaxReduceWsHdrId(Long taTaxReduceWsHdrId) {
		this.taTaxReduceWsHdrId = taTaxReduceWsHdrId;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getTaAnalysisId() {
		return taAnalysisId;
	}

	public void setTaAnalysisId(String taAnalysisId) {
		this.taAnalysisId = taAnalysisId;
	}

	public String getTaxationId() {
		return taxationId;
	}

	public void setTaxationId(String taxationId) {
		this.taxationId = taxationId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getPdtType() {
		return pdtType;
	}

	public void setPdtType(String pdtType) {
		this.pdtType = pdtType;
	}

	public String getSubPdtType() {
		return subPdtType;
	}

	public void setSubPdtType(String subPdtType) {
		this.subPdtType = subPdtType;
	}

}
