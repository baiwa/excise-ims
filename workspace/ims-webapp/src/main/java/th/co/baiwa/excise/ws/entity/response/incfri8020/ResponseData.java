
package th.co.baiwa.excise.ws.entity.response.incfri8020;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("IncomeList")
    @Expose
    private List<IncomeList> incomeList = null;

    public List<IncomeList> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<IncomeList> incomeList) {
        this.incomeList = incomeList;
    }

}
