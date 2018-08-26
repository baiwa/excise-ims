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
@Table(name = "IA_QUESTIONNAIRE_MAIN_DETAIL")
public class QuestionnaireMain extends BaseEntity {
	
	private static final long serialVersionUID = -8620136711978296093L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QTN_MAIN_DETAIL_GEN")
	@SequenceGenerator(name = "IA_QTN_MAIN_DETAIL_GEN", sequenceName = "IA_QTN_MAIN_DETAIL_SEQ", allocationSize = 1)
	@Column(name = "IA_QTN_MAIN_DETAIL_ID")
	private Long qtnMainDetailId;
	
	@Column(name = "HEADER_CODE")
	private String headerCode;
	
	@Column(name = "QTN_MAIN_DETAIL")
	private String qtnMainDetail;

	public Long getQtnMainDetailId() {
		return qtnMainDetailId;
	}

	public void setQtnMainDetailId(Long qtnMainDetailId) {
		this.qtnMainDetailId = qtnMainDetailId;
	}

	public String getHeaderCode() {
		return headerCode;
	}

	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}

	public String getQtnMainDetail() {
		return qtnMainDetail;
	}

	public void setQtnMainDetail(String qtnMainDetail) {
		this.qtnMainDetail = qtnMainDetail;
	}
	
}
