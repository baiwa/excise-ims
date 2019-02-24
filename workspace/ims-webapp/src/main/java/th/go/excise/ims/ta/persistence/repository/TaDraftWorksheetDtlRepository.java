
package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetDtl;

public interface TaDraftWorksheetDtlRepository extends CommonJpaCrudRepository<TaDraftWorksheetDtl, Long>, TaDraftWorksheetDtlCustom {
	public List<TaDraftWorksheetDtl> findByDraftNumber(String draftNumber);
}
