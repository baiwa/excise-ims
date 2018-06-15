package th.go.excise.ims.mockup.domain.ta;

import java.util.List;

import th.go.excise.ims.mockup.persistence.entity.ExciseTax;
import th.go.excise.ims.mockup.persistence.entity.ta.ExciseFileUpload;
import th.go.excise.ims.mockup.persistence.entity.ta.PlanWorksheetHeader;

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
