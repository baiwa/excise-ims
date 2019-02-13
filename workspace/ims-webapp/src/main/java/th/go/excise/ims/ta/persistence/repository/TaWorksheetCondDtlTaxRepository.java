
package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondDtlTax;

public interface TaWorksheetCondDtlTaxRepository extends CommonJpaCrudRepository<TaWorksheetCondDtlTax, BigDecimal> {
	public List<TaWorksheetCondDtlTax> findByAnalysisNumber(String analysisNumber);
}
