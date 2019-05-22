package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsPmPy2D;

public interface WsPmPy2DRepositoryCustom {

	public void batchMerge(List<WsPmPy2D> pmPy2DList);
}
