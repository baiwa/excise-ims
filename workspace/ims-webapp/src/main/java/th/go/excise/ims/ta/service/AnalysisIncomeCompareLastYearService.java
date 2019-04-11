package th.go.excise.ims.ta.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.co.baiwa.buckwaframework.common.util.ConvertDateUtils;
import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.persistence.repository.TaWsInc8000MRepository;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalyzeCompareOldYearVo;

@Service
public class AnalysisIncomeCompareLastYearService {

	@Autowired
	private TaWsInc8000MRepository wsInc8000MRepository;

	private static final Logger logger = LoggerFactory.getLogger(AnalysisIncomeCompareLastYearService.class);

	private static final Integer TOTAL = 17;

	public DataTableAjax<AnalyzeCompareOldYearVo> GetAnalysisIncomeCompareLastYear(AnalysisFormVo formVo) {
		logger.info("listAnalysisIncomeCompareLastYear");

		// data compare1 ====================>
		// convert year month (mm/yyyy) to yyyymm
		Date startDate = ConvertDateUtils.parseStringToDate(formVo.getStartDate(), ConvertDateUtils.MM_YYYY);
		Date endDate = ConvertDateUtils.parseStringToDate(formVo.getEndDate(), ConvertDateUtils.MM_YYYY);
		String convertStrDate1 = ConvertDateUtils.formatDateToString(startDate, ConvertDateUtils.YYYYMM,
				ConvertDateUtils.LOCAL_EN);
		String convertEndDate1 = ConvertDateUtils.formatDateToString(endDate, ConvertDateUtils.YYYYMM,
				ConvertDateUtils.LOCAL_EN);
		// convertStrDate1 = date start, convertEndDate1 = date end
		formVo.setStartDate(convertStrDate1);
		formVo.setEndDate(convertEndDate1);

		List<TaWsInc8000M> data1 = wsInc8000MRepository.findByAnalyzeCompareOldYear(formVo);

		// data compare2 =========================>
		// convert year month (mm/yyyy) to yyyymm
		String convertStrDate2 = ConvertDateUtils.formatDateToString(DateUtils.addYears(startDate, -1),
				ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		String convertEndDate2 = ConvertDateUtils.formatDateToString(DateUtils.addYears(endDate, -1),
				ConvertDateUtils.YYYYMM, ConvertDateUtils.LOCAL_EN);
		// convertStrDate2 = date start, convertEndDate2 = date end
		formVo.setStartDate(convertStrDate2);
		formVo.setEndDate(convertEndDate2);

		List<TaWsInc8000M> data2 = wsInc8000MRepository.findByAnalyzeCompareOldYear(formVo);
		// Union data
		List<AnalyzeCompareOldYearVo> dataAll = new ArrayList<>();
		for (int i = 0; i < data1.size(); i++) {
			Boolean haveTaxAmount = false;
			dataAll.add(new AnalyzeCompareOldYearVo());
			for (int j = 0; j < data2.size(); j++) {
				int year = Integer.parseInt(data2.get(j).getTaxYear());
				String convYear = Integer.toString(year + 1);
				if (data1.get(i).getTaxYear().equals(convYear)
						&& data1.get(i).getTaxMonth().equals(data2.get(j).getTaxMonth())) {
					BigDecimal diff = data1.get(i).getTaxAmount().subtract(data2.get(j).getTaxAmount());
					dataAll.get(i).setDiff(diff);
					BigDecimal percent = dataAll.get(i).getDiff().divide(
							data1.get(i).getTaxAmount().max(data2.get(j).getTaxAmount()), 8, RoundingMode.CEILING);
					percent = percent.multiply(new BigDecimal(100.00));
					dataAll.get(i).setDiffPercent(percent);
					dataAll.get(i).setNewRegId(data1.get(i).getNewRegId());
					dataAll.get(i).setTaxAmount(data1.get(i).getTaxAmount());
					dataAll.get(i).setTaxAmountOld(data2.get(j).getTaxAmount());
					dataAll.get(i).setTaxMonth(data1.get(i).getTaxMonth());
					Date date = ConvertDateUtils.parseStringToDate(data1.get(i).getTaxYear(), ConvertDateUtils.YYYY,
							ConvertDateUtils.LOCAL_EN);
					String dateStr = ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYY,
							ConvertDateUtils.LOCAL_TH);
					dataAll.get(i).setTaxYear(dateStr);
					dataAll.get(i).setWsInc8000MId(data1.get(i).getWsInc8000MId());
					dataAll.get(i).setMonthDesc(
							ConvertDateUtils.getMonthThai(Integer.parseInt(data1.get(i).getTaxMonth()) - 1,
									ConvertDateUtils.SHORT_MONTH) + " " + dateStr);

					haveTaxAmount = true;
					break;
				}
			}
			if (!haveTaxAmount) {
				dataAll.get(i).setDiff(data1.get(i).getTaxAmount());
				dataAll.get(i).setDiffPercent(new BigDecimal(100.00));
				dataAll.get(i).setNewRegId(data1.get(i).getNewRegId());
				dataAll.get(i).setTaxAmount(data1.get(i).getTaxAmount());
				dataAll.get(i).setTaxAmountOld(new BigDecimal(0.00));
				dataAll.get(i).setTaxMonth(data1.get(i).getTaxMonth());
				Date date = ConvertDateUtils.parseStringToDate(data1.get(i).getTaxYear(), ConvertDateUtils.YYYY,
						ConvertDateUtils.LOCAL_EN);
				String dateStr = ConvertDateUtils.formatDateToString(date, ConvertDateUtils.YYYY,
						ConvertDateUtils.LOCAL_TH);
				dataAll.get(i).setTaxYear(dateStr);
				dataAll.get(i).setWsInc8000MId(data1.get(i).getWsInc8000MId());
				dataAll.get(i)
						.setMonthDesc(ConvertDateUtils.getMonthThai(Integer.parseInt(data1.get(i).getTaxMonth()) - 1,
								ConvertDateUtils.SHORT_MONTH) + " " + dateStr);
			}
		}

		DataTableAjax<AnalyzeCompareOldYearVo> dataTableAjax = new DataTableAjax<>();

		dataTableAjax.setData(dataAll);
//				dataTableAjax.setDraw(formVo.getDraw() + 1);
		int count = dataAll.size();
		dataTableAjax.setRecordsFiltered(count);
		dataTableAjax.setRecordsTotal(count);
		return dataTableAjax;
	}

//	public List<AnalyzeCompareOldYearVo> listAnalysisIncomeCompareLastYear(AnalysisFormVo formVo) {
//		
//	}

}
