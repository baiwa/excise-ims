package th.co.baiwa.buckwaframework.common.persistence.repository;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

public abstract class AbstractCommonJpaRepository<T extends BaseEntity, ID extends Serializable> {
	
	private static final String ID_MUST_NOT_BE_NULL = "The given id must not be null!";
	private static final String ENTITY_MUST_NOT_BE_NULL = "The entity must not be null!";
	
	@PersistenceContext
	private EntityManager em;
	
	private Class<T> entityClass;
	
	@SuppressWarnings("unchecked")
	public AbstractCommonJpaRepository() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}
	
	public EntityManager getEm() {
		return em;
	}
	
	public T findOne(ID id) {
		Assert.notNull(id, ID_MUST_NOT_BE_NULL);
		
		T t = em.find(entityClass, id);
		if (t != null && FLAG.N_FLAG.equals(t.getIsDeleted())) {
			return t;
		} else {
			return null;
		}
	}
	
	public boolean exists(ID id) {
		Assert.notNull(id, ID_MUST_NOT_BE_NULL);
		
		return findOne(id) != null;
	}
	
	@Transactional
	public void delete(ID id) {
		Assert.notNull(id, ID_MUST_NOT_BE_NULL);
		
		delete(findOne(id));
	}

	@Transactional
	public void delete(T entity) {
		Assert.notNull(entity, ENTITY_MUST_NOT_BE_NULL);
		
		entity.setIsDeleted(FLAG.Y_FLAG);
		em.merge(entity);
	}
	
}
