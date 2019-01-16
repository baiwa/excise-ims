package th.co.baiwa.excise.ia.persistence.vo;

import org.springframework.web.multipart.MultipartFile;
import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;

public class Int0911T2Vo extends DataTableRequest {

	private Long id;
	private Long idProcess;
	private String createdDate;
	private String createdBy;
	private String documentName;
	private String documantSize;
	private String status;
	private String isDeleted;
	private String errorMsg;

	private String searchFlag;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdProcess() {
		return idProcess;
	}

	public void setIdProcess(Long idProcess) {
		this.idProcess = idProcess;
	}

	public String getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDocumentName() {
		return documentName;
	}

	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}

	public String getDocumantSize() {
		return documantSize;
	}

	public void setDocumantSize(String documantSize) {
		this.documantSize = documantSize;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

}
