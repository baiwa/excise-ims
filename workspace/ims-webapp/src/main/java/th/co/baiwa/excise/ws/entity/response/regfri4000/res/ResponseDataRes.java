
package th.co.baiwa.excise.ws.entity.response.regfri4000.res;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDataRes {

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
