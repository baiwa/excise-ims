package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaRepDisbPerMonth;

public interface IaRepDisbPerMonthRepositoryCustom {
	

	public void batchInsert(List<IaRepDisbPerMonth> iaRepDisbPerMonths) ;
}
