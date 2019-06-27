package th.go.excise.ims.ws.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ws.persistence.entity.WsIncfri8020Inc;

public interface WsIncfri8020IncRepository extends CommonJpaCrudRepository<WsIncfri8020Inc, Long>, WsIncfri8020IncRepositoryCustom {
//	@Query(
//			value = " SELECT DEPT_DISB, GL_ACC_NO FROM WS_INCFRI8020_INC " +
//					" WHERE OFFICE_RECEIVE LIKE ?1 " +
//					" GROUP BY DEPT_DISB, GL_ACC_NO " +
//					" ORDER BY DEPT_DISB "
//					,
//			nativeQuery = true
//		)
//		public List<WsIncfri8020Inc> findTabs(String officeCode);
}
