package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import th.go.excise.ims.ws.persistence.entity.WsPmAssessH;

public interface WsPmAssessHRepositoryCustom {
	public void batchMerge(List<WsPmAssessH> pmAssessHList);
}
