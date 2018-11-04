package th.co.baiwa.excise.ia.persistence.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import th.co.baiwa.excise.ws.entity.response.incfri8020.IncomeList;

public class TypeTaxVo {

	private String incomeCode;
	private String incomeName;
	
	@JsonIgnore
	@JsonIgnoreProperties("wsmap")
	private List<IncomeList> wsmap = new ArrayList<>();
	
	private List<OfficeTrailVo>  officeList = new ArrayList<>();

	public List<OfficeTrailVo> getOfficeList() {
		return officeList;
	}

	public void setOfficeList(List<OfficeTrailVo> officeList) {
		this.officeList = officeList;
	}

	public String getIncomeCode() {
		return incomeCode;
	}

	public void setIncomeCode(String incomeCode) {
		this.incomeCode = incomeCode;
	}

	public String getIncomeName() {
		return incomeName;
	}

	public void setIncomeName(String incomeName) {
		this.incomeName = incomeName;
	}

	public List<IncomeList> getWsmap() {
		return wsmap;
	}

	public void setWsmap(List<IncomeList> wsmap) {
		this.wsmap = wsmap;
	}

}
