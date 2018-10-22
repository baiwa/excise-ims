
package th.co.baiwa.excise.ws.entity.response.assessment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assessment {

    @SerializedName("data")
    @Expose
    public List<Datum> data = null;
    @SerializedName("status")
    @Expose
    public String status;

}
