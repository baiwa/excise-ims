package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.client.pcc.inquiryedoffice.model.EdOffice;

public interface ExciseDepartmentRepositoryCustom {
	
	public void batchMerge(List<EdOffice> edOfficeList);
	
}
