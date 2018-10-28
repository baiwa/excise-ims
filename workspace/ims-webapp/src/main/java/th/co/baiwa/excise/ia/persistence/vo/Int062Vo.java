package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.CwpScwdDtl;

public class Int062Vo {
	private Int062AddFieldVo cwpScwdDtl;
	private Long fileUploadID;
	private List<Int062AddFieldVo> cwpScwdDtlList;
	public Int062AddFieldVo getCwpScwdDtl() {
		return cwpScwdDtl;
	}
	public void setCwpScwdDtl(Int062AddFieldVo cwpScwdDtl) {
		this.cwpScwdDtl = cwpScwdDtl;
	}
	public Long getFileUploadID() {
		return fileUploadID;
	}
	public void setFileUploadID(Long fileUploadID) {
		this.fileUploadID = fileUploadID;
	}
	public List<Int062AddFieldVo> getCwpScwdDtlList() {
		return cwpScwdDtlList;
	}
	public void setCwpScwdDtlList(List<Int062AddFieldVo> cwpScwdDtlList) {
		this.cwpScwdDtlList = cwpScwdDtlList;
	}
	

}
