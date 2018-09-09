package th.co.baiwa.excise.ia.persistence.vo;

import th.co.baiwa.excise.domain.datatable.DataTableRequest;

import java.util.List;

public class Int05113FormVo extends DataTableRequest {
    /**
     *
     */
    private static final long serialVersionUID = -7737047148860962358L;
    private String sector;
    private String area;
    private String branch;
    private String dateForm;
    private String dateTo;
    private List<Int05113Vo> dataList;
    private Int0511Vo data;
    private String searchFlag;

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getDateForm() {
        return dateForm;
    }

    public void setDateForm(String dateForm) {
        this.dateForm = dateForm;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public List<Int05113Vo> getDataList() {
        return dataList;
    }

    public void setDataList(List<Int05113Vo> dataList) {
        this.dataList = dataList;
    }

    public Int0511Vo getData() {
        return data;
    }

    public void setData(Int0511Vo data) {
        this.data = data;
    }

    public String getSearchFlag() {
        return searchFlag;
    }

    public void setSearchFlag(String searchFlag) {
        this.searchFlag = searchFlag;
    }
}
