package th.co.baiwa.excise.ia.persistence.entity.qtn;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_QTN_FINAL_REP_HEADER")
public class QtnFinalRepHeader extends BaseEntity {

	private static final long serialVersionUID = 6237354256881472464L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QTN_FINAL_REP_HEADER_GEN")
	@SequenceGenerator(name = "IA_QTN_FINAL_REP_HEADER_GEN", sequenceName = "IA_QTN_FINAL_REP_HDR_SEQ", allocationSize = 1)
	@Column(name = "QTN_FINAL_REP_HDR_ID")
	private Long qtnFinalRepHdrId;
	
	@Column(name = "QTN_REPORT_HDR_ID")
	private Long qtnReportHdrId;
	
	@Column(name = "QTN_CREATOR")
	private String qtnCreator;
	
	@Column(name = "QTN_CONCLUSION")
	private String qtnConclusion;
	
	@Column(name = "QTN_FINISHED")
	private String qtnFinished;

	public String getQtnFinished() {
		return qtnFinished;
	}

	public void setQtnFinished(String qtnFinished) {
		this.qtnFinished = qtnFinished;
	}

	public Long getQtnFinalRepHdrId() {
		return qtnFinalRepHdrId;
	}

	public void setQtnFinalRepHdrId(Long qtnFinalRepHdrId) {
		this.qtnFinalRepHdrId = qtnFinalRepHdrId;
	}

	public Long getQtnReportHdrId() {
		return qtnReportHdrId;
	}

	public void setQtnReportHdrId(Long qtnReportHdrId) {
		this.qtnReportHdrId = qtnReportHdrId;
	}

	public String getQtnCreator() {
		return qtnCreator;
	}

	public void setQtnCreator(String qtnCreator) {
		this.qtnCreator = qtnCreator;
	}

	public String getQtnConclusion() {
		return qtnConclusion;
	}

	public void setQtnConclusion(String qtnConclusion) {
		this.qtnConclusion = qtnConclusion;
	}
	
}
