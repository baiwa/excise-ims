
package th.go.excise.ims.preferences.persistence.repository;

import java.math.BigDecimal;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseProvice;

public interface ExciseProviceRepository extends CommonJpaCrudRepository<ExciseProvice, BigDecimal>  ,ExciseProviceCustom{

}
