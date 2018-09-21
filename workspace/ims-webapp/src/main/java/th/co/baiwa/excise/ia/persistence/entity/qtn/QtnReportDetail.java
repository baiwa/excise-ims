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
@Table(name = "IA_QTN_REPORT_DETAIL")
public class QtnReportDetail extends BaseEntity {
	
	private static final long serialVersionUID = -201475679654552089L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QTN_REPORT_GEN")
	@SequenceGenerator(name = "IA_QTN_REPORT_GEN", sequenceName = "IA_QTN_REPORT_DETAIL_SEQ", allocationSize = 1)
	@Column(name = "QTN_REPORT_DTL_ID")
	private Long qtnReportDtlId;
	
	@Column(name = "QTN_REPORT_MAN_ID")
	private Long qtnReportManId;
	
	@Column(name = "QTN_MAIN_DETAIL")
	private String qtnMainDetail;

	public Long getQtnReportDtlId() {
		return qtnReportDtlId;
	}

	public void setQtnReportDtlId(Long qtnReportDtlId) {
		this.qtnReportDtlId = qtnReportDtlId;
	}

	public Long getQtnReportManId() {
		return qtnReportManId;
	}

	public void setQtnReportManId(Long qtnReportManId) {
		this.qtnReportManId = qtnReportManId;
	}

	public String getQtnMainDetail() {
		return qtnMainDetail;
	}

	public void setQtnMainDetail(String qtnMainDetail) {
		this.qtnMainDetail = qtnMainDetail;
	}

}
