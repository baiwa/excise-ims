package th.go.excise.ims.ws.client.pcc.oasfri0100.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ReceiveDoc {

	@SerializedName("signBy")
	@Expose
	private String signBy;

	@SerializedName("signDate")
	@Expose
	private String signDate;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
