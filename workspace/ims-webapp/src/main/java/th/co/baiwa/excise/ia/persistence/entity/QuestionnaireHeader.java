package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_QUESTIONNAIRE_HEADER database table.
 * 
 */
@Entity
@Table(name="IA_QUESTIONNAIRE_HEADER")
public class QuestionnaireHeader extends BaseEntity  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6347187652801366831L;

	@Id
	@SequenceGenerator(name="IA_QTN_HEADER_CODE_GEN", sequenceName="IA_QTN_HEADER_CODE_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_QTN_HEADER_CODE_GEN")
	@Column(name="QTN_HEADER_ID")
	private long qtnHeaderId;

	@Column(name="QTN_HEADER_CODE")
	private String qtnHeaderCode;

	@Column(name="QTN_HEADER_NAME")
	private String qtnHeaderName;

	public QuestionnaireHeader() {
	}

	public long getQtnHeaderId() {
		return this.qtnHeaderId;
	}

	public void setQtnHeaderId(long qtnHeaderId) {
		this.qtnHeaderId = qtnHeaderId;
	}

	public String getQtnHeaderCode() {
		return this.qtnHeaderCode;
	}

	public void setQtnHeaderCode(String qtnHeaderCode) {
		this.qtnHeaderCode = qtnHeaderCode;
	}

	public String getQtnHeaderName() {
		return this.qtnHeaderName;
	}

	public void setQtnHeaderName(String qtnHeaderName) {
		this.qtnHeaderName = qtnHeaderName;
	}

}