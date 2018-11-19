package th.co.baiwa.excise.cop.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.cop.persistence.entity.CopCheckFiscalReport;

public interface CopCheckFiscalReportRepository extends CommonJpaCrudRepository<CopCheckFiscalReport, Long> {

	List<CopCheckFiscalReport> findByFiscalYearId(Long fiscalYearId);

}
