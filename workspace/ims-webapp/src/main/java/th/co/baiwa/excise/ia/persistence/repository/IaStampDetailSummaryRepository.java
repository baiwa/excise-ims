package th.co.baiwa.excise.ia.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.IaStampDetailSummary;

public interface IaStampDetailSummaryRepository extends CommonJpaCrudRepository<IaStampDetailSummary, Long>{

	IaStampDetailSummary findByStampGenreIdAndYear(Long stampGenreId, String year);
}
