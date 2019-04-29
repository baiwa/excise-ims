package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.client.pcc.inquiryincmast.model.IncomeMaster;

public interface ExciseIncMastRepositoryCustom {
	
	public void batchUpdate(List<IncomeMaster> incMastList);

}
