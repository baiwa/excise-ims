package th.go.excise.ims.ws.client.pcc.oasfri0100.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FormDoc {

	@SerializedName("rec0142No")
	@Expose
	private String rec0142No;

	@SerializedName("rec0142Date")
	@Expose
	private String rec0142Date;

	@SerializedName("rec0142By")
	@Expose
	private String rec0142By;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}

}
