package th.co.baiwa.buckwaframework.security.service;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import th.co.baiwa.buckwaframework.accesscontrol.persistence.dao.UserDao;
import th.co.baiwa.buckwaframework.accesscontrol.persistence.entity.User;
import th.co.baiwa.buckwaframework.common.constant.CommonConstants.FLAG;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.PARAMETER_GROUP;
import th.co.baiwa.buckwaframework.preferences.constant.ParameterConstants.SYSTEM_CONFIG;
import th.co.baiwa.buckwaframework.preferences.persistence.dao.SEQDao;
import th.co.baiwa.buckwaframework.preferences.persistence.entity.ParameterInfo;
import th.co.baiwa.buckwaframework.security.persistence.dao.UserAttemptDao;
import th.co.baiwa.buckwaframework.security.persistence.entity.UserAttempt;
import th.co.baiwa.buckwaframework.support.ApplicationCache;

@Service("userAttemptService")
public class UserAttemptService {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAttemptService.class);
	
	@Autowired
	private UserAttemptDao userAttemptDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SEQDao seqDao;
	
	public void resetFailAttempt(String username) {
		logger.info("resetFailAttempt username={}", username);
		
		UserAttempt userAttempt = userAttemptDao.findByUsername(username);
		if (userAttempt == null) {
			userAttempt = new UserAttempt();
			userAttempt.setUserAttemptId(seqDao.autoNumberRunningBySeqName("ADM_USER_ATTEMPT_SEQ").longValue());    
			userAttempt.setUsername(username);
			userAttempt.setAttempts(0);
			userAttempt.setLastModified(new Date());
			userAttemptDao.insert(userAttempt);
		} else {
			userAttempt.setAttempts(0);
			userAttempt.setLastModified(new Date());
			userAttemptDao.update(userAttempt);
		}
	}
	
	@Transactional
	public void updateFailAttempt(String username) {
		logger.info("updateFailAttempt username={}", username);
		
		UserAttempt userAttempt = userAttemptDao.findByUsername(username);
		if (userAttempt == null) {
			userAttempt = new UserAttempt();
			userAttempt.setUserAttemptId(seqDao.autoNumberRunningBySeqName("ADM_USER_ATTEMPT_SEQ").longValue());
			userAttempt.setUsername(username);
			userAttempt.setAttempts(1);
			userAttempt.setLastModified(new Date());
			userAttemptDao.insert(userAttempt);
		} else {
			userAttempt.setAttempts(userAttempt.getAttempts() + 1);
			userAttempt.setLastModified(new Date());
			userAttemptDao.update(userAttempt);
			
			ParameterInfo maxLoginAttempts = ApplicationCache.getParameterInfoByCode(PARAMETER_GROUP.SYSTEM_CONFIG, SYSTEM_CONFIG.LOGIN_ATTEMPTS);
			if (FLAG.Y_FLAG.equals(maxLoginAttempts.getValue1()) && userAttempt.getAttempts() + 1 >= Integer.parseInt(maxLoginAttempts.getValue2())) {
				// Locked User
				User user = userDao.findByUsername(username);
				user.setAccountNonLocked(FLAG.N_FLAG);
				userDao.update(user);
				
				// Throw Exception
				throw new LockedException("User Account is locked!");
			}
		}
	}
	
	public UserAttempt getUserAttemptByUsername(String username) {
		logger.info("getUserAttemptByUsername username={}", username);
		return userAttemptDao.findByUsername(username);
	}
	
}
