package th.go.excise.ims.ia.vo;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncD1;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD2;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncD3;
import th.go.excise.ims.ia.persistence.entity.IaAuditIncH;

public class Int0601SaveVo {


	private IaAuditIncH iaAuditIncH;
	private List<IaAuditIncD1> iaAuditIncD1List;
	private List<IaAuditIncD2> iaAuditIncD2List;
	private List<IaAuditIncD3> iaAuditIncD3List;
	
	
	public IaAuditIncH getIaAuditIncH() {
		return iaAuditIncH;
	}
	public void setIaAuditIncH(IaAuditIncH iaAuditIncH) {
		this.iaAuditIncH = iaAuditIncH;
	}
	public List<IaAuditIncD1> getIaAuditIncD1List() {
		return iaAuditIncD1List;
	}
	public void setIaAuditIncD1List(List<IaAuditIncD1> iaAuditIncD1List) {
		this.iaAuditIncD1List = iaAuditIncD1List;
	}
	public List<IaAuditIncD2> getIaAuditIncD2List() {
		return iaAuditIncD2List;
	}
	public void setIaAuditIncD2List(List<IaAuditIncD2> iaAuditIncD2List) {
		this.iaAuditIncD2List = iaAuditIncD2List;
	}
	public List<IaAuditIncD3> getIaAuditIncD3List() {
		return iaAuditIncD3List;
	}
	public void setIaAuditIncD3List(List<IaAuditIncD3> iaAuditIncD3List) {
		this.iaAuditIncD3List = iaAuditIncD3List;
	}


}
