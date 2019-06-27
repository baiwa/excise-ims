
package th.go.excise.ims.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.go.excise.ims.preferences.persistence.entity.ExciseDepaccMas;

public interface ExciseDepaccMasRepository
		extends CommonJpaCrudRepository<ExciseDepaccMas, String>, ExciseDepaccMasRepositoryCustom {
	
	
	@Query(value = " SELECT * FROM EXCISE_DEPACC_MAS WHERE IS_DELETED = '" + FLAG.N_FLAG + "' ", nativeQuery = true)
	public List<ExciseDepaccMas> listData();
}
