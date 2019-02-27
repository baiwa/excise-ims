package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import java.util.Optional;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRiskQtnConfig;

public interface IaRiskQtnConfigRepository 
extends CommonJpaCrudRepository<IaRiskQtnConfig, BigDecimal>
{

	public Optional<IaRiskQtnConfig> findByIdQtnHdr(BigDecimal idQtnHdr);
	
}
