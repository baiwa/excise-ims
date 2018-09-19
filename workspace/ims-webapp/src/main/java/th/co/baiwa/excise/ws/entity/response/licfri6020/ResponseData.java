package th.co.baiwa.excise.ws.entity.response.licfri6020;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("LicenseList")
    @Expose
    private List<LicenseList> licenseList = null;

    public List<LicenseList> getLicenseList() {
        return licenseList;
    }

    public void setLicenseList(List<LicenseList> licenseList) {
        this.licenseList = licenseList;
    }

}
