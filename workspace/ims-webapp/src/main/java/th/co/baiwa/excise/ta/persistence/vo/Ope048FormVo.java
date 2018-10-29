package th.co.baiwa.excise.ta.persistence.vo;

import org.springframework.web.multipart.MultipartFile;
import th.co.baiwa.excise.domain.datatable.DataTableRequest;

import java.util.List;

public class Ope048FormVo extends DataTableRequest {

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
    private List<Ope048ExcelVo> dataExcel;
    private List<Ope048FormVo> dataList;

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

    public List<Ope048ExcelVo> getDataExcel() {
        return dataExcel;
    }

    public void setDataExcel(List<Ope048ExcelVo> dataExcel) {
        this.dataExcel = dataExcel;
    }

    public List<Ope048FormVo> getDataList() {
        return dataList;
    }

    public void setDataList(List<Ope048FormVo> dataList) {
        this.dataList = dataList;
    }
}
