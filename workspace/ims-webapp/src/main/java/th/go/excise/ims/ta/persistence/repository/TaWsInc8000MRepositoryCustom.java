package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.vo.AnalysisFormVo;

public interface TaWsInc8000MRepositoryCustom {
	
	public Map<String, List<TaWsInc8000M>> findByMonthRange(String startMonth, String endMonth);
	
	public Map<String, Map<String, BigDecimal>> findByMonthRangeDuty(String officeCode, String startMonth, String endMonth);
	
	public List<TaWsInc8000M> findByAnalyzeCompareOldYear(AnalysisFormVo formVo);
	
	public List<TaWsInc8000M> findByAnalysisIncomeCompareLastMonth(AnalysisFormVo formVo);
	
}
