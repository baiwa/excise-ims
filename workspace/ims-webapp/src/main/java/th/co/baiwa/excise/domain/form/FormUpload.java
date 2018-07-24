package th.co.baiwa.excise.domain.form;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

public class FormUpload implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 533180199277673495L;
	private MultipartFile fileExel;

	public MultipartFile getFileExel() {
		return fileExel;
	}

	public void setFileExel(MultipartFile fileExel) {
		this.fileExel = fileExel;
	}
	
}
