package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetCondMainDtl;

public interface TaWorksheetCondMainDtlRepository extends CommonJpaCrudRepository<TaWorksheetCondMainDtl, Long> {

	public List<TaWorksheetCondMainDtl> findByAnalysisNumber(String analysisNumber);

}
