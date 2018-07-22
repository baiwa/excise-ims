package th.co.baiwa.buckwaframework.common.persistence.repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

@NoRepositoryBean
public interface CommonJpaPagingAndSortingRepository<T extends BaseEntity, ID extends Serializable> extends CommonJpaCrudRepository<T, ID> {
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "'")
	public List<T> findAll(Sort sort);
	
	@Query("select e from #{#entityName} e where e.isDeleted = '" + FLAG.N_FLAG + "'")
	public Page<T> findAll(Pageable pageable);
	
}
