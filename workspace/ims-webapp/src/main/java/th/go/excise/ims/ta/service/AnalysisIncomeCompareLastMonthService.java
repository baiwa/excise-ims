package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.chrono.ThaiBuddhistDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.LocalDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAM_GROUP;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.TA_CONFIG;
import th.co.baiwa.buckwaframework.support.ApplicationCache;
import th.co.baiwa.buckwaframework.support.domain.ParamInfo;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastMonthVo;
import th.go.excise.ims.ta.vo.WorksheetDateRangeVo;

@Service
public class AnalysisIncomeCompareLastMonthService extends AbstractBasicAnalysisService<DataTableAjax<AnalysisIncomeCompareLastMonthVo>> {

	private static final Logger logger = LoggerFactory.getLogger(AnalysisIncomeCompareLastMonthService.class);

	@Autowired
	private TaWsInc8000MRepository wsInc8000MRepository;

	@Override
	protected DataTableAjax<AnalysisIncomeCompareLastMonthVo> inquiryByWs(AnalysisFormVo formVo) {
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
		LocalDate localDateG2Start = LocalDate.from(localDateG1Start);
		LocalDate localDateG2End = LocalDate.from(localDateG1End);
		localDateG1End = localDateG1End.plusMonths(1);
		List<LocalDate> subLocalDateG1List = LocalDateUtils.getLocalDateRange(localDateG1Start, localDateG1End);
		List<LocalDate> subLocalDateG2List = LocalDateUtils.getLocalDateRange(localDateG2Start, localDateG2End);
		subLocalDateG2List.add(0, null);

		WorksheetDateRangeVo dateRangeVo = new WorksheetDateRangeVo();
		dateRangeVo.setYmG1StartInc8000M(localDateG1Start.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
		dateRangeVo.setYmG1EndInc8000M(localDateG1End.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
		dateRangeVo.setYmG2StartInc8000M(localDateG1Start.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
		dateRangeVo.setYmG2EndInc8000M(localDateG1End.format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));

		logger.debug("localDateG1Start={}", localDateG1Start);
		logger.debug("localDateG1End  ={}", localDateG1End);
		logger.debug("localDateG2Start={}", localDateG2Start);
		logger.debug("localDateG2End  ={}", localDateG2End);
		logger.debug("ymG1StartInc8000M={}, ymG1EndInc8000M={}, ymG2StartInc8000M={}, ymG2EndInc8000M={}", dateRangeVo.getYmG1StartInc8000M(), dateRangeVo.getYmG1EndInc8000M(), dateRangeVo.getYmG2StartInc8000M(), dateRangeVo.getYmG2EndInc8000M());
		logger.debug("subLocalDateList1.size()={}, subLocalDateList1={}", subLocalDateG1List.size(), org.springframework.util.StringUtils.collectionToCommaDelimitedString(subLocalDateG1List));
		logger.debug("subLocalDateList2.size()={}, subLocalDateList2={}", subLocalDateG2List.size(), org.springframework.util.StringUtils.collectionToCommaDelimitedString(subLocalDateG2List));

		Map<String, BigDecimal> incomeMap = wsInc8000MRepository.findByMonthRangeDuty(formVo.getNewRegId(), formVo.getDutyGroupId(), dateRangeVo, incomeTaxType);

		List<AnalysisIncomeCompareLastMonthVo> voList = new ArrayList<>();
		AnalysisIncomeCompareLastMonthVo vo = null;
		BigDecimal incomeCurrentMonthAmt = null;
		BigDecimal incomePreviousMonthAmt = null;
		BigDecimal diffIncomeAmt = null;
		BigDecimal diffIncomePnt = null;
		int dateLength = subLocalDateG1List.size();
		DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
		for (int i = 0; i < dateLength; i++) {
			vo = new AnalysisIncomeCompareLastMonthVo();
			incomeCurrentMonthAmt = incomeMap.get(subLocalDateG1List.get(i).format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
			if (incomeCurrentMonthAmt == null) {
				incomeCurrentMonthAmt = BigDecimal.ZERO;
			}
			if (i > 0) {
				incomePreviousMonthAmt = incomeMap.get(subLocalDateG2List.get(i).format(DateTimeFormatter.ofPattern(ConvertDateUtils.YYYYMM)));
				if (incomePreviousMonthAmt == null) {
					incomePreviousMonthAmt = BigDecimal.ZERO;
				}
			} else {
				incomePreviousMonthAmt = BigDecimal.ZERO;
			}
			diffIncomeAmt = incomeCurrentMonthAmt.subtract(incomePreviousMonthAmt);
			diffIncomePnt = NumberUtils.calculatePercent(incomeCurrentMonthAmt, incomePreviousMonthAmt);

			vo.setTaxMonth(ThaiBuddhistDate.from(subLocalDateG1List.get(i)).format(DateTimeFormatter.ofPattern("MMM yy", ConvertDateUtils.LOCAL_TH)));
			vo.setIncomeAmt(decimalFormat.format(incomeCurrentMonthAmt));
			vo.setDiffIncomeAmt(i == 0 ? "-" : decimalFormat.format(diffIncomeAmt));
			vo.setDiffIncomePnt(i == 0 ? "-" : decimalFormat.format(diffIncomePnt));
			voList.add(vo);
		}

		DataTableAjax<AnalysisIncomeCompareLastMonthVo> dataTableAjax = new DataTableAjax<>();
		dataTableAjax.setData(voList);

		return dataTableAjax;
	}

	@Override
	protected DataTableAjax<AnalysisIncomeCompareLastMonthVo> inquiryByPaperBaNumber(AnalysisFormVo formVo) {
		// TODO Auto-generated method stub
		return null;
	}
}
