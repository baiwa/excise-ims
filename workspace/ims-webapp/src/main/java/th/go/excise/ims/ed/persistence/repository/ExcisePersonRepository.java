package th.go.excise.ims.ed.persistence.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ed.persistence.entity.ExcisePerson;

public interface ExcisePersonRepository extends CommonJpaCrudRepository<ExcisePerson, Long> {
	
//	@Query("select new th.go.excise.ims.ed.vo.Ed02Vo(e.edPersonName, e.edPositionName, e.edPersonId, e.edOffcode) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' order by e.edOffcode, e.edLogin")
//	public List<Ed02Vo> findAllEd02Vo();
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and edLogin = :edLogin")
	public ExcisePerson findByEdLogin(@Param("edLogin") String edLogin);
	
}
