
package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_QUESTIONNAIRE_MADE")
public class IaQuestionnaireMade extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8965630066886997777L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_QUESTIONNAIRE_MADE_GEN")
	@SequenceGenerator(name = "IA_QUESTIONNAIRE_MADE_GEN", sequenceName = "IA_QUESTIONNAIRE_MADE_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "ID_SIDE_DTL")
	private BigDecimal idSideDtl;
	@Column(name = "CHECK_FLAG")
	private String checkFlag;
	@Column(name = "OFFICE_CODE")
	private String officeCode;
	@Column(name = "QTN_LEVEL")
	private String qtnLevel;
	@Column(name = "STATUS")
	private String status;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdSideDtl() {
		return idSideDtl;
	}

	public void setIdSideDtl(BigDecimal idSideDtl) {
		this.idSideDtl = idSideDtl;
	}

	public String getCheckFlag() {
		return checkFlag;
	}

	public void setCheckFlag(String checkFlag) {
		this.checkFlag = checkFlag;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

	public String getOfficeCode() {
		return officeCode;
	}

	public void setOfficeCode(String officeCode) {
		this.officeCode = officeCode;
	}

	public String getQtnLevel() {
		return qtnLevel;
	}

	public void setQtnLevel(String qtnLevel) {
		this.qtnLevel = qtnLevel;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
