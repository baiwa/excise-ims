
package th.co.baiwa.excise.ws.entity.response.projectbase;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseData {

    @SerializedName("listPMProject")
    @Expose
    public List<ListPMProject> listPMProject = null;

	public List<ListPMProject> getListPMProject() {
		return listPMProject;
	}

	public void setListPMProject(List<ListPMProject> listPMProject) {
		this.listPMProject = listPMProject;
	}

}
