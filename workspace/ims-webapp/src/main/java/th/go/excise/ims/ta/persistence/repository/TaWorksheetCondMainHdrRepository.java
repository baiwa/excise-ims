package th.go.excise.ims.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainHdr;

public interface TaWorksheetCondMainHdrRepository extends CommonJpaCrudRepository<TaWorksheetCondMainHdr, Long> {
	
	public TaWorksheetCondMainHdr findByAnalysisNumber(String analysisNumber);
	
}
