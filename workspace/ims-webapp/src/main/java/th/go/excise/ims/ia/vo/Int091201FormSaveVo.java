package th.go.excise.ims.ia.vo;

import th.go.excise.ims.ia.persistence.entity.IaAuditWorkingH;

public class Int091201FormSaveVo extends IaAuditWorkingH {
	private String branch;
	private String sector;
	private String area;

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
