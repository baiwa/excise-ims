package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaGfdrawAccount;
import th.go.excise.ims.ia.persistence.entity.IaGfledgerAccount;
import th.go.excise.ims.ia.persistence.entity.IaGfmovementAccount;
import th.go.excise.ims.ia.persistence.entity.IaGftrialBalance;

public class Int15ResponseUploadVo {
	
	private String fileName;
	private List<IaGfdrawAccount> formData1;
	private List<IaGftrialBalance> formData2;
	private List<IaGfledgerAccount> formData3;
	private List<IaGfmovementAccount> formData4;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public List<IaGfdrawAccount> getFormData1() {
		return formData1;
	}
	public void setFormData1(List<IaGfdrawAccount> formData1) {
		this.formData1 = formData1;
	}
	public List<IaGftrialBalance> getFormData2() {
		return formData2;
	}
	public void setFormData2(List<IaGftrialBalance> formData2) {
		this.formData2 = formData2;
	}
	public List<IaGfledgerAccount> getFormData3() {
		return formData3;
	}
	public void setFormData3(List<IaGfledgerAccount> formData3) {
		this.formData3 = formData3;
	}
	public List<IaGfmovementAccount> getFormData4() {
		return formData4;
	}
	public void setFormData4(List<IaGfmovementAccount> formData4) {
		this.formData4 = formData4;
	}
	
}
