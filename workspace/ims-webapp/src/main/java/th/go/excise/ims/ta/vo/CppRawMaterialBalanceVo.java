package th.go.excise.ims.ta.vo;

public class CppRawMaterialBalanceVo {
	private Long id;
	private String list;
	private String balance;
	private String balanceCount;
	private String maxDiff;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getList() {
		return list;
	}

	public void setList(String list) {
		this.list = list;
	}

	public String getBalance() {
		return balance;
	}

	public void setBalance(String balance) {
		this.balance = balance;
	}

	public String getBalanceCount() {
		return balanceCount;
	}

	public void setBalanceCount(String balanceCount) {
		this.balanceCount = balanceCount;
	}

	public String getMaxDiff() {
		return maxDiff;
	}

	public void setMaxDiff(String maxDiff) {
		this.maxDiff = maxDiff;
	}

}
