package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.go.excise.ims.ta.persistence.entity.TaWsReg4000;

public interface TaWsReg4000RepositoryCustom {
	
	public void batchMerge(List<TaWsReg4000> taWsReg4000List);
	
}
