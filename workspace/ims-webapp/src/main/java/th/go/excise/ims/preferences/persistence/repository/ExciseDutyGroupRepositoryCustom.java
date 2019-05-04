package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.client.pcc.inquirydutygroup.model.DutyGroup;

public interface ExciseDutyGroupRepositoryCustom {
	
	public void batchMerge(List<DutyGroup> dutyGroupList);
	
}
