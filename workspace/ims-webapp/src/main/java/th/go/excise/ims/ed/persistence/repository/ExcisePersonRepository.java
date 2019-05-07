
package th.go.excise.ims.ed.persistence.repository;

import java.math.BigDecimal;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ed.persistence.entity.ExcisePerson;

public interface ExcisePersonRepository
    extends CommonJpaCrudRepository<ExcisePerson, BigDecimal>
{


}
