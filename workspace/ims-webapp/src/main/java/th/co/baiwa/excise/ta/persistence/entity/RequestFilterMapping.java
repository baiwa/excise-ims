package th.co.baiwa.excise.ta.persistence.entity;

import java.util.List;

import th.co.baiwa.excise.domain.DataTableRequest;

public class RequestFilterMapping extends DataTableRequest{
	
	private String analysNumber;
	private String num1;
	private String num2;
	private String percent1;
	private String percent2;
	private String flag;
	private String indexFilter;
	private String workShheetNumber;
	private String productType;
	private String exciseId;
	private List<String> exiceList;
	private String sector;
	
	public String getAnalysNumber() {
		return analysNumber;
	}
	public void setAnalysNumber(String analysNumber) {
		this.analysNumber = analysNumber;
	}
	public String getNum1() {
		return num1;
	}
	public void setNum1(String num1) {
		this.num1 = num1;
	}
	public String getNum2() {
		return num2;
	}
	public void setNum2(String num2) {
		this.num2 = num2;
	}
	public String getPercent1() {
		return percent1;
	}
	public void setPercent1(String percent1) {
		this.percent1 = percent1;
	}
	public String getPercent2() {
		return percent2;
	}
	public void setPercent2(String percent2) {
		this.percent2 = percent2;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	public String getIndexFilter() {
		return indexFilter;
	}
	public void setIndexFilter(String indexFilter) {
		this.indexFilter = indexFilter;
	}
	public String getWorkShheetNumber() {
		return workShheetNumber;
	}
	public void setWorkShheetNumber(String workShheetNumber) {
		this.workShheetNumber = workShheetNumber;
	}
	public String getProductType() {
		return productType;
	}
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public String getExciseId() {
		return exciseId;
	}
	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}
	public List<String> getExiceList() {
		return exiceList;
	}
	public void setExiceList(List<String> exiceList) {
		this.exiceList = exiceList;
	}
	public String getSector() {
		return sector;
	}
	public void setSector(String sector) {
		this.sector = sector;
	}
	
}
