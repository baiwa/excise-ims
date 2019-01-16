package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.co.baiwa.excise.ws.entity.response.licfri6020.LicenseList6020;

public class Int0161Vo extends DataTableRequest {

	private LicenseList6020 licenseList6020;
	private List<Long> year;

	public LicenseList6020 getLicenseList6020() {
		return licenseList6020;
	}

	public void setLicenseList6020(LicenseList6020 licenseList6020) {
		this.licenseList6020 = licenseList6020;
	}

	public List<Long> getYear() {
		return year;
	}

	public void setYear(List<Long> year) {
		this.year = year;
	}

}
