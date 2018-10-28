
package th.co.baiwa.excise.ws.entity.response.systemError;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class Datum {

    @SerializedName("countAll")
    @Expose
    private String countAll;
    @SerializedName("systemName")
    @Expose
    private String systemName;
    @SerializedName("countError")
    @Expose
    private String countError;
    @SerializedName("systemCode")
    @Expose
    private String systemCode;
    @SerializedName("errorDetail")
    @Expose
    private ErrorDetail errorDetail;

    public String getCountAll() {
        return countAll;
    }

    public void setCountAll(String countAll) {
        this.countAll = countAll;
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName;
    }

    public String getCountError() {
        return countError;
    }

    public void setCountError(String countError) {
        this.countError = countError;
    }

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public ErrorDetail getErrorDetail() {
        return errorDetail;
    }

    public void setErrorDetail(ErrorDetail errorDetail) {
        this.errorDetail = errorDetail;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("countAll", countAll).append("systemName", systemName).append("countError", countError).append("systemCode", systemCode).append("errorDetail", errorDetail).toString();
    }

}
