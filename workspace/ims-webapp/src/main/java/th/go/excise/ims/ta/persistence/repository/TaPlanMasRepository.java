package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPlanMas;

public interface TaPlanMasRepository extends CommonJpaCrudRepository<TaPlanMas, Long> {

	public List<TaPlanMas> findByBudgetYearAndOfficeCode(String budgetYear,String officeCode);

}
