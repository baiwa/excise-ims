package th.go.excise.ims.ta.persistence.repository;

import java.util.List;
import java.util.Map;

import th.go.excise.ims.ta.persistence.entity.TaWsInc8000M;

public interface TaWsInc8000MCustom {
	public Map<String, List<TaWsInc8000M>> findAllTaWsInc8000MSet( String startMonth, String endMonth);
}
