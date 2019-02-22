package th.go.excise.ims.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetHdr;

public interface TaDraftWorksheetHdrRepository extends CommonJpaCrudRepository<TaDraftWorksheetHdr, Long> {
	public TaDraftWorksheetHdr findByDraftNumber(String draftNumber);
	
}
