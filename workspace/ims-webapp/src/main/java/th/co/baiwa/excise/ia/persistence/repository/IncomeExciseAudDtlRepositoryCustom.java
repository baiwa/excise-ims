package th.co.baiwa.excise.ia.persistence.repository;

import java.sql.SQLException;
import java.util.List;

import th.co.baiwa.excise.ia.persistence.entity.IncomeExciseAudDtl;

public interface IncomeExciseAudDtlRepositoryCustom {
	
	public int[][] insertIncomeExciseAudDtlBatch(List<IncomeExciseAudDtl> incList) throws SQLException ;
	

}
