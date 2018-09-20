package th.co.baiwa.excise.ia.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.excise.ia.persistence.entity.IaProcurementList;

public interface IaProcurementListRepository extends CommonJpaCrudRepository<IaProcurementList, Long> {
	@Query(
			value = "SELECT * FROM IA_PROCUREMENT_LIST PL WHERE PL.PROCUREMENT_ID = ? AND PL.IS_DELETED='"+FLAG.N_FLAG+"' ",
			nativeQuery = true
	)
	public List<IaProcurementList> findByIdFilter(long procurementId);

}
