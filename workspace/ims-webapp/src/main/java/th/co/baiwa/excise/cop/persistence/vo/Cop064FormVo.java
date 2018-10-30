package th.co.baiwa.excise.cop.persistence.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

public class Cop064FormVo extends DataTableRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5141138090662896644L;
	private String taExciseAcc0502Id;

	private String fiscalYear;
	private String exciseId;
	private String exciseName;
	private String exciseAddress;
	private String exciseType;
	private String productType;
	
	private String dateFrom ;
	private String dateTo;
	
	private MultipartFile fileName;
	private List<Cop064ExcelVo> dataExcel;
	private List<Cop064FormVo> dataList;
	private List<Cop064Vo> dataListVo;

	public String getTaExciseAcc0502Id() {
		return taExciseAcc0502Id;
	}

	public void setTaExciseAcc0502Id(String taExciseAcc0502Id) {
		this.taExciseAcc0502Id = taExciseAcc0502Id;
	}

	public String getFiscalYear() {
		return fiscalYear;
	}

	public void setFiscalYear(String fiscalYear) {
		this.fiscalYear = fiscalYear;
	}

	public String getExciseId() {
		return exciseId;
	}

	public void setExciseId(String exciseId) {
		this.exciseId = exciseId;
	}

	public String getExciseName() {
		return exciseName;
	}

	public void setExciseName(String exciseName) {
		this.exciseName = exciseName;
	}

	public String getExciseAddress() {
		return exciseAddress;
	}

	public void setExciseAddress(String exciseAddress) {
		this.exciseAddress = exciseAddress;
	}

	public String getExciseType() {
		return exciseType;
	}

	public void setExciseType(String exciseType) {
		this.exciseType = exciseType;
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

	public List<Cop064ExcelVo> getDataExcel() {
		return dataExcel;
	}

	public void setDataExcel(List<Cop064ExcelVo> dataExcel) {
		this.dataExcel = dataExcel;
	}

	public List<Cop064FormVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Cop064FormVo> dataList) {
		this.dataList = dataList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Cop064Vo> getDataListVo() {
		return dataListVo;
	}

	public void setDataListVo(List<Cop064Vo> dataListVo) {
		this.dataListVo = dataListVo;
	}

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
	
	
	

}
