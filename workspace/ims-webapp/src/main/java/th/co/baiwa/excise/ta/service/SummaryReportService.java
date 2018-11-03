package th.co.baiwa.excise.ta.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.excise.ta.persistence.dao.PlanWorksheetHeaderDao;
import th.co.baiwa.excise.ta.persistence.entity.SummaryReport;
import th.co.baiwa.excise.ta.persistence.repository.SummaryReportRepository;

@Service
public class SummaryReportService {
	private Logger logger = LoggerFactory.getLogger(SummaryReportService.class);
	private final String ALL_SECTOR_CONFIG = "ALL_SECTOR_CONFIG";
	
	@Autowired
	private SummaryReportRepository summaryReportRepository;
	
	@Autowired
	private PlanWorksheetHeaderDao planWorksheetHeaderDao;
	
	public void createSummaryReport(String analysnumber) {
		logger.info("createSummaryReport analysnumber : {}" , analysnumber);
		List<Lov> lovList = ApplicationCache.getListOfValueByTypeParentId(ALL_SECTOR_CONFIG, null);
		List<SummaryReport> summaryReportList = new ArrayList<SummaryReport>();
		SummaryReport summaryReport = null;
		for (Lov lov : lovList) {
			summaryReport = new SummaryReport();
			summaryReport.setAnalysnumber(analysnumber);
			summaryReport.setSector(lov.getValue1());
			if("11".equals(lov.getSubType())) {
				summaryReport.setSendDate(new Date());
			}
			summaryReportList.add(summaryReport);
		}
		summaryReportRepository.save(summaryReportList);
		logger.info("createSummaryReport Completed");
	}
	
	public void centralReceiveLine(String analysNumber,List<String> exciseIdList) {
		
		SummaryReport summaryReport = new SummaryReport();
		Date currenDate = new Date();
		List<Lov> lovList = ApplicationCache.getListOfValueByValueType(ALL_SECTOR_CONFIG, "11");
		summaryReport = summaryReportRepository.findByAnalysnumberAndSectorOrderById(analysNumber, lovList.get(0).getValue1());
		summaryReport.setReceiveDate(currenDate);
		summaryReportRepository.save(summaryReport);
		List<String> sectorNameList = planWorksheetHeaderDao.findSectorByNotInExciseAndAnalysNumber(analysNumber, exciseIdList);
		for (String sector : sectorNameList) {
			summaryReport = new SummaryReport();
			summaryReport = summaryReportRepository.findByAnalysnumberAndSectorOrderById(analysNumber, sector);
			summaryReport.setSendDate(currenDate);
			summaryReportRepository.save(summaryReport);
	
		}
		
	}
	public void sectorReceiveLine(String analysNumber,List<String> exciseIdList) {
		SummaryReport summaryReport = null;
		Date currenDate = new Date();
		List<String> sectorNameList = planWorksheetHeaderDao.findSectorByExciseAndAnalysNumber(analysNumber, exciseIdList);
		for (String sector : sectorNameList) {
			summaryReport = new SummaryReport();
			summaryReport = summaryReportRepository.findByAnalysnumberAndSectorOrderById(analysNumber, sector);
			summaryReport.setReceiveDate(currenDate);
			summaryReportRepository.save(summaryReport);
		}
	}
	
	public List<SummaryReport> findByAnalysnumber(String analysnumber){
		return summaryReportRepository.findByAnalysnumberOrderById(analysnumber);
	}
}
