//package th.co.baiwa.framework.security.service;
//
//import java.util.Date;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.LockedException;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
//
//import th.co.baiwa.framework.admin.persistence.dao.UserDao;
//import th.co.baiwa.framework.admin.persistence.entity.User;
//import th.co.baiwa.framework.common.constant.CommonConstants.FLAG;
//import th.co.baiwa.framework.security.persistence.dao.UserAttemptDao;
//import th.co.baiwa.framework.security.persistence.entity.UserAttempt;
//
//@Service("userAttemptService")
//public class UserAttemptService {
//	
//	private static final Logger logger = LoggerFactory.getLogger(UserAttemptService.class);
//	
//	@Autowired
//	private UserAttemptDao userAttemptDao;
//	@Autowired
//	private UserDao userDao;
//	
//	
//	@Transactional
//	public void resetFailAttempt(String username) {
//		logger.info("resetFailAttempt username=" + username);
//		
//		UserAttempt userAttempt = userAttemptDao.findByUsername(username);
//		if (userAttempt == null) {
//			userAttempt = new UserAttempt();
//			userAttempt.setUsername(username);
//			userAttempt.setAttempts(0);
//			userAttempt.setLastModified(new Date());
//			userAttemptDao.persist(userAttempt);
//		} else {
//			userAttempt.setAttempts(0);
//			userAttempt.setLastModified(new Date());
//			userAttemptDao.merge(userAttempt);
//		}
//	}
//	
//	public void updateFailAttempt(String username) {
//		logger.info("updateFailAttempt username=" + username);
//		
//		UserAttempt userAttempt = userAttemptDao.findByUsername(username);
//		if (userAttempt == null) {
//			userAttempt = new UserAttempt();
//			userAttempt.setUsername(username);
//			userAttempt.setAttempts(1);
//			userAttempt.setLastModified(new Date());
//			userAttemptDao.persist(userAttempt);
//		} else {
//			userAttempt.setAttempts(userAttempt.getAttempts() + 1);
//			userAttempt.setLastModified(new Date());
//			userAttemptDao.merge(userAttempt);
//			
////			ParameterInfo maxLoginAttempts = ApplicationCache.getParameterInfoByCode(PARAMETER_GROUP.SYSTEM_CONFIG, SYSTEM_CONFIG.LOGIN_ATTEMPTS);
////			if (FLAG.Y_FLAG.equals(maxLoginAttempts.getValue1()) && userAttempt.getAttempts() + 1 >= Integer.parseInt(maxLoginAttempts.getValue2())) {
////				// Locked User
////				User user = userDao.findByUsername(username);
////				user.setAccountNonLocked(FLAG.N_FLAG);
////				userDao.merge(user);
////				
////				// Throw Exception
////				throw new LockedException("User Account is locked!");
////			}
//		}
//	}
//	
//}
