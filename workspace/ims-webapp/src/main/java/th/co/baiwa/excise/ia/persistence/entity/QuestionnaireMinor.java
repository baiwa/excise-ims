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
@Table(name = "IA_QUESTIONNAIRE_MINOR_DETAIL")
public class QuestionnaireMinor extends BaseEntity {
	
	private static final long serialVersionUID = -8620136711978296093L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QTN_MINOR_DETAIL_GEN")
	@SequenceGenerator(name = "IA_QTN_MINOR_DETAIL_GEN", sequenceName = "IA_QTN_MINOR_DETAIL_SEQ", allocationSize = 1)
	@Column(name = "QTN_MINOR_ID")
	private Long qtnMinorId;
	
	@Column(name = "MAIN_ID")
	private Long mainId;
	
	@Column(name = "HEADER_CODE")
	private String headerCode;
	
	@Column(name = "QTN_MINOR_DETAIL")
	private String qtnMinorDetail;

	public Long getQtnMinorId() {
		return qtnMinorId;
	}

	public void setQtnMinorId(Long qtnMinorId) {
		this.qtnMinorId = qtnMinorId;
	}

	public Long getMainId() {
		return mainId;
	}

	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	public String getHeaderCode() {
		return headerCode;
	}

	public void setHeaderCode(String headerCode) {
		this.headerCode = headerCode;
	}

	public String getQtnMinorDetail() {
		return qtnMinorDetail;
	}

	public void setQtnMinorDetail(String qtnMinorDetail) {
		this.qtnMinorDetail = qtnMinorDetail;
	}
	
}
