
package th.go.excise.ims.ia.persistence.repository;

import java.math.BigDecimal;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ia.persistence.entity.IaRepDisbPerMonth;

public interface IaRepDisbPerMonthRepository extends CommonJpaCrudRepository<IaRepDisbPerMonth, BigDecimal> ,IaRepDisbPerMonthRepositoryCustom {

}
