
package th.go.excise.ims.ta.persistence.repository;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public interface TaWorksheetHdrCustom {
	
	public void insertBatch(List<TaWorksheetHdr> taWorksheetHdrList) throws SQLException;
	public List<TaWorksheetHdr> findSubConditionRegCapital(BigDecimal from, BigDecimal to) throws SQLException;
}
