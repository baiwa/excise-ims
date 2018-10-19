package th.co.baiwa.excise.ta.persistence.vo;

import th.co.baiwa.excise.domain.DataTableRequest;

public class MockupForm extends DataTableRequest {

    private String formSearch;
    private String coordinatesFlag;

    public String getFormSearch() {
        return formSearch;
    }

    public void setFormSearch(String formSearch) {
        this.formSearch = formSearch;
    }

    public String getCoordinatesFlag() {
        return coordinatesFlag;
    }

    public void setCoordinatesFlag(String coordinatesFlag) {
        this.coordinatesFlag = coordinatesFlag;
    }
}
