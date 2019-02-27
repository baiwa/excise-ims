package th.go.excise.ims.ta.persistence.repository;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetDtl;

import java.util.List;

public interface TaPlanWorksheetDtlRepository extends CommonJpaCrudRepository<TaPlanWorksheetDtl, Long>,TaPlanWorksheetDtlRepositoryCustom {

    List<TaPlanWorksheetDtl> findByAnalysisNumberAndPlanNumberAndOfficeCodeAndIsDeleted(String analysisNumber, String planNumber, String officeCode, String isDeleted);    
}
