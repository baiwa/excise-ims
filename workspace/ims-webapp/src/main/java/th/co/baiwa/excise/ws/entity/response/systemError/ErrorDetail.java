
package th.co.baiwa.excise.ws.entity.response.systemError;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import org.apache.commons.lang3.builder.ToStringBuilder;

public class ErrorDetail {

    @SerializedName("error11")
    @Expose
    private String error11;
    @SerializedName("error12")
    @Expose
    private String error12;
    @SerializedName("error01")
    @Expose
    private String error01;
    @SerializedName("error02")
    @Expose
    private String error02;
    @SerializedName("error03")
    @Expose
    private String error03;
    @SerializedName("error04")
    @Expose
    private String error04;
    @SerializedName("error05")
    @Expose
    private String error05;
    @SerializedName("Error10")
    @Expose
    private String error10;
    @SerializedName("error06")
    @Expose
    private String error06;
    @SerializedName("error07")
    @Expose
    private String error07;
    @SerializedName("error08")
    @Expose
    private String error08;
    @SerializedName("error09")
    @Expose
    private String error09;

    public String getError11() {
        return error11;
    }

    public void setError11(String error11) {
        this.error11 = error11;
    }

    public String getError12() {
        return error12;
    }

    public void setError12(String error12) {
        this.error12 = error12;
    }

    public String getError01() {
        return error01;
    }

    public void setError01(String error01) {
        this.error01 = error01;
    }

    public String getError02() {
        return error02;
    }

    public void setError02(String error02) {
        this.error02 = error02;
    }

    public String getError03() {
        return error03;
    }

    public void setError03(String error03) {
        this.error03 = error03;
    }

    public String getError04() {
        return error04;
    }

    public void setError04(String error04) {
        this.error04 = error04;
    }

    public String getError05() {
        return error05;
    }

    public void setError05(String error05) {
        this.error05 = error05;
    }

    public String getError10() {
        return error10;
    }

    public void setError10(String error10) {
        this.error10 = error10;
    }

    public String getError06() {
        return error06;
    }

    public void setError06(String error06) {
        this.error06 = error06;
    }

    public String getError07() {
        return error07;
    }

    public void setError07(String error07) {
        this.error07 = error07;
    }

    public String getError08() {
        return error08;
    }

    public void setError08(String error08) {
        this.error08 = error08;
    }

    public String getError09() {
        return error09;
    }

    public void setError09(String error09) {
        this.error09 = error09;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).append("error11", error11).append("error12", error12).append("error01", error01).append("error02", error02).append("error03", error03).append("error04", error04).append("error05", error05).append("error10", error10).append("error06", error06).append("error07", error07).append("error08", error08).append("error09", error09).toString();
    }

}
