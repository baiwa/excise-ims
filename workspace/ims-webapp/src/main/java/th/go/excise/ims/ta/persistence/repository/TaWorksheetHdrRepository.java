package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaWorksheetHdr;

public interface TaWorksheetHdrRepository extends CommonJpaCrudRepository<TaWorksheetHdr, Long>, TaWorksheetHdrRepositoryCustom {

	@Query("select new java.lang.String(e.analysisNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode order by e.analysisNumber desc")
	public List<String> findAllAnalysisNumberByOfficeCode(@Param("officeCode") String officeCode);

}
