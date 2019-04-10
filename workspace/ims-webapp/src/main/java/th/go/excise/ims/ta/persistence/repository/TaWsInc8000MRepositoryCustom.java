package th.go.excise.ims.ta.persistence.repository;

import java.util.List;
import java.util.Map;

import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;
import th.go.excise.ims.ta.vo.AnalysisFormVo;
import th.go.excise.ims.ta.vo.AnalyzeCompareOldYearVo;

public interface TaWsInc8000MRepositoryCustom {
	
	public Map<String, List<TaWsInc8000M>> findByMonthRange(String startMonth, String endMonth);
	
	public List<TaWsInc8000M> findByAnalyzeCompareOldYear(AnalyzeCompareOldYearVo formVo);
	
	public List<TaWsInc8000M> findByAnalysisIncomeCompareLastMonth(AnalysisFormVo formVo);
	
}
