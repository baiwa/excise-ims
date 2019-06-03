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
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_CONFIG;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
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

	@Override
	protected List<BasicAnalysisIncomeCompareLastYearVo> inquiryByWs(BasicAnalysisFormVo formVo) {
		logger.info("inquiryByWs");
		
		String incomeTaxType = null;
		ParamInfo taxType = ApplicationCache.getParamInfoByCode(PARAM_GROUP.TA_CONFIG, TA_CONFIG.INCOME_TYPE);
		if (taxType != null) {
			incomeTaxType = taxType.getValue1();
		} else {
			incomeTaxType = TA_CONFIG.INCOME_TYPE_TAX;
		}
		
		LocalDate localDateG1Start = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getStartDate().split("/")[1]), Integer.parseInt(formVo.getStartDate().split("/")[0]), 1));
		LocalDate localDateG1End = LocalDate.from(ThaiBuddhistDate.of(Integer.parseInt(formVo.getEndDate().split("/")[1]), Integer.parseInt(formVo.getEndDate().split("/")[0]), 1));
		LocalDate localDateG2Start = localDateG1Start.minus(1, ChronoUnit.YEARS);
		LocalDate localDateG2End = localDateG1End.minus(1, ChronoUnit.YEARS);
		List<LocalDate> subLocalDateG1List = LocalDateUtils.getLocalDateRange(localDateG1Start, localDateG1End);
		List<LocalDate> subLocalDateG2List = LocalDateUtils.getLocalDateRange(localDateG2Start, localDateG2End);
		
		WorksheetDateRangeVo dateRangeVo = new WorksheetDateRangeVo();
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
		
		Map<String, BigDecimal> incomeMap = wsInc8000MRepository.findByMonthRangeDuty(formVo.getNewRegId(), formVo.getDutyGroupId(), dateRangeVo, incomeTaxType);
		
		List<BasicAnalysisIncomeCompareLastYearVo> voList = new ArrayList<>();
		BasicAnalysisIncomeCompareLastYearVo vo = null;
		BigDecimal incomeCurrentYearAmt = null;
		BigDecimal incomeLastYearAmt = null;
		BigDecimal diffIncomeAmt = null;
		BigDecimal diffIncomePnt = null;
		int dateLength = subLocalDateG1List.size();
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		for (int i = 0; i < dateLength; i++) {
			vo = new BasicAnalysisIncomeCompareLastYearVo();
			incomeCurrentYearAmt = incomeMap.get(subLocalDateG1List.get(i).format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
			if (incomeCurrentYearAmt == null) {
				incomeCurrentYearAmt = BigDecimal.ZERO;
			}
			incomeLastYearAmt = incomeMap.get(subLocalDateG2List.get(i).format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
			if (incomeLastYearAmt == null) {
				incomeLastYearAmt = BigDecimal.ZERO;
			}
			diffIncomeAmt = incomeCurrentYearAmt.subtract(incomeLastYearAmt);
			diffIncomePnt = NumberUtils.calculatePercent(incomeCurrentYearAmt, incomeLastYearAmt);
			
			vo.setTaxMonth(ThaiBuddhistDate.from(subLocalDateG1List.get(i)).format(DateTimeFormatter.ofPattern("MMM yy", ConvertDateUtils.LOCAL_TH)));
			vo.setIncomeLastYearAmt(decimalFormat.format(incomeLastYearAmt));
			vo.setIncomeCurrentYearAmt(decimalFormat.format(incomeCurrentYearAmt));
			vo.setDiffIncomeAmt(decimalFormat.format(diffIncomeAmt));
			vo.setDiffIncomePnt(decimalFormat.format(diffIncomePnt));
			voList.add(vo);
		}
		
		return voList;
	}

	@Override
	protected List<BasicAnalysisIncomeCompareLastYearVo> inquiryByPaperBaNumber(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void save(BasicAnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		
	}

}
