package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;
import th.co.baiwa.excise.ia.persistence.entity.CwpTblDtl;

public class Int0621CompareFormVo {
//	 private CwpScwdDtl cwpScwdDtl;
//	 private List<CwpScwdDtl> cwpScwdDtlList;
//	 
//	 private CwpTblDtl cwpTblDtl;
//	 private List<CwpTblDtl> cwpTblDtlList;
	 
	 private List<String> cwpTblDtlId;
	 
	 private String budgetCode;
	 
	 private Long cwpScwdHdrId;

	public List<String> getCwpTblDtlId() {
		return cwpTblDtlId;
	}

	public void setCwpTblDtlId(List<String> cwpTblDtlId) {
		this.cwpTblDtlId = cwpTblDtlId;
	}

	public String getBudgetCode() {
		return budgetCode;
	}

	public void setBudgetCode(String budgetCode) {
		this.budgetCode = budgetCode;
	}

	public Long getCwpScwdHdrId() {
		return cwpScwdHdrId;
	}

	public void setCwpScwdHdrId(Long cwpScwdHdrId) {
		this.cwpScwdHdrId = cwpScwdHdrId;
	}

}