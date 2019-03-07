package th.go.excise.ims.ta.vo;

public class LeftInStockServiceVo {
	private String list; //รายการ
	private String accountBalance; //ยอดคงเหลือตามบัญชี
	private String inventoryBalance; //ยอดสินค้าคงเหลือจากการตรวจนับ
	private String 	difference;  //ผลต่าง
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public String getAccountBalance() {
		return accountBalance;
	}
	public void setAccountBalance(String accountBalance) {
		this.accountBalance = accountBalance;
	}
	public String getInventoryBalance() {
		return inventoryBalance;
	}
	public void setInventoryBalance(String inventoryBalance) {
		this.inventoryBalance = inventoryBalance;
	}
	public String getDifference() {
		return difference;
	}
	public void setDifference(String difference) {
		this.difference = difference;
	}
}
