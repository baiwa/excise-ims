
package th.go.excise.ims.ws.client.pcc.systemunworking.oxm;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("year")
    @Expose
    private String year;
    
    @SerializedName("month")
    @Expose
    private String month;
    
    @SerializedName("status")
    @Expose
    private String status;
    
    @SerializedName("data")
    @Expose
    private List<DataList> data;

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<DataList> getData() {
		return data;
	}

	public void setData(List<DataList> data) {
		this.data = data;
	}

	

}
