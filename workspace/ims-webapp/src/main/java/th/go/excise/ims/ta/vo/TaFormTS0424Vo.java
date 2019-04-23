package th.go.excise.ims.ta.vo;

import java.util.Date;
import java.util.List;

public class TaFormTS0424Vo {
	private String formTsNumber;
	private String factoryName;
	private String auditMonthStart;
	private String auditMonthEnd;
	private String auditYear;
	
	
	public String getAuditMonthStart() {
		return auditMonthStart;
	}
	public void setAuditMonthStart(String auditMonthStart) {
		this.auditMonthStart = auditMonthStart;
	}
	public String getAuditMonthEnd() {
		return auditMonthEnd;
	}
	public void setAuditMonthEnd(String auditMonthEnd) {
		this.auditMonthEnd = auditMonthEnd;
	}

	
	private List<TaFormTS0424DtlVo> taFormTS0424DtlVoList;
	public String getFormTsNumber() {
		return formTsNumber;
	}
	public void setFormTsNumber(String formTsNumber) {
		this.formTsNumber = formTsNumber;
	}
	public String getFactoryName() {
		return factoryName;
	}
	public void setFactoryName(String factoryName) {
		this.factoryName = factoryName;
	}

	public String getAuditYear() {
		return auditYear;
	}
	public void setAuditYear(String auditYear) {
		this.auditYear = auditYear;
	}
	public List<TaFormTS0424DtlVo> getTaFormTS0424DtlVoList() {
		return taFormTS0424DtlVoList;
	}
	public void setTaFormTS0424DtlVoList(List<TaFormTS0424DtlVo> taFormTS0424DtlVoList) {
		this.taFormTS0424DtlVoList = taFormTS0424DtlVoList;
	}
	
	
}
