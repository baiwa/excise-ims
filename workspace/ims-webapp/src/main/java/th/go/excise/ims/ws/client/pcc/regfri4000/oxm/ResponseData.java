
package th.go.excise.ims.ws.client.pcc.regfri4000.oxm;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("RegMaster60List")
    @Expose
    private List<RegMaster60List> regMaster60List = null;

    public List<RegMaster60List> getRegMaster60List() {
        return regMaster60List;
    }

    public void setRegMaster60List(List<RegMaster60List> regMaster60List) {
        this.regMaster60List = regMaster60List;
    }

}
