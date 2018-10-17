package th.co.baiwa.excise.ta.persistence.vo;

import th.co.baiwa.excise.domain.DataTableRequest;

public class MockupForm extends DataTableRequest {

    private String formSearch;

    public String getFormSearch() {
        return formSearch;
    }

    public void setFormSearch(String formSearch) {
        this.formSearch = formSearch;
    }
}
