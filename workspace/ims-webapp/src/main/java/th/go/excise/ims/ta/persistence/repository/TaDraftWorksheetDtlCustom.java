package th.go.excise.ims.ta.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetDtl;

public interface TaDraftWorksheetDtlCustom {
	
	public void save(List<TaDraftWorksheetDtl> draftWorksheetList) throws SQLException;
	
}
