package th.go.excise.ims.ta.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.ta.persistence.entity.TaPaperBaH;

public interface TaPaperBaHRepository extends CommonJpaCrudRepository<TaPaperBaH, Long> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.paperBaNumber = :paperBaNumber")
	public TaPaperBaH findByPaperBaNumber(@Param("paperBaNumber") String paperBaNumber);
	
	@Query("select new java.lang.String(e.paperBaNumber) from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "' and e.officeCode = :officeCode order by e.paperBaNumber desc")
	public List<String> findPaperBaNumberByOfficeCode(@Param("officeCode") String officeCode);
	
}
