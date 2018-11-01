
package th.co.baiwa.excise.ws.entity.response.assessment;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Assessment {

    @SerializedName("data")
    @Expose
    private List<Datum> data;
    
    @SerializedName("status")
    @Expose
    private String status;
    
	public List<Datum> getData() {
		return data;
	}
	public void setData(List<Datum> data) {
		this.data = data;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
    
    

}
