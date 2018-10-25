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
