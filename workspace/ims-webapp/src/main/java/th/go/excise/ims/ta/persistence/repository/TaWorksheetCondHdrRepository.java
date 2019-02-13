
package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondHdr;

public interface TaWorksheetCondHdrRepository extends CommonJpaCrudRepository<TaWorksheetCondHdr, BigDecimal> {
	public TaWorksheetCondHdr findByAnalysisNumber(String analysisNumber);
}
