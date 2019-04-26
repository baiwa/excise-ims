package th.go.excise.ims.ws.client.pcc.inquiryoffcodeaddress.oxm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InquiryOffcodeAddressRequest {
	@SerializedName("OFFCODE")
	@Expose
	private String offcode;

	public String getOffcode() {
		return offcode;
	}

	public void setOffcode(String offcode) {
		this.offcode = offcode;
	}
	
}