package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskQtnConfig;

public interface IaRiskQtnConfigRepository 
extends CommonJpaCrudRepository<IaRiskQtnConfig, BigDecimal>
{

	public IaRiskQtnConfig findByIdQtnHdrAndIsDeleted(BigDecimal idQtnHdr, String idDeleted);
	
}
