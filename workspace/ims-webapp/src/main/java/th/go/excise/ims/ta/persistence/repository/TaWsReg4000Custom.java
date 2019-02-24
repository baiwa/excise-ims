package th.go.excise.ims.ta.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;
import th.go.excise.ims.ta.vo.TaxDratfVo;

public interface TaWsReg4000Custom {

	public void insertBatch(List<TaWsReg4000> taWsReg4000List) throws SQLException ;
	public void truncateTaWsReg4000() throws SQLException ;
	
	public List<TaWsReg4000> findAllPagination(TaWsReg4000 taWsReg4000, int start, int length) throws SQLException;
	public Long countAll(TaWsReg4000 taWsReg4000) throws SQLException;
	public List<TaxDratfVo> findByDraftNumbwe(String draftNumbwe) throws SQLException;
}
