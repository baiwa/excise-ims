package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class Int061103FormUpload {
	
	private MultipartFile[] files;
	private BigDecimal id;
	
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	

}
