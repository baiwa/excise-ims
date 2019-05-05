package th.go.excise.ims.ta.vo;

import th.go.excise.ims.ws.client.pcc.regfri4000.model.RegMaster60;

public class WsRegfri4000FormVo extends RegMaster60 {

	private String newRegId;
	private String customerAddress;
	private String facAddress;
	private String factoryType;
	private String factoryTypeText;

	public String getNewRegId() {
		return newRegId;
	}

	public void setNewRegId(String newRegId) {
		this.newRegId = newRegId;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getFacAddress() {
		return facAddress;
	}

	public void setFacAddress(String facAddress) {
		this.facAddress = facAddress;
	}

	public String getFactoryType() {
		return factoryType;
	}

	public void setFactoryType(String factoryType) {
		this.factoryType = factoryType;
	}

	public String getFactoryTypeText() {
		return factoryTypeText;
	}

	public void setFactoryTypeText(String factoryTypeText) {
		this.factoryTypeText = factoryTypeText;
	}

}
