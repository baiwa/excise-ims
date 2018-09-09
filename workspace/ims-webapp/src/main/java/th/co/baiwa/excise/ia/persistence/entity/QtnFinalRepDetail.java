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
@Table(name = "IA_QTN_FINAL_REP_DETAIL")
public class QtnFinalRepDetail extends BaseEntity {

	private static final long serialVersionUID = 7172079055714711123L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QTN_FINAL_REP_DETAIL_GEN")
	@SequenceGenerator(name = "IA_QTN_FINAL_REP_DETAIL_GEN", sequenceName = "IA_QTN_FINAL_REP_DTL_SEQ", allocationSize = 1)
	@Column(name = "QTN_FINAL_REP_DTL_ID")
	private Long qtnFinalRepDtlId;
	
	@Column(name = "QTN_FINAL_REP_HDR_ID")
	private Long qtnFinalRepHdrId;
	
	@Column(name = "QTN_REPORT_DTL_ID")
	private Long qtnReportDtlId;
	
	@Column(name = "QTN_POINT")
	private String qtnPoint;
	
	@Column(name = "QTN_CREATOR")
	private String qtnCreator;

	public Long getQtnFinalRepDtlId() {
		return qtnFinalRepDtlId;
	}

	public void setQtnFinalRepDtlId(Long qtnFinalRepDtlId) {
		this.qtnFinalRepDtlId = qtnFinalRepDtlId;
	}

	public Long getQtnFinalRepHdrId() {
		return qtnFinalRepHdrId;
	}

	public void setQtnFinalRepHdrId(Long qtnFinalRepHdrId) {
		this.qtnFinalRepHdrId = qtnFinalRepHdrId;
	}

	public Long getQtnReportDtlId() {
		return qtnReportDtlId;
	}

	public void setQtnReportDtlId(Long qtnReportDtlId) {
		this.qtnReportDtlId = qtnReportDtlId;
	}

	public String getQtnPoint() {
		return qtnPoint;
	}

	public void setQtnPoint(String qtnPoint) {
		this.qtnPoint = qtnPoint;
	}

	public String getQtnCreator() {
		return qtnCreator;
	}

	public void setQtnCreator(String qtnCreator) {
		this.qtnCreator = qtnCreator;
	}
	
}