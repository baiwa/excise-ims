package th.go.excise.ims.ia.persistence.repository;

import java.util.List;

import th.go.excise.ims.ia.persistence.entity.IaEmpWorkingDtl;
import th.go.excise.ims.ia.vo.IaEmpWorkingDtlSaveVo;

public interface IaEmpWorkingDtlRepositoryCustom {

	public List<IaEmpWorkingDtl> findByMonth(IaEmpWorkingDtlSaveVo formVo);
}
