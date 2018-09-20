
package th.co.baiwa.excise.ws.entity.response.licfri6010;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData6010 {

    @SerializedName("LicenseList")
    @Expose
    private List<LicenseList6010> licenseList = null;

    public List<LicenseList6010> getLicenseList() {
        return licenseList;
    }

    public void setLicenseList(List<LicenseList6010> licenseList) {
        this.licenseList = licenseList;
    }

}
