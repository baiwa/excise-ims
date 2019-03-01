package th.go.excise.ims.ia.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@Entity
@Table(name = "IA_RISK_SELECT_CASE")
public class IaRiskSelectCase extends BaseEntity {

	private static final long serialVersionUID = 1126425384322194149L;
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_RISK_SELECT_CASE_GEN")
	@SequenceGenerator(name = "IA_RISK_SELECT_CASE_GEN", sequenceName = "IA_RISK_SELECT_CASE_SEQ", allocationSize = 1)
	@Column(name = "ID")
	private BigDecimal id;
	@Column(name = "ID_DATA")
	private BigDecimal idData;
	@Column(name = "STATUS")
	private String status;

	public BigDecimal getId() {
		return id;
	}

	public void setId(BigDecimal id) {
		this.id = id;
	}

	public BigDecimal getIdData() {
		return idData;
	}

	public void setIdData(BigDecimal idData) {
		this.idData = idData;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
