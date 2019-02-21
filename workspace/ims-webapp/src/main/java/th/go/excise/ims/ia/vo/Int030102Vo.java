package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;

public class Int030102Vo {
	private IaRiskFactorsMaster iaRiskFactorsMaster;
	private String createdDateDesc;
	private String updateDateDesc;
	private BigDecimal idMaster;

	public IaRiskFactorsMaster getIaRiskFactorsMaster() {
		return iaRiskFactorsMaster;
	}
	public void setIaRiskFactorsMaster(IaRiskFactorsMaster iaRiskFactorsMaster) {
		this.iaRiskFactorsMaster = iaRiskFactorsMaster;
	}
	public String getCreatedDateDesc() {
		return createdDateDesc;
	}
	public void setCreatedDateDesc(String createdDateDesc) {
		this.createdDateDesc = createdDateDesc;
	}
	public String getUpdateDateDesc() {
		return updateDateDesc;
	}
	public void setUpdateDateDesc(String updateDateDesc) {
		this.updateDateDesc = updateDateDesc;
	}
	public BigDecimal getIdMaster() {
		return idMaster;
	}
	public void setIdMaster(BigDecimal idMaster) {
		this.idMaster = idMaster;
	}

	
}
