
package th.co.baiwa.excise.ws.entity.response.systemError;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class SystemError {

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;
    @SerializedName("year")
    @Expose
    private String year;
    @SerializedName("status")
    @Expose
    private String status;

    public List<Datum> getData() {
        return data;
    }

    public void setData(List<Datum> data) {
        this.data = data;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("data", data).append("year", year).append("status", status).toString();
    }

}
