package th.go.excise.ims.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSelect;

public interface TaPlanWorksheetSelectRepository extends CommonJpaCrudRepository<TaPlanWorksheetSelect, Long>, TaPlanWorksheetSelectRepositoryCustom {

}
