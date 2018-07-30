package th.co.baiwa.buckwaframework.common.persistence.repository.support;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;

import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.common.persistence.entity.BaseEntity;

/*
 * @Author: Taechapon Himarat (Su)
 * @Create: Jul 27, 2018
 */
public class CommonSimpleJpaRepository<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> {

	private final EntityManager em;
	private Class<T> entityClass;

	public CommonSimpleJpaRepository(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager);
		this.em = entityManager;
		this.entityClass = entityInformation.getJavaType();
	}

	@Override
	public T findOne(ID id) {
		if (BaseEntity.class.isAssignableFrom(entityClass)) {
			T entity = super.findOne(id);
			if (FLAG.N_FLAG.equals(((BaseEntity) entity).getIsDeleted())) {
				return entity;
			} else {
				return null;
			}
		} else {
			return super.findOne(id);
		}
	}

	@Override
	public List<T> findAll(Iterable<ID> ids) {
		if (BaseEntity.class.isAssignableFrom(entityClass)) {
			List<T> results = new ArrayList<T>();
			for (ID id : ids) {
				results.add(findOne(id));
			}
			results.removeIf(Objects::isNull);
			return results;
		} else {
			return super.findAll(ids);
		}
	}

	@Override
	public boolean exists(ID id) {
		if (BaseEntity.class.isAssignableFrom(entityClass)) {
			return findOne(id) != null;
		} else {
			return super.exists(id);
		}
	}

	@Override
	@Transactional
	public void delete(T entity) {
		if (BaseEntity.class.isAssignableFrom(entityClass)) {
			BaseEntity baseEntity = (BaseEntity) entity;
			baseEntity.setIsDeleted(FLAG.Y_FLAG);
			em.merge(baseEntity);
		} else {
			super.delete(entity);
		}
	}

	@Transactional
	public void delete(Collection<ID> ids) {
		for (ID id : ids) {
			delete(id);
		}
	}

}
