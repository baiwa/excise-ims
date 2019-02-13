
package th.go.excise.ims.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public interface TaWorksheetHdrRepository extends CommonJpaCrudRepository<TaWorksheetHdr, Long>  , TaWorksheetHdrCustom{

}
