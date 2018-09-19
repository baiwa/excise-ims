package th.co.baiwa.excise.ws.entity.response.licfri6020;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LicFri6020 {

	@SerializedName("Nid")
	@Expose
	private String nid;
	@SerializedName("NewregId")
	@Expose
	private String newregId;
	@SerializedName("PageNo")
	@Expose
	private String pageNo;
	@SerializedName("DataPerPage")
	@Expose
	private String dataPerPage;
	
    @SerializedName("ResponseCode")
    @Expose
    private String responseCode;
    @SerializedName("ResponseMessage")
    @Expose
    private String responseMessage;
    @SerializedName("ResponseData")
    @Expose
    private ResponseData responseData;

    public String getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public ResponseData getResponseData() {
        return responseData;
    }

    public void setResponseData(ResponseData responseData) {
        this.responseData = responseData;
    }

	public String getNid() {
		return nid;
	}

	public void setNid(String nid) {
		this.nid = nid;
	}

	public String getNewregId() {
		return newregId;
	}

	public void setNewregId(String newregId) {
		this.newregId = newregId;
	}

	public String getPageNo() {
		return pageNo;
	}

	public void setPageNo(String pageNo) {
		this.pageNo = pageNo;
	}

	public String getDataPerPage() {
		return dataPerPage;
	}

	public void setDataPerPage(String dataPerPage) {
		this.dataPerPage = dataPerPage;
	}

}
