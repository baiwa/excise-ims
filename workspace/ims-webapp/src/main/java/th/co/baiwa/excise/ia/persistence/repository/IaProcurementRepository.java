package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurement;
import th.co.baiwa.excise.ia.persistence.vo.Int0541Vo;

public interface IaProcurementRepository extends CommonJpaCrudRepository<IaProcurement, Long> {
	@Query(
			value = "SELECT * FROM IA_PROCUREMENT P WHERE P.BUDGET_YEAR = ? AND P.BUDGET_TYPE = ? AND P.SUPPLY_CHOICE = ? AND P.IS_DELETED='"+FLAG.N_FLAG+"' ORDER BY P.PROCUREMENT_ID ASC",
			nativeQuery = true
	)
	public List<IaProcurement> oderByfilter(String budgetYear, String budgetType, String supplyChoice);

}
