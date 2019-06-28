package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaD8;
import th.go.excise.ims.ta.persistence.repository.TaPaperBaD8Repository;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.vo.BasicAnalysisFormVo;
import th.go.excise.ims.ta.vo.BasicAnalysisIncomeCompareLastYearVo;
import th.go.excise.ims.ta.vo.WorksheetDateRangeVo;

@Service
public class BasicAnalysisIncomeCompareLastYearService extends AbstractBasicAnalysisService<BasicAnalysisIncomeCompareLastYearVo> {

	private static final Logger logger = LoggerFactory.getLogger(BasicAnalysisIncomeCompareLastYearService.class);

	@Autowired
	private TaPaperBaD8Repository taPaperBaD8Repository;
	@Autowired
	private TaWsInc8000MRepository wsInc8000MRepository;

	// FIXME Rewrite logic for calculate Difference and Percent
	@Override
	protected List<BasicAnalysisIncomeCompareLastYearVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		LocalDate localDateG1Start = null;
		LocalDate localDateG1End = null;
		LocalDate localDateG2Start = null;
		LocalDate localDateG2End = null;
		WorksheetDateRangeVo dateRangeVo = null;
		
		Map<String, BigDecimal> incomeMap = null;
		List<BasicAnalysisIncomeCompareLastYearVo> voList = new ArrayList<>();
		BasicAnalysisIncomeCompareLastYearVo vo = null;
		BigDecimal incomeCurrentYearAmt = null;
		BigDecimal incomeLastYearAmt = null;
		BigDecimal diffIncomeAmt = null;
		BigDecimal diffIncomePnt = null;
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		
		int yearNum = Integer.parseInt(formVo.getYearNum());
		for (int i = 1; i <= yearNum; i++) {
			int year = i - 1;
			localDateG1Start = toLocalDate(formVo.getStartDate()).minusYears(year);
			localDateG1End = toLocalDate(formVo.getEndDate()).minusYears(year);
			localDateG2Start = localDateG1Start.minus(1, ChronoUnit.YEARS);
			localDateG2End = localDateG1End.minus(1, ChronoUnit.YEARS);
			
			List<LocalDate> subLocalDateG1List = LocalDateUtils.getLocalDateRange(localDateG1Start, localDateG1End);
			List<LocalDate> subLocalDateG2List = LocalDateUtils.getLocalDateRange(localDateG2Start, localDateG2End);
			
			dateRangeVo = new WorksheetDateRangeVo();
			dateRangeVo.setYmG1StartInc8000M(localDateG1Start.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
			dateRangeVo.setYmG1EndInc8000M(localDateG1End.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
			dateRangeVo.setYmG2StartInc8000M(localDateG2Start.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
			dateRangeVo.setYmG2EndInc8000M(localDateG2End.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
			
			logger.debug("localDateG1Start={}", localDateG1Start);
			logger.debug("localDateG1End  ={}", localDateG1End);
			logger.debug("localDateG2Start={}", localDateG2Start);
			logger.debug("localDateG2End  ={}", localDateG2End);
			logger.debug("ymG1StartInc8000M={}, ymG1EndInc8000M={}, ymG2StartInc8000M={}, ymG2EndInc8000M={}", dateRangeVo.getYmG1StartInc8000M(), dateRangeVo.getYmG1EndInc8000M(), dateRangeVo.getYmG2StartInc8000M(), dateRangeVo.getYmG2EndInc8000M());
			logger.debug("subLocalDateList1.size()={}, subLocalDateList1={}", subLocalDateG1List.size(), org.springframework.util.StringUtils.collectionToCommaDelimitedString(subLocalDateG1List));
			logger.debug("subLocalDateList2.size()={}, subLocalDateList2={}", subLocalDateG2List.size(), org.springframework.util.StringUtils.collectionToCommaDelimitedString(subLocalDateG2List));
			
			incomeMap = wsInc8000MRepository.findByMonthRangeDuty(formVo.getNewRegId(), formVo.getDutyGroupId(), dateRangeVo, formVo.getYearIncomeType());
			
			int dateLength = subLocalDateG1List.size();
			for (int j = 0; j < dateLength; j++) {
				vo = new BasicAnalysisIncomeCompareLastYearVo();
				incomeCurrentYearAmt = incomeMap.get(subLocalDateG1List.get(j).format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
				if (incomeCurrentYearAmt == null) {
					incomeCurrentYearAmt = BigDecimal.ZERO;
				}
				incomeLastYearAmt = incomeMap.get(subLocalDateG2List.get(j).format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
				if (incomeLastYearAmt == null) {
					incomeLastYearAmt = BigDecimal.ZERO;
				}
				diffIncomeAmt = incomeCurrentYearAmt.subtract(incomeLastYearAmt);
				diffIncomePnt = NumberUtils.calculatePercent(incomeCurrentYearAmt, incomeLastYearAmt);
				
				vo.setTaxMonth(ThaiBuddhistDate.from(subLocalDateG1List.get(j)).format(DateTimeFormatter.ofPattern("MMM yy", ConvertDateUtils.LOCAL_TH)));
				vo.setIncomeCurrentYearAmt(decimalFormat.format(incomeCurrentYearAmt));
				vo.setIncomeLastYear1Amt(decimalFormat.format(incomeLastYearAmt));
				vo.setDiffIncomeLastYear1Amt(decimalFormat.format(diffIncomeAmt));
				vo.setDiffIncomeLastYear1Pnt(decimalFormat.format(diffIncomePnt));
				voList.add(vo);
			}
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisIncomeCompareLastYearVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByPaperBaNumber paperBaNumber={}", formVo.getPaperBaNumber());

		List<TaPaperBaD8> entityList = taPaperBaD8Repository.findByPaperBaNumber(formVo.getPaperBaNumber());
		List<BasicAnalysisIncomeCompareLastYearVo> voList = new ArrayList<>();
		BasicAnalysisIncomeCompareLastYearVo vo = null;
		for (TaPaperBaD8 entity : entityList) {
			vo = new BasicAnalysisIncomeCompareLastYearVo();
			vo.setTaxMonth(entity.getTaxMonth());
			vo.setIncomeCurrentYearAmt(entity.getIncomeCurrentYearAmt().toString());
			vo.setIncomeLastYear1Amt(entity.getIncomeLastYearAmt().toString());
			vo.setDiffIncomeLastYear1Amt(entity.getDiffIncomeAmt().toString());
			vo.setDiffIncomeLastYear1Pnt(entity.getDiffIncomePnt().toString());
			voList.add(vo);
		}
		return voList;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		List<BasicAnalysisIncomeCompareLastYearVo> dataSaveList = inquiryByWs(formVo);
		int i = 1;
		TaPaperBaD8 entity = null;
		for (BasicAnalysisIncomeCompareLastYearVo saveData : dataSaveList) {
			entity = new TaPaperBaD8();
			entity.setPaperBaNumber(formVo.getPaperBaNumber());
			entity.setSeqNo(i);
			entity.setTaxMonth(saveData.getTaxMonth());
			entity.setIncomeCurrentYearAmt(new BigDecimal((saveData.getIncomeCurrentYearAmt()).replaceAll(",", "")));
			entity.setIncomeLastYearAmt(new BigDecimal((saveData.getIncomeLastYear1Amt()).replaceAll(",", "")));
			entity.setDiffIncomeAmt(new BigDecimal((saveData.getDiffIncomeLastYear1Amt()).replaceAll(",", "")));
			entity.setDiffIncomePnt(new BigDecimal((saveData.getDiffIncomeLastYear1Pnt()).replaceAll(",", "")));
			taPaperBaD8Repository.save(entity);
			i++;
		}
	}

}
