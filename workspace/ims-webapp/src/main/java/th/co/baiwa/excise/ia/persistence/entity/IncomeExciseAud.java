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
@Table(name="IA_INCOME_EXCISE_AUD")
public class IncomeExciseAud extends BaseEntity{
	
	private static final long serialVersionUID = -6645410182130983008L;

	@Id
	@SequenceGenerator(name="IA_INCOME_EXCISE_AUD_GEN", sequenceName="IA_INCOME_EXCISE_AUD_SEQ", allocationSize=1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_INCOME_EXCISE_AUD_GEN")
	@Column(name="IA_INCOME_EXCISE_AUD_ID")
	private long iaIncomeExciseAudId;

	@Column(name="END_MONTH")
	private String endMonth;

	@Column(name="START_MONTH")
	private String startMonth;
	

	public IncomeExciseAud() {
	}

	public long getIaIncomeExciseAudId() {
		return this.iaIncomeExciseAudId;
	}

	public void setIaIncomeExciseAudId(long iaIncomeExciseAudId) {
		this.iaIncomeExciseAudId = iaIncomeExciseAudId;
	}

	public String getEndMonth() {
		return this.endMonth;
	}

	public void setEndMonth(String endMonth) {
		this.endMonth = endMonth;
	}

	public String getStartMonth() {
		return this.startMonth;
	}

	public void setStartMonth(String startMonth) {
		this.startMonth = startMonth;
	}

}