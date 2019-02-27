package th.go.excise.ims.ta.persistence.repository;

import java.util.List;
import java.util.Map;

import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;

public interface TaWsInc8000MRepositoryCustom {
	
	public Map<String, List<TaWsInc8000M>> findByMonthRange(String startMonth, String endMonth);
	
}
