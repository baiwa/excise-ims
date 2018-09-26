package th.co.baiwa.excise.ia.persistence.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;


/**
 * The persistent class for the IA_ALLOCATED_BUDGET database table.
 * 
 */
@Entity
@Table(name="IA_ALLOCATED_BUDGET")
public class AllocatedBudget extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5336759432167193312L;

	@Id
	@SequenceGenerator(name="IA_ALLOCATED_BUDGET_GEN", sequenceName="IA_ALLOCATED_BUDGET_SEQ", allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_ALLOCATED_BUDGET_GEN")
	@Column(name="ALLOCATED_BUDGET_ID")
	private long allocatedBudgetId;

	@Column(name="ALLOCATED_BUDGET")
	private BigDecimal allocatedBudget;

	public AllocatedBudget() {
	}

	public long getAllocatedBudgetId() {
		return this.allocatedBudgetId;
	}

	public void setAllocatedBudgetId(long allocatedBudgetId) {
		this.allocatedBudgetId = allocatedBudgetId;
	}

	public BigDecimal getAllocatedBudget() {
		return this.allocatedBudget;
	}

	public void setAllocatedBudget(BigDecimal allocatedBudget) {
		this.allocatedBudget = allocatedBudget;
	}

}