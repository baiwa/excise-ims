package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


@Entity
@Table(name = "IA_RISK_ASS_RISK_WS_HDR")
public class RiskAssRiskWsHdr extends BaseEntity{
	
	
	private static final long serialVersionUID = 7005171568952161795L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_ASS_RISK_WS_HDR_GEN")
	@SequenceGenerator(name = "IA_RISK_ASS_RISK_WS_HDR_GEN", sequenceName = "IA_RISK_ASS_RISK_WS_HDR_SEQ", allocationSize = 1)
	@Column(name = "RISK_HRD_ID")
	private Long riskHrdId;
	
	@Column(name = "RISK_HDR_NAME")
	private String riskHdrName;
	
	
	
	public String getRiskHdrName() {
		return riskHdrName;
	}
	public void setRiskHdrName(String riskHdrName) {
		this.riskHdrName = riskHdrName;
	}
	public Long getRiskHrdId() {
		return riskHrdId;
	}
	public void setRiskHrdId(Long riskHrdId) {
		this.riskHrdId = riskHrdId;
	}
	
	
	
}