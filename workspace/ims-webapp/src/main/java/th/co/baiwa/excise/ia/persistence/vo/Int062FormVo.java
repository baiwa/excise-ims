package th.co.baiwa.excise.ia.persistence.vo;

import org.springframework.web.multipart.MultipartFile;

public class Int062FormVo {
	private MultipartFile fileExcel1;
	
	private MultipartFile fileExcel2;
	
	private String systemSort;
	
	public MultipartFile getFileExcel1() {
		return fileExcel1;
	}
	public void setFileExcel1(MultipartFile fileExcel1) {
		this.fileExcel1 = fileExcel1;
	}
	public MultipartFile getFileExcel2() {
		return fileExcel2;
	}
	public void setFileExcel2(MultipartFile fileExcel2) {
		this.fileExcel2 = fileExcel2;
	}
	public String getSystemSort() {
		return systemSort;
	}
	public void setSystemSort(String systemSort) {
		this.systemSort = systemSort;
	}
}