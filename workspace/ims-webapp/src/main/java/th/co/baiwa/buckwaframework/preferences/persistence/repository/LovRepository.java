package th.co.baiwa.buckwaframework.preferences.persistence.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import th.co.baiwa.buckwaframework.common.persistence.repository.CommonJpaCrudRepository;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.Lov;

public interface LovRepository extends CommonJpaCrudRepository<Lov, Long>, LovRepositoryCustom {
	
        public Lov findByTypeAndLovId(String type, Long lovId);
        public Lov findByLovId(Long lovId);        
        public List<Lov> findByTypeAndLovIdMasterIsNull(String type);
        public List<Lov> findByTypeAndLovIdMasterIsNullOrderBySubType(String type);
        public Lov findByTypeAndSubType(String type, String subType);
        public List<Lov> findByLovIdMasterOrderBySubType(Long idMaster);
        public List<Lov> findByType(String type);
        public Lov findBySubType(String officeCode);
        public List<Lov> findByTypeAndLovIdMaster(String type, Long idMaster);
        
        @Query(
    			value = "SELECT * FROM sys_lov L  WHERE L.TYPE = 'SECTOR_LIST' AND L.value1 LIKE '%?1%' AND L.sub_type LIKE '____00' ",
    			nativeQuery = true
    	)
        public Lov findAreaByProvice(String provice);
}
