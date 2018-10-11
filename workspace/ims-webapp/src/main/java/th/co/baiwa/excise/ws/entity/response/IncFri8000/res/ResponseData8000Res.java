
package th.co.baiwa.excise.ws.entity.response.IncFri8000.res;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData8000Res {

    @SerializedName("IncomeList")
    @Expose
    private List<IncomeList8000Res> incomeList = null;

    public List<IncomeList8000Res> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<IncomeList8000Res> incomeList) {
        this.incomeList = incomeList;
    }

}
