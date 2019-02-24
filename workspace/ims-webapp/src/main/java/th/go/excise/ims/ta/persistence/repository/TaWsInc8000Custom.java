package th.go.excise.ims.ta.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWsInc8000;

public interface TaWsInc8000Custom {
	
	public void insertBatchList(List<TaWsInc8000> taWsInc8000List) throws SQLException;
	
}
