
package th.go.excise.ims.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondHdr;

public interface TaWorksheetCondHdrRepository extends CommonJpaCrudRepository<TaWorksheetCondHdr, Long> {
	public TaWorksheetCondHdr findByAnalysisNumber(String analysisNumber);
}
