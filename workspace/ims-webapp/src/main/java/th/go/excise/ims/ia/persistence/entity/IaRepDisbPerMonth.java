
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
@Table(name = "IA_REP_DISB_PER_MONTH")
public class IaRepDisbPerMonth extends BaseEntity {
	
	private static final long serialVersionUID = 9035508519749912878L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "IA_REP_DISB_PER_MONTH_GEN")
	@SequenceGenerator(name = "IA_REP_DISB_PER_MONTH_GEN", sequenceName = "IA_REP_DISB_PER_MONTH_SEQ", allocationSize = 1)
	@Column(name = "IA_REP_DISB_PER_MONTH_ID")
	private BigDecimal iaRepDisbPerMonthId;
	@Column(name = "ACC_NO")
	private String accNo;
	@Column(name = "ACC_NAME")
	private String accName;
	@Column(name = "CARRY_FORWARD")
	private BigDecimal carryForward;
	@Column(name = "CARRYFORWARD")
	private BigDecimal carryforward;
	@Column(name = "DEBIT")
	private BigDecimal debit;
	@Column(name = "CREBIT")
	private BigDecimal crebit;
	

	public BigDecimal getIaRepDisbPerMonthId() {
		return iaRepDisbPerMonthId;
	}

	public void setIaRepDisbPerMonthId(BigDecimal iaRepDisbPerMonthId) {
		this.iaRepDisbPerMonthId = iaRepDisbPerMonthId;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}

	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public BigDecimal getCarryForward() {
		return carryForward;
	}

	public void setCarryForward(BigDecimal carryForward) {
		this.carryForward = carryForward;
	}

	public BigDecimal getCarryforward() {
		return carryforward;
	}

	public void setCarryforward(BigDecimal carryforward) {
		this.carryforward = carryforward;
	}

	public BigDecimal getDebit() {
		return debit;
	}

	public void setDebit(BigDecimal debit) {
		this.debit = debit;
	}

	public BigDecimal getCrebit() {
		return crebit;
	}

	public void setCrebit(BigDecimal crebit) {
		this.crebit = crebit;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
