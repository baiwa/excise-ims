package th.go.excise.ims.ta.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import th.co.baiwa.buckwaframework.common.bean.DataTableAjax;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalysisIncomeCompareLastYearVo;

@Service
public class AnalysisIncomeCompareLastYearService {

	private static final Logger logger = LoggerFactory.getLogger(AnalysisIncomeCompareLastYearService.class);

	private static final Integer TOTAL = 17;

	public DataTableAjax<AnalysisIncomeCompareLastYearVo> GetAnalysisIncomeCompareLastYear(AnalysisFormVo request) {
		DataTableAjax<AnalysisIncomeCompareLastYearVo> dataTableAjax = new DataTableAjax<AnalysisIncomeCompareLastYearVo>();
		dataTableAjax.setDraw(request.getDraw() + 1);
		dataTableAjax.setData(listAnalysisIncomeCompareLastYear(request.getStart(), request.getLength(), TOTAL));
		dataTableAjax.setRecordsTotal(TOTAL);
		dataTableAjax.setRecordsFiltered(TOTAL);
		return dataTableAjax;
	}

	public List<AnalysisIncomeCompareLastYearVo> listAnalysisIncomeCompareLastYear(int start, int length, int total) {
		logger.info("listAnalysisIncomeCompareLastYear");
		List<AnalysisIncomeCompareLastYearVo> datalist = new ArrayList<AnalysisIncomeCompareLastYearVo>();
		AnalysisIncomeCompareLastYearVo data = null;
		for (int i = start; i < (start + length); i++) {
			if (i >= total) {
				break;
			}
			data = new AnalysisIncomeCompareLastYearVo();
			data.setId(Long.valueOf(i));
			data.setTaxMonth("พ.ค. 2560");
			data.setIncomeCurrentYear("25000.00");
			data.setIncomeLastYear("23520.00");
			data.setDiffIncomeAmt("-1480.00");
			data.setDiffIncomePnt("-5.92");
			datalist.add(data);
		}
		return datalist;
	}

}
