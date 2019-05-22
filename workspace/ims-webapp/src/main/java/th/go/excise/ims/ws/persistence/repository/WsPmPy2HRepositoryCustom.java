package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsPmPy2H;

public interface WsPmPy2HRepositoryCustom {
	
	public void batchMerge(List<WsPmPy2H> pmPy2HList);
}
