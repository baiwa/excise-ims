package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaDraftWorksheetDtl;

public interface TaDraftWorksheetDtlRepository extends CommonJpaCrudRepository<TaDraftWorksheetDtl, Long>, TaDraftWorksheetDtlCustom {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.draftNumber = :draftNumber")
	public List<TaDraftWorksheetDtl> findByDraftNumber(String draftNumber);
	
}
