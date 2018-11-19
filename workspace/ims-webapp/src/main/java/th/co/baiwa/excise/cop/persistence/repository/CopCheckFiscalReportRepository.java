package th.co.baiwa.excise.cop.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.cop.persistence.entity.CopCheckFiscalReport;

public interface CopCheckFiscalReportRepository extends CommonJpaCrudRepository<CopCheckFiscalReport, Long> {

	public CopCheckFiscalReport findByFiscalYearId(Long fiscalYearId);

}
