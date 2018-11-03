package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

public class TrialBalanceMapping {

	private String accountNo;
	private List<String> incomeCodes;

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public List<String> getIncomeCodes() {
		return incomeCodes;
	}

	public void setIncomeCodes(List<String> incomeCodes) {
		this.incomeCodes = incomeCodes;
	}

}
