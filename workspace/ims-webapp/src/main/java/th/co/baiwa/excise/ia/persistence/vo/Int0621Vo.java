package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.CwpTblDtl;

public class Int0621Vo {
	private Long cwpScwdDtlId;
	
	private String budgetCode;
	
	private BigDecimal netAmount;
	
	private String budgetName;
	
	private List<CwpTblDtl> cwpTblDtlList;

	public Long getCwpScwdDtlId() {
		return cwpScwdDtlId;
	}

	public void setCwpScwdDtlId(Long cwpScwdDtlId) {
		this.cwpScwdDtlId = cwpScwdDtlId;
	}

	public String getBudgetCode() {
		return budgetCode;
	}

	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public BigDecimal getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(BigDecimal netAmount) {
		this.netAmount = netAmount;
	}

	public String getBudgetName() {
		return budgetName;
	}

	public void setBudgetName(String budgetName) {
		this.budgetName = budgetName;
	}

	public List<CwpTblDtl> getCwpTblDtlList() {
		return cwpTblDtlList;
	}

	public void setCwpTblDtlList(List<CwpTblDtl> cwpTblDtlList) {
		this.cwpTblDtlList = cwpTblDtlList;
	}

}