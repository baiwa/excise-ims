package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;

public interface TaPlanWorksheetSendRepository extends CommonJpaCrudRepository<TaPlanWorksheetSend, Long> {

	public List<TaPlanWorksheetSend> findByBudgetYear(String budgetYear);

	public TaPlanWorksheetSend findByPlanNumberAndOfficeCode(String planNumber, String officeCode);

	public TaPlanWorksheetSend findByPlanNumberAndOfficeCodeAndSubmitDateIsNull(String planNumber, String officeCode);

	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode and e.budgetYear = :budgetYear")
	public TaPlanWorksheetSend findByOfficeCodeAndBudgetYear(@Param("officeCode") String officeCode, @Param("budgetYear") String budgetYear);
}
