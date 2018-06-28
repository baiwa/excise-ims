package th.co.baiwa.excise.ta.persistence.entity;

import java.util.List;

import th.co.baiwa.excise.ta.persistence.entity.ExciseTax;

public class ExciseDetail extends PlanWorksheetHeader{
	private List<ExciseFileUpload> file;
    private List<ExciseTax> exciseTax;
	public List<ExciseFileUpload> getFile() {
		return file;
	}
	public void setFile(List<ExciseFileUpload> file) {
		this.file = file;
	}
	public List<ExciseTax> getExciseTax() {
		return exciseTax;
	}
	public void setExciseTax(List<ExciseTax> exciseTax) {
		this.exciseTax = exciseTax;
	}
}
