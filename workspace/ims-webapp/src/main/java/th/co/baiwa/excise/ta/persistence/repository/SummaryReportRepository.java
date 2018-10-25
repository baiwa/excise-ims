package th.co.baiwa.excise.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ta.persistence.entity.SummaryReport;

public interface SummaryReportRepository extends CommonJpaCrudRepository<SummaryReport, Long>{

	
	public List<SummaryReport> findByAnalysnumber(String analysnumber);
	
	public SummaryReport findByAnalysnumberAndSector(String analysnumber , String sector);
	

}
