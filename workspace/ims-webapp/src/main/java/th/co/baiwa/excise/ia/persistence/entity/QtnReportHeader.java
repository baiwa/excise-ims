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
@Table(name = "IA_QTN_REPORT_HEADER")
public class QtnReportHeader extends BaseEntity {

	private static final long serialVersionUID = -2919566743738637379L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QTN_REPORT_GEN")
	@SequenceGenerator(name = "IA_QTN_REPORT_GEN", sequenceName = "IA_QTN_REPORT_SEQ", allocationSize = 1)
	@Column(name = "QTN_REPORT_HDR_ID")
	private Long qtnReportHdrId;
	
	@Column(name = "QTN_REPORT_HDR_NAME")
	private String qtnReportHdrName;
	
	@Column(name = "CREATOR")
	private String creator;

	public Long getQtnReportHdrId() {
		return qtnReportHdrId;
	}

	public void setQtnReportHdrId(Long qtnReportHdrId) {
		this.qtnReportHdrId = qtnReportHdrId;
	}

	public String getQtnReportHdrName() {
		return qtnReportHdrName;
	}

	public void setQtnReportHdrName(String qtnReportHdrName) {
		this.qtnReportHdrName = qtnReportHdrName;
	}

	public String getCreator() {
		return creator;
	}

	public void setCreator(String creator) {
		this.creator = creator;
	}
}
