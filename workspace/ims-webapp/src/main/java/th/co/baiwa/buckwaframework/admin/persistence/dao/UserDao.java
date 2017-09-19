//package th.co.baiwa.framework.admin.persistence.dao;
//
//import java.util.List;
//
//import javax.persistence.Query;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Repository;
//import org.springframework.util.CollectionUtils;
//
//import th.co.baiwa.framework.admin.persistence.entity.User;
//import th.co.baiwa.framework.common.constant.CommonConstants.FLAG;
//import th.co.baiwa.framework.common.persistence.dao.BaseDomainJpaDaoImpl;
//
//@Repository("userDao")
//public class UserDao {
//
//	private final Logger logger = LoggerFactory.getLogger(this.getClass());
//
//	public User findByUsername(String username) {
//		logger.info("findByUsername username=" + username);
//
//		Query query = getEntityManager().createQuery(" SELECT e FROM " + getEntityClass().getName() + " e "
//				+ " WHERE e.isDeleted=:isDeleted " + " AND e.username=:username ");
//
//		query.setParameter("isDeleted", FLAG.N_FLAG);
//		query.setParameter("username", username);
//
//		@SuppressWarnings("unchecked")
//		List<User> resultList = query.getResultList();
//
//		User result = null;
//		if (!CollectionUtils.isEmpty(resultList)) {
//			result = resultList.get(0);
//		}
//
//		return result;
//	}
//
//	public Long queryCountUserList() {
//		return super.count();
//	}
//
//	public List<User> queryUserList(Integer start, Integer length) {
//		Query query = getEntityManager().createQuery("from " + getEntityClass().getName() +  " e WHERE e.isDeleted='N'");
//		query.setFirstResult(start);
//		query.setMaxResults(length);
//		
//		@SuppressWarnings("unchecked")
//		List<User> resultList = query.getResultList();
//		
//		return resultList;
//	}
//
//}