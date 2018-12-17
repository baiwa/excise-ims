package th.co.baiwa.excise.ta.persistence.vo;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class NotificationFormVo {
	private Long id;
	private Long type;
	private Long countType;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}

	public Long getCountType() {
		return countType;
	}

	public void setCountType(Long countType) {
		this.countType = countType;
	}

}