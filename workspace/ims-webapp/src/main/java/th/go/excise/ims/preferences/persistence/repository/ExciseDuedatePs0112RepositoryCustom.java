package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.client.pcc.inquiryduedateps0112.model.DuedatePs0112;

public interface ExciseDuedatePs0112RepositoryCustom {

	public void batchMerge(List<DuedatePs0112> duedatePs0112List);

}
