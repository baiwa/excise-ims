
package th.go.excise.ims.preferences.persistence.repository;

import java.math.BigDecimal;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseGeo;

public interface ExciseGeoRepository extends CommonJpaCrudRepository<ExciseGeo, BigDecimal> , ExciseGeoCustom {

}
