package th.co.baiwa.excise.ia.persistence.vo;

import java.math.BigDecimal;

import org.springframework.web.multipart.MultipartFile;

public class Int061101FormVo {

	// private List<WithdrawFileUpload> listExcel;
	private MultipartFile[] files;
	private BigDecimal rentHouseId;
	private String type;

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public BigDecimal getRentHouseId() {
		return rentHouseId;
	}

	public void setRentHouseId(BigDecimal rentHouseId) {
		this.rentHouseId = rentHouseId;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
