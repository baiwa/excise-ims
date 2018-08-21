package th.co.baiwa.excise.ws.entity.api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestServiceExcise {

	@SerializedName("Systemid")
	@Expose
	private String systemid;
	
	@SerializedName("Username")
	@Expose
	private String username;
	
	@SerializedName("Password")
	@Expose
	private String password;
	
	@SerializedName("Ipaddress")
	@Expose
	private String ipaddress;
	
	@SerializedName("RequestData")
	@Expose
	private Object requestData;
	public String getSystemid() {
		return systemid;
	}
	public void setSystemid(String systemid) {
		this.systemid = systemid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getIpaddress() {
		return ipaddress;
	}
	public void setIpaddress(String ipaddress) {
		this.ipaddress = ipaddress;
	}
	public Object getRequestData() {
		return requestData;
	}
	public void setRequestData(Object requestData) {
		this.requestData = requestData;
	}
	

}
