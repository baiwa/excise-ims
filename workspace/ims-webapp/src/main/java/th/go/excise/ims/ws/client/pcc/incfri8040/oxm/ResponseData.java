package th.go.excise.ims.ws.client.pcc.incfri8040.oxm;


import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {
	@SerializedName("IncomeList")
	@Expose
	private List<IncomeList> incomeList;

	public List<IncomeList> getIncomeList() {
		return incomeList;
	}

	public void setIncomeList(List<IncomeList> incomeList) {
		this.incomeList = incomeList;
	}

}
