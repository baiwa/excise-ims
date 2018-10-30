package th.co.baiwa.excise.ta.persistence.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Ope046FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5141138090662896644L;
	private String taExciseAcc0502Id;
	private String exciseId;
	private String taxFeeId;
	private String exciseName;
	private String productType;
	private MultipartFile fileName;
	private List<Ope046ExcelVo> dataExcel;
	private List<Ope046FormVo> dataList;
	private List<Ope046Vo> voList;
	
	private String dateFrom;
	private String dateTo;
	private String entrepreneur;
	private String anlysisNumber;
	private String type;
	private String coordinates;
	private String userNumber;
	private String searchFlag;
	
	

	public String getDateFrom() {
		return dateFrom;
	}

	public void setDateFrom(String dateFrom) {
		this.dateFrom = dateFrom;
	}

	public String getDateTo() {
		return dateTo;
	}

	public void setDateTo(String dateTo) {
		this.dateTo = dateTo;
	}

	public String getEntrepreneur() {
		return entrepreneur;
	}

	public void setEntrepreneur(String entrepreneur) {
		this.entrepreneur = entrepreneur;
	}

	public String getAnlysisNumber() {
		return anlysisNumber;
	}

	public void setAnlysisNumber(String anlysisNumber) {
		this.anlysisNumber = anlysisNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getUserNumber() {
		return userNumber;
	}

	public void setUserNumber(String userNumber) {
		this.userNumber = userNumber;
	}

	public String getSearchFlag() {
		return searchFlag;
	}

	public void setSearchFlag(String searchFlag) {
		this.searchFlag = searchFlag;
	}

	public List<Ope046Vo> getVoList() {
		return voList;
	}

	public void setVoList(List<Ope046Vo> voList) {
		this.voList = voList;
	}

	public List<Ope046FormVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Ope046FormVo> dataList) {
		this.dataList = dataList;
	}

	public List<Ope046ExcelVo> getDataExcel() {
		return dataExcel;
	}

	public void setDataExcel(List<Ope046ExcelVo> dataExcel) {
		this.dataExcel = dataExcel;
	}

	public String getTaExciseAcc0502Id() {
		return taExciseAcc0502Id;
	}

	public void setTaExciseAcc0502Id(String taExciseAcc0502Id) {
		this.taExciseAcc0502Id = taExciseAcc0502Id;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getTaxFeeId() {
		return taxFeeId;
	}

	public void setTaxFeeId(String taxFeeId) {
		this.taxFeeId = taxFeeId;
	}

	public String getExciseName() {
		return exciseName;
	}

	public void setExciseName(String exciseName) {
		this.exciseName = exciseName;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public MultipartFile getFileName() {
		return fileName;
	}

	public void setFileName(MultipartFile fileName) {
		this.fileName = fileName;
	}
}
