package th.go.excise.ims.ia.vo;

import java.math.BigDecimal;

import th.go.excise.ims.ia.persistence.entity.IaQuestionnaireMadeHdr;

public class IntCalculateCriteriaVo {

	private BigDecimal riskRate;
	private String translatingRisk;
	private String color;
	private String codeColor;

	public BigDecimal getRiskRate() {
		return riskRate;
	}

	public void setRiskRate(BigDecimal riskRate) {
		this.riskRate = riskRate;
	}

	public String getTranslatingRisk() {
		return translatingRisk;
	}

	public void setTranslatingRisk(String translatingRisk) {
		this.translatingRisk = translatingRisk;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCodeColor() {
		return codeColor;
	}

	public void setCodeColor(String codeColor) {
		this.codeColor = codeColor;
	}

}
