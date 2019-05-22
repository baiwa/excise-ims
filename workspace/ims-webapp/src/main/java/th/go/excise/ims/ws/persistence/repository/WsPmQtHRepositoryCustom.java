package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsPmQtH;

public interface WsPmQtHRepositoryCustom {
	
	public void batchMerge(List<WsPmQtH> pmQtHList);

}
