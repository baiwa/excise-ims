package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.co.baiwa.buckwaframework.common.util.NumberUtils;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastMonthVo;

@Service
public class AnalysisIncomeCompareLastMonthService {

	private TaWsInc8000MRepository wsInc8000MRepository;

	@Autowired
	public AnalysisIncomeCompareLastMonthService(TaWsInc8000MRepository wsInc8000MRepository) {
		this.wsInc8000MRepository = wsInc8000MRepository;
	}

	private static final Logger logger = LoggerFactory.getLogger(AnalysisIncomeCompareLastMonthService.class);

	public DataTableAjax<AnalysisIncomeCompareLastMonthVo> getAnalysisIncomeCompareLastMonth(AnalysisFormVo request) {
		DataTableAjax<AnalysisIncomeCompareLastMonthVo> dataTableAjax = null;
		try {
			// convert String to Date
			Date start = ConvertDateUtils.parseStringToDate(request.getStartDate(), ConvertDateUtils.MM_YYYY);
			Date end = ConvertDateUtils.parseStringToDate(request.getEndDate(), ConvertDateUtils.MM_YYYY);

			// Date to Calendar
			Calendar startCal = Calendar.getInstance(ConvertDateUtils.LOCAL_EN);
			startCal.setTime(start);
			// Calendar MONTH, -1
			startCal.add(Calendar.MONTH, -1);
			String startCalStr = DateFormatUtils.format(startCal, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);

			Calendar endCal = Calendar.getInstance(ConvertDateUtils.LOCAL_EN);
			endCal.setTime(end);
			String endCalStr = DateFormatUtils.format(endCal, ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);

			// set OOP to DB
			request.setStartDate(startCalStr);
			request.setEndDate(endCalStr);

			// call DB
			List<TaWsInc8000M> data = wsInc8000MRepository.findByAnalysisIncomeCompareLastMonth(request);

			// set output
			List<AnalysisIncomeCompareLastMonthVo> datasList = new ArrayList<>();
			AnalysisIncomeCompareLastMonthVo analysisIncomeCompareLastMonthVo = null;

			Date yearDate;
			String yearDateStr = "";
			String monYear = "";

			BigDecimal calDiffIncomeAmt;
			BigDecimal calDiffIncomePnt;

			for (int i = 0; i < data.size(); i++) {
				analysisIncomeCompareLastMonthVo = new AnalysisIncomeCompareLastMonthVo();

				yearDate = ConvertDateUtils.parseStringToDate(data.get(i).getTaxYear(), ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_EN);
				yearDateStr = ConvertDateUtils.formatDateToString(yearDate, ConvertDateUtils.YYYY, ConvertDateUtils.LOCAL_TH);
				monYear = ConvertDateUtils.getMonthThai(Integer.parseInt(data.get(i).getTaxMonth()) - 1, ConvertDateUtils.SHORT_MONTH) + " " + yearDateStr;

				if (i == 0) {
					analysisIncomeCompareLastMonthVo.setTaxMonth(monYear);
					analysisIncomeCompareLastMonthVo.setIncomeAmt(data.get(i).getTaxAmount());
					analysisIncomeCompareLastMonthVo.setDiffIncomeAmt(null);
					analysisIncomeCompareLastMonthVo.setDiffIncomePnt(null);
				} else {
					analysisIncomeCompareLastMonthVo.setTaxMonth(monYear);
					analysisIncomeCompareLastMonthVo.setIncomeAmt(data.get(i).getTaxAmount());

					calDiffIncomeAmt = data.get(i).getTaxAmount().subtract(NumberUtils.nullToZero(data.get(i - 1).getTaxAmount()));
					calDiffIncomePnt = (calDiffIncomeAmt.divide(data.get(i).getTaxAmount(), 2, RoundingMode.HALF_UP)).multiply(BigDecimal.valueOf(100));

					analysisIncomeCompareLastMonthVo.setDiffIncomeAmt(calDiffIncomeAmt);
					analysisIncomeCompareLastMonthVo.setDiffIncomePnt(calDiffIncomePnt);

				}

				datasList.add(analysisIncomeCompareLastMonthVo);
			}

			Integer count = datasList.size();
			dataTableAjax = new DataTableAjax<AnalysisIncomeCompareLastMonthVo>();
			dataTableAjax.setData(datasList);
			dataTableAjax.setRecordsTotal(count);
			dataTableAjax.setRecordsFiltered(count);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return dataTableAjax;
	}
}
