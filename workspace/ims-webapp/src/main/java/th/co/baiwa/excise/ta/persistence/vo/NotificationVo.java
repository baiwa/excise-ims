package th.co.baiwa.excise.ta.persistence.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class NotificationVo {
	private Long id;
	private String type;
	private Long countType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getCountType() {
		return countType;
	}

	public void setCountType(Long countType) {
		this.countType = countType;
	}

}