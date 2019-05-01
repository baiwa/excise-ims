package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaAuditIncD3;

public interface IaAuditIncD3RepositoryCustom {
	public void batchInsert( List<IaAuditIncD3> wsAuditIncD3List );
}
