
package th.go.excise.ims.ws.client.pcc.regfri4000.oxm;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegDutyList {

    @SerializedName("GroupId")
    @Expose
    private String groupId;
    @SerializedName("GroupName")
    @Expose
    private String groupName;

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
