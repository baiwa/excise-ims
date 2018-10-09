package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.List;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;

public interface LovRepository extends CommonJpaCrudRepository<Lov, Long>, LovRepositoryCustom {
	
        Lov findByTypeAndLovId(String type, Long lovId);
        Lov findByLovId(Long lovId);        
        List<Lov> findByTypeAndLovIdMasterIsNull(String type);
        List<Lov> findByTypeAndLovIdMasterIsNullOrderBySubType(String type);
        Lov findByTypeAndSubType(String type, String subType);
        List<Lov> findByLovIdMasterOrderBySubType(Long idMaster);
        List<Lov> findByType(String type);
        Lov findBySubType(String officeCode);
}
