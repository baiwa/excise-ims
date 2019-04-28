package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncD2;

public interface IaAuditIncD2RepositoryCustom {
	public void batchInsert( List<IaAuditIncD2> wsAuditIncD2List );
}
