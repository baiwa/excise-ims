package th.go.excise.ims.ta.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWorksheetDtl;

public interface TaWorksheetDtlCustom {
	public void insertBatch(List<TaWorksheetDtl> taWorksheetHdrList) throws SQLException;
}
