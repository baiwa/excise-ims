package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;

public interface IaEmpWorkingDtlRepositoryCustom {

	public List<IaEmpWorkingDtl> findByMonth(String workingDate);
}
