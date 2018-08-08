package th.co.baiwa.excise.ia.persistence.vo;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Int09225FormVo {
	
	@JsonIgnore
	private MultipartFile uploadFile;

	private String statusUpload;

	public MultipartFile getUploadFile() {
		return uploadFile;
	}

	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}

	public String getStatusUpload() {
		return statusUpload;
	}

	public void setStatusUpload(String statusUpload) {
		this.statusUpload = statusUpload;
	}

}
