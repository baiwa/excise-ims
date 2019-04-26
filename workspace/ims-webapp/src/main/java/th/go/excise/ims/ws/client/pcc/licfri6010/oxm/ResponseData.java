package th.go.excise.ims.ws.client.pcc.licfri6010.oxm;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {
	@SerializedName("LicenseList")
	@Expose
	private List<LicenseList> licenseList;

	public List<LicenseList> getLicenseList() {
		return licenseList;
	}

	public void setLicenseList(List<LicenseList> licenseList) {
		this.licenseList = licenseList;
	}

}
