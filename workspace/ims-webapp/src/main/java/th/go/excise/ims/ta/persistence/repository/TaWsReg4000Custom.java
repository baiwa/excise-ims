package th.go.excise.ims.ta.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;

public interface TaWsReg4000Custom {

	public void insertBatch(List<TaWsReg4000> taWsReg4000List) throws SQLException ;
	public void truncateTaWsReg4000() throws SQLException ;
	
	public List<TaWsReg4000> findAllPagination(int start , int length)throws SQLException;
	public Long countAll() throws SQLException;
}
