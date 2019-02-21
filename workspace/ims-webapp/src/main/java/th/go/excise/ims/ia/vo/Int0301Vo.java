package th.go.excise.ims.ia.vo;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactors;
import th.go.excise.ims.ia.persistence.entity.IaRiskFactorsConfig;

public class Int0301Vo extends DataTableRequest {

	private IaRiskFactors iaRiskFactors;
	private IaRiskFactorsConfig iaRiskFactorsConfig;
	private String createdDateDesc;
	private String updateDateDesc;

	public IaRiskFactors getIaRiskFactors() {
		return iaRiskFactors;
	}

	public void setIaRiskFactors(IaRiskFactors iaRiskFactors) {
		this.iaRiskFactors = iaRiskFactors;
	}

	public IaRiskFactorsConfig getIaRiskFactorsConfig() {
		return iaRiskFactorsConfig;
	}

	public void setIaRiskFactorsConfig(IaRiskFactorsConfig iaRiskFactorsConfig) {
		this.iaRiskFactorsConfig = iaRiskFactorsConfig;
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

}
