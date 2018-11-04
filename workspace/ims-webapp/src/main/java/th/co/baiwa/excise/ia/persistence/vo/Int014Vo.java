package th.co.baiwa.excise.ia.persistence.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Int014Vo {
	
	@JsonIgnore
	private MultipartFile fileExel;
	
	private List<TrialBalanceVo> lines;

	public List<TrialBalanceVo> getLines() {
		return lines;
	}

	public void setLines(List<TrialBalanceVo> lines) {
		this.lines = lines;
	}

	public MultipartFile getFileExel() {
		return fileExel;
	}

	public void setFileExel(MultipartFile fileExel) {
		this.fileExel = fileExel;
	}
}
