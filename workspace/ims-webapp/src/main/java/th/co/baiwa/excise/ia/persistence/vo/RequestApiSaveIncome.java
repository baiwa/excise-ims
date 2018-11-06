
package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestApiSaveIncome {

    @SerializedName("username")
    @Expose
    private String username;
    @SerializedName("datas")
    @Expose
    private List<DataUpdateStatus> datas = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

	public List<DataUpdateStatus> getDatas() {
		return datas;
	}

	public void setDatas(List<DataUpdateStatus> datas) {
		this.datas = datas;
	}

    

}
