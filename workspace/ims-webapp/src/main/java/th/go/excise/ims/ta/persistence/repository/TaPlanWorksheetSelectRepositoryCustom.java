package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

public interface TaPlanWorksheetSelectRepositoryCustom {
	
	public void batchInsert(String budgetYear, List<String> newRegIdList);
	
	public Integer findCentalAllSend();
	
}
