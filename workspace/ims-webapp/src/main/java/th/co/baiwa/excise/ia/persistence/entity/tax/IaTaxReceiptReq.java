package th.co.baiwa.excise.ia.persistence.entity.tax;

import java.util.List;

public class IaTaxReceiptReq {
	private List<IaTaxReceipt> data;

	public List<IaTaxReceipt> getData() {
		return data;
	}

	public void setData(List<IaTaxReceipt> data) {
		this.data = data;
	}
}
