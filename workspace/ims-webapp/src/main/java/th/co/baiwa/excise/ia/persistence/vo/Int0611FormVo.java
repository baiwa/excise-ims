package th.co.baiwa.excise.ia.persistence.vo;

import org.springframework.web.multipart.MultipartFile;
import th.co.baiwa.excise.ta.persistence.entity.ExciseFile;

public class Int0611FormVo {

	private MultipartFile fileName;

	public MultipartFile getFileName() {
		return fileName;
	}

	public void setFileName(MultipartFile fileName) {
		this.fileName = fileName;
	}

}
