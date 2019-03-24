package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaFormTs0104;

public interface TaFormTs0104Repository extends CommonJpaCrudRepository<TaFormTs0104, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.formTsNumber = :formTsNumber")
	public TaFormTs0104 findByFormTsNumber(@Param("formTsNumber") String formTsNumber);
	
	@Query("select new java.lang.String(e.formTsNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode order by e.formTsNumber desc")
	public List<String> findFormTsNumberByOfficeCode(@Param("officeCode") String officeCode);
	
}
