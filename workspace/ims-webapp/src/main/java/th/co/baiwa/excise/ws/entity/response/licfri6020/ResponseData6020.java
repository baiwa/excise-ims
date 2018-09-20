package th.co.baiwa.excise.ws.entity.response.licfri6020;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData6020 {

    @SerializedName("LicenseList")
    @Expose
    private List<LicenseList6020> licenseList = null;

    public List<LicenseList6020> getLicenseList() {
        return licenseList;
    }

    public void setLicenseList(List<LicenseList6020> licenseList) {
        this.licenseList = licenseList;
    }

}
