package th.co.baiwa.excise.ia.persistence.entity.tax;

import java.util.List;

public class IaTaxReceiptReq {
	private List<TaxReceipt> data;

	public List<TaxReceipt> getData() {
		return data;
	}

	public void setData(List<TaxReceipt> data) {
		this.data = data;
	}
}
