package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;

public interface TaWsReg4000RepositoryCustom {

	public void batchInsert(List<TaWsReg4000> wsReg4000List);

	public void truncateTaWsReg4000();

	public List<TaWsReg4000> findByCriteria(TaWsReg4000 wsReg4000, int start, int length);

	public Long countByCriteria(TaWsReg4000 wsReg4000);
	
}
