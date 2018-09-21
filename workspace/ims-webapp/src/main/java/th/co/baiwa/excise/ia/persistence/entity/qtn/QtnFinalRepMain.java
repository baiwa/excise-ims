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
@Table(name = "IA_QTN_FINAL_REP_MAIN")
public class QtnFinalRepMain extends BaseEntity {

	private static final long serialVersionUID = 7172079055714711123L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QTN_FINAL_REP_MAIN_GEN")
	@SequenceGenerator(name = "IA_QTN_FINAL_REP_MAIN_GEN", sequenceName = "IA_QTN_FINAL_REP_MAN_SEQ", allocationSize = 1)
	@Column(name = "QTN_FINAL_REP_MAN_ID")
	private Long qtnFinalRepManId;
	
	@Column(name = "QTN_FINAL_REP_HDR_ID")
	private Long qtnFinalRepHdrId;
	
	@Column(name = "QTN_REPORT_MAN_ID")
	private Long qtnReportManId;
	
	@Column(name = "QTN_POINT")
	private String qtnPoint;
	
	@Column(name = "QTN_CREATOR")
	private String qtnCreator;

	public Long getQtnFinalRepManId() {
		return qtnFinalRepManId;
	}

	public void setQtnFinalRepManId(Long qtnFinalRepManId) {
		this.qtnFinalRepManId = qtnFinalRepManId;
	}

	public Long getQtnFinalRepHdrId() {
		return qtnFinalRepHdrId;
	}

	public void setQtnFinalRepHdrId(Long qtnFinalRepHdrId) {
		this.qtnFinalRepHdrId = qtnFinalRepHdrId;
	}

	public Long getQtnReportManId() {
		return qtnReportManId;
	}

	public void setQtnReportManId(Long qtnReportManId) {
		this.qtnReportManId = qtnReportManId;
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