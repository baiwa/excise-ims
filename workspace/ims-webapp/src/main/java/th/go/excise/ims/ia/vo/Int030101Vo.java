package th.go.excise.ims.ia.vo;

import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsMaster;

public class Int030101Vo {
	private IaRiskFactorsMaster iaRiskFactorsMaster;
	private IaRiskFactorsConfig iaRiskFactorsConfig;
	public IaRiskFactorsMaster getIaRiskFactorsMaster() {
		return iaRiskFactorsMaster;
	}
	public void setIaRiskFactorsMaster(IaRiskFactorsMaster iaRiskFactorsMaster) {
		this.iaRiskFactorsMaster = iaRiskFactorsMaster;
	}
	public IaRiskFactorsConfig getIaRiskFactorsConfig() {
		return iaRiskFactorsConfig;
	}
	public void setIaRiskFactorsConfig(IaRiskFactorsConfig iaRiskFactorsConfig) {
		this.iaRiskFactorsConfig = iaRiskFactorsConfig;
	}
	
	
}
