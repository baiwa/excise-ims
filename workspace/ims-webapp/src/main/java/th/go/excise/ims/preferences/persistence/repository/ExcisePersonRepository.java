package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExcisePerson;
import th.go.excise.ims.ta.persistence.entity.TaPlanWorksheetSend;

public interface ExcisePersonRepository extends CommonJpaCrudRepository<ExcisePerson, Long> {
	
//	@Query("select new th.go.excise.ims.ed.vo.Ed02Vo(e.edPersonName, e.edPositionName, e.edPersonId, e.edOffcode) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' order by e.edOffcode, e.edLogin")
//	public List<Ed02Vo> findAllEd02Vo();
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and edLogin = :edLogin")
	public ExcisePerson findByEdLogin(@Param("edLogin") String edLogin);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.edPersonName like :edPersonName ")
	public List<ExcisePerson> findByEdPersonName(@Param("edPersonName") String edPersonName);
	
}
