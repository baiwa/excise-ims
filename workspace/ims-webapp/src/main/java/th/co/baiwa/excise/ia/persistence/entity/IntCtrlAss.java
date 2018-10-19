package th.co.baiwa.excise.ia.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public class IntCtrlAss {
	@Entity
	@Table(name="IA_INT_CTRL_ASS")
	public static class License  extends BaseEntity {
	 
		private static final long serialVersionUID = -1452200423762763954L;

		@Id
		@SequenceGenerator(name="IA_INT_CTRL_ASS_GEN", sequenceName="IA_INT_CTRL_ASS_SEQ" , allocationSize = 1)
		
		@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="IA_INT_CTRL_ASS_GEN")	
		
	
		@Column(name="INT_CTRL_ASS_NAME")
		private String intCtrlAssName;

		@Column(name="BUDGET_YEAR")
		private String budgetYesr;
		
		@Column(name="INT_CTRL_ASS_ID")		
		private Long intCtrlAssId;

		public Long getIntCtrlAssId() {
			return intCtrlAssId;
		}

		public void setIntCtrlAssId(Long intCtrlAssId) {
			this.intCtrlAssId = intCtrlAssId;
		}

		public String getIntCtrlAssName() {
			return intCtrlAssName;
		}

		public void setIntCtrlAssName(String intCtrlAssName) {
			this.intCtrlAssName = intCtrlAssName;
		}

		public String getBudgetYesr() {
			return budgetYesr;
		}

		public void setBudgetYesr(String budgetYesr) {
			this.budgetYesr = budgetYesr;
		}

		public static long getSerialversionuid() {
			return serialVersionUID;
		}

	

	}
}
