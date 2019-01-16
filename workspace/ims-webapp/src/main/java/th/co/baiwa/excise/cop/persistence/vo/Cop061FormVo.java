package th.co.baiwa.excise.cop.persistence.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import th.co.baiwa.buckwaframework.common.bean.DataTableRequest;
import th.co.baiwa.excise.ta.persistence.vo.Ope046ExcelVo;

public class Cop061FormVo extends DataTableRequest {

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

	private MultipartFile fileName;
	private List<Cop061ExcelVo> dataExcel;
	private List<Cop061FormVo> dataList;

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

	public List<Cop061ExcelVo> getDataExcel() {
		return dataExcel;
	}

	public void setDataExcel(List<Cop061ExcelVo> dataExcel) {
		this.dataExcel = dataExcel;
	}

	public List<Cop061FormVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<Cop061FormVo> dataList) {
		this.dataList = dataList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
